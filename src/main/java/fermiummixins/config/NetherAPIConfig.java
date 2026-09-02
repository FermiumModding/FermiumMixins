package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class NetherAPIConfig {
	
	@Config.Comment({
			"Enables retrying random spawn placement to get a better location (Avoids spawning in blocks or liquid)",
			"See Random Respawn Placement Protection (Vanilla) for additional related options",
			"Note: this also in turn fixes default NetherAPIs respawn attempt implementation breaking servers if spawnRadius gamerule is large"
	})
	@Config.Name("Random Respawn Placement Protection (NetherAPI)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.netherapi.respawnprotection.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.NetherAPI_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean randomRespawnPlacementProtectionNetherAPI = false;

	@Config.Comment("Removes NetherAPIs feature of allowing respawns in other dimensions when a respawn is set there and in the current dimension the respawn is missing or obstructed.")
	@Config.Name("Disable Cross-Dimension Respawn (NetherAPI)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.netherapi.nocrossdimrespawn.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.NetherAPI_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean noCrossDimRespawns = false;
}