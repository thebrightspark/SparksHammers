package com.brightspark.sparkshammers.init;

import com.brightspark.sparkshammers.reference.Names;
import com.brightspark.sparkshammers.util.LoaderHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class SHRecipes
{
    public static void init()
    {
        //Hammer Crafting Table
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHBlocks.blockHammerCraft), new Object[]{"scs", "chc", "scs", 's', "stone", 'c', Blocks.crafting_table, 'h', SHItems.hammerWood}));

        //Wooden Hammer
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHItems.itemHeadWood), new Object[]{"xxx", "xxx", "   ", 'x', "logWood"}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHItems.hammerWood), new Object[]{" x ", " s ", " s ", 'x', SHItems.itemHeadWood, 's', "plankWood"}));

        if(LoaderHelper.isModLoaded(Names.Mods.EXTRA_UTILITIES))
        {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHModItems.itemHeadUnstable), new Object[]{"xxx", "xxx", "   ", 'x', Names.ModOreDicts.INGOT_UNSTABLE}));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHModItems.hammerUnstable), new Object[]{" x ", " y ", " y ", 'x', SHModItems.itemHeadUnstable, 'y', "stickWood"}));
        }
    }
}
