package fermiummixins.mixin.bountifulbaubles;

import cursedflames.bountifulbaubles.proxy.ISideProxy;
import cursedflames.bountifulbaubles.proxy.ServerProxy;
import net.minecraft.util.text.translation.I18n;
import org.spongepowered.asm.mixin.Mixin;

//The server proxy inherits ISideProxy's defaults, which return the key untranslated
@Mixin(ServerProxy.class)
public abstract class ServerProxy_TranslateMixin implements ISideProxy {
	
	@Override
	public String translate(String string) {
		return I18n.translateToLocal(string);
	}
	
	@Override
	public String translateWithArgs(String string, Object... args) {
		return I18n.translateToLocalFormatted(string, args);
	}
	
	@Override
	public boolean hasTranslationKey(String string) {
		return I18n.canTranslate(string);
	}
}