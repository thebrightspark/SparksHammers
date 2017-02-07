package com.brightspark.sparkshammers.item;

import com.brightspark.sparkshammers.reference.Names;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemHammerNetherStar extends ItemAOE
{
    public ItemHammerNetherStar()
    {
        super(Names.EnumMaterials.NETHERSTAR);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase entityHit, EntityLivingBase player)
    {
        //Damages hammer for 1 tunnel mining use
        stack.damageItem(1, entityHit);
        return true;
    }

    //Overriding this to stop item damage which is handled elsewhere
    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving)
    {
        return true;
    }
}
