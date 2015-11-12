package com.brightspark.sparkshammers.init;

import com.brightspark.sparkshammers.block.BlockHammer;
import com.brightspark.sparkshammers.block.BlockHammerCraft;
import com.brightspark.sparkshammers.reference.Names;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class SHBlocks
{
    public static Block blockHammer = new BlockHammer();
    public static Block blockHammerCraft = new BlockHammerCraft();

    public static void init()
    {
        //Register blocks here

        //GameRegistry.registerBlock(blockHammer, Names.Blocks.HAMMER);

        GameRegistry.registerBlock(blockHammerCraft, Names.Blocks.HAMMER_CRAFT);
    }
}
