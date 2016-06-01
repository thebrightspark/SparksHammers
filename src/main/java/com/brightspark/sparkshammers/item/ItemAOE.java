package com.brightspark.sparkshammers.item;

import com.brightspark.sparkshammers.SparksHammers;
import com.brightspark.sparkshammers.util.CommonUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.Set;

public class ItemAOE extends ItemTool
{
    protected int mineWidth = 1;
    protected int mineHeight = 1;
    protected int mineDepth = 0; //Depth (behind block)
    private boolean infiniteUse = false;
    private boolean shiftRotating = false;

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
    public boolean hitEntity(ItemStack stack, EntityLivingBase entityHit, EntityLivingBase player)
    {
        //Does not decrease durability if has infinite use
        return !infiniteUse && super.hitEntity(stack, entityHit, player);
    }

    public MovingObjectPosition getMovingObjectPositionFromPlayer(World worldIn, EntityPlayer playerIn, boolean useLiquids)
    {
        return super.getMovingObjectPositionFromPlayer(worldIn, playerIn, useLiquids);
    }

    //Override method from ItemTool to stop durability loss
    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, BlockPos pos, EntityLivingBase player)
    {
        //Does not decrease durability if has infinite use
        return !infiniteUse && super.onBlockDestroyed(stack, world, block, pos, player);
    }

    // <<<< From Tinkers Construct: HarvestTool >>>>
    public boolean isEffective(Block block)
    {
        return materials.contains(block.getMaterial());
    }

    /**
     * Used for most hammers
     */
    private void breakArea(ItemStack stack, EntityPlayer player, BlockPos posHit, BlockPos posStart, BlockPos posEnd)
    {
        for (int xPos = posStart.getX(); xPos <= posEnd.getX(); xPos++)
            for (int yPos = posStart.getY(); yPos <= posEnd.getY(); yPos++)
                for (int zPos = posStart.getZ(); zPos <= posEnd.getZ(); zPos++)
                {
                    // don't break the originally already broken block, duh
                    if (xPos == posHit.getX() && yPos == posHit.getY() && zPos == posHit.getZ())
                        continue;

                    if(!super.onBlockStartBreak(stack, new BlockPos(xPos, yPos, zPos), player))
                        CommonUtils.breakBlock(stack, player.worldObj, player, new BlockPos(xPos, yPos, zPos), posHit);
                }
    }

    // <<<< Also made with some help from Tinkers Construct >>>>
    public boolean onBlockStartBreak (ItemStack stack, BlockPos pos, EntityPlayer player)
    {
        //Block being mined
        MovingObjectPosition mop = super.getMovingObjectPositionFromPlayer(player.worldObj, player, false);
        if(mop == null)
            return super.onBlockStartBreak(stack, pos, player);

        //Calculate area to break
        BlockPos[] positions = CommonUtils.getBreakArea((ItemAOE) stack.getItem(), pos, mop.sideHit, player);
        BlockPos start = positions[0];
        BlockPos end = positions[1];

        //LogHelper.info("Breaking blocks from " + start.toString() + " to " + end.toString());
        breakArea(stack, player, pos, start, end);

        return super.onBlockStartBreak(stack, pos, player);
    }

    public ItemAOE setMineWidth(int width)
    {
        this.mineWidth = width;
        return this;
    }

    public ItemAOE setMineHeight(int height)
    {
        this.mineHeight = height;
        return this;
    }

    public ItemAOE setShiftRotating(boolean bool)
    {
        this.shiftRotating = bool;
        return this;
    }

    public int getMineWidth()
    {
        return this.mineWidth;
    }

    public int getMineHeight()
    {
        return this.mineHeight;
    }

    public int getMineDepth()
    {
        return this.mineDepth;
    }

    public boolean getShiftRotating()
    {
        return this.shiftRotating;
    }
}
