package brightspark.sparkshammers;

import java.io.File;
import java.util.List;

import brightspark.sparkshammers.gui.GuiHandler;
import brightspark.sparkshammers.hammerCrafting.HammerCraftingManager;
import brightspark.sparkshammers.hammerCrafting.HammerShapedOreRecipe;
import brightspark.sparkshammers.handlers.ConfigurationHandler;
import brightspark.sparkshammers.init.SHBlocks;
import brightspark.sparkshammers.init.SHItems;
import brightspark.sparkshammers.item.ItemAOE;
import brightspark.sparkshammers.reference.Config;
import brightspark.sparkshammers.reference.Reference;
import brightspark.sparkshammers.util.LoaderHelper;
import brightspark.sparkshammers.util.LogHelper;
import brightspark.sparkshammers.worldgen.WorldGenMjolnirShrine;
import net.minecraft.advancements.Advancement;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid=Reference.MOD_ID, name=Reference.MOD_NAME, version=Reference.VERSION, dependencies=Reference.DEPENDENCIES)
public class SparksHammers
{
    //Instance of this mod that is safe to reference
    @Mod.Instance(Reference.MOD_ID)
    public static SparksHammers instance;

    public static final CreativeTabs SH_TAB = new CreativeTabs(Reference.MOD_ID)
    {
        @Override
        public ItemStack getTabIconItem()
        {
            return new ItemStack(SHItems.hammerDiamond);
        }

        @Override
        public String getTranslatedTabLabel()
        {
            return Reference.MOD_NAME;
        }
    };

    public static DamageSource fallingHammer = new DamageSource("fallingHammer");
    private static Advancement killDragonAdvancement;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        //Initialize item, blocks, textures/models and configs here

        ConfigurationHandler.init(new File(Reference.CONFIG_DIR, "config.cfg"));
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        //Initialize GUIs, tile entities, recipies, event handlers here

        //Some ore dictionary entries are missed, as the list is created in preInit, so we re-load the list here.
        LoaderHelper.reloadLocalOreDict();

        if(event.getSide() == Side.CLIENT)
            SHItems.regColours();

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

        //Register world generation for Mjolnir Shrine
        if(Config.shouldGenerateMjolnirShrines && Config.enableMjolnir)
            GameRegistry.registerWorldGenerator(new WorldGenMjolnirShrine(), 10);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        //Run stuff after mods have initialized here

        //Make sure all tools have recipes
        List<HammerShapedOreRecipe> recipes = HammerCraftingManager.getRecipes();
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

        //Null all the lists so it's not taking up extra memory
        SHItems.voidLists();
        SHBlocks.BLOCKS = null;
        SHBlocks.ITEM_BLOCKS = null;

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

    public static boolean hasKillDragonAdvancement(EntityPlayerMP player)
    {
        if(killDragonAdvancement == null)
            killDragonAdvancement = player.getServer().getAdvancementManager().getAdvancement(new ResourceLocation("minecraft", "end/kill_dragon"));
        return killDragonAdvancement != null && player.getAdvancements().getProgress(killDragonAdvancement).isDone();
    }
}
