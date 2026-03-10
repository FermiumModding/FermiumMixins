package fermiummixins.mixin.vanilla;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.stats.RecipeBook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(RecipeBook.class)
public abstract class RecipeBookMixin_UnlockAll {

    @ModifyReturnValue(
            method = "isUnlocked",
            at = @At(value = "RETURN")
    )
    private boolean fermiummixins_vanillaRecipeBook_isUnlocked(boolean original){
        return true;
    }
}
