package com.brightspark.sparkshammers.handlers;

import com.brightspark.sparkshammers.SparksHammers;
import com.brightspark.sparkshammers.init.SHAchievements;
import com.brightspark.sparkshammers.init.SHBlocks;
import com.brightspark.sparkshammers.init.SHItems;
import com.brightspark.sparkshammers.item.ItemHammer;
import com.brightspark.sparkshammers.item.ItemHammerThor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class AchieveEventHandler
{
    @SubscribeEvent
    public void onCrafting(PlayerEvent.ItemCraftedEvent event)
    {
        Item item = event.crafting.getItem();
        if(item.equals(Item.getItemFromBlock(SHBlocks.blockHammerCraft)))
            event.player.addStat(SHAchievements.craftingTable);
    }

    @SubscribeEvent
    public void onPickup(PlayerEvent.ItemPickupEvent event)
    {
        ItemStack stack = event.pickedUp.getEntityItem();
        if(stack.getItem() instanceof ItemHammerThor && !ItemHammerThor.isOwner(stack, event.player))
            event.player.addStat(SHAchievements.mjolnirNope);
    }

    @SubscribeEvent
    public void onDeath(PlayerDropsEvent event)
    {
        if(event.getSource().getDamageType().equals(SparksHammers.fallingHammer.getDamageType()))
            event.getEntityPlayer().addStat(SHAchievements.mjolnirFallDeath);
    }
}
