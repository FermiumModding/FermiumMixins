package fermiummixins.mixin.netherapi;

import fermiummixins.handlers.ConfigHandler;
import git.jbredwards.nether_api.mod.asm.transformers.vanilla.TransformerWorldProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(TransformerWorldProvider.Hooks.class)
public abstract class TransformerWorldProvider_RespawnProtectionMixin {
	
	@Inject(
			method = "getRandomizedSpawnPoint",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getTopSolidOrLiquidBlock(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/util/math/BlockPos;"),
			locals = LocalCapture.CAPTURE_FAILHARD,
			cancellable = true
	)
	private static void fermiummixins_netherAPITransformerWorldProviderHooks_getRandomizedSpawnPoint_inject(World world, CallbackInfoReturnable<BlockPos> cir, BlockPos spawnCoord, BlockPos spawnPoint, int spawnFuzz, int spawnFuzzHalf) {
		if(world.provider.isNether()) {
			cir.setReturnValue(spawnPoint);
			return;
		}
		for(int i = 0; i < ConfigHandler.VANILLA_CONFIG.respawnProtectionAttempts; i++) {
			BlockPos attempt = spawnPoint.add(spawnFuzzHalf - world.rand.nextInt(spawnFuzz), 0, spawnFuzzHalf - world.rand.nextInt(spawnFuzz));
			BlockPos returnable = fermiummixins$getOrAttemptTopSolidBlock(world, attempt, false);
			
			if(returnable != null) {
				cir.setReturnValue(returnable);
				return;
			}
		}
	}
	
	@Redirect(
			method = "getRandomizedSpawnPoint",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getTopSolidOrLiquidBlock(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/util/math/BlockPos;")
	)
	private static BlockPos fermiummixins_netherAPITransformerWorldProviderHooks_getRandomizedSpawnPoint_redirect(World instance, BlockPos blockpos1) {
		return fermiummixins$getOrAttemptTopSolidBlock(instance, blockpos1, true);
	}
	
	@Unique
	private static BlockPos fermiummixins$getOrAttemptTopSolidBlock(World world, BlockPos pos, boolean force) {
		Chunk chunk = world.getChunk(pos);
		BlockPos blockpos;
		BlockPos blockpos1;
		boolean goodPos = false;
		
		if(!force && ConfigHandler.VANILLA_CONFIG.isRespawnBlacklistedFromBiome(world.getBiome(pos))) {
			return null;
		}
		
		for(blockpos = new BlockPos(pos.getX(), Math.max(chunk.getTopFilledSegment() + 16, world.getSeaLevel() + 1), pos.getZ()); blockpos.getY() > 8; blockpos = blockpos1) {
			blockpos1 = blockpos.down();
			IBlockState state = chunk.getBlockState(blockpos1);
			
			if(state.getMaterial().isLiquid() && state.getMaterial() != Material.WATER) {
				break;
			}
			
			if(state.getMaterial() == Material.WATER || (state.getMaterial().blocksMovement() && !state.getBlock().isLeaves(state, world, blockpos1) && !state.getBlock().isFoliage(world, blockpos1))) {
				if(chunk.getBlockState(blockpos).causesSuffocation() || chunk.getBlockState(blockpos.up()).causesSuffocation()) {
					break;
				}
				goodPos = true;
				break;
			}
		}
		
		return (force || goodPos) ? blockpos : null;
	}
}