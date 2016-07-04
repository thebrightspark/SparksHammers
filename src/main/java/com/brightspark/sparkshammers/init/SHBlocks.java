package com.brightspark.sparkshammers.init;

import com.brightspark.sparkshammers.block.BlockHammer;
import com.brightspark.sparkshammers.block.BlockHammerCraft;
import com.brightspark.sparkshammers.item.ItemBlockBasic;
import com.brightspark.sparkshammers.reference.Names;
import com.brightspark.sparkshammers.util.ClientUtils;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;

public class SHBlocks
{
    public static BlockHammer blockHammer = new BlockHammer();
    public static ItemBlock itemBlockHammer = new ItemBlockBasic(blockHammer);
    public static BlockHammerCraft blockHammerCraft = new BlockHammerCraft();
    public static ItemBlock itemBlockHammerCraft = new ItemBlockBasic(blockHammerCraft);

    public static void regBlocks()
    {
        //Register blocks here
        GameRegistry.register(blockHammer);
        GameRegistry.register(itemBlockHammer);
        GameRegistry.register(blockHammerCraft);
        GameRegistry.register(itemBlockHammerCraft);
    }

    public static void regModels()
    {
        //Blocks
        ClientUtils.regModel(SHBlocks.blockHammer);
        ClientUtils.regModel(SHBlocks.blockHammerCraft);
    }
}
