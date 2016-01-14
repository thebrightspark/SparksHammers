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
    public static final String SPECIFIC_TOOLS = "specific tools";
}

class Descriptions
{
    //public static final String TOOL_AUTO_UPDATE = "Whether tool material should be auto changed to use the respective material traits from it's pickaxe from the original mod.";
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
        Config.useEasyUnstableHammerRecipe = configuration.getBoolean("useEasyUnstableHammerRecipe", Categories.GENERAL, false, "If set to true, will use old 'easier' recipe using a vanilla crafting table.");
        Config.toolDurabilityModifier = configuration.getFloat("toolDurabilityModifier", Categories.TOOLS, Config.toolDurabilityModifier, Config.toolDurabilityModifierMin, Config.toolDurabilityModifierMax, "Modifier for hammers and excavators made of vanilla materials to adjust durability.");
        Config.toolSpeedModifier = configuration.getFloat("toolSpeedModifier", Categories.TOOLS, Config.toolSpeedModifier, Config.toolSpeedModifierMin, Config.toolSpeedModifierMax, "Modifier for hammers and excavators made of vanilla materials to adjust mining speed.");
        //Config.netherStarHammerDurability = configuration.getInt("netherStarHammerDurability", Categories.TOOLS, Config.netherStarHammerDurability, Config.netherStarHammerDurabilityMin, Config.netherStarHammerDurabilityMax, "Durability of the Nether Star made hammer");
        //Config.netherStarHammerDistance = configuration.getInt("netherStarHammerDistance", Categories.TOOLS, Config.netherStarHammerDistance, Config.netherStarHammerDistanceMin, Config.netherStarHammerDistanceMax, "Max mining distance of the Nether Star made hammer");

        /*
         * Tool Specific Configs
         */
        Config.manasteelHarvestLevel = configuration.getInt("manasteelHarvestLevel", Categories.SPECIFIC_TOOLS, Config.manasteelHarvestLevel, Config.harvestLevelMin, Config.harvestLevelMax, "");
        Config.manasteelMaxUses = configuration.getInt("manasteelMaxUses", Categories.SPECIFIC_TOOLS, Config.manasteelMaxUses, Config.maxUsesMin, Config.maxUsesMax, "");
        Config.manasteelEfficiency = configuration.getFloat("manasteelEfficiency", Categories.SPECIFIC_TOOLS, Config.manasteelEfficiency, Config.efficiencyMin, Config.efficiencyMax, "");
        Config.manasteelDamageVsEntity = configuration.getFloat("manasteelDamageVsEntity", Categories.SPECIFIC_TOOLS, Config.manasteelDamageVsEntity, Config.damageMin, Config.damageMax, "");
        Config.manasteelEnchantability = configuration.getInt("manasteelEnchantability", Categories.SPECIFIC_TOOLS, Config.manasteelEnchantability, Config.enchantabilityMin, Config.enchantabilityMax, "");

        Config.elementiumHarvestLevel = configuration.getInt("elementiumHarvestLevel", Categories.SPECIFIC_TOOLS, Config.elementiumHarvestLevel, Config.harvestLevelMin, Config.harvestLevelMax, "");
        Config.elementiumMaxUses = configuration.getInt("elementiumMaxUses", Categories.SPECIFIC_TOOLS, Config.elementiumMaxUses, Config.maxUsesMin, Config.maxUsesMax, "");
        Config.elementiumEfficiency = configuration.getFloat("elementiumEfficiency", Categories.SPECIFIC_TOOLS, Config.elementiumEfficiency, Config.efficiencyMin, Config.efficiencyMax, "");
        Config.elementiumDamageVsEntity = configuration.getFloat("elementiumDamageVsEntity", Categories.SPECIFIC_TOOLS, Config.elementiumDamageVsEntity, Config.damageMin, Config.damageMax, "");
        Config.elementiumEnchantability = configuration.getInt("elementiumEnchantability", Categories.SPECIFIC_TOOLS, Config.elementiumEnchantability, Config.enchantabilityMin, Config.enchantabilityMax, "");

