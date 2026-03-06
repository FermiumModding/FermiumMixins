package fermiummixins.mixin.charm;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import svenhjol.charm.crafting.feature.Crate;
import svenhjol.charm.crafting.tile.TileCrate;
import vazkii.arl.util.ItemNBTHelper;
import vazkii.quark.management.capability.ShulkerBoxDropIn;

@Mixin(ShulkerBoxDropIn.class)
public abstract class ShulkerBoxDropInMixin {
    @Inject(
            method = "tryAddToShulkerBox",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/tileentity/TileEntityShulkerBox;<init>()V"),
            cancellable = true
    )
    private void fermiumMixins_quarkShulkerBoxDropIn_tryAddToShulkerBox(EntityPlayer player, ItemStack shulkerBox, ItemStack stack, boolean simulate, CallbackInfoReturnable<Boolean> cir){
        if(shulkerBox.getItem() != Item.getItemFromBlock(Crate.crate)) return;

        TileCrate tile = new TileCrate();
        tile.setWorld(player.world);
        NBTTagCompound stackCmp = shulkerBox.getTagCompound();
        NBTTagCompound blockCmp = stackCmp != null && stackCmp.hasKey("BlockEntityTag") ? stackCmp.getCompoundTag("BlockEntityTag") : new NBTTagCompound();
        tile.readFromNBT(blockCmp);

        IItemHandler handler = tile.getInventory();
        ItemStack result = ItemHandlerHelper.insertItem(handler, stack, simulate);
        boolean didMove = result.isEmpty();
        if (!simulate && didMove) {
            tile.writeToNBT(blockCmp);
            ItemNBTHelper.setCompound(shulkerBox, "BlockEntityTag", blockCmp);
        }

        cir.setReturnValue(didMove);
    }
}
