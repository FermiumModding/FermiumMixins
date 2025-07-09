package fermiummixins.mixin.bountifulbaubles;

import com.jamieswhiteshirt.trumpetskeleton.common.item.ItemTrumpet;
import cursedflames.bountifulbaubles.item.ItemAmuletSinGluttony;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemAmuletSinGluttony.class)
public abstract class ItemAmuletSinGluttony_TrumpetMixin {

    @Redirect(
            method = "onItemUse(Lnet/minecraftforge/event/entity/living/LivingEntityUseItemEvent$Finish;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;getItemUseAction(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/EnumAction;")
    )
    private static EnumAction fermiummixins_bountifulBaublesItemAmuletSinGluttony_onItemUseFinish(Item instance, ItemStack stack) {
        return instance instanceof ItemTrumpet ? EnumAction.NONE : instance.getItemUseAction(stack);
    }
}