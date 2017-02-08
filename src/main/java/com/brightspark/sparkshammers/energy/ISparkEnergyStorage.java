package com.brightspark.sparkshammers.energy;

import net.minecraftforge.energy.IEnergyStorage;

public interface ISparkEnergyStorage extends IEnergyStorage
{
    void setEnergyStored(int amount);

    int receiveEnergyInternal(int maxReceive, boolean simulate);

    int extractEnergyInternal(int maxExtract, boolean simulate);
}
