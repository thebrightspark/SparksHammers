package com.brightspark.sparkshammers.init;

import com.brightspark.sparkshammers.item.ItemHammer;
import com.brightspark.sparkshammers.item.ItemHammerMjolnir;
import com.brightspark.sparkshammers.item.ItemResource;
import com.brightspark.sparkshammers.reference.Names;
import com.brightspark.sparkshammers.reference.Reference;
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
    public static final ItemTool hammerWood = new ItemHammer(Names.Items.HAMMER_WOOD, Reference.HAMMER_WOOD);
    public static final ItemTool hammerStone = new ItemHammer(Names.Items.HAMMER_STONE, Reference.HAMMER_STONE);
    public static final ItemTool hammerIron = new ItemHammer(Names.Items.HAMMER_IRON, Reference.HAMMER_IRON);
    public static final ItemTool hammerGold = new ItemHammer(Names.Items.HAMMER_GOLD, Reference.HAMMER_GOLD);
    public static final ItemTool hammerDiamond = new ItemHammer(Names.Items.HAMMER_DIAMOND, Reference.HAMMER_DIAMOND);

    public static final ItemTool hammerThor = new ItemHammerMjolnir();

    public static void init()
    {
        //Register items here
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

        //GameRegistry.registerItem(hammerThor, Names.Items.HAMMER_THOR);
    }
}
