package com.brightspark.sparkshammers.util;

import com.brightspark.sparkshammers.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.ItemModelMesherForge;

/**
 * Using this class to collect methods which are common across at least 2 classes to help repetitive code.
 */
public class ClientUtils
{
    private static ItemModelMesherForge m = (ItemModelMesherForge) Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
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
        String itemName = item.getUnlocalizedName();
        m.register(item, meta, new ModelResourceLocation(Reference.ITEM_TEXTURE_DIR + itemName.substring(itemName.indexOf(".") + 1), "inventory"));
        //ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
