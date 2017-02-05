package com.brightspark.sparkshammers.init;

import com.brightspark.sparkshammers.hammerCrafting.HammerCraftingManager;
import com.brightspark.sparkshammers.item.ItemAOE;
import com.brightspark.sparkshammers.reference.Names;
import com.brightspark.sparkshammers.util.CommonUtils;
import com.brightspark.sparkshammers.util.LoaderHelper;
import com.brightspark.sparkshammers.util.LogHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class SHRecipes
{
    public static void init()
    {
        //Hammer Crafting Table
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHBlocks.blockHammerCraft), "scs", "chc", "scs", 's', "stone", 'c', Blocks.CRAFTING_TABLE, 'h', new ItemStack(SHItems.getItemById("hammer_wood"), 1, OreDictionary.WILDCARD_VALUE)));

        //Wooden Hammer
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHItems.hammerHeadWood), "xxx", "xxx", "   ", 'x', "logWood"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHItems.getItemById("hammer_wood")), " x ", " s ", " s ", 'x', SHItems.hammerHeadWood, 's', "plankWood"));

        //Wooden Excavator
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHItems.excavatorHeadWood), " x ", "xxx", "   ", 'x', "logWood"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHItems.getItemById("excavator_wood")), " x ", " s ", " s ", 'x', SHItems.excavatorHeadWood, 's', "plankWood"));

        /*
         * Hammer Crafting Table Recipes
         */

        HammerCraftingManager hammerCraft = HammerCraftingManager.getInstance();

        hammerCraft.addRecipe(new ItemStack(SHItems.hammerMini), " HHH ", " HHH ", "SSSS ", 'H', Items.IRON_INGOT, 'S', "stickWood");
        hammerCraft.addRecipe(new ItemStack(SHItems.hammerGiant), "HHHHH", "HHDHH", "SSSS ", 'H', Blocks.IRON_BLOCK, 'S', "stickWood", 'D', new ItemStack(Items.DYE, 1, 5));
        hammerCraft.addRecipe(new ItemStack(SHItems.hammerNetherStar), "HHBHH", "HBNBH", "SSSS ", 'H', Items.DIAMOND, 'B', Blocks.GOLD_BLOCK, 'N', Items.NETHER_STAR, 'S', "stickWood");

        //Create recipes for all tools which have an ore dictionary ready for the item ingredient
        for(ItemAOE tool : SHItems.AOE_TOOLS)
        {
            String oreDic = tool.getDependantOreDic();
            if(oreDic == null)
            {
                //LogHelper.warn("No dependant ore dictionary entry for tool " + tool.getRegistryName().getResourcePath());
                continue;
            }
            String topRow = tool.isExcavator ? " HHH " : "HHHHH";
            if(oreDic.equals(Names.EnumMaterials.STONE.dependantOreDic) && LoaderHelper.isModLoaded(Names.Mods.EXTRA_UTILITIES))
            {
                //Swap out for compressed cobblestone
                Item compressedCobble = CommonUtils.getRegisteredItem(Names.ModItemIds.COMPRESSED_COBBLE);
                if(compressedCobble != null)
                {
                    LogHelper.info("Compressed Cobblestone found in " + Names.Mods.EXTRA_UTILITIES + ". Using for " + tool.getRegistryName().getResourcePath() + " recipe.");
                    hammerCraft.addRecipe(new ItemStack(tool), topRow, "HHHHH", "SSSS ", 'H', new ItemStack(compressedCobble), 'S', "stickWood");
                    continue;
                }
                else
                    LogHelper.warn("Compressed Cobblestone not found in " + Names.Mods.EXTRA_UTILITIES + ". Resorting to normal recipes. Please report this to mod author!");
            }
            hammerCraft.addRecipe(new ItemStack(tool), topRow, "HHHHH", "SSSS ", 'H', oreDic, 'S', "stickWood");
        }
    }
}
