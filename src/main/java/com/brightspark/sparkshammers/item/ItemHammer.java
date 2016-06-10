package com.brightspark.sparkshammers.item;

import com.brightspark.sparkshammers.init.SHItems;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.Set;

public class ItemHammer extends ItemAOE
{
    private static final Set<Block> PickaxeBlocks = Sets.newHashSet(new Block[]{Blocks.activator_rail, Blocks.coal_ore, Blocks.cobblestone, Blocks.detector_rail, Blocks.diamond_block, Blocks.diamond_ore, Blocks.double_stone_slab, Blocks.golden_rail, Blocks.gold_block, Blocks.gold_ore, Blocks.ice, Blocks.iron_block, Blocks.iron_ore, Blocks.lapis_block, Blocks.lapis_ore, Blocks.lit_redstone_ore, Blocks.mossy_cobblestone, Blocks.netherrack, Blocks.packed_ice, Blocks.rail, Blocks.redstone_ore, Blocks.sandstone, Blocks.red_sandstone, Blocks.stone, Blocks.stone_slab});
    //These are the material types which the hammer can mine in AOE:
    private static final Set<Material> PickaxeMats = Sets.newHashSet(new Material[]{Material.anvil, Material.glass, Material.ice, Material.iron, Material.packedIce, Material.piston, Material.rock});
    //private static final Material[] PickaxeMats = {Material.anvil, Material.glass, Material.ice, Material.iron, Material.packedIce, Material.piston, Material.rock};

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
            stack.setStackDisplayName(EnumChatFormatting.LIGHT_PURPLE + StatCollector.translateToLocal(getUnlocalizedName(stack) + ".8brickdmg"));
    }
}
