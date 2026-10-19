package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class FirstAidConfig {
	
	@Config.Comment("Fixes the health bar moving left with OverlayMode=HEARTS and high health, fixes absorption hearts not being displayed with OverlayMode=NUMBERS")
	@Config.Name("Fix Health Display (FirstAid)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.firstaid.heartrender.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.FirstAid_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean fixHealthDisplay = false;

	@Config.Comment("Fixes the body part health display disagreeing with the vanilla health bar after max health changes, by only scaling body parts on the server and syncing them")
	@Config.Name("Fix Body Part Sync (FirstAid)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.firstaid.partsync.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.FirstAid_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean fixBodyPartSync = false;
	
	@Config.Comment("Amount of health a body part needs to have to display as numbers instead of hearts" + "\n" +
			"Requires \"Fix Health Display (FirstAid)\" enabled")
	@Config.Name("Draw Health As Numbers Threshold (FirstAid)")
	public int drawHealthAsNumbersThreshold = 16;
}