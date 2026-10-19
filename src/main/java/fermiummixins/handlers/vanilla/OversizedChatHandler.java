package fermiummixins.handlers.vanilla;

import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.common.util.Constants;

import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

public class OversizedChatHandler {

	private static final int LIMIT = 32767;

	//Works on a copy, the same message is often sent to several players
	public static ITextComponent shrink(ITextComponent message) {
		ITextComponent copy = message.createCopy();
		walk(copy, OversizedChatHandler::trimItemHover);
		if(fits(copy)) return copy;

		walk(copy, component -> setHover(component.getStyle(), null));
		if(fits(copy)) return copy;

		String text = copy.getUnformattedText();
		for(int length = Math.min(text.length(), LIMIT / 4); ; length /= 2) {
			ITextComponent plain = new TextComponentString(text.substring(0, length) + (length < text.length() ? "..." : ""));
			if(fits(plain) || length == 0) return plain;
		}
	}

	private static boolean fits(ITextComponent message) {
		return ITextComponent.Serializer.componentToJson(message).getBytes(StandardCharsets.UTF_8).length <= LIMIT;
	}

	private static void walk(ITextComponent component, Consumer<ITextComponent> action) {
		action.accept(component);
		for(ITextComponent sibling : component.getSiblings()) walk(sibling, action);
		if(component instanceof TextComponentTranslation) {
			for(Object arg : ((TextComponentTranslation)component).getFormatArgs()) {
				if(arg instanceof ITextComponent) walk((ITextComponent)arg, action);
			}
		}
	}

	//Keep what names the item: id, count, damage and a custom name
	private static void trimItemHover(ITextComponent component) {
		Style style = component.getStyle();
		HoverEvent hover = style.getHoverEvent();
		if(hover == null || hover.getAction() != HoverEvent.Action.SHOW_ITEM) return;
		try {
			NBTTagCompound full = JsonToNBT.getTagFromJson(hover.getValue().getUnformattedText());
			NBTTagCompound item = new NBTTagCompound();
			for(String key : new String[]{"id", "Count", "Damage"}) {
				if(full.hasKey(key)) item.setTag(key, full.getTag(key).copy());
			}
			NBTTagCompound display = full.getCompoundTag("tag").getCompoundTag("display");
			if(display.hasKey("Name", Constants.NBT.TAG_STRING)) {
				NBTTagCompound name = new NBTTagCompound();
				name.setString("Name", display.getString("Name"));
				NBTTagCompound tag = new NBTTagCompound();
				tag.setTag("display", name);
				item.setTag("tag", tag);
			}
			setHover(style, new HoverEvent(HoverEvent.Action.SHOW_ITEM, new TextComponentString(item.toString())));
		}
		catch(Exception e) {
			setHover(style, null);
		}
	}

	private static void setHover(Style style, HoverEvent hover) {
		try {
			style.setHoverEvent(hover);
		}
		catch(UnsupportedOperationException ignored) {
			//Style.ROOT
		}
	}
}
