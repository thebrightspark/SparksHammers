package com.brightspark.sparkshammers.proxy;

public class ClientProxy extends CommonProxy
{
    @Override
    public void RegisterBlockHandlers()
    {
        //Register new block renderer
        //Reference.HAMMER_RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
        //RenderingRegistry.registerBlockHandler(Reference.HAMMER_RENDER_ID, new BlockHammerRender());
    }
}
