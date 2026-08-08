package fermiummixins.mixin.vanilla.lootentrynull;

import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.util.JsonUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(JsonUtils.class)
public abstract class JsonUtilsMixin_StopCrash {
    @Expression("? == null")
    @ModifyExpressionValue(
            method = "getItem(Lcom/google/gson/JsonElement;Ljava/lang/String;)Lnet/minecraft/item/Item;",
            at = @At(value = "MIXINEXTRAS:EXPRESSION")
    )
    private static boolean fermiumMixins_vanillaJsonUtils_crashAnotherTime(boolean original, @Local(ordinal = 1) String s) {
        // this method is only used by vanilla loot tables. if any other mod uses this method and relies on it filtering out null entries by crashing
        // it will probably just crash somewhere else
        if(original)
            System.out.println("FermiumMixins prevented unknown item " + s + " in a loot table from deleting the entire loottable");
        return false; //don't throw an error
    }
}
