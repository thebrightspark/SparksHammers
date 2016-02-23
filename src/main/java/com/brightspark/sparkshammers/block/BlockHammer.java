package com.brightspark.sparkshammers.block;

import com.brightspark.sparkshammers.SHCreativeTab;
import com.brightspark.sparkshammers.init.SHItems;
import com.brightspark.sparkshammers.item.IHasModel;
import com.brightspark.sparkshammers.reference.Names;
import com.brightspark.sparkshammers.tileentity.TileHammer;
import com.brightspark.sparkshammers.util.SHModelResourceLocation;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.Random;

public class BlockHammer extends BlockContainer implements IHasModel
{
    private ModelResourceLocation model;

    public BlockHammer()
    {
        super(Material.anvil);
        setCreativeTab(SHCreativeTab.SH_TAB);
        setUnlocalizedName(Names.Blocks.HAMMER);
        setBlockUnbreakable();
        setLightOpacity(0);
        model = new SHModelResourceLocation(Names.Blocks.HAMMER);
        setBlockBounds(0.25F, 0.0F, 0.0625F, 0.75F, 1.0F, 0.9375F);
    }

    @Override
    public ModelResourceLocation getModel()
    {
        return model;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int p_149915_2_)
    {
        return new TileHammer();
    }

    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        //Set owner to hammer tile entity
        TileHammer hammerTile = (TileHammer) world.getTileEntity(pos);
        if(placer instanceof EntityPlayer)
            hammerTile.setOwner((EntityPlayer) placer);
        else
            hammerTile.setNoOwner();
        //ItemBlock.setTileEntityNBT(world, (EntityPlayer) placer, pos, stack);

        //Play anvil sound
        if(!world.isRemote)
            world.playAuxSFX(1022, pos, 0);
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean isFullCube()
    {
        return false;
    }

    //Return 3 for standard block models
    public int getRenderType()
    {
        return 3;
    }

    //Will return no item when destroyed, since the block is unbreakable anyway, there's no need.
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return null;
    }

    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        //When the player right clicks on this block

        //Get the owner from the tile entity
        TileHammer hammer = (TileHammer) world.getTileEntity(pos);
        if(player.getHeldItem() == null)
        {
            //If no item in hand
            if(!hammer.hasOwner() || hammer.isOwner(player))
            {
                if(world.isRemote)
                {
                    if(hammer.hasOwner())
                        //Player is worthy
                        player.addChatMessage(new ChatComponentText("You are my Master, " + player.getDisplayNameString()));
                    else
                        //Hammer had no previous owner
                        player.addChatMessage(new ChatComponentText("Ah! A new Master!"));
                }
                player.setCurrentItemOrArmor(0, new ItemStack(SHItems.hammerThor));
                world.setBlockToAir(new BlockPos(pos));
            }
            else if(world.isRemote)
            {
                //Player is not worthy
                player.addChatMessage(new ChatComponentText("You are not worthy to wield this!"));
                player.addChatMessage(new ChatComponentText(hammer.getOwnerName() + " is my true Master!"));
            }
        }
        return false;
    }
}
