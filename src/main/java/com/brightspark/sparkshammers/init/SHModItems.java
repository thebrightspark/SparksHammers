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
    public static ItemPickaxe hammerManasteel, hammerTerrasteel, hammerElementium;
    //Random Things
    public static ItemPickaxe hammerSpectre;
    //Extra Utilities
    public static Item itemHeadUnstable;
    public static ItemPickaxe hammerUnstable;
    //EnderIO
    public static ItemPickaxe hammerDarksteel;
    //Misc
    public static ItemPickaxe hammerBronze;

    public static void init()
    {
        //Botania
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_MANASTEEL))
        {
            hammerManasteel = new ItemHammer(Names.ModItems.HAMMER_MANASTEEL, ModHammerMaterials.HAMMER_MANASTEEL, Names.Mods.BOTANIA);
            GameRegistry.registerItem(hammerManasteel, Names.ModItems.HAMMER_MANASTEEL);
        }
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_TERRASTEEL))
        {
            hammerTerrasteel = new ItemHammer(Names.ModItems.HAMMER_TERRASTEEL, ModHammerMaterials.HAMMER_TERRASTEEL, Names.Mods.BOTANIA);
            GameRegistry.registerItem(hammerTerrasteel, Names.ModItems.HAMMER_TERRASTEEL);
        }
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_ELEMENTIUM))
        {
            hammerElementium = new ItemHammer(Names.ModItems.HAMMER_ELEMENTIUM, ModHammerMaterials.HAMMER_ELEMENTIUM, Names.Mods.BOTANIA);
            GameRegistry.registerItem(hammerElementium, Names.ModItems.HAMMER_ELEMENTIUM);
        }

        //Random Things
        if(LoaderHelper.isModLoaded(Names.Mods.RANDOM_THINGS))
        {
            hammerSpectre = new ItemHammer(Names.ModItems.HAMMER_SPECTRE, ModHammerMaterials.HAMMER_SPECTRE, Names.Mods.RANDOM_THINGS);
            GameRegistry.registerItem(hammerSpectre, Names.ModItems.HAMMER_SPECTRE);
        }

        //Extra Utilities
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_UNSTABLE))
        {
            itemHeadUnstable = new ItemResource(Names.ModItems.HEAD_UNSTABLE, Names.Mods.EXTRA_UTILITIES);
            hammerUnstable = new ItemHammerInfinite(Names.ModItems.HAMMER_UNSTABLE, HammerMaterials.HAMMER_DIAMOND, Names.Mods.EXTRA_UTILITIES);
            GameRegistry.registerItem(itemHeadUnstable, Names.ModItems.HEAD_UNSTABLE);
            GameRegistry.registerItem(hammerUnstable, Names.ModItems.HAMMER_UNSTABLE);
        }

        //EnderIO
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_DARKSTEEL))
        {
            hammerDarksteel = new ItemHammer(Names.ModItems.HAMMER_DARKSTEEL, ModHammerMaterials.HAMMER_DARKSTEEL, Names.Mods.ENDERIO);
            GameRegistry.registerItem(hammerDarksteel, Names.ModItems.HAMMER_DARKSTEEL);
        }

        //Misc
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_BRONZE))
        {
            hammerBronze = new ItemHammer(Names.ModItems.HAMMER_BRONZE, ModHammerMaterials.HAMMER_BRONZE, Names.Mods.MISC);
            GameRegistry.registerItem(hammerBronze, Names.ModItems.HAMMER_BRONZE);
        }
    }
}
