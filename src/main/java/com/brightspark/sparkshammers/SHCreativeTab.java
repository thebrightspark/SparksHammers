package com.brightspark.sparkshammers;

import com.brightspark.sparkshammers.init.SHItems;
import com.brightspark.sparkshammers.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class SHCreativeTab
{
    public static final CreativeTabs SH_TAB = new CreativeTabs(Reference.MOD_ID)
    {
        @Override
        public Item getTabIconItem()
        {
            return SHItems.hammerDiamond;
        }

        @Override
        public String getTranslatedTabLabel()
        {
            return Reference.MOD_NAME;
        }
    };
}
