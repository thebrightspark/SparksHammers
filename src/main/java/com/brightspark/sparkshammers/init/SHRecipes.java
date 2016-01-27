package com.brightspark.sparkshammers.init;

import com.brightspark.sparkshammers.reference.Config;
import com.brightspark.sparkshammers.reference.Names;
import com.brightspark.sparkshammers.util.LoaderHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class SHRecipes
{
    @SuppressWarnings("all")
    public static void init()
    {
        //Hammer Crafting Table
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHBlocks.blockHammerCraft), new Object[]{"scs", "chc", "scs", 's', "stone", 'c', Blocks.crafting_table, 'h', new ItemStack(SHItems.hammerWood, 1, OreDictionary.WILDCARD_VALUE)}));

        //Wooden Hammer
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHItems.hammerHeadWood), new Object[]{"xxx", "xxx", "   ", 'x', "logWood"}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHItems.hammerWood), new Object[]{" x ", " s ", " s ", 'x', SHItems.hammerHeadWood, 's', "plankWood"}));

        //Wooden Excavator
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHItems.excavatorHeadWood), new Object[]{" x ", "xxx", "   ", 'x', "logWood"}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHItems.excavatorWood), new Object[]{" x ", " s ", " s ", 'x', SHItems.excavatorHeadWood, 's', "plankWood"}));

        //'Easy' Unstable Hammer Recipe
        if(Config.useEasyUnstableRecipe && LoaderHelper.isModLoaded(Names.Mods.EXTRA_UTILITIES))
        {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHModItems.hammerHeadUnstable), new Object[]{"xxx", "xxx", "   ", 'x', Names.ModOreDicts.INGOT_UNSTABLE}));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHModItems.hammerUnstable), new Object[]{" x ", " y ", " y ", 'x', SHModItems.hammerHeadUnstable, 'y', "stickWood"}));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHModItems.excavatorHeadUnstable), new Object[]{" x ", "xxx", "   ", 'x', Names.ModOreDicts.INGOT_UNSTABLE}));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHModItems.excavatorUnstable), new Object[]{" x ", " y ", " y ", 'x', SHModItems.excavatorHeadUnstable, 'y', "stickWood"}));
        }
    }
}
