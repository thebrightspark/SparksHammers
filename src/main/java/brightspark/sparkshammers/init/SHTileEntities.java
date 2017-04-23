package brightspark.sparkshammers.init;

import brightspark.sparkshammers.reference.Names;
import brightspark.sparkshammers.tileentity.TileHammer;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SHTileEntities
{
    public static void init()
    {
        //Register tile entities here

        GameRegistry.registerTileEntity(TileHammer.class, Names.Blocks.HAMMER);
    }
}
