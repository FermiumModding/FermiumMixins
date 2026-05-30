package fermiummixins.mixin.vanilla;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.network.NetHandlerPlayServer;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(NetHandlerPlayServer.class)
public abstract class NetHandlerPlayServerMixin_WrongMovement {
    @WrapWithCondition(
            method = "processPlayer",
            at = @At(value = "INVOKE", target = "Lorg/apache/logging/log4j/Logger;warn(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V", remap = false)
    )
    private boolean fermiummixins_vanillaNetHandlerPlayServer_processPlayer_silenceLogSpam_tooQuickly(Logger instance, String s, Object o1, Object o2, Object o3, Object o4){
        return false;
    }

    @WrapWithCondition(
            method = "processVehicleMove",
            at = @At(value = "INVOKE", target = "Lorg/apache/logging/log4j/Logger;warn(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V", remap = false)
    )
    private boolean fermiummixins_vanillaNetHandlerPlayServer_processPlayer_silenceLogSpam_tooQuickly_mount(Logger instance, String s, Object o1, Object o2, Object o3, Object o4, Object o5){
        return false;
    }

    @WrapWithCondition(
            method = {"processPlayer", "processVehicleMove"},
            at = @At(value = "INVOKE", target = "Lorg/apache/logging/log4j/Logger;warn(Ljava/lang/String;Ljava/lang/Object;)V", remap = false)
    )
    private boolean fermiummixins_vanillaNetHandlerPlayServer_processPlayer_silenceLogSpam_wrongly(Logger instance, String s, Object o){
        return false;
    }
}
