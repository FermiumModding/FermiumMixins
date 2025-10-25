package fermiummixins.mixin.infernalmobs;

import atomicstryker.infernalmobs.common.InfernalMobsCore;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "atomicstryker.infernalmobs.common.network.MobModsPacket$ScheduledCode")
public abstract class MobModsPacket_SetHealthMixin {
	@Redirect(method = "run", at = @At(value = "INVOKE", target = "Latomicstryker/infernalmobs/common/InfernalMobsCore;sendHealthPacket(Lnet/minecraft/entity/EntityLivingBase;)V"), remap = false)
	public void fermiumMixins_infernalMobsMobModsPacket_run(InfernalMobsCore instance, EntityLivingBase mob) {
		//no op, no need to update client
	}
}