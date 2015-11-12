package com.brightspark.sparkshammers.util;

import com.brightspark.sparkshammers.reference.Config;
import cpw.mods.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

public class LoaderHelper
{
    private static ArrayList<String> modsChecked = new ArrayList<String>();
    private static ArrayList<String> modsLoaded = new ArrayList<String>();

    private static ArrayList<String> oresChecked = new ArrayList<String>();
    private static ArrayList<String> oresExist = new ArrayList<String>();

    /**
     * Uses {@link Loader} to check if a mod is loaded, but first
     * checks the config for whether it should check for the mod or not.
     * @param modName Name of mod to be checked for
     * @return Whether the mod is loaded
     */
    public static boolean isModLoaded(String modName)
    {
        //Check config
        if(!Config.includeOtherModItems)
            return false;

        //Has mod already been checked before
        if(modsChecked.contains(modName))
            return modsLoaded.contains(modName);
        else
        {
            //Check mod and add to the list for future mod checks
            modsChecked.add(modName);
            if(Loader.isModLoaded(modName))
            {
                modsLoaded.add(modName);
                return true;
            }
            return false;
        }
    }

    /**
     * Uses {@link OreDictionary} to check if an ore dictionary name exists,
     * but first checks the config fro whether it should check for the ore or not.
     * @param oreName Name of the ore to check for
     * @return Whether the ore exists or not
     */
    public static boolean doesOreExists(String oreName)
    {
        //Check config
        if(!Config.includeOtherModItems)
            return false;

        //Has ore already been check before
        if(oresChecked.contains(oreName))
            return oresExist.contains(oreName);
        else
        {
            //Check ore and add to the list for future ore checks
            oresChecked.add(oreName);
            if(OreDictionary.doesOreNameExist(oreName))
            {
                oresExist.add(oreName);
                return true;
            }
            return false;
        }
    }
}
