package com.brightspark.sparkshammers.init;

import com.brightspark.sparkshammers.item.ItemExcavator;
import com.brightspark.sparkshammers.item.ItemHammer;
import com.brightspark.sparkshammers.item.ItemResource;
import com.brightspark.sparkshammers.reference.Materials;
import com.brightspark.sparkshammers.reference.ModMaterials;
import com.brightspark.sparkshammers.reference.Names;
import com.brightspark.sparkshammers.util.LoaderHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class SHModItems
{
    //Botania
    public static ItemHammer hammerManasteel, hammerTerrasteel, hammerElementium;
    public static ItemExcavator excavatorManasteel, excavatorTerrasteel, excavatorElementium;
    //Random Things
    public static ItemHammer hammerSpectre;
    public static ItemExcavator excavatorSpectre;
    //Extra Utilities
    public static Item hammerHeadUnstable, excavatorHeadUnstable;
    public static ItemHammer hammerUnstable;
    public static ItemExcavator excavatorUnstable;
    //EnderIO
    public static ItemHammer hammerDarksteel;
    public static ItemExcavator excavatorDarksteel;
    //Misc
    public static ItemHammer hammerBronze;
    public static ItemExcavator excavatorBronze;

    public static void init()
    {
        //Botania
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_MANASTEEL))
        {
            hammerManasteel = new ItemHammer(Names.ModItems.HAMMER_MANASTEEL, ModMaterials.HAMMER_MANASTEEL, Names.Mods.BOTANIA);
            excavatorManasteel = new ItemExcavator(Names.ModItems.EXCAVATOR_MANASTEEL, ModMaterials.HAMMER_MANASTEEL, Names.Mods.BOTANIA);
            GameRegistry.registerItem(hammerManasteel, Names.ModItems.HAMMER_MANASTEEL);
            GameRegistry.registerItem(excavatorManasteel, Names.ModItems.EXCAVATOR_MANASTEEL);
        }
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_TERRASTEEL))
        {
            hammerTerrasteel = new ItemHammer(Names.ModItems.HAMMER_TERRASTEEL, ModMaterials.HAMMER_TERRASTEEL, Names.Mods.BOTANIA);
            excavatorTerrasteel = new ItemExcavator(Names.ModItems.EXCAVATOR_TERRASTEEL, ModMaterials.HAMMER_TERRASTEEL, Names.Mods.BOTANIA);
            GameRegistry.registerItem(hammerTerrasteel, Names.ModItems.HAMMER_TERRASTEEL);
            GameRegistry.registerItem(excavatorTerrasteel, Names.ModItems.EXCAVATOR_TERRASTEEL);
        }
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_ELEMENTIUM))
        {
            hammerElementium = new ItemHammer(Names.ModItems.HAMMER_ELEMENTIUM, ModMaterials.HAMMER_ELEMENTIUM, Names.Mods.BOTANIA);
            excavatorElementium = new ItemExcavator(Names.ModItems.EXCAVATOR_ELEMENTIUM, ModMaterials.HAMMER_ELEMENTIUM, Names.Mods.BOTANIA);
            GameRegistry.registerItem(hammerElementium, Names.ModItems.HAMMER_ELEMENTIUM);
            GameRegistry.registerItem(excavatorElementium, Names.ModItems.EXCAVATOR_ELEMENTIUM);
        }

        //Random Things
        if(LoaderHelper.isModLoaded(Names.Mods.RANDOM_THINGS))
        {
            hammerSpectre = new ItemHammer(Names.ModItems.HAMMER_SPECTRE, ModMaterials.HAMMER_SPECTRE, Names.Mods.RANDOM_THINGS);
            excavatorSpectre = new ItemExcavator(Names.ModItems.EXCAVATOR_SPECTRE, ModMaterials.HAMMER_SPECTRE, Names.Mods.RANDOM_THINGS);
            GameRegistry.registerItem(hammerSpectre, Names.ModItems.HAMMER_SPECTRE);
            GameRegistry.registerItem(excavatorSpectre, Names.ModItems.EXCAVATOR_SPECTRE);
        }

        //Extra Utilities
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_UNSTABLE))
        {
            hammerHeadUnstable = new ItemResource(Names.ModItems.HAMMER_HEAD_UNSTABLE, Names.Mods.EXTRA_UTILITIES);
            hammerUnstable = new ItemHammer(Names.ModItems.HAMMER_UNSTABLE, Materials.HAMMER_DIAMOND, Names.Mods.EXTRA_UTILITIES, true);
            excavatorHeadUnstable = new ItemResource(Names.ModItems.EXCAVATOR_HEAD_UNSTABLE, Names.Mods.EXTRA_UTILITIES);
            excavatorUnstable = new ItemExcavator(Names.ModItems.EXCAVATOR_UNSTABLE, Materials.HAMMER_DIAMOND, Names.Mods.EXTRA_UTILITIES, true);
            GameRegistry.registerItem(hammerHeadUnstable, Names.ModItems.HAMMER_HEAD_UNSTABLE);
            GameRegistry.registerItem(hammerUnstable, Names.ModItems.HAMMER_UNSTABLE);
            GameRegistry.registerItem(excavatorHeadUnstable, Names.ModItems.EXCAVATOR_HEAD_UNSTABLE);
            GameRegistry.registerItem(excavatorUnstable, Names.ModItems.EXCAVATOR_UNSTABLE);
        }

        //EnderIO
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_DARKSTEEL))
        {
            hammerDarksteel = new ItemHammer(Names.ModItems.HAMMER_DARKSTEEL, ModMaterials.HAMMER_DARKSTEEL, Names.Mods.ENDERIO);
            excavatorDarksteel = new ItemExcavator(Names.ModItems.EXCAVATOR_DARKSTEEL, ModMaterials.HAMMER_DARKSTEEL, Names.Mods.ENDERIO);
            GameRegistry.registerItem(hammerDarksteel, Names.ModItems.HAMMER_DARKSTEEL);
            GameRegistry.registerItem(excavatorDarksteel, Names.ModItems.EXCAVATOR_DARKSTEEL);
        }

        //Misc
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_BRONZE))
        {
            hammerBronze = new ItemHammer(Names.ModItems.HAMMER_BRONZE, ModMaterials.HAMMER_BRONZE, Names.Mods.MISC);
            excavatorBronze = new ItemExcavator(Names.ModItems.EXCAVATOR_BRONZE, ModMaterials.HAMMER_BRONZE, Names.Mods.MISC);
            GameRegistry.registerItem(hammerBronze, Names.ModItems.HAMMER_BRONZE);
            GameRegistry.registerItem(excavatorBronze, Names.ModItems.EXCAVATOR_BRONZE);
        }
    }
}
