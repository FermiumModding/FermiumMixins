package fermiummixins.mixin.dynamicsurroundings;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(targets = "org.orecruncher.dsurround.client.handlers.effects.EntityChatEffect$WeightTableBuilder")
public abstract class EntityChatEffectWeightTableBuilder_CustomChatMixin {

    @ModifyArg(
            method = "<init>",
            at = @At(value = "INVOKE", target = "Ljava/util/regex/Pattern;compile(Ljava/lang/String;)Ljava/util/regex/Pattern;", ordinal = 0),
            remap = false
    )
    private String fermiumMixins_dynamicSurroundingsEntityChatEffect$WeightTableBuilder_init(String regex){
        //original regex: chat\\.([a-zA-Z.]*)\\.[0-9]*$
        //difference:
        // - allow underscores in entity name
        // - don't allow "chat..0", require at least one field in between the two outer periods
        // - don't allow "chat......0", require at least one letter or underscore in between each set of periods
        // - don't allow "chat.xyz.", require at least one digit at the end
        return "chat\\.([a-zA-Z_]+\\.)+\\d+$";
    }
}