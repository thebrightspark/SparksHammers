package com.brightspark.sparkshammers.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class SHRecipies
{
    public static void init()
    {
        //Register recipies here
        //Hammer heads
        GameRegistry.addShapedRecipe(new ItemStack(SHItems.itemHeadWood), new Object[] {"xxx", "xxx", "   ", 'x', Blocks.planks});
        GameRegistry.addShapedRecipe(new ItemStack(SHItems.itemHeadStone), new Object[] {"xxx", "xxx", 'x', Blocks.cobblestone});
        GameRegistry.addShapedRecipe(new ItemStack(SHItems.itemHeadIron), new Object[] {"xxx", "xxx", 'x', Items.iron_ingot});
        GameRegistry.addShapedRecipe(new ItemStack(SHItems.itemHeadGold), new Object[] {"xxx", "xxx", 'x', Items.gold_ingot});
        GameRegistry.addShapedRecipe(new ItemStack(SHItems.itemHeadDiamond), new Object[] {"xxx", "xxx", 'x', Items.diamond});
        //Hammers
        GameRegistry.addShapedRecipe(new ItemStack(SHItems.hammerWood), new Object[] {" x ", " y ", " y ", 'x', SHItems.itemHeadWood, 'y', Items.stick});
        GameRegistry.addShapedRecipe(new ItemStack(SHItems.hammerStone), new Object[] {" x ", " y ", " y ", 'x', SHItems.itemHeadStone, 'y', Items.stick});
        GameRegistry.addShapedRecipe(new ItemStack(SHItems.hammerIron), new Object[] {" x ", " y ", " y ", 'x', SHItems.itemHeadIron, 'y', Items.stick});
        GameRegistry.addShapedRecipe(new ItemStack(SHItems.hammerGold), new Object[] {" x ", " y ", " y ", 'x', SHItems.itemHeadGold, 'y', Items.stick});
        GameRegistry.addShapedRecipe(new ItemStack(SHItems.hammerDiamond), new Object[] {" x ", " y ", " y ", 'x', SHItems.itemHeadDiamond, 'y', Items.stick});
    }
}
