package com.brightspark.sparkshammers.util;

import com.brightspark.sparkshammers.reference.Config;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class LoaderHelper
{
    private static HashMap<String, Boolean> modsChecked = new HashMap<String, Boolean>();
    private static HashMap<String, Boolean> oresChecked = new HashMap<String, Boolean>();
    private static HashMap<String, Boolean> materialsChecked = new HashMap<String, Boolean>();

    //Ore Dictionary
    private static ArrayList<String> oreDict = new ArrayList<String>(Arrays.asList(OreDictionary.getOreNames()));

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
        if(!modsChecked.containsKey(modName))
            //Check mod and add to the list for future mod checks
            modsChecked.put(modName, Loader.isModLoaded(modName));
        return modsChecked.get(modName);
    }

    /**
     * Uses {@link OreDictionary} to check if an ore dictionary name exists,
     * but first checks the config fro whether it should check for the ore or not.
     * @param oreName Name of the ore to check for
     * @return Whether the ore exists or not
     */
    public static boolean doesOreExist(String oreName)
    {
        //Check config
        if(!Config.includeOtherModItems)
            return false;

        //Has ore already been checked before
        if(!oresChecked.containsKey(oreName))
            //Check ore and add to the list for future ore checks
            oresChecked.put(oreName, oreDict.contains(oreName));
        return oresChecked.get(oreName);
    }

    public static boolean doesToolMaterialExist(String materialName)
    {
        //Check config
        if(!Config.includeOtherModItems)
            return false;

        //Has tool material already been check before
        if(!materialsChecked.containsKey(materialName))
        {
            //Check tool material and add to the list for future tool material checks
            ToolMaterial[] materials = ToolMaterial.values();
            for(ToolMaterial mat : materials)
            {
                if(mat.name().equals(materialName))
                {
                    materialsChecked.put(materialName, true);
                    return true;
                }
            }
            materialsChecked.put(materialName, false);
        }
        return materialsChecked.get(materialName);
    }
}
