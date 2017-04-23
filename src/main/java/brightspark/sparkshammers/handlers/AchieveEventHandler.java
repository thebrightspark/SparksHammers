package brightspark.sparkshammers.handlers;

import brightspark.sparkshammers.SparksHammers;
import brightspark.sparkshammers.init.SHAchievements;
import brightspark.sparkshammers.item.ItemHammerMjolnir;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

@Mod.EventBusSubscriber
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
