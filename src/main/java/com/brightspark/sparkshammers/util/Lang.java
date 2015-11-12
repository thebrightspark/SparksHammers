package com.brightspark.sparkshammers.util;

import com.brightspark.sparkshammers.reference.Reference;
import net.minecraft.util.StatCollector;

public class Lang
{
    public static final String prefix = Reference.MOD_ID + ".";

    public static String localize(String s)
    {
        return localize(s, true);
    }

    public static String localize(String s, boolean appendPre)
    {
        if(appendPre)
        {
            s = prefix + s;
        }
        return StatCollector.translateToLocal(s);
    }
}
