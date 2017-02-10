package com.brightspark.sparkshammers.util;

import com.brightspark.sparkshammers.init.SHItems;
import com.brightspark.sparkshammers.item.ItemAOE;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

/**
 * Using this class to collect methods which are common across at least 2 classes to help repetitive code.
 */
public class ClientUtils
{
    //Register a model
    public static void regModel(Item item)
    {
        regModel(item, 0);
    }
    public static void regModel(Block block)
    {
        regModel(Item.getItemFromBlock(block), 0);
    }

    //Register a model with meta
    public static void regModel(Item item, int meta)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    //Register a tool to use either hammer.json or excavator.json so I don't need a file for every tool
    public static void regTool(ItemAOE item)
    {
        String itemName = item.getRegistryName().getResourceDomain() + ":" + (item.isExcavator ? "excavator" : "hammer");
        if(item == SHItems.hammerGiant)
            itemName += "Giant";
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(itemName, "inventory"));
    }
}
