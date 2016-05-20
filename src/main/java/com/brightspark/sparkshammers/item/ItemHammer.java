package com.brightspark.sparkshammers.item;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

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
}
