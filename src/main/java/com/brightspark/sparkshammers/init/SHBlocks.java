package com.brightspark.sparkshammers.init;

import com.brightspark.sparkshammers.block.BlockHammer;
import net.minecraft.block.Block;

public class SHBlocks
{
    public static Block blockHammer = new BlockHammer();

    public static void init()
    {
        //Register blocks here

        //GameRegistry.registerBlock(blockHammer, Names.Blocks.HAMMER);
    }
}
