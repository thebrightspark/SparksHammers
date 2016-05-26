package com.brightspark.sparkshammers.item;

import com.brightspark.sparkshammers.reference.Materials;
import com.brightspark.sparkshammers.reference.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;

/**
 * Created by Mark on 25/05/2016.
 */
public class ItemHammerMini extends ItemHammer
{
    public ItemHammerMini()
    {
        super(Names.Items.HAMMER_MINI, Materials.HAMMER_MINI);
        setMineWidth(0);
    }

    public boolean onBlockStartBreak (ItemStack stack, BlockPos pos, EntityPlayer player)
    {
        if(player.isSneaking())
        {
            //Mine horizontally
            setMineWidth(1);
            setMineHeight(0);
            boolean r = super.onBlockStartBreak(stack, pos, player);
            //Set back to normal after
            setMineWidth(0);
            setMineHeight(1);
            return r;
        }
        else
            return super.onBlockStartBreak(stack, pos, player);
    }
}
