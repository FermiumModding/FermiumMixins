package fermiummixins.mixin.reskillable;

import codersafterdark.reskillable.api.data.PlayerData;
import codersafterdark.reskillable.api.data.PlayerDataHandler;
import codersafterdark.reskillable.api.unlockable.Trait;
import codersafterdark.reskillable.base.LevelLockHandler;
import codersafterdark.reskillable.skill.farming.TraitHungryFarmer;
import fermiummixins.handlers.ConfigHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/**
 * Fix by Nischhelm
 */
@Mixin(TraitHungryFarmer.class)
public abstract class TraitHungryFarmer_ReworkMixin extends Trait {
    
    public TraitHungryFarmer_ReworkMixin(ResourceLocation name, int x, int y, ResourceLocation skillName, int cost, String... defaultRequirements) {
        super(name, x, y, skillName, cost, defaultRequirements);
    }

    /**
     * @author Nischhelm
     * @reason fully reworked
     */
    @Overwrite(remap = false)
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;
        if(player == null || player.isCreative() || player.isSpectator()) return;
        
        PlayerData data = PlayerDataHandler.get(player);
        if(data == null || !data.getSkillInfo(this.getParentSkill()).isUnlocked(this)) return;

        if(player.getFoodStats().getFoodLevel() >= 19.5) return;

        //Choose the first item in player inventory (not ContainerPlayer!) that is edible
        ItemStack chosenStack = ItemStack.EMPTY;
        for(int i = 0; i < player.inventory.getSizeInventory(); i++) {
            ItemStack stack = player.inventory.getStackInSlot(i);

            if(stack.isEmpty()) continue;
            if(!(stack.getItem() instanceof ItemFood)) continue;
            if(!LevelLockHandler.canPlayerUseItem(player, stack)) continue;
            
            ResourceLocation loc = stack.getItem().getRegistryName();
            if(loc != null && ConfigHandler.RESKILLABLE_CONFIG.getHungryFarmerBlacklist().contains(loc)) continue;

            chosenStack = stack;
            break;
        }

        if(!chosenStack.isEmpty()) {
            ItemStack chosenStackCopy = chosenStack.copy();
            ItemStack eatenStack = chosenStack.getItem().onItemUseFinish(chosenStack, player.getEntityWorld(), player);

            //Fire Forge LivingEntityUseItemEvent.Finish for thirst and other side effects
            if(ConfigHandler.RESKILLABLE_CONFIG.hungryFarmerFiresForgeEvents) {
                ForgeEventFactory.onItemUseFinish(player, chosenStackCopy, 1, eatenStack);
            }
        }
    }
}


