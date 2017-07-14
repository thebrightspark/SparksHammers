package brightspark.sparkshammers.init;

import brightspark.sparkshammers.block.BlockHammer;
import brightspark.sparkshammers.block.BlockHammerCraft;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class SHBlocks
{
    public static List<Block> BLOCKS = new ArrayList<>();
    public static List<ItemBlock> ITEM_BLOCKS = new ArrayList<>();

    public static BlockHammer blockHammer = new BlockHammer();
    public static BlockHammerCraft blockHammerCraft = new BlockHammerCraft();

    public static void regBlock(Block block)
    {
        BLOCKS.add(block);
        ITEM_BLOCKS.add((ItemBlock) new ItemBlock(block)
            {
                @Override
                public void onCreated(ItemStack stack, World worldIn, EntityPlayer player)
                {
                    super.onCreated(stack, worldIn, player);
                    //Handle achievements
                    //TODO: Review for 1.12
                    /*
                    Item item = stack.getItem();
                    if(item.equals(Item.getItemFromBlock(SHBlocks.blockHammerCraft)))
                        player.addStat(SHAchievements.craftingTable);
                    */
                }
            }.setRegistryName(block.getRegistryName()));
    }

    public static void regBlocks()
    {
        //Only register once
        if(!BLOCKS.isEmpty()) return;

        regBlock(blockHammer);
        regBlock(blockHammerCraft);
    }
}
