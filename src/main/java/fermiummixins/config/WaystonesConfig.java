package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class WaystonesConfig {
	
	@Config.Comment("Reworks Waystones used name system to use less memory and be more performant")
	@Config.Name("Rework Waystone Used Name Check (Waystones)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(
			earlyMixin = "mixins.fermiummixins.early.waystones.naming.json",
			lateMixin = "mixins.fermiummixins.late.waystones.naming.json",
			defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Waystones_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean waystonesUsedNameRework = false;
	
	@Config.Comment("Allows for removing Biome names from village waystones" + "\n" +
			"Requires \"Rework Waystone Used Name Check (Waystones)\" enabled")
	@Config.Name("Village Waystones Remove Biome Name")
	public boolean villageWaystoneRemoveBiome = false;

	@Config.Comment("Fixes a bug that makes it impossible to reorder waystones correctly if a player has access to more than 127 waystones.")
	@Config.Name("Fix Waystone Swapping (Waystones)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.waystones.swapping.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Waystones_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean fixWaystoneSwapping = false;

	@Config.Comment("Resets fall distance to 0 when players teleport to waystones, making it consistent with other teleportation methods.")
	@Config.Name("Reset Fall Distance (Waystones)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.waystones.falldistance.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Waystones_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean resetFallDistance = false;
}