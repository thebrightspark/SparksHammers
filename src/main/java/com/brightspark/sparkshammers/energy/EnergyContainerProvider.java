package com.brightspark.sparkshammers.energy;

import com.brightspark.sparkshammers.SparksHammers;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nullable;

public class EnergyContainerProvider implements ICapabilitySerializable<NBTTagCompound>
{
    private final EnergyContainer energy;

    public EnergyContainerProvider(EnergyContainer energy)
    {
        this.energy = energy;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing)
    {
        return capability == SparksHammers.energyCapability;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
    {
        return capability == SparksHammers.energyCapability ? (T) energy : null;
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        return energy.serializeNBT();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {
        energy.deserializeNBT(nbt);
    }
}
