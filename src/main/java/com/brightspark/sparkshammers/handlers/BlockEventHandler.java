package com.brightspark.sparkshammers.handlers;

import com.brightspark.sparkshammers.item.ItemHammerNetherStar;
import com.brightspark.sparkshammers.reference.Config;
import com.brightspark.sparkshammers.util.CommonUtils;
import com.brightspark.sparkshammers.util.LogHelper;
import com.brightspark.sparkshammers.util.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

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
     * 4: Float -> block strength
     * 5: Integer -> current iteration
     * 6: Byte -> used to make sure iteration happens on client and server
     */
    private ArrayList<Object[]> miningSchedule;
    private boolean isMining;
    private int tickDelay;
    private static final int tickDelayMax = 5;

    public BlockEventHandler()
    {
        isMining = false;
        tickDelay = 0;
        miningSchedule = new ArrayList<Object[]>();
    }

    public boolean canPlayerMine(EntityPlayer player)
    {
        return getPlayerMining(player) == null;
    }

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
        ItemStack heldStack = event.getPlayer().getHeldItem();
        Item heldItem = heldStack.getItem();
        if(heldItem instanceof ItemHammerNetherStar)
        {
            Object[] playerMining = getPlayerMining(event.getPlayer());
            if(playerMining == null)
            {
                LogHelper.info("Starting new mining!");
                //Player isn't mining yet, so we'll start mining
                EntityPlayer player = event.getPlayer();
                //Add a NBT tag to the hammer for future checks
                NBTHelper.setBoolean(heldStack, "mining", true);
                miningSchedule.add(new Object[] {
                        player,
                        ItemStack.copyItemStack(heldStack),
                        ((ItemHammerNetherStar) heldItem).getMovingObjectPositionFromPlayer(event.world, player, false).sideHit.getOpposite(),
                        event.pos,
                        new Float(ForgeHooks.blockStrength(event.state, player, player.worldObj, event.pos)),
                        new Integer(1),
                        new Byte((byte)2)});
                heldStack.damageItem(1, player);
                return;
            }
            else if(!NBTHelper.hasTag(heldStack, "mining"))
            {
                //LogHelper.info("Already mining! Cancelling block break.");
                event.setCanceled(true);
                return;
            }
        }
    }

    //Iterates through the mining schedule and digs as necessary
    @SubscribeEvent
    public void onTick(TickEvent event)
    {
        //Counts up the tick delay
        //if(miningSchedule.size() > 0 && event.type == TickEvent.Type.SERVER && event.phase == TickEvent.Phase.END)
        //    tickDelay++;

        //if(event.phase == TickEvent.Phase.END && (event.type == TickEvent.Type.CLIENT || event.type == TickEvent.Type.SERVER))
        //    LogHelper.info(event.type.name());

        //Mines if possible
        if(!isMining && miningSchedule.size() > 0 && (event.type == TickEvent.Type.CLIENT || event.type == TickEvent.Type.SERVER) && event.phase == TickEvent.Phase.END)
        {
            isMining = true;
            //tickDelay = 0;
            for(int i = 0; i < miningSchedule.size(); i++)
            {
                Object[] o = miningSchedule.get(i);
                byte num = ((Byte) o[6]).byteValue();
                if(num == 2 && event.type == TickEvent.Type.CLIENT)
                    num--;
                else if(num == 1 && event.type == TickEvent.Type.SERVER)
                    num--;
                else
                    continue;
                o[6] = num;

                ItemStack stack = (ItemStack) o[1];
                ItemHammerNetherStar hammer = (ItemHammerNetherStar) stack.getItem();
                EnumFacing miningDir = (EnumFacing) o[2];
                int iteration = ((Integer) o[5]).intValue();
                LogHelper.info("Mining iteration " + iteration + " for hole " + i);
                BlockPos centerPos = ((BlockPos) o[3]).offset(miningDir, iteration);
                BlockPos[] positions = CommonUtils.getBreakArea(hammer, centerPos, miningDir.getOpposite(), (EntityPlayer) o[0]);
                BlockPos start = positions[0];
                BlockPos end = positions[1];

                //Break the blocks
                //LogHelper.info("Breaking from " + start.toString() + " to " + end.toString());
                CommonUtils.breakArea(stack, (EntityPlayer) o[0], ((Float) o[4]).floatValue(), start, end);

                //LogHelper.info("Finished mining");

                //Make sure block breaking has happened on client and server first
                if(num <= 0)
                {
                    if(++iteration > Config.netherStarHammerDistance)
                    {
                        NBTHelper.removeTag(stack, "mining");
                        miningSchedule.remove(o);
                    }
                    else
                    {
                        //Increase the iteration
                        o[5] = iteration;
                        o[6] = (byte) 2;
                        miningSchedule.set(i, o);
                    }
                }
            }
            isMining = false;
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
