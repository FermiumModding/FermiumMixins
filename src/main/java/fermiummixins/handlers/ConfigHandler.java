package fermiummixins.handlers;

import fermiummixins.FermiumMixins;
import fermiummixins.config.*;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = FermiumMixins.MODID)
public class ConfigHandler {
	
	@Config.Name("AdvancedRocketry Config")
	public static final AdvancedRocketryConfig ADVANCEDROCKETRY_CONFIG = new AdvancedRocketryConfig();
	
	@Config.Name("AnvilPatch Config")
	public static final AnvilPatchConfig ANVILPATCH_CONFIG = new AnvilPatchConfig();
	
	@Config.Name("AstikorCarts Config")
	public static final AstikorCartsConfig ASTIKORCARTS_CONFIG = new AstikorCartsConfig();
	
	@Config.Name("AutoRegLib Config")
	public static final AutoRegLibConfig AUTOREGLIB_CONFIG = new AutoRegLibConfig();
	
	@Config.Name("Battletowers Config")
	public static final BattletowersConfig BATTLETOWERS_CONFIG = new BattletowersConfig();
	
	@Config.Name("BetterFoliage Config")
	public static final BetterFoliageConfig BETTERFOLIAGE_CONFIG = new BetterFoliageConfig();
	
	@Config.Name("BetterMineshafts Config")
	public static final BetterMineshaftsConfig BETTERMINESHAFTS_CONFIG = new BetterMineshaftsConfig();
	
	@Config.Name("BetterNether Config")
	public static final BetterNetherConfig BETTERNETHER_CONFIG = new BetterNetherConfig();
	
	@Config.Name("BetterQuestingStandard Config")
	public static final BetterQuestingStandardConfig BETTERQUESTINGSTANDARD_CONFIG = new BetterQuestingStandardConfig();
	
	@Config.Name("BiomesOPlenty Config")
	public static final BiomesOPlentyConfig BIOMESOPLENTY_CONFIG = new BiomesOPlentyConfig();
	
	@Config.Name("Bloodmoon Config")
	public static final BloodmoonConfig BLOODMOON_CONFIG = new BloodmoonConfig();
	
	@Config.Name("BountifulBaubles Config")
	public static final BountifulBaublesConfig BOUNTIFULBAUBLES_CONFIG = new BountifulBaublesConfig();
	
	@Config.Name("CarryOn Config")
	public static final CarryOnConfig CARRYON_CONFIG = new CarryOnConfig();
	
	@Config.Name("Champions Config")
	public static final ChampionsConfig CHAMPIONS_CONFIG = new ChampionsConfig();
	
	@Config.Name("Charm Config")
	public static final CharmConfig CHARM_CONFIG = new CharmConfig();
	
	@Config.Name("ClassyHats Config")
	public static final ClassyHatsConfig CLASSYHATS_CONFIG = new ClassyHatsConfig();
	
	@Config.Name("CodeChickenLib Config")
	public static final CodeChickenLibConfig CODECHICKENLIB_CONFIG = new CodeChickenLibConfig();
	
	@Config.Name("CorpseComplex Config")
	public static final CorpseComplexConfig CORPSECOMPLEX_CONFIG = new CorpseComplexConfig();
	
	@Config.Name("CosmeticArmorReworked Config")
	public static final CosmeticArmorReworkedConfig COSMETICARMORREWORKED_CONFIG = new CosmeticArmorReworkedConfig();
	
	@Config.Name("DefiledLands Config")
	public static final DefiledLandsConfig DEFILEDLANDS_CONFIG = new DefiledLandsConfig();
	
	@Config.Name("Disenchanter Config")
	public static final DisenchanterConfig DISENCHANTER_CONFIG = new DisenchanterConfig();
	
	@Config.Name("DistinctDamageDescriptions Config")
	public static final DistinctDamageDescriptionsConfig DISTINCTDAMAGEDESCRIPTIONS_CONFIG = new DistinctDamageDescriptionsConfig();
	
	@Config.Name("DoomlikeDungeons Config")
	public static final DoomlikeDungeonsConfig DOOMLIKEDUNGEONS_CONFIG = new DoomlikeDungeonsConfig();
	
	@Config.Name("DramaticTrees Config")
	public static final DramaticTreesConfig DRAMATICTREES_CONFIG = new DramaticTreesConfig();
	
	@Config.Name("DynamicSurroundings Config")
	public static final DynamicSurroundingsConfig DYNAMICSURROUNDINGS_CONFIG = new DynamicSurroundingsConfig();
	
	@Config.Name("ElenaiDodge Config")
	public static final ElenaiDodgeConfig ELENAIDODGE_CONFIG = new ElenaiDodgeConfig();
	
	@Config.Name("FancyMenu Config")
	public static final FancyMenuConfig FANCYMENU_CONFIG = new FancyMenuConfig();
	
	@Config.Name("FirstAid Config")
	public static final FirstAidConfig FIRSTAID_CONFIG = new FirstAidConfig();
	
	@Config.Name("FoodExpansion Config")
	public static final FoodExpansionConfig FOODEXPANSION_CONFIG = new FoodExpansionConfig();
	
