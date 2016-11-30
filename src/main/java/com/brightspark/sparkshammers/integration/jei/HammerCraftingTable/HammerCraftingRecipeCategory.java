package com.brightspark.sparkshammers.integration.jei.HammerCraftingTable;

import com.brightspark.sparkshammers.reference.Reference;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;

public class HammerCraftingRecipeCategory implements IRecipeCategory
{
    private final String locTitle;
    private final IDrawable background;

    public HammerCraftingRecipeCategory(IGuiHelper guiHelper)
    {
        locTitle = I18n.format(Reference.JEI.HAMMER_CRAFTING_TITLE_UNLOC);
        //background = guiHelper.createBlankDrawable(150, 110);
        background = guiHelper.createDrawable(new ResourceLocation(Reference.MOD_ID, Reference.GUI_TEXTURE_DIR + "guiHammerCraftNEI.png"), 0, 0, 166, 112);
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

    @Override
    public void drawExtras(@Nonnull Minecraft minecraft) {}

    @Override
    public void drawAnimations(@Nonnull Minecraft minecraft) {}

    @Override
    @SuppressWarnings("unchecked")
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull IRecipeWrapper recipeWrapper)
    {
        if(!(recipeWrapper instanceof HammerCraftingRecipeWrapper))
            return;

        HammerCraftingRecipeWrapper wrapper = (HammerCraftingRecipeWrapper) recipeWrapper;
        IGuiItemStackGroup guiStacks = recipeLayout.getItemStacks();

        int slotId = 0;

        //Input Slots
        List inputs = wrapper.getInputs();
        for(Object o : inputs)
        {
            int y = (int) Math.floor(slotId / 5);
            int x = slotId - (y * 5);
            if(slotId < 10)
                guiStacks.init(slotId, true, 7 + x * 18, 2 + y * 18); //Head
            else
                guiStacks.init(slotId, true, 43, 38 + x * 18); //Handle
            if(o instanceof Collection)
                guiStacks.set(slotId, (Collection) o);
            else if(o instanceof ItemStack)
                guiStacks.set(slotId, (ItemStack) o);
            slotId++;
        }

        //Output Slot
        guiStacks.init(slotId, false, 137, 56);
        guiStacks.set(slotId, wrapper.getOutputs().get(0));
    }
}
