package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class DynamicSurroundingsConfig {
	
	@Config.Comment("Adds the ability to define entity chat bubble messages through a config file")
	@Config.Name("Custom Entity Chat Bubbles (DynamicSurroundings)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.dynamicsurroundings.customchat.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.DynamicSurroundings_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean customEntityChatBubbles = false;
	
	@Config.Comment("Modifies entity chat bubble rendering to make it (slightly) more compatible with shaders")
	@Config.Name("Entity Chat Bubble Shaders Patch (DynamicSurroundings/Optifine)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.dynamicsurroundings.chatrender.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.DynamicSurroundings_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean chatBubbleShaderPatch = false;
	
	@Config.Comment("Makes potion effects properly display their amplifier levels above 3")
	@Config.Name("Potion Amplifier Visibility (DynamicSurroundings/DSHuds)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.dynamicsurroundings.potionamplifier.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.DynamicSurroundings_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.DynamicSurroundingsHuds_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean potionAmplifierVisibility = false;

	@Config.Comment("Fixes the light level HUD showing light levels of caves through terrain that is still reloading, such as after F3+A")
	@Config.Name("Light Level HUD Reloading Terrain Fix (DynamicSurroundings/DSHuds)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(
			earlyMixin = "mixins.fermiummixins.early.dynamicsurroundings.lightlevelterrain.json",
			lateMixin = "mixins.fermiummixins.late.dynamicsurroundings.lightlevelterrain.json",
			defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.DynamicSurroundingsHuds_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean lightLevelHUDReloadingTerrain = false;
	
	@Config.Comment("Display the Y level when holding the Barometer and display the light level when holding the photometer" + "\n" +
			"Additionally displays the barometer/photometer/compass information when aiming at one in an item frame")
	@Config.Name("Display Barometer/Photometer Information (DynamicSurroundings/DSHuds/Inspirations)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.dynamicsurroundings.barophotometer.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.DynamicSurroundings_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.DynamicSurroundingsHuds_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Inspirations_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean displayBarometerPhotometer = false;
}