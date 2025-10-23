package fermiummixins.mixin.vanilla;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import fermiummixins.handlers.ConfigHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityFishHook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityTracker.class)
public abstract class EntityTracker_ViewDistanceOverride {

    @WrapOperation(
            method = "track(Lnet/minecraft/entity/Entity;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityTracker;track(Lnet/minecraft/entity/Entity;II)V")
    )
    private void fermiumMixins_vanillaEntityTracker_trackPlayer(EntityTracker tracker, Entity entity, int originalRange, int updateFrequency, Operation<Void> original){
        //EntityPlayerMP already cached
        Integer newRange = ConfigHandler.VANILLA_CONFIG.getCachedVanillaEntityViewDistanceOverride(entity);
        if(newRange != null) {
            original.call(tracker, entity, newRange, updateFrequency);
            return;
        }

        newRange = ConfigHandler.VANILLA_CONFIG.getEntityViewDistanceOverride("PLAYER");
        if(newRange == null) { //No config override, cache originalRange for faster lookup
            ConfigHandler.VANILLA_CONFIG.putCachedVanillaEntityViewDistanceOverride(entity, originalRange);
            original.call(tracker, entity, originalRange, updateFrequency);
            return;
        }

        //Config override found, cache config value for the entity class for faster lookup
        ConfigHandler.VANILLA_CONFIG.putCachedVanillaEntityViewDistanceOverride(entity, newRange);
        original.call(tracker, entity, newRange, updateFrequency);
    }

    @WrapOperation(
            method = "track(Lnet/minecraft/entity/Entity;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityTracker;track(Lnet/minecraft/entity/Entity;IIZ)V")
    )
    private void fermiumMixins_vanillaEntityTracker_trackVanillaEntities(EntityTracker tracker, Entity entity, int originalRange, final int updateFrequency, boolean sendVelocityUpdates, Operation<Void> original){
        //already cached
        Integer newRange = ConfigHandler.VANILLA_CONFIG.getCachedVanillaEntityViewDistanceOverride(entity);
        if(newRange != null) {
            original.call(tracker, entity, newRange, updateFrequency, sendVelocityUpdates);
            return;
        }

        newRange = ConfigHandler.VANILLA_CONFIG.getEntityViewDistanceOverride(EntityList.getKey(entity)); //config override for this specific entity
        if(newRange == null) newRange = ConfigHandler.VANILLA_CONFIG.getEntityViewDistanceOverride(fermiumMixins$getName(entity)); //config override for this type
        if(newRange == null) { //No config override for either, cache originalRange for faster lookup
            ConfigHandler.VANILLA_CONFIG.putCachedVanillaEntityViewDistanceOverride(entity, originalRange);
            original.call(tracker, entity, originalRange, updateFrequency, sendVelocityUpdates);
            return;
        }

        //Config override found, cache config value for the entity class for faster lookup
        ConfigHandler.VANILLA_CONFIG.putCachedVanillaEntityViewDistanceOverride(entity, newRange);
        original.call(tracker, entity, newRange, updateFrequency, sendVelocityUpdates);
    }

    @Unique
    private static String fermiumMixins$getName(Entity entity){
        // Order copied from EntityTracker.track(Entity)
        // leaving out entries that are specific entities with no vanilla inheritors
        if(entity instanceof EntityFishHook) return "FISH_HOOK";
        if(entity instanceof EntityArrow) return "ARROW";
        if(entity instanceof EntityFireball) return "FIREBALL";
        if(entity instanceof EntityMinecart) return "MINECART";
        if(entity instanceof IAnimals) return "LIVING";
        if(entity instanceof EntityHanging) return "HANGING";
        return null;
    }
}
