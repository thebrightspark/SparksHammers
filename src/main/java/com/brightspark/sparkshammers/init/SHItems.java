package com.brightspark.sparkshammers.init;

import com.brightspark.sparkshammers.item.*;
import com.brightspark.sparkshammers.reference.Materials;
import com.brightspark.sparkshammers.reference.Names;
import com.brightspark.sparkshammers.util.ClientUtils;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SHItems
{
    //Hammer Heads
    public static final ItemResource hammerHeadWood = new ItemResource(Names.Items.HAMMER_HEAD_WOOD);
    //Hammers
    public static final ItemHammer hammerWood = new ItemHammer(Names.Items.HAMMER_WOOD, Materials.HAMMER_WOOD);
    public static final ItemHammer hammerStone = new ItemHammer(Names.Items.HAMMER_STONE, Materials.HAMMER_STONE);
    public static final ItemHammer hammerIron = new ItemHammer(Names.Items.HAMMER_IRON, Materials.HAMMER_IRON);
    public static final ItemHammer hammerGold = new ItemHammer(Names.Items.HAMMER_GOLD, Materials.HAMMER_GOLD);
    public static final ItemHammer hammerDiamond = new ItemHammer(Names.Items.HAMMER_DIAMOND, Materials.HAMMER_DIAMOND);

    public static final ItemHammer hammerThor = new ItemHammerThor();
    public static final ItemHammer hammerMini = new ItemHammer(Names.Items.HAMMER_MINI, Materials.HAMMER_MINI).setMineWidth(0).setShiftRotating(true);
    public static final ItemHammer hammerGiant = new ItemHammer(Names.Items.HAMMER_GIANT, Materials.HAMMER_GIANT).setMineWidth(4).setMineHeight(4);
    public static final ItemHammer hammerNetherStar = new ItemHammerNetherStar();

    //Excavator Heads
    public static final ItemResource excavatorHeadWood = new ItemResource(Names.Items.EXCAVATOR_HEAD_WOOD);
    //Excavators
    public static final ItemExcavator excavatorWood = new ItemExcavator(Names.Items.EXCAVATOR_WOOD, Materials.HAMMER_WOOD);
    public static final ItemExcavator excavatorStone = new ItemExcavator(Names.Items.EXCAVATOR_STONE, Materials.HAMMER_STONE);
    public static final ItemExcavator excavatorIron = new ItemExcavator(Names.Items.EXCAVATOR_IRON, Materials.HAMMER_IRON);
    public static final ItemExcavator excavatorGold = new ItemExcavator(Names.Items.EXCAVATOR_GOLD, Materials.HAMMER_GOLD);
    public static final ItemExcavator excavatorDiamond = new ItemExcavator(Names.Items.EXCAVATOR_DIAMOND, Materials.HAMMER_DIAMOND);

    public static void regItems()
    {
        //Heammer Heads
        GameRegistry.registerItem(hammerHeadWood, Names.Items.HAMMER_HEAD_WOOD);
        //Hammers
        GameRegistry.registerItem(hammerWood, Names.Items.HAMMER_WOOD);
        GameRegistry.registerItem(hammerStone, Names.Items.HAMMER_STONE);
        GameRegistry.registerItem(hammerIron, Names.Items.HAMMER_IRON);
        GameRegistry.registerItem(hammerGold, Names.Items.HAMMER_GOLD);
        GameRegistry.registerItem(hammerDiamond, Names.Items.HAMMER_DIAMOND);

        GameRegistry.registerItem(hammerThor, Names.Items.HAMMER_THOR);
        GameRegistry.registerItem(hammerMini, Names.Items.HAMMER_MINI);
        GameRegistry.registerItem(hammerGiant, Names.Items.HAMMER_GIANT);
        //GameRegistry.registerItem(hammerNetherStar, Names.Items.HAMMER_NETHERSTAR);

        //Excavator Heads
        GameRegistry.registerItem(excavatorHeadWood, Names.Items.EXCAVATOR_HEAD_WOOD);
        //Excavators
        GameRegistry.registerItem(excavatorWood, Names.Items.EXCAVATOR_WOOD);
        GameRegistry.registerItem(excavatorStone, Names.Items.EXCAVATOR_STONE);
        GameRegistry.registerItem(excavatorIron, Names.Items.EXCAVATOR_IRON);
        GameRegistry.registerItem(excavatorGold, Names.Items.EXCAVATOR_GOLD);
        GameRegistry.registerItem(excavatorDiamond, Names.Items.EXCAVATOR_DIAMOND);
    }

    public static void regModels()
    {
        //Hammers
        ClientUtils.regModel(hammerHeadWood);
        ClientUtils.regModel(hammerWood);
        ClientUtils.regModel(hammerStone);
        ClientUtils.regModel(hammerIron);
        ClientUtils.regModel(hammerGold);
        ClientUtils.regModel(hammerDiamond);

        ClientUtils.regModel(hammerThor);
        ClientUtils.regModel(hammerMini);
        ClientUtils.regModel(hammerGiant);
        //Common.regModel(hammerNetherStar);

        //Excavators
        ClientUtils.regModel(excavatorHeadWood);
        ClientUtils.regModel(excavatorWood);
        ClientUtils.regModel(excavatorStone);
        ClientUtils.regModel(excavatorIron);
        ClientUtils.regModel(excavatorGold);
        ClientUtils.regModel(excavatorDiamond);
    }
}
