package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class QuarkConfig {
	
	@Config.Comment("Fixes Quark's armor rune enchantment glint not working when Optifine is installed")
	@Config.Name("Optifine Rune Render Fix (Quark)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.quark.runeoptifine.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Quark_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean optifineRuneRenderFix = false;
	
	@Config.Comment("Patches a dupe when switching items with a Stoneling while it is dying")
	@Config.Name("Stoneling Dupe Patch (Quark)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.quark.stonelingdupe.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Quark_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean stonelingDupePatch = false;
	
	@Config.Comment("Forces Quark's Right-Click sign edit to sync the config value from server to client to prevent desyncs, thanks to Venom")
	@Config.Name("Sync Sign Edit Config (Quark)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.quark.signeditsync.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Quark_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean syncSignEditConfig = false;
	
	@Config.Comment("Fixes Quark chest boats duping")
	@Config.Name("Chest Boat Dupe (Quark)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.quark.chestboatdupe.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Quark_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean chestBoatDupe = false;
	
	@Config.Comment("Fixes crashes caused by Quark's chat linking when items with large nbt are linked")
	@Config.Name("Chat Link NBT Crash (Quark)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.quark.chatlinkcrash.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Quark_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean chatLinkCrash = false;
	
	@Config.Comment("Patches Quarks Ender Watcher to greatly improve performance")
	@Config.Name("Ender Watcher Performance Patch (Quark)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.quark.enderwatcher.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Quark_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean enderWatcherPerformance = false;
	
	@Config.Comment("Allows for setting how many ticks the ender watcher should take before updating" + "\n" +
			"Requires \"Ender Watcher Performance Patch (Quark)\" enabled")
	@Config.Name("Ender Watcher Tick Rate (Quark)")
	@Config.RequiresMcRestart
	@Config.RangeInt(min = 1)
	public int enderWatchingTickFrequency = 1;
	
	@Config.Comment("Disables checks that still run even when springy slime is disabled that waste performance" + "\n" +
			"Springy Slime should still be disabled in the Quark config")
	@Config.Name("Springy Slime Force Disable (Quark)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.quark.disablespringyslime.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Quark_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean springySlimeForceDisable = false;
	
	@Config.Comment("Disables checks that still run even when emotes are disabled that waste performance" + "\n" +
			"Emotes should still be disabled in the Quark config")
	@Config.Name("Emotes Force Disable (Quark)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.quark.disableemotes.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Quark_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean emoteForceDisable = false;
	
	@Config.Comment("Disables the potion colorizer which wastes performance and networking" + "\n" +
			"Only affects rendering of vanity color potion effects given by eating cave roots")
	@Config.Name("Potion Colorizer Force Disable (Quark)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.quark.disablecolorizer.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Quark_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean quarkColorizer = false;
	
	@Config.Comment("Reduces the frequency of Quark attempting to replace villager AI to open double doors for performance")
	@Config.Name("Reduced Villager Double Door AI Checks (Quark)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.quark.villagerdoorai.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Quark_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean doubleDoorVillagerAI = false;
	
	@Config.Comment("Forces Stonelings to assume minimum entity eyeheight to prevent stalls/crashes that can be caused by some projectiles")
	@Config.Name("Stoneling Eyeheight Stall Patch (Quark)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.quark.stonelingstall.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Quark_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean stonelingEyeHeightStall = false;
	
	@Config.Comment("Fixes Stoneling biome spawns being registered too early, resulting in some biomes being missed")
	@Config.Name("Stoneling Spawn Patch (Quark)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.quark.stonelingspawn.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Quark_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean stonelingSpawnPatch = false;
	
	@Config.Comment("Makes Quark's enchanted book tooltip rendering wrap lines and fixes item lighting rendering")
	@Config.Name("Enchanted Book Tooltip Rendering Patch (Quark)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.quark.enchanttooltip.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Quark_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean enchantedTooltipRendering = false;
	
	@Config.Comment("How many items to render per line before wrapping when rendering enchanted book tooltips" + "\n" +
			"Requires \"Enchanted Book Tooltip Rendering Patch (Quark)\" enabled")
	@Config.Name("Enchanted Book Tooltip Wrap Count")
	@Config.RangeInt(min = 4, max = 40)
	public int enchantedTooltipWrapCount = 20;
	
	@Config.Comment("Keeps Quark Usage Ticker rendered instead of fading out")
	@Config.Name("Usage Ticker Forced Visible (Quark)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.quark.usagetickervisible.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Quark_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean usageTickerForcedVisible = false;

	@Config.Comment("Increases performance of Monster Boxes looking for players nearby to activate, and prevents creative mode from triggering the box")
	@Config.Name("Monster Box Check Player Performance (Quark)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.quark.monsterboxperformance.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Quark_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean monsterBoxPerformance = false;
	
	@Config.Comment("Increases performance of revamped stone gen and underground biome generation")
	@Config.Name("Underground Gen Performance Fix (Quark)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.quark.worldgenlag.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Quark_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean undergroundGenPerformanceFix = false;
}