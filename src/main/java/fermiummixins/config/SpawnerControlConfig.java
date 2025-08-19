package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class SpawnerControlConfig {
	
	@Config.Comment("Makes curing Zombie Villagers and Pig lightning conversions count as a kill for spawners")
	@Config.Name("Curing/Conversion Ticks Spawners (MobSpawnerControl)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.spawnercontrol.curing.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.SpawnerControl_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean curingTicksSpawners = false;
	
	@Config.Comment("After a spawner is broken, prevents mob drops after a certain threshold of kills is reached to prevent farming exploits")
	@Config.Name("Spawner Farming Fix (MobSpawnerControl)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.spawnercontrol.farmingfix.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.SpawnerControl_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean spawnerFarmingFix = false;
	
	@Config.Comment("Additional kill threshold before mob drops will be cancelled after a spawner breaks" + "\n" +
			"Requires \"Spawner Farming Fix (MobSpawnerControl)\" enabled")
	@Config.Name("Spawner Farming Fix Threshold")
	public int spawnerFarmingFixThreshold = 20;

	@Config.Comment("Makes Lycanites entities that transform into new entities count as a kill for spawners")
	@Config.Name("Lycanites Transforming Ticks Spawners (MobSpawnerControl/LycanitesMobs)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.spawnercontrol.lycanitesmobs.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.SpawnerControl_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.LycanitesMobs_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean lycanitesTicksSpawners = false;
}