package fermiummixins.wrapper;

import com.google.common.collect.Lists;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ObjectIntIdentityMap;

import javax.annotation.Nullable;
import java.util.ArrayList;

public abstract class BlockStateIdentityPatchWrapper {
	
	//https://en.wikipedia.org/wiki/Jesus_nut
	private static int currentID = 0;
	
	public static int getNewID() {
		return currentID++;
	}
	
	public static class ClearableObjectIntIdentityMapPatched extends ObjectIntIdentityMap<IBlockState> {
		
		private final ArrayList<Integer> identityArray;
		private int size = 0;
		
		public ClearableObjectIntIdentityMapPatched() {
			this(512);
		}
		
		public ClearableObjectIntIdentityMapPatched(int expectedSize) {
			super(expectedSize);
			this.identityArray = Lists.newArrayListWithExpectedSize(expectedSize);
		}
		
		@Override
		public void put(IBlockState key, int value) {
			if(key == null) return;
			int fixKey = ((IBlockStateIdentity)key).fermiummixins$getIdentityKey();
			while(this.identityArray.size() <= fixKey) {
				this.identityArray.add(null);
			}
			if(this.identityArray.set(fixKey, value) == null) this.size++;
			//objectList can have multiple values return the same state ref but total size is based on total state refs
			while(this.objectList.size() <= value) {
				this.objectList.add(null);
			}
			this.objectList.set(value, key);
		}
		
		@Override
		public int get(@Nullable IBlockState key) {
			if(key == null) return -1;
			Integer integer = this.get(((IBlockStateIdentity)key).fermiummixins$getIdentityKey());
			if(integer == null) {
				integer = this.get(key.getBlock().getStateFromMeta(key.getBlock().getMetaFromState(key)));
			}
			return integer;
		}
		
		private Integer get(int key) {
			if(key >= this.identityArray.size()) return null;
			return this.identityArray.get(key);
		}
		
		@Override
		public int size() {
			return this.size;
		}
		
		public void clear() {
			this.objectList.clear();
			this.identityArray.clear();
			this.size = 0;
		}
		
		public void remove(IBlockState key) {
			if(key == null) return;
			int fixKey = ((IBlockStateIdentity)key).fermiummixins$getIdentityKey();
			//Do not use remove, will shift array
			Integer prev = this.get(fixKey);
			if(prev != null) {
				this.identityArray.set(fixKey, null);
				this.objectList.set(prev, null);
				this.size--;
			}
		}
	}
}