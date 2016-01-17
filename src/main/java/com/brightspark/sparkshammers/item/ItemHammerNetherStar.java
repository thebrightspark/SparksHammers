package com.brightspark.sparkshammers.item;

import com.brightspark.sparkshammers.reference.Config;
import com.brightspark.sparkshammers.reference.Materials;
import com.brightspark.sparkshammers.reference.Names;
import com.brightspark.sparkshammers.reference.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemHammerNetherStar extends ItemHammer
{
    private static boolean isMining = false;
    private static int ticks = 0;
    private static int tickDelay = 2;
    private static int maxMine = Config.netherStarHammerDistance;
    private static int startX, startY, startZ;

    public ItemHammerNetherStar()
    {
        super(Materials.HAMMER_NETHERSTAR);
        setUnlocalizedName(Names.Items.HAMMER_NETHERSTAR);
        setTextureName(Reference.ITEM_TEXTURE_DIR + Names.Items.HAMMER_IRON);
    }

    public boolean onBlockStartBreak (ItemStack stack, int x, int y, int z, EntityPlayer player)
    {
        if(!isMining)
        {
            boolean toReturn = super.onBlockStartBreak(stack, x, y, z, player);
            if(toReturn)
            {
                isMining = true;
                startX = x;
                startY = y;
                startZ = z;
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
