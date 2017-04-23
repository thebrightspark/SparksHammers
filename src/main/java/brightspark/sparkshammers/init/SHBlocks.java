package brightspark.sparkshammers.init;

import brightspark.sparkshammers.block.BlockHammer;
import brightspark.sparkshammers.block.BlockHammerCraft;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class SHBlocks
{
    public static Map<String, Block> BLOCKS = new HashMap<String, Block>();
    public static Map<String, ItemBlock> ITEM_BLOCKS = new HashMap<String, ItemBlock>();

    public static BlockHammer blockHammer = new BlockHammer();
    public static BlockHammerCraft blockHammerCraft = new BlockHammerCraft();

    public static void regBlock(Block block)
    {
        BLOCKS.put(block.getRegistryName().getResourcePath(), block);
        ITEM_BLOCKS.put(block.getRegistryName().getResourcePath(), (ItemBlock) new ItemBlock(block)
        {
            @Override
            public void onCreated(ItemStack stack, World worldIn, EntityPlayer player)
            {
                super.onCreated(stack, worldIn, player);
                //Handle achievements
                Item item = stack.getItem();
                if(item.equals(Item.getItemFromBlock(SHBlocks.blockHammerCraft)))
                    player.addStat(SHAchievements.craftingTable);
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
