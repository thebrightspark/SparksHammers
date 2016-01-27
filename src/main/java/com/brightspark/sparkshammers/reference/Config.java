package com.brightspark.sparkshammers.reference;

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
    public static boolean useEasyUnstableRecipe = false;
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

    /*
     * Values for different Tool Materials.
     * The auto updating of Tool Materials can be turn on or off.
     * This simply, when the Tool Material for it's respective pickaxe is in the mod pack, it will check against it
     *  and update these values against it. Otherwise, it will keep what's already saved.
     */
    public static int harvestLevelMin = 0;
    public static int harvestLevelMax = 6;
    public static int maxUsesMin = 1;
    public static int maxUsesMax = Integer.MAX_VALUE;
    public static float efficiencyMin = 0f;
    public static float efficiencyMax = 100f;
    public static float damageMin = 0f;
    public static float damageMax = 100f;
    public static int enchantabilityMin = 0;
    public static int enchantabilityMax = 50;

    public static int manasteelHarvestLevel = 3;
    public static int manasteelMaxUses = (int) (300*toolDurabilityModifier);
    public static float manasteelEfficiency = 6.2f*toolSpeedModifier;
    public static float manasteelDamageVsEntity = 2f;
    public static int manasteelEnchantability = 20;

    public static int elementiumHarvestLevel = 3;
    public static int elementiumMaxUses = (int) (720*toolDurabilityModifier);
    public static float elementiumEfficiency = 6.2f*toolSpeedModifier;
    public static float elementiumDamageVsEntity = 2f;
    public static int elementiumEnchantability = 20;

    public static int terrasteelHarvestLevel = 4;
    public static int terrasteelMaxUses = (int) (2300*toolDurabilityModifier);
    public static float terrasteelEfficiency = 9f*toolSpeedModifier;
    public static float terrasteelDamageVsEntity = 3f;
    public static int terrasteelEnchantability = 26;

    public static int darksteelHarvestLevel = 5;
    public static int darksteelMaxUses = (int) (1561*toolDurabilityModifier);
    public static float darksteelEfficiency = 7f*toolSpeedModifier;
    public static float darksteelDamageVsEntity = 2f;
    public static int darksteelEnchantability = 25;

    public static int bronzeHarvestLevel = 2;
    public static int bronzeMaxUses = (int) (350*toolDurabilityModifier);
    public static float bronzeEfficiency = 6f*toolSpeedModifier;
    public static float bronzeDamageVsEntity = 2f;
    public static int bronzeEnchantability = 13;
}
