package com.brightspark.sparkshammers.handlers;

import com.brightspark.sparkshammers.item.ItemHammerNetherStar;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class MiningObject
{
    public World world;
    public EntityPlayer player;
    public ItemStack stackActual, stackCopy;
    public EnumFacing facing;
    public BlockPos hitPos;
    public float blockStrength;
    public int iteration;

    public MiningObject(EntityPlayer player, BlockPos hitPos, IBlockState state)
    {
        world = player.worldObj;
        this.player = player;
        stackActual = player.getHeldItemMainhand();
        stackCopy = stackActual.copy();
        facing = ((ItemHammerNetherStar) stackActual.getItem()).rayTrace(world, player, false).sideHit.getOpposite();
        this.hitPos = hitPos;
        blockStrength = ForgeHooks.blockStrength(state, player, player.worldObj, hitPos);
        iteration = 1;
    }

    public BlockPos getCenterPos()
    {
        return hitPos.offset(facing, iteration);
    }
}
