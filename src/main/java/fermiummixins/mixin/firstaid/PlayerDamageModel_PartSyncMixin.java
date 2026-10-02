package fermiummixins.mixin.firstaid;

import ichttt.mods.firstaid.FirstAidConfig;
import ichttt.mods.firstaid.common.damagesystem.PlayerDamageModel;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerDamageModel.class)
public abstract class PlayerDamageModel_PartSyncMixin {

    @Shadow(remap = false)
    private float prevScaleFactor;

    @Shadow(remap = false)
    private int resyncTimer;

    // Only the server scales body parts and sends them, both sides scaling on their own can disagree
    @Inject(
            method = "runScaleLogic",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private void fermiummixins_firstAidPlayerDamageModel_runScaleLogic(EntityPlayer player, CallbackInfo ci) {
        if(!FirstAidConfig.scaleMaxHealth) return;
        float globalFactor = player.getMaxHealth() / 20.0F;
        if(player.world.isRemote) {
            this.prevScaleFactor = globalFactor;
            ci.cancel();
        }
        else if(this.prevScaleFactor != globalFactor && this.resyncTimer == -1) this.resyncTimer = 3;
    }

    // A rescale already scheduled one, as on respawn
    @Inject(
            method = "scheduleResync",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private void fermiummixins_firstAidPlayerDamageModel_scheduleResync(CallbackInfo ci) {
        if(this.resyncTimer != -1) ci.cancel();
    }
}
