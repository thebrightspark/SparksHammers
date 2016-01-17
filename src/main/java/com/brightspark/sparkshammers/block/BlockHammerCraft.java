package com.brightspark.sparkshammers.block;

import com.brightspark.sparkshammers.SHCreativeTab;
import com.brightspark.sparkshammers.SparksHammers;
import com.brightspark.sparkshammers.reference.Names;
import com.brightspark.sparkshammers.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockHammerCraft extends Block
{
    //Used to save the textures fro the block
    private IIcon textureTop, textureSide, textureBottom;

    public BlockHammerCraft()
    {
        super(Material.rock);
        setBlockName(Names.Blocks.HAMMER_CRAFT);
        setCreativeTab(SHCreativeTab.SH_TAB);
        setHardness(2f);
        setResistance(10f);
        setBlockTextureName(Reference.MOD_ID + ":" + Names.Blocks.HAMMER_CRAFT);
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

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister registry)
    {
        textureTop = registry.registerIcon(this.getTextureName() + "Top");
        textureSide = registry.registerIcon(this.getTextureName() + "Side");
        textureBottom = registry.registerIcon(this.getTextureName() + "Bottom");
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
        switch(side)
        {
            case 1:
                //Top
                return textureTop;
            case 2:
            case 3:
            case 4:
            case 5:
                //Sides
                return textureSide;
            case 0:
            default:
                //Bottom
                return textureBottom;
        }
    }
}
