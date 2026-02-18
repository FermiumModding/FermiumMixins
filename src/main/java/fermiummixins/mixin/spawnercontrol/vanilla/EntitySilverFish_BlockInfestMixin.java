package fermiummixins.mixin.spawnercontrol.vanilla;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import fermiummixins.wrapper.SpawnerControlWrapper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(targets = "net.minecraft.entity.monster.EntitySilverfish$AIHideInStone")
public abstract class EntitySilverFish_BlockInfestMixin extends EntityAIWander {

    public EntitySilverFish_BlockInfestMixin(EntityCreature creatureIn, double speedIn) {
        super(creatureIn, speedIn);
    }
    
    @WrapOperation(
            method = "startExecuting",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/state/IBlockState;I)Z")
    )
    private boolean fermiummixins_vanillaEntitySilverfish$AIHideInStone_startExecuting(World world, BlockPos pos, IBlockState newState, int flags, Operation<Boolean> original) {
        SpawnerControlWrapper.increaseSpawnerCount(this.entity);
        if(!SpawnerControlWrapper.shouldCancelDrops(this.entity)) return original.call(world, pos, newState, flags);
        else return false;
    }
}