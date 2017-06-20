package brightspark.sparkshammers.energy;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.EnergyStorage;

public class SHEnergyStorage extends EnergyStorage implements INBTSerializable<NBTTagCompound>
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

    /**
     * Internal method to bypass maxExtract check.
     */
    public int extractEnergyInternal(int maxExtract)
    {
        int energyExtracted = Math.min(energy, Math.min(this.maxReceive, maxExtract));
        energy -= energyExtracted;
        return energyExtracted;
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setInteger("energy", energy);
        tag.setInteger("maxEnergy", capacity);
        tag.setInteger("maxReceive", maxReceive);
        tag.setInteger("maxExtract", maxExtract);
        return tag;
    }

    @Override
    public void deserializeNBT(NBTTagCompound tag)
    {
        energy = tag.getInteger("energy");
        capacity = tag.getInteger("maxEnergy");
        maxReceive = tag.getInteger("maxReceive");
        maxExtract = tag.getInteger("maxExtract");
    }
}
