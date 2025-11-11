package fermiummixins.mixin.infernalmobs;

import atomicstryker.infernalmobs.common.InfernalMobsCore;
import atomicstryker.infernalmobs.common.MobModifier;
import com.llamalad7.mixinextras.sugar.Local;
import fermiummixins.handlers.ConfigHandler;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InfernalMobsCore.class)
public abstract class InfernalMobsCore_SetHealthMixin {
	/**
	 * @author Nischhelm
	 * @reason idk why stryker thought past max is a good idea
	 */
	@Overwrite(remap = false)
	public void setEntityHealthPastMax(EntityLivingBase entity, float amount) {
		entity.setHealth(amount);
	}

	@Inject(
			method = "processEntitySpawn",
			at = @At(value = "INVOKE", target = "Latomicstryker/infernalmobs/common/MobModifier;onSpawningComplete(Lnet/minecraft/entity/EntityLivingBase;)V", shift = At.Shift.AFTER),
			remap = false
	)
	private void fermiumMixins_infernalMobsInfernalMobsCore_processEntitySpawn(EntityLivingBase entity, CallbackInfo ci, @Local MobModifier mod){
		if(ConfigHandler.INFERNALMOBS_CONFIG.fixAdditionalHealth) {
			mod.getActualMaxHealth(entity); //update max health on initial modifier set
			entity.setHealth(entity.getMaxHealth()); //heal to max
		}
	}
}