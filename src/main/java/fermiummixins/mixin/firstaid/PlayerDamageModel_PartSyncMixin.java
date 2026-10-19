package fermiummixins.mixin.firstaid;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import ichttt.mods.firstaid.FirstAidConfig;
import ichttt.mods.firstaid.common.damagesystem.PlayerDamageModel;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PlayerDamageModel.class)
public abstract class PlayerDamageModel_PartSyncMixin {

    @Shadow(remap = false)
    private float prevScaleFactor;

    @Shadow(remap = false)
    private int resyncTimer;

    // Only the server scales body parts and sends them, both sides scaling on their own can disagree
    @WrapMethod(
            method = "runScaleLogic",
            remap = false
    )
    private void fermiummixins_firstAidPlayerDamageModel_runScaleLogic(EntityPlayer player, Operation<Void> original) {
        if(FirstAidConfig.scaleMaxHealth) {
            float globalFactor = player.getMaxHealth() / 20.0F;
            if(player.world.isRemote) {
                this.prevScaleFactor = globalFactor;
                return;
            }
            if(this.prevScaleFactor != globalFactor && this.resyncTimer == -1) this.resyncTimer = 3;
        }
        original.call(player);
    }

    // A rescale already scheduled one, as on respawn
    @WrapMethod(
            method = "scheduleResync",
            remap = false
    )
    private void fermiummixins_firstAidPlayerDamageModel_scheduleResync(Operation<Void> original) {
        if(this.resyncTimer == -1) original.call();
    }
}
