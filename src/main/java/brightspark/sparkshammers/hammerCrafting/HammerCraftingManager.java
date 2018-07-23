package brightspark.sparkshammers.hammerCrafting;

import brightspark.sparkshammers.item.ItemAOE;
import com.google.common.collect.Lists;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.List;

public class HammerCraftingManager
{
    public static IForgeRegistry<HammerShapedOreRecipe> REGISTRY;

    /**
     * Returns the static instance of this class
     */
    public static void setRegistry(IForgeRegistry<HammerShapedOreRecipe> registry)
    {
        REGISTRY = registry;
    }

    public static List<HammerShapedOreRecipe> getRecipes()
    {
        return REGISTRY.getValues();
    }

    private HammerCraftingManager() {}

    public static ItemStack findMatchingRecipe(InventoryCrafting invCrafting)
    {
        for(HammerShapedOreRecipe recipe : REGISTRY)
            if(recipe.matches(invCrafting))
                return recipe.getRecipeOutput();

        return ItemStack.EMPTY;
    }

    public static NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv)
    {
        for (HammerShapedOreRecipe recipe : REGISTRY)
            if (recipe.matches(inv))
                return recipe.getRemainingItems(inv);
        return ForgeHooks.defaultRecipeGetRemainingItems(inv);
    }

    /**
     * Returns the list of all valid recipes which do not have any missing ingredients
     * This is used by the JEI plugin
     */
    public static List<HammerShapedOreRecipe> getValidRecipeList()
    {
        List<HammerShapedOreRecipe> validRecipes = Lists.newArrayList();
        for(HammerShapedOreRecipe recipe : REGISTRY)
        {
            String oreDic = ((ItemAOE) recipe.getRecipeOutput().getItem()).getDependantOreDic();
            if(oreDic == null || !OreDictionary.getOres(oreDic).isEmpty())
                validRecipes.add(recipe);
        }
        return validRecipes;
    }
}
