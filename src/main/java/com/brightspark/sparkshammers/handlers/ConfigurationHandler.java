package com.brightspark.sparkshammers.handlers;

import com.brightspark.sparkshammers.reference.Config;
import com.brightspark.sparkshammers.reference.Reference;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

class Categories
{
    public static final String GENERAL = Configuration.CATEGORY_GENERAL;
    public static final String TOOLS = "tools";
    public static final String SPECIFIC_TOOLS = "specific tools";
    public static final String WORLD_GEN = "world generation";
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
        //Config.useEasyUnstableRecipe = configuration.getBoolean("useEasyUnstableRecipe", Categories.GENERAL, false, "If set to true, will use old 'easier' recipe using a vanilla crafting table for the unstable hammer and excavator.");
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

        Config.machaliteHarvestLevel = configuration.getInt("machaliteHarvestLevel", Categories.SPECIFIC_TOOLS, Config.machaliteHarvestLevel, Config.harvestLevelMin, Config.harvestLevelMax, "");
        Config.machaliteMaxUses = configuration.getInt("machaliteMaxUses", Categories.SPECIFIC_TOOLS, Config.machaliteMaxUses, Config.maxUsesMin, Config.maxUsesMax, "");
        Config.machaliteEfficiency = configuration.getFloat("machaliteEfficiency", Categories.SPECIFIC_TOOLS, Config.machaliteEfficiency, Config.efficiencyMin, Config.efficiencyMax, "");
        Config.machaliteDamageVsEntity = configuration.getFloat("machaliteDamageVsEntity", Categories.SPECIFIC_TOOLS, Config.machaliteDamageVsEntity, Config.damageMin, Config.damageMax, "");
        Config.machaliteEnchantability = configuration.getInt("machaliteEnchantability", Categories.SPECIFIC_TOOLS, Config.machaliteEnchantability, Config.enchantabilityMin, Config.enchantabilityMax, "");

        Config.dragoniteHarvestLevel = configuration.getInt("dragoniteHarvestLevel", Categories.SPECIFIC_TOOLS, Config.dragoniteHarvestLevel, Config.harvestLevelMin, Config.harvestLevelMax, "");
        Config.dragoniteMaxUses = configuration.getInt("dragoniteMaxUses", Categories.SPECIFIC_TOOLS, Config.dragoniteMaxUses, Config.maxUsesMin, Config.maxUsesMax, "");
        Config.dragoniteEfficiency = configuration.getFloat("dragoniteEfficiency", Categories.SPECIFIC_TOOLS, Config.dragoniteEfficiency, Config.efficiencyMin, Config.efficiencyMax, "");
        Config.dragoniteDamageVsEntity = configuration.getFloat("dragoniteDamageVsEntity", Categories.SPECIFIC_TOOLS, Config.dragoniteDamageVsEntity, Config.damageMin, Config.damageMax, "");
        Config.dragoniteEnchantability = configuration.getInt("dragoniteEnchantability", Categories.SPECIFIC_TOOLS, Config.dragoniteEnchantability, Config.enchantabilityMin, Config.enchantabilityMax, "");

        Config.gossamiteHarvestLevel = configuration.getInt("gossamiteHarvestLevel", Categories.SPECIFIC_TOOLS, Config.gossamiteHarvestLevel, Config.harvestLevelMin, Config.harvestLevelMax, "");
        Config.gossamiteMaxUses = configuration.getInt("gossamiteMaxUses", Categories.SPECIFIC_TOOLS, Config.gossamiteMaxUses, Config.maxUsesMin, Config.maxUsesMax, "");
        Config.gossamiteEfficiency = configuration.getFloat("gossamiteEfficiency", Categories.SPECIFIC_TOOLS, Config.gossamiteEfficiency, Config.efficiencyMin, Config.efficiencyMax, "");
        Config.gossamiteDamageVsEntity = configuration.getFloat("gossamiteDamageVsEntity", Categories.SPECIFIC_TOOLS, Config.gossamiteDamageVsEntity, Config.damageMin, Config.damageMax, "");
        Config.gossamiteEnchantability = configuration.getInt("gossamiteEnchantability", Categories.SPECIFIC_TOOLS, Config.gossamiteEnchantability, Config.enchantabilityMin, Config.enchantabilityMax, "");

        /*
         * Mjolnir Hammer
         */
        Config.mjolnirHarvestLevel = configuration.getInt("mjolnirHarvestLevel", Categories.SPECIFIC_TOOLS, Config.mjolnirHarvestLevel, Config.harvestLevelMin, Config.harvestLevelMax, "");
        Config.mjolnirEfficiency = configuration.getFloat("mjolnirEfficiency", Categories.SPECIFIC_TOOLS, Config.mjolnirEfficiency, Config.efficiencyMin, Config.efficiencyMax, "");
        Config.mjolnirDamageVsEntity = configuration.getFloat("mjolnirDamageVsEntity", Categories.SPECIFIC_TOOLS, Config.mjolnirDamageVsEntity, Config.damageMin, Config.damageMax, "");

        /*
         * World Generation
         */
        Config.shouldGenerateMjolnirShrines = configuration.getBoolean("shouldGenerateMjolnirShrines", Categories.WORLD_GEN, Config.shouldGenerateMjolnirShrines, "Whether shrine structures should be generated in the world to find Mjolnir in.");
        Config.mjolnirShrineRarity = configuration.getInt("mjolnirShrineRarity", Categories.WORLD_GEN, Config.mjolnirShrineRarity, Config.maxUsesMin, Config.maxUsesMax, "Chance of a shrine spawning (Higher is less chance).");
        Config.mjolnirShrineMinY = configuration.getInt("mjolnirShrineMinY", Categories.WORLD_GEN, Config.mjolnirShrineMinY, 0, 245, "Minimum Y coordinate value for the shrine to spawn at.");
        Config.mjolnirShrineDebug = configuration.getBoolean("mjolnirShrineDebug", Categories.WORLD_GEN, Config.mjolnirShrineDebug, "When true, a log will be printed in the console every time a shrine is generated with it's coordinates.");

        Config.shouldAddMjolnirToLoot = configuration.getBoolean("shouldAddMjolnirToLoot", Categories.WORLD_GEN, Config.shouldAddMjolnirToLoot, "Whether Mjolnir should be added to desert pyramid loot.");
        Config.mjolnirLootRarity = configuration.getInt("mjolnirLootRarity", Categories.WORLD_GEN, Config.mjolnirLootRarity, Config.maxUsesMin, Config.maxUsesMax, "Chance of finding Mjolnir (Lower is less chance).");
        Config.shouldAddMineshaftLoot = configuration.getBoolean("shouldAddMineshaftLoot", Categories.WORLD_GEN, Config.shouldAddMineshaftLoot, "Whether wooden and stone hammers and excavators should be added to mineshaft loot.");
        Config.mineshaftLootRarity = configuration.getInt("mineshaftLootRarity", Categories.WORLD_GEN, Config.mineshaftLootRarity, Config.maxUsesMin, Config.maxUsesMax, "Chance of finding loot (Lower is less chance).");

        if(configuration.hasChanged())
        {
            configuration.save();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if(event.getModID().equalsIgnoreCase(Reference.MOD_ID))
        {
            //Resync configs
            loadConfiguration();
        }
    }
}