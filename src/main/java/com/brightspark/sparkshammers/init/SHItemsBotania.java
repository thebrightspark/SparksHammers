package com.brightspark.sparkshammers.init;

import com.brightspark.sparkshammers.customTools.Tool;
import com.brightspark.sparkshammers.item.ItemHammerMana;

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
    public static void regItem(Tool tool)
    {
        SHItems.regItem(new ItemHammerMana(tool, false));
        SHItems.regItem(new ItemHammerMana(tool, true));
    }
}
