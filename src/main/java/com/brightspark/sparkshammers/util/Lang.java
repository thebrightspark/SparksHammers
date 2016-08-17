package com.brightspark.sparkshammers.util;

import com.brightspark.sparkshammers.reference.Reference;
import net.minecraft.client.resources.I18n;

public class Lang
{
    public static final String prefix = Reference.MOD_ID + ".";

    public static String localize(String s)
    {
        return localize(s, false);
    }

    public static String localize(String s, boolean appendPre)
    {
        if(appendPre)
            s = prefix + s;
        return I18n.format(s);
    }
}
