package com.brightspark.sparkshammers.handlers;

import com.brightspark.sparkshammers.init.SHItems;
import com.brightspark.sparkshammers.reference.Reference;
import net.minecraft.item.Item;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LootEventHandler
{
    private void addLoot(LootPool pool, Item item, int weight)
    {
        pool.addEntry(new LootEntryItem(item, weight, 0, new LootFunction[0], new LootCondition[0], item.getRegistryName().toString()));
    }

    @SubscribeEvent
    public void onLootLoad(LootTableLoadEvent event)
    {
        if(event.getName().equals(LootTableList.CHESTS_ABANDONED_MINESHAFT))
        {
            LootPool pool = event.getTable().getPool("main");
            addLoot(pool, SHItems.getItemById("hammer_wood"), 1);
            addLoot(pool, SHItems.getItemById("hammer_stone"), 1);
            //addLoot(pool, SHItems.excavatorWood, 1);
            //addLoot(pool, SHItems.excavatorStone, 1);
        }
    }
}
