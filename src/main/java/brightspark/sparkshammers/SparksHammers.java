package brightspark.sparkshammers;

import brightspark.sparkshammers.gui.GuiHandler;
import brightspark.sparkshammers.init.SHBlocks;
import brightspark.sparkshammers.init.SHItems;
import brightspark.sparkshammers.item.ItemAOE;
import brightspark.sparkshammers.util.LogHelper;
import brightspark.sparkshammers.worldgen.WorldGenMjolnirShrine;
import com.google.common.collect.Sets;
import net.minecraft.advancements.Advancement;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Set;

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

    public static ItemStack TORCH_STACK;
    public static ItemBlock TORCH_ITEM;

    private static Set SPECIAL_NAMES = Sets.newHashSet("mjolnir", "giant", "mini", "netherstar", "powered");

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        //Initialize GUIs, tile entities, recipies, event handlers here

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

        //Register world generation for Mjolnir Shrine
        if(SHConfig.MJOLNIR.shouldGenerateMjolnirShrines)
            GameRegistry.registerWorldGenerator(new WorldGenMjolnirShrine(), 10);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        //Run stuff after mods have initialized here

        TORCH_STACK = new ItemStack(Blocks.TORCH);
        TORCH_ITEM = (ItemBlock) TORCH_STACK.getItem();

        //Set the dependants for a tool to null if they have no recipe
        int count = 0;
        for(ItemAOE tool : SHItems.AOE_TOOLS)
        {
            if(shouldHideTool(tool))
            {
                tool.nullDependants();
                count++;
            }
        }
        if(count > 0)
            LogHelper.info("Removed " + count + " tools from creative menu due to missing ingredients");

        //Null all the lists so it's not taking up extra memory
        SHItems.voidLists();
        SHBlocks.BLOCKS = null;
        SHBlocks.ITEM_BLOCKS = null;
    }

    public static boolean shouldHideTool(ItemAOE tool)
    {
        if(SPECIAL_NAMES.contains(tool.getMaterialName()))
            return false;
        String ore = tool.getDependantOreDic();
        return !OreDictionary.doesOreNameExist(ore) || OreDictionary.getOres(ore).isEmpty();
    }

    public static boolean hasKillDragonAdvancement(EntityPlayerMP player)
    {
        if(killDragonAdvancement == null)
            killDragonAdvancement = player.getServer().getAdvancementManager().getAdvancement(new ResourceLocation("minecraft", "end/kill_dragon"));
        return killDragonAdvancement != null && player.getAdvancements().getProgress(killDragonAdvancement).isDone();
    }
}
