package com.brightspark.sparkshammers.item;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

import java.util.Set;

public class ItemExcavator extends ItemAOE
{
    private static final Set<Block> ShovelBlocks = Sets.newHashSet(new Block[] {Blocks.CLAY, Blocks.DIRT, Blocks.FARMLAND, Blocks.GRASS, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.SAND, Blocks.SNOW, Blocks.SNOW_LAYER, Blocks.SOUL_SAND, Blocks.GRASS_PATH});
    //These are the material types which the hammer can mine in AOE:
    private static final Set<Material> ShovelMats = Sets.newHashSet(new Material[]{Material.GRASS, Material.GROUND, Material.SAND, Material.SNOW, Material.CRAFTED_SNOW, Material.CLAY});
    //private static final Material[] ShovelMats = {Material.grass, Material.ground, Material.sand, Material.snow, Material.craftedSnow, Material.clay};

    public ItemExcavator(String name, ToolMaterial mat)
    {
        this(name, mat, false);
    }

    public ItemExcavator(String name, ToolMaterial mat, boolean hasInfiniteUse)
    {
        super(name, 2.0f, mat, ShovelBlocks, ShovelMats);
        setInfinite(hasInfiniteUse);
    }
}
