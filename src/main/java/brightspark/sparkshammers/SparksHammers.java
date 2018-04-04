package brightspark.sparkshammers;

import java.util.List;

import brightspark.sparkshammers.gui.GuiHandler;
import brightspark.sparkshammers.hammerCrafting.HammerCraftingManager;
import brightspark.sparkshammers.hammerCrafting.HammerShapedOreRecipe;
import brightspark.sparkshammers.init.SHBlocks;
import brightspark.sparkshammers.init.SHItems;
import brightspark.sparkshammers.item.ItemAOE;
import brightspark.sparkshammers.reference.SHConfig;
import brightspark.sparkshammers.reference.Reference;
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
    public void init(FMLInitializationEvent event)
    {
        //Initialize GUIs, tile entities, recipies, event handlers here

        if(event.getSide() == Side.CLIENT)
            SHItems.regColours();

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

        //Register world generation for Mjolnir Shrine
        if(SHConfig.shouldGenerateMjolnirShrines)
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
    }

    public static boolean hasKillDragonAdvancement(EntityPlayerMP player)
    {
        if(killDragonAdvancement == null)
            killDragonAdvancement = player.getServer().getAdvancementManager().getAdvancement(new ResourceLocation("minecraft", "end/kill_dragon"));
        return killDragonAdvancement != null && player.getAdvancements().getProgress(killDragonAdvancement).isDone();
    }
}
