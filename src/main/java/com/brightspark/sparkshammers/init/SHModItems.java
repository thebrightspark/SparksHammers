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
        for(Names.EnumMaterials mat : Names.EnumMaterials.values())
        {
            if(mat.dependantOreDic == null || mat.dependantOreDic.equals(Names.EnumMaterials.OTHER_DEPENDENCY))
                continue;
            if(LoaderHelper.doesOreExist(mat.dependantOreDic))
            {
                switch(mat)
                {
                    //Common Mod Metals
                    case COPPER:
                        SHItems.regItem(hammerCopper = new ItemAOE(Names.EnumMaterials.COPPER));
                        SHItems.regItem(excavatorCopper = new ItemAOE(Names.EnumMaterials.COPPER, true));
                        break;
                    case SILVER:
                        SHItems.regItem(hammerSilver = new ItemAOE(Names.EnumMaterials.SILVER));
                        SHItems.regItem(excavatorSilver = new ItemAOE(Names.EnumMaterials.SILVER, true));
                        break;
                    case TIN:
                        SHItems.regItem(hammerTin = new ItemAOE(Names.EnumMaterials.TIN));
                        SHItems.regItem(excavatorTin = new ItemAOE(Names.EnumMaterials.TIN, true));
                        break;
                    case LEAD:
                        SHItems.regItem(hammerLead = new ItemAOE(Names.EnumMaterials.LEAD));
                        SHItems.regItem(excavatorLead = new ItemAOE(Names.EnumMaterials.LEAD, true));
                        break;
                    case NICKEL:
                        SHItems.regItem(hammerNickel = new ItemAOE(Names.EnumMaterials.NICKEL));
                        SHItems.regItem(excavatorNickel = new ItemAOE(Names.EnumMaterials.NICKEL, true));
                        break;
                    case PLATINUM:
                        SHItems.regItem(hammerPlatinum = new ItemAOE(Names.EnumMaterials.PLATINUM));
                        SHItems.regItem(excavatorPlatinum = new ItemAOE(Names.EnumMaterials.PLATINUM, true));
                        break;
                    case BRONZE:
                        SHItems.regItem(hammerBronze = new ItemAOE(Names.EnumMaterials.BRONZE));
                        SHItems.regItem(excavatorBronze = new ItemAOE(Names.EnumMaterials.BRONZE, true));
                        break;
                    case STEEL:
                        SHItems.regItem(hammerSteel = new ItemAOE(Names.EnumMaterials.STEEL));
                        SHItems.regItem(excavatorSteel = new ItemAOE(Names.EnumMaterials.STEEL, true));
                        break;
                    case INVAR:
                        SHItems.regItem(hammerInvar = new ItemAOE(Names.EnumMaterials.INVAR));
                        SHItems.regItem(excavatorInvar = new ItemAOE(Names.EnumMaterials.INVAR, true));
                        break;
                    case ELECTRUM:
                        SHItems.regItem(hammerElectrum = new ItemAOE(Names.EnumMaterials.ELECTRUM));
                        SHItems.regItem(excavatorElectrum = new ItemAOE(Names.EnumMaterials.ELECTRUM, true));
                        break;

                    //Botania
                    case MANASTEEL:
                        SHItemsBotania.registerManasteel();
                        break;
                    case TERRASTEEL:
                    	SHItemsBotania.registerTerrasteel();
                        break;
                    case ELEMENTIUM:
                    	SHItemsBotania.registerElementium();
                        break;

                    //EnderIO
                    /*
                    case DARKSTEEL:
                        SHItems.regItem(hammerDarksteel = new ItemAOE(Names.EnumMaterials.DARKSTEEL));
                        SHItems.regItem(excavatorDarksteel = new ItemAOEDarkSteel(Names.EnumMaterials.DARKSTEEL, true));
                        break;
                    */

                    //Mob Hunter
                    case MACHALITE:
                        SHItems.regItem(hammerMachalite = new ItemAOE(Names.EnumMaterials.MACHALITE));
                        SHItems.regItem(excavatorMachalite = new ItemAOE(Names.EnumMaterials.MACHALITE, true));
                        break;
                    case DRAGONITE:
                        SHItems.regItem(hammerDragonite = new ItemAOE(Names.EnumMaterials.DRAGONITE));
                        SHItems.regItem(excavatorDragonite = new ItemAOE(Names.EnumMaterials.DRAGONITE, true));
                        break;
                    case GOSSAMITE:
                        SHItems.regItem(hammerGossamite = new ItemAOE(Names.EnumMaterials.GOSSAMITE));
                        SHItems.regItem(excavatorGossamite = new ItemAOE(Names.EnumMaterials.GOSSAMITE, true));
                        break;
                }
            }
        }
    }

    public static void regModels()
    {
        //This is now all automatically handled in SHItems.regModels()
        //Leaving this method here for now incase I need it.
    }
}
