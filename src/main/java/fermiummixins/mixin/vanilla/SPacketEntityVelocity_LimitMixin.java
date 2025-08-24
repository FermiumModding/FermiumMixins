package fermiummixins.mixin.vanilla;

import net.minecraft.network.play.server.SPacketEntityVelocity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

/**
 * Fix by Nischhelm
 */
@Mixin(SPacketEntityVelocity.class)
public abstract class SPacketEntityVelocity_LimitMixin {
    
    // see https://youtu.be/Q_MkxSD33Vw?si=uDKlxttiKw21lXuW&t=1335
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
}