package com.brightspark.sparkshammers.handlers;

import com.brightspark.sparkshammers.reference.Config;
import com.brightspark.sparkshammers.reference.Reference;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

@Mod.EventBusSubscriber
public class ConfigurationHandler
{
    public static class Categories
    {
        public static final String GENERAL = Configuration.CATEGORY_GENERAL;
        public static final String TOOLS = "tools";
        public static final String SPECIFIC_TOOLS = "specific tools";
        public static final String WORLD_GEN = "world generation";
    }

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

    /**
     * Gets a double array from the config which stores info about a tool material.
     */
    private static double[] getMaterial(String category, String key, double[] defaultMat, String comment)
    {
        return configuration.get(category, key, defaultMat, comment, 0, Integer.MAX_VALUE, true, 5).getDoubleList();
    }

    private static double[] getMaterial(String matName, double[] defaultMat)
    {
        return getMaterial(Categories.SPECIFIC_TOOLS, "material" + matName, defaultMat, matName + " material");
    }

    private static void loadConfiguration()
    {
        Config.enableOtherModItems = configuration.getBoolean("enableOtherModItems", Categories.GENERAL, true, "Whether to add tools made from other mod materials into the game.");
        Config.toolDurabilityModifier = configuration.getFloat("toolDurabilityModifier", Categories.TOOLS, Config.toolDurabilityModifier, Config.toolDurabilityModifierMin, Config.toolDurabilityModifierMax, "Global modifier for hammers and excavators to adjust durability.");
        Config.toolSpeedModifier = configuration.getFloat("toolSpeedModifier", Categories.TOOLS, Config.toolSpeedModifier, Config.toolSpeedModifierMin, Config.toolSpeedModifierMax, "Global modifier for hammers and excavators to adjust mining speed.");
        Config.toolAttackModifier = configuration.getFloat("toolAttackModifier", Categories.TOOLS, Config.toolAttackModifier, Config.toolAttackModifierMin, Config.toolAttackModifierMax, "Global modifier for hammers and excavators to adjust attack damage.");

        /*
         * World Generation
         */
        Config.shouldGenerateMjolnirShrines = configuration.getBoolean("shouldGenerateMjolnirShrines", Categories.WORLD_GEN, Config.shouldGenerateMjolnirShrines, "Whether shrine structures should be generated in the world to find Mjolnir in.");
        Config.mjolnirShrineRarity = configuration.getInt("mjolnirShrineRarity", Categories.WORLD_GEN, Config.mjolnirShrineRarity, 1, Integer.MAX_VALUE, "Chance of a shrine spawning (Higher is less chance).");
        Config.mjolnirShrineMinY = configuration.getInt("mjolnirShrineMinY", Categories.WORLD_GEN, Config.mjolnirShrineMinY, 0, 245, "Minimum Y coordinate value for the shrine to spawn at.");
        Config.mjolnirShrineDebug = configuration.getBoolean("mjolnirShrineDebug", Categories.WORLD_GEN, Config.mjolnirShrineDebug, "When true, a log will be printed in the console every time a shrine is generated with it's coordinates.");

        /*
         * Nether Star Hammer
         */
        Config.netherStarHammerDistance = configuration.getInt("netherStarHammerDistance", Categories.SPECIFIC_TOOLS, Config.netherStarHammerDistance, Config.netherStarHammerDistanceMin, Config.netherStarHammerDistanceMax, "Max mining distance of the Nether Star made hammer");

        /*
         * Mjolnir Hammer
         */
        Config.mjolnirPickupNeedsDragonAchieve = configuration.getBoolean("mjolnirPickupNeedsDragonAchieve", Categories.SPECIFIC_TOOLS, Config.mjolnirPickupNeedsDragonAchieve, "Whether the player needs to have gotten the 'End.' achievement to be able to pickup Mjolnir.");

        if(configuration.hasChanged())
            configuration.save();
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if(event.getModID().equalsIgnoreCase(Reference.MOD_ID))
            //Resync configs
            loadConfiguration();
    }
}