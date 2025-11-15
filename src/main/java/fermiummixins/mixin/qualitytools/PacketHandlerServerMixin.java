package fermiummixins.mixin.qualitytools;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.tmtravlr.qualitytools.network.PacketHandlerServer;
import com.tmtravlr.qualitytools.reforging.TileEntityReforgingStation;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PacketHandlerServer.class)
public abstract class PacketHandlerServerMixin {
    @WrapOperation(
            method = "onMessage(Lcom/tmtravlr/qualitytools/network/CToSMessage;Lnet/minecraftforge/fml/common/network/simpleimpl/MessageContext;)Lnet/minecraftforge/fml/common/network/simpleimpl/IMessage;",
            at = @At(value = "INVOKE", target = "Lcom/tmtravlr/qualitytools/reforging/TileEntityReforgingStation;reforgeTool()V"),
            remap = false
    )
    private void fermiumMixins_qualityToolsPacketHandlerServer_onMessage(TileEntityReforgingStation instance, Operation<Void> original, @Local MinecraftServer server){
        server.callFromMainThread(() -> original.call(instance));
    }
}
