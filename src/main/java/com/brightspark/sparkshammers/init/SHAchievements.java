package com.brightspark.sparkshammers.init;

import com.brightspark.sparkshammers.reference.Reference;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class SHAchievements
{
    public static Achievement woodHammer = new Achievement("achievement.woodHammer", "woodHammer", 0, -2, SHItems.getItemById("hammerWood"), null);
    public static Achievement craftingTable = new Achievement("achievement.craftingTable", "craftingTable", 0, 0, SHBlocks.blockHammerCraft, woodHammer);
    public static Achievement diamondHammer = new Achievement("achievement.diamondHammer", "diamondHammer", 0, 2, SHItems.getItemById("hammerDiamond"), craftingTable);
    public static Achievement netherStarHammer = new Achievement("achievement.netherStarHammer", "netherStarHammer", 0, 4, SHItems.hammerNetherStar, diamondHammer);
    public static Achievement mjolnir = new Achievement("achievement.mjolnir", "mjolnir", -2, -1, SHItems.hammerThor, null);
    public static Achievement mjolnirNope = new Achievement("achievement.mjolnirNope", "mjolnirNope", -2, 0, SHItems.hammerThor, null);
    public static Achievement mjolnirFallDeath = new Achievement("achievement.mjolnirFallDeath", "mjolnirFallDeath", -2, 1, SHItems.hammerThor, null);

    public static void init()
    {
        woodHammer.registerStat();
        craftingTable.registerStat();
        diamondHammer.registerStat();
        mjolnir.registerStat();
        mjolnirNope.registerStat();
        mjolnirFallDeath.registerStat();
        netherStarHammer.registerStat();

        AchievementPage.registerAchievementPage(new AchievementPage(Reference.MOD_ID,
                woodHammer, craftingTable, diamondHammer, netherStarHammer,
                mjolnir, mjolnirNope, mjolnirFallDeath));
    }
}
