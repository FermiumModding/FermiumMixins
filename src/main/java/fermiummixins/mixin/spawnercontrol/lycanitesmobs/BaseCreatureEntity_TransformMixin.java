package fermiummixins.mixin.spawnercontrol.lycanitesmobs;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import com.lycanitesmobs.core.entity.BaseCreatureEntity;
import fermiummixins.util.ModLoadedUtil;
import fermiummixins.wrapper.SpawnerControlWrapper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BaseCreatureEntity.class)
public abstract class BaseCreatureEntity_TransformMixin extends EntityLiving {

    public BaseCreatureEntity_TransformMixin(World world) {
        super(world);
    }

    @WrapWithCondition(
            method = "transform",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z", remap = true),
            remap = false
    )
    private boolean fermiummixins_lycanitesMobsBaseCreatureEntity_transform(World instance, Entity resultingEntity, @Local(argsOnly = true) Entity partner, @Local(argsOnly = true) boolean destroyPartner) {
        if(!this.world.isRemote && ModLoadedUtil.isSpawnerControlLoaded()) {
            SpawnerControlWrapper.increaseSpawnerCount(this); // Increase for initiating fusion entity
            if(destroyPartner) {
                SpawnerControlWrapper.increaseSpawnerCount(partner); // Increase for target fusion partner
            }
            // Cancel spawning resulting Entity
            return !SpawnerControlWrapper.shouldCancelDrops(this) && !SpawnerControlWrapper.shouldCancelDrops(partner);
        }
        return true;
    }
}
