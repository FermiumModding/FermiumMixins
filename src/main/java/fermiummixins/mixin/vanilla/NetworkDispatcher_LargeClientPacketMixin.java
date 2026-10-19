package fermiummixins.mixin.vanilla;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.handshake.NetworkDispatcher;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import net.minecraftforge.fml.relauncher.Side;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.IOException;

/**
 * Fix by Fresh-glitch
 */
//Forge splits large server packets into FML|MP parts but not client ones, so a client packet over 32767 bytes kicks the client
@Mixin(value = NetworkDispatcher.class, remap = false)
public abstract class NetworkDispatcher_LargeClientPacketMixin {

	@Unique private static final int fermiummixins$PART_SIZE = 32767;
	@Unique private static final int fermiummixins$MAX_LENGTH = 2097152;

	@Shadow @Final private Side side;

	@Unique private String fermiummixins$channel;
	@Unique private byte[] fermiummixins$data;
	@Unique private int fermiummixins$parts;
	@Unique private int fermiummixins$received;
	@Unique private int fermiummixins$offset;

	@Shadow
	private void kickWithMessage(String message) {}

	//Send client payloads over the limit as FML|MP parts, the same way Forge splits server payloads
	@Inject(
			method = "write",
			at = @At("HEAD"),
			cancellable = true
	)
	private void fermiummixins_vanillaNetworkDispatcher_write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise, CallbackInfo ci) {
		if(this.side != Side.CLIENT || !(msg instanceof FMLProxyPacket)) return;
		FMLProxyPacket packet = (FMLProxyPacket)msg;
		int length = packet.payload().writerIndex();
		if(length <= fermiummixins$PART_SIZE || length > fermiummixins$MAX_LENGTH) return;

		byte[] data = new byte[length];
		packet.payload().getBytes(0, data);
		int parts = (int)Math.ceil(length / (double)(fermiummixins$PART_SIZE - 1));

		PacketBuffer preamble = new PacketBuffer(Unpooled.buffer());
		preamble.writeString(packet.channel());
		preamble.writeByte(parts);
		preamble.writeInt(length);
		ctx.write(new CPacketCustomPayload("FML|MP", preamble), ctx.voidPromise());

		for(int part = 0, offset = 0; part < parts; part++) {
			byte[] tmp = new byte[Math.min(fermiummixins$PART_SIZE - 1, length - offset) + 1];
			tmp[0] = (byte)part;
			System.arraycopy(data, offset, tmp, 1, tmp.length - 1);
			offset += tmp.length - 1;
			ctx.write(new CPacketCustomPayload("FML|MP", new PacketBuffer(Unpooled.wrappedBuffer(tmp))), part == parts - 1 ? promise : ctx.voidPromise());
		}
		ci.cancel();
	}

	//Put client FML|MP parts back together and pass the whole packet on like any other
	@Inject(
			method = "handleServerSideCustomPacket",
			at = @At("HEAD"),
			cancellable = true
	)
	private void fermiummixins_vanillaNetworkDispatcher_handleServerSideCustomPacket(CPacketCustomPayload msg, ChannelHandlerContext context, CallbackInfoReturnable<Boolean> cir) {
		if(!"FML|MP".equals(msg.getChannelName())) return;
		PacketBuffer buf = msg.getBufferData();
		try {
			if(this.fermiummixins$data == null) {
				String channel = buf.readString(20);
				int parts = buf.readUnsignedByte();
				int length = buf.readInt();
				if(length <= fermiummixins$PART_SIZE || length > fermiummixins$MAX_LENGTH || (long)parts * (fermiummixins$PART_SIZE - 1) < length) {
					throw new IOException("Received a client FML MultiPart packet of invalid length " + length);
				}
				this.fermiummixins$channel = channel;
				this.fermiummixins$data = new byte[length];
				this.fermiummixins$parts = parts;
				this.fermiummixins$received = 0;
				this.fermiummixins$offset = 0;
			}
			else {
				int part = buf.readUnsignedByte();
				int length = buf.readableBytes();
				if(part != this.fermiummixins$received || this.fermiummixins$offset + length > this.fermiummixins$data.length) {
					throw new IOException("Received a client FML MultiPart packet out of order or too long");
				}
				buf.readBytes(this.fermiummixins$data, this.fermiummixins$offset, length);
				this.fermiummixins$offset += length;
				this.fermiummixins$received++;

				if(this.fermiummixins$received == this.fermiummixins$parts) {
					String channel = this.fermiummixins$channel;
					byte[] data = this.fermiummixins$data;
					boolean complete = this.fermiummixins$offset == data.length;
					this.fermiummixins$data = null;
					if(!complete) throw new IOException("Received an incomplete client FML MultiPart packet");
					if(NetworkRegistry.INSTANCE.hasChannel(channel, Side.SERVER)) {
						FMLProxyPacket proxy = new FMLProxyPacket(new PacketBuffer(Unpooled.wrappedBuffer(data)), channel);
						proxy.setDispatcher((NetworkDispatcher)(Object)this);
						context.fireChannelRead(proxy);
					}
				}
			}
		}
		catch(IOException | RuntimeException e) {
			this.fermiummixins$data = null;
			this.kickWithMessage(e.getMessage());
		}
		finally {
			buf.release();
		}
		cir.setReturnValue(true);
	}
}
