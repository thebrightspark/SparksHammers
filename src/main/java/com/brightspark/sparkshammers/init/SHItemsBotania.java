package com.brightspark.sparkshammers.init;

import com.brightspark.sparkshammers.item.ItemHammerMana;
import com.brightspark.sparkshammers.reference.Names.EnumMaterials;
import com.brightspark.sparkshammers.util.LogHelper;

/**
* @author CreeperShift
* com.brightspark.sparkshammers.init
* Oct 15, 2016
*/

/**
* 
* Seperate class to register Botania tools because it now requires Botania to
* not crash. This way the ItemHammerMana class isn't loaded when Botania is not
* there.
*/

public class SHItemsBotania
{
    public static void reg(EnumMaterials mat)
    {
        switch(mat)
        {
            case MANASTEEL:
                SHItems.regItem(SHModItems.hammerManasteel = new ItemHammerMana(mat));
                SHItems.regItem(SHModItems.excavatorManasteel = new ItemHammerMana(mat, true));
                break;
            case TERRASTEEL:
                SHItems.regItem(SHModItems.hammerTerrasteel = new ItemHammerMana(mat));
                SHItems.regItem(SHModItems.excavatorTerrasteel = new ItemHammerMana(mat, true));
                break;
            case ELEMENTIUM:
                SHItems.regItem(SHModItems.hammerElementium = new ItemHammerMana(mat));
                SHItems.regItem(SHModItems.excavatorElementium = new ItemHammerMana(mat, true));
                break;
            default:
                LogHelper.warn("Botania registration was passed material " + mat + " (This shouldn't happen)");
        }
    }
}
