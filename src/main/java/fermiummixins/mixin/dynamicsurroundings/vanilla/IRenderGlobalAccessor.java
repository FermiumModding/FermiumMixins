package fermiummixins.mixin.dynamicsurroundings.vanilla;

import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.ViewFrustum;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(RenderGlobal.class)
public interface IRenderGlobalAccessor {

	@Accessor("viewFrustum")
	ViewFrustum fermiummixins$getViewFrustum();
}
