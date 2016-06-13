package com.brightspark.sparkshammers.item;

import com.brightspark.sparkshammers.init.SHItems;
import com.brightspark.sparkshammers.util.Lang;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.Set;

public class ItemHammer extends ItemAOE
{
    private static final Set<Block> PickaxeBlocks = Sets.newHashSet(new Block[] {Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.DOUBLE_STONE_SLAB, Blocks.GOLDEN_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.LIT_REDSTONE_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.STONE_SLAB, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE});
    //These are the material types which the hammer can mine in AOE:
    private static final Set<Material> PickaxeMats = Sets.newHashSet(new Material[]{Material.ANVIL, Material.GLASS, Material.ICE, Material.IRON, Material.PACKED_ICE, Material.PISTON, Material.ROCK});
    //private static final Material[] PickaxeMats = {Material.anvil, Material.glass, Material.ice, Material.iron, Material.packedIce, Material.piston, Material.rock};

    //TODO: Try add knockback to hammers

    public ItemHammer(String name, ToolMaterial mat)
    {
        this(name, mat, false);
    }

    public ItemHammer(String name, ToolMaterial mat, boolean hasInfiniteUse)
    {
        super(name, 2.0f, mat, PickaxeBlocks, PickaxeMats);
        setInfinite(hasInfiniteUse);
    }

    public ItemHammer setMineWidth(int width)
    {
        return (ItemHammer) super.setMineWidth(width);
    }

    public ItemHammer setMineHeight(int height)
    {
        return (ItemHammer) super.setMineHeight(height);
    }

    public ItemHammer setShiftRotating(boolean bool)
    {
        return (ItemHammer) super.setShiftRotating(bool);
    }

    /**
     * Called when item is crafted/smelted. Used only by maps so far.
     */
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn)
    {
        //Sets the name to Jason's custom name if he crafts the giant hammer.
        if(stack.getItem().equals(SHItems.hammerGiant) && playerIn.getDisplayNameString().equals("8BrickDMG"))
            stack.setStackDisplayName(TextFormatting.LIGHT_PURPLE + Lang.localize(getUnlocalizedName(stack) + ".8brickdmg"));
    }
}
