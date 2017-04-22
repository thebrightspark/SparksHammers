package com.brightspark.sparkshammers.item;

import com.brightspark.sparkshammers.customTools.Tool;
import com.brightspark.sparkshammers.energy.SHEnergyStorage;
import com.brightspark.sparkshammers.reference.Config;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemHammerEnergy extends ItemAOE
{
    public ItemHammerEnergy(Tool tool, boolean isExcavator)
    {
        super(tool, isExcavator);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems)
    {
        super.getSubItems(itemIn, tab, subItems);

        ItemStack poweredStack = new ItemStack(itemIn);
        SHEnergyStorage energy = ((SHEnergyStorage) poweredStack.getCapability(CapabilityEnergy.ENERGY, null));
        if(energy != null)
        {
            energy.setEnergyStored(energy.getMaxEnergyStored());
            subItems.add(poweredStack);
        }
    }

    public static IEnergyStorage getEnergyStorage(ItemStack stack)
    {
        return stack.getCapability(CapabilityEnergy.ENERGY, null);
    }

    public static int getEnergyStored(ItemStack stack)
    {
        return getEnergyStorage(stack).getEnergyStored();
    }

    public static boolean useEnergy(ItemStack stack, int amount)
    {
        IEnergyStorage energy = getEnergyStorage(stack);
        if(energy.getEnergyStored() < amount)
            return false;
        else
        {
            energy.extractEnergy(amount, false);
            return true;
        }
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase entityHit, EntityLivingBase player)
    {
        //Uses energy before damaging stack
        return getEnergyStored(stack) < Config.poweredEnergyUsePerBlock * 2 ? super.hitEntity(stack, entityHit, player) : useEnergy(stack, Config.poweredEnergyUsePerBlock * 2);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving)
    {
        //Uses energy before damaging stack
        return getEnergyStored(stack) < Config.poweredEnergyUsePerBlock ? super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving) : useEnergy(stack, Config.poweredEnergyUsePerBlock);
    }

    @Override
    public net.minecraftforge.common.capabilities.ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt)
    {
        return new ICapabilityProvider()
        {
            SHEnergyStorage energy = new SHEnergyStorage(Config.poweredEnergyCapacity, Config.poweredEnergyInputRate, 0);

            @Override
            public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
            {
                return capability == CapabilityEnergy.ENERGY;
            }

            @Nullable
            @Override
            public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing)
            {
                if(capability == CapabilityEnergy.ENERGY)
                    return (T) energy;
                return null;
            }
        };
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        IEnergyStorage energy = getEnergyStorage(stack);
        tooltip.add(I18n.format("item.hammerPowered.tooltip") + ":");
        tooltip.add(energy.getEnergyStored() + " / " + energy.getMaxEnergyStored());
    }
}
