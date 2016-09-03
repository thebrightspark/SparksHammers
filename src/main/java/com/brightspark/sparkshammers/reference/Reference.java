package com.brightspark.sparkshammers.reference;

public class Reference
{
    //TODO: In 1.11, change this to "sparkshammers"
    public static final String MOD_ID = "SparksHammers";
    public static final String MOD_NAME = "Spark's Hammers";
    public static final String VERSION = "1.10.2-1.3";
    public static final String DEPENDENCIES =
            //"after:EnderIO;" +
            "after:MobHunter;" +
            "after:JEI";

    public static final String ITEM_TEXTURE_DIR = MOD_ID + ":";
    public static final String GUI_TEXTURE_DIR = "textures/gui/";

    public class JEI
    {
        public static final String HAMMER_CRAFTING_UID = MOD_ID + ":hammerCrafting";
        public static final String HAMMER_CRAFTING_TITLE_UNLOC = "jei.recipe.hammerCraftingTable";
    }
}
