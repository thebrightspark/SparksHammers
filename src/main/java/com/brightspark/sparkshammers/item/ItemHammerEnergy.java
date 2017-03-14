package com.brightspark.sparkshammers.item;

import com.brightspark.sparkshammers.SparksHammers;
import com.brightspark.sparkshammers.customTools.Tool;
import com.brightspark.sparkshammers.energy.EnergyContainer;
import com.brightspark.sparkshammers.energy.EnergyContainerProvider;
import com.brightspark.sparkshammers.reference.Config;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemHammerEnergy extends ItemAOE
{
    public ItemHammerEnergy(Tool tool)
    {
        super(tool, false);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems)
    {
        super.getSubItems(itemIn, tab, subItems);

        ItemStack poweredStack = new ItemStack(itemIn);
        EnergyContainer energy = ItemHammerEnergy.getEnergyStorage(poweredStack);
        energy.setEnergyStored(energy.getMaxEnergyStored());
        subItems.add(poweredStack);
    }

    public static EnergyContainer getEnergyStorage(ItemStack stack)
    {
        return (EnergyContainer) stack.getCapability(SparksHammers.energyCapability, null);
    }

    public static int getEnergyStored(ItemStack stack)
    {
        return getEnergyStorage(stack).getEnergyStored();
    }

    public static boolean useEnergy(ItemStack stack, int amount)
    {
        EnergyContainer energy = getEnergyStorage(stack);
        if(energy.getEnergyStored() < amount)
            return false;
        else
        {
            energy.extractEnergyInternal(amount, false);
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
        return new EnergyContainerProvider(new EnergyContainer(Config.poweredEnergyCapacity, Config.poweredEnergyInputRate, 0));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        EnergyContainer energy = getEnergyStorage(stack);
        tooltip.add(I18n.format("item.hammer_powered.tooltip") + ":");
        tooltip.add(energy.getEnergyStored() + " / " + energy.getMaxEnergyStored());
    }
}
