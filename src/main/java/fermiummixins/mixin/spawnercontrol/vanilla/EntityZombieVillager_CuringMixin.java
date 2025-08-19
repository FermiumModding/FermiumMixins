package fermiummixins.mixin.spawnercontrol.vanilla;

import fermiummixins.wrapper.SpawnerControlWrapper;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityZombieVillager.class)
public abstract class EntityZombieVillager_CuringMixin extends EntityZombie {
    
    public EntityZombieVillager_CuringMixin(World worldIn) {
        super(worldIn);
    }
    
    @Inject(
            method = "finishConversion",
            at = @At("HEAD"),
            cancellable = true
    )
    private void fermiummixins_vanillaEntityZombieVillager_finishConversion(CallbackInfo ci) {
        if(!this.world.isRemote) {
            SpawnerControlWrapper.increaseSpawnerCount(this);
            if(SpawnerControlWrapper.shouldCancelDrops(this)) {
                this.setDead();
                ci.cancel();
            }
        }
    }
}