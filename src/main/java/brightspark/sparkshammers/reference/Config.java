package brightspark.sparkshammers.reference;

public class Config
{
    /**
     * Whether to add tools made from other mod materials into the game.
     */
    public static boolean enableOtherModItems = true;
    public static boolean enableMjolnir = true;
    public static boolean enableMiniHammer = true;
    public static boolean enableGiantHammer = true;
    public static boolean enableNetherStarHammer = true;
    public static boolean enablePoweredHammer = true;

    /**
     * Modifier for hammers and excavators to adjust durability.
     */
    public static float toolDurabilityModifier = 1f;
    public static final float toolDurabilityModifierMin = 0.1f;
    public static final float toolDurabilityModifierMax = 10f;
    /**
     * Modifier for hammers and excavators to adjust mining speed.
     */
    public static float toolSpeedModifier = 1f;
    public static final float toolSpeedModifierMin = 0.1f;
    public static final float toolSpeedModifierMax = 10f;
    /**
     * Modifier for hammers and excavators to adjust attack damage.
     */
    public static float toolAttackModifier = 1f;
    public static final float toolAttackModifierMin = 0.1f;
    public static final float toolAttackModifierMax = 10f;

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

    /**
     * Whether Mjolnir needs the player to have the "End." achievement to be 'worthy'.
     */
    public static boolean mjolnirPickupNeedsDragonAchieve = true;

    /*
     * Powered Tools' Energy
     */
    public static int poweredEnergyCapacity = 100000;
    public static int poweredEnergyUsePerBlock = 100;
    public static int poweredEnergyInputRate = 10000;
}
