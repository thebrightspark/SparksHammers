package com.brightspark.sparkshammers.integration.jei.HammerCraftingTable;

import com.brightspark.sparkshammers.hammerCrafting.HammerShapedOreRecipe;
import com.brightspark.sparkshammers.reference.Reference;
import com.brightspark.sparkshammers.util.LogHelper;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

import javax.annotation.Nonnull;

public class HammerCraftingRecipeHandler implements IRecipeHandler<HammerShapedOreRecipe>
{
    @Nonnull
    @Override
    public Class<HammerShapedOreRecipe> getRecipeClass()
    {
        return HammerShapedOreRecipe.class;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid()
    {
        return Reference.JEI.HAMMER_CRAFTING_UID;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid(@Nonnull HammerShapedOreRecipe recipe)
    {
        return Reference.JEI.HAMMER_CRAFTING_UID;
    }

    @Nonnull
    @Override
    public IRecipeWrapper getRecipeWrapper(@Nonnull HammerShapedOreRecipe recipe)
    {
        return new HammerCraftingRecipeWrapper(recipe);
    }

    @Override
    public boolean isRecipeValid(@Nonnull HammerShapedOreRecipe recipe)
    {
        if(recipe.getRecipeOutput() == null)
        {
            LogHelper.error("Recipe has no output -> " + recipe.toString());
            return false;
        }
        if(recipe.getRecipeSize() > (recipe.getWidth() * recipe.getHeight()))
        {
            LogHelper.error("Recipe has too many inputs -> " + recipe.toString());
            return false;
        }
        if(recipe.getRecipeSize() == 0)
        {
            LogHelper.error("Recipe has no inputs -> " + recipe.toString());
            return false;
        }

        return true;
    }
}
