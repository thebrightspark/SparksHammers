package com.brightspark.sparkshammers.item;

import com.brightspark.sparkshammers.reference.Config;
import com.brightspark.sparkshammers.reference.Materials;
import com.brightspark.sparkshammers.reference.Names;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ItemHammerNetherStar extends ItemHammer
{
    private boolean isMining = false;
    private int ticks = 0;
    private int tickDelay = 2;
    private static int maxMine = Config.netherStarHammerDistance;
    //private int startX, startY, startZ;
    private BlockPos startPos;

    public ItemHammerNetherStar()
    {
        super(Names.Items.HAMMER_NETHERSTAR, Materials.HAMMER_NETHERSTAR);
    }

    public boolean onBlockStartBreak (ItemStack stack, BlockPos pos, EntityPlayer player)
    {
        if(!isMining)
        {
            boolean toReturn = super.onBlockStartBreak(stack, pos, player);
            if(toReturn)
            {
                //TODO: This should really be done when the block is broken or about to be
                isMining = true;
                startPos = pos;
                //startX = x;
                //startY = y;
                //startZ = z;
            }
        }
        //Prevent harvesting if mining already in progress
        return true;
    }

    public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5)
    {
        //Check if mining and ticks is even
        if((ticks/2) == maxMine)
        {
            isMining = false;
            ticks = 0;
        }
        if(isMining && ticks > 0 && (ticks%tickDelay == 0))
        {
            //TODO: Finish Nether Star Hammer
            int i = ticks/tickDelay;
            //Mine blocks!
        }
    }
}
