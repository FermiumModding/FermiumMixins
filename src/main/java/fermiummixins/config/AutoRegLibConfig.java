package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class AutoRegLibConfig {
	
	@Config.Comment("Fixes a crash when placing slabs next to redstone while FancyBlockParticles is installed")
	@Config.Name("Slab Crash Fix (AutoRegLib/FancyBlockParticles)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.autoreglib.fbpcrash.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.AutoRegLib_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.FancyBlockParticles_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean slabCrashFix = false;
}