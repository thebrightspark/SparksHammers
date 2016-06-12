package com.brightspark.sparkshammers.item;

import com.brightspark.sparkshammers.init.SHBlocks;
import com.brightspark.sparkshammers.reference.Materials;
import com.brightspark.sparkshammers.reference.Names;
import com.brightspark.sparkshammers.tileentity.TileHammer;
import com.brightspark.sparkshammers.util.Lang;
import com.brightspark.sparkshammers.util.NBTHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.UUID;

public class ItemHammerThor extends ItemHammer
{
    private static int cooldownMax = 200; //10 secs

    public ItemHammerThor()
    {
        super(Names.Items.HAMMER_THOR, Materials.HAMMER_MJOLNIR, true);
    }

    /**
     * Gets the item's lightning cooldown
     */
    private int getCooldown(ItemStack stack)
    {
        return NBTHelper.getInt(stack, "cooldown");
    }

    /**
     * Gets the UUID of the owner of the item
     */
    public static UUID getOwner(ItemStack stack)
    {
        return NBTHelper.getOwner(stack);
    }

    /**
     * Gets the owner's display name of the item
     */
    public static String getOwnerName(ItemStack stack)
    {
        if(NBTHelper.hasTag(stack, "owner"))
            return NBTHelper.getString(stack, "owner");
        return "None";
    }

    public static boolean isOwner(ItemStack stack, Entity entity)
    {
        return getOwner(stack) != null && (entity instanceof EntityPlayer && getOwner(stack).equals(entity.getUniqueID()));
    }

    /**
     * Sets the item's lightning cooldown to the max
     */
    private void setCooldownToMax(ItemStack stack)
    {
        NBTHelper.setInteger(stack, "cooldown", cooldownMax);
    }

    /**
     * Sets the owner of the item to the given player
     */
    public static void setOwner(ItemStack stack, EntityPlayer player)
    {
        NBTHelper.setOwner(stack, player);
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if(player.isSneaking() && getOwner(stack) != null)
        {
            //Place hammer in the world and remove item from inventory

            IBlockState blockHitState = world.getBlockState(pos);
            Block blockHit = blockHitState.getBlock();

            //Most of the following general stuff is taken from ItemReed.java
            //noinspection all
            if(blockHit == Blocks.SNOW_LAYER && blockHitState.getValue(BlockSnow.LAYERS).intValue() < 1)
            {
                side = EnumFacing.UP;
            }
            else if(! blockHit.isReplaceable(world, pos))
            {
                pos = pos.offset(side);
            }

            if(! player.canPlayerEdit(pos, side, stack))
            {
                return false;
            }
            else if(stack.stackSize == 0)
            {
                return false;
            }
            else if(world.canBlockBePlaced(SHBlocks.blockHammer, pos, false, side, null, stack))
            {
                //Place hammer block
                IBlockState hammerState = SHBlocks.blockHammer.onBlockPlaced(world, pos, side, hitX, hitY, hitZ, 0, player);
                if(world.setBlockState(pos, SHBlocks.blockHammer.getDefaultState())) //Returns true if block placed successfully
                {
                    hammerState.getBlock().onBlockPlacedBy(world, pos, hammerState, player, stack);
                    TileHammer te = (TileHammer) world.getTileEntity(pos);
                    te.setOwner(player);

                    //Remove hammer item from inventory if not in Creative
                    if(! player.capabilities.isCreativeMode) -- stack.stackSize;
                    return true;
                }
            }
        }
        return false;
    }

    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand)
    {
        if(getOwner(stack) == null)
            setOwner(stack, player);
        else if(!world.isRemote && !player.isSneaking() && getCooldown(stack) <= 0)
        {
            //Spawn lightning at cursor
            RayTraceResult ray = rayTrace(player.worldObj, player, false);
            if(ray != null)
            {
                BlockPos pos = ray.getBlockPos();
                if(ray.entityHit != null)
                    pos = ray.entityHit.getPosition();
                world.addWeatherEffect(new EntityLightningBolt(world, pos.getX(), pos.getY(), pos.getZ(), false));
            }
            if(!player.capabilities.isCreativeMode)
                setCooldownToMax(stack);
        }
        return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
    }

    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        if(isOwner(stack, entityIn))
        {
            int cooldown = getCooldown(stack);
            if(cooldown > 0) NBTHelper.setInteger(stack, "cooldown", --cooldown);
        }
        else if(!getOwnerName(stack).equals("None"))
        {
            if(entityIn instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer) entityIn;
                player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 600, 3));
                player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 10, 6));
                player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 10, -6));
            }
        }
    }

    public boolean showDurabilityBar(ItemStack stack)
    {
        return getCooldown(stack) > 0;
    }

    public double getDurabilityForDisplay(ItemStack stack)
    {
        int cooldown = getCooldown(stack);
        return cooldown == 0 ? 0 : (double)cooldown / (double)cooldownMax;
    }

    /**
     * Determine if the player switching between these two item stacks
     * I'm using this to stop the animation happening every time the NBT of the item is different.
     */
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged)
    {
        return !ItemStack.areItemStacksEqual(oldStack, newStack) && slotChanged;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced)
    {
        String name = getOwnerName(stack);
        String text = Lang.localize(stack.getUnlocalizedName() + ".tooltip");

        if(name.equals("None"))
            //No owner
            text += TextFormatting.GOLD;
        else if(name.equals(player.getDisplayNameString()))
            //Player holding item is owner
            text += TextFormatting.GREEN;
        else
            //Player holding item is not owner
            text += TextFormatting.RED;

        text += " " + name;
        tooltip.add(text);
    }
}
