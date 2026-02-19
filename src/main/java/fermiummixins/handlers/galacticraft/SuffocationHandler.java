package fermiummixins.handlers.galacticraft;

import fermiummixins.handlers.ConfigHandler;
import micdoodle8.mods.galacticraft.api.event.oxygen.GCCoreOxygenSuffocationEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SuffocationHandler {
	
	@SubscribeEvent
	public static void onSuffocationPre(GCCoreOxygenSuffocationEvent.Pre event) {
		EntityLivingBase entity = event.getEntityLiving();
		if(entity == null) return;
		if(ConfigHandler.GALACTICRAFT_CONFIG.isEntityImmune(entity)) event.setCanceled(true);
	}
}