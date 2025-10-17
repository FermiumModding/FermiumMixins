package fermiummixins.wrapper;

import fermiummixins.FermiumMixins;
import org.apache.logging.log4j.Level;

public interface IBlockStateIdentity {
	
	void fermiummixins$setIdentityKey(int id);
	
	default int fermiummixins$getIdentityKey() {
		FermiumMixins.LOGGER.log(Level.ERROR, "FermiumMixins BlockState Identity Registry Patch has encountered unexpected behavior, disable this patch.");
		return 0;
	}
}