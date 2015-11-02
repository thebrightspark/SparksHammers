package com.brightspark.sparkshammers.init;

import com.brightspark.sparkshammers.reference.Names;
import com.brightspark.sparkshammers.util.LoaderHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class SHModRecipes
{
    public static void init()
    {
        //This displays all item IDs:
        /*
        Iterator items = Item.itemRegistry.getKeys().iterator();
        while(items.hasNext())
            LogHelper.info(items.next().toString());
        */

        //Botania
        if(LoaderHelper.isModLoaded(Names.Mods.BOTANIA))
        {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHModItems.itemHeadManasteel), new Object[]{"xxx", "xxx", "   ", 'x', Names.ModOreDicts.BOTANIA_INGOT_MANASTEEL}));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHModItems.itemHeadTerrasteel), new Object[]{"xxx", "xxx", "   ", 'x', Names.ModOreDicts.BOTANIA_INGOT_TERRASTEEL}));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHModItems.itemHeadElementium), new Object[]{"xxx", "xxx", "   ", 'x', Names.ModOreDicts.BOTANIA_INGOT_ELEMENTIUM}));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHModItems.hammerManasteel), new Object[]{" x ", " y ", " y ", 'x', SHModItems.itemHeadManasteel, 'y', "stickWood"}));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHModItems.hammerTerrasteel), new Object[]{" x ", " y ", " y ", 'x', SHModItems.itemHeadTerrasteel, 'y', "stickWood"}));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHModItems.hammerElementium), new Object[]{" x ", " y ", " y ", 'x', SHModItems.itemHeadElementium, 'y', "stickWood"}));
        }
        //Random Things
        if(LoaderHelper.isModLoaded(Names.Mods.RANDOM_THINGS))
        {
            //Gets the spectre iron item for the recipes
            Item iron = (Item) Item.itemRegistry.getObject(Names.ModItemIDs.RANDOMTHINGS_ITEM_INGREDIENT);
            ItemStack itemSpectreIron = (new ItemStack(iron, 0, 4));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHModItems.itemHeadSpectre), new Object[]{"xxx", "xxx", "   ", 'x', itemSpectreIron}));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHModItems.hammerSpectre), new Object[]{" x ", " y ", " y ", 'x', SHModItems.itemHeadSpectre, 'y', "stickWood"}));
        }
        //Extra Utilities
        if(LoaderHelper.isModLoaded(Names.Mods.EXTRA_UTILITIES))
        {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHModItems.itemHeadUnstable), new Object[]{"xxx", "xxx", "   ", 'x', Names.ModOreDicts.EXTRATUTILITIES_INGOT_UNSTABLE}));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHModItems.hammerUnstable), new Object[]{" x ", " y ", " y ", 'x', SHModItems.itemHeadUnstable, 'y', "stickWood"}));
        }
        //Ender IO
        if(LoaderHelper.isModLoaded(Names.Mods.ENDERIO))
        {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHModItems.itemHeadDarksteel), new Object[]{"xxx", "xxx", "   ", 'x', Names.ModOreDicts.ENDERIO_INGOT_DARKSTEEL}));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHModItems.hammerDarksteel), new Object[]{" x ", " y ", " y ", 'x', SHModItems.itemHeadDarksteel, 'y', "stickWood"}));
        }
        //IC2
        if(LoaderHelper.isModLoaded(Names.Mods.IC2))
        {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHModItems.itemHeadBronze), new Object[]{"xxx", "xxx", "   ", 'x', Names.ModOreDicts.IC2_INGOT_BRONZE}));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SHModItems.hammerBronze), new Object[]{" x ", " y ", " y ", 'x', SHModItems.itemHeadBronze, 'y', "stickWood"}));
        }
    }
}
