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

	@Config.Comment("If \"Reforge Station Shows Quality\" is enabled, show either the whole items tooltip (FULL_ITEM), only the relevant quality parts (ONLY_QUALITY) or only the qualities name (ONLY_QUALITY_NAME).")
	@Config.Name("Reforge Station Tooltip Type (QualityTools)")
	public QualityToolTipType reforgeStationQualityShowType = QualityToolTipType.ONLY_QUALITY;
	public enum QualityToolTipType { FULL_ITEM, ONLY_QUALITY, ONLY_QUALITY_NAME}

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