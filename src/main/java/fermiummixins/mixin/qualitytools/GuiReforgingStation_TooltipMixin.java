package fermiummixins.mixin.qualitytools;

import com.tmtravlr.qualitytools.QualityToolsHelper;
import com.tmtravlr.qualitytools.reforging.GuiButtonReforgingStation;
import com.tmtravlr.qualitytools.reforging.GuiReforgingStation;
import com.tmtravlr.qualitytools.reforging.TileEntityReforgingStation;
import fermiummixins.config.QualityToolsConfig;
import fermiummixins.handlers.ConfigHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.TextFormatting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Fix by cdstk
 */
@Mixin(GuiReforgingStation.class)
public abstract class GuiReforgingStation_TooltipMixin extends GuiContainer {
	
	@Shadow(remap = false)
	@Final
	private TileEntityReforgingStation tileReforgingStation;
	
	@Shadow(remap = false)
	private GuiButtonReforgingStation reforgeButton;
	
	public GuiReforgingStation_TooltipMixin(Container inventorySlotsIn) {
		super(inventorySlotsIn);
	}
	
	@Inject(
			method = "drawScreen",
			at = @At("TAIL")
	)
	private void fermiummixins_qualityToolsGuiReforgingStation_drawScreen(int mouseX, int mouseY, float partialTicks, CallbackInfo ci) {
		ItemStack tool = this.tileReforgingStation.getStackInSlot(0);
		if(tool != null && !tool.isEmpty()) {
			if(mouseX >= reforgeButton.x && mouseY >= reforgeButton.y && mouseX < reforgeButton.x + reforgeButton.width && mouseY < reforgeButton.y + reforgeButton.height) {
				if(this.mc.player.inventory.getItemStack().isEmpty()) {
					if(ConfigHandler.QUALITYTOOLS_CONFIG.reforgeStationQualityShowType == QualityToolsConfig.QualityToolTipType.FULL_ITEM)
						this.renderToolTip(tool, mouseX, mouseY + 20);
					else {
						FontRenderer font = tool.getItem().getFontRenderer(tool);
						this.drawHoveringText(fermiumMixins$getQualityTooltip(tool), mouseX, mouseY + 20, (font == null ? fontRenderer : font));
					}
				}
			}
		}
	}

	@Unique
	//Copied and modified from com.tmtravlr.qualitytools.ClientEventHandler::onItemTooltip
	private static List<String> fermiumMixins$getQualityTooltip(ItemStack stack){
		List<String> tooltips = new ArrayList<>();

		NBTTagCompound tag = QualityToolsHelper.getQualityTag(stack);
		if (tag.isEmpty()) return tooltips;

		TextFormatting color = TextFormatting.getValueByName(tag.getString("Color"));
		tooltips.add(TextFormatting.GRAY + I18n.format("info.quality.name") + (color == null ? "" : color) + I18n.format(tag.getString("Name")));

		if(ConfigHandler.QUALITYTOOLS_CONFIG.reforgeStationQualityShowType == QualityToolsConfig.QualityToolTipType.ONLY_QUALITY_NAME) return tooltips;

		NBTTagList slotList = tag.getTagList("Slots", 8);
		for(int i = 0; i < slotList.tagCount(); ++i) {
			String slot = slotList.getStringTagAt(i);
			tooltips.add(TextFormatting.GRAY + I18n.format("item.modifiers." + slot.toLowerCase()));
		}

		NBTTagList attributeList = tag.getTagList("AttributeModifiers", 10);

		for(int i = 0; i < attributeList.tagCount(); ++i) {
			AttributeModifier modifier = SharedMonsterAttributes.readAttributeModifierFromNBT(attributeList.getCompoundTagAt(i));
			String attributeName = attributeList.getCompoundTagAt(i).getString("AttributeName");
			double displayAmount = modifier.getAmount() * (modifier.getOperation() != 0 ? 100 : 1);

			if (displayAmount > 0) {
				tooltips.add(TextFormatting.BLUE + " " + I18n.format("attribute.modifier.plus." + modifier.getOperation(), ItemStack.DECIMALFORMAT.format(displayAmount), I18n.format("attribute.name." + attributeName)));
			} else if (displayAmount < 0) {
				displayAmount *= -1.0F;
				tooltips.add(TextFormatting.RED + " " + I18n.format("attribute.modifier.take." + modifier.getOperation(), ItemStack.DECIMALFORMAT.format(displayAmount), I18n.format("attribute.name." + attributeName)));
			}
		}

		return tooltips;
	}
}