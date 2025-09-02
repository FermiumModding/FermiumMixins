package fermiummixins.mixin.bountifulbaubles;

import cursedflames.bountifulbaubles.item.ItemShieldCobalt;
import cursedflames.bountifulbaubles.item.ModItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(ItemShieldCobalt.class)
public abstract class ItemShieldCobalt_ShieldMixin extends ItemShield {

    @Override
    public boolean isShield(ItemStack stack, @Nullable EntityLivingBase entity) {
        return stack.getItem() == ModItems.shieldCobalt;
    }

    // Remove jank copy of shield handling
    @Inject(
            method = "onLivingAttack",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private static void fermiummixins_bountifulBaublesItemShieldCobalt_onLivingAttack(LivingAttackEvent event, CallbackInfo ci){
        ci.cancel();
    }

    // Unbreakable-unusable intention
    @Unique
    @Override
    public void setDamage(@NotNull ItemStack stack, int damage){
        super.setDamage(stack, Math.min(damage, this.getMaxDamage(stack)));
    }
}