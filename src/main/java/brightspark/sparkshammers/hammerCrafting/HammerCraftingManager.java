package brightspark.sparkshammers.hammerCrafting;

import brightspark.sparkshammers.item.ItemAOE;
import brightspark.sparkshammers.util.LoaderHelper;
import com.google.common.collect.Lists;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

import java.util.List;

public class HammerCraftingManager
{
    /** The static instance of this class */
    private static final HammerCraftingManager instance = new HammerCraftingManager();
    /** A list of all the recipes added */
    private List<HammerShapedOreRecipe> recipes = Lists.newArrayList();
    /** A list of all the recipes which do not have any missing ingredients **/
    private List<HammerShapedOreRecipe> validRecipes = null;

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

        return new ItemStack(Blocks.AIR);
    }

    public ItemStack[] func_180303_b(InventoryCrafting p_180303_1_, World worldIn)
    {
        for (IRecipe irecipe : this.recipes)
        {
            if (irecipe.matches(p_180303_1_, worldIn))
            {
                NonNullList<ItemStack> list = irecipe.getRemainingItems(p_180303_1_);
                return list.toArray(new ItemStack[list.size()]);
            }
        }

        ItemStack[] aitemstack = new ItemStack[p_180303_1_.getSizeInventory()];

        for (int i = 0; i < aitemstack.length; ++i)
            aitemstack[i] = p_180303_1_.getStackInSlot(i);

        return aitemstack;
    }

    /**
     * Returns the list of all recipes
     */
    public List<HammerShapedOreRecipe> getRecipeList()
    {
        return this.recipes;
    }

    /**
     * Returns the list of all valid recipes which do not have any missing ingredients
     * This is used by the JEI plugin
     */
    public List<HammerShapedOreRecipe> getValidRecipeList()
    {
        if(validRecipes == null)
        {
            validRecipes = Lists.newArrayList();
            for(HammerShapedOreRecipe recipe : recipes)
            {
                String oreDic = ((ItemAOE) recipe.getRecipeOutput().getItem()).getDependantOreDic();
                if(oreDic == null || LoaderHelper.doesOreExist(oreDic))
                    validRecipes.add(recipe);
            }
        }
        return validRecipes;
    }
}
