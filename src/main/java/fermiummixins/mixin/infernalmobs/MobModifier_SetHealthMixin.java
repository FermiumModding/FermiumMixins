package fermiummixins.mixin.infernalmobs;

import atomicstryker.infernalmobs.common.InfernalMobsCore;
import atomicstryker.infernalmobs.common.MobModifier;
import fermiummixins.handlers.ConfigHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.UUID;

@Mixin(MobModifier.class)
public abstract class MobModifier_SetHealthMixin {
	@Shadow(remap = false) public abstract int getModSize();
	@Unique private static final UUID fermiumMixins$hpUuid_modifierCount = UUID.fromString("0ad3c584-7c75-42d5-992e-dc9c60ae656f");

	/**
	 * @author Nischhelm
	 * @reason use attribute modifiers instead of a whole custom system
	 */
	@Overwrite(remap = false)
	public float getActualMaxHealth(EntityLivingBase mob) {
		if(ConfigHandler.INFERNALMOBS_CONFIG.fixAdditionalHealth) {
			IAttributeInstance hpAttr = mob.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
			if (hpAttr.getModifier(fermiumMixins$hpUuid_modifierCount) == null)
				hpAttr.applyModifier(new AttributeModifier(fermiumMixins$hpUuid_modifierCount, "infernalmobs_hp_per_modifier", this.getModSize() * InfernalMobsCore.instance().getMobModHealthFactor() - 1, 2));
		}

		return mob.getMaxHealth();
	}

	/**
	 * @author Nischhelm
	 * @reason no need for custom sync to client
	 */
	@Overwrite(remap = false)
	public float getActualHealth(EntityLivingBase mob) {
		return mob.getHealth();
	}
}