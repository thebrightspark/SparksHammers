package com.brightspark.sparkshammers.hammerCrafting;

import com.brightspark.sparkshammers.init.SHItems;
import com.brightspark.sparkshammers.init.SHModItems;
import com.brightspark.sparkshammers.reference.Config;
import com.brightspark.sparkshammers.reference.Names;
import com.brightspark.sparkshammers.util.LoaderHelper;
import com.brightspark.sparkshammers.util.LogHelper;
import com.google.common.collect.Lists;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.List;

public class HammerCraftingManager
{
    /** The static instance of this class */
    private static final HammerCraftingManager instance = new HammerCraftingManager();
    /** A list of all the recipes added */
    private List<IRecipe> recipes = Lists.newArrayList();

    /**
     * Returns the static instance of this class
     */
    public static HammerCraftingManager getInstance()
    {
        /** The static instance of this class */
        return instance;
    }

    private HammerCraftingManager()
    {
        //Add recipes here

        //Vanilla
        addRecipe(new ItemStack(SHItems.hammerWood), "HHHHH", "HHHHH", "SSSS ", 'H', "plankWood", 'S', "stickWood");
        addRecipe(new ItemStack(SHItems.hammerIron), "HHHHH", "HHHHH", "SSSS ", 'H', Items.IRON_INGOT, 'S', "stickWood");
        addRecipe(new ItemStack(SHItems.hammerGold), "HHHHH", "HHHHH", "SSSS ", 'H', Items.GOLD_INGOT, 'S', "stickWood");
        addRecipe(new ItemStack(SHItems.hammerDiamond), "HHHHH", "HHHHH", "SSSS ", 'H', Items.DIAMOND, 'S', "stickWood");

        addRecipe(new ItemStack(SHItems.hammerMini), " HHH ", " HHH ", "SSSS ", 'H', Items.IRON_INGOT, 'S', "stickWood");
        addRecipe(new ItemStack(SHItems.hammerGiant), "HHHHH", "HHDHH", "SSSS ", 'H', Blocks.IRON_BLOCK, 'S', "stickWood", 'D', new ItemStack(Items.DYE, 1, 5));
        addRecipe(new ItemStack(SHItems.hammerNetherStar), "HHBHH", "HBNBH", "SSSS ", 'H', Items.DIAMOND, 'B', Blocks.GOLD_BLOCK, 'N', Items.NETHER_STAR, 'S', "stickWood");

        addRecipe(new ItemStack(SHItems.excavatorWood), " HHH ", "HHHHH", "SSSS ", 'H', "plankWood", 'S', "stickWood");
        addRecipe(new ItemStack(SHItems.excavatorIron), " HHH ", "HHHHH", "SSSS ", 'H', Items.IRON_INGOT, 'S', "stickWood");
        addRecipe(new ItemStack(SHItems.excavatorGold), " HHH ", "HHHHH", "SSSS ", 'H', Items.GOLD_INGOT, 'S', "stickWood");
        addRecipe(new ItemStack(SHItems.excavatorDiamond), " HHH ", "HHHHH", "SSSS ", 'H', Items.DIAMOND, 'S', "stickWood");

        //Make stone recipes made of compressed cobble when Extra Utilities is installed
        ItemStack cobblestone = new ItemStack(Blocks.COBBLESTONE);
        if(LoaderHelper.isModLoaded(Names.Mods.EXTRA_UTILITIES))
        {
            Item compressedCobble = Item.REGISTRY.getObject(new ResourceLocation(Names.ModItemIds.COMPRESSED_COBBLE));
            if(compressedCobble == null)
                LogHelper.warn("Compressed Cobblestone not found in " + Names.Mods.EXTRA_UTILITIES + ". Resorting to normal recipes.");
            else
                cobblestone = new ItemStack(compressedCobble, 1, 0);
        }
        addRecipe(new ItemStack(SHItems.hammerStone), "HHHHH", "HHHHH", "SSSS ", 'H', cobblestone, 'S', "stickWood");
        addRecipe(new ItemStack(SHItems.excavatorStone), " HHH ", "HHHHH", "SSSS ", 'H', cobblestone, 'S', "stickWood");

        //Modded
        if(Config.includeOtherModItems)
        {
            //Botania
            if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_MANASTEEL))
            {
                addRecipe(new ItemStack(SHModItems.hammerManasteel), "HHHHH", "HHHHH", "SSSS ", 'H', Names.ModOreDicts.INGOT_MANASTEEL, 'S', "stickWood");
                addRecipe(new ItemStack(SHModItems.excavatorManasteel), " HHH ", "HHHHH", "SSSS ", 'H', Names.ModOreDicts.INGOT_MANASTEEL, 'S', "stickWood");
            }
            if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_TERRASTEEL))
            {
                addRecipe(new ItemStack(SHModItems.hammerTerrasteel), "HHHHH", "HHHHH", "SSSS ", 'H', Names.ModOreDicts.INGOT_TERRASTEEL, 'S', "stickWood");
                addRecipe(new ItemStack(SHModItems.excavatorTerrasteel), " HHH ", "HHHHH", "SSSS ", 'H', Names.ModOreDicts.INGOT_TERRASTEEL, 'S', "stickWood");
            }
            if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_ELEMENTIUM))
            {
                addRecipe(new ItemStack(SHModItems.hammerElementium), "HHHHH", "HHHHH", "SSSS ", 'H', Names.ModOreDicts.INGOT_ELEMENTIUM, 'S', "stickWood");
                addRecipe(new ItemStack(SHModItems.excavatorElementium), " HHH ", "HHHHH", "SSSS ", 'H', Names.ModOreDicts.INGOT_ELEMENTIUM, 'S', "stickWood");
            }
            //EnderIO
            /*
            if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_DARKSTEEL))
            {
                addRecipe(new ItemStack(SHModItems.hammerDarksteel), "HHHHH", "HHHHH", "SSSS ", 'H', Names.ModOreDicts.INGOT_DARKSTEEL, 'S', "stickWood");
                addRecipe(new ItemStack(SHModItems.excavatorDarksteel), " HHH ", "HHHHH", "SSSS ", 'H', Names.ModOreDicts.INGOT_DARKSTEEL, 'S', "stickWood");
            }
            */
            //MobHunter
            if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_MACHALITE))
            {
                addRecipe(new ItemStack(SHModItems.hammerMachalite), "HHHHH", "HHHHH", "SSSS ", 'H', Names.ModOreDicts.INGOT_MACHALITE, 'S', "stickWood");
                addRecipe(new ItemStack(SHModItems.excavatorMachalite), " HHH ", "HHHHH", "SSSS ", 'H', Names.ModOreDicts.INGOT_MACHALITE, 'S', "stickWood");
            }
            if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_DRAGONITE))
            {
                addRecipe(new ItemStack(SHModItems.hammerDragonite), "HHHHH", "HHHHH", "SSSS ", 'H', Names.ModOreDicts.INGOT_DRAGONITE, 'S', "stickWood");
                addRecipe(new ItemStack(SHModItems.excavatorDragonite), " HHH ", "HHHHH", "SSSS ", 'H', Names.ModOreDicts.INGOT_DRAGONITE, 'S', "stickWood");
            }
            if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_GOSSAMITE))
            {
                addRecipe(new ItemStack(SHModItems.hammerGossamite), "HHHHH", "HHHHH", "SSSS ", 'H', Names.ModOreDicts.INGOT_GOSSAMITE, 'S', "stickWood");
                addRecipe(new ItemStack(SHModItems.excavatorGossamite), " HHH ", "HHHHH", "SSSS ", 'H', Names.ModOreDicts.INGOT_GOSSAMITE, 'S', "stickWood");
            }
            //Misc
            if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_BRONZE))
            {
                addRecipe(new ItemStack(SHModItems.hammerBronze), "HHHHH", "HHHHH", "SSSS ", 'H', Names.ModOreDicts.INGOT_BRONZE, 'S', "stickWood");
                addRecipe(new ItemStack(SHModItems.excavatorBronze), " HHH ", "HHHHH", "SSSS ", 'H', Names.ModOreDicts.INGOT_BRONZE, 'S', "stickWood");
            }
        }
    }

    public void addRecipe(ItemStack stack, Object ... recipeObj)
    {
        recipes.add(new HammerShapedOreRecipe(stack, recipeObj));
    }

    public ItemStack findMatchingRecipe(InventoryCrafting invCrafting, World world)
    {
        for(IRecipe irecipe : recipes)
            if(irecipe.matches(invCrafting, world))
                return irecipe.getCraftingResult(invCrafting);

        return null;
    }

    public ItemStack[] func_180303_b(InventoryCrafting p_180303_1_, World worldIn)
    {
        for (IRecipe irecipe : this.recipes)
        {
            if (irecipe.matches(p_180303_1_, worldIn))
            {
                return irecipe.getRemainingItems(p_180303_1_);
            }
        }

        ItemStack[] aitemstack = new ItemStack[p_180303_1_.getSizeInventory()];

        for (int i = 0; i < aitemstack.length; ++i)
        {
            aitemstack[i] = p_180303_1_.getStackInSlot(i);
        }

        return aitemstack;
    }

    /**
     * Returns the List<> of all recipes
     */
    public List getRecipeList()
    {
        return this.recipes;
    }
}
