package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class InControlConfig {

    @Config.Comment("Fixes Optifine causing InControl to apply spawn actions multiple times to the same entity")
    @Config.Name("Fix Spawn Rule Repetition (InControl/Optifine)")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.incontrol.spawnrulefix.json", defaultValue = false)
    @MixinConfig.CompatHandling(
            modid = ModLoadedUtil.InControl_MODID,
            desired = true,
            reason = "Requires mod to properly function"
    )
    @MixinConfig.CompatHandling(
            modid = ModLoadedUtil.Optifine_MODID,
            desired = true,
            reason = "Issue only arises if OptiFine is present"
    )
    public boolean fixSpawnRuleRepetition = false;
}