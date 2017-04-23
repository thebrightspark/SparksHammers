package brightspark.sparkshammers.integration.jei.HammerCraftingTable;

import brightspark.sparkshammers.hammerCrafting.HammerShapedOreRecipe;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HammerCraftingRecipeWrapper extends BlankRecipeWrapper
{
    private final List input;
    private final ItemStack output;

    public HammerCraftingRecipeWrapper(HammerShapedOreRecipe recipe)
    {
        input = Arrays.asList(recipe.getInput());
        output = recipe.getRecipeOutput();
    }

    @Override
    @Nonnull
    public List getInputs()
    {
        return input;
    }

    @Override
    @Nonnull
    public List<ItemStack> getOutputs()
    {
        return Collections.singletonList(output);
    }
}
