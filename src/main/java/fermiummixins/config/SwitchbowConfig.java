package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class SwitchbowConfig {
	
	@Config.Comment("Fixes the quivers not closing when the quiver is dropped")
	@Config.Name("Quiver Dupe Fix (Switchbow)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.switchbow.quiver.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Switchbow_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean quiverDupeFix = false;
	
	@Config.Comment("Removes the arrow refund when hitting animals already in love")
	@Config.Name("Love Arrow Dupe Fix (Switchbow)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.switchbow.lovearrow.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Switchbow_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean loveArrowDupeFix = false;
	
	@Config.Comment("Fixes luck arrows overriding looting level and not stacking with other looting mechanics")
	@Config.Name("Luck Arrow Looting Fix (Switchbow)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.switchbow.luckarrow.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Switchbow_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean luckArrowLootingFix = false;

	@Config.Comment("Fixes Arrow Launcher's fake entity returning null ItemStacks when queried for held items")
	@Config.Name("Arrow Launcher Null Stack Fix (Switchbow)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.switchbow.arrowlaunchernullstacks.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Switchbow_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean arrowLauncherNullStacks = false;
}