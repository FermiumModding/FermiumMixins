package fermiummixins.mixin.infernalmobs;

import atomicstryker.infernalmobs.common.InfernalMobsCore;
import atomicstryker.infernalmobs.common.MobModifier;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(InfernalMobsCore.class)
public abstract class InfernalMobsCore_SetHealthMixin {
	@Unique private static final UUID fermiumMixins$hpUuid_increase = UUID.fromString("92bf6d29-8f71-4b07-b723-3c1c8080603d");

	/**
	 * @author Nischhelm
	 * @reason use attribute modifiers instead of a whole custom system
	 */
	@Overwrite(remap = false)
	public void setEntityHealthPastMax(EntityLivingBase entity, float amount) {
		IAttributeInstance hpAttr = entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
		double currIncrease = 0;

		AttributeModifier mod = hpAttr.getModifier(fermiumMixins$hpUuid_increase);
		if(mod != null) {
			currIncrease = mod.getAmount();
			hpAttr.removeModifier(fermiumMixins$hpUuid_increase);
		}

		//assumes all calls to setEntityHealthPastMax use entity.getHealth + x which is true in infernalmobs internally (minus 1UP, handled separately)
		// and tries to account for the new op2 modifier for modifier count
		double newIncrease = (amount - entity.getHealth()) / InfernalMobsCore.getMobModifiers(entity).getModSize() / InfernalMobsCore.instance().getMobModHealthFactor();

		hpAttr.applyModifier(new AttributeModifier(fermiumMixins$hpUuid_increase,"infernalmobs_hp_extra", currIncrease + newIncrease, 0));

		entity.setHealth(amount);
	}

	@Inject(
			method = "processEntitySpawn",
			at = @At(value = "INVOKE", target = "Latomicstryker/infernalmobs/common/MobModifier;onSpawningComplete(Lnet/minecraft/entity/EntityLivingBase;)V", shift = At.Shift.AFTER),
			remap = false
	)
	private void fermiumMixins_infernalMobsInfernalMobsCore_processEntitySpawn(EntityLivingBase entity, CallbackInfo ci, @Local MobModifier mod){
		mod.getActualHealth(entity); //update health on initial modifier set
	}
}