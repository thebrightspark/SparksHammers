package com.brightspark.sparkshammers.handlers;

import com.brightspark.sparkshammers.reference.Config;
import com.brightspark.sparkshammers.reference.Reference;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

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
        Config.includeOtherModItems = configuration.getBoolean("includeOtherModItems", Categories.GENERAL, true, "Whether to add tools made from other mod materials into the game.");
        Config.toolDurabilityModifier = configuration.getFloat("toolDurabilityModifier", Categories.TOOLS, Config.toolDurabilityModifier, Config.toolDurabilityModifierMin, Config.toolDurabilityModifierMax, "Modifier for hammers and excavators made of vanilla materials to adjust durability.");
        Config.toolSpeedModifier = configuration.getFloat("toolSpeedModifier", Categories.TOOLS, Config.toolSpeedModifier, Config.toolSpeedModifierMin, Config.toolSpeedModifierMax, "Modifier for hammers and excavators made of vanilla materials to adjust mining speed.");

        //Init after loading tool modifiers, but before loading tools
        Config.initMaterials();

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
        Config.netherStarHammerDurability = configuration.getInt("netherStarHammerDurability", Categories.SPECIFIC_TOOLS, Config.netherStarHammerDurability, Config.netherStarHammerDurabilityMin, Config.netherStarHammerDurabilityMax, "Durability of the Nether Star made hammer");
        Config.netherStarHammerDistance = configuration.getInt("netherStarHammerDistance", Categories.SPECIFIC_TOOLS, Config.netherStarHammerDistance, Config.netherStarHammerDistanceMin, Config.netherStarHammerDistanceMax, "Max mining distance of the Nether Star made hammer");

        /*
         * Mjolnir Hammer
         */
        Config.mjolnirHarvestLevel = configuration.getInt("mjolnirHarvestLevel", Categories.SPECIFIC_TOOLS, Config.mjolnirHarvestLevel, 0, Integer.MAX_VALUE, "");
        Config.mjolnirEfficiency = configuration.getFloat("mjolnirEfficiency", Categories.SPECIFIC_TOOLS, Config.mjolnirEfficiency, 0f, 100f, "");
        Config.mjolnirDamageVsEntity = configuration.getFloat("mjolnirDamageVsEntity", Categories.SPECIFIC_TOOLS, Config.mjolnirDamageVsEntity, 0f, Integer.MAX_VALUE, "");
        Config.mjolnirPickupNeedsDragonAchieve = configuration.getBoolean("mjolnirPickupNeedsDragonAchieve", Categories.SPECIFIC_TOOLS, Config.mjolnirPickupNeedsDragonAchieve, "Whether the player needs to have gotten the 'End.' achievement to be able to pickup Mjolnir.");

        /*
         * Common Mod Metal Tools
         */
        Config.materialCopper = getMaterial("Copper", Config.materialCopper);
        Config.materialSilver = getMaterial("Silver", Config.materialSilver);
        Config.materialTin = getMaterial("Tin", Config.materialTin);
        Config.materialLead = getMaterial("Lead", Config.materialLead);
        Config.materialNickel = getMaterial("Nickel", Config.materialNickel);
        Config.materialPlatinum = getMaterial("Platinum", Config.materialPlatinum);
        Config.materialBronze = getMaterial("Bronze", Config.materialBronze);
        Config.materialSteel = getMaterial("Steel", Config.materialSteel);
        Config.materialInvar = getMaterial("Invar", Config.materialInvar);
        Config.materialElectrum = getMaterial("Electrum", Config.materialElectrum);
        Config.materialAluminium = getMaterial("Aluminium", Config.materialAluminium);

        Config.materialOsmium = getMaterial("Osmium", Config.materialOsmium);
        Config.materialZinc = getMaterial("Zinc", Config.materialZinc);
        Config.materialChrome = getMaterial("Chrome", Config.materialChrome);
        Config.materialIridium = getMaterial("Iridium", Config.materialIridium);
        Config.materialTitanium = getMaterial("Titanium", Config.materialTitanium);
        Config.materialTungsten = getMaterial("Tungsten", Config.materialTungsten);

        Config.materialSapphire = getMaterial("Sapphire", Config.materialSapphire);
        Config.materialRuby = getMaterial("Ruby", Config.materialRuby);
        Config.materialPeridot = getMaterial("Peridot", Config.materialPeridot);

        Config.materialManasteel = getMaterial("Manasteel", Config.materialManasteel);
        Config.materialElementium = getMaterial("Elementium", Config.materialElementium);
        Config.materialTerrasteel = getMaterial("Terrasteel", Config.materialTerrasteel);

        Config.materialMachalite = getMaterial("Machalite", Config.materialMachalite);
        Config.materialDragonite = getMaterial("Dragonite", Config.materialDragonite);
        Config.materialGossamite = getMaterial("Gossamite", Config.materialGossamite);

        Config.materialDarksteel = getMaterial("Darksteel", Config.materialDarksteel);

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