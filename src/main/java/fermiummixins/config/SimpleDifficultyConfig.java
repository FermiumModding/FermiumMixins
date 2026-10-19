package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class SimpleDifficultyConfig {
	
	@Config.Comment("Forces gathering rain directly with a canteen to give purified water instead of dirty water")
	@Config.Name("Purified Rain Water (SimpleDifficulty)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.simpledifficulty.raincanteen.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.SimpleDifficulty_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean purifiedRainWater = false;
	
	@Config.Comment("Fixes not being able to use Iron Canteen and Dragon Canteen on Rain Collectors")
	@Config.Name("Rain Collector Canteen Fix (SimpleDifficulty)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.simpledifficulty.collectorcanteen.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.SimpleDifficulty_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean rainCollectorCanteenFix = false;
	
	@Config.Comment("Fixes command permission levels")
	@Config.Name("Command Permissions Fix (SimpleDifficulty)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.simpledifficulty.commands.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.SimpleDifficulty_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean commandPermissionsFix = false;
	
	@Config.Comment("Makes the Dragon Canteen fill completely from water source blocks like the other canteens, instead of one dose per click")
	@Config.Name("Dragon Canteen Refill Fix (SimpleDifficulty)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.simpledifficulty.dragoncanteen.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.SimpleDifficulty_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean dragonCanteenRefillFix = false;
}