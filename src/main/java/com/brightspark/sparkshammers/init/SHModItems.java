package com.brightspark.sparkshammers.init;

import com.brightspark.sparkshammers.item.ItemExcavator;
import com.brightspark.sparkshammers.item.ItemHammer;
import com.brightspark.sparkshammers.reference.ModMaterials;
import com.brightspark.sparkshammers.reference.Names;
import com.brightspark.sparkshammers.util.Common;
import com.brightspark.sparkshammers.util.LoaderHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SHModItems
{
    //Botania
    public static ItemHammer hammerManasteel, hammerTerrasteel, hammerElementium;
    public static ItemExcavator excavatorManasteel, excavatorTerrasteel, excavatorElementium;
    //Extra Utilities
    /*
    public static ItemResource hammerHeadUnstable, excavatorHeadUnstable;
    public static ItemHammer hammerUnstable;
    public static ItemExcavator excavatorUnstable;
    */
    //EnderIO
    public static ItemHammer hammerDarksteel;
    public static ItemExcavator excavatorDarksteel;
    //Misc
    public static ItemHammer hammerBronze;
    public static ItemExcavator excavatorBronze;

    public static void regItems()
    {
        //Botania
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_MANASTEEL))
        {
            hammerManasteel = new ItemHammer(Names.ModItems.HAMMER_MANASTEEL, ModMaterials.HAMMER_MANASTEEL);
            excavatorManasteel = new ItemExcavator(Names.ModItems.EXCAVATOR_MANASTEEL, ModMaterials.HAMMER_MANASTEEL);
            GameRegistry.registerItem(hammerManasteel, Names.ModItems.HAMMER_MANASTEEL);
            GameRegistry.registerItem(excavatorManasteel, Names.ModItems.EXCAVATOR_MANASTEEL);
        }
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_TERRASTEEL))
        {
            hammerTerrasteel = new ItemHammer(Names.ModItems.HAMMER_TERRASTEEL, ModMaterials.HAMMER_TERRASTEEL);
            excavatorTerrasteel = new ItemExcavator(Names.ModItems.EXCAVATOR_TERRASTEEL, ModMaterials.HAMMER_TERRASTEEL);
            GameRegistry.registerItem(hammerTerrasteel, Names.ModItems.HAMMER_TERRASTEEL);
            GameRegistry.registerItem(excavatorTerrasteel, Names.ModItems.EXCAVATOR_TERRASTEEL);
        }
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_ELEMENTIUM))
        {
            hammerElementium = new ItemHammer(Names.ModItems.HAMMER_ELEMENTIUM, ModMaterials.HAMMER_ELEMENTIUM);
            excavatorElementium = new ItemExcavator(Names.ModItems.EXCAVATOR_ELEMENTIUM, ModMaterials.HAMMER_ELEMENTIUM);
            GameRegistry.registerItem(hammerElementium, Names.ModItems.HAMMER_ELEMENTIUM);
            GameRegistry.registerItem(excavatorElementium, Names.ModItems.EXCAVATOR_ELEMENTIUM);
        }

        //Extra Utilities
        /*
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_UNSTABLE))
        {
            hammerHeadUnstable = new ItemResource(Names.ModItems.HAMMER_HEAD_UNSTABLE);
            hammerUnstable = new ItemHammer(Names.ModItems.HAMMER_UNSTABLE, Materials.HAMMER_DIAMOND, true);
            excavatorHeadUnstable = new ItemResource(Names.ModItems.EXCAVATOR_HEAD_UNSTABLE);
            excavatorUnstable = new ItemExcavator(Names.ModItems.EXCAVATOR_UNSTABLE, Materials.HAMMER_DIAMOND, true);
            GameRegistry.registerItem(hammerHeadUnstable, Names.ModItems.HAMMER_HEAD_UNSTABLE);
            GameRegistry.registerItem(hammerUnstable, Names.ModItems.HAMMER_UNSTABLE);
            GameRegistry.registerItem(excavatorHeadUnstable, Names.ModItems.EXCAVATOR_HEAD_UNSTABLE);
            GameRegistry.registerItem(excavatorUnstable, Names.ModItems.EXCAVATOR_UNSTABLE);
        }
        */

        //EnderIO
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_DARKSTEEL))
        {
            hammerDarksteel = new ItemHammer(Names.ModItems.HAMMER_DARKSTEEL, ModMaterials.HAMMER_DARKSTEEL);
            excavatorDarksteel = new ItemExcavator(Names.ModItems.EXCAVATOR_DARKSTEEL, ModMaterials.HAMMER_DARKSTEEL);
            GameRegistry.registerItem(hammerDarksteel, Names.ModItems.HAMMER_DARKSTEEL);
            GameRegistry.registerItem(excavatorDarksteel, Names.ModItems.EXCAVATOR_DARKSTEEL);
        }

        //Misc
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_BRONZE))
        {
            hammerBronze = new ItemHammer(Names.ModItems.HAMMER_BRONZE, ModMaterials.HAMMER_BRONZE);
            excavatorBronze = new ItemExcavator(Names.ModItems.EXCAVATOR_BRONZE, ModMaterials.HAMMER_BRONZE);
            GameRegistry.registerItem(hammerBronze, Names.ModItems.HAMMER_BRONZE);
            GameRegistry.registerItem(excavatorBronze, Names.ModItems.EXCAVATOR_BRONZE);
        }
    }

    public static void regModels()
    {
        //Botania
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_MANASTEEL))
        {
            Common.regModel(SHModItems.hammerManasteel);
            Common.regModel(SHModItems.excavatorManasteel);
        }
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_TERRASTEEL))
        {
            Common.regModel(SHModItems.hammerTerrasteel);
            Common.regModel(SHModItems.excavatorTerrasteel);
        }
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_ELEMENTIUM))
        {
            Common.regModel(SHModItems.hammerElementium);
            Common.regModel(SHModItems.excavatorElementium);
        }
        //Extra Utilities
        /*
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_UNSTABLE))
        {
            Common.regModel(SHModItems.hammerHeadUnstable);
            Common.regModel(SHModItems.hammerUnstable);
            Common.regModel(SHModItems.excavatorHeadUnstable);
            Common.regModel(SHModItems.excavatorUnstable);
        }
        */
        //EnderIO
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_DARKSTEEL))
        {
            Common.regModel(SHModItems.hammerDarksteel);
            Common.regModel(SHModItems.excavatorDarksteel);
        }
        //Misc
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_BRONZE))
        {
            Common.regModel(SHModItems.hammerBronze);
            Common.regModel(SHModItems.excavatorBronze);
        }
    }
}
