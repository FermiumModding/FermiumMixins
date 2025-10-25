package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class InfernalMobsConfig {
	
	@Config.Comment("Stops infernals from spamming particles while the game is paused")
	@Config.Name("Infernal Particle Spam (InfernalMobs)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.infernalmobs.particle.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.InfernalMobs_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean infernalParticleSpam = false;
	
	@Config.Comment("Stops excessive config loading and saving which wastes performance")
	@Config.Name("Config Load Spam Fix (InfernalMobs)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.infernalmobs.configperformance.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.InfernalMobs_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean configLoadSpamFix = false;

	@Config.Comment("Fixes how InfernalMobs changes max health of infernal mobs, making the \"entitybasehealth\" config of InfernalMobs obsolete.")
	@Config.Name("SetHealth Fix (InfernalMobs)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.infernalmobs.sethealthoverwrite.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.InfernalMobs_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean fixSetHealth = false;
}