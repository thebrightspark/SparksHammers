package com.brightspark.sparkshammers.nei;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.RecipeInfo;
import codechicken.nei.recipe.ShapedRecipeHandler;
import com.brightspark.sparkshammers.gui.GuiHammerCraft;
import com.brightspark.sparkshammers.hammerCrafting.HammerCraftingManager;
import com.brightspark.sparkshammers.hammerCrafting.HammerShapedOreRecipe;
import com.brightspark.sparkshammers.init.SHBlocks;
import com.brightspark.sparkshammers.reference.Reference;
import com.brightspark.sparkshammers.util.Lang;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class NEIHammerCraftRecipeHandler extends ShapedRecipeHandler
{
    public class CachedHammerRecipe extends CachedRecipe
    {
        public ArrayList<PositionedStack> ingredients;
        public PositionedStack result;

        public CachedHammerRecipe(HammerShapedOreRecipe recipe)
        {
            this(recipe.getWidth(), recipe.getHeight(), recipe.getInput(), recipe.getRecipeOutput());
        }

        public CachedHammerRecipe(int width, int height, Object[] items, ItemStack out)
        {
            this.result = new PositionedStack(out, 138, 57);
            this.ingredients = new ArrayList<PositionedStack>();
            setIngredients(width, height, items);
        }

        public void setIngredients(int width, int height, Object[] items)
        {
            int handleXStart = 8 + 18 * 2;
            int handleYStart = 3 + 18 * 2;
            PositionedStack stack = null;

            for(int y = 0; y < height; y++)
            {
                for(int x = 0; x < width; x++)
                {
                    if(items[y * width + x] != null)
                    {
                        if(y == 2)
                        {
                            if(x < 4)
                                stack = new PositionedStack(items[y * width + x], handleXStart, handleYStart + x * 18);
                            else
                                stack = null;
                        }
                        else
                            stack = new PositionedStack(items[y * width + x], 8 + x * 18, 3 + y * 18);

                        if(stack != null)
                        {
                            stack.setMaxSize(1);
                            this.ingredients.add(stack);
                        }
                    }
                }
            }
        }

        @Override
        public ArrayList<PositionedStack> getIngredients()
        {
            return (ArrayList<PositionedStack>) getCycledIngredients(NEIHammerCraftRecipeHandler.this.cycleticks / 20, this.ingredients);
        }

        public PositionedStack getResult()
        {
            return this.result;
        }

        public void computeVisuals()
        {
            for (PositionedStack p : ingredients)
                p.generatePermutations();
        }
    }

    @Override
    public int recipiesPerPage()
    {
        return 1;
    }

    @Override
    public String getGuiTexture()
    {
        return new ResourceLocation(Reference.GUI_TEXTURE_DIR + "guiHammerCraftNEI.png").toString();
    }

    @Override
    public String getRecipeName()
    {
        return Lang.localize(SHBlocks.blockHammerCraft.getUnlocalizedName() + ".nei", false);
    }

    public NEIHammerCraftRecipeHandler()
    {
    }

    public void loadTransferRects()
    {
        this.transferRects.add(new RecipeTransferRect(new Rectangle(103, 56, 24, 18), "hammerCraft"));
    }

    public Class<? extends GuiContainer> getGuiClass()
    {
        return GuiHammerCraft.class;
    }

    public void loadCraftingRecipes(String outputId, Object... results)
    {
        if(outputId.equals("hammerCraft") && getClass() == NEIHammerCraftRecipeHandler.class)
        {
            for(IRecipe irecipe : (List<IRecipe>) HammerCraftingManager.getInstance().getRecipeList())
            {
                CachedHammerRecipe recipe = null;
                if(irecipe instanceof HammerShapedOreRecipe)
                    recipe = forgeHammerRecipe((HammerShapedOreRecipe) irecipe);
                /*
                else if(irecipe instanceof HammerShapedRecipe)
                    recipe = new CachedHammerRecipe((HammerShapedRecipe)irecipe);
                */

                if(recipe != null)
                {
                    recipe.computeVisuals();
                    arecipes.add(recipe);
                }
            }
        }
        else
        {
            super.loadCraftingRecipes(outputId, results);
        }

    }

    public void loadCraftingRecipes(ItemStack result)
    {
        for(IRecipe irecipe : (List<IRecipe>) HammerCraftingManager.getInstance().getRecipeList())
        {
            if(NEIServerUtils.areStacksSameTypeCrafting(irecipe.getRecipeOutput(), result))
            {
                CachedHammerRecipe recipe = null;
                if(irecipe instanceof HammerShapedOreRecipe)
                    recipe = forgeHammerRecipe((HammerShapedOreRecipe) irecipe);
                /*
                else if(irecipe instanceof HammerShapedRecipe)
                    recipe = new CachedHammerRecipe((HammerShapedRecipe)irecipe);
                */

                if(recipe != null)
                {
                    recipe.computeVisuals();
                    arecipes.add(recipe);
                }
            }
        }

    }

    public void loadUsageRecipes(ItemStack ingredient)
    {
        for(IRecipe irecipe : (List<IRecipe>) HammerCraftingManager.getInstance().getRecipeList())
        {
            CachedHammerRecipe recipe = null;
            if(irecipe instanceof HammerShapedOreRecipe)
                recipe = forgeHammerRecipe((HammerShapedOreRecipe) irecipe);
            /*
            else if(irecipe instanceof HammerShapedRecipe)
                recipe = new CachedHammerRecipe((HammerShapedRecipe)irecipe);
            */

            if(recipe != null && recipe.contains(recipe.ingredients, ingredient.getItem()))
            {
                recipe.computeVisuals();
                if(recipe.contains(recipe.ingredients, ingredient))
                {
                    recipe.setIngredientPermutation(recipe.ingredients, ingredient);
                    arecipes.add(recipe);
                }
            }
        }
    }

    public CachedHammerRecipe forgeHammerRecipe(HammerShapedOreRecipe recipe)
    {
        int width = recipe.getWidth();
        int height = recipe.getHeight();

        Object[] items = recipe.getInput();
        for(Object item : items)
            if(item instanceof List && ((List<?>)item).isEmpty())
                return null;

        return new CachedHammerRecipe(width, height, items, recipe.getRecipeOutput());
    }

    public String getOverlayIdentifier()
    {
        return "hammerCraft";
    }

    public boolean hasOverlay(GuiContainer gui, net.minecraft.inventory.Container container, int recipe)
    {
        return RecipeInfo.hasDefaultOverlay(gui, "hammerCraft");
    }

    public void drawBackground(int recipe)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GuiDraw.changeTexture(getGuiTexture());
        GuiDraw.drawTexturedModalRect(0, 0, 0, 0, 165, 111);
    }

    public boolean isRecipe2x2(int recipe)
    {
        return false;
    }
}
