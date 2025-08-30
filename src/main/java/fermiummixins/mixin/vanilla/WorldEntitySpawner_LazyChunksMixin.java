package fermiummixins.mixin.vanilla;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.WorldServer;
import org.spongepowered.asm.mixin.Mixin;
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
        int x1 = (cpos.x << 4) + 8;
        int z1 = (cpos.z << 4) + 8;
        
        if(!((IWorldInvoker)world).invokeIsAreaLoaded(x1 - 32, 0, z1 - 32, x1 + 32, 0, z1 + 32, true)) {
            return false;
        }
        return original.call(instance, obj);
    }
}