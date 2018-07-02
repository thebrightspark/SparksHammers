package brightspark.sparkshammers.init;

import brightspark.sparkshammers.customTools.CustomTools;
import brightspark.sparkshammers.customTools.Tool;
import brightspark.sparkshammers.item.*;
import brightspark.sparkshammers.item.upgrade.EnumUpgrades;
import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class SHItems
{
    //Contains all items
    private static List<Item> ITEMS;
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

    //Powered Hammer Upgrades
    public static Item upgradeBase;
    public static ItemUpgrade upgradeSize, upgradeSpeed, upgradeAttack, upgradeHarvest, upgradeCapacity;

    //Debug
    public static ItemDebug debug;

    private static void addItem(Item item)
    {
        ITEMS.add(item);
        if(item instanceof IColourable && ((IColourable)item).getTextureColour() >= 0)
            COLOURED_ITEMS.add(item);
        if(item instanceof ItemAOE)
            AOE_TOOLS.add((ItemAOE) item);
    }

    private static void addTool(Tool tool)
    {
        String name = tool.name;

        //Don't register if tool is made from other mod materials and respective config is disabled
        if(!VANILLA_NAMES.contains(name))
            return;

        switch(name)
        {
            case "wood":
                addItem(hammerHeadWood = new ItemResource("hammer_head_wood"));
                addItem(excavatorHeadWood = new ItemResource("excavator_head_wood"));
                addItem(hammerWood = new ItemAOE(tool, false));
                addItem(excavatorWood = new ItemAOE(tool, true));
                break;
            case "stone":
                addItem(hammerStone = new ItemAOE(tool, false));
                addItem(new ItemAOE(tool, true));
                break;
            case "diamond":
                addItem(hammerDiamond = new ItemAOE(tool, false));
                addItem(new ItemAOE(tool, true));
                break;
            case "mjolnir":
                addItem(hammerMjolnir = new ItemHammerMjolnir(tool));
                break;
            case "mini":
                addItem(hammerMini = new ItemAOE(tool, false).setMineWidth(0).setShiftRotating(true));
                break;
            case "giant":
                addItem(hammerGiant = new ItemAOE(tool, false).setMineWidth(4).setMineHeight(4));
                break;
            case "netherstar":
                addItem(hammerNetherStar = new ItemHammerNetherStar(tool));
                break;
            case "powered":
                addItem(hammerPowered = new ItemHammerEnergy(tool));
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
                addItem(new ItemAOE(tool, false));
                addItem(new ItemAOE(tool, true));
        }
    }

    private static void init()
    {
        ITEMS = new ArrayList<>();

        //Debug
        addItem(debug = new ItemDebug());

        //Gets tools from json file
        List<Tool> tools = CustomTools.read();
        //Register tools from json
        tools.forEach(SHItems::addTool);

        if(hammerPowered != null)
        {
            //Register upgrades
            addItem(upgradeBase = new ItemResource("upgrade_base"));
            addItem(upgradeSize = new ItemUpgrade(EnumUpgrades.SIZE));
            addItem(upgradeSpeed = new ItemUpgrade(EnumUpgrades.SPEED));
            addItem(upgradeAttack = new ItemUpgrade(EnumUpgrades.ATTACK));
            addItem(upgradeHarvest = new ItemUpgrade(EnumUpgrades.HARVEST));
            addItem(upgradeCapacity = new ItemUpgrade(EnumUpgrades.CAPACITY));
        }
    }

    public static Item[] getItems()
    {
        if(ITEMS == null) init();
        return ITEMS.toArray(new Item[ITEMS.size()]);
    }

    public static List<Item> getItemsList()
    {
        return ITEMS;
    }

    public static void voidLists()
    {
        ITEMS = null;
        //AOE_TOOLS = null; <- Can't void this because it's used by the JEI plugin after preinit
        COLOURED_ITEMS = null;
        VANILLA_NAMES = null;
    }

    @SideOnly(Side.CLIENT)
    public static void regColours()
    {
        //Register item colours
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> tintIndex == 0 ? ((IColourable)stack.getItem()).getTextureColour() : -1, COLOURED_ITEMS.toArray(new Item[]{}));
    }
}
