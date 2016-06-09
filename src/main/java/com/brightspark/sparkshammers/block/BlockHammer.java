package com.brightspark.sparkshammers.block;

import com.brightspark.sparkshammers.SparksHammers;
import com.brightspark.sparkshammers.entity.EntityFallingHammer;
import com.brightspark.sparkshammers.init.SHAchievements;
import com.brightspark.sparkshammers.init.SHItems;
import com.brightspark.sparkshammers.item.ItemHammerThor;
import com.brightspark.sparkshammers.reference.Names;
import com.brightspark.sparkshammers.tileentity.TileHammer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.Random;

public class BlockHammer extends BlockContainer
{
    public BlockHammer()
    {
        super(Material.anvil);
        setCreativeTab(SparksHammers.SH_TAB);
        setUnlocalizedName(Names.Blocks.HAMMER);
        setBlockUnbreakable();
        setLightOpacity(0);
        setBlockBounds(0.25F, 0.0F, 0.0625F, 0.75F, 1.0F, 0.9375F);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int p_149915_2_)
    {
        return new TileHammer();
    }

    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
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
            if(!hammer.hasOwner())
                player.triggerAchievement(SHAchievements.mjolnir);
            if(!hammer.hasOwner() || hammer.isOwner(player))
            {
                //Player is worthy
                player.setCurrentItemOrArmor(0, new ItemStack(SHItems.hammerThor));
                ItemHammerThor.setOwner(player.getHeldItem(), player);
                world.setBlockToAir(new BlockPos(pos));
            }
            else
            {
                //Player is not worthy
                player.triggerAchievement(SHAchievements.mjolnirNope);
                player.addPotionEffect(new PotionEffect(Potion.weakness.getId(), 200, 1));
                if(world.isRemote)
                {
                    player.addChatMessage(new ChatComponentText("You are not worthy to wield me!"));
                    player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + hammer.getOwnerName() + EnumChatFormatting.RESET + " is my true Master!"));
                }
            }
        }
        return false;
    }

    //--------------------Falling Code--------------------//
    //(Taken from BlockFalling)

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
    }

    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
    {
        worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
            this.checkFallable(worldIn, pos);
    }

    private void checkFallable(World worldIn, BlockPos pos)
    {
        if (canFallInto(worldIn, pos.down()) && pos.getY() >= 0)
        {
            int i = 32;

            if (worldIn.isAreaLoaded(pos.add(-i, -i, -i), pos.add(i, i, i)))
            {
                TileHammer teHammer = (TileHammer) worldIn.getTileEntity(pos);
                EntityFallingHammer entityfallingblock = new EntityFallingHammer(worldIn, pos.getX() + 0.5d, pos.getY() + 0.5d, pos.getZ() + 0.5d, worldIn.getBlockState(pos), teHammer.getOwnerUUID());
                this.onStartFalling(entityfallingblock);
                worldIn.spawnEntityInWorld(entityfallingblock);
            }
            else
            {
                worldIn.setBlockToAir(pos);
                BlockPos blockpos;

                for (blockpos = pos.down(); canFallInto(worldIn, blockpos) && blockpos.getY() > 0; blockpos = blockpos.down())
                {
                    ;
                }

                if (blockpos.getY() > 0)
                {
                    worldIn.setBlockState(blockpos.up(), this.getDefaultState());
                }
            }
        }
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World worldIn)
    {
        return 2;
    }

    public static boolean canFallInto(World worldIn, BlockPos pos)
    {
        if (worldIn.isAirBlock(pos)) return true;
        Block block = worldIn.getBlockState(pos).getBlock();
        Material material = block.getMaterial();
        return block == Blocks.fire || material == Material.air || material == Material.water || material == Material.lava;
    }

    protected void onStartFalling(EntityFallingBlock fallingEntity)
    {
        fallingEntity.setHurtEntities(true);
    }

    public void onEndFalling(World worldIn, BlockPos pos)
    {
        worldIn.playAuxSFX(1022, pos, 0);
    }
}
