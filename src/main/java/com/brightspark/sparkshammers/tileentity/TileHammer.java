package com.brightspark.sparkshammers.tileentity;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.UUID;

public class TileHammer extends TileEntity
{
    private UUID ownerUUID = null;
    private boolean hasNoOwner = false;

    /**
     * Sets the hammer to have no owner.
     */
    public void setNoOwner()
    {
        hasNoOwner = true;
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

    public void setOwner(UUID uuid)
    {
        ownerUUID = uuid;
        markDirty();
    }

    /**
     * Returns whether the hammer has an owner.
     */
    public boolean hasOwner()
    {
        return ownerUUID != null;
    }

    private static GameProfile getGameProfile(UUID uuid)
    {
        if(uuid == null) return null;
        return FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerProfileCache().getProfileByUUID(uuid);
    }

    /**
     * Returns whether the given player is the owner of the hammer.
     */
    public boolean isOwner(EntityPlayer player)
    {
        GameProfile gp = getGameProfile(ownerUUID);
        return gp != null && ownerUUID != null && gp.getId().equals(player.getUniqueID());
    }

    /**
     * Returns the name of the player who owns the hammer.
     * "Null" if no owner.
     */
    public String getOwnerName()
    {
        GameProfile gp = getGameProfile(ownerUUID);
        return gp == null ? "Null" : gp.getName();
    }

    public UUID getOwnerUUID()
    {
        return ownerUUID;
    }

    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        //Get the owner's UUID from the NBT
        if(tag.hasKey("uuidMostSig"))
            ownerUUID = new UUID(tag.getLong("uuidMostSig"), tag.getLong("uuidLeastSig"));
        else
            ownerUUID = null;
    }

    public NBTTagCompound writeToNBT(NBTTagCompound tag)
    {
        NBTTagCompound compound = super.writeToNBT(tag);
        //Sets the owner's UUID to the NBT
        if(ownerUUID != null)
        {
            compound.setLong("uuidLeastSig", ownerUUID.getLeastSignificantBits());
            compound.setLong("uuidMostSig", ownerUUID.getMostSignificantBits());
        }
        else if(hasNoOwner)
        {
            compound.removeTag("uuidLeastSig");
            compound.removeTag("uuidMostSig");
        }
        return compound;
    }

    /**
     * Use this to send data about the block. In this case, the owner's UUID in the NBTTagCompound.
     */
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        return new SPacketUpdateTileEntity(pos, 0, nbt);
    }

    /**
     * Use this to update the hammer when a packet is received.
     */
    public void onDataPacket(net.minecraft.network.NetworkManager net, net.minecraft.network.play.server.SPacketUpdateTileEntity pkt)
    {
        readFromNBT(pkt.getNbtCompound());
    }
}
