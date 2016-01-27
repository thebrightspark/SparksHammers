package com.brightspark.sparkshammers.hammerCrafting;

import com.brightspark.sparkshammers.init.SHItems;
import com.brightspark.sparkshammers.init.SHModItems;
import com.brightspark.sparkshammers.reference.Config;
import com.brightspark.sparkshammers.reference.Names;
import com.brightspark.sparkshammers.util.LoaderHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class HammerCraftingManager
{
    /** The static instance of this class */
    private static final HammerCraftingManager instance = new HammerCraftingManager();
    /** A list of all the recipes added */
    private List recipes = new ArrayList();

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
        if(LoaderHelper.isModLoaded(Names.Mods.EXTRA_UTILITIES)) //Make stone head recipe made of compressed cobble when Extra Utilities is installed
            addRecipe(new ItemStack(SHItems.hammerStone), new Object[]{"HHHHH", "HHHHH", "SSSS ", 'H', Names.ModOreDicts.COMPRESSED_COBBLE_1, 'S', "stickWood"});
        else
            addRecipe(new ItemStack(SHItems.hammerStone), new Object[]{"HHHHH", "HHHHH", "SSSS ", 'H', Blocks.cobblestone, 'S', "stickWood"});
        addRecipe(new ItemStack(SHItems.hammerIron), new Object[]{"HHHHH", "HHHHH", "SSSS ", 'H', Items.iron_ingot, 'S', "stickWood"});
        addRecipe(new ItemStack(SHItems.hammerGold), new Object[]{"HHHHH", "HHHHH", "SSSS ", 'H', Items.gold_ingot, 'S', "stickWood"});
        addRecipe(new ItemStack(SHItems.hammerDiamond), new Object[]{"HHHHH", "HHHHH", "SSSS ", 'H', Items.diamond, 'S', "stickWood"});

        addRecipe(new ItemStack(SHItems.excavatorWood), new Object[]{" HHH ", "HHHHH", "SSSS ", 'H', "plankWood", 'S', "stickWood"});
        if(LoaderHelper.isModLoaded(Names.Mods.EXTRA_UTILITIES)) //Make stone head recipe made of compressed cobble when Extra Utilities is installed
            addRecipe(new ItemStack(SHItems.excavatorStone), new Object[]{" HHH ", "HHHHH", "SSSS ", 'H', Names.ModOreDicts.COMPRESSED_COBBLE_1, 'S', "stickWood"});
        else
            addRecipe(new ItemStack(SHItems.excavatorStone), new Object[]{" HHH ", "HHHHH", "SSSS ", 'H', Blocks.cobblestone, 'S', "stickWood"});
        addRecipe(new ItemStack(SHItems.excavatorIron), new Object[]{" HHH ", "HHHHH", "SSSS ", 'H', Items.iron_ingot, 'S', "stickWood"});
        addRecipe(new ItemStack(SHItems.excavatorGold), new Object[]{" HHH ", "HHHHH", "SSSS ", 'H', Items.gold_ingot, 'S', "stickWood"});
        addRecipe(new ItemStack(SHItems.excavatorDiamond), new Object[]{" HHH ", "HHHHH", "SSSS ", 'H', Items.diamond, 'S', "stickWood"});

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
            if(!Config.useEasyUnstableRecipe && LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_UNSTABLE))
            {
                addRecipe(new ItemStack(SHModItems.hammerUnstable), new Object[]{"HHHHH", "HHHHH", "SSSS ", 'H', Names.ModOreDicts.INGOT_UNSTABLE, 'S', "stickWood"});
                addRecipe(new ItemStack(SHModItems.excavatorUnstable), new Object[]{" HHH ", "HHHHH", "SSSS ", 'H', Names.ModOreDicts.INGOT_UNSTABLE, 'S', "stickWood"});
            }
            //Misc
            if(LoaderHelper.doesOreExist(Names.ModOreDicts.INGOT_BRONZE))
            {
                addRecipe(new ItemStack(SHModItems.hammerBronze), new Object[]{"HHHHH", "HHHHH", "SSSS ", 'H', Names.ModOreDicts.INGOT_BRONZE, 'S', "stickWood"});
                addRecipe(new ItemStack(SHModItems.excavatorBronze), new Object[]{" HHH ", "HHHHH", "SSSS ", 'H', Names.ModOreDicts.INGOT_BRONZE, 'S', "stickWood"});
            }
        }
    }

    @SuppressWarnings("unchecked")
    public HammerShapedOreRecipe addRecipe(ItemStack stack, Object ... recipeObj)
    {
        HammerShapedOreRecipe recipe = new HammerShapedOreRecipe(stack, recipeObj);
        recipes.add(recipe);
        return recipe;

        //Old code
        /*
        String s = "";
        int i = 0;
        int j = 0;
        int k = 0;

        if (recipeObj[i] instanceof String[])
        {
            String[] astring = (String[])((String[])recipeObj[i++]);

            for (int l = 0; l < astring.length; ++l)
            {
                String s1 = astring[l];
                ++k;
                j = s1.length();
                s = s + s1;
            }
        }
        else
        {
            while (recipeObj[i] instanceof String)
            {
                String s2 = (String)recipeObj[i++];
                ++k;
                j = s2.length();
                s = s + s2;
            }
        }

        HashMap hashmap;

        for (hashmap = new HashMap(); i < recipeObj.length; i += 2)
        {
            Character character = (Character)recipeObj[i];
            ItemStack itemstack1 = null;

            if (recipeObj[i + 1] instanceof Item)
            {
                itemstack1 = new ItemStack((Item)recipeObj[i + 1]);
            }
            else if (recipeObj[i + 1] instanceof Block)
            {
                itemstack1 = new ItemStack((Block)recipeObj[i + 1], 1, 32767);
            }
            else if (recipeObj[i + 1] instanceof ItemStack)
            {
                itemstack1 = (ItemStack)recipeObj[i + 1];
            }

            hashmap.put(character, itemstack1);
        }

        ItemStack[] aitemstack = new ItemStack[j * k];

        for (int i1 = 0; i1 < j * k; ++i1)
        {
            char c0 = s.charAt(i1);

            if (hashmap.containsKey(Character.valueOf(c0)))
            {
                aitemstack[i1] = ((ItemStack)hashmap.get(Character.valueOf(c0))).copy();
            }
            else
            {
                aitemstack[i1] = null;
            }
        }

        ShapedRecipes shapedrecipes = new ShapedRecipes(j, k, aitemstack, stack);
        this.recipes.add(shapedrecipes);
        return shapedrecipes;
        */
    }

    public ItemStack findMatchingRecipe(InventoryCrafting invCrafting, World world)
    {
        int j;
        for (j = 0; j < this.recipes.size(); ++j)
        {
            IRecipe irecipe = (IRecipe)this.recipes.get(j);

            if (irecipe.matches(invCrafting, world))
            {
                return irecipe.getCraftingResult(invCrafting);
            }
        }

        return null;
    }

    /**
     * returns the List<> of all recipes
     */
    public List getRecipeList()
    {
        return this.recipes;
    }
}
