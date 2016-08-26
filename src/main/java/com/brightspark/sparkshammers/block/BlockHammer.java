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
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockHammer extends BlockContainer
{
    private static final AxisAlignedBB HAMMER_BOUNDS = new AxisAlignedBB(0.25F, 0.0F, 0.0625F, 0.75F, 1.0F, 0.9375F);

    public BlockHammer()
    {
        super(Material.ANVIL);
        setCreativeTab(SparksHammers.SH_TAB);
        setUnlocalizedName(Names.Blocks.HAMMER);
        setBlockUnbreakable();
        setLightOpacity(0);
        setRegistryName(Names.Blocks.HAMMER);
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return HAMMER_BOUNDS;
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
            world.playEvent(1031, pos, 0);
    }

    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    //Return 3 for standard block models
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    //Will return no item when destroyed, since the block is unbreakable anyway, there's no need.
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return null;
    }

    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        //When the player right clicks on this block
        if(world.isRemote) return false;

        TileHammer hammer = (TileHammer) world.getTileEntity(pos);
        //If no item in hand
        if(hammer != null && hand == EnumHand.MAIN_HAND && heldItem == null)
        {
            if(!hammer.hasOwner())
                player.addStat(SHAchievements.mjolnir);
            //Player needs to have killed the dragon to be worthy
            if((!hammer.hasOwner() || hammer.isOwner(player)) && player.hasAchievement(AchievementList.THE_END2))
            {
                //Player is worthy
                ItemStack givenHammer = new ItemStack(SHItems.hammerThor);
                ItemHammerThor.setOwner(givenHammer, player);
                player.setHeldItem(hand, givenHammer);
                world.setBlockToAir(new BlockPos(pos));
                world.removeTileEntity(pos);
            }
            else
            {
                //Player is not worthy
                player.addStat(SHAchievements.mjolnirNope);
                player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 200, 1));
                if(player.hasAchievement(AchievementList.THE_END2))
                {
                    player.addChatMessage(new TextComponentTranslation("item.hammerThor.chat.wrongPlayer.1"));
                    player.addChatMessage(new TextComponentString(TextFormatting.GOLD + hammer.getOwnerName() + TextFormatting.RESET + " " + I18n.format("item.hammerThor.chat.wrongPlayer.2")));
                }
                else
                    //Player has not killed ender dragon
                    player.addChatMessage(new TextComponentTranslation("item.hammerThor.chat.noAchieve"));
            }
            return true;
        }
        return false;
    }

    //--------------------Falling Code--------------------//
    //(Taken from BlockFalling)

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
    }

    /**
     * Called when a neighboring block was changed and marks that this state should perform any checks during a neighbor
     * change. Cases may include when redstone power is updated, cactus blocks popping off due to a neighboring solid
     * block, etc.
     */
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn)
    {
        worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
    }

    /*
    /**
     * Called when a tile entity on a side of this block changes is created or is destroyed.
     * @param world The world
     * @param pos Block position in world
     * @param neighbor Block position of neighbor

    public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor)
    {
        ((World)world).scheduleUpdate(pos, this, this.tickRate((World) world));
    }
    */

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
        IBlockState block = worldIn.getBlockState(pos);
        Material material = block.getMaterial();
        return block == Blocks.FIRE || material == Material.AIR || material == Material.WATER || material == Material.LAVA;
    }

    protected void onStartFalling(EntityFallingBlock fallingEntity)
    {
        fallingEntity.setHurtEntities(true);
    }

    public void onEndFalling(World worldIn, BlockPos pos)
    {
        worldIn.playEvent(1031, pos, 0);
    }
}
