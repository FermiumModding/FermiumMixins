package fermiummixins.config;

import com.google.common.collect.BiMap;
import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.*;

@MixinConfig(name = FermiumMixins.MODID)
public class VanillaConfig {
	
	@Config.Comment("Patches MC-119971, created by EigenCraft Unofficial Patch")
	@Config.Name("MC-119971 Outdated Chunk Data Patch (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.outdatedchunkdata.json", defaultValue = false)
	public boolean outdatedChunkData = false;
	
	@Config.Comment("Makes water reduce fall distance per tick instead of forcing distance to 0")
	@Config.Name("Configurable Water Fall Damage Reduction (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.configwaterfalldamage.json", defaultValue = false)
	public boolean configurableWaterFallDamageReduction = false;
	
	@Config.Comment(
			"How many blocks to reduce fall distance by per tick when falling in water" + "\n" +
					"Requires \"Configurable Water Fall Damage Reduction (Vanilla)\" enabled")
	@Config.Name("Fall Distance Reduction in Water")
	@Config.RangeDouble(min = 1.0D, max = 100.0D)
	public double waterFallDamageReduction = 4.0D;
	
	@Config.Comment("Lowers the player's eye height while crouching to be more like modern minecraft versions")
	@Config.Name("Lowered Crouch Eye Height (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.lowercroucheyeheight.json", defaultValue = false)
	public boolean lowerCrouchEyeHeight = false;
	
	@Config.Comment("Patches issues with player health attributes being lowered between packets causing desynced death")
	@Config.Name("Health Desync Patch (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.healthdesyncpatch.json", defaultValue = false)
	public boolean healthDesyncPatch = false;
	
