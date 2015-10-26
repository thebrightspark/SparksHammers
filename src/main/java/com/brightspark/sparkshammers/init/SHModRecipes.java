package com.brightspark.sparkshammers.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class SHModRecipes
{
    public static void init()
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHModItems.itemHeadManasteel), new Object[]{"xxx", "xxx", "   ", 'x', "ingotManasteel"}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHModItems.itemHeadTerrasteel), new Object[]{"xxx", "xxx", "   ", 'x', "ingotTerrasteel"}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHModItems.itemHeadElementium), new Object[]{"xxx", "xxx", "   ", 'x', "ingotElvenElementium"}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHModItems.hammerManasteel), new Object[] {" x ", " y ", " y ", 'x', SHModItems.itemHeadManasteel, 'y', "stickWood"}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHModItems.hammerTerrasteel), new Object[] {" x ", " y ", " y ", 'x', SHModItems.itemHeadTerrasteel, 'y', "stickWood"}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHModItems.hammerElementium), new Object[] {" x ", " y ", " y ", 'x', SHModItems.itemHeadElementium, 'y', "stickWood"}));
    }
}
