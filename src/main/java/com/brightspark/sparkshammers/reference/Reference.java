package com.brightspark.sparkshammers.reference;

public class Reference
{
    public static final String MOD_ID = "SparksHammers";
    public static final String MOD_NAME = "Spark's Hammers";
    public static final String VERSION = "1.7.10-1.3";
    public static final String CLIENT_PROXY_CLASS = "com.brightspark.sparkshammers.proxy.ClientProxy";
    public static final String SERVER_PROXY_CLASS = "com.brightspark.sparkshammers.proxy.ServerProxy";
    public static final String DEPENDENCIES =
                    "after:" + Names.Mods.BOTANIA + ";" +
                    "after:" + Names.Mods.EXTRA_UTILITIES + ";" +
                    "after:" + Names.Mods.RANDOM_THINGS + ";" +
                    "after:" + Names.Mods.ENDERIO + ";" +
                    "after:" + Names.Mods.IC2;

    public static final String ITEM_TEXTURE_DIR = MOD_ID + ":";
    public static final String GUI_TEXTURE_DIR = ITEM_TEXTURE_DIR + "textures/gui/";

    public static int HAMMER_RENDER_ID;
}
