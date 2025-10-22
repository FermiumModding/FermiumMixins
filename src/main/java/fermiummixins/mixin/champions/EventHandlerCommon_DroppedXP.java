package fermiummixins.mixin.champions;

import c4.champions.common.EventHandlerCommon;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EventHandlerCommon.class)
public abstract class EventHandlerCommon_DroppedXP {
    @WrapOperation(
            method = "livingXP",
            at = @At(value = "INVOKE", target = "Lnet/minecraftforge/event/entity/living/LivingExperienceDropEvent;getOriginalExperience()I"),
            remap = false
    )
    private int fermiumMixins_championsEventHandlerCommon_livingXP(LivingExperienceDropEvent event, Operation<Integer> original){
        return event.getDroppedExperience();
    }
}
