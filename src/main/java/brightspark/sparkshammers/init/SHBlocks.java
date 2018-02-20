package brightspark.sparkshammers.init;

import brightspark.sparkshammers.block.BlockHammer;
import brightspark.sparkshammers.block.BlockHammerCraft;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

import java.util.ArrayList;
import java.util.List;

public class SHBlocks
{
    public static List<Block> BLOCKS;
    public static List<ItemBlock> ITEM_BLOCKS;

    public static BlockHammer blockHammer = new BlockHammer();
    public static BlockHammerCraft blockHammerCraft = new BlockHammerCraft();

    public static void addBlock(Block block)
    {
        BLOCKS.add(block);
        ITEM_BLOCKS.add((ItemBlock) new ItemBlock(block).setRegistryName(block.getRegistryName()));
    }

    private static void init()
    {
        BLOCKS = new ArrayList<>();
        ITEM_BLOCKS = new ArrayList<>();

        addBlock(blockHammer);
        addBlock(blockHammerCraft);
    }

    public static Block[] getBlocks()
    {
        if(BLOCKS == null) init();
        return BLOCKS.toArray(new Block[BLOCKS.size()]);
    }

    public static ItemBlock[] getItemBlocks()
    {
        if(ITEM_BLOCKS == null) init();
        return ITEM_BLOCKS.toArray(new ItemBlock[ITEM_BLOCKS.size()]);
    }
}
