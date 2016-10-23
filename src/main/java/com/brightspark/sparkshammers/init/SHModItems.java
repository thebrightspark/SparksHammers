package com.brightspark.sparkshammers.init;

import com.brightspark.sparkshammers.item.ItemAOE;
import com.brightspark.sparkshammers.reference.Names;
import com.brightspark.sparkshammers.util.LoaderHelper;
import com.brightspark.sparkshammers.util.LogHelper;

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
            switch(mat)
            {
                //Ignore vanilla materials
                case WOOD:
                case STONE:
                case IRON:
                case GOLD:
                case DIAMOND:
                    break;

                //Common Mod Metals
                case COPPER:
                    SHItems.regItem(hammerCopper = new ItemAOE(mat));
                    SHItems.regItem(excavatorCopper = new ItemAOE(mat, true));
                    break;
                case SILVER:
                    SHItems.regItem(hammerSilver = new ItemAOE(mat));
                    SHItems.regItem(excavatorSilver = new ItemAOE(mat, true));
                    break;
                case TIN:
                    SHItems.regItem(hammerTin = new ItemAOE(mat));
                    SHItems.regItem(excavatorTin = new ItemAOE(mat, true));
                    break;
                case LEAD:
                    SHItems.regItem(hammerLead = new ItemAOE(mat));
                    SHItems.regItem(excavatorLead = new ItemAOE(mat, true));
                    break;
                case NICKEL:
                    SHItems.regItem(hammerNickel = new ItemAOE(mat));
                    SHItems.regItem(excavatorNickel = new ItemAOE(mat, true));
                    break;
                case PLATINUM:
                    SHItems.regItem(hammerPlatinum = new ItemAOE(mat));
                    SHItems.regItem(excavatorPlatinum = new ItemAOE(mat, true));
                    break;
                case BRONZE:
                    SHItems.regItem(hammerBronze = new ItemAOE(mat));
                    SHItems.regItem(excavatorBronze = new ItemAOE(mat, true));
                    break;
                case STEEL:
                    SHItems.regItem(hammerSteel = new ItemAOE(mat));
                    SHItems.regItem(excavatorSteel = new ItemAOE(mat, true));
                    break;
                case INVAR:
                    SHItems.regItem(hammerInvar = new ItemAOE(mat));
                    SHItems.regItem(excavatorInvar = new ItemAOE(mat, true));
                    break;
                case ELECTRUM:
                    SHItems.regItem(hammerElectrum = new ItemAOE(mat));
                    SHItems.regItem(excavatorElectrum = new ItemAOE(mat, true));
                    break;

                //Botania
                case MANASTEEL:
                case TERRASTEEL:
                case ELEMENTIUM:
                    if(LoaderHelper.isModLoaded(Names.Mods.BOTANIA))
                        SHItemsBotania.reg(mat);
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
                    SHItems.regItem(hammerMachalite = new ItemAOE(mat));
                    SHItems.regItem(excavatorMachalite = new ItemAOE(mat, true));
                    break;
                case DRAGONITE:
                    SHItems.regItem(hammerDragonite = new ItemAOE(mat));
                    SHItems.regItem(excavatorDragonite = new ItemAOE(mat, true));
                    break;
                case GOSSAMITE:
                    SHItems.regItem(hammerGossamite = new ItemAOE(mat));
                    SHItems.regItem(excavatorGossamite = new ItemAOE(mat, true));
                    break;
                default:
                    LogHelper.info("Hammer/Excavator tool material " + mat + " was not registered to any tool.");
            }
        }
    }

    public static void regModels()
    {
        //This is now all automatically handled in SHItems.regModels()
        //Leaving this method here for now incase I need it.
    }
}
