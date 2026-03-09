package fermiummixins.mixin.dynamicsurroundings.dshuds;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.potion.PotionEffect;
import org.orecruncher.lib.Localization;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(targets = "org.orecruncher.dshuds.hud.PotionHUD$PotionInfo")
public abstract class PotionHudPotionInfo_AmplifierMixin {

    @ModifyExpressionValue(
            method = "<init>",
            at = @At(value = "INVOKE", target = "Lorg/orecruncher/lib/Localization;format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", ordinal = 0, remap = false),
            remap = false
    )
    private String fermiummixins_dshudsPotionHudPotionInfo_init_redirect1(String text, PotionEffect effect) {
        if(effect.getAmplifier() > 3 && effect.getAmplifier() < 10)
            text += " " + Localization.format("enchantment.level." + (effect.getAmplifier() + 1));
        else if(effect.getAmplifier() < 0 || effect.getAmplifier() >= 10)
            text += " " + (effect.getAmplifier() + 1);
        return text;
    }
}