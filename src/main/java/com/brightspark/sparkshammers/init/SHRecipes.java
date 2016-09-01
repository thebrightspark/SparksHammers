package com.brightspark.sparkshammers.init;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class SHRecipes
{
    public static void init()
    {
        //Hammer Crafting Table
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHBlocks.blockHammerCraft), "scs", "chc", "scs", 's', "stone", 'c', Blocks.CRAFTING_TABLE, 'h', new ItemStack(SHItems.hammerWood, 1, OreDictionary.WILDCARD_VALUE)));

        //Wooden Hammer
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHItems.hammerHeadWood), "xxx", "xxx", "   ", 'x', "logWood"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHItems.hammerWood), " x ", " s ", " s ", 'x', SHItems.hammerHeadWood, 's', "plankWood"));

        //Wooden Excavator
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHItems.excavatorHeadWood), " x ", "xxx", "   ", 'x', "logWood"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHItems.excavatorWood), " x ", " s ", " s ", 'x', SHItems.excavatorHeadWood, 's', "plankWood"));
    }
}
