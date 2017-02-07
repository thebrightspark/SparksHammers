package com.brightspark.sparkshammers.reference;

import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

public class Config
{
    /**
     * Whether to add tools made from other mod materials into the game.
     */
    public static boolean includeOtherModItems = true;
    /**
     * If set to true, will use old 'easier' recipe using a vanilla crafting table for the unstable hammer and excavator.
     * Else, set to false will keep the hammer crafting table recipe (which requires mobius ingots rather than unstable).
     */
    //public static boolean useEasyUnstableRecipe = false;
    /**
     * Modifier for hammers and excavators to adjust durability.
     */
    public static float toolDurabilityModifier = 6f;
    public static final float toolDurabilityModifierMin = 1f;
    public static final float toolDurabilityModifierMax = 10f;
    /**
     * Modifier for hammers and excavators to adjust mining speed.
     */
    public static float toolSpeedModifier = 0.6f;
    public static final float toolSpeedModifierMin = 0.1f;
    public static final float toolSpeedModifierMax = 2f;
    /**
     * Modifier for hammers and excavators to adjust attack damage.
     */
    public static float toolAttackModifier = 1f;
    public static final float toolAttackModifierMin = 0.1f;
    public static final float toolAttackModifierMax = 10f;

    /**
     * Durability of the Nether Star made hammer
     */
    public static int netherStarHammerDurability = 10;
    public static final int netherStarHammerDurabilityMin = 1;
    public static final int netherStarHammerDurabilityMax = Short.MAX_VALUE/2;
    /**
     * Max mining distance of the Nether Star made hammer
     */
    public static int netherStarHammerDistance = 16;
    public static final int netherStarHammerDistanceMin = 3;
    public static final int netherStarHammerDistanceMax = 100;

    /**
     * Whether shrine structures should be generated in the world to find Mjolnir in.
     */
    public static boolean shouldGenerateMjolnirShrines = true;
    public static int mjolnirShrineRarity = 50;
    public static int mjolnirShrineMinY = 125;
    public static boolean mjolnirShrineDebug = false;

    /*
     * Mjolnir
     */
    public static int mjolnirHarvestLevel = Integer.MAX_VALUE;
    public static float mjolnirEfficiency = 10f;
    public static float mjolnirDamageVsEntity = 10f;
    public static boolean mjolnirPickupNeedsDragonAchieve = true;

    /*
     * Powered Tools' Energy
     */
    public static int poweredEnergyCapacity = 100000;
    public static int poweredEnergyUsePerBlock = 100;
    public static int poweredEnergyInputRate = 10000;

    /*
     * Common mod metals
     */

    /**
     * Create and returns the material currently saved as a double array as a tool material.
     */
    public static Item.ToolMaterial getAsToolMaterial(String name, double[] material)
    {
        return EnumHelper.addToolMaterial(name, (int) material[0], (int) material[1], (float) material[2], (float) material[3], (int) material[4]);
    }

    /**
     * Initialises the materials later after the tool modifiers have been loaded from the configs.
     */
    public static void initMaterials()
    {
        //Name, harvest level, durability, mining speed, damage vs entities, enchantability
        materialCopper = new double[] {1d, 128d*toolDurabilityModifier, 4d*toolSpeedModifier, 4d, 12d};
        materialSilver = new double[] {1d, 128d*toolDurabilityModifier, 5d*toolSpeedModifier, 4.3d, 15d};
        materialTin = new double[] {1d, 32d*toolDurabilityModifier, 3d*toolSpeedModifier, 3.8d, 5d};
        materialLead = new double[] {0d, 32d*toolDurabilityModifier, 1d*toolSpeedModifier, 3.3d, 2d};
        materialNickel = new double[] {1d, 128d*toolDurabilityModifier, 4d*toolSpeedModifier, 4d, 17d};
        materialPlatinum = new double[] {1d, 160d*toolDurabilityModifier, 3d*toolSpeedModifier, 3.8d, 37d};
        materialBronze = new double[] {2d, 128d*toolDurabilityModifier, 8d*toolSpeedModifier, 5d, 11d};
        materialSteel = new double[] {2d, 480d*toolDurabilityModifier, 8d*toolSpeedModifier, 5d, 5d};
        materialInvar = new double[] {2d, 320d*toolDurabilityModifier, 9d*toolSpeedModifier, 5.3d, 7d};
        materialElectrum = new double[] {1d, 128d*toolDurabilityModifier, 5d*toolSpeedModifier, 4.3d, 25d};
        materialAluminium = new double[] {2d, 220d*toolDurabilityModifier, 12d*toolSpeedModifier, 4.8d, 14d};
        materialBrass = new double[] {2d, 96d*toolDurabilityModifier, 3.5d*toolSpeedModifier, 4.6d, 10d};

        materialOsmium = new double[] {2d, 500d*toolDurabilityModifier, 10d*toolSpeedModifier, 7d, 12d};
        materialZinc = new double[] {0d, 32d*toolDurabilityModifier, 1d*toolSpeedModifier, 3.3d, 2d};
        materialChrome = new double[] {3d, 600d*toolDurabilityModifier, 7d*toolSpeedModifier, 7d, 7d};
        //materialIridium = new double[] {5d, 2000d*toolDurabilityModifier, 10d*toolSpeedModifier, 8d, 1d};
        //materialTitanium = new double[] {4d, 1000d*toolDurabilityModifier, 8d*toolSpeedModifier, 7d, 3d};
        //materialTungsten = new double[] {4d, 1000d*toolDurabilityModifier, 8d*toolSpeedModifier, 7d, 3d};

        materialSapphire = new double[] {2d, 620d*toolDurabilityModifier, 5d*toolSpeedModifier, 5d, 8d};
        materialRuby = new double[] {2d, 320d*toolDurabilityModifier, 6.2d*toolSpeedModifier, 5.7d, 10d};
        materialPeridot = new double[] {2d, 400d*toolDurabilityModifier, 7d*toolSpeedModifier, 5.4d, 16d};
        materialObsidian = new double[] {1d, 1000d*toolDurabilityModifier, 4d*toolSpeedModifier, 3d, 5d};

        materialManasteel = new double[] {3d, 300d*toolDurabilityModifier, 6.2d*toolSpeedModifier, 5d, 20d};
        materialElementium = new double[] {3d, 720d*toolDurabilityModifier, 6.2d*toolSpeedModifier, 5d, 20d};
        materialTerrasteel = new double[] {4d, 2300d*toolDurabilityModifier, 9d*toolSpeedModifier, 6d, 26d};

        materialMachalite = new double[] {2d, 500d*toolDurabilityModifier, 7d*toolSpeedModifier, 6d, 15d};
        materialDragonite = new double[] {3d, 750d*toolDurabilityModifier, 7d*toolSpeedModifier, 7d, 15d};
        materialGossamite = new double[] {3d, 1000d*toolDurabilityModifier, 8d*toolSpeedModifier, 8d, 15d};

        materialDarksteel = new double[] {5d, 1561d*toolDurabilityModifier, 7d*toolSpeedModifier, 5d, 25d};
    }

    public static double[] materialCopper, materialSilver, materialTin, materialLead, materialNickel, materialPlatinum, materialBronze, materialSteel, materialInvar, materialElectrum, materialAluminium, materialBrass,
            materialOsmium, materialZinc, materialChrome, //materialIridium, materialTitanium, materialTungsten,
            materialSapphire, materialRuby, materialPeridot, materialObsidian,
            materialManasteel, materialElementium, materialTerrasteel,
            materialMachalite, materialDragonite, materialGossamite,
            materialDarksteel;
}
