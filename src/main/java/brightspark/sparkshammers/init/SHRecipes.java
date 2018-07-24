package brightspark.sparkshammers.init;

import brightspark.sparkshammers.Reference;
import brightspark.sparkshammers.hammerCrafting.HammerShapedOreRecipe;
import brightspark.sparkshammers.item.ItemAOE;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.ArrayList;
import java.util.List;

public class SHRecipes
{
    public static List<IRecipe> VANILLA_RECIPES;
    public static List<HammerShapedOreRecipe> SH_RECIPES;

    private static void addVanillaRecipe(Item output, Object... inputs)
    {
        addVanillaRecipe(new ItemStack(output), inputs);
    }

    private static void addVanillaRecipe(ItemStack output, Object... inputs)
    {
        VANILLA_RECIPES.add(new ShapedOreRecipe(null, output, inputs).setRegistryName(Reference.MOD_ID, output.getItem().getRegistryName().getResourcePath()));
    }

    public static void addSHRecipe(Item output, Object... inputs)
    {
        addSHRecipe(new ItemStack(output), inputs);
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
        addVanillaRecipe(SHItems.hammerHeadWood, "xxx", "xxx", "   ", 'x', "logWood");

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
            addVanillaRecipe(SHItems.hammerWood, " x ", " s ", " s ", 'x', SHItems.hammerHeadWood, 's', "plankWood");

        //Wooden Excavator
        if(SHItems.excavatorHeadWood != null)
        {
            addVanillaRecipe(SHItems.excavatorHeadWood, " x ", "xxx", "   ", 'x', "logWood");
            addVanillaRecipe(SHItems.excavatorWood, " x ", " s ", " s ", 'x', SHItems.excavatorHeadWood, 's', "plankWood");
        }

        /*
         * Hammer Crafting Table Recipes
         */

        if(SHItems.hammerMini != null)
            addSHRecipe(SHItems.hammerMini, " HHH ", " HHH ", "SSSS ", 'H', Items.IRON_INGOT, 'S', "stickWood");
        if(SHItems.hammerGiant != null)
            addSHRecipe(SHItems.hammerGiant, "HHHHH", "HHDHH", "SSSS ", 'H', Blocks.IRON_BLOCK, 'S', "stickWood", 'D', new ItemStack(Items.DYE, 1, 5));
        if(SHItems.hammerNetherStar != null)
            addSHRecipe(SHItems.hammerNetherStar, "HHBHH", "HBNBH", "SSSS ", 'H', Items.DIAMOND, 'B', Blocks.GOLD_BLOCK, 'N', Items.NETHER_STAR, 'S', "stickWood");
        if(SHItems.hammerPowered != null)
        {
            addSHRecipe(SHItems.hammerPowered, "IBGBI", "IGRGI", "SSSS ", 'I', Items.IRON_INGOT, 'B', Blocks.IRON_BLOCK, 'G', Items.GOLD_INGOT, 'R', Blocks.REDSTONE_BLOCK, 'S', "stickWood");

            //Upgrades
            addVanillaRecipe(SHItems.upgradeBase, "IGI", "GDG", "IGI", 'I', "ingotIron", 'G', "ingotGold", 'D', "gemDiamond");
            Object stone = SHItems.hammerStone;
            if(stone == null) stone = Blocks.OBSIDIAN;
            addVanillaRecipe(SHItems.upgradeSize, " H ", "HBH", " H ", 'B', SHItems.upgradeBase, 'H', stone);
            addVanillaRecipe(SHItems.upgradeSpeed, "SCS", "CBC", "SCS", 'B', SHItems.upgradeBase, 'S', Items.SUGAR, 'C', Blocks.CAKE);
            addVanillaRecipe(SHItems.upgradeAttack, " S ", "SBS", " S ", 'B', SHItems.upgradeBase, 'S', Items.STONE_SWORD);
            addVanillaRecipe(SHItems.upgradeHarvest, " D ", "DBD", " D ", 'B', SHItems.upgradeBase, 'D', "gemDiamond");
            addVanillaRecipe(SHItems.upgradeCapacity, " R ", "RBR", " R ", 'B', SHItems.upgradeBase, 'R', Blocks.REDSTONE_BLOCK);
        }

        //Create recipes for all tools which have an ore dictionary ready for the item ingredient
        for(ItemAOE tool : SHItems.AOE_TOOLS)
        {
            Object dep = tool.getDependantOreDic();
            if(dep == null)
                dep = tool.getDependantStack();
            if(dep != null)
                addSHRecipe(tool, tool.isExcavator ? " HHH " : "HHHHH", "HHHHH", "SSSS ", 'H', dep, 'S', "stickWood");
        }
    }

    public static IRecipe[] getVanillaRecipes()
    {
        if(VANILLA_RECIPES == null) init();
        return VANILLA_RECIPES.toArray(new IRecipe[0]);
    }

    public static HammerShapedOreRecipe[] getSHRecipes()
    {
        if(SH_RECIPES == null) init();
        return SH_RECIPES.toArray(new HammerShapedOreRecipe[0]);
    }
}
