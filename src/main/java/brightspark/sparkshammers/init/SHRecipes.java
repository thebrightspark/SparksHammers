package brightspark.sparkshammers.init;

import brightspark.sparkshammers.hammerCrafting.HammerCraftingManager;
import brightspark.sparkshammers.item.ItemAOE;
import brightspark.sparkshammers.reference.Config;
import brightspark.sparkshammers.reference.Names;
import brightspark.sparkshammers.reference.Reference;
import brightspark.sparkshammers.util.CommonUtils;
import brightspark.sparkshammers.util.LoaderHelper;
import brightspark.sparkshammers.util.LogHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class SHRecipes
{
    private static final ResourceLocation GROUP = new ResourceLocation(Reference.MOD_ID);

    //TODO: Review for 1.12
    private static void addShapedOreRecipe(ItemStack output, Object... inputs)
    {
        CraftingManager.func_193372_a(output.getItem().getRegistryName(), new ShapedOreRecipe(GROUP, output, inputs));
    }

    public static void init()
    {
        //Wooden Hammer Head
        addShapedOreRecipe(new ItemStack(SHItems.hammerHeadWood), "xxx", "xxx", "   ", 'x', "logWood");

        //Hammer Crafting Table
        Item hammerWood = SHItems.getItemById("hammer_wood");
        ItemStack centerItem;
        if(hammerWood == null)
            centerItem = new ItemStack(SHItems.hammerHeadWood);
        else
            centerItem = new ItemStack(hammerWood, 1, OreDictionary.WILDCARD_VALUE);
        addShapedOreRecipe(new ItemStack(SHBlocks.blockHammerCraft), "scs", "chc", "scs", 's', "stone", 'c', Blocks.CRAFTING_TABLE, 'h', centerItem);

        //Wooden Hammer
        if(hammerWood != null)
            addShapedOreRecipe(new ItemStack(SHItems.getItemById("hammer_wood")), " x ", " s ", " s ", 'x', SHItems.hammerHeadWood, 's', "plankWood");

        //Wooden Excavator
        if(SHItems.excavatorHeadWood != null)
        {
            addShapedOreRecipe(new ItemStack(SHItems.excavatorHeadWood), " x ", "xxx", "   ", 'x', "logWood");
            addShapedOreRecipe(new ItemStack(SHItems.getItemById("excavator_wood")), " x ", " s ", " s ", 'x', SHItems.excavatorHeadWood, 's', "plankWood");
        }

        /*
         * Hammer Crafting Table Recipes
         */

        HammerCraftingManager hammerCraft = HammerCraftingManager.getInstance();

        if(Config.enableMiniHammer)
            hammerCraft.addRecipe(new ItemStack(SHItems.hammerMini), " HHH ", " HHH ", "SSSS ", 'H', Items.IRON_INGOT, 'S', "stickWood");
        if(Config.enableGiantHammer)
            hammerCraft.addRecipe(new ItemStack(SHItems.hammerGiant), "HHHHH", "HHDHH", "SSSS ", 'H', Blocks.IRON_BLOCK, 'S', "stickWood", 'D', new ItemStack(Items.DYE, 1, 5));
        if(Config.enableNetherStarHammer)
            hammerCraft.addRecipe(new ItemStack(SHItems.hammerNetherStar), "HHBHH", "HBNBH", "SSSS ", 'H', Items.DIAMOND, 'B', Blocks.GOLD_BLOCK, 'N', Items.NETHER_STAR, 'S', "stickWood");
        if(Config.enablePoweredHammer)
        {
            boolean enderioRecipeAdded = false;
            if(LoaderHelper.isModLoaded(Names.Mods.ENDERIO))
            {
                Item capBank = Item.getByNameOrId(Names.ModItemIds.CAPACITOR_BANK);
                if(capBank == null)
                    LogHelper.warn("Couldn't get " + Names.ModItemIds.CAPACITOR_BANK + " for Powered Hammer recipe! Resorting to normal recipe. Please report this to mod author!");
                else
                {
                    hammerCraft.addRecipe(new ItemStack(SHItems.hammerPowered), "IBDBI", "IDCDI", "SSSS ", 'I', Items.IRON_INGOT, 'B', Blocks.IRON_BLOCK, 'D', "blockDarkSteel", 'C', new ItemStack(capBank, 1, 1), 'S', "stickWood");
                    enderioRecipeAdded = true;
                }
            }
            if(!enderioRecipeAdded)
                hammerCraft.addRecipe(new ItemStack(SHItems.hammerPowered), "IBGBI", "IGRGI", "SSSS ", 'I', Items.IRON_INGOT, 'B', Blocks.IRON_BLOCK, 'G', Items.GOLD_INGOT, 'R', Blocks.REDSTONE_BLOCK, 'S', "stickWood");
        }

        //Create recipes for all tools which have an ore dictionary ready for the item ingredient
        for(ItemAOE tool : SHItems.AOE_TOOLS)
        {
            String oreDic = tool.getDependantOreDic();
            if(oreDic == null)
            {
                //LogHelper.warn("No dependant ore dictionary entry for tool " + tool.getRegistryName().getResourcePath());
                continue;
            }
            String topRow = tool.isExcavator ? " HHH " : "HHHHH";
            if(oreDic.equals(Names.EnumMaterials.STONE.dependantOreDic) && LoaderHelper.isModLoaded(Names.Mods.EXTRA_UTILITIES))
            {
                //Swap out for compressed cobblestone
                Item compressedCobble = CommonUtils.getRegisteredItem(Names.ModItemIds.COMPRESSED_COBBLE);
                if(compressedCobble != null)
                {
                    LogHelper.info("Compressed Cobblestone found in " + Names.Mods.EXTRA_UTILITIES + ". Using for " + tool.getRegistryName().getResourcePath() + " recipe.");
                    hammerCraft.addRecipe(new ItemStack(tool), topRow, "HHHHH", "SSSS ", 'H', new ItemStack(compressedCobble), 'S', "stickWood");
                    continue;
                }
                else
                    LogHelper.warn("Compressed Cobblestone not found in " + Names.Mods.EXTRA_UTILITIES + ". Resorting to normal recipes. Please report this to mod author!");
            }
            hammerCraft.addRecipe(new ItemStack(tool), topRow, "HHHHH", "SSSS ", 'H', oreDic, 'S', "stickWood");
        }
    }
}
