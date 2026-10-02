package fermiummixins.mixin.dynamicsurroundings.dshuds;

import fermiummixins.mixin.dynamicsurroundings.orelib.IObjectArrayAccessor;
import fermiummixins.mixin.dynamicsurroundings.vanilla.IRenderGlobalAccessor;
import fermiummixins.mixin.dynamicsurroundings.vanilla.IViewFrustumInvoker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ViewFrustum;
import net.minecraft.client.renderer.chunk.CompiledChunk;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.orecruncher.dshuds.hud.LightLevelHUD;
import org.orecruncher.lib.collections.ObjectArray;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightLevelHUD.class)
public abstract class LightLevelHUD_ReloadingTerrainMixin {

    @Shadow(remap = false)
    @Final
    private static ObjectArray<?> lightLevels;

    @Shadow(remap = false)
    private static int nextCoord;

    @Unique
    private static final BlockPos.MutableBlockPos fermiummixins$pos = new BlockPos.MutableBlockPos();

    // Only draw coords whose render chunk is compiled, terrain not rebuilt yet (F3+A, joining) would show caves through it
    @Inject(
            method = "doRender",
            at = @At("HEAD"),
            remap = false
    )
    private static void fermiummixins_dshudsLightLevelHUD_doRender(RenderWorldLastEvent event, CallbackInfo ci) {
        if(!LightLevelHUD.showHUD || nextCoord == 0) return;
        ViewFrustum frustum = ((IRenderGlobalAccessor)Minecraft.getMinecraft().renderGlobal).fermiummixins$getViewFrustum();
        if(frustum == null) return;

        Object[] data = ((IObjectArrayAccessor)lightLevels).fermiummixins$getData();
        int kept = 0;
        for(int i = 0; i < nextCoord; i++) {
            Object coord = data[i];
            ILightCoordAccessor accessor = (ILightCoordAccessor)coord;
            fermiummixins$pos.setPos(accessor.fermiummixins$getX(), MathHelper.floor(accessor.fermiummixins$getY() + 0.5), accessor.fermiummixins$getZ());
            RenderChunk chunk = ((IViewFrustumInvoker)frustum).fermiummixins$getRenderChunk(fermiummixins$pos);
            if(chunk == null || chunk.getCompiledChunk() == CompiledChunk.DUMMY) continue;
            data[i] = data[kept];
            data[kept] = coord;
            kept++;
        }
        nextCoord = kept;
    }
}
