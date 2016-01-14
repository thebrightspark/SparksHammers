package com.brightspark.sparkshammers.hammerCrafting;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/*
This class is made mostly from the Forge code for ShapedOreRecipe.
I have just adapted it to work for my uses of hammer recipes.

The mirroring has been commented out so that it can't be used.
 */

public class HammerShapedOreRecipe implements IRecipe
{
    //Added in for future ease of change, but hard coded for now.
    private static final int MAX_CRAFT_GRID_WIDTH = 5;
    private static final int MAX_CRAFT_GRID_HEIGHT = 3;

    private ItemStack output = null;
    private Object[] input = null;
    public int width = 0;
    public int height = 0;
    //private boolean mirrored = true;

    public HammerShapedOreRecipe(Block result, Object... recipe){ this(new ItemStack(result), recipe); }
    public HammerShapedOreRecipe(Item result, Object... recipe){ this(new ItemStack(result), recipe); }
    public HammerShapedOreRecipe(ItemStack result, Object... recipe)
    {
        output = result.copy();

        String shape = "";
        int idx = 0;

        /*
        if (recipe[idx] instanceof Boolean)
        {
            mirrored = (Boolean)recipe[idx];
            if (recipe[idx+1] instanceof Object[])
            {
                recipe = (Object[])recipe[idx+1];
            }
            else
            {
                idx = 1;
            }
        }
        */

        if (recipe[idx] instanceof String[])
        {
            String[] parts = ((String[])recipe[idx++]);

            for (String s : parts)
            {
                width = s.length();
                shape += s;
            }

            height = parts.length;
        }
        else
        {
            while (recipe[idx] instanceof String)
            {
                String s = (String)recipe[idx++];
                shape += s;
                width = s.length();
                height++;
            }
        }

        if (width * height != shape.length())
        {
            String ret = "Invalid shaped ore recipe: ";
            for (Object tmp :  recipe)
            {
                ret += tmp + ", ";
            }
            ret += output;
            throw new RuntimeException(ret);
        }

        HashMap<Character, Object> itemMap = new HashMap<Character, Object>();

        for (; idx < recipe.length; idx += 2)
        {
            Character chr = (Character)recipe[idx];
            Object in = recipe[idx + 1];

            if (in instanceof ItemStack)
            {
                itemMap.put(chr, ((ItemStack)in).copy());
            }
            else if (in instanceof Item)
            {
                itemMap.put(chr, new ItemStack((Item)in));
            }
            else if (in instanceof Block)
            {
                itemMap.put(chr, new ItemStack((Block)in, 1, OreDictionary.WILDCARD_VALUE));
            }
            else if (in instanceof String)
            {
                itemMap.put(chr, OreDictionary.getOres((String)in));
            }
            else
            {
                String ret = "Invalid shaped ore recipe: ";
                for (Object tmp :  recipe)
                {
                    ret += tmp + ", ";
                }
                ret += output;
                throw new RuntimeException(ret);
            }
        }

        input = new Object[width * height];
        int x = 0;
        for (char chr : shape.toCharArray())
        {
            input[x++] = itemMap.get(chr);
        }
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    @Override
    public ItemStack getCraftingResult(InventoryCrafting var1){ return output.copy(); }

    /**
     * Returns the size of the recipe area
     */
    @Override
    public int getRecipeSize(){ return input.length; }

    @Override
    public ItemStack getRecipeOutput(){ return output; }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    @Override
    public boolean matches(InventoryCrafting inv, World world)
    {
        if (checkMatch(inv))
        {
            //LogHelper.info("Recipe Checked. We have a match!");
            return true;
        }

        /*
        if (mirrored && checkMatch(inv, true))
        {
            return true;
        }
        */

        //LogHelper.info("Recipe Checked. No match.");
        return false;
    }

    @SuppressWarnings("unchecked")
    private boolean checkMatch(InventoryCrafting inv) //, boolean mirror)
    {
        for (int y = 0; y < MAX_CRAFT_GRID_HEIGHT; y++)
        {
            for (int x = 0; x < MAX_CRAFT_GRID_WIDTH; x++)
            {
                Object target = input[x + (y * MAX_CRAFT_GRID_WIDTH)];

                ItemStack slot = inv.getStackInRowAndColumn(x, y);
                /*
                if(slot == null)
                    LogHelper.info("Crafting slot " + x + "," + y + ": Null");
                else
                    LogHelper.info("Crafting slot " + x + "," + y + ": " + slot.getDisplayName());
                */

                if (target instanceof ItemStack)
                {
                    //LogHelper.info("Comparing slot to: " + ((ItemStack)target).getDisplayName());

                    if (!OreDictionary.itemMatches((ItemStack)target, slot, false))
                    {
                        //LogHelper.info("Crafting slot " + x + "," + y + " doesn't match ore dictionary.");
                        return false;
                    }
                }
                else if (target instanceof ArrayList)
                {
                    boolean matched = false;

                    Iterator<ItemStack> itr = ((ArrayList<ItemStack>)target).iterator();
                    while (itr.hasNext() && !matched)
                    {
                        matched = OreDictionary.itemMatches(itr.next(), slot, false);
                    }

                    if (!matched)
                    {
                        return false;
                    }
                }
                else if (target == null && slot != null)
                {
                    return false;
                }
            }
        }

        return true;
    }

    /*
    public HammerShapedOreRecipe setMirrored(boolean mirror)
    {
        mirrored = mirror;
        return this;
    }
    */

    /**
     * Returns the input for this recipe, any mod accessing this value should never
     * manipulate the values in this array as it will effect the recipe itself.
     * @return The recipes input vales.
     */
    public Object[] getInput()
    {
        return input;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }
}
