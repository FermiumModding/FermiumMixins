package fermiummixins.mixin.charm;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(targets = "svenhjol.charm.crafting.tile.TileCrate$1")
public abstract class TileCrate_ItemStackHandlerMixin {
    @ModifyReturnValue(
            method = "getStackLimit",
            at = @At(value = "RETURN"),
            remap = false
    )
    private int fermiumMixins_charmTileCrateItemStackHandler_getStackLimit(int original, int slot, ItemStack stack){
        if(original == 0) return 0;
        return stack.getMaxStackSize();
    }
}
