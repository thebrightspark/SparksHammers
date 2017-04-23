package brightspark.sparkshammers.integration.jei;

import brightspark.sparkshammers.gui.GuiHammerCraft;
import brightspark.sparkshammers.hammerCrafting.HammerCraftingManager;
import brightspark.sparkshammers.init.SHBlocks;
import brightspark.sparkshammers.init.SHItems;
import brightspark.sparkshammers.integration.jei.HammerCraftingTable.HammerCraftingRecipeCategory;
import brightspark.sparkshammers.integration.jei.HammerCraftingTable.HammerCraftingRecipeHandler;
import brightspark.sparkshammers.item.ItemAOE;
import brightspark.sparkshammers.reference.Reference;
import brightspark.sparkshammers.util.LoaderHelper;
import brightspark.sparkshammers.util.LogHelper;
import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

@JEIPlugin
public class SparksHammersPlugin extends BlankModPlugin
{
    public static IJeiHelpers jeiHelper;

    @Override
    public void register(@Nonnull IModRegistry registry)
    {
        jeiHelper = registry.getJeiHelpers();

        registry.addRecipeCategories(new HammerCraftingRecipeCategory(jeiHelper.getGuiHelper()));
        registry.addRecipeHandlers(new HammerCraftingRecipeHandler());

        registry.addRecipes(HammerCraftingManager.getInstance().getRecipeList());

        registry.addRecipeClickArea(GuiHammerCraft.class, 111, 69, 26, 19, Reference.JEI.HAMMER_CRAFTING_UID);
        registry.addRecipeCategoryCraftingItem(new ItemStack(SHBlocks.blockHammerCraft), Reference.JEI.HAMMER_CRAFTING_UID);

        //Hide tools from JEI if you can't make them!
        int count = 0;
        for(ItemAOE tool : SHItems.AOE_TOOLS)
            if(tool.getDependantOreDic() != null && !LoaderHelper.doesOreExist(tool.getDependantOreDic()))
            {
                jeiHelper.getItemBlacklist().addItemToBlacklist(new ItemStack(tool));
                count++;
            }
        if(count > 0)
            LogHelper.info("Hidden " + count + " tools from JEI due to missing ingredients");
    }
}
