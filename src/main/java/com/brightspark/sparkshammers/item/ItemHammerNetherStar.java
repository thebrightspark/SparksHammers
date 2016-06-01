package com.brightspark.sparkshammers.item;

import com.brightspark.sparkshammers.SparksHammers;
import com.brightspark.sparkshammers.reference.Config;
import com.brightspark.sparkshammers.reference.Materials;
import com.brightspark.sparkshammers.reference.Names;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemHammerNetherStar extends ItemHammer
{
    private final int tickDelay = 2;
    private static int maxMine = Config.netherStarHammerDistance;
    //private int startX, startY, startZ;
    private BlockPos startPos;
    private EnumFacing mineDir;

    public ItemHammerNetherStar()
    {
        super(Names.Items.HAMMER_NETHERSTAR, Materials.HAMMER_NETHERSTAR);
    }

    //TODO: Temp until have it's own texture!
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }

    public boolean hitEntity(ItemStack stack, EntityLivingBase entityHit, EntityLivingBase player)
    {
        //Damages hammer for 1 tunnel mining use
        stack.damageItem(1, entityHit);
        return true;
    }

    //Overriding this to stop item damage which is handled
    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, BlockPos pos, EntityLivingBase player)
    {
        return true;
    }

    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player)
    {
        return !SparksHammers.blockEV.canPlayerMine(player) || super.onBlockStartBreak(stack, pos, player);
    }
}
