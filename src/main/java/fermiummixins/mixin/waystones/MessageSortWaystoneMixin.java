package fermiummixins.mixin.waystones;

import io.netty.buffer.ByteBuf;
import net.blay09.mods.waystones.network.message.MessageSortWaystone;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MessageSortWaystone.class)
public abstract class MessageSortWaystoneMixin {
	//use readInt/writeInt instead of readByte/writeByte for the int values that are sent
	@Shadow(remap = false) private int index;
	@Shadow(remap = false) private int otherIndex;

	@Inject(
			method = "fromBytes",
			at = @At(value = "TAIL"),
			remap = false
	)
    private void fermiumMixins_waystonesMessageSortWaystone_fromBytes(ByteBuf buf, CallbackInfo ci) {
		this.index = buf.readInt();
		this.otherIndex = buf.readInt();
	}

	@Inject(
			method = "toBytes",
			at = @At(value = "TAIL"),
			remap = false
	)
	private void fermiumMixins_waystonesMessageSortWaystone_toBytes(ByteBuf buf, CallbackInfo ci) {
		buf.writeInt(this.index);
		buf.writeInt(this.otherIndex);
	}
}