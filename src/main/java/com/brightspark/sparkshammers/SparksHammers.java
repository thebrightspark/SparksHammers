package com.brightspark.sparkshammers;

import com.brightspark.sparkshammers.handlers.ConfigurationHandler;
import com.brightspark.sparkshammers.init.*;
import com.brightspark.sparkshammers.proxy.IProxy;
import com.brightspark.sparkshammers.reference.Config;
import com.brightspark.sparkshammers.reference.ModHammerMaterials;
import com.brightspark.sparkshammers.reference.Names;
import com.brightspark.sparkshammers.reference.Reference;
import com.brightspark.sparkshammers.util.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

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

        LogHelper.info("Current Tool Materials!");
        //Go through tool materials and get the other mod ones we want
        Item.ToolMaterial[] mat = Item.ToolMaterial.values();
        for(Item.ToolMaterial m : mat)
        {
            LogHelper.info("Material: " + m.name());
            if(m.name().equals(Names.ModMaterials.MANASTEEL)) { ModHammerMaterials.MANASTEEL = m; continue; }
            if(m.name().equals(Names.ModMaterials.TERRASTEEL)) { ModHammerMaterials.TERRASTEEL = m; continue; }
            if(m.name().equals(Names.ModMaterials.ELEMENTIUM)) { ModHammerMaterials.ELEMENTIUM = m; }
        }

        //Passes suggested configuration file into the init method
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

        SHItems.init();
        SHBlocks.init();

        if(Config.includeOtherModItems)
        {
            SHModItems.init();
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        //Initialize GUIs, tile entities, recipies, event handlers here

        SHRecipies.init();
        SHTileEntities.init();

        if(Config.includeOtherModItems)
        {
            SHModRecipes.init();
        }
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        //Run stuff after mods have initialized here

        LogHelper.info(Names.Mods.BOTANIA + " = " + Loader.isModLoaded(Names.Mods.BOTANIA));
        LogHelper.info(Names.Mods.EXTRA_UTILITIES + " = " + Loader.isModLoaded(Names.Mods.EXTRA_UTILITIES));


        for(String ore : OreDictionary.getOreNames())
        {
            LogHelper.info(ore);
        }
    }
}
