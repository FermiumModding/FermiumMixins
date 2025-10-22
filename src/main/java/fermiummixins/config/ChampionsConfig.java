package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class ChampionsConfig {
	
	@Config.Comment("Makes mobs summoned by Infested champions not drop loot/xp, to prevent farming")
	@Config.Name("Prevent Infested Farming (Champions)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.champions.infested.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Champions_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean preventInfestedFarming = false;
	
	@Config.Comment("Fixes a bug where the amount of time the Jailed effect is applied for is applied in ticks not seconds")
	@Config.Name("Jailer Time Fix (Champions)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.champions.jailer.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Champions_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean jailerTimeFix = false;
	
	@Config.Comment("Makes death messages use the actual name of the Champion")
	@Config.Name("Death Message Tweak (Champions)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.champions.deathmessage.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Champions_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean deathMessageTweak = false;
	
	@Config.Comment("Makes Champions with potions use invisible particles")
	@Config.Name("Potion Particle Visibility (Champions)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.champions.particle.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Champions_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean potionParticleVisibility = false;
	
	@Config.Comment("Fixes Reflecting Champions modifying their own received damage")
	@Config.Name("Reflecting Damage Fix (Champions)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.champions.reflecting.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Champions_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean reflectingDamageFix = false;
	
	@Config.Comment("Prevents ownable entities from becoming Champions")
	@Config.Name("Prevent Ownable Champions (Champions)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.champions.ownable.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Champions_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean preventOwnableChampions = false;
	
	@Config.Comment("Prevents Champions from becoming Infernals and vice-versa")
	@Config.Name("Prevent Infernal Champions (Champions/InfernalMobs)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.champions.infernals.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Champions_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.InfernalMobs_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean preventInfernalChampions = false;

	@Config.Comment("Fix overwriting dropped xp with champmulti * original and instead multiply champmulti * current. Makes the Champion xp multiplier apply on top of for example ScalingHealth Blight xp multiplier instead of replacing it.")
	@Config.Name("XP Drop Fix (Champions)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.champions.droppedxp.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Champions_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean fixDroppedXP = false;
}