package com.brightspark.sparkshammers.item;

import com.brightspark.sparkshammers.SparksHammersCreativeTab;
import com.brightspark.sparkshammers.reference.Reference;
import net.minecraft.item.Item;

public class ItemResource extends Item
{
    public ItemResource(String name)
    {
        setUnlocalizedName(name);
        setTextureName(Reference.ITEM_TEXTURE_DIR + name);
        setMaxStackSize(64);
        setCreativeTab(SparksHammersCreativeTab.SH_TAB);
    }
}
