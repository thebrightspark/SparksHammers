package com.brightspark.sparkshammers.block;

import com.brightspark.sparkshammers.SparksHammers;
import com.brightspark.sparkshammers.reference.Names;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockHammerCraft extends Block
{
    public BlockHammerCraft()
    {
        super(Material.rock);
        setUnlocalizedName(Names.Blocks.HAMMER_CRAFT);
        setCreativeTab(SparksHammers.SH_TAB);
        setHardness(2f);
        setResistance(10f);
    }

    //Return 3 for standard block models
    public int getRenderType()
    {
        return 3;
    }

    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if(world.isRemote)
            return true;
        //Open crafting gui
        if(!player.isSneaking())
            player.openGui(SparksHammers.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
}
