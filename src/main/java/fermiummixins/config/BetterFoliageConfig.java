package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class BetterFoliageConfig {
	
	@Config.Comment("Caches the results of BetterFoliage Class whitelist/blacklist checks for performance and memory usage")
	@Config.Name("BetterFoliage Whitelist/Blacklist Cache (BetterFoliage)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.betterfoliage.cache.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.BetterFoliage_MODID,
			targetVersionRange = "(,2.5.0)",
			desired = true,
			reason = "Requires BetterFoliage or RLFoliage before 2.5.0 (RLFoliage 2.5.0+ is a Java rewrite without the targeted classes)"
	)
	public boolean betterFoliageListCache = false;
	
	@Config.Comment("Modifies BetterFoliage geometry checks to not create a new BlockPos if offset is 0 for memory usage")
	@Config.Name("BetterFoliage Geometry Offset (BetterFoliage)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.betterfoliage.offset.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.BetterFoliage_MODID,
			targetVersionRange = "(,2.5.0)",
			desired = true,
			reason = "Requires BetterFoliage or RLFoliage before 2.5.0 (RLFoliage 2.5.0+ is a Java rewrite without the targeted classes)"
	)
	public boolean betterFoliageGeometry = false;
}