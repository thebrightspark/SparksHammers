package com.brightspark.sparkshammers.init;

import com.brightspark.sparkshammers.customTools.CustomTools;
import com.brightspark.sparkshammers.customTools.Tool;
import com.brightspark.sparkshammers.item.*;
import com.brightspark.sparkshammers.reference.Config;
import com.brightspark.sparkshammers.reference.Names;
import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.*;

public class SHItems
{
    //Contains all items
    public static Map<String, Item> ITEMS = new HashMap<String, Item>();
    //Contains all of the AOE tools
    public static List<ItemAOE> AOE_TOOLS = new ArrayList<ItemAOE>();
    //Contains all of the items which use a basic coloured texture
    private static List<Item> COLOURED_ITEMS = new ArrayList<Item>();
    //A list of material names of my tools which aren't made from other modded items
    private static List<String> VANILLA_NAMES = Lists.newArrayList("wood", "stone", "iron", "gold", "diamond", "mjolnir", "giant", "mini", "netherstar", "powered");

    //Tool Heads
    public static ItemResource hammerHeadWood, excavatorHeadWood;

    //Special Hammers
    public static ItemAOE hammerMjolnir, hammerMini, hammerGiant, hammerNetherStar, hammerPowered;

    //Debug
    public static ItemDebug debug;

    public static Item getItemById(String itemId)
    {
        return ITEMS.get(itemId.toLowerCase());
    }

    private static void regGeneralItem(Item item)
    {
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

    public static void regTool(Tool tool)
    {
        String name = tool.materialName;

        //Don't register if tool is made from other mod materials and respective config is disabled
        if(!Config.enableOtherModItems && !VANILLA_NAMES.contains(name))
            return;

        if(name.equals("wood"))
        {
            regItem(hammerHeadWood = new ItemResource(Names.Items.HAMMER_HEAD_WOOD));
            regItem(excavatorHeadWood = new ItemResource(Names.Items.EXCAVATOR_HEAD_WOOD));
            regItem(new ItemAOE(tool, false));
            regItem(new ItemAOE(tool, true));
        }
        else if(name.equals("mjolnir"))
        {
            if(Config.enableMjolnir)
                regItem(hammerMjolnir = new ItemHammerMjolnir(tool));
        }
        else if(name.equals("mini"))
        {
            if(Config.enableMiniHammer)
                regItem(hammerMini = new ItemAOE(tool, false).setMineWidth(0).setShiftRotating(true));
        }
        else if(name.equals("giant"))
        {
            if(Config.enableGiantHammer)
                regItem(hammerGiant = new ItemAOE(tool, false).setMineWidth(4).setMineHeight(4));
        }
        else if(name.equals("netherstar"))
        {
            if(Config.enableNetherStarHammer)
                regItem(hammerNetherStar = new ItemHammerNetherStar(tool));
        }
        else if(name.equals("powered"))
        {
            if(Config.enablePoweredHammer)
                regItem(hammerPowered = new ItemHammerEnergy(tool));
        }
        //TODO: Re-add Botania tools when the mod gets updated!
        /*
        else if((name.equals("manasteel") || name.equals("elementium") || name.equals("terrasteel")) && LoaderHelper.isModLoaded(Names.Mods.BOTANIA))
            SHItemsBotania.regItems(mat);
        */
        else
        {
            regItem(new ItemAOE(tool, false));
            regItem(new ItemAOE(tool, true));
        }
    }

    public static void regItems()
    {
        //Only register once
        if(!ITEMS.isEmpty()) return;

        //Debug
        regItem(debug = new ItemDebug());

        //Gets tools from json file
        List<Tool> tools = CustomTools.read();
        //Register tools from json
        for(Tool tool : tools)
            regTool(tool);

        //Because I want both wooden heads to be next to each other in JEI XD
        if(hammerHeadWood != null)
            regItem(hammerHeadWood = new ItemResource(Names.Items.HAMMER_HEAD_WOOD));
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
