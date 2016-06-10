package com.brightspark.sparkshammers.item;

import com.brightspark.sparkshammers.SparksHammers;
import net.minecraft.item.Item;

public class ItemResource extends Item
{
    public ItemResource(String name)
    {
        setUnlocalizedName(name);
        setMaxStackSize(64);
        setCreativeTab(SparksHammers.SH_TAB);
    }
}
