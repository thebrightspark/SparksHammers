package com.brightspark.sparkshammers.tileentity;

import net.minecraft.tileentity.TileEntity;

public class TileHammer extends TileEntity
{
    private String owner;

    public void setOwner(String playerName)
    {
        owner = playerName;
    }

    public String getOwner()
    {
        return owner;
    }
}
