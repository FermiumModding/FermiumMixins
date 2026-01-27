package fermiummixins.mixin.waystones;

import net.blay09.mods.waystones.WaystoneManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WaystoneManager.class)
public abstract class WaystoneManagerMixin {
	@Inject(
			method = "teleportToPosition",
			at = @At(value = "INVOKE", target = "Lnet/blay09/mods/waystones/WaystoneManager;sendTeleportEffect(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)V"),
			remap = false
	)
    private static void getName(EntityPlayer player, World world, BlockPos pos, EnumFacing facing, int dimensionId, CallbackInfo ci) {
		player.fallDistance = 0.0F; //reset fall distance on all waystone teleports
	}
}