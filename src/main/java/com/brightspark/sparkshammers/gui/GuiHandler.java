package com.brightspark.sparkshammers.gui;

import com.brightspark.sparkshammers.hammerCrafting.ContainerHammerCraft;
import com.brightspark.sparkshammers.init.SHBlocks;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        //Server side - returns instance of the container
        if(world.getBlock(x, y, z) == SHBlocks.blockHammerCraft)
            return new ContainerHammerCraft(player.inventory, world, x, y, z);
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        //Client side - returns intance of the gui
        if(world.getBlock(x, y, z) == SHBlocks.blockHammerCraft)
            return new GuiHammerCraft(player.inventory, world, x, y, z);
        return null;
    }
}
