package com.brightspark.sparkshammers.init;

import com.brightspark.sparkshammers.reference.Names;
import com.brightspark.sparkshammers.util.LoaderHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class SHRecipies
{
    public static void init()
    {
        //Hammer heads
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHItems.itemHeadWood), new Object[]{"xxx", "xxx", "   ", 'x', "plankWood"}));
        if(LoaderHelper.isModLoaded(Names.Mods.EXTRA_UTILITIES))
            //Make stone head recipe made of compressed cobble when Extra Utilities is installed
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHItems.itemHeadStone), new Object[]{"xxx", "xxx", "   ", 'x', "compressedCobblestone1x"}));
        else
            GameRegistry.addShapedRecipe(new ItemStack(SHItems.itemHeadStone), new Object[] {"xxx", "xxx", "   ", 'x', Blocks.cobblestone});
        GameRegistry.addShapedRecipe(new ItemStack(SHItems.itemHeadIron), new Object[] {"xxx", "xxx", "   ", 'x', Items.iron_ingot});
        GameRegistry.addShapedRecipe(new ItemStack(SHItems.itemHeadGold), new Object[] {"xxx", "xxx", "   ", 'x', Items.gold_ingot});
        GameRegistry.addShapedRecipe(new ItemStack(SHItems.itemHeadDiamond), new Object[]{"xxx", "xxx", "   ", 'x', Items.diamond});

        //Hammers
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHItems.hammerWood), new Object[] {" x ", " y ", " y ", 'x', SHItems.itemHeadWood, 'y', "stickWood"}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHItems.hammerStone), new Object[] {" x ", " y ", " y ", 'x', SHItems.itemHeadStone, 'y', "stickWood"}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHItems.hammerIron), new Object[] {" x ", " y ", " y ", 'x', SHItems.itemHeadIron, 'y', "stickWood"}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHItems.hammerGold), new Object[] {" x ", " y ", " y ", 'x', SHItems.itemHeadGold, 'y', "stickWood"}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHItems.hammerDiamond), new Object[] {" x ", " y ", " y ", 'x', SHItems.itemHeadDiamond, 'y', "stickWood"}));
    }
}
