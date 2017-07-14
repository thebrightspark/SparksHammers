package brightspark.sparkshammers.init;

import brightspark.sparkshammers.customTools.CustomTools;
import brightspark.sparkshammers.customTools.Tool;
import brightspark.sparkshammers.item.*;
import brightspark.sparkshammers.reference.Config;
import brightspark.sparkshammers.reference.Names;
import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.*;

public class SHItems
{
    //Contains all items
    public static List<Item> ITEMS = new ArrayList<>();
    //Contains all of the AOE tools
    public static List<ItemAOE> AOE_TOOLS = new ArrayList<>();
    //Contains all of the items which use a basic coloured texture
    private static List<Item> COLOURED_ITEMS = new ArrayList<>();
    //A list of material names of my tools which aren't made from other modded items
    private static List<String> VANILLA_NAMES = Lists.newArrayList("wood", "stone", "iron", "gold", "diamond", "mjolnir", "giant", "mini", "netherstar", "powered");

    //Tool Heads
    public static ItemResource hammerHeadWood, excavatorHeadWood;

    //Specific Tools
    public static ItemAOE hammerWood, excavatorWood, hammerStone, hammerDiamond;

    //Special Hammers
    public static ItemAOE hammerMjolnir, hammerMini, hammerGiant, hammerNetherStar, hammerPowered;

    //Debug
    public static ItemDebug debug;

    public static void regItem(Item item)
    {
        ITEMS.add(item);
        if(item instanceof IColourable && ((IColourable)item).getTextureColour() >= 0)
            COLOURED_ITEMS.add(item);
        if(item instanceof ItemAOE)
            AOE_TOOLS.add((ItemAOE) item);
    }

    public static void regTool(Tool tool)
    {
        String name = tool.name;

        //Don't register if tool is made from other mod materials and respective config is disabled
        if(!Config.enableOtherModItems && !VANILLA_NAMES.contains(name))
            return;

        switch(name)
        {
            case "wood":
                regItem(hammerHeadWood = new ItemResource(Names.Items.HAMMER_HEAD_WOOD));
                regItem(excavatorHeadWood = new ItemResource(Names.Items.EXCAVATOR_HEAD_WOOD));
                regItem(hammerWood = new ItemAOE(tool, false));
                regItem(excavatorWood = new ItemAOE(tool, true));
                break;
            case "stone":
                regItem(hammerStone = new ItemAOE(tool, false));
                regItem(new ItemAOE(tool, true));
                break;
            case "diamond":
                regItem(hammerDiamond = new ItemAOE(tool, false));
                regItem(new ItemAOE(tool, true));
                break;
            case "mjolnir":
                if(Config.enableMjolnir)
                    regItem(hammerMjolnir = new ItemHammerMjolnir(tool));
                break;
            case "mini":
                if(Config.enableMiniHammer)
                    regItem(hammerMini = new ItemAOE(tool, false).setMineWidth(0).setShiftRotating(true));
                break;
            case "giant":
                if(Config.enableGiantHammer)
                    regItem(hammerGiant = new ItemAOE(tool, false).setMineWidth(4).setMineHeight(4));
                break;
            case "netherstar":
                if(Config.enableNetherStarHammer)
                    regItem(hammerNetherStar = new ItemHammerNetherStar(tool));
                break;
            case "powered":
                if(Config.enablePoweredHammer)
                    regItem(hammerPowered = new ItemHammerEnergy(tool));
                break;
            //TODO: Re-add Botania tools when the mod gets updated!
            /*
            case "manasteel":
            case "elementium":
            case "terrasteel":
                if(LoaderHelper.isModLoaded(Names.Mods.BOTANIA))
                    SHItemsBotania.regItems(mat);
                break;
            */
            default:
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
        tools.forEach(SHItems::regTool);
    }

    @SideOnly(Side.CLIENT)
    public static void regColours()
    {
        //Register item colours
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> tintIndex == 0 ? ((IColourable)stack.getItem()).getTextureColour() : -1, COLOURED_ITEMS.toArray(new Item[]{}));
    }
}
