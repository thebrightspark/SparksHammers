package brightspark.sparkshammers.init;

import brightspark.sparkshammers.EnumMaterials;
import brightspark.sparkshammers.api.ModTool;
import brightspark.sparkshammers.api.RegisterToolEvent;
import brightspark.sparkshammers.customTools.CustomTools;
import brightspark.sparkshammers.customTools.RegisterToolException;
import brightspark.sparkshammers.customTools.Tool;
import brightspark.sparkshammers.item.*;
import brightspark.sparkshammers.item.upgrade.EnumUpgrades;
import brightspark.sparkshammers.util.LogHelper;
import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.LinkedList;
import java.util.List;

public class SHItems
{
    //Contains all items
    private static List<Item> ITEMS;
    //Contains all of the AOE tools
    public static List<ItemAOE> AOE_TOOLS = new LinkedList<>();
    //Contains all of the items which use a basic coloured texture
    private static List<Item> COLOURED_ITEMS = new LinkedList<>();
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

    private static boolean hasToolName(String name)
    {
        return AOE_TOOLS.stream().anyMatch(tool -> tool.getMaterialName().equalsIgnoreCase(name));
    }

    private static void addItem(Item item)
    {
        ITEMS.add(item);
        if(item instanceof ItemAOE)
        {
            AOE_TOOLS.add((ItemAOE) item);
            if(((ItemAOE) item).getTextureColour() >= 0)
                COLOURED_ITEMS.add(item);
        }
    }

    public static void addTool(Tool tool)
    {
        String name = tool.name;

        if(hasToolName(name))
            throw new RegisterToolException("A tool with the name %s has already been added!", name);

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
                if(tool instanceof ModTool)
                {
                    //Add tool from another mod
                    ModTool modTool = (ModTool) tool;
                    ItemAOE item;
                    if(modTool.getType().isHammer())
                    {
                        addItem(item = new ItemAOE(tool, false));
                        if(modTool.recipe != null)
                            SHRecipes.addSHRecipe(item, modTool.recipe);
                    }
                    if(modTool.getType().isExcavator())
                    {
                        addItem(item = new ItemAOE(tool, true));
                        if(modTool.recipe != null)
                            SHRecipes.addSHRecipe(item, modTool.recipe);
                    }
                }
                else
                {
                    addItem(new ItemAOE(tool, false));
                    addItem(new ItemAOE(tool, true));
                }
        }
    }

    public static void init()
    {
        ITEMS = new LinkedList<>();

        //Debug
        addItem(debug = new ItemDebug());

        //Gets tools from json file
        List<Tool> tools = CustomTools.read();
        //If file not found or couldn't be read
        if(tools == null)
        {
            tools = new LinkedList<>();

            //Create the default tools
            for(EnumMaterials material : EnumMaterials.values())
                tools.add(new Tool(material));

            //Get other mod tools to register
            LogHelper.info("Firing RegisterToolEvent for other mods");
            int toolsSize = tools.size();
            MinecraftForge.EVENT_BUS.post(new RegisterToolEvent(tools));
            LogHelper.info("Collected " + (tools.size() - toolsSize) + " tools from other mods");

            //Create the json file
            CustomTools.write(tools);
        }
        //Add tools to be registered
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
        return ITEMS.toArray(new Item[0]);
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
        //TODO: Change this to use the ColorHandlerEvent
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> tintIndex == 0 ? ((ItemAOE) stack.getItem()).getTextureColour() : -1, COLOURED_ITEMS.toArray(new Item[0]));
    }
}
