package com.brightspark.sparkshammers.init;

import com.brightspark.sparkshammers.item.ItemExcavator;
import com.brightspark.sparkshammers.item.ItemHammer;
import com.brightspark.sparkshammers.item.ItemHammerDarkSteel;
import com.brightspark.sparkshammers.reference.ModMaterials;
import com.brightspark.sparkshammers.reference.Names;
import com.brightspark.sparkshammers.util.ClientUtils;
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
    //MobHunter
    public static ItemHammer hammerMachalite, hammerDragonite, hammerGossamite;
    public static ItemExcavator excavatorMachalite, excavatorDragonite, excavatorGossamite;
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
            GameRegistry.register(hammerManasteel);
            GameRegistry.register(excavatorManasteel);
        }
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_TERRASTEEL))
        {
            hammerTerrasteel = new ItemHammer(Names.ModItems.HAMMER_TERRASTEEL, ModMaterials.HAMMER_TERRASTEEL);
            excavatorTerrasteel = new ItemExcavator(Names.ModItems.EXCAVATOR_TERRASTEEL, ModMaterials.HAMMER_TERRASTEEL);
            GameRegistry.register(hammerTerrasteel);
            GameRegistry.register(excavatorTerrasteel);
        }
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_ELEMENTIUM))
        {
            hammerElementium = new ItemHammer(Names.ModItems.HAMMER_ELEMENTIUM, ModMaterials.HAMMER_ELEMENTIUM);
            excavatorElementium = new ItemExcavator(Names.ModItems.EXCAVATOR_ELEMENTIUM, ModMaterials.HAMMER_ELEMENTIUM);
            GameRegistry.register(hammerElementium);
            GameRegistry.register(excavatorElementium);
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
            hammerDarksteel = new ItemHammerDarkSteel();
            excavatorDarksteel = new ItemExcavator(Names.ModItems.EXCAVATOR_DARKSTEEL, ModMaterials.HAMMER_DARKSTEEL);
            GameRegistry.register(hammerDarksteel);
            GameRegistry.register(excavatorDarksteel);
        }

        //MobHunter
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_MACHALITE))
        {
            hammerMachalite = new ItemHammer(Names.ModItems.HAMMER_MACHALITE, ModMaterials.HAMMER_MACHALITE);
            excavatorMachalite = new ItemExcavator(Names.ModItems.EXCAVATOR_MACHALITE, ModMaterials.HAMMER_MACHALITE);
            GameRegistry.register(hammerMachalite);
            GameRegistry.register(excavatorMachalite);
        }
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_DRAGONITE))
        {
            hammerDragonite = new ItemHammer(Names.ModItems.HAMMER_DRAGONITE, ModMaterials.HAMMER_DRAGONITE);
            excavatorDragonite = new ItemExcavator(Names.ModItems.EXCAVATOR_DRAGONITE, ModMaterials.HAMMER_DRAGONITE);
            GameRegistry.register(hammerDragonite);
            GameRegistry.register(excavatorDragonite);
        }
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_GOSSAMITE))
        {
            hammerGossamite = new ItemHammer(Names.ModItems.HAMMER_GOSSAMITE, ModMaterials.HAMMER_GOSSAMITE);
            excavatorGossamite = new ItemExcavator(Names.ModItems.EXCAVATOR_GOSSAMITE, ModMaterials.HAMMER_GOSSAMITE);
            GameRegistry.register(hammerGossamite);
            GameRegistry.register(excavatorGossamite);
        }

        //Misc
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_BRONZE))
        {
            hammerBronze = new ItemHammer(Names.ModItems.HAMMER_BRONZE, ModMaterials.HAMMER_BRONZE);
            excavatorBronze = new ItemExcavator(Names.ModItems.EXCAVATOR_BRONZE, ModMaterials.HAMMER_BRONZE);
            GameRegistry.register(hammerBronze);
            GameRegistry.register(excavatorBronze);
        }
    }

    public static void regModels()
    {
        //Botania
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_MANASTEEL))
        {
            ClientUtils.regModel(hammerManasteel);
            ClientUtils.regModel(excavatorManasteel);
        }
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_TERRASTEEL))
        {
            ClientUtils.regModel(hammerTerrasteel);
            ClientUtils.regModel(excavatorTerrasteel);
        }
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_ELEMENTIUM))
        {
            ClientUtils.regModel(hammerElementium);
            ClientUtils.regModel(excavatorElementium);
        }

        //Extra Utilities
        /*
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_UNSTABLE))
        {
            Common.regModel(hammerHeadUnstable);
            Common.regModel(hammerUnstable);
            Common.regModel(excavatorHeadUnstable);
            Common.regModel(excavatorUnstable);
        }
        */

        //EnderIO
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_DARKSTEEL))
        {
            ClientUtils.regModel(hammerDarksteel);
            ClientUtils.regModel(excavatorDarksteel);
        }

        //MobHunter
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_MACHALITE))
        {
            ClientUtils.regModel(hammerMachalite);
            ClientUtils.regModel(excavatorMachalite);
        }
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_DRAGONITE))
        {
            ClientUtils.regModel(hammerDragonite);
            ClientUtils.regModel(excavatorDragonite);
        }
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_GOSSAMITE))
        {
            ClientUtils.regModel(hammerGossamite);
            ClientUtils.regModel(excavatorGossamite);
        }

        //Misc
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_BRONZE))
        {
            ClientUtils.regModel(hammerBronze);
            ClientUtils.regModel(excavatorBronze);
        }
    }
}
