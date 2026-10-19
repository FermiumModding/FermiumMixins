package fermiummixins.mixin.dynamicsurroundings.orelib;

import org.orecruncher.lib.collections.ObjectArray;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ObjectArray.class)
public interface IObjectArrayAccessor {

	@Accessor(value = "data", remap = false)
	Object[] fermiummixins$getData();
}
