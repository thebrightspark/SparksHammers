package com.brightspark.sparkshammers;

import com.brightspark.sparkshammers.handlers.ConfigurationHandler;
import com.brightspark.sparkshammers.init.*;
import com.brightspark.sparkshammers.proxy.IProxy;
import com.brightspark.sparkshammers.reference.Config;
import com.brightspark.sparkshammers.reference.ModHammerMaterials;
import com.brightspark.sparkshammers.reference.Reference;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid= Reference.MOD_ID, name=Reference.MOD_NAME, version=Reference.VERSION, dependencies = Reference.DEPENDENCIES)
public class SparksHammers
{
    //Instance of this mod that is safe to reference
    @Mod.Instance(Reference.MOD_ID)
    public static SparksHammers instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        //Initialize item, blocks and configs here

        //Passes suggested configuration file into the init method
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

        SHItems.init();
        SHBlocks.init();

        //Adds mod material made items if enabled in config
        if(Config.includeOtherModItems)
        {
            ModHammerMaterials.init();
            SHModItems.init();
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        //Initialize GUIs, tile entities, recipies, event handlers here

        SHRecipies.init();
        SHTileEntities.init();

        //Adds mod material made items' recipes if enabled in config
        if(Config.includeOtherModItems)
        {
            SHModRecipes.init();
        }
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        //Run stuff after mods have initialized here

        //Prints out all of the items in the ore dictionary
        /*
        for(String ore : OreDictionary.getOreNames())
        {
            LogHelper.info(ore);
        }
        */
    }
}
