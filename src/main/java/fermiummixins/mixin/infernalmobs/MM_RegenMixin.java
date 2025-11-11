package fermiummixins.mixin.infernalmobs;

import atomicstryker.infernalmobs.common.mods.MM_Regen;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MM_Regen.class)
public abstract class MM_RegenMixin {
	@Redirect(
			method = "onUpdate",
			at = @At(value = "INVOKE", target = "Ljava/lang/System;currentTimeMillis()J"),
			remap = false
	)
	public long onUpdate(EntityLivingBase mob) {
		return (long) mob.ticksExisted * 50; //convert to milliseconds
	}
}