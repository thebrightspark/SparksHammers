package brightspark.sparkshammers.handlers;

import brightspark.sparkshammers.init.SHBlocks;
import brightspark.sparkshammers.init.SHItems;
import brightspark.sparkshammers.item.ItemAOE;
import brightspark.sparkshammers.util.ClientUtils;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by Mark on 04/02/2017.
 */
@Mod.EventBusSubscriber(Side.CLIENT)
public class ModelHandler
{
    @SubscribeEvent
    public static void regModels(ModelRegistryEvent event)
    {
        //Register all item models
        for(Item tool : SHItems.ITEMS.values())
        {
            if(tool instanceof ItemAOE && ((ItemAOE)tool).getTextureColour() >= 0)
                ClientUtils.regTool((ItemAOE) tool);
            else
                ClientUtils.regModel(tool);
        }

        //Register block models
        for(Block block : SHBlocks.BLOCKS.values())
            ClientUtils.regModel(block);
    }
}
