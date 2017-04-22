package com.brightspark.sparkshammers.energy;

import net.minecraftforge.energy.EnergyStorage;

public class SHEnergyStorage extends EnergyStorage
{
    public SHEnergyStorage(int capacity, int maxReceive, int maxExtract)
    {
        super(capacity, maxReceive, maxExtract);
    }

    public void setEnergyStored(int amount)
    {
        energy = amount;
        if(energy > capacity)
            energy = capacity;
        if(energy < 0)
            energy = 0;
    }
}
