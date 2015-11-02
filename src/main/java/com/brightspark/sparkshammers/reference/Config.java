package com.brightspark.sparkshammers.reference;

public class Config
{
    /**
     * Whether to add tools made from other mod materials into the game.
     */
    public static boolean includeOtherModItems = true;
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
}
