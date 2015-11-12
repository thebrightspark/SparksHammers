package com.brightspark.sparkshammers.block;

import com.brightspark.sparkshammers.SHCreativeTab;
import com.brightspark.sparkshammers.SparksHammers;
import com.brightspark.sparkshammers.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockHammerCraft extends Block
{
    public BlockHammerCraft()
    {
        super(Material.rock);
        setBlockName("hammerCraft");
        setCreativeTab(SHCreativeTab.SH_TAB);
        setHardness(2f);
        setResistance(10f);
        setBlockTextureName(Reference.MOD_ID + ":hammerCraft");
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if(world.isRemote)
            return true;
        //Open crafting gui
        if(!player.isSneaking())
            player.openGui(SparksHammers.instance, 0, world, x, y, z);
        return true;
    }
}
