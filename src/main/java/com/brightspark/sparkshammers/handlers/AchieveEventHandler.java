package com.brightspark.sparkshammers.handlers;

import com.brightspark.sparkshammers.SparksHammers;
import com.brightspark.sparkshammers.init.SHAchievements;
import com.brightspark.sparkshammers.item.ItemHammerMjolnir;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class AchieveEventHandler
{
    @SubscribeEvent
    public void onPickup(PlayerEvent.ItemPickupEvent event)
    {
        ItemStack stack = event.pickedUp.getEntityItem();
        if(stack.getItem() instanceof ItemHammerMjolnir && ! ItemHammerMjolnir.isOwner(stack, event.player))
            event.player.addStat(SHAchievements.mjolnirNope);
    }

    @SubscribeEvent
    public void onDeath(PlayerDropsEvent event)
    {
        if(event.getSource().getDamageType().equals(SparksHammers.fallingHammer.getDamageType()))
            event.getEntityPlayer().addStat(SHAchievements.mjolnirFallDeath);
    }
}
