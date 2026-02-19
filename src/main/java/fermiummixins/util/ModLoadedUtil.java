package fermiummixins.util;

import fermiumbooter.FermiumRegistryAPI;
import net.minecraftforge.fml.common.Loader;

public abstract class ModLoadedUtil {
	
	public static final String AdvancedRocketry_MODID = "advancedrocketry";
	public static final String AnvilPatch_MODID = "anvilpatch";
	public static final String AquaAcrobatics_MODID = "aquaacrobatics";
	public static final String AstikorCarts_MODID = "astikorcarts";
	public static final String AutoRegLib_MODID = "autoreglib";
	public static final String Battletowers_MODID = "battletowers";
	public static final String BetterFoliage_MODID = "betterfoliage";
	public static final String BetterMineshafts_MODID = "bettermineshafts";
	public static final String BetterNether_MODID = "betternether";
	public static final String BetterQuestingStandard_MODID = "bq_standard";
	public static final String BiomesOPlenty_MODID = "biomesoplenty";
	public static final String Bloodmoon_MODID = "bloodmoon";
	public static final String BountifulBaubles_MODID = "bountifulbaubles";
	public static final String CarryOn_MODID = "carryon";
	public static final String Champions_MODID = "champions";
	public static final String Charm_MODID = "charm";
	public static final String ClassyHats_MODID = "classyhats";
	public static final String Clumps_MODID = "clumps";
	public static final String CodeChickenLib_MODID = "codechickenlib";
	public static final String CorpseComplex_MODID = "corpsecomplex";
	public static final String CosmeticArmorReworked_MODID = "cosmeticarmorreworked";
	public static final String DefiledLands_MODID = "defiledlands";
	public static final String Disenchanter_MODID = "disenchanter";
	public static final String DistinctDamageDescriptions_MODID = "distinctdamagedescriptions";
	public static final String DoomlikeDungeons_MODID = "dldungeonsjbg";
	public static final String DramaticTrees_MODID = "dynamictrees";
	public static final String DynamicSurroundings_MODID = "dsurround";
	public static final String DynamicSurroundingsHuds_MODID = "dshuds";
	public static final String ElenaiDodge_MODID = "elenaidodge";
	public static final String FancyBlockParticles_MODID = "fbp";
	public static final String FancyMenu_MODID = "fancymenu";
	public static final String FirstAid_MODID = "firstaid";
	public static final String FoodExpansion_MODID = "foodexpansion";
	public static final String ForgottenItems_MODID = "forgottenitems";
	public static final String GalactiCraft_MODID = "galacticraftcore";
	public static final String InControl_MODID = "incontrol";
	public static final String InfernalMobs_MODID = "infernalmobs";
	public static final String Inspirations_MODID = "inspirations";
	public static final String ItemPhysics_MODID = "itemphysic";
	public static final String JEI_MODID = "jei";
	public static final String Locks_MODID = "locks";
	public static final String LostCities_MODID = "lostcities";
	public static final String LycanitesMobs_MODID = "lycanitesmobs";
	public static final String MoBends_MODID = "mobends";
	public static final String Neat_MODID = "neat";
	public static final String NetherAPI_MODID = "nether_api";
	public static final String NoTreePunching_MODID = "notreepunching";
	public static final String OpenTerrainGenerator_MODID = "openterraingenerator";
	public static final String Optifine_MODID = "optifine";
	public static final String PotionCore_MODID = "potioncore";
	public static final String QualityTools_MODID = "qualitytools";
	public static final String Quark_MODID = "quark";
	public static final String RandomPatches_MODID = "randompatches";
	public static final String ReachFix_MODID = "reachfix";
	public static final String Reskillable_MODID = "reskillable";
	public static final String RoughTweaks_MODID = "roughtweaks";
	public static final String ScalingHealth_MODID = "scalinghealth";
	public static final String SereneSeasons_MODID = "sereneseasons";
	public static final String SetBonus_MODID = "setbonus";
	public static final String SimpleDifficulty_MODID = "simpledifficulty";
	public static final String SpawnerControl_MODID = "spawnercontrol";
	public static final String Switchbow_MODID = "switchbow";
	public static final String TestDummy_MODID = "testdummy";
	public static final String Toolbelt_MODID = "toolbelt";
	public static final String TrumpetSkeleton_MODID = "trumpetskeleton";
	public static final String Waystones_MODID = "waystones";
	
