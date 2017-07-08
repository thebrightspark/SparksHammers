package brightspark.sparkshammers.integration.jei.HammerCraftingTable;

import brightspark.sparkshammers.reference.Reference;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class HammerCraftingRecipeCategory implements IRecipeCategory
{
    private final String locTitle;
    private final IDrawable background;

    public HammerCraftingRecipeCategory(IGuiHelper guiHelper)
    {
        locTitle = I18n.format(Reference.JEI.HAMMER_CRAFTING_TITLE_UNLOC);
        //background = guiHelper.createBlankDrawable(150, 110);
        background = guiHelper.createDrawable(new ResourceLocation(Reference.MOD_ID, Reference.GUI_TEXTURE_DIR + "gui_hammer_craft_nei.png"), 0, 0, 166, 112);
    }

    @Override
    @Nonnull
    public String getUid()
    {
        return Reference.JEI.HAMMER_CRAFTING_UID;
    }

    @Override
    @Nonnull
    public String getTitle()
    {
        return locTitle;
    }

    @Override
    public String getModName()
    {
        return Reference.MOD_NAME;
    }

    @Override
    @Nonnull
    public IDrawable getBackground()
    {
        return background;
    }

    @Nullable
    @Override
    public IDrawable getIcon()
    {
        return null;
    }

    @Override
    public void drawExtras(@Nonnull Minecraft minecraft) {}

    @Override
    //@SuppressWarnings("unchecked")
    public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients)
    {
        if(!(recipeWrapper instanceof HammerCraftingRecipeWrapper))
            return;

        IGuiItemStackGroup guiStacks = recipeLayout.getItemStacks();

        int slotId = 0;

        //Input Slots
        for(int i = 0; i < ingredients.getInputs(ItemStack.class).size(); i++)
        {
            int y = (int) Math.floor(i / 5);
            int x = i - (y * 5);
            if(i < 10)
                guiStacks.init(slotId++, true, 7 + x * 18, 2 + y * 18); //Head
            else
                guiStacks.init(slotId++, true, 43, 38 + x * 18); //Handle
            //guiStacks.set(slotId, itemList);
        }

        //Output Slot
        guiStacks.init(slotId, false, 137, 56);
        guiStacks.set(ingredients);
        //guiStacks.set(slotId, ingredients.getOutputs(ItemStack.class).get(0));
    }
}
