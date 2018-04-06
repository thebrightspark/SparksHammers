package brightspark.sparkshammers;

import net.minecraftforge.common.config.Config;

@Config(modid = Reference.MOD_ID, name = Reference.MOD_ID + "/config")
@Config.LangKey(Reference.MOD_ID + ".title")
public class SHConfig
{
    @Config.Comment("Set this to true to enable right clicking with tools to place torches from the player's inventory")
    public static boolean rightClickPlacesTorches = false;

    @Config.Comment("The depth which the Nether Star Hammer will destroy")
    @Config.RangeInt(min = 1)
    public static int netherStarHammerDistance = 16;

    @Config.Comment("Powered Hammer configs")
    public static final Powered POWERED = new Powered();

    @Config.Comment("Mjolnir configs")
    public static final Mjolnir MJOLNIR = new Mjolnir();

    public static class Powered
    {
        @Config.Comment("The energy capacity of powered tools")
        @Config.RequiresMcRestart
        public int poweredEnergyCapacity = 100000;

        @Config.Comment("How much energy powered tools will use per block destroyed (Hitting entities will use x2 this amount)")
        @Config.RequiresMcRestart
        public int poweredEnergyUsePerBlock = 100;

        @Config.Comment("The rate at which you can fill the energy of powered tools, in energy per tick")
        @Config.RequiresMcRestart
        public int poweredEnergyInputRate = 10000;
    }

    public static class Mjolnir
    {
        @Config.Comment("Whether shrine structures should be generated in the world to find Mjolnir in")
        @Config.RequiresMcRestart
        public boolean shouldGenerateMjolnirShrines = true;

        @Config.Comment("Chance of a shrine spawning (Higher is less chance)")
        public int mjolnirShrineRarity = 50;

        @Config.Comment("Minimum Y coordinate value for the shrine to spawn at")
        public int mjolnirShrineMinY = 125;

        @Config.Comment("When true, a log will be printed in the console every time a shrine is generated with it's coordinates")
        public boolean mjolnirShrineDebug = false;

        @Config.Comment("Whether Mjolnir needs the player to have the \"end/kill_dragon\" advancement to be 'worthy'")
        public boolean mjolnirPickupNeedsDragonAchieve = true;
    }
}
