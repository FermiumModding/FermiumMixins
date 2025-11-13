package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class LycanitesMobsConfig {
	
	@Config.Comment("Modifies the render bounding boxes of some mobs to fix under/oversized render boxes")
	@Config.Name("Render Box Resize (LycanitesMobs)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.lycanitesmobs.render.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.LycanitesMobs_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean renderBoxResize = false;
	
	@Config.Comment("Modifies Spriggans to properly allow changing the farming growth rate")
	@Config.Name("Spriggan Growth Rate Override Patch (LycanitesMobs)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.lycanitesmobs.spriggan.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.LycanitesMobs_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean sprigganGrowthRateOverridePatch = false;
	
	@Config.Comment("Every x ticks Spriggans will attempt growing crops around them" + "\n" +
			"Requires \"Spriggan Growth Rate Override Patch (LycanitesMobs)\" enabled")
	@Config.Name("Spriggan Growth Rate Override Value")
	public int sprigganGrowthRateOverrideValue = 20;
	
	@Config.Comment("Fixes a bug where minions despawn and don't get properly cleared from boss mechanics")
	@Config.Name("Asmodeus Minion Patch (LycanitesMobs)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.lycanitesmobs.minion.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.LycanitesMobs_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean asmodeusMinionPatch = false;

	@Config.Comment("Fixes the missing registry entry that causes lycanites item drops to always be removed when unloaded")
	@Config.Name("Custom Item Entity Registry Patch (LycanitesMobs)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.lycanitesmobs.customitem.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.LycanitesMobs_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean customItemEntityRegistryPatch = false;
	
	@Config.Comment("Allows for placing mob charges in item frames")
	@Config.Name("Allow Charges In Item Frames (LycanitesMobs)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.lycanitesmobs.itemframe.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.LycanitesMobs_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean allowChargesInItemFrames = false;
}