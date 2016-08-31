package com.brightspark.sparkshammers.init;

import com.brightspark.sparkshammers.item.ItemAOE;
import com.brightspark.sparkshammers.reference.ModMaterials;
import com.brightspark.sparkshammers.reference.Names;
import com.brightspark.sparkshammers.util.ClientUtils;
import com.brightspark.sparkshammers.util.LoaderHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SHModItems
{
    //Botania
    public static ItemAOE hammerManasteel, hammerTerrasteel, hammerElementium,
            excavatorManasteel, excavatorTerrasteel, excavatorElementium;
    //Extra Utilities
    /*
    public static ItemResource hammerHeadUnstable, excavatorHeadUnstable;
    public static ItemAOE hammerUnstable, excavatorUnstable;
    */
    //EnderIO
    //public static ItemAOEDarkSteel hammerDarksteel, excavatorDarksteel;
    //MobHunter
    public static ItemAOE hammerMachalite, hammerDragonite, hammerGossamite,
            excavatorMachalite, excavatorDragonite, excavatorGossamite;
    //Misc
    public static ItemAOE hammerBronze, excavatorBronze;

    public static void regItems()
    {
        //Botania
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_MANASTEEL))
        {
            hammerManasteel = new ItemAOE(Names.ModItems.HAMMER_MANASTEEL, ModMaterials.MANASTEEL);
            excavatorManasteel = new ItemAOE(Names.ModItems.EXCAVATOR_MANASTEEL, ModMaterials.MANASTEEL, true);
            GameRegistry.register(hammerManasteel);
            GameRegistry.register(excavatorManasteel);
        }
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_TERRASTEEL))
        {
            hammerTerrasteel = new ItemAOE(Names.ModItems.HAMMER_TERRASTEEL, ModMaterials.TERRASTEEL);
            excavatorTerrasteel = new ItemAOE(Names.ModItems.EXCAVATOR_TERRASTEEL, ModMaterials.TERRASTEEL, true);
            GameRegistry.register(hammerTerrasteel);
            GameRegistry.register(excavatorTerrasteel);
        }
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_ELEMENTIUM))
        {
            hammerElementium = new ItemAOE(Names.ModItems.HAMMER_ELEMENTIUM, ModMaterials.ELEMENTIUM);
            excavatorElementium = new ItemAOE(Names.ModItems.EXCAVATOR_ELEMENTIUM, ModMaterials.ELEMENTIUM, true);
            GameRegistry.register(hammerElementium);
            GameRegistry.register(excavatorElementium);
        }

        //Extra Utilities
        /*
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_UNSTABLE))
        {
            hammerHeadUnstable = new ItemResource(Names.ModItems.HAMMER_HEAD_UNSTABLE);
            hammerUnstable = new ItemAOE(Names.ModItems.HAMMER_UNSTABLE, Materials.HAMMER_DIAMOND);
            excavatorHeadUnstable = new ItemResource(Names.ModItems.EXCAVATOR_HEAD_UNSTABLE);
            excavatorUnstable = new ItemAOE(Names.ModItems.EXCAVATOR_UNSTABLE, Materials.HAMMER_DIAMOND, true);
            GameRegistry.registerItem(hammerHeadUnstable, Names.ModItems.HAMMER_HEAD_UNSTABLE);
            GameRegistry.registerItem(hammerUnstable, Names.ModItems.HAMMER_UNSTABLE);
            GameRegistry.registerItem(excavatorHeadUnstable, Names.ModItems.EXCAVATOR_HEAD_UNSTABLE);
            GameRegistry.registerItem(excavatorUnstable, Names.ModItems.EXCAVATOR_UNSTABLE);
        }
        */

        //EnderIO
        /*
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_DARKSTEEL))
        {
            hammerDarksteel = new ItemAOEDarkSteel();
            excavatorDarksteel = new ItemAOEDarkSteel(true);
            GameRegistry.register(hammerDarksteel);
            GameRegistry.register(excavatorDarksteel);
        }
        */

        //MobHunter
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_MACHALITE))
        {
            hammerMachalite = new ItemAOE(Names.ModItems.HAMMER_MACHALITE, ModMaterials.MACHALITE);
            excavatorMachalite = new ItemAOE(Names.ModItems.EXCAVATOR_MACHALITE, ModMaterials.MACHALITE, true);
            GameRegistry.register(hammerMachalite);
            GameRegistry.register(excavatorMachalite);
        }
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_DRAGONITE))
        {
            hammerDragonite = new ItemAOE(Names.ModItems.HAMMER_DRAGONITE, ModMaterials.DRAGONITE);
            excavatorDragonite = new ItemAOE(Names.ModItems.EXCAVATOR_DRAGONITE, ModMaterials.DRAGONITE, true);
            GameRegistry.register(hammerDragonite);
            GameRegistry.register(excavatorDragonite);
        }
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_GOSSAMITE))
        {
            hammerGossamite = new ItemAOE(Names.ModItems.HAMMER_GOSSAMITE, ModMaterials.GOSSAMITE);
            excavatorGossamite = new ItemAOE(Names.ModItems.EXCAVATOR_GOSSAMITE, ModMaterials.GOSSAMITE, true);
            GameRegistry.register(hammerGossamite);
            GameRegistry.register(excavatorGossamite);
        }

        //Misc
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_BRONZE))
        {
            hammerBronze = new ItemAOE(Names.ModItems.HAMMER_BRONZE, ModMaterials.BRONZE);
            excavatorBronze = new ItemAOE(Names.ModItems.EXCAVATOR_BRONZE, ModMaterials.BRONZE, true);
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
        /*
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_DARKSTEEL))
        {
            ClientUtils.regModel(hammerDarksteel);
            ClientUtils.regModel(excavatorDarksteel);
        }
        */

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
