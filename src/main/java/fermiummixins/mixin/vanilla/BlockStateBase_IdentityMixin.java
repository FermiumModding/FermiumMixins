package fermiummixins.mixin.vanilla;

import fermiummixins.wrapper.IBlockStateIdentity;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.IBlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(BlockStateBase.class)
public abstract class BlockStateBase_IdentityMixin implements IBlockState, IBlockStateIdentity {
	
	//ID for unknown state is -1
	@Unique
	private int fermiummixins$blockStateID = -1;
	
	@Override
	public void fermiummixins$setIdentityKey(int id) {
		this.fermiummixins$blockStateID = id;
	}
	
	@Override
	public int fermiummixins$getIdentityKey() {
		return this.fermiummixins$blockStateID;
	}
}