package com.brightspark.sparkshammers.item;

import com.brightspark.sparkshammers.init.SHBlocks;
import com.brightspark.sparkshammers.reference.Materials;
import com.brightspark.sparkshammers.reference.Names;
import com.brightspark.sparkshammers.tileentity.TileHammer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class itemHammerThor extends ItemHammer
{
    public itemHammerThor()
    {
        super(Names.Items.HAMMER_THOR, Materials.HAMMER_DIAMOND, null, true);
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }

    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        //Place hammer in the world and remove item from inventory
        player.addChatMessage(new ChatComponentText("Right-Clicked Mjolnir!"));
        //LogHelper.info("Right-Clicked Mjolnir!");

        //Check side of block hit and place on that side of the block
        if (world.getBlockState(pos).getBlock() != Blocks.snow_layer)
        {
            player.addChatMessage(new ChatComponentText("Block pos hit: " + pos.toString()));
            player.addChatMessage(new ChatComponentText("Side hit: " + side.getName()));
            pos = pos.offset(side);
            player.addChatMessage(new ChatComponentText("Block pos to place: " + pos.toString()));
            if (!world.isAirBlock(pos))
            {
                return false;
            }
        }
        //Place hammer block
        world.setBlockState(pos, SHBlocks.blockHammer.getDefaultState());
        //Set owner to hammer tile entity
        TileHammer hammerTile = (TileHammer) world.getTileEntity(pos);
        hammerTile.setOwner(player);
        if(world.isRemote)
        {
            //TODO: This isn't playing the sound!
            //float rand = (float) ((Math.random() / 2) + 0.5);
            //world.playSoundEffect(pos.getX()+0.5, pos.getY()+0.5, pos.getZ()+0.5, "random.anvil_land", 0.75f, rand);
            world.playAuxSFX(1022, pos, 0);
            //player.addChatMessage(new ChatComponentText("Anvil sound!"));
        }
        player.addChatMessage(new ChatComponentText("Removing hammer (if not in creative)."));
        //Remove hammer item from inventory if not in Creative
        if(!player.capabilities.isCreativeMode)
            player.setCurrentItemOrArmor(0, null);
        return true;
    }
}
