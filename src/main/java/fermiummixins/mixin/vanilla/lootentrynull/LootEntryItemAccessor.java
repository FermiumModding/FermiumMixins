package fermiummixins.mixin.vanilla.lootentrynull;

import net.minecraft.item.Item;
import net.minecraft.world.storage.loot.LootEntryItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LootEntryItem.class)
public interface LootEntryItemAccessor {
    @Accessor("item")
    Item getItem();
}
