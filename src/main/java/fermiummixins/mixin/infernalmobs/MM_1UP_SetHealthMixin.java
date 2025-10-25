package fermiummixins.mixin.infernalmobs;

import atomicstryker.infernalmobs.common.InfernalMobsCore;
import atomicstryker.infernalmobs.common.mods.MM_1UP;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MM_1UP.class)
public abstract class MM_1UP_SetHealthMixin {
	@Redirect(method = "onUpdate", at = @At(value = "INVOKE", target = "Latomicstryker/infernalmobs/common/InfernalMobsCore;setEntityHealthPastMax(Lnet/minecraft/entity/EntityLivingBase;F)V"), remap = false)
	public void fermiumMixins_infernalMobsMM_1UP_onUpdate(InfernalMobsCore instance, EntityLivingBase entity, float amount) {
		entity.setHealth(entity.getMaxHealth()); //not actually past max
	}
}