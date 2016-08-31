package com.brightspark.sparkshammers.init;

import com.brightspark.sparkshammers.item.*;
import com.brightspark.sparkshammers.reference.Materials;
import com.brightspark.sparkshammers.reference.Names;
import com.brightspark.sparkshammers.util.ClientUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;

public class SHItems
{
    //Contains all of the AOE tools
    public static List<ItemAOE> ALL_AOE_TOOLS = new ArrayList<ItemAOE>();
    //Contains all of the tools which use a basic coloured texture
    public static List<ItemAOE> COLOUR_TOOLS = new ArrayList<ItemAOE>();

    //Hammer Heads
    public static final ItemResource hammerHeadWood = new ItemResource(Names.Items.HAMMER_HEAD_WOOD);
    //Hammers
    public static final ItemAOE hammerWood = new ItemAOE(Names.EnumMaterials.WOOD);
    public static final ItemAOE hammerStone = new ItemAOE(Names.EnumMaterials.STONE);
    public static final ItemAOE hammerIron = new ItemAOE(Names.EnumMaterials.IRON);
    public static final ItemAOE hammerGold = new ItemAOE(Names.EnumMaterials.GOLD);
    public static final ItemAOE hammerDiamond = new ItemAOE(Names.EnumMaterials.DIAMOND);

    public static final ItemAOE hammerThor = new ItemHammerThor();
    public static final ItemAOE hammerMini = new ItemAOE(Names.Items.HAMMER_MINI, Materials.MINI).setMineWidth(0).setShiftRotating(true);
    public static final ItemAOE hammerGiant = new ItemAOE(Names.Items.HAMMER_GIANT, Materials.GIANT).setMineWidth(4).setMineHeight(4);
    public static final ItemAOE hammerNetherStar = new ItemHammerNetherStar();

    //Excavator Heads
    public static final ItemResource excavatorHeadWood = new ItemResource(Names.Items.EXCAVATOR_HEAD_WOOD);
    //Excavators
    /*
    public static final ItemAOE excavatorWood = new ItemAOE(Names.Items.EXCAVATOR_WOOD, Materials.HAMMER_WOOD, true);
    public static final ItemAOE excavatorStone = new ItemAOE(Names.Items.EXCAVATOR_STONE, Materials.HAMMER_STONE, true);
    public static final ItemAOE excavatorIron = new ItemAOE(Names.Items.EXCAVATOR_IRON, Materials.HAMMER_IRON, true);
    public static final ItemAOE excavatorGold = new ItemAOE(Names.Items.EXCAVATOR_GOLD, Materials.HAMMER_GOLD, true);
    public static final ItemAOE excavatorDiamond = new ItemAOE(Names.Items.EXCAVATOR_DIAMOND, Materials.HAMMER_DIAMOND, true);
    */

    //Debug
    public static final ItemDebug debug = new ItemDebug();

    private static void regAOE(ItemAOE tool, boolean isColour)
    {
        GameRegistry.register(tool);
        ALL_AOE_TOOLS.add(tool);
        if(isColour)
            COLOUR_TOOLS.add(tool);
    }

    public static void regItems()
    {
        //Heammer Heads
        GameRegistry.register(hammerHeadWood);
        //Hammers
        regAOE(hammerWood, true);
        regAOE(hammerStone, true);
        regAOE(hammerIron, true);
        regAOE(hammerGold, true);
        regAOE(hammerDiamond, true);

        regAOE(hammerThor, false);
        regAOE(hammerMini, false);
        regAOE(hammerGiant, false);
        regAOE(hammerNetherStar, false);

        //Excavator Heads
        GameRegistry.register(excavatorHeadWood);
        //Excavators
        /*
        regAOE(excavatorWood, true);
        regAOE(excavatorStone, true);
        regAOE(excavatorIron, true);
        regAOE(excavatorGold, true);
        regAOE(excavatorDiamond, true);
        */

        //Debug
        GameRegistry.register(debug);
    }

    public static void regModels()
    {
        //Hammers
        ClientUtils.regModel(hammerHeadWood);
        /*
        ClientUtils.regModel(hammerWood);
        ClientUtils.regModel(hammerStone);
        ClientUtils.regModel(hammerIron);
        ClientUtils.regModel(hammerGold);
        ClientUtils.regModel(hammerDiamond);

        ClientUtils.regModel(hammerThor);
        ClientUtils.regModel(hammerMini);
        ClientUtils.regModel(hammerGiant);
        ClientUtils.regModel(hammerNetherStar);
        */

        //Excavators
        ClientUtils.regModel(excavatorHeadWood);
        /*
        ClientUtils.regModel(excavatorWood);
        ClientUtils.regModel(excavatorStone);
        ClientUtils.regModel(excavatorIron);
        ClientUtils.regModel(excavatorGold);
        ClientUtils.regModel(excavatorDiamond);
        */

        //Hammers and Excavators
        for(ItemAOE tool : ALL_AOE_TOOLS)
            ClientUtils.regModel(tool);

        //Register item colours
        ItemColors itemColors = Minecraft.getMinecraft().getItemColors();
        itemColors.registerItemColorHandler(new IItemColor()
        {
            @Override
            public int getColorFromItemstack(ItemStack stack, int tintIndex)
            {
                return tintIndex == 0 ? ((ItemAOE)stack.getItem()).textureColour : -1;
            }
        }, COLOUR_TOOLS.toArray(new Item[]{}));

        //Debug
        ClientUtils.regModel(debug);
    }
}
