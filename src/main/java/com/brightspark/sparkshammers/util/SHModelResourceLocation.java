package com.brightspark.sparkshammers.util;

import com.brightspark.sparkshammers.reference.Reference;
import net.minecraft.client.resources.model.ModelResourceLocation;

public class SHModelResourceLocation extends ModelResourceLocation
{
    public SHModelResourceLocation(String itemName)
    {
        super(Reference.ITEM_TEXTURE_DIR + itemName, "inventory");
        //LogHelper.info("Created ModelResourceLocation for " + Reference.ITEM_TEXTURE_DIR + itemName);
    }
}
