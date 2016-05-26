package com.brightspark.sparkshammers.init;

import com.brightspark.sparkshammers.block.BlockHammer;
import com.brightspark.sparkshammers.block.BlockHammerCraft;
import com.brightspark.sparkshammers.reference.Names;
import com.brightspark.sparkshammers.util.ClientUtils;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SHBlocks
{
    public static BlockHammer blockHammer = new BlockHammer();
    public static BlockHammerCraft blockHammerCraft = new BlockHammerCraft();

    public static void regBlocks()
    {
        //Register blocks here

        GameRegistry.registerBlock(blockHammer, Names.Blocks.HAMMER);
        GameRegistry.registerBlock(blockHammerCraft, Names.Blocks.HAMMER_CRAFT);
    }

    public static void regModels()
    {
        //Blocks
        ClientUtils.regModel(SHBlocks.blockHammer);
        ClientUtils.regModel(SHBlocks.blockHammerCraft);
    }
}
