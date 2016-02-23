package com.brightspark.sparkshammers.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;

import java.util.UUID;

public class TileHammer extends TileEntity
{
    private UUID ownerUUID;

    /**
     * Sets the hammer to have no owner.
     */
    public void setNoOwner()
    {
        ownerUUID = null;
        markDirty();
    }

    /**
     * Sets the owner of the hammer to the given player.
     */
    public void setOwner(EntityPlayer owner)
    {
        ownerUUID = owner.getUniqueID();
        markDirty();
    }

    /**
     * Returns whether the hammer has an owner.
     */
    public boolean hasOwner()
    {
        return ownerUUID != null;
    }

    /**
     * Returns whether the given player is the owner of the hammer.
     */
    public boolean isOwner(EntityPlayer player)
    {
        return ownerUUID != null && MinecraftServer.getServer().getPlayerProfileCache().getProfileByUUID(ownerUUID).getId().equals(player.getUniqueID());
    }

    /**
     * Returns the name of the player who owns the hammer.
     * "Null" if no owner.
     */
    public String getOwnerName()
    {
        return ownerUUID == null ? "Null" : MinecraftServer.getServer().getPlayerProfileCache().getProfileByUUID(ownerUUID).getName();
    }

    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        //Get the owner's UUID from the NBT
        if(tag.hasKey("uuidMostSig") && tag.hasKey("uuidLeastSig"))
            ownerUUID = new UUID(tag.getLong("uuidMostSig"), tag.getLong("uuidLeastSig"));
        else
            ownerUUID = null;
    }

    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        //Sets the owner's UUID to the NBT
        if(ownerUUID != null)
        {
            tag.setLong("uuidLeastSig", ownerUUID.getLeastSignificantBits());
            tag.setLong("uuidMostSig", ownerUUID.getMostSignificantBits());
        }
        else
        {
            tag.removeTag("uuidLeastSig");
            tag.removeTag("uuidMostSig");
        }
    }

    /**
     * Use this to send data about the block. In this case, the owner's UUID in the NBTTagCompound.
     */
    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        return new S35PacketUpdateTileEntity(pos, 0, nbt);
    }

    /**
     * Use this to update the hammer when a packet is received.
     */
    public void onDataPacket(net.minecraft.network.NetworkManager net, net.minecraft.network.play.server.S35PacketUpdateTileEntity pkt)
    {
        readFromNBT(pkt.getNbtCompound());
    }
}