        Config.terrasteelHarvestLevel = configuration.getInt("terrasteelHarvestLevel", Categories.SPECIFIC_TOOLS, Config.terrasteelHarvestLevel, Config.harvestLevelMin, Config.harvestLevelMax, "");
        Config.terrasteelMaxUses = configuration.getInt("terrasteelMaxUses", Categories.SPECIFIC_TOOLS, Config.terrasteelMaxUses, Config.maxUsesMin, Config.maxUsesMax, "");
        Config.terrasteelEfficiency = configuration.getFloat("terrasteelEfficiency", Categories.SPECIFIC_TOOLS, Config.terrasteelEfficiency, Config.efficiencyMin, Config.efficiencyMax, "");
        Config.terrasteelDamageVsEntity = configuration.getFloat("terrasteelDamageVsEntity", Categories.SPECIFIC_TOOLS, Config.terrasteelDamageVsEntity, Config.damageMin, Config.damageMax, "");
        Config.terrasteelEnchantability = configuration.getInt("terrasteelEnchantability", Categories.SPECIFIC_TOOLS, Config.terrasteelEnchantability, Config.enchantabilityMin, Config.enchantabilityMax, "");

        Config.spectreHarvestLevel = configuration.getInt("spectreHarvestLevel", Categories.SPECIFIC_TOOLS, Config.spectreHarvestLevel, Config.harvestLevelMin, Config.harvestLevelMax, "");
        Config.spectreMaxUses = configuration.getInt("spectreMaxUses", Categories.SPECIFIC_TOOLS, Config.spectreMaxUses, Config.maxUsesMin, Config.maxUsesMax, "");
        Config.spectreEfficiency = configuration.getFloat("spectreEfficiency", Categories.SPECIFIC_TOOLS, Config.spectreEfficiency, Config.efficiencyMin, Config.efficiencyMax, "");
        Config.spectreDamageVsEntity = configuration.getFloat("spectreDamageVsEntity", Categories.SPECIFIC_TOOLS, Config.spectreDamageVsEntity, Config.damageMin, Config.damageMax, "");
        Config.spectreEnchantability = configuration.getInt("spectreEnchantability", Categories.SPECIFIC_TOOLS, Config.spectreEnchantability, Config.enchantabilityMin, Config.enchantabilityMax, "");

        Config.darksteelHarvestLevel = configuration.getInt("darksteelHarvestLevel", Categories.SPECIFIC_TOOLS, Config.darksteelHarvestLevel, Config.harvestLevelMin, Config.harvestLevelMax, "");
        Config.darksteelMaxUses = configuration.getInt("darksteelMaxUses", Categories.SPECIFIC_TOOLS, Config.darksteelMaxUses, Config.maxUsesMin, Config.maxUsesMax, "");
        Config.darksteelEfficiency = configuration.getFloat("darksteelEfficiency", Categories.SPECIFIC_TOOLS, Config.darksteelEfficiency, Config.efficiencyMin, Config.efficiencyMax, "");
        Config.darksteelDamageVsEntity = configuration.getFloat("darksteelDamageVsEntity", Categories.SPECIFIC_TOOLS, Config.darksteelDamageVsEntity, Config.damageMin, Config.damageMax, "");
        Config.darksteelEnchantability = configuration.getInt("darksteelEnchantability", Categories.SPECIFIC_TOOLS, Config.darksteelEnchantability, Config.enchantabilityMin, Config.enchantabilityMax, "");

        Config.bronzeHarvestLevel = configuration.getInt("bronzeHarvestLevel", Categories.SPECIFIC_TOOLS, Config.bronzeHarvestLevel, Config.harvestLevelMin, Config.harvestLevelMax, "");
        Config.bronzeMaxUses = configuration.getInt("bronzeMaxUses", Categories.SPECIFIC_TOOLS, Config.bronzeMaxUses, Config.maxUsesMin, Config.maxUsesMax, "");
        Config.bronzeEfficiency = configuration.getFloat("bronzeEfficiency", Categories.SPECIFIC_TOOLS, Config.bronzeEfficiency, Config.efficiencyMin, Config.efficiencyMax, "");
        Config.bronzeDamageVsEntity = configuration.getFloat("bronzeDamageVsEntity", Categories.SPECIFIC_TOOLS, Config.bronzeDamageVsEntity, Config.damageMin, Config.damageMax, "");
        Config.bronzeEnchantability = configuration.getInt("bronzeEnchantability", Categories.SPECIFIC_TOOLS, Config.bronzeEnchantability, Config.enchantabilityMin, Config.enchantabilityMax, "");

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