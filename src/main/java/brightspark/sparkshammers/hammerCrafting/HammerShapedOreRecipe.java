package brightspark.sparkshammers.hammerCrafting;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.List;

/*
This class is made mostly from the Forge code for ShapedOreRecipe.
I have just adapted it to work for my uses of hammer recipes.
 */

public class HammerShapedOreRecipe extends IForgeRegistryEntry.Impl<HammerShapedOreRecipe>
{
    //Added in for future ease of change, but hard coded for now.
    private static final int MAX_CRAFT_GRID_WIDTH = 5;
    private static final int MAX_CRAFT_GRID_HEIGHT = 3;

    private ItemStack output = null;
    private NonNullList<Ingredient> input = null;
    public int width = 0;
    public int height = 0;

    public HammerShapedOreRecipe(Block result, Object... recipe){ this(new ItemStack(result), recipe); }
    public HammerShapedOreRecipe(Item result, Object... recipe){ this(new ItemStack(result), recipe); }
    public HammerShapedOreRecipe(ItemStack result, Object... recipe)
    {
        output = result.copy();
        CraftingHelper.ShapedPrimer primer = CraftingHelper.parseShaped(recipe);
        width = primer.width;
        height = primer.height;
        input = primer.input;
        setRegistryName(result.getItem().getRegistryName());
    }

    public ItemStack getRecipeOutput(){ return output.copy(); }

    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv)
    {
        return ForgeHooks.defaultRecipeGetRemainingItems(inv);
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    public boolean matches(InventoryCrafting inv)
    {
        for (int y = 0; y < MAX_CRAFT_GRID_HEIGHT; y++)
        {
            for (int x = 0; x < MAX_CRAFT_GRID_WIDTH; x++)
            {
                Ingredient target = input.get(x + (y * MAX_CRAFT_GRID_WIDTH));
                ItemStack slot = inv.getStackInRowAndColumn(x, y);

                if(!target.apply(slot))
                    return false;
            }
        }
        return true;
    }

    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder("Output: " + output.getUnlocalizedName() + ", Inputs: ");
        for(Object o : input)
        {
            if(o instanceof ItemStack)
                str.append(((ItemStack)o).getUnlocalizedName());
            else if(o instanceof Item)
                str.append(((Item)o).getUnlocalizedName());
            else if(o instanceof Block)
                str.append(((Block)o).getUnlocalizedName());
            else if(o instanceof String)
                str.append((String)o);
            else if(o instanceof List)
                str.append("List of ").append(((List)o).size());
            else
                str.append("<UNKNOWN>");
            str.append(", ");
        }
        return str.toString().substring(0, str.length()-2);
    }

    /**
     * Gets the inputs
     */
    public NonNullList<Ingredient> getIngredients()
    {
        return input;
    }
}
