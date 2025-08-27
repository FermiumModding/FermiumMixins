package fermiummixins;

import fermiummixins.handlers.PacketHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import fermiummixins.proxy.CommonProxy;

@Mod(modid = FermiumMixins.MODID, version = FermiumMixins.VERSION, name = FermiumMixins.NAME, dependencies = "required:fermiumbooter")
public class FermiumMixins {
    
    public static final String MODID = "fermiummixins";
    public static final String VERSION = "1.0.5";
    public static final String NAME = "FermiumMixins";
    public static final Logger LOGGER = LogManager.getLogger();

    @SidedProxy(clientSide = "fermiummixins.proxy.ClientProxy", serverSide = "fermiummixins.proxy.CommonProxy")
    public static CommonProxy PROXY;
	
	@Instance(MODID)
	public static FermiumMixins instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        PacketHandler.init();
        FermiumMixins.PROXY.registerSubscribers();
    }
}