package fermiummixins.proxy;

import fermiummixins.handlers.bountifulbaubles.BrokenHeartBaubleHandler;
import fermiummixins.handlers.bountifulbaubles.FireResistanceBaubleHandler;
import fermiummixins.handlers.bountifulbaubles.KnockbackBaubleHandler;
import fermiummixins.handlers.champions.InfestedLootHandler;
import fermiummixins.handlers.ConfigHandler;
import fermiummixins.handlers.betterquesting.ListenMapHandler;
import fermiummixins.handlers.bountifulbaubles.flare.EntityFlareNonAlbedo;
import fermiummixins.handlers.charm.MagneticHandler;
import fermiummixins.handlers.forgottenitems.VeinPickaxeHandler;
import fermiummixins.handlers.galacticraft.SuffocationHandler;
import fermiummixins.handlers.inspirations.MilkCooldownHandler;
import fermiummixins.handlers.quark.ChestBoatDupeHandler;
import fermiummixins.handlers.quark.RightClickSignEditHandler;
import fermiummixins.handlers.reskillable.UndershirtHandler;
import fermiummixins.handlers.spawnercontrol.SpawnerFarmingHandler;
import fermiummixins.handlers.incontrol.InControlOptiFineHandler;
import fermiummixins.handlers.vanilla.LightningItemDamageHandler;
import fermiummixins.handlers.vanilla.TimeCacheHandler;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {

    public void registerSubscribers() {
        if(ConfigHandler.BETTERQUESTINGSTANDARD_CONFIG.betterQuestingMemLeak && ModLoadedUtil.isBetterQuestingStandardLoaded()) {
            MinecraftForge.EVENT_BUS.register(ListenMapHandler.class);
        }
        if(ConfigHandler.BOUNTIFULBAUBLES_CONFIG.brokenHeartFirstAid && ModLoadedUtil.isBountifulBaublesLoaded() && ModLoadedUtil.isFirstAidLoaded()) {
            MinecraftForge.EVENT_BUS.register(BrokenHeartBaubleHandler.class);
        }
        if(ConfigHandler.BOUNTIFULBAUBLES_CONFIG.fireResistanceRework && ModLoadedUtil.isBountifulBaublesLoaded()) {
            MinecraftForge.EVENT_BUS.register(FireResistanceBaubleHandler.class);
        }
        if(ConfigHandler.BOUNTIFULBAUBLES_CONFIG.cobaltShieldCancelsKnockback && ModLoadedUtil.isBountifulBaublesLoaded()) {
            MinecraftForge.EVENT_BUS.register(KnockbackBaubleHandler.class);
        }
        if(ConfigHandler.CHAMPIONS_CONFIG.preventInfestedFarming && ModLoadedUtil.isChampionsLoaded()) {
            MinecraftForge.EVENT_BUS.register(InfestedLootHandler.class);
        }
        if(ConfigHandler.CHARM_CONFIG.magneticEnchantDupePatch && ModLoadedUtil.isCharmLoaded()) {
            MinecraftForge.EVENT_BUS.register(MagneticHandler.class);
        }
        if(ConfigHandler.FORGOTTENITEMS_CONFIG.veinPickaxeAbusePatch && ModLoadedUtil.isForgottenItemsLoaded()) {
            MinecraftForge.EVENT_BUS.register(VeinPickaxeHandler.class);
        }
        if(ConfigHandler.GALACTICRAFT_CONFIG.suffocationImmunityHandling && ModLoadedUtil.isGalactiCraftLoaded()) {
            MinecraftForge.EVENT_BUS.register(SuffocationHandler.class);
        }
        if(ConfigHandler.INCONTROL_CONFIG.fixSpawnRuleRepetition && ModLoadedUtil.isInControlLoaded() && ModLoadedUtil.isOptifineLoaded()) {
            MinecraftForge.EVENT_BUS.register(InControlOptiFineHandler.class);
        }
        if(ConfigHandler.INSPIRATIONS_CONFIG.milkingCooldownFix && ModLoadedUtil.isInspirationsLoaded()) {
            MinecraftForge.EVENT_BUS.register(MilkCooldownHandler.class);
        }
        if(ConfigHandler.QUARK_CONFIG.syncSignEditConfig && ModLoadedUtil.isQuarkLoaded()) {
            MinecraftForge.EVENT_BUS.register(RightClickSignEditHandler.class);
        }
        if(ConfigHandler.QUARK_CONFIG.chestBoatDupe && ModLoadedUtil.isQuarkLoaded()) {
            MinecraftForge.EVENT_BUS.register(ChestBoatDupeHandler.class);
        }
        if(ConfigHandler.RESKILLABLE_CONFIG.undershirtCompat && ModLoadedUtil.isReskillableLoaded() && ModLoadedUtil.isFirstAidLoaded()) {
            MinecraftForge.EVENT_BUS.register(UndershirtHandler.class);
        }
        if(ConfigHandler.SPAWNERCONTROL_CONFIG.spawnerFarmingFix && ModLoadedUtil.isSpawnerControlLoaded()) {
            MinecraftForge.EVENT_BUS.register(SpawnerFarmingHandler.class);
        }
        if(ConfigHandler.VANILLA_CONFIG.worldBorderTimeCache) {
            MinecraftForge.EVENT_BUS.register(TimeCacheHandler.class);
        }
        if(ConfigHandler.VANILLA_CONFIG.stopLightningDestroyingItems) {
            MinecraftForge.EVENT_BUS.register(LightningItemDamageHandler.class);
        }
    }
    
    public void setGamma(float min, float max) { }
    
    public void playSoundFlare(EntityFlareNonAlbedo flare) { }
}