	private static Boolean betterQuestingStandardLoaded = null;
	private static Boolean biomesOPlentyLoaded = null;
	private static Boolean bountifulBaublesLoaded = null;
	private static Boolean championsLoaded = null;
	private static Boolean charmLoaded = null;
	private static Boolean firstAidLoaded = null;
	private static Boolean forgottenItemsLoaded = null;
	private static Boolean galactiCraftLoaded = null;
	private static Boolean inControlLoaded = null;
	private static Boolean infernalMobsLoaded = null;
	private static Boolean inspirationsLoaded = null;
	private static Boolean optifineLoaded = null;
	private static Boolean quarkLoaded = null;
	private static Boolean reskillableLoaded = null;
	private static Boolean scalingHealthLoaded = null;
	private static Boolean setBonusLoaded = null;
	private static Boolean simpleDifficultyLoaded = null;
	private static Boolean spawnerControlLoaded = null;
	
	public static boolean isBetterQuestingStandardLoaded() {
		if(betterQuestingStandardLoaded == null) betterQuestingStandardLoaded = Loader.isModLoaded(BetterQuestingStandard_MODID);
		return betterQuestingStandardLoaded;
	}
	
	public static boolean isBiomesOPlentyLoaded() {
		if(biomesOPlentyLoaded == null) biomesOPlentyLoaded = Loader.isModLoaded(BiomesOPlenty_MODID);
		return biomesOPlentyLoaded;
	}
	
	public static boolean isBountifulBaublesLoaded() {
		if(bountifulBaublesLoaded == null) bountifulBaublesLoaded = Loader.isModLoaded(BountifulBaubles_MODID);
		return bountifulBaublesLoaded;
	}
	
	public static boolean isChampionsLoaded() {
		if(championsLoaded == null) championsLoaded = Loader.isModLoaded(Champions_MODID);
		return championsLoaded;
	}
	
	public static boolean isCharmLoaded() {
		if(charmLoaded == null) charmLoaded = Loader.isModLoaded(Charm_MODID);
		return charmLoaded;
	}
	
	public static boolean isFirstAidLoaded() {
		if(firstAidLoaded == null) firstAidLoaded = Loader.isModLoaded(FirstAid_MODID);
		return firstAidLoaded;
	}
	
	public static boolean isForgottenItemsLoaded() {
		if(forgottenItemsLoaded == null) forgottenItemsLoaded = Loader.isModLoaded(ForgottenItems_MODID);
		return forgottenItemsLoaded;
	}
	
	public static boolean isGalactiCraftLoaded() {
		if(galactiCraftLoaded == null) galactiCraftLoaded = Loader.isModLoaded(GalactiCraft_MODID);
		return galactiCraftLoaded;
	}

	public static boolean isInControlLoaded() {
		if(inControlLoaded == null) inControlLoaded = Loader.isModLoaded(InControl_MODID);
		return inControlLoaded;
	}
	
	public static boolean isInfernalMobsLoaded() {
		if(infernalMobsLoaded == null) infernalMobsLoaded = Loader.isModLoaded(InfernalMobs_MODID);
		return infernalMobsLoaded;
	}
	
	public static boolean isInspirationsLoaded() {
		if(inspirationsLoaded == null) inspirationsLoaded = Loader.isModLoaded(Inspirations_MODID);
		return inspirationsLoaded;
	}
	
	public static boolean isOptifineLoaded() {
		if(optifineLoaded == null) optifineLoaded = FermiumRegistryAPI.isModPresent(Optifine_MODID);
		return optifineLoaded;
	}
	
	public static boolean isQuarkLoaded() {
		if(quarkLoaded == null) quarkLoaded = Loader.isModLoaded(Quark_MODID);
		return quarkLoaded;
	}
	
	public static boolean isReskillableLoaded() {
		if(reskillableLoaded == null) reskillableLoaded = Loader.isModLoaded(Reskillable_MODID);
		return reskillableLoaded;
	}
	
	public static boolean isScalingHealthLoaded() {
		if(scalingHealthLoaded == null) scalingHealthLoaded = Loader.isModLoaded(ScalingHealth_MODID);
		return scalingHealthLoaded;
	}
	
	public static boolean isSetBonusLoaded() {
		if(setBonusLoaded == null) setBonusLoaded = Loader.isModLoaded(SetBonus_MODID);
		return setBonusLoaded;
	}
	
	public static boolean isSimpleDifficultyLoaded() {
		if(simpleDifficultyLoaded == null) simpleDifficultyLoaded = Loader.isModLoaded(SimpleDifficulty_MODID);
		return simpleDifficultyLoaded;
	}
	
	public static boolean isSpawnerControlLoaded() {
		if(spawnerControlLoaded == null) spawnerControlLoaded = Loader.isModLoaded(SpawnerControl_MODID);
		return spawnerControlLoaded;
	}
}