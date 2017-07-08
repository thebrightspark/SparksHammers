package brightspark.sparkshammers.integration.jei.HammerCraftingTable;

import brightspark.sparkshammers.hammerCrafting.HammerShapedOreRecipe;
import brightspark.sparkshammers.integration.jei.SparksHammersPlugin;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import net.minecraft.item.ItemStack;

import java.util.List;

public class HammerCraftingRecipeWrapper implements IRecipeWrapper
{
    public static final Factory FACTORY = new Factory();

    private List<List<ItemStack>> input;
    private ItemStack output;

    public HammerCraftingRecipeWrapper(HammerShapedOreRecipe recipe)
    {
        input = SparksHammersPlugin.jeiHelper.getStackHelper().expandRecipeItemStackInputs(recipe.getIngredients());
        output = recipe.getRecipeOutput();
    }

    @Override
    public void getIngredients(IIngredients ingredients)
    {
        ingredients.setInputLists(ItemStack.class, input);
        ingredients.setOutput(ItemStack.class, output);
    }

    private static class Factory implements IRecipeWrapperFactory<HammerShapedOreRecipe>
    {
        @Override
        public IRecipeWrapper getRecipeWrapper(HammerShapedOreRecipe recipe)
        {
            return new HammerCraftingRecipeWrapper(recipe);
        }
    }
}
