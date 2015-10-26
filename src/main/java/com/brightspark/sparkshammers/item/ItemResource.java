package com.brightspark.sparkshammers.item;

import com.brightspark.sparkshammers.SparksHammersCreativeTab;
import com.brightspark.sparkshammers.reference.Reference;
import net.minecraft.item.Item;

public class ItemResource extends Item
{
    public ItemResource(String name)
    {
        this(name, null);
    }

    public ItemResource(String name, String modName)
    {
        setUnlocalizedName(name);
        setMaxStackSize(64);
        setCreativeTab(SparksHammersCreativeTab.SH_TAB);
        if(modName == null)
            setTextureName(Reference.ITEM_TEXTURE_DIR + name);
        else
            setTextureName(Reference.ITEM_TEXTURE_DIR + modName + "/" + name);
    }
}
