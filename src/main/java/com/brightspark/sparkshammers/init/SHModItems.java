package com.brightspark.sparkshammers.init;

import com.brightspark.sparkshammers.item.ItemAOE;
import com.brightspark.sparkshammers.reference.Names;
import com.brightspark.sparkshammers.util.LoaderHelper;

public class SHModItems
{
    //Common Metals
    public static ItemAOE hammerCopper, hammerSilver, hammerTin, hammerLead, hammerNickel,
            hammerPlatinum, hammerBronze, hammerSteel, hammerInvar, hammerElectrum,
            excavatorCopper, excavatorSilver, excavatorTin, excavatorLead, excavatorNickel,
            excavatorPlatinum, excavatorBronze, excavatorSteel, excavatorInvar, excavatorElectrum;

    //Botania
    public static ItemAOE hammerManasteel, hammerTerrasteel, hammerElementium,
            excavatorManasteel, excavatorTerrasteel, excavatorElementium;

    //EnderIO
    //public static ItemAOEDarkSteel hammerDarksteel, excavatorDarksteel;

    //MobHunter
    public static ItemAOE hammerMachalite, hammerDragonite, hammerGossamite,
            excavatorMachalite, excavatorDragonite, excavatorGossamite;

    public static void regItems()
    {
        //Botania
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_MANASTEEL))
        {
            SHItems.regItem(hammerManasteel = new ItemAOE(Names.EnumMaterials.MANASTEEL));
            SHItems.regItem(excavatorManasteel = new ItemAOE(Names.EnumMaterials.MANASTEEL, true));
        }
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_TERRASTEEL))
        {
            SHItems.regItem(hammerTerrasteel = new ItemAOE(Names.EnumMaterials.TERRASTEEL));
            SHItems.regItem(excavatorTerrasteel = new ItemAOE(Names.EnumMaterials.TERRASTEEL, true));
        }
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_ELEMENTIUM))
        {
            SHItems.regItem(hammerElementium = new ItemAOE(Names.EnumMaterials.ELEMENTIUM));
            SHItems.regItem(excavatorElementium = new ItemAOE(Names.EnumMaterials.ELEMENTIUM, true));
        }

        //EnderIO
        /*
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_DARKSTEEL))
        {
            SHItems.regItem(hammerDarksteel = new ItemAOE(Names.EnumMaterials.DARKSTEEL));
            SHItems.regItem(excavatorDarksteel = new ItemAOEDarkSteel(Names.EnumMaterials.DARKSTEEL, true));
        }
        */

        //MobHunter
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_MACHALITE))
        {
            SHItems.regItem(hammerMachalite = new ItemAOE(Names.EnumMaterials.MACHALITE));
            SHItems.regItem(excavatorMachalite = new ItemAOE(Names.EnumMaterials.MACHALITE, true));
        }
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_DRAGONITE))
        {
            SHItems.regItem(hammerDragonite = new ItemAOE(Names.EnumMaterials.DRAGONITE));
            SHItems.regItem(excavatorDragonite = new ItemAOE(Names.EnumMaterials.DRAGONITE, true));
        }
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_GOSSAMITE))
        {
            SHItems.regItem(hammerGossamite = new ItemAOE(Names.EnumMaterials.GOSSAMITE));
            SHItems.regItem(excavatorGossamite = new ItemAOE(Names.EnumMaterials.GOSSAMITE, true));
        }

        //Misc
        if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_BRONZE))
        {
            SHItems.regItem(hammerBronze = new ItemAOE(Names.EnumMaterials.BRONZE));
            SHItems.regItem(excavatorBronze = new ItemAOE(Names.EnumMaterials.BRONZE, true));
        }
    }

    public static void regModels()
    {
        //This is now all automatically handled in SHItems.regModels()
        //Leaving this method here for now incase I need it.
    }
}
