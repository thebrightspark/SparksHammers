package com.brightspark.sparkshammers.item;

import com.brightspark.sparkshammers.init.SHBlocks;
import com.brightspark.sparkshammers.reference.Materials;
import com.brightspark.sparkshammers.reference.Names;
import com.brightspark.sparkshammers.reference.Reference;
import com.brightspark.sparkshammers.tileentity.TileHammer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ItemHammerMjolnir extends ItemHammer
{
    public ItemHammerMjolnir()
    {
        super(Materials.HAMMER_DIAMOND);
        setUnlocalizedName(Names.Items.HAMMER_THOR);
        setTextureName(Reference.ITEM_TEXTURE_DIR + Names.Items.HAMMER_IRON);
        setInfinite(true);
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack, int pass)
    {
        return pass == 0;
    }

    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        //Place hammer in the world and remove item from inventory

        //Check side of block hit and place on that side of the block
        if (world.getBlock(x, y, z) != Blocks.snow_layer)
        {
            switch(side)
            {
                case 0:
                    --y;
                    break;
                case 1:
                    ++y;
                    break;
                case 2:
                    --z;
                    break;
                case 3:
                    ++z;
                    break;
                case 4:
                    --x;
                    break;
                case 5:
                    ++x;
                    break;
                default:
                    //Just a fail-safe incase the impossible happens...
                    player.addChatMessage(new ChatComponentText("Error: Side given is " + side));
                    return false;
            }
            if (!world.isAirBlock(x, y, z))
            {
                return false;
            }
        }
        //Place hammer block and remove hammer item from inventory
        //world.setBlock(x, y, z, new BlockHammer(player.getDisplayName()));
        world.setBlock(x, y, z, SHBlocks.blockHammer);
        //Set owner to hammer tile entity
        TileHammer hammerTile = (TileHammer) world.getTileEntity(x, y, z);
        hammerTile.setOwner(player.getDisplayName());
        if(world.isRemote)
        {
            //TODO: This isn't playing the sound!
            float rand = (float) ((Math.random() / 2) + 0.5);
            world.playSoundEffect(x+0.5, y+0.5, z+0.5, "random.anvil_land", 0.75f, rand);
            //player.addChatMessage(new ChatComponentText("Anvil sound!"));
        }
        if(!player.capabilities.isCreativeMode)
            player.setCurrentItemOrArmor(0, null);
        return true;
    }
}
