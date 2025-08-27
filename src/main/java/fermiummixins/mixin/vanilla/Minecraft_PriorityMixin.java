package fermiummixins.mixin.vanilla;

import fermiummixins.FermiumMixins;
import net.minecraft.client.Minecraft;
import net.minecraft.client.main.GameConfiguration;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class Minecraft_PriorityMixin {

	@Inject(
			method = "<init>",
			at = @At("TAIL")
	)
	private void fermiummixins_vanillaMinecraft_init(GameConfiguration gameConfig, CallbackInfo ci) {
		try {
			Thread.currentThread().setPriority(8);
			FermiumMixins.LOGGER.log(Level.INFO, "FermiumMixins setting {} to priority 8 (High)", Thread.currentThread().getName());
		}
		catch(Exception ex) {
			FermiumMixins.LOGGER.log(Level.WARN, "FermiumMixins failed to set {} priority", Thread.currentThread().getName());
		}
	}
}