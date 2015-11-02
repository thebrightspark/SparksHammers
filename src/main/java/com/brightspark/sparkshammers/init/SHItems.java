package com.brightspark.sparkshammers.init;

import com.brightspark.sparkshammers.item.ItemHammer;
import com.brightspark.sparkshammers.item.ItemHammerMjolnir;
import com.brightspark.sparkshammers.item.ItemHammerNetherStar;
import com.brightspark.sparkshammers.item.ItemResource;
import com.brightspark.sparkshammers.reference.HammerMaterials;
import com.brightspark.sparkshammers.reference.Names;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTool;

public class SHItems
{
    //Hammer heads
    public static final Item itemHeadWood = new ItemResource(Names.Items.HEAD_WOOD);
    public static final Item itemHeadStone = new ItemResource(Names.Items.HEAD_STONE);
    public static final Item itemHeadIron = new ItemResource(Names.Items.HEAD_IRON);
    public static final Item itemHeadGold = new ItemResource(Names.Items.HEAD_GOLD);
    public static final Item itemHeadDiamond = new ItemResource(Names.Items.HEAD_DIAMOND);
    //Hammers
    public static final ItemTool hammerWood = new ItemHammer(Names.Items.HAMMER_WOOD, HammerMaterials.HAMMER_WOOD);
    public static final ItemTool hammerStone = new ItemHammer(Names.Items.HAMMER_STONE, HammerMaterials.HAMMER_STONE);
    public static final ItemTool hammerIron = new ItemHammer(Names.Items.HAMMER_IRON, HammerMaterials.HAMMER_IRON);
    public static final ItemTool hammerGold = new ItemHammer(Names.Items.HAMMER_GOLD, HammerMaterials.HAMMER_GOLD);
    public static final ItemTool hammerDiamond = new ItemHammer(Names.Items.HAMMER_DIAMOND, HammerMaterials.HAMMER_DIAMOND);

    public static final ItemTool hammerNetherStar = new ItemHammerNetherStar();

    public static final ItemTool hammerThor = new ItemHammerMjolnir();

    public static void init()
    {
        //Heammer heads
        GameRegistry.registerItem(itemHeadWood, Names.Items.HEAD_WOOD);
        GameRegistry.registerItem(itemHeadStone, Names.Items.HEAD_STONE);
        GameRegistry.registerItem(itemHeadIron, Names.Items.HEAD_IRON);
        GameRegistry.registerItem(itemHeadGold, Names.Items.HEAD_GOLD);
        GameRegistry.registerItem(itemHeadDiamond, Names.Items.HEAD_DIAMOND);
        //Hammers
        GameRegistry.registerItem(hammerWood, Names.Items.HAMMER_WOOD);
        GameRegistry.registerItem(hammerStone, Names.Items.HAMMER_STONE);
        GameRegistry.registerItem(hammerIron, Names.Items.HAMMER_IRON);
        GameRegistry.registerItem(hammerGold, Names.Items.HAMMER_GOLD);
        GameRegistry.registerItem(hammerDiamond, Names.Items.HAMMER_DIAMOND);

        //GameRegistry.registerItem(hammerNetherStar, Names.Items.HAMMER_NETHERSTAR);

        //GameRegistry.registerItem(hammerThor, Names.Items.HAMMER_THOR);
    }
}