	@Config.Comment("Smoothed eye height when crouching, created by RandomPatches" + "\n" +
			"Incompatible: RandomPatches" + "\n" +
			"Incompatible: AquaAcrobatics")
	@Config.Name("Smooth Crouching (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.smoothcrouching.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.RandomPatches_MODID,
			desired = false,
			reason = "Mod contains option for identical feature, ensure only one is enabled"
	)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.AquaAcrobatics_MODID,
			desired = false,
			reason = "Mod contains identical feature, will conflict if this is enabled"
	)
	public boolean smoothCrouching = false;
	
	@Config.Comment("Force Mending to prioritize damaged items first, instead of randomly picking")
	@Config.Name("Mending Priorities (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.mendingpriorities.json", defaultValue = false)
	public boolean mendingPriorities = false;
	
	@Config.Comment("Patches MC-92916, created by EigenCraft Unofficial Patch")
	@Config.Name("MC-92916 Entity Tracker Desync Patch (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.entitytrackerdesync.json", defaultValue = false)
	public boolean entityTrackerDesyncPatch = false;
	
	@Config.Comment("Fixes certain particles sent to the client from serverside never actually rendering, created by RandomPatches" + "\n" +
			"Incompatible: RandomPatches")
	@Config.Name("Particle Render Patch (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.particlerenderpatch.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.RandomPatches_MODID,
			desired = false,
			reason = "Mod contains option for identical feature, ensure only one is enabled"
	)
	public boolean particleRenderPatch = false;
	
	@Config.Comment("Patches MC-108469, created by EigenCraft Unofficial Patch")
	@Config.Name("MC-108469 Chunk Entity List Patch (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.chunkentitylistpatch.json", defaultValue = false)
	public boolean chunkEntityListPatch = false;
	
	@Config.Comment("Overrides getCanSpawn for Giant Zombies, allowing them to spawn from spawners")
	@Config.Name("Giant Zombie Spawn Fix (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.giantzombiespawnfix.json", defaultValue = false)
	public boolean giantZombieSpawnFix = false;
	
	@Config.Comment("Prevents nether portals from spawning zombie pigmen")
	@Config.Name("Stop Pigmen Portal Spawns (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.stoppigmenportalspawns.json", defaultValue = false)
	public boolean stopPigmenPortalSpawns = false;
	
	@Config.Comment("Adds a blacklist to prevent certain potion effects from working on tipped arrows")
	@Config.Name("Allow Tipped Arrow Blacklist (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.tippedarrowblacklist.json", defaultValue = false)
	public boolean allowTippedArrowBlacklist = false;
	
	@Config.Comment("Potion Blacklist for Tipped Arrows" + "\n" +
			"Requires \"Tipped Arrow Blacklist (Vanilla)\" enabled")
	@Config.Name("Tipped Arrow Blacklist")
	public String[] tippedArrowBlacklist = {""};
	
	@Config.Comment("Skips checking over-sized AABB for collisions when teleporting long distances which can cause extreme lag")
	@Config.Name("Teleporting Lag Patch (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.teleportinglagpatch.json", defaultValue = false)
	public boolean teleportingLagPatch = false;
	
	@Config.Comment("Removes the sky light requirement check from Strays and Husks when spawning")
	@Config.Name("Stray/Husk Spawning Fix (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.strayhuskspawningfix.json", defaultValue = false)
	public boolean strayHuskSpawningFix = false;
	
	@Config.Comment("Makes Guardians not sink while idle in water")
	@Config.Name("Prevent Idle Guardian Sinking (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.idleguardiansinking.json", defaultValue = false)
	public boolean preventIdleGuardianSinking = false;
	
	@Config.Comment("Makes potion effects actually display their values above amplifier 3 in the inventory screen")
	@Config.Name("Potion Amplifier Visibility (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.potionamplifiervisibility.json", defaultValue = false)
	public boolean potionAmplifierVisibility = false;
	
	@Config.Comment("Patches MC-63340 stops sleep resetting weather and weather timers which causes weather to be less common")
	@Config.Name("MC-63340 Stop Sleep Resetting Weather (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.stopsleepresettingweather.json", defaultValue = false)
	public boolean stopSleepResettingWeather = false;
	
	@Config.Comment("Makes sleeping still reset the weather only if it is actively raining/thundering" + "\n" +
			"Requires \"MC-63340 Stop Sleep Resetting Weather (Vanilla)\" enabled")
	@Config.Name("Fix Weather Reset on Sleep Conditionally")
	public boolean stopSleepResettingWeatherConditionally = true;
	
	@Config.Comment("Caches System.currentTimeMillis per tick for use by WorldBorder::getDiameter for performance")
	@Config.Name("World Border Time Cache (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.worldbordertimecache.json", defaultValue = false)
	public boolean worldBorderTimeCache = false;
	
	@Config.Comment("Disables water ticking from forced updates on chunk generation for performance")
	@Config.Name("Water Chunk Gen Ticking Fix (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.waterchunkgenticking.json", defaultValue = false)
	public boolean waterChunkGenTicking = false;
	
	@Config.Comment("Enables retrying random spawn placement to get a better location (Avoids spawning in blocks or liquid)")
	@Config.Name("Random Respawn Placement Protection (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.respawnprotection.json", defaultValue = false)
	public boolean randomRespawnPlacementProtection = false;
	
	@Config.Comment("How many attempts will randomly respawning try to find a good spawn point" + "\n" +
			"Requires \"Random Respawn Placement Protection (Vanilla)\" enabled")
	@Config.Name("Respawn Protection Attempts")
	@Config.RangeInt(min = 0, max = 4)
	public int respawnProtectionAttempts = 1;
	
	@Config.Comment("Biome name blacklist to attempt to avoid respawning in" + "\n" +
			"Requires \"Random Respawn Placement Protection (Vanilla)\" enabled")
	@Config.Name("Respawn Protection Biome Name Blacklist")
	public String[] respawnProtectionBiomeBlacklist = {};
	
	@Config.Comment("Biome type blacklist to attempt to avoid respawning in" + "\n" +
			"Requires \"Random Respawn Placement Protection (Vanilla)\" enabled")
	@Config.Name("Respawn Protection Biome Type Blacklist")
	public String[] respawnProtectionBiomeTypeBlacklist = { "OCEAN" };
	
	@Config.Comment("Allows for setting minimum and maximum Gamma values")
	@Config.Name("Allow Clamping Gamma (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.clampgamma.json", defaultValue = false)
	public boolean allowClampingGamma = false;
	
	@Config.Comment("Minimum Gamma value for brightness" + "\n" +
			"Requires \"Allow Clamping Gamma (Vanilla)\" enabled")
	@Config.Name("Minimum Gamma Value")
	public float minimumGammaValue = 0.0F;
	
	@Config.Comment("Maximum Gamma value for brightness" + "\n" +
			"Requires \"Allow Clamping Gamma (Vanilla)\" enabled")
	@Config.Name("Maximum Gamma Value")
	public float maximumGammaValue = 1.0F;
	
	@Config.Comment("Nukes the Advancement system from loading, save a large amount of memory and performance")
	@Config.Name("Nuke Advancements (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.nukeadvancements.json", defaultValue = false)
	public boolean nukeAdvancements = false;
	
	@Config.Comment("Allows for modifying the radius of spawn chunks to keep loaded when a player is online")
	@Config.Name("Spawn Chunk Radius Patch (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.spawnchunkradiuspatch.json", defaultValue = false)
	public boolean spawnChunkRadiusPatch = false;
	
	@Config.Comment("Radius of spawn chunks to keep loaded (-1 to load none)" + "\n" +
			"Requires \"Spawn Chunk Radius Patch (Vanilla)\" enabled")
	@Config.Name("Spawn Chunk Radius")
	@Config.RangeInt(min = -1, max = 8)
	public int spawnChunkRadius = -1;
	
	@Config.Comment("Cache the player's chunk position to not refresh the visible chunk list every tick to save on memory allocation")
	@Config.Name("Chunk Visibility Cache (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.chunkvisibilitycache.json", defaultValue = false)
	public boolean chunkVisibilityCache = false;
	
	@Config.Comment("Sets world flammable checks to use an existing mutable blockpos instead of creating a new one during flammable checks to save on memory allocation")
	@Config.Name("World Flammable BlockPos Patch (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.worldflammablepos.json", defaultValue = false)
	public boolean worldFlammableBlockposPatch = false;
	
	@Config.Comment("Sets particles by default to not do collision checks to save performance, unless defined otherwise")
	@Config.Name("Particle Collision Fix (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.particlecollisionfix.json", defaultValue = false)
	public boolean particleCollisionFix = false;
	
	@Config.Comment("List of particle classes to keep collision enabled for" + "\n" +
			"Requires \"Particle Collision Fix (Vanilla)\" enabled")
	@Config.Name("Particle Retain Collision List")
	public String[] particleRetainCollisionList = {""};
	
	@Config.Comment("Fixes arrows stuck in the ground rendering particles like tipped arrows when a world is reloaded")
	@Config.Name("Untipped Arrow Particles Fix (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.untippedarrowparticles.json", defaultValue = false)
	public boolean untippedArrowParticlesFix = false;
	
	@Config.Comment("Fixes the player's model shaking when in the death screen")
	@Config.Name("Player Death Shake Fix (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.playerdeathshakefix.json", defaultValue = false)
	public boolean playerDeathShakeFix = false;
	
	@Config.Comment("Allows for hoes to be repaired using their repair material like normal tools")
	@Config.Name("Allow Hoe Repairing (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.hoerepair.json", defaultValue = false)
	public boolean allowHoeRepairing = false;
	
	@Config.Comment("Allows for setting timings of weather events with config values")
	@Config.Name("Weather Timing Config (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.weathertiming.json", defaultValue = false)
	public boolean weatherTiming = false;
	
	@Config.Comment("Sets the range of how many ticks thunder will last, added to minimum" + "\n" +
			"Requires \"Weather Timing Config (Vanilla)\" enabled")
	@Config.Name("Thunder Duration Range Ticks")
	@Config.RangeInt(min = 1200)
	public int thunderActiveRange = 12000;
	
	@Config.Comment("Sets the minimum amount of ticks thunder will last" + "\n" +
			"Requires \"Weather Timing Config (Vanilla)\" enabled")
	@Config.Name("Thunder Duration Minimum Ticks")
	@Config.RangeInt(min = 1200)
	public int thunderActiveMinimum = 3600;
	
	@Config.Comment("Sets the range of how many ticks it will take for thunder to start, added to minimum" + "\n" +
			"Requires \"Weather Timing Config (Vanilla)\" enabled")
	@Config.Name("Thunder Time Until Start Range Ticks")
	@Config.RangeInt(min = 1200)
	public int thunderInactiveRange = 168000;
	
	@Config.Comment("Sets the minimum amount of ticks it will take for thunder to start" + "\n" +
			"Requires \"Weather Timing Config (Vanilla)\" enabled")
	@Config.Name("Thunder Time Until Start Minimum Ticks")
	@Config.RangeInt(min = 1200)
	public int thunderInactiveMinimum = 12000;
	
	@Config.Comment("Sets the range of how many ticks rain will last, added to minimum" + "\n" +
			"Requires \"Weather Timing Config (Vanilla)\" enabled")
	@Config.Name("Rain Duration Range Ticks")
	@Config.RangeInt(min = 1200)
	public int rainActiveRange = 12000;
	
	@Config.Comment("Sets the minimum amount of ticks rain will last" + "\n" +
			"Requires \"Weather Timing Config (Vanilla)\" enabled")
	@Config.Name("Rain Duration Minimum Ticks")
	@Config.RangeInt(min = 1200)
	public int rainActiveMinimum = 12000;
	
	@Config.Comment("Sets the range of how many ticks it will take for rain to start, added to minimum" + "\n" +
			"Requires \"Weather Timing Config (Vanilla)\" enabled")
	@Config.Name("Rain Time Until Start Range Ticks")
	@Config.RangeInt(min = 1200)
	public int rainInactiveRange = 168000;
	
	@Config.Comment("Sets the minimum amount of ticks it will take for rain to start" + "\n" +
			"Requires \"Weather Timing Config (Vanilla)\" enabled")
	@Config.Name("Rain Time Until Start Minimum Ticks")
	@Config.RangeInt(min = 1200)
	public int rainInactiveMinimum = 12000;
	
	@Config.Comment("Fixes a vanilla/forge bug limiting the xp result of smelting to 1 - 2 xp per item")
	@Config.Name("Furnace XP Limit Fix (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.furnacexplimitfix.json", defaultValue = false)
	public boolean furnaceXPLimitFix = false;
	
	@Config.Comment("Suppress addpacket for removed entity log warnings")
	@Config.Name("Suppress AddPacket For Removed Entity Warnings (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.suppressaddpacket.json", defaultValue = false)
	public boolean suppressAddPacketWarnings = false;
	
	@Config.Comment("Suppress received passengers for unknown entity log warnings")
	@Config.Name("Suppress Received Passengers For Unknown Entity Warnings (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.suppressreceivedpassengers.json", defaultValue = false)
	public boolean suppressReceivedPassengersWarnings = false;
	
	@Config.Comment("Fixes dying then leaving a hardcore world without spectating first not properly unloading the world")
	@Config.Name("Hardcore Game Over Unloading Fix (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.hardcoregameoverfix.json", defaultValue = false)
	public boolean hardcoreGameOverFix = false;
	
	@Config.Comment("Attempts to fix errors from processing packets received after the player has already left a world or server")
	@Config.Name("Fix Delayed Packet Errors (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.delayedpacketerrors.json", defaultValue = false)
	public boolean fixDelayedPacketErrors = false;
	
	@Config.Comment("At low render distances (<9) mobs can spawn in lazy loaded chunks, filling the mob cap without being able to despawn")
	@Config.Name("Prevent Mob Spawns in Lazy Chunks (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.lazychunkmobspawns.json", defaultValue = false)
	public boolean preventMobSpawnsInLazyChunks = false;
	
	@Config.Comment("Allows for setting the maximum range (height) of bedrock generation")
	@Config.Name("Maximum Bedrock Generation Range Config (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.maxbedrockrange.json", defaultValue = false)
	public boolean maximumBedrockGenerationRangeConfig = false;
	
	@Config.Comment("Upper limit for bedrock to attempt to generate" + "\n" +
			"Requires \"Maximum Bedrock Generation Range Config (Vanilla)\" enabled")
	@Config.Name("Maximum Bedrock Generation Range")
	@Config.RangeInt(min = 1)
	public int maximumBedrockGenerationRange = 5;
	
	@Config.Comment("Allows for replacing the world generation filler block by dimension id (Warning: this will occur a slight performance cost, and may cause issues with world generation that expects blocks to be stone)")
	@Config.Name("Custom Dimension Filler Block (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.customdimensionfillerblock.json", defaultValue = false)
	public boolean customDimensionFillerBlock = false;
	
	@Config.Comment("List of dimension ids and the block (Format: id,blockid) to override as the default filler block" + "\n" +
			"Requires \"Custom Dimension Filler Block (Vanilla)\" enabled")
	@Config.Name("Custom Dimension Filler Block Override List")
	public String[] customDimensionFillerBlockList = {};
	
	@Config.Comment("Allows for setting additional blocks to allow to be carved by caves and ravines")
	@Config.Name("Additional Caves and Ravines Carver Blocks (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.additionalcarverblocks.json", defaultValue = false)
	public boolean additionalCarverBlocks = false;
	
	@Config.Comment("List of blocks to additionally allow caves and ravines to carve" + "\n" +
			"Requires \"Additional Caves and Ravines Carver Blocks (Vanilla)\" enabled")
	@Config.Name("Cave and Ravine Carver Block List")
	public String[] additionalCarverBlocksList = {};
	
	@Config.Comment("Merge XP orbs together up to a customizable maximum XP value" + "\n" +
			"Incompatible: Clumps")
	@Config.Name("Merge XP Orbs (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.mergexporbs.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Clumps_MODID,
			desired = false,
			reason = "Replaces mod function"
	)
	public boolean mergeXPOrbs = false;
	
	@Config.Comment("XP orbs will only keep merging until they have this amount of XP stored in them" + "\n" +
			"Requires \"Merge XP Orbs (Vanilla)\" enabled")
	@Config.Name("XP Orb Max Merged Value")
	public int xpOrbMaxMergedValue = 100;
	
	@Config.Comment("XP orbs will only start merging if they have existed for at least this many ticks" + "\n" +
			"Requires \"Merge XP Orbs (Vanilla)\" enabled")
	@Config.Name("XP Orb Earliest Merge Tick")
	public int xpOrbEarliestMergeTick = 100;
	
	@Config.Comment("Some mods apply potion effects clientside which leads to desyncs, enable to forcibly prevent those")
	@Config.Name("Cancel Incorrect Clientside addPotionEffect Calls (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.cancelclientpotions.json", defaultValue = false)
	public boolean cancelClientPotions = false;
	
	@Config.Comment("Fixes soups not returning bowls correctly when allowed to stack")
	@Config.Name("Stackable Soup Return Bowls Fix (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.stackablesoupbowlfix.json", defaultValue = false)
	public boolean stackableSoupBowlFix = false;
	
	@Config.Comment("Suppresses potentially dangerous prefix errors")
	@Config.Name("Suppress Dangerous Prefix Errors (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.suppressdangerousprefix.json", defaultValue = false)
	public boolean suppressDangerousPrefixErrors = false;
	
	@Config.Comment("Suppresses broken ore dictionary errors")
	@Config.Name("Suppress Broken Ore Dictionary Errors (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.suppressbrokenoredict.json", defaultValue = false)
	public boolean suppressBrokenOreDict = false;
	
	@Config.Comment("Allows for blacklisting biomes to prevent spawning of Mineshafts")
	@Config.Name("Mineshaft Biome Blacklist Patch (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.mineshaftbiomeblacklist.json", defaultValue = false)
	public boolean mineshaftBiomeBlacklistPatch = false;
	
	@Config.Comment("Biome name blacklist to prevent Mineshafts from spawning" + "\n" +
			"Requires \"Mineshaft Biome Blacklist Patch (Vanilla)\" enabled")
	@Config.Name("Mineshaft Biome Name Blacklist")
	public String[] mineshaftBiomeNameBlacklist = {""};
	
	@Config.Comment("Biome type blacklist to prevent Mineshafts from spawning" + "\n" +
			"Requires \"Mineshaft Biome Blacklist Patch (Vanilla)\" enabled")
	@Config.Name("Mineshaft Biome Type Blacklist")
	public String[] mineshaftBiomeTypeBlacklist = {""};
	
	@Config.Comment("Allows for overriding entity view distances with alternate values")
	@Config.Name("Allow Entity View Distance Override (Vanilla)")
	public boolean allowEntityViewDistanceOverride = false;
	
	@Config.Comment("List of entities and the value of their view distance to override with" + "\n" +
			"Format: entityid=distance" + "\n" +
			"Requires \"Allow Entity View Distance Override (Vanilla)\" enabled")
	@Config.Name("Entity View Distance Override List")
	public Map<String, Integer> entityViewDistanceOverrideList = new HashMap<String, Integer>() {{
		put("battletowers:golem", 64);
	}};
	
	@Config.Comment("Makes lightning not destroy items")
	@Config.Name("Stop Lightning Destroying Items")
	@Config.RequiresMcRestart
	public boolean stopLightningDestroyingItems = false;
	
	@Config.Comment("Disallows boss mobs from entering minecarts and boats" + "\n" +
			"Additionally disallows Infernal/Blight/Champion mobs if InfernalMobs/ScalingHealth/Champions is loaded")
	@Config.Name("Boss Vehicle Prevention (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.bossvehicle.json", defaultValue = false)
	public boolean bossVehiclePrevention = false;
	
	@Config.Comment("Disallows all hostile mobs (IMob) from entering minecarts and boats" + "\n" +
			"Requires \"Boss Vehicle Prevention (Vanilla)\" enabled")
	@Config.Name("All Mob Vehicle Prevention")
	public boolean allMobVehiclePrevention = false;
	
	@Config.Comment("Attempts to return items to the player's inventory on closing beacon/merchant guis instead of dropping them on the ground")
	@Config.Name("Beacon/Merchant GUI Item Drop Fix (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.containerdropfix.json", defaultValue = false)
	public boolean beaconMerchantGuiItemDropFix = false;
	
	@Config.Comment("Improves performance by preventing pathfinding from forcing chunk loading")
	@Config.Name("Prevent Pathfinding Chunk Loading (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.pathfindingloading.json", defaultValue = false)
	public boolean preventPathfindingChunkLoading = false;
	
	@Config.Comment("Replaces some getMaterial checks during chunk and biome generation with direct reference checks, slightly improving performance")
	@Config.Name("World Gen Material Check Fix (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.materialcheck.json", defaultValue = false)
	public boolean worldGenMaterialCheckFix = false;
	
	@Config.Comment("Improves tick performance of falling block checks")
	@Config.Name("Falling Block Tick Performance (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.fallblocktick.json", defaultValue = false)
	public boolean fallingBlockTickPerformance = false;
	
	@Config.Comment("Suppresses checking and logging of OpenGL errors for performance" + "\n" +
			"Warning: Do not report render errors to anyone while this option is enabled, disable this first in order to get a correct error log")
	@Config.Name("Suppress OpenGL Error Checking (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.glerror.json", defaultValue = false)
	public boolean suppressOpenGLErrorChecks = false;
	
	@Config.Comment("Rewrites part of Forge/Vanilla IBlockState registry handling to fix slow identity-hash-based checks" + "\n" +
			"This can improve performance of chunk saving and chunk generation" + "\n" +
			"Warning: Do not add this to an existing world/pack without making a backup and testing, any mod modifying blockstate instancing/blockstate registry (Unlikely) will conflict" + "\n" +
			"I won't take responsibility for you rolling the dice and nuking your world")
	@Config.Name("EXPERIMENTAL: BlockState Identity Registry Patch (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.blockstateidentity.json", defaultValue = false)
	public boolean blockStateIdentityRegistryPatch = false;
	
	@Config.Comment("Fixes Optifine adding reflection to block storage methods causing wasted performance")
	@Config.Name("Block Storage Optifine Lag Fix (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.blockstorageoptifine.json", defaultValue = false)
	public boolean blockStorageOptifineLagFix = false;

	@Config.Comment("Fixes server entity velocity being limited when sending velocity update packets to clients, making fast projectiles visibly strafe sideways when shot in specific directions")
	@Config.Name("Entity Velocity Limit Fix (Vanilla)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.vanilla.entityvelocitysync.json", defaultValue = false)
	public boolean entityVelocitySync = false;
	
	private Set<Potion> tippedArrowBlacklistedPotions = null;
	private List<String> particleRetainCollisionClasses = null;
	private Map<Integer, IBlockState> dimensionFillerBlockMap = null;
	private Set<Block> customCarverBlocks = null;
	private Map<Biome, Boolean> mineshaftBiomeBlacklistMap = null;
	private Set<BiomeDictionary.Type> mineshaftBiomeTypeBlacklistSet = null;
	private Map<Biome, Boolean> respawnBiomeBlacklistMap = null;
	private Set<BiomeDictionary.Type> respawnBiomeTypeBlacklistSet = null;
	
	private Field fieldEntityClassRegistrations = null;
	private Field fieldTrackingRange = null;
	
	public Set<Potion> getTippedArrowBlacklistedPotions() {
		if(this.tippedArrowBlacklistedPotions == null) {
			this.tippedArrowBlacklistedPotions = new HashSet<>();
			for(String potionName : this.tippedArrowBlacklist) {
				Potion potion = ForgeRegistries.POTIONS.getValue(new ResourceLocation(potionName));
				if(potion != null) this.tippedArrowBlacklistedPotions.add(potion);
			}
		}
		return this.tippedArrowBlacklistedPotions;
	}
	
	public List<String> getParticleRetainCollisionClasses() {
		if(this.particleRetainCollisionClasses == null) this.particleRetainCollisionClasses = Arrays.asList(this.particleRetainCollisionList);
		return particleRetainCollisionClasses;
	}
	
	public IBlockState getDimensionFillerBlock(int dimension) {
		if(this.dimensionFillerBlockMap == null) {
			this.dimensionFillerBlockMap = new HashMap<>();
			for(String entry : this.customDimensionFillerBlockList) {
				try {
					if(entry.isEmpty()) continue;
					String[] arr = entry.split(",");
					if(arr.length != 2) continue;
					int id = Integer.parseInt(arr[0].trim());
					String name = arr[1].trim();
					if(name.isEmpty()) continue;
					ResourceLocation loc = new ResourceLocation(name);
					Block block = ForgeRegistries.BLOCKS.getValue(loc);
					if(block == null) continue;
					
					this.dimensionFillerBlockMap.put(id, block.getDefaultState());
				}
				catch(Exception ignored) {}
			}
		}
		return this.dimensionFillerBlockMap.get(dimension);
	}
	
	public boolean isBlockCarvable(Block blockIn) {
		if(this.customCarverBlocks == null) {
			this.customCarverBlocks = new HashSet<>();
			for(String entry : this.additionalCarverBlocksList) {
				try {
					if(entry.isEmpty()) continue;
					String name = entry.trim();
					if(name.isEmpty()) continue;
					ResourceLocation loc = new ResourceLocation(name);
					Block block = ForgeRegistries.BLOCKS.getValue(loc);
					if(block == null) continue;
					
					this.customCarverBlocks.add(block);
				}
				catch(Exception ignored) {}
			}
		}
		return this.customCarverBlocks.contains(blockIn);
	}
	
	public boolean isMineshaftsBlacklistedFromBiome(Biome biome) {
		if(biome == null) return false;
		if(this.mineshaftBiomeBlacklistMap == null) {
			this.mineshaftBiomeBlacklistMap = new HashMap<>();
			for(String name : this.mineshaftBiomeNameBlacklist) {
				Biome reg = ForgeRegistries.BIOMES.getValue(new ResourceLocation(name));
				if(reg != null) this.mineshaftBiomeBlacklistMap.put(reg, true);
			}
		}
		Boolean val = this.mineshaftBiomeBlacklistMap.get(biome);
		if(val != null) return val;
		
		Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biome);
		for(BiomeDictionary.Type type : this.getMineshaftBiomeTypesBlacklist()) {
			if(types.contains(type)) {
				this.mineshaftBiomeBlacklistMap.put(biome, true);
				return true;
			}
		}
		
		this.mineshaftBiomeBlacklistMap.put(biome, false);
		return false;
	}
	
	private Set<BiomeDictionary.Type> getMineshaftBiomeTypesBlacklist() {
		if(this.mineshaftBiomeTypeBlacklistSet == null) {
			this.mineshaftBiomeTypeBlacklistSet = new HashSet<>();
			for(String string : this.mineshaftBiomeTypeBlacklist) {
				this.mineshaftBiomeTypeBlacklistSet.add(BiomeDictionary.Type.getType(string));
			}
		}
		return this.mineshaftBiomeTypeBlacklistSet;
	}
	
	public boolean isRespawnBlacklistedFromBiome(Biome biome) {
		if(this.respawnBiomeBlacklistMap == null) {
			this.respawnBiomeBlacklistMap = new HashMap<>();
			for(String name : this.respawnProtectionBiomeBlacklist) {
				Biome reg = ForgeRegistries.BIOMES.getValue(new ResourceLocation(name));
				if(reg != null) this.respawnBiomeBlacklistMap.put(reg, true);
			}
		}
		Boolean val = this.respawnBiomeBlacklistMap.get(biome);
		if(val != null) return val;
		
		Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biome);
		for(BiomeDictionary.Type type : this.getRespawnBiomeTypeBlacklistSet()) {
			if(types.contains(type)) {
				this.respawnBiomeBlacklistMap.put(biome, true);
				return true;
			}
		}
		
		this.respawnBiomeBlacklistMap.put(biome, false);
		return false;
	}
	
	private Set<BiomeDictionary.Type> getRespawnBiomeTypeBlacklistSet() {
		if(this.respawnBiomeTypeBlacklistSet == null) {
			this.respawnBiomeTypeBlacklistSet = new HashSet<>();
			for(String string : this.respawnProtectionBiomeTypeBlacklist) {
				this.respawnBiomeTypeBlacklistSet.add(BiomeDictionary.Type.getType(string));
			}
		}
		return this.respawnBiomeTypeBlacklistSet;
	}
	
	private void handleGammaOverrides() {
		FermiumMixins.PROXY.setGamma(this.minimumGammaValue, this.maximumGammaValue);
	}
	
	private void handleEntityViewDistanceOverrides() {
		try {
			if(this.fieldEntityClassRegistrations == null) {
				this.fieldEntityClassRegistrations = EntityRegistry.class.getDeclaredField("entityClassRegistrations");
				this.fieldEntityClassRegistrations.setAccessible(true);
			}
			BiMap<Class<? extends Entity>, EntityRegistry.EntityRegistration> registry = (BiMap<Class<? extends Entity>, EntityRegistry.EntityRegistration>)this.fieldEntityClassRegistrations.get(EntityRegistry.instance());
			for(EntityRegistry.EntityRegistration entityRegister : registry.values()) {
				Integer distance = this.getEntityViewDistanceOverride(entityRegister.getRegistryName());
				if(distance != null) {
					if(this.fieldTrackingRange == null) {
						this.fieldTrackingRange = EntityRegistry.EntityRegistration.class.getDeclaredField("trackingRange");
						this.fieldTrackingRange.setAccessible(true);
					}
					this.fieldTrackingRange.set(entityRegister, distance);
				}
			}
		}
		catch(Exception ignored) { }
	}
	
	@Nullable
	private Integer getEntityViewDistanceOverride(ResourceLocation id) {
		if(id == null) return null;
		return this.entityViewDistanceOverrideList.get(id.toString());
	}
	
	public void refreshConfig() {
		this.tippedArrowBlacklistedPotions = null;
		this.particleRetainCollisionClasses = null;
		this.dimensionFillerBlockMap = null;
		this.customCarverBlocks = null;
		this.mineshaftBiomeBlacklistMap = null;
		this.mineshaftBiomeTypeBlacklistSet = null;
		this.respawnBiomeBlacklistMap = null;
		this.respawnBiomeTypeBlacklistSet = null;
		
		if(this.allowClampingGamma) this.handleGammaOverrides();
		if(this.allowEntityViewDistanceOverride) this.handleEntityViewDistanceOverrides();
	}
}