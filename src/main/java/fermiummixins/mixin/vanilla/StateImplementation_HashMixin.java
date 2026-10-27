package fermiummixins.mixin.vanilla;

import net.minecraft.block.state.BlockStateContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;

@Mixin(BlockStateContainer.StateImplementation.class)
public abstract class StateImplementation_HashMixin {

	@Unique
	private static int fermiummixins$nextHash = 0;

	@Unique
	private final int fermiummixins$hash = fermiummixins$nextHash++;

	/**
	 * @author fonnymunkey
	 * @reason equals is identity based, property map hash is slow to compute and collides between different blocks
	 */
	@Overwrite(remap = false)
	public int hashCode() {
		return this.fermiummixins$hash;
	}
}