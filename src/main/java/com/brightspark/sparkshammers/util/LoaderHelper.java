package com.brightspark.sparkshammers.util;

import com.brightspark.sparkshammers.reference.Config;
import cpw.mods.fml.common.Loader;

public class LoaderHelper
{
    /**
     * Uses {@link Loader} to check is a mod is loaded, but first
     * checks the config for whether it should check for the mod or not.
     * @param modName Name of mod to be checked for
     * @return Whether the mod is loaded
     */
    public static boolean isModLoaded(String modName)
    {
        return Config.includeOtherModItems && Loader.isModLoaded(modName);
    }
}
