package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class BountifulBaublesConfig {
	
	@Config.Comment("Reworks and improves the flare gun entity and handling including removing dependency on Albedo")
	@Config.Name("Flare Gun Rework (BountifulBaubles)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(
			earlyMixin = "mixins.fermiummixins.early.bountifulbaubles.flaregun.json",
			lateMixin = "mixins.fermiummixins.late.bountifulbaubles.flaregun.json",
			defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.BountifulBaubles_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean flareGunRework = false;
	
	@Config.Comment("Fixes shields not properly overriding isShield method which breaks enchantment handling")
	@Config.Name("Enchantment isShield Fix (BountifulBaubles)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.bountifulbaubles.isshield.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.BountifulBaubles_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean isShieldFix = false;
	
	@Config.Comment("Fixes shields and modifier books showing untranslated names in messages from a dedicated server, such as Quark item links")
	@Config.Name("Server Item Names Fix (BountifulBaubles)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.bountifulbaubles.servernames.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.BountifulBaubles_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean serverItemNamesFix = false;
	
	@Config.Comment("Modifies the Cobalt Shield Knockback Resistance Attribute from 10 -> 1000.")
	@Config.Name("Cobalt Shield Increased Resistance (BountifulBaubles)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.bountifulbaubles.knockback.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.BountifulBaubles_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean cobaltShieldIncreasedResistance = false;
	
	@Config.Comment("Makes the Cobalt Shield cancel knockback events instead of only applying an attribute")
	@Config.Name("Cobalt Shield Cancels Knockback (BountifulBaubles)")
	@Config.RequiresMcRestart
	public boolean cobaltShieldCancelsKnockback = false;
	
	@Config.Comment("Fixes using the reforging station to bypass removing armor that has the binding curse")
	@Config.Name("Reforging Binding Fix (BountifulBaubles)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.bountifulbaubles.binding.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.BountifulBaubles_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean reforgingBindingFix = false;
	
	@Config.Comment("Reworks fire resistance bauble handling to be less buggy")
	@Config.Name("Fire Resistance Baubles Rework (BountifulBaubles)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.bountifulbaubles.fireresistance.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.BountifulBaubles_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean fireResistanceRework = false;
	
	@Config.Comment("Prevents the Gluttony amulet from increasing drinking speed")
	@Config.Name("Gluttony Eating Speed Only (BountifulBaubles)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.bountifulbaubles.drink.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.BountifulBaubles_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean gluttonEatingOnly = false;
	
	@Config.Comment("Prevents trumpets from triggering the Gluttony amulet effect")
	@Config.Name("Gluttony Ignore Trumpet Fix (BountifulBaubles/TrumpetSkeleton)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.bountifulbaubles.trumpet.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.BountifulBaubles_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.TrumpetSkeleton_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean gluttonIgnoreTrumpet = false;
	
	@Config.Comment("Reworks the Broken Heart trinket to work with FirstAid")
	@Config.Name("Broken Heart First Aid Fix (BountifulBaubles/FirstAid)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.bountifulbaubles.brokenheart.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.BountifulBaubles_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.FirstAid_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean brokenHeartFirstAid = false;
}