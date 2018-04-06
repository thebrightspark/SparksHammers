package brightspark.sparkshammers.block;

import brightspark.sparkshammers.SparksHammers;
import brightspark.sparkshammers.entity.EntityFallingHammer;
import brightspark.sparkshammers.init.SHItems;
import brightspark.sparkshammers.item.ItemHammerMjolnir;
import brightspark.sparkshammers.SHConfig;
import brightspark.sparkshammers.tileentity.TileHammer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
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

import java.util.Random;

public class BlockHammer extends BlockContainer
{
    private static final AxisAlignedBB HAMMER_BOUNDS = new AxisAlignedBB(0.25F, 0.0F, 0.0625F, 0.75F, 1.0F, 0.9375F);

    public BlockHammer()
    {
        super(Material.ANVIL);
        setCreativeTab(SparksHammers.SH_TAB);
        setUnlocalizedName("hammer_block");
        setBlockUnbreakable();
        setLightOpacity(0);
        setRegistryName("hammer_block");
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return HAMMER_BOUNDS;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int p_149915_2_)
    {
        return new TileHammer();
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        //Play anvil sound
        if(!world.isRemote)
            world.playEvent(1031, pos, 0);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    //Return 3 for standard block models
    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    //Will return no item when destroyed, since the block is unbreakable anyway, there's no need.
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return null;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        //When the player right clicks on this block
        if(world.isRemote) return false;

        TileHammer hammer = (TileHammer) world.getTileEntity(pos);
        ItemStack heldStack = player.getHeldItem(hand);

        //If no item in hand
        if(hammer != null && hand == EnumHand.MAIN_HAND && heldStack.isEmpty())
        {
            //Player needs to have killed the dragon to be worthy
            if((!hammer.hasOwner() || hammer.isOwner(player)) &&
                    (!SHConfig.MJOLNIR.mjolnirPickupNeedsDragonAchieve || SparksHammers.hasKillDragonAdvancement((EntityPlayerMP) player)))
            {
                //Player is worthy
                ItemStack givenHammer = new ItemStack(SHItems.hammerMjolnir);
                ItemHammerMjolnir.setOwner(givenHammer, player);
                player.setHeldItem(hand, givenHammer);
                world.setBlockToAir(new BlockPos(pos));
                world.removeTileEntity(pos);
            }
            else
            {
                //Player is not worthy
                player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 200, 1));
                if(SparksHammers.hasKillDragonAdvancement((EntityPlayerMP) player))
                {
                    player.sendMessage(new TextComponentTranslation("item.hammer_mjolnir.chat.wrongPlayer.1"));
                    player.sendMessage(new TextComponentString(TextFormatting.GOLD + hammer.getOwnerName() + TextFormatting.RESET + " " + I18n.format("item.hammer_mjolnir.chat.wrongPlayer.2")));
                }
                else
                    //Player has not killed ender dragon
                    player.sendMessage(new TextComponentTranslation("item.hammer_mjolnir.chat.noAchieve"));
            }
            return true;
        }
        return false;
    }

    //--------------------Falling Code--------------------//
    //(Taken from BlockFalling)

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        worldIn.scheduleUpdate(pos, this, tickRate(worldIn));
    }

    /**
     * Called when a neighboring block was changed and marks that this state should perform any checks during a neighbor
     * change. Cases may include when redstone power is updated, cactus blocks popping off due to a neighboring solid
     * block, etc.
     */
    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos neighbourPos)
    {
        worldIn.scheduleUpdate(pos, this, tickRate(worldIn));
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if(!worldIn.isRemote)
            checkFallable(worldIn, pos);
    }

    private void checkFallable(World worldIn, BlockPos pos)
    {
        if(canFallInto(worldIn, pos.down()) && pos.getY() >= 0)
        {
            int i = 32;

            if(worldIn.isAreaLoaded(pos.add(-i, -i, -i), pos.add(i, i, i)))
            {
                TileHammer teHammer = (TileHammer) worldIn.getTileEntity(pos);
                EntityFallingHammer entityfallingblock = new EntityFallingHammer(worldIn, pos.getX() + 0.5d, pos.getY() + 0.5d, pos.getZ() + 0.5d, worldIn.getBlockState(pos), teHammer.getOwnerUUID());
                onStartFalling(entityfallingblock);
                worldIn.spawnEntity(entityfallingblock);
            }
            else
            {
                worldIn.setBlockToAir(pos);
                BlockPos blockpos;

                blockpos = pos.down();
                while(canFallInto(worldIn, blockpos) && blockpos.getY() > 0)
                    blockpos = blockpos.down();

                if(blockpos.getY() > 0)
                    worldIn.setBlockState(blockpos.up(), getDefaultState());
            }
        }
    }

    /**
     * How many world ticks before ticking
     */
    @Override
    public int tickRate(World worldIn)
    {
        return 2;
    }

    private static boolean canFallInto(World worldIn, BlockPos pos)
    {
        if(worldIn.isAirBlock(pos)) return true;
        IBlockState block = worldIn.getBlockState(pos);
        Material material = block.getMaterial();
        return block == Blocks.FIRE || material == Material.AIR || material == Material.WATER || material == Material.LAVA;
    }

    private void onStartFalling(EntityFallingBlock fallingEntity)
    {
        fallingEntity.setHurtEntities(true);
    }

    public void onEndFalling(World worldIn, BlockPos pos)
    {
        worldIn.playEvent(1031, pos, 0);
    }
}
