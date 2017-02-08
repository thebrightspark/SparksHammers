package com.brightspark.sparkshammers.energy;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class EnergyContainer implements ISparkEnergyStorage, INBTSerializable<NBTTagCompound>
{
    protected int energy, capacity, maxReceive, maxExtract;

    public EnergyContainer(int capacity)
    {
        this(capacity, capacity);
    }
    public EnergyContainer(int capacity, int maxTransfer)
    {
        this(capacity, maxTransfer, maxTransfer);
    }
    public EnergyContainer(int capacity, int maxReceive, int maxExtract)
    {
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setInteger("energyStored", energy);
        tag.setInteger("energyCapacity", capacity);
        tag.setInteger("energyReceive", maxReceive);
        tag.setInteger("energyExtract", maxExtract);
        return tag;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {
        energy = nbt.getInteger("energyStored");
        capacity = nbt.getInteger("energyCapacity");
        maxReceive = nbt.getInteger("energyReceive");
        maxExtract = nbt.getInteger("energyExtract");
        if(energy > capacity)
            energy = capacity;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate)
    {
        if (!canReceive())
            return 0;
        return receiveEnergyInternal(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate)
    {
        if (!canExtract())
            return 0;
        return extractEnergyInternal(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored()
    {
        return energy;
    }

    @Override
    public int getMaxEnergyStored()
    {
        return capacity;
    }

    @Override
    public boolean canExtract()
    {
        return this.maxExtract > 0;
    }

    @Override
    public boolean canReceive()
    {
        return this.maxReceive > 0;
    }

    @Override
    public void setEnergyStored(int amount)
    {
        energy = amount;
        if(energy > capacity)
            energy = capacity;
        if(energy < 0)
            energy = 0;
    }

    @Override
    public int receiveEnergyInternal(int maxReceive, boolean simulate)
    {
        int energyReceived = Math.min(capacity - energy, maxReceive);
        if (!simulate)
            energy += energyReceived;
        return energyReceived;
    }

    @Override
    public int extractEnergyInternal(int maxExtract, boolean simulate)
    {
        int energyExtracted = Math.min(energy, maxExtract);
        if (!simulate)
            energy -= energyExtracted;
        return energyExtracted;
    }
}
