package fermiummixins.wrapper;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ObjectIntIdentityMap;

import javax.annotation.Nullable;

public abstract class BlockStateIdentityPatchWrapper {
	
	public static class ClearableObjectIntIdentityMapPatched extends ObjectIntIdentityMap<IBlockState> {
		
		private int size = 0;
		
		public ClearableObjectIntIdentityMapPatched() {
			this(512);
		}
		
		public ClearableObjectIntIdentityMapPatched(int expectedSize) {
			super(expectedSize);
		}
		
		@Override
		public void put(IBlockState key, int value) {
			if(key == null) return;
			((IBlockStateIdentity) key).fermiummixins$setIdentityKey(value);
			//objectList can have multiple values return the same state ref but total size is based on total state refs
			while(this.objectList.size() <= value) {
				this.objectList.add(null);
			}
			if(this.objectList.set(value, key) == null) this.size++;
		}
		
		@Override
		public int get(@Nullable IBlockState key) {
			if(key == null) return -1;
			return ((IBlockStateIdentity) key).fermiummixins$getIdentityKey();
		}
		
		@Override
		public int size() {
			return this.size;
		}
		
		public void clear() {
			this.objectList.clear();
			this.size = 0;
		}
		
		public void remove(IBlockState key) {
			if(key == null) return;
			int value = ((IBlockStateIdentity)key).fermiummixins$getIdentityKey();
			//Do not use remove, will shift array
			if(value >= 0 && value < this.objectList.size()) {
				if(this.objectList.set(value, null) != null) this.size--;
			}
		}
	}
}