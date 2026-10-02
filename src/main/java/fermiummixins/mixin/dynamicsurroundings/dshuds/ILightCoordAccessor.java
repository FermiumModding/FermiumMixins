package fermiummixins.mixin.dynamicsurroundings.dshuds;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "org.orecruncher.dshuds.hud.LightLevelHUD$LightCoord")
public interface ILightCoordAccessor {

	@Accessor(value = "x", remap = false)
	int fermiummixins$getX();

	@Accessor(value = "y", remap = false)
	double fermiummixins$getY();

	@Accessor(value = "z", remap = false)
	int fermiummixins$getZ();
}
