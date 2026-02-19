package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class JEIConfig {
	
	@Config.Comment("Makes JEI ignore anvil enchantment recipes to save on a significant amount of memory")
	@Config.Name("Ignore Anvil Enchantment Recipes (JEI)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.jei.enchant.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.JEI_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean ignoreAnvilEnchantmentRecipes = false;
	
	@Config.Comment("Simplifies JEI anvil enchantment recipes instead to save on most of the memory without removing them fully" + "\n" +
			"Shows the base item as input and cycles applicable enchantments + levels in the same recipe, and leaves output as unenchanted copy" + "\n" +
			"Requires \"Ignore Anvil Enchantment Recipes (JEI)\" enabled")
	@Config.Name("Simplified Anvil Enchantment Recipes (JEI)")
	@Config.RequiresMcRestart
	public boolean simplifiedAnvilEnchantmentRecipes = false;
}