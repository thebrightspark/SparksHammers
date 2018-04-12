package brightspark.sparkshammers.init;

import brightspark.sparkshammers.hammerCrafting.HammerShapedOreRecipe;
import brightspark.sparkshammers.item.ItemAOE;
import brightspark.sparkshammers.EnumMaterials;
import brightspark.sparkshammers.Reference;
import brightspark.sparkshammers.util.CommonUtils;
import brightspark.sparkshammers.util.LogHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.ArrayList;
import java.util.List;

public class SHRecipes
{
    public static List<IRecipe> VANILLA_RECIPES;
    public static List<HammerShapedOreRecipe> SH_RECIPES;

    private static void addVanillaRecipe(ItemStack output, Object... inputs)
    {
        VANILLA_RECIPES.add(new ShapedOreRecipe(null, output, inputs).setRegistryName(Reference.MOD_ID, output.getItem().getRegistryName().getResourcePath()));
    }

    private static void addSHRecipe(ItemStack output, Object...inputs)
    {
        SH_RECIPES.add(new HammerShapedOreRecipe(output, inputs));
    }

    private static void init()
    {
        VANILLA_RECIPES = new ArrayList<>();
        SH_RECIPES = new ArrayList<>();

        //Wooden Hammer Head
        addVanillaRecipe(new ItemStack(SHItems.hammerHeadWood), "xxx", "xxx", "   ", 'x', "logWood");

        //Hammer Crafting Table
        Item hammerWood = SHItems.hammerWood;
        ItemStack centerItem;
        if(hammerWood == null)
            centerItem = new ItemStack(SHItems.hammerHeadWood);
        else
            centerItem = new ItemStack(hammerWood, 1, OreDictionary.WILDCARD_VALUE);
        addVanillaRecipe(new ItemStack(SHBlocks.blockHammerCraft), "scs", "chc", "scs", 's', "stone", 'c', Blocks.CRAFTING_TABLE, 'h', centerItem);

        //Wooden Hammer
        if(hammerWood != null)
            addVanillaRecipe(new ItemStack(SHItems.hammerWood), " x ", " s ", " s ", 'x', SHItems.hammerHeadWood, 's', "plankWood");

        //Wooden Excavator
        if(SHItems.excavatorHeadWood != null)
        {
            addVanillaRecipe(new ItemStack(SHItems.excavatorHeadWood), " x ", "xxx", "   ", 'x', "logWood");
            addVanillaRecipe(new ItemStack(SHItems.excavatorWood), " x ", " s ", " s ", 'x', SHItems.excavatorHeadWood, 's', "plankWood");
        }

        /*
         * Hammer Crafting Table Recipes
         */

        if(SHItems.hammerMini != null)
            addSHRecipe(new ItemStack(SHItems.hammerMini), " HHH ", " HHH ", "SSSS ", 'H', Items.IRON_INGOT, 'S', "stickWood");
        if(SHItems.hammerGiant != null)
            addSHRecipe(new ItemStack(SHItems.hammerGiant), "HHHHH", "HHDHH", "SSSS ", 'H', Blocks.IRON_BLOCK, 'S', "stickWood", 'D', new ItemStack(Items.DYE, 1, 5));
        if(SHItems.hammerNetherStar != null)
            addSHRecipe(new ItemStack(SHItems.hammerNetherStar), "HHBHH", "HBNBH", "SSSS ", 'H', Items.DIAMOND, 'B', Blocks.GOLD_BLOCK, 'N', Items.NETHER_STAR, 'S', "stickWood");
        if(SHItems.hammerPowered != null)
        {
            boolean enderioRecipeAdded = false;
            if(Loader.isModLoaded(Reference.Mods.ENDERIO))
            {
                Item capBank = Item.getByNameOrId(Reference.ModItemIds.CAPACITOR_BANK);
                if(capBank == null)
                    LogHelper.warn("Couldn't get " + Reference.ModItemIds.CAPACITOR_BANK + " for Powered Hammer recipe! Resorting to normal recipe. Please report this to mod author!");
                else
                {
                    addSHRecipe(new ItemStack(SHItems.hammerPowered), "IBDBI", "IDCDI", "SSSS ", 'I', Items.IRON_INGOT, 'B', Blocks.IRON_BLOCK, 'D', "blockDarkSteel", 'C', new ItemStack(capBank, 1, 1), 'S', "stickWood");
                    enderioRecipeAdded = true;
                }
            }
            if(!enderioRecipeAdded)
                addSHRecipe(new ItemStack(SHItems.hammerPowered), "IBGBI", "IGRGI", "SSSS ", 'I', Items.IRON_INGOT, 'B', Blocks.IRON_BLOCK, 'G', Items.GOLD_INGOT, 'R', Blocks.REDSTONE_BLOCK, 'S', "stickWood");

            addVanillaRecipe(new ItemStack(SHItems.upgradeBase), "IGI", "GDG", "IGI", 'I', "ingotIron", 'G', "ingotGold", 'D', "gemDiamond");
            addVanillaRecipe(new ItemStack(SHItems.upgradeSize), " H ", "HBH", " H ", 'B', SHItems.upgradeBase, 'H', SHItems.hammerStone);
            addVanillaRecipe(new ItemStack(SHItems.upgradeSpeed), "SCS", "CBC", "SCS", 'B', SHItems.upgradeBase, 'S', Items.SUGAR, 'C', Blocks.CAKE);
            addVanillaRecipe(new ItemStack(SHItems.upgradeAttack), " S ", "SBS", " S ", 'B', SHItems.upgradeBase, 'S', Items.STONE_SWORD);
            addVanillaRecipe(new ItemStack(SHItems.upgradeHarvest), " D ", "DBD", " D ", 'B', SHItems.upgradeBase, 'D', "gemDiamond");
            addVanillaRecipe(new ItemStack(SHItems.upgradeCapacity), " R ", "RBR", " R ", 'B', SHItems.upgradeBase, 'R', Blocks.REDSTONE_BLOCK);
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
            if(oreDic.equals(EnumMaterials.STONE.dependantOreDic) && Loader.isModLoaded(Reference.Mods.EXTRA_UTILITIES))
            {
                //Swap out for compressed cobblestone
                Item compressedCobble = CommonUtils.getRegisteredItem(Reference.ModItemIds.COMPRESSED_COBBLE);
                if(compressedCobble != null)
                {
                    LogHelper.info("Compressed Cobblestone found in " + Reference.Mods.EXTRA_UTILITIES + ". Using for " + tool.getRegistryName().getResourcePath() + " recipe.");
                    addSHRecipe(new ItemStack(tool), topRow, "HHHHH", "SSSS ", 'H', new ItemStack(compressedCobble), 'S', "stickWood");
                    continue;
                }
                else
                    LogHelper.warn("Compressed Cobblestone not found in " + Reference.Mods.EXTRA_UTILITIES + ". Resorting to normal recipes. Please report this to mod author!");
            }
            addSHRecipe(new ItemStack(tool), topRow, "HHHHH", "SSSS ", 'H', oreDic, 'S', "stickWood");
        }
    }

    public static IRecipe[] getVanillaRecipes()
    {
        if(VANILLA_RECIPES == null) init();
        return VANILLA_RECIPES.toArray(new IRecipe[VANILLA_RECIPES.size()]);
    }

    public static HammerShapedOreRecipe[] getSHRecipes()
    {
        if(SH_RECIPES == null) init();
        return SH_RECIPES.toArray(new HammerShapedOreRecipe[SH_RECIPES.size()]);
    }
}
