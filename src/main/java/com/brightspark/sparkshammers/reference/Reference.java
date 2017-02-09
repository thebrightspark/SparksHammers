package com.brightspark.sparkshammers.reference;

import net.minecraftforge.fml.common.Loader;

import java.io.File;
import java.util.UUID;

public class Reference
{
    public static final String MOD_ID = "sparkshammers";
    public static final String MOD_NAME = "Spark's Hammers";
    public static final String VERSION = "1.11.2-1.6.1";
    public static final String DEPENDENCIES =
            "after:enderio;" +
            "after:botania;" +
            "after:mobhunter;" +
            "after:jei";

    public static final String ITEM_TEXTURE_DIR = MOD_ID + ":";
    public static final String GUI_TEXTURE_DIR = "textures/gui/";
    public static final File CONFIG_DIR = new File(Loader.instance().getConfigDir(), MOD_ID);

    public static class JEI
    {
        public static final String HAMMER_CRAFTING_UID = MOD_ID + ":hammerCrafting";
        public static final String HAMMER_CRAFTING_TITLE_UNLOC = "jei.recipe.hammerCraftingTable";
    }

    public static class UUIDs
    {
        public static final UUID BRIGHT_SPARK = UUID.fromString("4adad317-d08b-412d-a75b-c2834386b088");
        public static final UUID _8BRICKDMG = UUID.fromString("647c557d-b494-45cf-9be9-f9774348d4c1");
    }
}
