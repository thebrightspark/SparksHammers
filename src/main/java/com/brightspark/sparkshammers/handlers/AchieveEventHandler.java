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

/**
 * Created by Mark on 09/06/2016.
 */
public class AchieveEventHandler
{
    @SubscribeEvent
    public void onCrafting(PlayerEvent.ItemCraftedEvent event)
    {
        Item item = event.crafting.getItem();
        if(item instanceof ItemHammer)
        {
            if(item.equals(SHItems.hammerWood))
                event.player.triggerAchievement(SHAchievements.woodHammer);
            else if(item.equals(SHItems.hammerDiamond))
                event.player.triggerAchievement(SHAchievements.diamondHammer);
        }
        else if(item.equals(Item.getItemFromBlock(SHBlocks.blockHammerCraft)))
            event.player.triggerAchievement(SHAchievements.craftingTable);
    }

    @SubscribeEvent
    public void onPickup(PlayerEvent.ItemPickupEvent event)
    {
        ItemStack stack = event.pickedUp.getEntityItem();
        if(stack.getItem() instanceof ItemHammerThor && !ItemHammerThor.isOwner(stack, event.player))
            event.player.triggerAchievement(SHAchievements.mjolnirNope);
    }

    @SubscribeEvent
    public void onDeath(PlayerDropsEvent event)
    {
        if(event.source.getDamageType().equals(SparksHammers.fallingHammer.getDamageType()))
            event.entityPlayer.triggerAchievement(SHAchievements.mjolnirFallDeath);
    }
}
