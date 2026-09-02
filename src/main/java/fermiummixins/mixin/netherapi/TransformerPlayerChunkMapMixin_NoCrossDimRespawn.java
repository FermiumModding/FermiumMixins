package fermiummixins.mixin.netherapi;

import git.jbredwards.nether_api.mod.asm.transformers.vanilla.TransformerPlayerChunkMap;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TransformerPlayerChunkMap.Hooks.class)
public abstract class TransformerPlayerChunkMapMixin_NoCrossDimRespawn {
	@Inject(
			method = "getRespawnDimension",
			at = @At("HEAD"),
			cancellable = true,
			remap = false
	)
	private static void fermiummixins_netherAPITransformerPlayerChunkMapHooks_getRespawnDimension(EntityPlayerMP player, World world, int dimension, boolean conqueredEnd, CallbackInfoReturnable<Integer> cir){
		cir.setReturnValue(dimension); //disables the entire hook
	}
}