package fermiummixins.mixin.simpledifficulty;

import com.charles445.simpledifficulty.api.thirst.ThirstEnum;
import com.charles445.simpledifficulty.item.ItemDragonCanteen;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemDragonCanteen.class)
public abstract class ItemDragonCanteen_RefillMixin {

    //Always adds a single purified dose, even from water source blocks which fill other canteens completely
    @WrapOperation(
            method = "onItemRightClick",
            at = @At(value = "INVOKE", target = "Lcom/charles445/simpledifficulty/item/ItemDragonCanteen;tryAddDose(Lnet/minecraft/item/ItemStack;Lcom/charles445/simpledifficulty/api/thirst/ThirstEnum;)Z", remap = false)
    )
    private boolean fermiummixins_simpleDifficultyItemDragonCanteen_onItemRightClick(ItemDragonCanteen instance, ItemStack stack, ThirstEnum thirstEnum, Operation<Boolean> original, @Local ThirstEnum trace) {
        boolean added = original.call(instance, stack, thirstEnum);
        if(trace == ThirstEnum.NORMAL) instance.setCanteenFull(stack);
        return added;
    }
}
