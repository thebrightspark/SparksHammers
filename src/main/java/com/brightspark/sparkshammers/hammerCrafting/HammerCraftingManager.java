package com.brightspark.sparkshammers.hammerCrafting;

import com.brightspark.sparkshammers.init.SHItems;
import com.brightspark.sparkshammers.util.LogHelper;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class HammerCraftingManager
{
    /** The static instance of this class */
    private static final HammerCraftingManager instance = new HammerCraftingManager();
    /** A list of all the recipes added */
    private List recipes = new ArrayList();

    /**
     * Returns the static instance of this class
     */
    public static final HammerCraftingManager getInstance()
    {
        /** The static instance of this class */
        return instance;
    }

    private HammerCraftingManager()
    {
        //Add recipes here

        addRecipe(new ItemStack(SHItems.hammerIron), new Object[]{"IIIII", "IIIII", "SSSS ", 'I', Items.iron_ingot, 'S', Items.stick});

        LogHelper.info("HammerCraftingManager: " + recipes.size() + " recipes added.");
    }

    public HammerShapedOreRecipe addRecipe(ItemStack stack, Object ... recipeObj)
    {
        HammerShapedOreRecipe recipe = new HammerShapedOreRecipe(stack, recipeObj);
        this.recipes.add(recipe);
        return recipe;

        //Old code
        /*
        String s = "";
        int i = 0;
        int j = 0;
        int k = 0;

        if (recipeObj[i] instanceof String[])
        {
            String[] astring = (String[])((String[])recipeObj[i++]);

            for (int l = 0; l < astring.length; ++l)
            {
                String s1 = astring[l];
                ++k;
                j = s1.length();
                s = s + s1;
            }
        }
        else
        {
            while (recipeObj[i] instanceof String)
            {
                String s2 = (String)recipeObj[i++];
                ++k;
                j = s2.length();
                s = s + s2;
            }
        }

        HashMap hashmap;

        for (hashmap = new HashMap(); i < recipeObj.length; i += 2)
        {
            Character character = (Character)recipeObj[i];
            ItemStack itemstack1 = null;

            if (recipeObj[i + 1] instanceof Item)
            {
                itemstack1 = new ItemStack((Item)recipeObj[i + 1]);
            }
            else if (recipeObj[i + 1] instanceof Block)
            {
                itemstack1 = new ItemStack((Block)recipeObj[i + 1], 1, 32767);
            }
            else if (recipeObj[i + 1] instanceof ItemStack)
            {
                itemstack1 = (ItemStack)recipeObj[i + 1];
            }

            hashmap.put(character, itemstack1);
        }

        ItemStack[] aitemstack = new ItemStack[j * k];

        for (int i1 = 0; i1 < j * k; ++i1)
        {
            char c0 = s.charAt(i1);

            if (hashmap.containsKey(Character.valueOf(c0)))
            {
                aitemstack[i1] = ((ItemStack)hashmap.get(Character.valueOf(c0))).copy();
            }
            else
            {
                aitemstack[i1] = null;
            }
        }

        ShapedRecipes shapedrecipes = new ShapedRecipes(j, k, aitemstack, stack);
        this.recipes.add(shapedrecipes);
        return shapedrecipes;
        */
    }

    public ItemStack findMatchingRecipe(InventoryCrafting invCrafting, World world)
    {
        int j;
        for (j = 0; j < this.recipes.size(); ++j)
        {
            IRecipe irecipe = (IRecipe)this.recipes.get(j);

            if (irecipe.matches(invCrafting, world))
            {
                return irecipe.getCraftingResult(invCrafting);
            }
        }

        return null;
    }

    /**
     * returns the List<> of all recipes
     */
    public List getRecipeList()
    {
        return this.recipes;
    }
}
