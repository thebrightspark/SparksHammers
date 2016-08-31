package com.brightspark.sparkshammers.reference;

import com.brightspark.sparkshammers.util.CommonUtils;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;

public class Names
{
    public enum EnumMaterials
    {
        //Vanilla
        WOOD(0x755821, Materials.WOOD),
        STONE(0x898989, Materials.STONE),
        IRON(0xD8D8D8, Materials.IRON),
        GOLD(0xCDD050, Materials.GOLD),
        DIAMOND(0x2BC7AC, Materials.DIAMOND),

        //Mod Metals
        COPPER(0xFF976E, ModMaterials.COPPER),
        SILVER(0xBEBEBE, null),
        TIN(0xFFF3E5, null),
        LEAD(0x737373, null),
        NICKEL(0xE5FFE1, null),
        PLATINUM(0xB0BEBA, null),
        BRONZE(0xEC9D4B, ModMaterials.BRONZE),
        STEEL(0xCDDADC, ModMaterials.STEEL),
        INVAR(0xCBC6B2, null),
        ELECTRUM(0xFFF0A4, null);

        public int colour;
        public Item.ToolMaterial material;

        EnumMaterials(int colour, Item.ToolMaterial material)
        {
            this.colour = colour;
            this.material = material;
        }

        public String unlocToolName(boolean isExcavator)
        {
            return I18n.format((isExcavator ? Items.EXCAVATOR : Items.HAMMER) + CommonUtils.capitaliseFirstLetter(toString().toLowerCase()));
        }
    }

    public static class Items
    {
        public static final String HAMMER = "hammer";
        public static final String EXCAVATOR = "excavator";

        //<<<< Hammers >>>>
        public static final String HAMMER_HEAD_WOOD = "hammerHeadWood";

        //public static final String HAMMER = "hammer";
        public static final String HAMMER_WOOD = "hammerWood";
        public static final String HAMMER_STONE = "hammerStone";
        public static final String HAMMER_IRON = "hammerIron";
        public static final String HAMMER_GOLD = "hammerGold";
        public static final String HAMMER_DIAMOND = "hammerDiamond";

        public static final String HAMMER_THOR = "hammerThor";
        public static final String HAMMER_MINI = "hammerMini";
        public static final String HAMMER_GIANT = "hammerGiant";
        public static final String HAMMER_NETHERSTAR = "hammerNetherStar";

        //<<<< Excavators >>>>
        public static final String EXCAVATOR_HEAD_WOOD = "excavatorHeadWood";

        public static final String EXCAVATOR_WOOD = "excavatorWood";
        public static final String EXCAVATOR_STONE = "excavatorStone";
        public static final String EXCAVATOR_IRON = "excavatorIron";
        public static final String EXCAVATOR_GOLD = "excavatorGold";
        public static final String EXCAVATOR_DIAMOND = "excavatorDiamond";

        //<<<< Debug >>>>
        public static final String DEBUG = "debug";
    }

    public static class ModItems
    {
        //Botania
        public static final String HAMMER_MANASTEEL = "hammerManasteel";
        public static final String HAMMER_TERRASTEEL = "hammerTerrasteel";
        public static final String HAMMER_ELEMENTIUM = "hammerElementium";
        public static final String EXCAVATOR_MANASTEEL = "excavatorManasteel";
        public static final String EXCAVATOR_TERRASTEEL = "excavatorTerrasteel";
        public static final String EXCAVATOR_ELEMENTIUM = "excavatorElementium";
        //Extra Utilities
        public static final String HAMMER_HEAD_UNSTABLE = "hammerHeadUnstable";
        public static final String HAMMER_UNSTABLE = "hammerUnstable";
        public static final String EXCAVATOR_HEAD_UNSTABLE = "excavatorHeadUnstable";
        public static final String EXCAVATOR_UNSTABLE = "excavatorUnstable";
        //EnderIO
        public static final String HAMMER_DARKSTEEL = "hammerDarksteel";
        public static final String EXCAVATOR_DARKSTEEL = "excavatorDarksteel";
        //MobHunter
        public static final String HAMMER_MACHALITE = "hammerMachalite";
        public static final String HAMMER_DRAGONITE = "hammerDragonite";
        public static final String HAMMER_GOSSAMITE = "hammerGossamite";
        public static final String EXCAVATOR_MACHALITE = "excavatorMachalite";
        public static final String EXCAVATOR_DRAGONITE = "excavatorDragonite";
        public static final String EXCAVATOR_GOSSAMITE = "excavatorGossamite";
        //Misc
        public static final String HAMMER_BRONZE = "hammerBronze";
        public static final String EXCAVATOR_BRONZE = "excavatorBronze";
    }

    public static class Blocks
    {
        public static final String HAMMER = "hammerBlock";
        public static final String HAMMER_CRAFT = "hammerCraft";
    }

    public static class Mods
    {
        public static final String BOTANIA = "Botania";
        public static final String EXTRA_UTILITIES = "extrautils2";
        public static final String ENDERIO = "EnderIO";
        public static final String MISC = "Misc";
    }

    public static class ModOreDicts
    {
        public static final String INGOT_MANASTEEL = "ingotManasteel";
        public static final String INGOT_TERRASTEEL = "ingotTerrasteel";
        public static final String INGOT_ELEMENTIUM = "ingotElvenElementium";
        public static final String INGOT_UNSTABLE = "ingotUnstable";
        public static final String INGOT_DARKSTEEL = "ingotDarkSteel";
        public static final String INGOT_BRONZE = "ingotBronze";
        public static final String INGOT_MACHALITE = "ingotMachalite";
        public static final String INGOT_DRAGONITE = "ingotDragonite";
        public static final String INGOT_GOSSAMITE = "ingotGossamite";
    }

    public static class ModItemIds
    {
        public static final String COMPRESSED_COBBLE = Mods.EXTRA_UTILITIES + "CompressedCobblestone";
    }
}
