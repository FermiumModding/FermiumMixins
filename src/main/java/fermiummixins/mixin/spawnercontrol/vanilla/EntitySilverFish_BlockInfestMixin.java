package fermiummixins.mixin.spawnercontrol.vanilla;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
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

    @WrapWithCondition(
            method = "startExecuting",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/state/IBlockState;I)Z")
    )
    public boolean fermiummixins_vanillaEntitySilverfish$AIHideInStone_startExecuting(World world, BlockPos pos, IBlockState newState, int flags){
        SpawnerControlWrapper.increaseSpawnerCount(this.entity);
        return !SpawnerControlWrapper.shouldCancelDrops(this.entity); // Don't set infested block
    }
}
