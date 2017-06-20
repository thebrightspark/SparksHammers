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
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;

import java.util.List;

/*
This class is made mostly from the Forge code for ShapedOreRecipe.
I have just adapted it to work for my uses of hammer recipes.

The mirroring has been commented out so that it can't be used.
 */

public class HammerShapedOreRecipe extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe
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
        CraftingHelper.ShapedPrimer primer = CraftingHelper.parseShaped(recipe);
        width = primer.width;
        height = primer.height;
        input = primer.input;
        //mirrored = primer.mirrored;
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    @Override
    public ItemStack getCraftingResult(InventoryCrafting var1){ return output.copy(); }

    @Override
    public boolean canFit(int i, int j)
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

    /**
     * Gets the inputs
     */
    @Override
    public NonNullList<Ingredient> getIngredients()
    {
        return input;
    }

    /**
     * Gets the group for this recipe
     */
    @Override
    public String getGroup()
    {
        return "";
    }
}
