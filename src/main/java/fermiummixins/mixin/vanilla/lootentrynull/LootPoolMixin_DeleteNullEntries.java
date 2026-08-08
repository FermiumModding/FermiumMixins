package fermiummixins.mixin.vanilla.lootentrynull;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.List;

@Mixin(LootPool.Serializer.class)
public abstract class LootPoolMixin_DeleteNullEntries {
    @ModifyExpressionValue(
            method = "deserialize(Lcom/google/gson/JsonElement;Ljava/lang/reflect/Type;Lcom/google/gson/JsonDeserializationContext;)Lnet/minecraft/world/storage/loot/LootPool;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/util/JsonUtils;deserializeClass(Lcom/google/gson/JsonObject;Ljava/lang/String;Lcom/google/gson/JsonDeserializationContext;Ljava/lang/Class;)Ljava/lang/Object;", ordinal = 0)
    )
    private Object fermiumMixins_vanillaLootPoolSerializer_deleteNullItems(Object original){
        LootEntry[] entries = (LootEntry[]) original;
        List<LootEntry> list = new ArrayList<>();
        boolean changed = false;
        for (LootEntry entry : entries) {
            if(entry instanceof LootEntryItem){
                LootEntryItem lootEntry = (LootEntryItem) entry;
                if(((LootEntryItemAccessor)lootEntry).getItem() == null) {
                    changed = true;
                    continue; //skip null entries created in JsonUtils Mixin
                }
            }
            list.add(entry);
        }

        return !changed ? original : list.toArray(new LootEntry[0]);
    }
}
