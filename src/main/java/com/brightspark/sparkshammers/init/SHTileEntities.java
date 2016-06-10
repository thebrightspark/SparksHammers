package com.brightspark.sparkshammers.init;

import com.brightspark.sparkshammers.reference.Names;
import com.brightspark.sparkshammers.tileentity.TileHammer;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SHTileEntities
{
    public static void init()
    {
        //Register tile entities here

        GameRegistry.registerTileEntity(TileHammer.class, Names.Blocks.HAMMER);
    }
}
