package fermiummixins.handlers.incontrol;

import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.asm.mixin.Unique;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class InControlOptiFineHandler {
    
    @Unique
    public static final Set<UUID> savedUUIDS = new HashSet<>();

    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public static void onSpecialSpawn(LivingSpawnEvent.SpecialSpawn event) {
        savedUUIDS.remove(event.getEntity().getUniqueID());
        //Removing is just to keep the set of saved uuids as small as possible
        //OptiFine removes its saved entities when CheckSpawn and the additional spawn placement checks succeed, right before firing SpecialSpawn.
    }
}