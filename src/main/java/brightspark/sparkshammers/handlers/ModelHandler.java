package brightspark.sparkshammers.handlers;

import brightspark.sparkshammers.init.SHBlocks;
import brightspark.sparkshammers.init.SHItems;
import brightspark.sparkshammers.item.ItemAOE;
import brightspark.sparkshammers.util.ClientUtils;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ModelHandler
{
    @SubscribeEvent
    public static void regModels(ModelRegistryEvent event)
    {
        //Register all item models
        SHItems.getItemsList().forEach(item -> {
            if(item instanceof ItemAOE && ((ItemAOE) item).getTextureColour() >= 0)
                ClientUtils.regTool((ItemAOE) item);
            else
                ClientUtils.regModel(item);
        });

        //Register block models
        SHBlocks.BLOCKS.forEach(ClientUtils::regModel);
    }
}
