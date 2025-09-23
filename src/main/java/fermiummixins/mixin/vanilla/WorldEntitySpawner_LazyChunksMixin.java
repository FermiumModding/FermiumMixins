package fermiummixins.mixin.vanilla;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.ChunkProviderServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Set;

/**
 * Fix by Nischhelm
 */
@Mixin(WorldEntitySpawner.class)
public abstract class WorldEntitySpawner_LazyChunksMixin {
    @WrapOperation(
            method = "findChunksForSpawning",
            at = @At(value="INVOKE",target = "Ljava/util/Set;add(Ljava/lang/Object;)Z")
    )
    private boolean fermiummixins_vanillaWorldEntitySpawner_findChunksForSpawning(Set<ChunkPos> instance, Object obj, Operation<Boolean> original, @Local(argsOnly = true) WorldServer world) {
        ChunkPos cpos = (ChunkPos) obj;
        if(fermiummixins_chunkIsFullyLoadedAtChunkPos(world, cpos.x, cpos.z))
            return original.call(instance, obj);
        else
            return false;
    }

    @ModifyExpressionValue(
            method = "findChunksForSpawning",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/WorldServer;isAnyPlayerWithinRangeAt(DDDD)Z")
    )
    private boolean fermiummixins_vanillaWorldEntitySpawner_findChunksForSpawning(boolean playerTooClose, @Local BlockPos.MutableBlockPos spawnPos, @Local ChunkPos selectedChunkPos, @Local(argsOnly = true) WorldServer world){
        if(playerTooClose) return true; //true = don't spawn = no need to check since we already early return
        int cx = spawnPos.getX() >> 4;
        int cz = spawnPos.getZ() >> 4;
        if(selectedChunkPos.x == cx && selectedChunkPos.z == cz) return false; //no need to check if spawn pos is still in same chunk where it started
        //check for pack spawning moving out of eligible area
        return !fermiummixins_chunkIsFullyLoadedAtChunkPos(world, cx, cz);
    }

    @Unique
    private static boolean fermiummixins_chunkIsFullyLoadedAtChunkPos(WorldServer world, int cx, int cz){
        ChunkProviderServer provider = world.getChunkProvider();

        //5x5 chunks around current chunk needs to be loaded for it to not be lazy loaded
        int range = 2;

        for (int dx = -range; dx <= range; ++dx)
            for (int dz = -range; dz <= range; ++dz)
                //Only checking the outer ring for slight performance increase for the price of not catching all concave situations with 2+ players near each other
                if (Math.max(Math.abs(dx), Math.abs(dz)) == range && !provider.chunkExists(cx + dx, cz + dz))
                    return false;
        return true;
    }
}