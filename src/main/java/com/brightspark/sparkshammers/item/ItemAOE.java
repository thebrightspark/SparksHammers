package com.brightspark.sparkshammers.item;

import com.brightspark.sparkshammers.SparksHammers;
import com.brightspark.sparkshammers.util.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

import java.util.Set;

public class ItemAOE extends ItemTool
{
    private int mineRadius = 1; //Radius around center block hit
    private int mineDepth = 0; //Depth (behind block)
    private boolean infiniteUse = false;

    //The material types which the tool can mine in AOE:
    private Set<Material> materials;

    public ItemAOE(String name, float attackDamage, ToolMaterial material, Set<Block> effectiveBlocks, Set<Material> effectiveMats)
    {
        super(attackDamage, material, effectiveBlocks);
        setUnlocalizedName(name);
        setCreativeTab(SparksHammers.SH_TAB);
        materials = effectiveMats;
    }

    /**
     * Check whether this Item can harvest the given Block
     */
    public boolean canHarvestBlock(Block blockIn)
    {
        if(this instanceof ItemHammer)
            return blockIn == Blocks.obsidian ? this.toolMaterial.getHarvestLevel() == 3 : (blockIn != Blocks.diamond_block && blockIn != Blocks.diamond_ore ? (blockIn != Blocks.emerald_ore && blockIn != Blocks.emerald_block ? (blockIn != Blocks.gold_block && blockIn != Blocks.gold_ore ? (blockIn != Blocks.iron_block && blockIn != Blocks.iron_ore ? (blockIn != Blocks.lapis_block && blockIn != Blocks.lapis_ore ? (blockIn != Blocks.redstone_ore && blockIn != Blocks.lit_redstone_ore ? (blockIn.getMaterial() == Material.rock ? true : (blockIn.getMaterial() == Material.iron ? true : blockIn.getMaterial() == Material.anvil)) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2);
        else
            return blockIn == Blocks.snow_layer ? true : blockIn == Blocks.snow;
    }

    /**
     * Used in vanilla code for the pickaxe, but not the shovel
     */
    public float getStrVsBlock(ItemStack stack, Block block)
    {
        if(this instanceof ItemHammer)
            return block.getMaterial() != Material.iron && block.getMaterial() != Material.anvil && block.getMaterial() != Material.rock ? super.getStrVsBlock(stack, block) : this.efficiencyOnProperMaterial;
        else
            return super.getStrVsBlock(stack, block);
    }

    /**
     * If true, then the tool will take do damage from digging or attacking.
     * @param isInfinite Set to true for infinite use
     */
    public void setInfinite(boolean isInfinite)
    {
        infiniteUse = isInfinite;
    }

    //Override method from ItemTool to stop durability loss
    public boolean hitEntity(ItemStack stack, EntityLivingBase player, EntityLivingBase entity)
    {
        //Does not decrease durability if has infinite use
        return !infiniteUse && super.hitEntity(stack, player, entity);
    }

    //Override method from ItemTool to stop durability loss
    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, BlockPos pos, EntityLivingBase player)
    {
        //Does not decrease durability if has infinite use
        return !infiniteUse && super.onBlockDestroyed(stack, world, block, pos, player);
    }

    // <<<< From Tinkers Construct: HarvestTool >>>>
    public boolean isEffective (Block block)
    {
        return materials.contains(block.getMaterial());
    }

    // <<<< From Tinkers Construct: HarvestTool >>>>
    protected void breakExtraBlock(ItemStack stack, World world, EntityPlayer player, BlockPos blockPos, BlockPos refBlockPos)
    {
        // prevent calling that stuff for air blocks, could lead to unexpected behaviour since it fires events
        if(world.isAirBlock(blockPos)) return;

        // check if the block can be broken, since extra block breaks shouldn't instantly break stuff like obsidian
        // or precious ores you can't harvest while mining stone
        IBlockState blockState = world.getBlockState(blockPos);
        Block block = blockState.getBlock();

        // only effective materials
        if(!isEffective(block)) return;

        IBlockState refBlockState = world.getBlockState(refBlockPos);
        float refStrength = ForgeHooks.blockStrength(refBlockState, player, world, refBlockPos);
        float strength = ForgeHooks.blockStrength(blockState, player, world, blockPos);

        // only harvestable blocks that aren't impossibly slow to harvest
        if(! ForgeHooks.canHarvestBlock(block, player, world, blockPos) || refStrength / strength > 10f) return;

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
        if(! world.isRemote)
        {
            // send the blockbreak event
            int xp = ForgeHooks.onBlockBreakEvent(world, ((EntityPlayerMP) player).theItemInWorldManager.getGameType(), (EntityPlayerMP) player, blockPos);
            if(xp == -1) {
                return;
            }

            // serverside we reproduce ItemInWorldManager.tryHarvestBlock

            // ItemInWorldManager.removeBlock
            block.onBlockHarvested(world, blockPos, blockState, player);

            boolean isDestroyed = block.removedByPlayer(world, blockPos, player, true);
            LogHelper.info("Is block destroyed: " + isDestroyed);

            if(isDestroyed) // boolean is if block can be harvested, checked above
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

    // <<<< Also made with some help from Tinkers Construct >>>>
    public boolean onBlockStartBreak (ItemStack stack, BlockPos pos, EntityPlayer player)
    {
        //Block being mined
        Block block = player.worldObj.getBlockState(pos).getBlock();
        MovingObjectPosition mop = super.getMovingObjectPositionFromPlayer(player.worldObj, player, false);
        if(mop == null)
            return super.onBlockStartBreak(stack, pos, player);
        EnumFacing sideHit = mop.sideHit;

        //If not effective, then use normal pickaxe block breaking
        if(!isEffective(block))
            return super.onBlockStartBreak(stack, pos, player);

        int yDist = mineRadius;
        int xDist = mineRadius;
        int zDist = mineRadius;

        //Block destroyed, now for AOE
        switch (sideHit) {
            case DOWN:
            case UP:
                yDist = mineDepth;
                break;
            case NORTH:
            case SOUTH:
                zDist = mineDepth;
                break;
            case WEST:
            case EAST:
                xDist = mineDepth;
                break;
        }

        for (int xPos = pos.getX() - xDist; xPos <= pos.getX() + xDist; xPos++)
            for (int yPos = pos.getY() - yDist; yPos <= pos.getY() + yDist; yPos++)
                for (int zPos = pos.getZ() - zDist; zPos <= pos.getZ() + zDist; zPos++) {
                    // don't break the originally already broken block, duh
                    if (xPos == pos.getX() && yPos == pos.getY() && zPos == pos.getZ())
                        continue;

                    if(!super.onBlockStartBreak(stack, new BlockPos(xPos, yPos, zPos), player))
                        breakExtraBlock(stack, player.worldObj, player, new BlockPos(xPos, yPos, zPos), pos);
                }

        return super.onBlockStartBreak(stack, pos, player);
    }
}
