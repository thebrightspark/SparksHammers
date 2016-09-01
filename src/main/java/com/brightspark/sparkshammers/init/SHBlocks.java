package com.brightspark.sparkshammers.init;

import com.brightspark.sparkshammers.block.BlockHammer;
import com.brightspark.sparkshammers.block.BlockHammerCraft;
import com.brightspark.sparkshammers.util.ClientUtils;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SHBlocks
{
    public static BlockHammer blockHammer = new BlockHammer();
    public static BlockHammerCraft blockHammerCraft = new BlockHammerCraft();

    public static void regBlock(Block block)
    {
        GameRegistry.register(block);
        GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
    }

    public static void regBlocks()
    {
        //Register blocks here
        regBlock(blockHammer);
        regBlock(blockHammerCraft);
    }

    public static void regModels()
    {
        //Blocks
        ClientUtils.regModel(SHBlocks.blockHammer);
        ClientUtils.regModel(SHBlocks.blockHammerCraft);
    }
}
