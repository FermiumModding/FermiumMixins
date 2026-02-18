package fermiummixins.mixin.lycanitesmobs;

import com.lycanitesmobs.LycanitesMobs;
import com.lycanitesmobs.ObjectManager;
import com.lycanitesmobs.core.entity.EntityItemCustom;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ObjectManager.class)
public abstract class ObjectManager_CustomItemMixin {

    @Inject(
            method = "registerSpecialEntities",
            at = @At("TAIL"),
            remap = false
    )
    private static void fermiummixins_lycanitesMobsObjectManager_registerSpecialEntities(RegistryEvent.Register<EntityEntry> event, CallbackInfo ci) {
        String entityName = "custom_item";
        String registryName = LycanitesMobs.modInfo.modid + ":" + entityName;
        EntityEntry entityEntry = EntityEntryBuilder.create()
                .entity(EntityItemCustom.class)
                .id(registryName, ObjectManager.getNextSpecialEntityNetworkId())
                .name(entityName)
                .tracker(64, 20, true)
                .build();
        event.getRegistry().register(entityEntry);
    }
}