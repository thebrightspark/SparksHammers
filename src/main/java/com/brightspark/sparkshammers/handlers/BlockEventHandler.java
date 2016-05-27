package com.brightspark.sparkshammers.handlers;

import com.brightspark.sparkshammers.item.ItemHammerNetherStar;
import com.brightspark.sparkshammers.reference.Config;
import com.brightspark.sparkshammers.util.CommonUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

import java.util.ArrayList;

/**
 * Created by Mark on 27/05/2016.
 */
public class BlockEventHandler
{
    /**
     * Object content:
     * 0: EntityPlayer -> player which mined the blocks
     * 1: ItemStack -> hammer being used
     * 2: EnumFacing -> mining direction
     * 3: BlockPos -> hit block position
     * 4: Integer -> current iteration
     */
    private ArrayList<Object[]> miningSchedule = new ArrayList<Object[]>();
    private static final int tickDelay = 2;

    private Object[] getPlayerMining(EntityPlayer player)
    {
        for(Object[] o : miningSchedule)
            if(((EntityPlayer)o[0]).equals(player))
                return o;
        return null;
    }

    private int getPlayerMiningIndex(EntityPlayer player)
    {
        return miningSchedule.indexOf(getPlayerMining(player));
    }

    //This will stop the player from mining using the Nether Star hammer if already mining.
    //Otherwise it'll add a new mining instance for the onWorldTick to dig out.
    @SubscribeEvent
    public void onBlockBreak(BreakEvent event)
    {
        Item heldItem = event.getPlayer().getHeldItem().getItem();
        if(heldItem instanceof ItemHammerNetherStar)
        {
            Object[] playerMining = getPlayerMining(event.getPlayer());
            if(playerMining == null)
            {
                //Player isn't mining yet, so we'll start mining
                EntityPlayer player = event.getPlayer();
                miningSchedule.add(new Object[] {
                        player,
                        player.getHeldItem(),
                        ((ItemHammerNetherStar) heldItem).getMovingObjectPositionFromPlayer(event.world, player, false).sideHit,
                        event.pos,
                        new Integer(0)});
                return;
            }

            //At this point, player is currently mining, so we'll cancel the block breaking
            event.setCanceled(true);
        }
    }

    //Iterates through the mining schedule and digs as necessary
    @SubscribeEvent
    public void onWorldTick(WorldTickEvent event)
    {
        if(miningSchedule.size() > 0)
        {
            for(Object[] o : miningSchedule) //TODO: Getting error here: java.util.ConcurrentModificationException
            {
                ItemStack stack = (ItemStack) o[1];
                ItemHammerNetherStar hammer = (ItemHammerNetherStar) stack.getItem();
                int mineWidth = hammer.getMineWidth();
                int mineHeight = hammer.getMineHeight();
                EnumFacing miningDir = (EnumFacing) o[2];
                int iteration = ((Integer) o[4]).intValue();
                BlockPos centerPos = ((BlockPos) o[3]).offset(miningDir, iteration);
                BlockPos start, end;

                //Block destroyed, now for AOE
                switch (miningDir) {
                    case DOWN:
                    case UP:
                        start = centerPos.add(-mineHeight, 0, -mineWidth);
                        end = centerPos.add(mineHeight, 0, mineWidth);
                    case NORTH:
                    case SOUTH:
                        //Z axis
                        start = centerPos.add(-mineWidth, -mineHeight, 0);
                        end = centerPos.add(mineWidth, mineHeight, 0);
                        break;
                    case WEST:
                    case EAST:
                    default:
                        //X axis
                        start = centerPos.add(0, -mineHeight, -mineWidth);
                        end = centerPos.add(0, mineHeight, mineWidth);
                        break;
                }

                //Break the blocks
                CommonUtils.breakArea(stack, (EntityPlayer) o[0], centerPos, start, end);

                if(++iteration > Config.netherStarHammerDistance)
                    miningSchedule.remove(o);
                else
                    //Increase the iteration
                    o[4] = iteration;
            }
        }
    }

    //This will be used to check if the players currently mining, and will remove the mining for the player.
    @SubscribeEvent
    public void onPlayerLogout(PlayerLoggedOutEvent event)
    {
        int miningIndex = getPlayerMiningIndex(event.player);
        if(miningIndex == -1) return;
        miningSchedule.remove(miningIndex);
    }
}
