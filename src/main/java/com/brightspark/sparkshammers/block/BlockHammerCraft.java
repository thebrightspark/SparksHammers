package com.brightspark.sparkshammers.block;

import com.brightspark.sparkshammers.SparksHammers;
import com.brightspark.sparkshammers.reference.Names;
import com.brightspark.sparkshammers.util.CommonUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockHammerCraft extends Block
{
    public BlockHammerCraft()
    {
        super(Material.ROCK);
        setUnlocalizedName(Names.Blocks.HAMMER_CRAFT);
        setCreativeTab(SparksHammers.SH_TAB);
        setHardness(2f);
        setResistance(10f);
        setRegistryName(Names.Blocks.HAMMER_CRAFT);
    }

    //Return 3 for standard block models
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if(world.isRemote)
            return true;
        //Open crafting gui
        if(!player.isSneaking())
            CommonUtils.openGui(world, player, pos);
        return true;
    }
}
