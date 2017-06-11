package brightspark.sparkshammers.integration.jei.HammerCraftingTable;

import brightspark.sparkshammers.hammerCrafting.HammerShapedOreRecipe;
import brightspark.sparkshammers.integration.jei.SparksHammersPlugin;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class HammerCraftingRecipeWrapper extends BlankRecipeWrapper
{
    private List<List<ItemStack>> input;
    private ItemStack output;

    public HammerCraftingRecipeWrapper(HammerShapedOreRecipe recipe)
    {
        IStackHelper stackHelper = SparksHammersPlugin.jeiHelper.getStackHelper();
        input = stackHelper.expandRecipeItemStackInputs(Arrays.asList(recipe.getInput()));
        output = recipe.getRecipeOutput();
    }

    @Override
    public void getIngredients(IIngredients ingredients)
    {
        ingredients.setInputLists(ItemStack.class, input);
        ingredients.setOutput(ItemStack.class, output);
    }
}
