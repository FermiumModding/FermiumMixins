package fermiummixins.mixin.vanilla;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Fix by Nischhelm
 */
// see https://youtu.be/Q_MkxSD33Vw?si=uDKlxttiKw21lXuW&t=1335
@Mixin(SPacketEntityVelocity.class)
public abstract class SPacketEntityVelocity_LimitMixin {

    @Shadow private int motionX;
    @Shadow private int motionY;
    @Shadow private int motionZ;
    @Unique private boolean fermiummixins$isOverLimit = false;

    //Don't clamp values to fit short limit
    @ModifyConstant(
            method = "<init>(IDDD)V",
            constant = @Constant(doubleValue = -3.9D)
    )
    private double fermiummixins_vanillaSPacketEntityVelocity_init_negLimit(double constant) {
        return -Double.MAX_VALUE;
    }

    @ModifyConstant(
            method = "<init>(IDDD)V",
            constant = @Constant(doubleValue = 3.9D)
    )
    private double fermiummixins_vanillaSPacketEntityVelocity_init_posLimit(double constant) {
        return Double.MAX_VALUE;
    }

    //Check if over short limit (~ 8000 x +-3.9)
    @Inject(
            method = "<init>(IDDD)V",
            at = @At("TAIL")
    )
    private void fermiummixins_vanillaSPacketEntityVelocity_init(int entityIdIn, double motionXIn, double motionYIn, double motionZIn, CallbackInfo ci) {
        this.fermiummixins$isOverLimit = Math.abs(motionXIn) > 3.9 || Math.abs(motionYIn) > 3.9 || Math.abs(motionZIn) > 3.9;
    }

    //read extra boolean and switch to int if over limit
    @Inject(
            method = "readPacketData",
            at = @At(value = "FIELD", target = "Lnet/minecraft/network/play/server/SPacketEntityVelocity;entityID:I", opcode = Opcodes.PUTFIELD, shift = At.Shift.AFTER),
            cancellable = true
    )
    private void fermiummixins_vanillaSPacketEntityVelocity_readPacketData(PacketBuffer buf, CallbackInfo ci){
        this.fermiummixins$isOverLimit = buf.readBoolean();
        if(!this.fermiummixins$isOverLimit) return;

        this.motionX = buf.readInt();
        this.motionY = buf.readInt();
        this.motionZ = buf.readInt();
        ci.cancel();
    }

    //write extra boolean and switch to int if over limit
    @Inject(
            method = "writePacketData",
            at = @At(value = "FIELD", target = "Lnet/minecraft/network/play/server/SPacketEntityVelocity;motionX:I", opcode = Opcodes.GETFIELD),
            cancellable = true
    )
    private void fermiummixins_vanillaSPacketEntityVelocity_writePacketData(PacketBuffer buf, CallbackInfo ci){
        buf.writeBoolean(this.fermiummixins$isOverLimit);
        if(!this.fermiummixins$isOverLimit) return;

        buf.writeInt(this.motionX);
        buf.writeInt(this.motionY);
        buf.writeInt(this.motionZ);
        ci.cancel();
    }
}