package com.brightspark.sparkshammers.init;

import com.brightspark.sparkshammers.item.ItemHammer;
import com.brightspark.sparkshammers.item.ItemHammerInfinite;
import com.brightspark.sparkshammers.item.ItemResource;
import com.brightspark.sparkshammers.reference.HammerMaterials;
import com.brightspark.sparkshammers.reference.ModHammerMaterials;
import com.brightspark.sparkshammers.reference.Names;
import com.brightspark.sparkshammers.util.LoaderHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;

public class SHModItems
{
    //Botania
    public static Item itemHeadManasteel, itemHeadTerrasteel, itemHeadElementium;
    public static ItemPickaxe hammerManasteel, hammerTerrasteel, hammerElementium;
    //Random Things
    public static Item itemHeadSpectre;
    public static ItemPickaxe hammerSpectre;
    //Extra Utilities
    public static Item itemHeadUnstable;
    public static ItemPickaxe hammerUnstable;
    //EnderIO
    public static Item itemHeadDarksteel;
    public static ItemPickaxe hammerDarksteel;
    //IC2
    public static Item itemHeadBronze;
    public static ItemPickaxe hammerBronze;

    public static void init()
    {
        //Botania
        if(LoaderHelper.isModLoaded(Names.Mods.BOTANIA))
        {
            itemHeadManasteel = new ItemResource(Names.ModItems.HEAD_MANASTEEL, Names.Mods.BOTANIA);
            itemHeadTerrasteel = new ItemResource(Names.ModItems.HEAD_TERRASTEEL, Names.Mods.BOTANIA);
            itemHeadElementium = new ItemResource(Names.ModItems.HEAD_ELEMENTIUM, Names.Mods.BOTANIA);
            hammerManasteel = new ItemHammer(Names.ModItems.HAMMER_MANASTEEL, ModHammerMaterials.HAMMER_MANASTEEL, Names.Mods.BOTANIA);
            hammerTerrasteel = new ItemHammer(Names.ModItems.HAMMER_TERRASTEEL, ModHammerMaterials.HAMMER_TERRASTEEL, Names.Mods.BOTANIA);
            hammerElementium = new ItemHammer(Names.ModItems.HAMMER_ELEMENTIUM, ModHammerMaterials.HAMMER_ELEMENTIUM, Names.Mods.BOTANIA);
            GameRegistry.registerItem(itemHeadManasteel, Names.ModItems.HEAD_MANASTEEL);
            GameRegistry.registerItem(itemHeadTerrasteel, Names.ModItems.HEAD_TERRASTEEL);
            GameRegistry.registerItem(itemHeadElementium, Names.ModItems.HEAD_ELEMENTIUM);
            GameRegistry.registerItem(hammerManasteel, Names.ModItems.HAMMER_MANASTEEL);
            GameRegistry.registerItem(hammerTerrasteel, Names.ModItems.HAMMER_TERRASTEEL);
            GameRegistry.registerItem(hammerElementium, Names.ModItems.HAMMER_ELEMENTIUM);
        }
        //Random Things
        if(LoaderHelper.isModLoaded(Names.Mods.RANDOM_THINGS))
        {
            itemHeadSpectre = new ItemResource(Names.ModItems.HEAD_SPECTRE, Names.Mods.RANDOM_THINGS);
            hammerSpectre = new ItemHammer(Names.ModItems.HAMMER_SPECTRE, ModHammerMaterials.HAMMER_SPECTRE, Names.Mods.RANDOM_THINGS);
            GameRegistry.registerItem(itemHeadSpectre, Names.ModItems.HEAD_SPECTRE);
            GameRegistry.registerItem(hammerSpectre, Names.ModItems.HAMMER_SPECTRE);
        }
        //Extra Utilities
        if(LoaderHelper.isModLoaded(Names.Mods.EXTRA_UTILITIES))
        {
            itemHeadUnstable = new ItemResource(Names.ModItems.HEAD_UNSTABLE, Names.Mods.EXTRA_UTILITIES);
            hammerUnstable = new ItemHammerInfinite(Names.ModItems.HAMMER_UNSTABLE, HammerMaterials.HAMMER_DIAMOND, Names.Mods.EXTRA_UTILITIES);
            GameRegistry.registerItem(itemHeadUnstable, Names.ModItems.HEAD_UNSTABLE);
            GameRegistry.registerItem(hammerUnstable, Names.ModItems.HAMMER_UNSTABLE);
        }
        //EnderIO
        if(LoaderHelper.isModLoaded(Names.Mods.ENDERIO))
        {
            itemHeadDarksteel = new ItemResource(Names.ModItems.HEAD_DARKSTEEL, Names.Mods.ENDERIO);
            hammerDarksteel = new ItemHammer(Names.ModItems.HAMMER_DARKSTEEL, ModHammerMaterials.HAMMER_DARKSTEEL, Names.Mods.ENDERIO);
            GameRegistry.registerItem(itemHeadDarksteel, Names.ModItems.HEAD_DARKSTEEL);
            GameRegistry.registerItem(hammerDarksteel, Names.ModItems.HAMMER_DARKSTEEL);
        }
        //IC2
        if(LoaderHelper.isModLoaded(Names.Mods.IC2))
        {
            itemHeadBronze = new ItemResource(Names.ModItems.HEAD_BRONZE, Names.Mods.IC2);
            hammerBronze = new ItemHammer(Names.ModItems.HAMMER_BRONZE, ModHammerMaterials.HAMMER_BRONZE, Names.Mods.IC2);
            GameRegistry.registerItem(itemHeadBronze, Names.ModItems.HEAD_BRONZE);
            GameRegistry.registerItem(hammerBronze, Names.ModItems.HAMMER_BRONZE);
        }
    }
}
