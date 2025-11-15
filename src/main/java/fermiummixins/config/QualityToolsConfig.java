package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class QualityToolsConfig {
	
	@Config.Comment("Limits modifier checks to only players and tamed horses for performance")
	@Config.Name("Limit Modifier Checks (QualityTools)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.qualitytools.performance.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.QualityTools_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean limitModifierChecks = false;
	
	@Config.Comment("Makes the Reforge Station show the item's tooltip while still hovering over the reforge button")
	@Config.Name("Reforge Station Shows Quality (QualityTools)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.qualitytools.tooltip.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.QualityTools_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean reforgeStationShowsQuality = false;

	@Config.Comment("Fixes a somewhat rare server crash during reforging")
	@Config.Name("Fix Reforging Crash (QualityTools)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.qualitytools.reforgingcrashfix.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.QualityTools_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean fixReforgingCrash = false;
}