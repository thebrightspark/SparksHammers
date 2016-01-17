package com.brightspark.sparkshammers.block;

import com.brightspark.sparkshammers.init.SHItems;
import com.brightspark.sparkshammers.tileentity.TileHammer;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.util.Random;

public class BlockHammer extends BlockContainer
{
    public BlockHammer()
    {
        super(Material.anvil);
        setBlockUnbreakable();
        //this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int p_149915_2_)
    {
        return new TileHammer();
    }

    /*
    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public int getRenderType()
    {
        return Reference.HAMMER_RENDER_ID;
    }
    */

    public Item getItemDropped(int p_149650_1_, Random rand, int p_149650_3_)
    {
        return SHItems.hammerThor;
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        //When the player right clicks on this block

        //Get the owner from the tile entity
        TileHammer hammer = (TileHammer) world.getTileEntity(x, y, z);
        String owner = hammer.getOwner();
        if(player.getHeldItem() == null)
        {
            //If no item in hand
            if(player.getDisplayName().equals(owner))
            {
                //Player is worthy
                player.addChatMessage(new ChatComponentText("You are my Master, " + owner));
                player.setCurrentItemOrArmor(0, new ItemStack(SHItems.hammerThor));
                world.setBlockToAir(x, y, z);
            }
            else if(!world.isRemote)
            {
                //Player is not worthy
                player.addChatMessage(new ChatComponentText("You are not worthy to wield this!"));
                player.addChatMessage(new ChatComponentText(owner + " is my true Master!"));
            }
        }
        return true;
    }
}
