package fermiummixins.mixin.quark;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.quark.client.feature.ChestSearchBar;

@Mixin(ChestSearchBar.class)
public abstract class ChestSearchBarMixin_ClearSearchbar {
    @Inject(
            method = "initGui",
            at = @At(value = "FIELD", target = "Lvazkii/quark/client/feature/ChestSearchBar;text:Ljava/lang/String;"),
            remap = false
    )
    private void fermiumMixins_quarkChestSearchBar_initGui(CallbackInfo ci){
        ChestSearchBar.text = "";
    }
}
