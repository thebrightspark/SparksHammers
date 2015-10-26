package com.brightspark.sparkshammers.handlers;

import com.brightspark.sparkshammers.reference.Config;
import com.brightspark.sparkshammers.reference.Reference;
import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

class Categories
{
    public static final String GENERAL = Configuration.CATEGORY_GENERAL;
    public static final String TOOLS = "tools";
}

public class ConfigurationHandler
{
    public static Configuration configuration;

    public static void init(File configFile)
    {
        //Create configuration object from the given configuration file
        if(configuration == null)
        {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }

    private static void loadConfiguration()
    {
        //testValue = configuration.getBoolean("configValue", Categories.GENERAL, false, "This is an example config value");
        Config.includeOtherModItems = configuration.getBoolean("includeOtherModItems", Categories.GENERAL, true, "Whether to add tools made from other mod materials into the game.");
        Config.toolDurabilityModifier = configuration.getFloat("toolDurabilityModifier", Categories.TOOLS, Config.toolDurabilityModifier, Config.toolDurabilityModifierMin, Config.toolDurabilityModifierMax, "Modifier for hammers and excavators made of vanilla materials to adjust durability.");
        Config.toolSpeedModifier = configuration.getFloat("toolSpeedModifier", Categories.TOOLS, Config.toolSpeedModifier, Config.toolSpeedModifierMin, Config.toolSpeedModifierMax, "Modifier for hammers and excavators made of vanilla materials to adjust mining speed.");

        if(configuration.hasChanged())
        {
            configuration.save();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(OnConfigChangedEvent event)
    {
        if(event.modID.equalsIgnoreCase(Reference.MOD_ID))
        {
            //Resync configs
            loadConfiguration();
        }
    }
}