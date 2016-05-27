package com.brightspark.sparkshammers.util;

import com.brightspark.sparkshammers.item.ItemAOE;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import org.lwjgl.input.Keyboard;

/**
 * Created by Mark on 26/05/2016.
 */
public class CommonUtils
{
    public static boolean isCtrlKeyDown()
    {
        // prioritize CONTROL, but allow OPTION as well on Mac (note: GuiScreen's isCtrlKeyDown only checks for the OPTION key on Mac)
        boolean isCtrlKeyDown = Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL);
        if (!isCtrlKeyDown && Minecraft.isRunningOnMac)
            isCtrlKeyDown = Keyboard.isKeyDown(Keyboard.KEY_LMETA) || Keyboard.isKeyDown(Keyboard.KEY_RMETA);

        return isCtrlKeyDown;
    }

    public static boolean isShiftKeyDown()
    {
        return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
    }

    public static void breakArea(ItemStack stack, EntityPlayer player, BlockPos posHit, BlockPos posStart, BlockPos posEnd)
    {
        for (int xPos = posStart.getX(); xPos <= posEnd.getX(); xPos++)
            for (int yPos = posStart.getY(); yPos <= posEnd.getY(); yPos++)
                for (int zPos = posStart.getZ(); zPos <= posEnd.getZ(); zPos++)
                    if(!stack.getItem().onBlockStartBreak(stack, new BlockPos(xPos, yPos, zPos), player))
                        CommonUtils.breakBlock(stack, player.worldObj, player, new BlockPos(xPos, yPos, zPos), posHit);
    }

    // <<<< Adapted Tinkers Construct >>>>
    public static void breakBlock(ItemStack stack, World world, EntityPlayer player, BlockPos blockPos, BlockPos refBlockPos)
    {
        LogHelper.info("Breaking block at: " + blockPos.toString());

        // prevent calling that stuff for air blocks, could lead to unexpected behaviour since it fires events
        if(world.isAirBlock(blockPos)) return;

        // check if the block can be broken, since extra block breaks shouldn't instantly break stuff like obsidian
        // or precious ores you can't harvest while mining stone
        IBlockState blockState = world.getBlockState(blockPos);
        Block block = blockState.getBlock();

        // only effective materials
        if(!((ItemAOE)stack.getItem()).isEffective(block)) return;

        IBlockState refBlockState = world.getBlockState(refBlockPos);
        float refStrength = ForgeHooks.blockStrength(refBlockState, player, world, refBlockPos);
        float strength = ForgeHooks.blockStrength(blockState, player, world, blockPos);

        // only harvestable blocks that aren't impossibly slow to harvest
        if(!ForgeHooks.canHarvestBlock(block, player, world, blockPos) || refStrength / strength > 10f) return;

        // From this point on it's clear that the player CAN break the block

        if(player.capabilities.isCreativeMode)
        {
            block.onBlockHarvested(world, blockPos, blockState, player);
            if(block.removedByPlayer(world, blockPos, player, false))
                block.onBlockDestroyedByPlayer(world, blockPos, blockState);

            // send update to client
            if(!world.isRemote)
                ((EntityPlayerMP)player).playerNetServerHandler.sendPacket(new S23PacketBlockChange(world, blockPos));
            return;
        }

        // callback to the tool the player uses. Called on both sides. This damages the tool n stuff.
        stack.onBlockDestroyed(world, block, blockPos, player);

        // server sided handling
        if(!world.isRemote)
        {
            // send the blockbreak event
            int xp = ForgeHooks.onBlockBreakEvent(world, ((EntityPlayerMP) player).theItemInWorldManager.getGameType(), (EntityPlayerMP) player, blockPos);
            if(xp == -1) {
                return;
            }

            // serverside we reproduce ItemInWorldManager.tryHarvestBlock

            // ItemInWorldManager.removeBlock
            block.onBlockHarvested(world, blockPos, blockState, player);

            if(block.removedByPlayer(world, blockPos, player, true)) // boolean is if block can be harvested, checked above
            {
                block.onBlockDestroyedByPlayer(world, blockPos, blockState);
                block.harvestBlock(world, player, blockPos, blockState, world.getTileEntity(blockPos));
                block.dropXpOnBlockBreak(world, blockPos, xp);
            }

            // always send block update to client
            ((EntityPlayerMP)player).playerNetServerHandler.sendPacket(new S23PacketBlockChange(world, blockPos));
        }
        // client sided handling
        else
        {
            // clientside we do a "this block has been clicked on long enough to be broken" call. This should not send any new packets
            // the code above, executed on the server, sends a block-updates that give us the correct state of the block we destroy.

            // following code can be found in PlayerControllerMP.onPlayerDestroyBlock
            world.playAuxSFX(2001, blockPos, Block.getStateId(blockState));
            if(block.removedByPlayer(world, blockPos, player, true))
                block.onBlockDestroyedByPlayer(world, blockPos, blockState);
            // callback to the tool
            stack.onBlockDestroyed(world, block, blockPos, player);

            if(stack.stackSize == 0 && stack == player.getCurrentEquippedItem())
                player.destroyCurrentEquippedItem();

            // send an update to the server, so we get an update back
            Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockPos, Minecraft.getMinecraft().objectMouseOver.sideHit));
        }
    }
}
