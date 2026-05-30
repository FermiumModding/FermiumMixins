package fermiummixins.mixin.vanilla;

import net.minecraft.entity.EntityTrackerEntry;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityTrackerEntry.class)
public abstract class EntityTrackerEntry_SuppressMixin {
	
	@Redirect(
			method = "createSpawnPacket",
			at = @At(value = "INVOKE", target = "Lorg/apache/logging/log4j/Logger;warn(Ljava/lang/String;)V", remap = false)
	)
	private void fermiummixins_vanillaEntityTrackerEntry_createSpawnPacket(Logger instance, String s) {
		//noop
	}
}