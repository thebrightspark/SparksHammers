package com.brightspark.sparkshammers.hammerCrafting;

import com.google.common.collect.Lists;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import java.util.List;

public class HammerCraftingManager
{
    /** The static instance of this class */
    private static final HammerCraftingManager instance = new HammerCraftingManager();
    /** A list of all the recipes added */
    private List<IRecipe> recipes = Lists.newArrayList();

    /**
     * Returns the static instance of this class
     */
    public static HammerCraftingManager getInstance()
    {
        return instance;
    }

    private HammerCraftingManager() {}

    public void addRecipe(ItemStack stack, Object ... recipeObj)
    {
        recipes.add(new HammerShapedOreRecipe(stack, recipeObj));
    }

    public ItemStack findMatchingRecipe(InventoryCrafting invCrafting, World world)
    {
        for(IRecipe irecipe : recipes)
            if(irecipe.matches(invCrafting, world))
                return irecipe.getCraftingResult(invCrafting);

        return null;
    }

    public ItemStack[] func_180303_b(InventoryCrafting p_180303_1_, World worldIn)
    {
        for (IRecipe irecipe : this.recipes)
        {
            if (irecipe.matches(p_180303_1_, worldIn))
            {
                return irecipe.getRemainingItems(p_180303_1_);
            }
        }

        ItemStack[] aitemstack = new ItemStack[p_180303_1_.getSizeInventory()];

        for (int i = 0; i < aitemstack.length; ++i)
        {
            aitemstack[i] = p_180303_1_.getStackInSlot(i);
        }

        return aitemstack;
    }

    /**
     * Returns the List<> of all recipes
     */
    public List getRecipeList()
    {
        return this.recipes;
    }
}
