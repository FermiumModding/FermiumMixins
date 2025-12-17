package fermiummixins.mixin.autoreglib;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import vazkii.arl.block.BlockModSlab;

import javax.annotation.Nonnull;

@Mixin(BlockModSlab.class)
public abstract class BlockModSlab_CrashMixin extends BlockSlab {
	
	public BlockModSlab_CrashMixin(Material materialIn) {
		super(materialIn);
	}
	
	/**
	 * @author fonnymunkey
	 * @reason fix crash on place with FBP
	 */
	@Overwrite(remap = false)
	public boolean isSideSolid(IBlockState base_state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, EnumFacing side) {
		return super.isSideSolid(base_state, world, pos, side);
	}
}