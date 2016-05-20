package com.brightspark.sparkshammers.item;

import com.brightspark.sparkshammers.init.SHBlocks;
import com.brightspark.sparkshammers.reference.Materials;
import com.brightspark.sparkshammers.reference.Names;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class temHammerThor extends ItemHammer
{
    public temHammerThor()
    {
        super(Names.Items.HAMMER_THOR, Materials.HAMMER_DIAMOND, true);
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        //Place hammer in the world and remove item from inventory

        IBlockState blockHitState = world.getBlockState(pos);
        Block blockHit = blockHitState.getBlock();

        //Most of the following general stuff is taken from ItemReed.java
        //noinspection all
        if (blockHit == Blocks.snow_layer && blockHitState.getValue(BlockSnow.LAYERS).intValue() < 1)
        {
            side = EnumFacing.UP;
        }
        else if (!blockHit.isReplaceable(world, pos))
        {
            pos = pos.offset(side);
        }

        if (!player.canPlayerEdit(pos, side, stack))
        {
            return false;
        }
        else if (stack.stackSize == 0)
        {
            return false;
        }
        else if (world.canBlockBePlaced(SHBlocks.blockHammer, pos, false, side, null, stack))
        {
            //Place hammer block
            IBlockState hammerState = SHBlocks.blockHammer.onBlockPlaced(world, pos, side, hitX, hitY, hitZ, 0, player);
            if (world.setBlockState(pos, SHBlocks.blockHammer.getDefaultState())) //Returns true if block placed successfully
            {
                hammerState.getBlock().onBlockPlacedBy(world, pos, hammerState, player, stack);

                //Remove hammer item from inventory if not in Creative
                if(!player.capabilities.isCreativeMode)
                    --stack.stackSize;
                return true;
            }
        }
        return false;
    }
}
