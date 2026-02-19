package fermiummixins.mixin.jei;

import fermiummixins.handlers.ConfigHandler;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IVanillaRecipeFactory;
import mezz.jei.plugins.vanilla.anvil.AnvilRecipeMaker;
import mezz.jei.util.ErrorUtil;
import mezz.jei.util.Log;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.*;
import java.util.stream.Collectors;

@Mixin(AnvilRecipeMaker.class)
public abstract class AnvilRecipeMaker_EnchantmentMixin {
    
    @Shadow(remap = false)
    @Final
    private static ItemStack ENCHANTED_BOOK;
    
    @Inject(
            method = "getBookEnchantmentRecipes(Ljava/util/List;Lmezz/jei/api/recipe/IVanillaRecipeFactory;Lmezz/jei/api/ingredients/IIngredientRegistry;)V",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private static void fermiummixins_jeiAnvilRecipeMaker_getBookEnchantmentRecipes(List<IRecipeWrapper> recipes, IVanillaRecipeFactory vanillaRecipeFactory, IIngredientRegistry ingredientRegistry, CallbackInfo ci) {
        if(!ConfigHandler.JEI_CONFIG.simplifiedAnvilEnchantmentRecipes) {
            ci.cancel();
            return;
        }
        
        List<ItemStack> ingredients = ingredientRegistry.getAllIngredients(VanillaTypes.ITEM).stream().filter(ItemStack::isItemEnchantable).collect(Collectors.toList());
        Collection<Enchantment> enchantments = ForgeRegistries.ENCHANTMENTS.getValuesCollection();
        
        Map<Enchantment,List<ItemStack>> bookMap = new HashMap<>();
        for(Enchantment enchantment : enchantments) {
            List<ItemStack> bookList = new ArrayList<>();
            for(int i = 1; i <= enchantment.getMaxLevel(); i++) {
                Map<Enchantment,Integer> enchMap = Collections.singletonMap(enchantment, i);
                ItemStack bookEnchant = ENCHANTED_BOOK.copy();
                EnchantmentHelper.setEnchantments(enchMap, bookEnchant);
                bookList.add(bookEnchant);
            }
            if(!bookList.isEmpty()) bookMap.put(enchantment, bookList);
        }
        
        for(ItemStack ingredient : ingredients) {
            try {
                List<ItemStack> applicableBooks = new ArrayList<>();
                for(Map.Entry<Enchantment,List<ItemStack>> entry : bookMap.entrySet()) {
                    if(entry.getKey().canApply(ingredient) && ingredient.getItem().isBookEnchantable(ingredient, entry.getValue().get(0))) {
                        applicableBooks.addAll(entry.getValue());
                    }
                }
                if(!applicableBooks.isEmpty()) {
                    IRecipeWrapper anvilRecipe = vanillaRecipeFactory.createAnvilRecipe(ingredient, applicableBooks, Collections.nCopies(applicableBooks.size(), ingredient));
                    recipes.add(anvilRecipe);
                }
            }
            catch (RuntimeException e) {
                String ingredientInfo = ErrorUtil.getIngredientInfo(ingredient);
                Log.get().error("Failed to register book enchantment recipes for ingredient: {}", ingredientInfo, e);
            }
        }
        ci.cancel();
    }
}