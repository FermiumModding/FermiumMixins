package fermiummixins.mixin.vanilla;

import fermiummixins.FermiumMixins;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServer_SaveLogMixin {
	
	@Unique
	private long fermiummixins$saveStartTime = 0;
	
	@Inject(
			method = "tick",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/server/management/PlayerList;saveAllPlayerData()V", shift = At.Shift.BEFORE)
	)
	private void fermiummixins_vanillaMinecraftServer_tick0(CallbackInfo ci) {
		FermiumMixins.LOGGER.log(Level.INFO, "Automatic world save starting...");
		this.fermiummixins$saveStartTime = System.currentTimeMillis();
	}
	
	@Inject(
			method = "tick",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;saveAllWorlds(Z)V", shift = At.Shift.AFTER)
	)
	private void fermiummixins_vanillaMinecraftServer_tick1(CallbackInfo ci) {
		FermiumMixins.LOGGER.log(Level.INFO, "Automatic world save finished, took {}ms", System.currentTimeMillis() - this.fermiummixins$saveStartTime);
	}
}