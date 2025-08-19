package fermiummixins.mixin.spawnercontrol.vanilla;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import fermiummixins.wrapper.SpawnerControlWrapper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityPig.class)
public abstract class EntityPig_CuringMixin extends EntityAnimal {
    
    public EntityPig_CuringMixin(World worldIn) {
        super(worldIn);
    }
    
    @WrapWithCondition(
            method = "onStruckByLightning",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z")
    )
    private boolean fermiummixins_vanillaEntityPig_onStruckByLightning(World instance, Entity entity) {
        SpawnerControlWrapper.increaseSpawnerCount(this);
        return !SpawnerControlWrapper.shouldCancelDrops(this);
    }
}