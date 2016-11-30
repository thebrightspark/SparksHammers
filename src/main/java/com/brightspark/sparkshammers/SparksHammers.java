package com.brightspark.sparkshammers;

import com.brightspark.sparkshammers.gui.GuiHandler;
import com.brightspark.sparkshammers.hammerCrafting.HammerCraftingManager;
import com.brightspark.sparkshammers.hammerCrafting.HammerShapedOreRecipe;
import com.brightspark.sparkshammers.handlers.AchieveEventHandler;
import com.brightspark.sparkshammers.handlers.BlockEventHandler;
import com.brightspark.sparkshammers.handlers.ConfigurationHandler;
import com.brightspark.sparkshammers.handlers.LootEventHandler;
import com.brightspark.sparkshammers.init.*;
import com.brightspark.sparkshammers.item.ItemAOE;
import com.brightspark.sparkshammers.reference.Config;
import com.brightspark.sparkshammers.reference.Reference;
import com.brightspark.sparkshammers.util.LoaderHelper;
import com.brightspark.sparkshammers.util.LogHelper;
import com.brightspark.sparkshammers.worldgen.WorldGenMjolnirShrine;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

import java.util.List;

@Mod(modid=Reference.MOD_ID, name=Reference.MOD_NAME, version=Reference.VERSION, dependencies=Reference.DEPENDENCIES)
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
            return SHItems.getItemById("hammerDiamond");
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
        //Initialize item, blocks, textures/models and configs here

        //Passes suggested configuration file into the init method
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        MinecraftForge.EVENT_BUS.register(new ConfigurationHandler());

        SHItems.regItems();
        SHBlocks.regBlocks();

        //Registers all of the item and block textures
        if(event.getSide() == Side.CLIENT)
        {
            SHItems.regModels();
            SHBlocks.regModels();
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        //Initialize GUIs, tile entities, recipies, event handlers here

        //Some ore dictionary entries are missed, as the list is created in preInit, so we re-load the list here.
        LoaderHelper.reloadLocalOreDict();

        if(event.getSide() == Side.CLIENT)
            SHItems.regColours();
        SHRecipes.init(); //Adds vanilla crafting table recipes
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

        //Make sure all tools have recipes
        List<HammerShapedOreRecipe> recipes = HammerCraftingManager.getInstance().getRecipeList();
        for(ItemAOE tool : SHItems.AOE_TOOLS)
        {
            if(tool.equals(SHItems.hammerMjolnir))
                continue;
            boolean found = false;
            for(HammerShapedOreRecipe r : recipes)
            {
                if(r.getRecipeOutput() != null && r.getRecipeOutput().getItem().equals(tool))
                {
                    found = true;
                    break;
                }
            }
            if(!found) LogHelper.warn("No hammer crafting recipe found for " + tool.getRegistryName() + "!");
        }

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
        LogHelper.info("\nORE DICTIONARIES:\n");
        for(String ore : OreDictionary.getOreNames())
            LogHelper.info(ore);
        */

        //This displays all item IDs:
        /*
        LogHelper.info("\nITEM IDS:\n");
        Iterator items = Item.REGISTRY.getKeys().iterator();
        while(items.hasNext())
            LogHelper.info(items.next().toString());
        */

        //Go through tool materials and print them out with details
        /*
        LogHelper.info("Current Tool Materials!");
        ToolMaterial[] mat = ToolMaterial.values();
        for(ToolMaterial m : mat)
        {
            LogHelper.info("Material: " + m.name());
            LogHelper.info(m.getHarvestLevel()+","+m.getMaxUses()+","+m.getEfficiencyOnProperMaterial()+","+m.getDamageVsEntity()+","+m.getEnchantability());
        }
        */
    }
}
