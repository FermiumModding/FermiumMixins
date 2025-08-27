package fermiummixins.mixin.vanilla;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import fermiummixins.FermiumMixins;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServer_PriorityMixin {
	
	@ModifyExpressionValue(
			method = "startServerThread",
			at = @At(value = "NEW", target = "(Ljava/lang/ThreadGroup;Ljava/lang/Runnable;Ljava/lang/String;)Ljava/lang/Thread;")
	)
	private Thread fermiummixins_vanillaMinecraftServer_startServerThread(Thread thread) {
		try {
			thread.setPriority(Thread.MAX_PRIORITY);
			FermiumMixins.LOGGER.log(Level.INFO, "FermiumMixins setting {} to priority 10 (Max)", thread.getName());
		}
		catch(Exception ex) {
			FermiumMixins.LOGGER.log(Level.WARN, "FermiumMixins failed to set {} priority", thread.getName());
		}
		return thread;
	}
}