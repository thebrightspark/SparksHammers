package com.brightspark.sparkshammers.handlers;

import com.brightspark.sparkshammers.init.SHBlocks;
import com.brightspark.sparkshammers.init.SHItems;
import com.brightspark.sparkshammers.util.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

/**
 * Created by Mark on 04/02/2017.
 */
@Mod.EventBusSubscriber
public class RegistrationHandler
{
    @SubscribeEvent
    public static void regItems(RegistryEvent.Register<Item> event)
    {
        //Register all items
        SHItems.regItems();
        IForgeRegistry<Item> registry = event.getRegistry();
        for(Item item : SHItems.ITEMS.values())
            registry.register(item);

        //Register item blocks
        SHBlocks.regBlocks();
        for(Item item : SHBlocks.ITEM_BLOCKS.values())
            registry.register(item);
    }

    @SubscribeEvent
    public static void regBlocks(RegistryEvent.Register<Block> event)
    {
        //Register all blocks
        SHBlocks.regBlocks();
        IForgeRegistry<Block> registry = event.getRegistry();
        for(Block block : SHBlocks.BLOCKS.values())
            registry.register(block);
    }
}