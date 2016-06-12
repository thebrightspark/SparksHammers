package com.brightspark.sparkshammers;

import com.brightspark.sparkshammers.gui.GuiHandler;
import com.brightspark.sparkshammers.hammerCrafting.HammerCraftingManager;
import com.brightspark.sparkshammers.handlers.AchieveEventHandler;
import com.brightspark.sparkshammers.handlers.BlockEventHandler;
import com.brightspark.sparkshammers.handlers.ConfigurationHandler;
import com.brightspark.sparkshammers.handlers.LootEventHandler;
import com.brightspark.sparkshammers.init.*;
import com.brightspark.sparkshammers.reference.Config;
import com.brightspark.sparkshammers.reference.ModMaterials;
import com.brightspark.sparkshammers.reference.Reference;
import com.brightspark.sparkshammers.worldgen.WorldGenMjolnirShrine;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid=Reference.MOD_ID, name=Reference.MOD_NAME, version=Reference.VERSION)
public class SparksHammers
{
    //Instance of this mod that is safe to reference
    @Mod.Instance(Reference.MOD_ID)
    public static SparksHammers instance;

    //@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    //public static IProxy proxy;

    public static final CreativeTabs SH_TAB = new CreativeTabs(Reference.MOD_ID)
    {
        @Override
        public Item getTabIconItem()
        {
            return SHItems.hammerDiamond;
        }

        @Override
        public String getTranslatedTabLabel()
        {
            return Reference.MOD_NAME;
        }
    };

    public static DamageSource fallingHammer = new DamageSource("fallingHammer");
    public static BlockEventHandler blockEH = new BlockEventHandler();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        //Initialize item, blocks and configs here

        //Passes suggested configuration file into the init method
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        MinecraftForge.EVENT_BUS.register(new ConfigurationHandler());

        SHItems.regItems();
        SHBlocks.regBlocks();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        //Initialize textures/models, GUIs, tile entities, recipies, event handlers here

        //Registers all of the item and block textures
        if(event.getSide() == Side.CLIENT)
        {
            SHItems.regModels();
            SHBlocks.regModels();
        }

        //Adds mod material made items if enabled in config
        if(Config.includeOtherModItems)
        {
            ModMaterials.init();
            SHModItems.regItems();
            //Registers mod textures
            if(event.getSide() == Side.CLIENT)
                SHModItems.regModels();
        }

        SHRecipes.init(); //Adds vanilla crafting table recipes
        HammerCraftingManager.getInstance(); //Calls the method so that the recipes are created.
        SHTileEntities.init();

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
        MinecraftForge.EVENT_BUS.register(blockEH); //Block Event Handler for the Nether Star Hammer
        MinecraftForge.EVENT_BUS.register(new AchieveEventHandler()); //Event handlers for Achievements
        MinecraftForge.EVENT_BUS.register(new LootEventHandler()); //Event handler to add loot to chests

        SHAchievements.init(); //Adds achievements

        //Register world generation for Mjolnir Shrine
        if(Config.shouldGenerateMjolnirShrines)
            GameRegistry.registerWorldGenerator(new WorldGenMjolnirShrine(), 10);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        //Run stuff after mods have initialized here

        //Prints out all crafting recipes
        /*
        LogHelper.info("<<<<<<<<<< Sparks Hammers Crafting Recipes >>>>>>>>>>");
        List<HammerShapedOreRecipe> recipes = HammerCraftingManager.getInstance().getRecipeList();
        for(HammerShapedOreRecipe r : recipes)
        {
            String inputs = "";
            for(Object item : r.getInput())
            {
                if(item instanceof ItemStack)
                    inputs = inputs.concat(((ItemStack)item).getDisplayName() + ", ");
                else if(item instanceof List)
                    inputs = inputs.concat(((List)item).get(0).toString() + ", ");
                else if(item == null)
                    inputs = inputs.concat("Null" + ", ");
                else
                    inputs = inputs.concat("Unknown" + ", ");
            }
            LogHelper.info(r.getRecipeOutput().getDisplayName() + " ---> " + inputs);
        }
        */

        //Prints out all of the items in the ore dictionary
        /*
        for(String ore : OreDictionary.getOreNames())
        {
            LogHelper.info(ore);
        }
        */

        //This displays all item IDs:
        /*
        Iterator items = Item.itemRegistry.getKeys().iterator();
        while(items.hasNext())
            LogHelper.info(items.next().toString());
        */
    }
}
