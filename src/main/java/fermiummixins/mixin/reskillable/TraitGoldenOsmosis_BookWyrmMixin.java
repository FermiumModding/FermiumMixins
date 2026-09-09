package fermiummixins.mixin.reskillable;

import codersafterdark.reskillable.skill.magic.TraitGoldenOsmosis;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import fermiummixins.wrapper.DefiledLandsWrapper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TraitGoldenOsmosis.class)
public abstract class TraitGoldenOsmosis_BookWyrmMixin {

    @WrapOperation(
            method = "tryRepair",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;getIsRepairable(Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)Z")
    )
    private boolean fermiummixins_reskillableTraitGoldenOsmosis_tryRepair(Item instance, ItemStack toRepair, ItemStack repair, Operation<Boolean> original) {
        return original.call(instance, toRepair, repair) || original.call(instance, toRepair, new ItemStack(DefiledLandsWrapper.getGoldenBookWyrmScale()));
    }
}