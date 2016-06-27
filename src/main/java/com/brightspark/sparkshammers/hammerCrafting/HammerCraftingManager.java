package com.brightspark.sparkshammers.hammerCrafting;

import com.brightspark.sparkshammers.init.SHItems;
import com.brightspark.sparkshammers.init.SHModItems;
import com.brightspark.sparkshammers.reference.Config;
import com.brightspark.sparkshammers.reference.Names;
import com.brightspark.sparkshammers.util.LoaderHelper;
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

    @SuppressWarnings("all")
    private HammerCraftingManager()
    {
        //Add recipes here

        //Vanilla
        addRecipe(new ItemStack(SHItems.hammerWood), new Object[]{"HHHHH", "HHHHH", "SSSS ", 'H', "plankWood", 'S', "stickWood"});
        addRecipe(new ItemStack(SHItems.hammerIron), new Object[]{"HHHHH", "HHHHH", "SSSS ", 'H', Items.IRON_INGOT, 'S', "stickWood"});
        addRecipe(new ItemStack(SHItems.hammerGold), new Object[]{"HHHHH", "HHHHH", "SSSS ", 'H', Items.GOLD_INGOT, 'S', "stickWood"});
        addRecipe(new ItemStack(SHItems.hammerDiamond), new Object[]{"HHHHH", "HHHHH", "SSSS ", 'H', Items.DIAMOND, 'S', "stickWood"});

        addRecipe(new ItemStack(SHItems.hammerMini), new Object[]{" HHH ", " HHH ", "SSSS ", 'H', Items.IRON_INGOT, 'S', "stickWood"});
        addRecipe(new ItemStack(SHItems.hammerGiant), new Object[]{"HHHHH", "HHDHH", "SSSS ", 'H', Blocks.IRON_BLOCK, 'S', "stickWood", 'D', new ItemStack(Items.DYE, 1, 5)});
        addRecipe(new ItemStack(SHItems.hammerNetherStar), new Object[]{"HHBHH", "HBNBH", "SSSS ", 'H', Items.DIAMOND, 'B', Blocks.GOLD_BLOCK, 'N', Items.NETHER_STAR, 'S', "stickWood"});

        addRecipe(new ItemStack(SHItems.excavatorWood), new Object[]{" HHH ", "HHHHH", "SSSS ", 'H', "plankWood", 'S', "stickWood"});
        addRecipe(new ItemStack(SHItems.excavatorIron), new Object[]{" HHH ", "HHHHH", "SSSS ", 'H', Items.IRON_INGOT, 'S', "stickWood"});
        addRecipe(new ItemStack(SHItems.excavatorGold), new Object[]{" HHH ", "HHHHH", "SSSS ", 'H', Items.GOLD_INGOT, 'S', "stickWood"});
        addRecipe(new ItemStack(SHItems.excavatorDiamond), new Object[]{" HHH ", "HHHHH", "SSSS ", 'H', Items.DIAMOND, 'S', "stickWood"});

        //Make stone recipes made of compressed cobble when Extra Utilities is installed
        if(LoaderHelper.isModLoaded(Names.Mods.EXTRA_UTILITIES))
        {
            Item compressedCobble = (Item) Item.REGISTRY.getObject(new ResourceLocation(Names.ModItemIds.COMPRESSED_COBBLE));
            ItemStack cobble1x = new ItemStack(compressedCobble, 1, 0);
            addRecipe(new ItemStack(SHItems.hammerStone), new Object[]{"HHHHH", "HHHHH", "SSSS ", 'H', cobble1x, 'S', "stickWood"});
            addRecipe(new ItemStack(SHItems.excavatorStone), new Object[]{" HHH ", "HHHHH", "SSSS ", 'H', cobble1x, 'S', "stickWood"});
        }
        else
        {
            addRecipe(new ItemStack(SHItems.hammerStone), new Object[]{"HHHHH", "HHHHH", "SSSS ", 'H', Blocks.COBBLESTONE, 'S', "stickWood"});
            addRecipe(new ItemStack(SHItems.excavatorStone), new Object[]{" HHH ", "HHHHH", "SSSS ", 'H', Blocks.COBBLESTONE, 'S', "stickWood"});
        }

        //Modded
        if(Config.includeOtherModItems)
        {
            //Botania
            if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_MANASTEEL))
            {
                addRecipe(new ItemStack(SHModItems.hammerManasteel), new Object[]{"HHHHH", "HHHHH", "SSSS ", 'H', Names.ModOreDicts.INGOT_MANASTEEL, 'S', "stickWood"});
                addRecipe(new ItemStack(SHModItems.excavatorManasteel), new Object[]{" HHH ", "HHHHH", "SSSS ", 'H', Names.ModOreDicts.INGOT_MANASTEEL, 'S', "stickWood"});
            }
            if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_TERRASTEEL))
            {
                addRecipe(new ItemStack(SHModItems.hammerTerrasteel), new Object[]{"HHHHH", "HHHHH", "SSSS ", 'H', Names.ModOreDicts.INGOT_TERRASTEEL, 'S', "stickWood"});
                addRecipe(new ItemStack(SHModItems.excavatorTerrasteel), new Object[]{" HHH ", "HHHHH", "SSSS ", 'H', Names.ModOreDicts.INGOT_TERRASTEEL, 'S', "stickWood"});
            }
            if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_ELEMENTIUM))
            {
                addRecipe(new ItemStack(SHModItems.hammerElementium), new Object[]{"HHHHH", "HHHHH", "SSSS ", 'H', Names.ModOreDicts.INGOT_ELEMENTIUM, 'S', "stickWood"});
                addRecipe(new ItemStack(SHModItems.excavatorElementium), new Object[]{" HHH ", "HHHHH", "SSSS ", 'H', Names.ModOreDicts.INGOT_ELEMENTIUM, 'S', "stickWood"});
            }
            //EnderIO
            if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_DARKSTEEL))
            {
                addRecipe(new ItemStack(SHModItems.hammerDarksteel), new Object[]{"HHHHH", "HHHHH", "SSSS ", 'H', Names.ModOreDicts.INGOT_DARKSTEEL, 'S', "stickWood"});
                addRecipe(new ItemStack(SHModItems.excavatorDarksteel), new Object[]{" HHH ", "HHHHH", "SSSS ", 'H', Names.ModOreDicts.INGOT_DARKSTEEL, 'S', "stickWood"});
            }
            //Extra Utilities
            /*
            if(!Config.useEasyUnstableRecipe && LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_UNSTABLE))
            {
                addRecipe(new ItemStack(SHModItems.hammerUnstable), new Object[]{"HHHHH", "HHHHH", "SSSS ", 'H', Names.ModOreDicts.INGOT_UNSTABLE, 'S', "stickWood"});
                addRecipe(new ItemStack(SHModItems.excavatorUnstable), new Object[]{" HHH ", "HHHHH", "SSSS ", 'H', Names.ModOreDicts.INGOT_UNSTABLE, 'S', "stickWood"});
            }
            */
            //Misc
            if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_BRONZE))
            {
                addRecipe(new ItemStack(SHModItems.hammerBronze), new Object[]{"HHHHH", "HHHHH", "SSSS ", 'H', Names.ModOreDicts.INGOT_BRONZE, 'S', "stickWood"});
                addRecipe(new ItemStack(SHModItems.excavatorBronze), new Object[]{" HHH ", "HHHHH", "SSSS ", 'H', Names.ModOreDicts.INGOT_BRONZE, 'S', "stickWood"});
            }
        }
    }

    public void addRecipe(ItemStack stack, Object ... recipeObj)
    {
        recipes.add(new HammerShapedOreRecipe(stack, recipeObj));
    }

    public ItemStack findMatchingRecipe(InventoryCrafting invCrafting, World world)
    {
        int j;
        for (j = 0; j < this.recipes.size(); ++j)
        {
            IRecipe irecipe = this.recipes.get(j);

            if (irecipe.matches(invCrafting, world))
            {
                return irecipe.getCraftingResult(invCrafting);
            }
        }

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
     * returns the List<> of all recipes
     */
    public List getRecipeList()
    {
        return this.recipes;
    }
}
