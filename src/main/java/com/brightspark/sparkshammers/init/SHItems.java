package com.brightspark.sparkshammers.init;

import com.brightspark.sparkshammers.item.*;
import com.brightspark.sparkshammers.reference.Config;
import com.brightspark.sparkshammers.reference.Materials;
import com.brightspark.sparkshammers.reference.Names;
import com.brightspark.sparkshammers.util.ClientUtils;
import com.brightspark.sparkshammers.util.LoaderHelper;
import com.brightspark.sparkshammers.util.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SHItems
{
    //Contains all items
    public static Map<String, Item> ITEMS = new HashMap<String, Item>();
    //Contains all of the AOE tools
    public static List<ItemAOE> AOE_TOOLS = new ArrayList<ItemAOE>();
    //Contains all of the items which use a basic coloured texture
    private static List<Item> COLOURED_ITEMS = new ArrayList<Item>();

    //Tool Heads
    public static ItemResource hammerHeadWood, excavatorHeadWood;

    //Special Hammers
    public static ItemAOE hammerThor, hammerMini, hammerGiant, hammerNetherStar;

    //Debug
    public static ItemDebug debug;

    public static Item getItemById(String itemId)
    {
        return ITEMS.get(itemId);
    }

    private static void regGeneralItem(Item item)
    {
        GameRegistry.register(item);
        ITEMS.put(item.getRegistryName().getResourcePath(), item);
        if(item instanceof IColourable && ((IColourable)item).getTextureColour() >= 0)
            COLOURED_ITEMS.add(item);
    }

    public static void regItem(Item item)
    {
        regGeneralItem(item);
        if(item instanceof ItemAOE)
            AOE_TOOLS.add((ItemAOE) item);
    }

    public static void regAOE(Names.EnumMaterials mat)
    {
        regAOE(mat, false);
    }

    public static void regAOE(Names.EnumMaterials mat, boolean isExcavator)
    {
        ItemAOE tool = new ItemAOE(mat, isExcavator);
        regItem(tool);
    }

    public static void regItems()
    {
        //Tool Heads
        regItem(hammerHeadWood = new ItemResource(Names.Items.HAMMER_HEAD_WOOD));
        regItem(excavatorHeadWood = new ItemResource(Names.Items.EXCAVATOR_HEAD_WOOD));

        //Special Hammers
        regItem(hammerThor = new ItemHammerThor());
        regItem(hammerMini = new ItemAOE(Names.Items.HAMMER_MINI, Materials.MINI).setMineWidth(0).setShiftRotating(true));
        regItem(hammerGiant = new ItemAOE(Names.EnumMaterials.GIANT).setMineWidth(4).setMineHeight(4));
        regItem(hammerNetherStar = new ItemHammerNetherStar());

        //Debug
        regItem(debug = new ItemDebug());

        //All Regular Hammers And Excavators
        Names.EnumMaterials[] materials = Config.includeOtherModItems ? Names.EnumMaterials.values() : Names.EnumMaterials.VANILLA;
        for(Names.EnumMaterials mat : materials)
        {
            if(mat.dependantOreDic == null && mat.dependantItem == null)
            {
                LogHelper.warn("No dependant ore dictionary or item stack for material " + mat);
                continue;
            }
            LogHelper.info("Registering material " + mat);
            switch(mat)
            {
                case MANASTEEL:
                case TERRASTEEL:
                case ELEMENTIUM:
                    if(LoaderHelper.isModLoaded(Names.Mods.BOTANIA))
                        SHItemsBotania.regItems(mat);
                    break;
                default:
                    regAOE(mat);
                    regAOE(mat, true);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public static void regModels()
    {
        //Register all item models
        for(Item tool : ITEMS.values())
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
