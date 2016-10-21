package com.brightspark.sparkshammers.reference;

import java.util.UUID;

public class Reference
{
    //TODO: In 1.11, change this to "sparkshammers"
    public static final String MOD_ID = "SparksHammers";
    public static final String MOD_NAME = "Spark's Hammers";
    public static final String VERSION = "1.10.2-1.4";
    public static final String DEPENDENCIES =
            //"after:EnderIO;" +
            "after:Botania;" +
            "after:MobHunter;" +
            "after:JEI";

    public static final String ITEM_TEXTURE_DIR = MOD_ID + ":";
    public static final String GUI_TEXTURE_DIR = "textures/gui/";

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
