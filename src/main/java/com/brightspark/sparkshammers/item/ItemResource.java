package com.brightspark.sparkshammers.item;

import com.brightspark.sparkshammers.SHCreativeTab;
import com.brightspark.sparkshammers.util.SHModelResourceLocation;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class ItemResource extends Item implements IHasModel
{
    private ModelResourceLocation model;

    public ItemResource(String name)
    {
        this(name, null);
    }

    public ItemResource(String name, String modName)
    {
        setUnlocalizedName(name);
        setMaxStackSize(64);
        setCreativeTab(SHCreativeTab.SH_TAB);
        if(modName == null)
            model = new SHModelResourceLocation(name);
        else
            model = new SHModelResourceLocation(modName + "/" + name);
    }

    public ModelResourceLocation getModel()
    {
        return model;
    }
}
