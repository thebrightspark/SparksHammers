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

    private static void loadConfiguration()
    {
        Config.includeOtherModItems = configuration.getBoolean("includeOtherModItems", Categories.GENERAL, true, "Whether to add tools made from other mod materials into the game.");
        Config.toolDurabilityModifier = configuration.getFloat("toolDurabilityModifier", Categories.TOOLS, Config.toolDurabilityModifier, Config.toolDurabilityModifierMin, Config.toolDurabilityModifierMax, "Modifier for hammers and excavators made of vanilla materials to adjust durability.");
        Config.toolSpeedModifier = configuration.getFloat("toolSpeedModifier", Categories.TOOLS, Config.toolSpeedModifier, Config.toolSpeedModifierMin, Config.toolSpeedModifierMax, "Modifier for hammers and excavators made of vanilla materials to adjust mining speed.");

        /*
         * World Generation
         */
        Config.shouldGenerateMjolnirShrines = configuration.getBoolean("shouldGenerateMjolnirShrines", Categories.WORLD_GEN, Config.shouldGenerateMjolnirShrines, "Whether shrine structures should be generated in the world to find Mjolnir in.");
        Config.mjolnirShrineRarity = configuration.getInt("mjolnirShrineRarity", Categories.WORLD_GEN, Config.mjolnirShrineRarity, Config.maxUsesMin, Config.maxUsesMax, "Chance of a shrine spawning (Higher is less chance).");
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
        Config.mjolnirHarvestLevel = configuration.getInt("mjolnirHarvestLevel", Categories.SPECIFIC_TOOLS, Config.mjolnirHarvestLevel, Config.harvestLevelMin, Config.harvestLevelMax, "");
        Config.mjolnirEfficiency = configuration.getFloat("mjolnirEfficiency", Categories.SPECIFIC_TOOLS, Config.mjolnirEfficiency, Config.efficiencyMin, Config.efficiencyMax, "");
        Config.mjolnirDamageVsEntity = configuration.getFloat("mjolnirDamageVsEntity", Categories.SPECIFIC_TOOLS, Config.mjolnirDamageVsEntity, Config.damageMin, Config.damageMax, "");
        Config.mjolnirPickupNeedsDragonAchieve = configuration.getBoolean("mjolnirPickupNeedsDragonAchieve", Categories.SPECIFIC_TOOLS, Config.mjolnirPickupNeedsDragonAchieve, "Whether the player needs to have gotten the 'End.' achievement to be able to pickup Mjolnir.");

        /*
         * Common Mod Metal Tools
         */

        Config.copperHarvestLevel = configuration.getInt("copperHarvestLevel", Categories.SPECIFIC_TOOLS, Config.copperHarvestLevel, Config.harvestLevelMin, Config.harvestLevelMax, "");
        Config.copperMaxUses = configuration.getInt("copperMaxUses", Categories.SPECIFIC_TOOLS, Config.copperMaxUses, Config.maxUsesMin, Config.maxUsesMax, "");
        Config.copperEfficiency = configuration.getFloat("copperEfficiency", Categories.SPECIFIC_TOOLS, Config.copperEfficiency, Config.efficiencyMin, Config.efficiencyMax, "");
        Config.copperDamageVsEntity = configuration.getFloat("copperDamageVsEntity", Categories.SPECIFIC_TOOLS, Config.copperDamageVsEntity, Config.damageMin, Config.damageMax, "");
        Config.copperEnchantability = configuration.getInt("copperEnchantability", Categories.SPECIFIC_TOOLS, Config.copperEnchantability, Config.enchantabilityMin, Config.enchantabilityMax, "");

        Config.silverHarvestLevel = configuration.getInt("silverHarvestLevel", Categories.SPECIFIC_TOOLS, Config.silverHarvestLevel, Config.harvestLevelMin, Config.harvestLevelMax, "");
        Config.silverMaxUses = configuration.getInt("silverMaxUses", Categories.SPECIFIC_TOOLS, Config.silverMaxUses, Config.maxUsesMin, Config.maxUsesMax, "");
        Config.silverEfficiency = configuration.getFloat("silverEfficiency", Categories.SPECIFIC_TOOLS, Config.silverEfficiency, Config.efficiencyMin, Config.efficiencyMax, "");
        Config.silverDamageVsEntity = configuration.getFloat("silverDamageVsEntity", Categories.SPECIFIC_TOOLS, Config.silverDamageVsEntity, Config.damageMin, Config.damageMax, "");
        Config.silverEnchantability = configuration.getInt("silverEnchantability", Categories.SPECIFIC_TOOLS, Config.silverEnchantability, Config.enchantabilityMin, Config.enchantabilityMax, "");

        Config.tinHarvestLevel = configuration.getInt("tinHarvestLevel", Categories.SPECIFIC_TOOLS, Config.tinHarvestLevel, Config.harvestLevelMin, Config.harvestLevelMax, "");
        Config.tinMaxUses = configuration.getInt("tinMaxUses", Categories.SPECIFIC_TOOLS, Config.tinMaxUses, Config.maxUsesMin, Config.maxUsesMax, "");
        Config.tinEfficiency = configuration.getFloat("tinEfficiency", Categories.SPECIFIC_TOOLS, Config.tinEfficiency, Config.efficiencyMin, Config.efficiencyMax, "");
        Config.tinDamageVsEntity = configuration.getFloat("tinDamageVsEntity", Categories.SPECIFIC_TOOLS, Config.tinDamageVsEntity, Config.damageMin, Config.damageMax, "");
        Config.tinEnchantability = configuration.getInt("tinEnchantability", Categories.SPECIFIC_TOOLS, Config.tinEnchantability, Config.enchantabilityMin, Config.enchantabilityMax, "");

        Config.leadHarvestLevel = configuration.getInt("leadHarvestLevel", Categories.SPECIFIC_TOOLS, Config.leadHarvestLevel, Config.harvestLevelMin, Config.harvestLevelMax, "");
        Config.leadMaxUses = configuration.getInt("leadMaxUses", Categories.SPECIFIC_TOOLS, Config.leadMaxUses, Config.maxUsesMin, Config.maxUsesMax, "");
        Config.leadEfficiency = configuration.getFloat("leadEfficiency", Categories.SPECIFIC_TOOLS, Config.leadEfficiency, Config.efficiencyMin, Config.efficiencyMax, "");
        Config.leadDamageVsEntity = configuration.getFloat("leadDamageVsEntity", Categories.SPECIFIC_TOOLS, Config.leadDamageVsEntity, Config.damageMin, Config.damageMax, "");
        Config.leadEnchantability = configuration.getInt("leadEnchantability", Categories.SPECIFIC_TOOLS, Config.leadEnchantability, Config.enchantabilityMin, Config.enchantabilityMax, "");

        Config.nickelHarvestLevel = configuration.getInt("nickelHarvestLevel", Categories.SPECIFIC_TOOLS, Config.nickelHarvestLevel, Config.harvestLevelMin, Config.harvestLevelMax, "");
        Config.nickelMaxUses = configuration.getInt("nickelMaxUses", Categories.SPECIFIC_TOOLS, Config.nickelMaxUses, Config.maxUsesMin, Config.maxUsesMax, "");
        Config.nickelEfficiency = configuration.getFloat("nickelEfficiency", Categories.SPECIFIC_TOOLS, Config.nickelEfficiency, Config.efficiencyMin, Config.efficiencyMax, "");
        Config.nickelDamageVsEntity = configuration.getFloat("nickelDamageVsEntity", Categories.SPECIFIC_TOOLS, Config.nickelDamageVsEntity, Config.damageMin, Config.damageMax, "");
        Config.nickelEnchantability = configuration.getInt("nickelEnchantability", Categories.SPECIFIC_TOOLS, Config.nickelEnchantability, Config.enchantabilityMin, Config.enchantabilityMax, "");

        Config.platinumHarvestLevel = configuration.getInt("platinumHarvestLevel", Categories.SPECIFIC_TOOLS, Config.platinumHarvestLevel, Config.harvestLevelMin, Config.harvestLevelMax, "");
        Config.platinumMaxUses = configuration.getInt("platinumMaxUses", Categories.SPECIFIC_TOOLS, Config.platinumMaxUses, Config.maxUsesMin, Config.maxUsesMax, "");
        Config.platinumEfficiency = configuration.getFloat("platinumEfficiency", Categories.SPECIFIC_TOOLS, Config.platinumEfficiency, Config.efficiencyMin, Config.efficiencyMax, "");
        Config.platinumDamageVsEntity = configuration.getFloat("platinumDamageVsEntity", Categories.SPECIFIC_TOOLS, Config.platinumDamageVsEntity, Config.damageMin, Config.damageMax, "");
        Config.platinumEnchantability = configuration.getInt("platinumEnchantability", Categories.SPECIFIC_TOOLS, Config.platinumEnchantability, Config.enchantabilityMin, Config.enchantabilityMax, "");

        Config.bronzeHarvestLevel = configuration.getInt("bronzeHarvestLevel", Categories.SPECIFIC_TOOLS, Config.bronzeHarvestLevel, Config.harvestLevelMin, Config.harvestLevelMax, "");
        Config.bronzeMaxUses = configuration.getInt("bronzeMaxUses", Categories.SPECIFIC_TOOLS, Config.bronzeMaxUses, Config.maxUsesMin, Config.maxUsesMax, "");
        Config.bronzeEfficiency = configuration.getFloat("bronzeEfficiency", Categories.SPECIFIC_TOOLS, Config.bronzeEfficiency, Config.efficiencyMin, Config.efficiencyMax, "");
        Config.bronzeDamageVsEntity = configuration.getFloat("bronzeDamageVsEntity", Categories.SPECIFIC_TOOLS, Config.bronzeDamageVsEntity, Config.damageMin, Config.damageMax, "");
        Config.bronzeEnchantability = configuration.getInt("bronzeEnchantability", Categories.SPECIFIC_TOOLS, Config.bronzeEnchantability, Config.enchantabilityMin, Config.enchantabilityMax, "");

        Config.steelHarvestLevel = configuration.getInt("steelHarvestLevel", Categories.SPECIFIC_TOOLS, Config.steelHarvestLevel, Config.harvestLevelMin, Config.harvestLevelMax, "");
        Config.steelMaxUses = configuration.getInt("steelMaxUses", Categories.SPECIFIC_TOOLS, Config.steelMaxUses, Config.maxUsesMin, Config.maxUsesMax, "");
        Config.steelEfficiency = configuration.getFloat("steelEfficiency", Categories.SPECIFIC_TOOLS, Config.steelEfficiency, Config.efficiencyMin, Config.efficiencyMax, "");
        Config.steelDamageVsEntity = configuration.getFloat("steelDamageVsEntity", Categories.SPECIFIC_TOOLS, Config.steelDamageVsEntity, Config.damageMin, Config.damageMax, "");
        Config.steelEnchantability = configuration.getInt("steelEnchantability", Categories.SPECIFIC_TOOLS, Config.steelEnchantability, Config.enchantabilityMin, Config.enchantabilityMax, "");

        Config.invarHarvestLevel = configuration.getInt("invarHarvestLevel", Categories.SPECIFIC_TOOLS, Config.invarHarvestLevel, Config.harvestLevelMin, Config.harvestLevelMax, "");
        Config.invarMaxUses = configuration.getInt("invarMaxUses", Categories.SPECIFIC_TOOLS, Config.invarMaxUses, Config.maxUsesMin, Config.maxUsesMax, "");
        Config.invarEfficiency = configuration.getFloat("invarEfficiency", Categories.SPECIFIC_TOOLS, Config.invarEfficiency, Config.efficiencyMin, Config.efficiencyMax, "");
        Config.invarDamageVsEntity = configuration.getFloat("invarDamageVsEntity", Categories.SPECIFIC_TOOLS, Config.invarDamageVsEntity, Config.damageMin, Config.damageMax, "");
        Config.invarEnchantability = configuration.getInt("invarEnchantability", Categories.SPECIFIC_TOOLS, Config.invarEnchantability, Config.enchantabilityMin, Config.enchantabilityMax, "");

        Config.electrumHarvestLevel = configuration.getInt("electrumHarvestLevel", Categories.SPECIFIC_TOOLS, Config.electrumHarvestLevel, Config.harvestLevelMin, Config.harvestLevelMax, "");
        Config.electrumMaxUses = configuration.getInt("electrumMaxUses", Categories.SPECIFIC_TOOLS, Config.electrumMaxUses, Config.maxUsesMin, Config.maxUsesMax, "");
        Config.electrumEfficiency = configuration.getFloat("electrumEfficiency", Categories.SPECIFIC_TOOLS, Config.electrumEfficiency, Config.efficiencyMin, Config.efficiencyMax, "");
        Config.electrumDamageVsEntity = configuration.getFloat("electrumDamageVsEntity", Categories.SPECIFIC_TOOLS, Config.electrumDamageVsEntity, Config.damageMin, Config.damageMax, "");
        Config.electrumEnchantability = configuration.getInt("electrumEnchantability", Categories.SPECIFIC_TOOLS, Config.electrumEnchantability, Config.enchantabilityMin, Config.enchantabilityMax, "");

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