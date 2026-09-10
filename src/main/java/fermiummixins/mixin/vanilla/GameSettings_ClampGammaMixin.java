package fermiummixins.mixin.vanilla;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.settings.GameSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GameSettings.class)
public abstract class GameSettings_ClampGammaMixin {

    @Definition(id = "gammaSetting", field = "Lnet/minecraft/client/settings/GameSettings;gammaSetting:F")
    @Expression("this.gammaSetting = ?")
    @WrapOperation(method = "loadOptions", at = @At("MIXINEXTRAS:EXPRESSION"))
    private void fermiummixins_clampGammaOnLoad(GameSettings instance, float value, Operation<Void> original) {
        original.call(instance, GameSettings.Options.GAMMA.snapToStepClamp(value));
    }
}