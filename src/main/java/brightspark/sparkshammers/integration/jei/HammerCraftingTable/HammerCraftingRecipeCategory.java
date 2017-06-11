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
import java.util.List;

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

        HammerCraftingRecipeWrapper wrapper = (HammerCraftingRecipeWrapper) recipeWrapper;
        IGuiItemStackGroup guiStacks = recipeLayout.getItemStacks();

        int slotId = 0;

        //Input Slots
        List<List<ItemStack>> inputs = ingredients.getInputs(ItemStack.class);
        for(List<ItemStack> itemList : inputs)
        {
            int y = (int) Math.floor(slotId / 5);
            int x = slotId - (y * 5);
            if(slotId < 10)
                guiStacks.init(slotId, true, 7 + x * 18, 2 + y * 18); //Head
            else
                guiStacks.init(slotId, true, 43, 38 + x * 18); //Handle
            guiStacks.set(slotId, itemList);
            slotId++;
        }

        //Output Slot
        guiStacks.init(slotId, false, 137, 56);
        guiStacks.set(slotId, ingredients.getOutputs(ItemStack.class).get(0));
    }
}
