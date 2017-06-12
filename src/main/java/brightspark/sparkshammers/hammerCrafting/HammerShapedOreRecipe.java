package brightspark.sparkshammers.hammerCrafting;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.OreIngredient;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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
    private NonNullList<Ingredient> input = null;
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

        if(recipe[idx] instanceof String[])
        {
            String[] parts = ((String[])recipe[idx++]);

            for(String s : parts)
            {
                width = s.length();
                shape += s;
            }

            height = parts.length;
        }
        else
        {
            while(recipe[idx] instanceof String)
            {
                String s = (String)recipe[idx++];
                shape += s;
                width = s.length();
                height++;
            }
        }

        if(width * height != shape.length())
        {
            String ret = "Invalid shaped ore recipe: ";
            for (Object tmp :  recipe)
                ret += tmp + ", ";
            ret += output;
            throw new RuntimeException(ret);
        }

        HashMap<Character, Ingredient> itemMap = new HashMap<>();

        for(; idx < recipe.length; idx += 2)
        {
            Character chr = (Character)recipe[idx];
            Object in = recipe[idx + 1];

            //TODO: Review for 1.12
            if(in instanceof ItemStack)
                itemMap.put(chr, Ingredient.func_193369_a(((ItemStack)in).copy()));
            else if(in instanceof Item)
                itemMap.put(chr, Ingredient.func_193367_a((Item)in));
            else if(in instanceof Block)
                itemMap.put(chr, Ingredient.func_193369_a(new ItemStack((Block)in, 1, OreDictionary.WILDCARD_VALUE)));
            else if(in instanceof String)
                itemMap.put(chr, new OreIngredient((String)in));
            else if(in instanceof Ingredient)
                itemMap.put(chr, (Ingredient) in);
            else
            {
                String ret = "Invalid shaped ore recipe: ";
                for (Object tmp :  recipe)
                    ret += tmp + ", ";
                ret += output;
                throw new RuntimeException(ret);
            }
        }

        //TODO: Review for 1.12
        input = NonNullList.withSize(width * height, Ingredient.field_193370_a);
        int x = 0;
        for(char chr : shape.toCharArray())
            input.add(x++, itemMap.get(chr));
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    @Override
    public ItemStack getCraftingResult(InventoryCrafting var1){ return output.copy(); }

    //TODO: Review for 1.12
    @Override
    public boolean func_194133_a(int i, int j)
    {
        return i >= width && j >= height;
    }

    @Override
    public ItemStack getRecipeOutput(){ return output; }

    @Override
    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv)
    {
        return ForgeHooks.defaultRecipeGetRemainingItems(inv);
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    @Override
    public boolean matches(InventoryCrafting inv, World world)
    {
        if (checkMatch(inv))
            return true;

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
                Ingredient target = input.get(x + (y * MAX_CRAFT_GRID_WIDTH));
                ItemStack slot = inv.getStackInRowAndColumn(x, y);

                if(!target.apply(slot))
                    return false;

                /*
                if (target instanceof ItemStack)
                {
                    if(!OreDictionary.itemMatches((ItemStack)target, slot, false))
                        return false;
                }
                else if(target instanceof List)
                {
                    boolean matched = false;

                    Iterator<ItemStack> itr = ((List<ItemStack>)target).iterator();
                    while(itr.hasNext() && !matched)
                        matched = OreDictionary.itemMatches(itr.next(), slot, false);

                    if(!matched)
                        return false;
                }
                else if(target == null && !slot.isEmpty())
                    return false;
                */
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

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    @Override
    public String toString()
    {
        String str = "Output: " + output.getUnlocalizedName() + ", Inputs: ";
        for(Object o : input)
        {
            if(o instanceof ItemStack)
                str += ((ItemStack)o).getUnlocalizedName();
            else if(o instanceof Item)
                str += ((Item)o).getUnlocalizedName();
            else if(o instanceof Block)
                str += ((Block)o).getUnlocalizedName();
            else if(o instanceof String)
                str += (String)o;
            else if(o instanceof List)
                str += "List of " + ((List)o).size();
            else
                str += "<UNKNOWN>";
            str += ", ";
        }
        str = str.substring(0, str.length()-2);
        return str;
    }

    //TODO: Review for 1.12
    /**
     * Gets the inputs
     */
    @Override
    public NonNullList<Ingredient> func_192400_c()
    {
        return input;
    }

    //TODO: Review for 1.12
    /**
     * Gets the group for this recipe
     */
    @Override
    public String func_193358_e()
    {
        return "";
    }
}
