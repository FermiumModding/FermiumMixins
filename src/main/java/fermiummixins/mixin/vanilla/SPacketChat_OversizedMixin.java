package fermiummixins.mixin.vanilla;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import fermiummixins.handlers.vanilla.OversizedChatHandler;
import io.netty.handler.codec.EncoderException;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * Fix by Fresh-glitch
 */
//An item's hover text carries its whole NBT, so a message naming a heavy item can pass the 32767 byte string limit and kick whoever receives it
@Mixin(SPacketChat.class)
public abstract class SPacketChat_OversizedMixin {

	//writeString throws before writing anything, so the shortened message can be written in its place
	@WrapOperation(
			method = "writePacketData",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/network/PacketBuffer;writeTextComponent(Lnet/minecraft/util/text/ITextComponent;)Lnet/minecraft/network/PacketBuffer;")
	)
	private PacketBuffer fermiummixins_vanillaSPacketChat_writePacketData(PacketBuffer buf, ITextComponent component, Operation<PacketBuffer> original) {
		try {
			return original.call(buf, component);
		}
		catch(EncoderException e) {
			return original.call(buf, OversizedChatHandler.shrink(component));
		}
	}
}
