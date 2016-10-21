package com.brightspark.sparkshammers.init;

import com.brightspark.sparkshammers.item.*;
import com.brightspark.sparkshammers.reference.Materials;
import com.brightspark.sparkshammers.reference.Names;
import com.brightspark.sparkshammers.util.ClientUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class SHItems
{
    //Contains all items
    public static List<Item> ITEMS = new ArrayList<Item>();
    //Contains all of the AOE tools
    public static List<ItemAOE> AOE_TOOLS = new ArrayList<ItemAOE>();
    //Contains all of the items which use a basic coloured texture
    public static List<Item> COLOURED_ITEMS = new ArrayList<Item>();

    //Tool Heads
    public static ItemResource hammerHeadWood, excavatorHeadWood;

    //Hammers
    public static ItemAOE hammerWood, hammerStone, hammerIron, hammerGold, hammerDiamond,
            hammerThor, hammerMini, hammerGiant, hammerNetherStar,
    //Excavators
            excavatorWood, excavatorStone, excavatorIron, excavatorGold, excavatorDiamond;

    //Debug
    public static ItemDebug debug;

    public static void regItem(Item item)
    {
        GameRegistry.register(item);
        ITEMS.add(item);
        if(item instanceof ItemAOE)
            AOE_TOOLS.add((ItemAOE) item);
        if(item instanceof IColourable && ((IColourable)item).getTextureColour() >= 0)
            COLOURED_ITEMS.add(item);
    }

    public static void regItems()
    {
        //Hammer Heads
        regItem(hammerHeadWood = new ItemResource(Names.Items.HAMMER_HEAD_WOOD));

        //Hammers
        regItem(hammerWood = new ItemAOE(Names.EnumMaterials.WOOD));
        regItem(hammerStone = new ItemAOE(Names.EnumMaterials.STONE));
        regItem(hammerIron = new ItemAOE(Names.EnumMaterials.IRON));
        regItem(hammerGold = new ItemAOE(Names.EnumMaterials.GOLD));
        regItem(hammerDiamond = new ItemAOE(Names.EnumMaterials.DIAMOND));

        regItem(hammerThor = new ItemHammerThor());
        regItem(hammerMini = new ItemAOE(Names.Items.HAMMER_MINI, Materials.MINI).setMineWidth(0).setShiftRotating(true));
        regItem(hammerGiant = new ItemAOE(Names.EnumMaterials.GIANT).setMineWidth(4).setMineHeight(4));
        regItem(hammerNetherStar = new ItemHammerNetherStar());

        //Excavator Heads
        regItem(excavatorHeadWood = new ItemResource(Names.Items.EXCAVATOR_HEAD_WOOD));

        //Excavators
        regItem(excavatorWood = new ItemAOE(Names.EnumMaterials.WOOD, true));
        regItem(excavatorStone = new ItemAOE(Names.EnumMaterials.STONE, true));
        regItem(excavatorIron = new ItemAOE(Names.EnumMaterials.IRON, true));
        regItem(excavatorGold = new ItemAOE(Names.EnumMaterials.GOLD, true));
        regItem(excavatorDiamond = new ItemAOE(Names.EnumMaterials.DIAMOND, true));

        //Debug
        regItem(debug = new ItemDebug());
    }

    @SideOnly(Side.CLIENT)
    public static void regModels()
    {
        //Register all item models
        for(Item tool : ITEMS)
            ClientUtils.regModel(tool);
    }

    @SideOnly(Side.CLIENT)
    public static void regColours()
    {
        //Register item colours
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor()
        {
            @Override
            public int getColorFromItemstack(ItemStack stack, int tintIndex)
            {
                return tintIndex == 0 ? ((IColourable)stack.getItem()).getTextureColour() : -1;
            }
        }, COLOURED_ITEMS.toArray(new Item[]{}));
    }
}
