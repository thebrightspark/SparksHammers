package brightspark.sparkshammers.handlers;

import brightspark.sparkshammers.item.ItemHammerNetherStar;
import brightspark.sparkshammers.SHConfig;
import brightspark.sparkshammers.util.CommonUtils;
import brightspark.sparkshammers.util.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.Iterator;

@Mod.EventBusSubscriber
public class BlockEventHandler
{
    private static ArrayList<MiningObject> miningSchedule = new ArrayList<MiningObject>();
    private static boolean isMining = false;
    private static int tickDelay = 0;
    private static final int tickDelayMax = 2;

    private static MiningObject getPlayerMining(EntityPlayer player)
    {
        for(MiningObject o : miningSchedule)
            if(o.player.equals(player))
                return o;
        return null;
    }

    //This will stop the player from mining using the Nether Star hammer if already mining.
    //Otherwise it'll add a new mining instance for the onWorldTick to dig out.
    @SubscribeEvent
    public static void onBlockBreak(BreakEvent event)
    {
        EntityPlayer player = event.getPlayer();
        ItemStack heldStack = player.getHeldItemMainhand();
        if(heldStack.isEmpty()) return;
        Item heldItem = heldStack.getItem();
        if(heldItem instanceof ItemHammerNetherStar)
        {
            MiningObject playerMining = getPlayerMining(player);
            if(player.capabilities.isCreativeMode || playerMining == null)
            {
                //Player is in creative or isn't mining yet, so we'll start mining
                miningSchedule.add(new MiningObject(player, event.getPos(), event.getState()));
                heldStack.damageItem(1, player);
            }
        }
    }

    //This event is called after the BreakEvent, as well as Item#onBlockStartBreak, so we'll add the NBT tag here.
    @SubscribeEvent
    public static void onHarvestBlock(BlockEvent.HarvestDropsEvent event)
    {
        if(event.getHarvester() == null || event.getHarvester().capabilities.isCreativeMode) return;
        ItemStack heldStack = event.getHarvester().getHeldItemMainhand();
        if(heldStack.isEmpty() || !(heldStack.getItem() instanceof ItemHammerNetherStar)) return;

        MiningObject mining = getPlayerMining(event.getHarvester());
        if(mining != null && mining.iteration == 1)
            NBTHelper.setBoolean(mining.stackActual, "mining", true);
    }

    //Iterates through the mining schedule and digs as necessary
    @SubscribeEvent
    public static void onTick(TickEvent event)
    {
        //Mines if possible
        if(!isMining && miningSchedule.size() > 0 && event.type == TickEvent.Type.SERVER && event.phase == TickEvent.Phase.END)
        {
            //Count up the delay. If it's less than the max, then don't mine yet.
            if(tickDelay++ < tickDelayMax)
                return;
            isMining = true;
            tickDelay = 0;

            Iterator<MiningObject> miningIterator = miningSchedule.iterator();
            while(miningIterator.hasNext())
            {
                MiningObject o = miningIterator.next();

                ItemHammerNetherStar hammer = (ItemHammerNetherStar) o.stackCopy.getItem();
                BlockPos centerPos = o.getCenterPos();
                BlockPos[] positions = CommonUtils.getBreakArea(hammer, centerPos, o.facing.getOpposite(), o.player);

                //Break the blocks
                CommonUtils.breakArea(o.stackCopy, o.world, o.player, o.blockStrength, positions[0], centerPos, positions[1]);

                if(++o.iteration > SHConfig.netherStarHammerDistance)
                {
                    //If reached end of mining, then remove from mining schedule
                    if(!o.stackActual.isEmpty())
                        NBTHelper.setBoolean(o.stackActual, "mining", false);
                    miningIterator.remove();
                }
            }

            isMining = false;
        }
    }

    //This will be used to check if the players currently mining, and will remove the mining for the player.
    @SubscribeEvent
    public static void onPlayerLogout(PlayerLoggedOutEvent event)
    {
        Iterator<MiningObject> miningIterator = miningSchedule.iterator();
        while(miningIterator.hasNext())
        {
            MiningObject o = miningIterator.next();
            if(o.player.equals(event.player))
            {
                if(!o.stackActual.isEmpty())
                    NBTHelper.setBoolean(o.stackActual, "mining", false);
                miningIterator.remove();
            }
        }
    }
}
