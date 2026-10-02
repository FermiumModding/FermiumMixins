package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class ItemPhysicsConfig {
	
	@Config.Comment("Fixes possible serverside crash due to calling clientside code")
	@Config.Name("Server Crash Fix (ItemPhysics)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(
			earlyMixin = "mixins.fermiummixins.early.itemphysics.servercrash.json",
			lateMixin = "mixins.fermiummixins.late.itemphysics.servercrash.json",
			defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.ItemPhysics_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean serverCrashFix = false;
	
	@Config.Comment("Makes picking up items use the player's reach attribute instead of a hardcoded value")
	@Config.Name("Item Pickup Reach Attribute (ItemPhysics/ReachFix)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.itemphysics.reach.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.ItemPhysics_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.ReachFix_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean itemPickupReachAttribute = false;
	
	@Config.Comment("Fixes being able to throw CarryOn entities/tiles by holding Q")
	@Config.Name("CarryOn Q Fix (ItemPhysics/CarryOn)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.itemphysics.carryonq.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.ItemPhysics_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.CarryOn_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean carryOnQFix = false;
	
	@Config.Comment("Fixes right click pickup doing nothing while holding items in both hands with no block behind the item, such as underwater")
	@Config.Name("Full Hands Pickup Fix (ItemPhysics)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.itemphysics.fullhandspickup.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.ItemPhysics_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean fullHandsPickupFix = false;
}