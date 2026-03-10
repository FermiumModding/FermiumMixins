package fermiummixins.mixin.vanilla;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(GuiInventory.class)
public abstract class GuiInventoryMixin_RecipeBookDisable {
    @WrapOperation(
            method = "initGui",
            at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z")
    )
    private boolean fermiummixins_vanillaGuiInventory_initGui_disableRecipeBook(List<GuiButton> instance, Object e, Operation<Boolean> original){
        return false;
    }
}
