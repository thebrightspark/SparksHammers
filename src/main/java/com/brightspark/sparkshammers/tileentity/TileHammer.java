package com.brightspark.sparkshammers.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;

import java.util.UUID;

public class TileHammer extends TileEntity
{
    private UUID playerUUID;

    public void setOwner(EntityPlayer player)
    {
        playerUUID = player.getUniqueID();
    }

    public boolean isOwner(EntityPlayer player)
    {
        return MinecraftServer.getServer().getPlayerProfileCache().getProfileByUUID(playerUUID).getId().equals(player.getUniqueID());
    }

    public String getOwnerName()
    {
        return MinecraftServer.getServer().getPlayerProfileCache().getProfileByUUID(playerUUID).getName();
    }
}