	@Config.Name("ForgottenItems Config")
	public static final ForgottenItemsConfig FORGOTTENITEMS_CONFIG = new ForgottenItemsConfig();
	
	@Config.Name("GalactiCraft Config")
	public static final GalactiCraftConfig GALACTICRAFT_CONFIG = new GalactiCraftConfig();

	@Config.Name("InControl Config")
	public static final InControlConfig INCONTROL_CONFIG = new InControlConfig();
	
	@Config.Name("InfernalMobs Config")
	public static final InfernalMobsConfig INFERNALMOBS_CONFIG = new InfernalMobsConfig();
	
	@Config.Name("Inspirations Config")
	public static final InspirationsConfig INSPIRATIONS_CONFIG = new InspirationsConfig();
	
	@Config.Name("ItemPhysics Config")
	public static final ItemPhysicsConfig ITEMPHYSICS_CONFIG = new ItemPhysicsConfig();
	
	@Config.Name("JEI Config")
	public static final JEIConfig JEI_CONFIG = new JEIConfig();
	
	@Config.Name("Locks Config")
	public static final LocksConfig LOCKS_CONFIG = new LocksConfig();
	
	@Config.Name("LostCities Config")
	public static final LostCitiesConfig LOSTCITIES_CONFIG = new LostCitiesConfig();
	
	@Config.Name("LycanitesMobs Config")
	public static final LycanitesMobsConfig LYCANITESMOBS_CONFIG = new LycanitesMobsConfig();
	
	@Config.Name("MoBends Config")
	public static final MoBendsConfig MOBENDS_CONFIG = new MoBendsConfig();
	
	@Config.Name("Neat Config")
	public static final NeatConfig NEAT_CONFIG = new NeatConfig();
	
	@Config.Name("NetherAPI Config")
	public static final NetherAPIConfig NETHERAPI_CONFIG = new NetherAPIConfig();
	
	@Config.Name("NoTreePunching Config")
	public static final NoTreePunchingConfig NOTREEPUNCHING_CONFIG = new NoTreePunchingConfig();
	
	@Config.Name("OpenTerrainGenerator Config")
	public static final OpenTerrainGeneratorConfig OPENTERRAINGENERATOR_CONFIG = new OpenTerrainGeneratorConfig();
	
	@Config.Name("PotionCore Config")
	public static final PotionCoreConfig POTIONCORE_CONFIG = new PotionCoreConfig();
	
	@Config.Name("QualityTools Config")
	public static final QualityToolsConfig QUALITYTOOLS_CONFIG = new QualityToolsConfig();
	
	@Config.Name("Quark Config")
	public static final QuarkConfig QUARK_CONFIG = new QuarkConfig();
	
	@Config.Name("Reskillable Config")
	public static final ReskillableConfig RESKILLABLE_CONFIG = new ReskillableConfig();
	
	@Config.Name("RoughTweaks Config")
	public static final RoughTweaksConfig ROUGHTWEAKS_CONFIG = new RoughTweaksConfig();
	
	@Config.Name("SereneSeasons Config")
	public static final SereneSeasonsConfig SERENESEASONS_CONFIG = new SereneSeasonsConfig();
	
	@Config.Name("SimpleDifficulty Config")
	public static final SimpleDifficultyConfig SIMPLEDIFFICULTY_CONFIG = new SimpleDifficultyConfig();
	
	@Config.Name("SpawnerControl Config")
	public static final SpawnerControlConfig SPAWNERCONTROL_CONFIG = new SpawnerControlConfig();
	
	@Config.Name("Switchbow Config")
	public static final SwitchbowConfig SWITCHBOW_CONFIG = new SwitchbowConfig();
	
	@Config.Name("Toolbelt Config")
	public static final ToolbeltConfig TOOLBELT_CONFIG = new ToolbeltConfig();
	
	@Config.Name("Vanilla Config")
	public static final VanillaConfig VANILLA_CONFIG = new VanillaConfig();
	
	@Config.Name("Waystones Config")
	public static final WaystonesConfig WAYSTONES_CONFIG = new WaystonesConfig();
	
	@Mod.EventBusSubscriber(modid = FermiumMixins.MODID)
	private static class ConfigSyncHandler {
		
		@SubscribeEvent
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
			if(event.getModID().equals(FermiumMixins.MODID)) {
				ConfigManager.sync(FermiumMixins.MODID, Config.Type.INSTANCE);
				refreshConfigs();
			}
		}
	}

	public static void refreshConfigs(){
		ConfigHandler.ADVANCEDROCKETRY_CONFIG.refreshConfig();
		ConfigHandler.BETTERMINESHAFTS_CONFIG.refreshConfig();
		ConfigHandler.DRAMATICTREES_CONFIG.refreshConfig();
		ConfigHandler.GALACTICRAFT_CONFIG.refreshConfig();
		ConfigHandler.RESKILLABLE_CONFIG.refreshConfig();
		ConfigHandler.VANILLA_CONFIG.refreshConfig();
	}
}