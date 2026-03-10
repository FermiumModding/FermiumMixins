package fermiummixins.mixin.vanilla;

import net.minecraft.stats.RecipeBook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RecipeBook.class)
public abstract class RecipeBookMixin_RecipeBookDisable {
    @Shadow protected boolean isGuiOpen;

    @Inject(
            method = "setGuiOpen",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void fermiummixins_vanillaGuiInventory_initGui_disableRecipeBook(boolean _open, CallbackInfo ci){
        //Only needed if old player data had the recipe saved as open, could also move to serverside NBT saving
        this.isGuiOpen = false;
        ci.cancel();
    }
}
