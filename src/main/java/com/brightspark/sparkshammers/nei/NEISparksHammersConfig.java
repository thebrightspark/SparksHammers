package com.brightspark.sparkshammers.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import com.brightspark.sparkshammers.init.SHModItems;
import com.brightspark.sparkshammers.reference.Config;
import com.brightspark.sparkshammers.reference.Reference;
import net.minecraft.item.ItemStack;

public class NEISparksHammersConfig implements IConfigureNEI
{
    @Override
    public void loadConfig()
    {
        //Register handlers and hide any unwanted items in NEI here

        //API.hideItem();

        if(!Config.useEasyUnstableRecipe)
        {
            API.hideItem(new ItemStack(SHModItems.hammerHeadUnstable));
            API.hideItem(new ItemStack(SHModItems.excavatorHeadUnstable));
        }

        API.registerRecipeHandler(new NEIHammerCraftRecipeHandler());
        API.registerUsageHandler(new NEIHammerCraftRecipeHandler());
    }

    @Override
    public String getName()
    {
        return Reference.MOD_NAME;
    }

    @Override
    public String getVersion()
    {
        return Reference.VERSION;
    }
}
