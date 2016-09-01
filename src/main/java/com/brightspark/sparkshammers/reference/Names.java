package com.brightspark.sparkshammers.reference;

import com.brightspark.sparkshammers.util.CommonUtils;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;

public class Names
{
    public enum EnumMaterials
    {
        //Vanilla
        WOOD(0x866526, Materials.WOOD),
        STONE(0x9A9A9A, Materials.STONE),
        IRON(0xFFFFFF, Materials.IRON),
        GOLD(0xEAEE57, Materials.GOLD),
        DIAMOND(0x33EBCB, Materials.DIAMOND),

        //Special
        GIANT(0x955CC4, Materials.GIANT),

        //Common Mod Metals
        COPPER(0xFF976E, ModMaterials.COPPER, ModOreDicts.INGOT_COPPER),
        SILVER(0xBEBEBE, ModMaterials.SILVER, ModOreDicts.INGOT_SILVER),
        TIN(0xFFF3E5, ModMaterials.TIN, ModOreDicts.INGOT_TIN),
        LEAD(0x737373, ModMaterials.LEAD, ModOreDicts.INGOT_LEAD),
        NICKEL(0xE5FFE1, ModMaterials.NICKEL, ModOreDicts.INGOT_NICKEL),
        PLATINUM(0xB0BEBA, ModMaterials.PLATINUM, ModOreDicts.INGOT_PLATINUM),
        BRONZE(0xEC9D4B, ModMaterials.BRONZE, ModOreDicts.INGOT_BRONZE),
        STEEL(0xCDDADC, ModMaterials.STEEL, ModOreDicts.INGOT_STEEL),
        INVAR(0xCBC6B2, ModMaterials.INVAR, ModOreDicts.INGOT_INVAR),
        ELECTRUM(0xFFF0A4, ModMaterials.ELECTRUM, ModOreDicts.INGOT_ELECTRUM),

        //Mod Materials
        //Botania
        MANASTEEL(0xA1E0FF, ModMaterials.MANASTEEL, ModOreDicts.INGOT_MANASTEEL),
        TERRASTEEL(0x9BF76C, ModMaterials.TERRASTEEL, ModOreDicts.INGOT_TERRASTEEL),
        ELEMENTIUM(0xF8A1FF, ModMaterials.ELEMENTIUM, ModOreDicts.INGOT_ELEMENTIUM),
        //EnderIO
        DARKSTEEL(0x636363, ModMaterials.DARKSTEEL),
        //MobHunter
        MACHALITE(0xC1DCEA, ModMaterials.MACHALITE, ModOreDicts.INGOT_MACHALITE),
        DRAGONITE(0xCBE0CB, ModMaterials.DRAGONITE, ModOreDicts.INGOT_DRAGONITE),
        GOSSAMITE(0xE2CFDC, ModMaterials.GOSSAMITE, ModOreDicts.INGOT_GOSSAMITE);

        public int colour;
        public Item.ToolMaterial material;
        public String dependantOreDic = null;
        public static String OTHER_DEPENDENCY = "<OTHER>";

        EnumMaterials(int colour, Item.ToolMaterial material)
        {
            this.colour = colour;
            this.material = material;
        }

        EnumMaterials(int colour, Item.ToolMaterial material, String dependantOreDic)
        {
            this(colour, material);
            this.dependantOreDic = dependantOreDic;
        }

        @Override
        public String toString()
        {
            return CommonUtils.capitaliseFirstLetter(super.toString().toLowerCase());
        }

        public String unlocToolName(boolean isExcavator)
        {
            return I18n.format((isExcavator ? Items.EXCAVATOR : Items.HAMMER) + toString());
        }
    }

    public static class Items
    {
        public static final String HAMMER = "hammer";
        public static final String EXCAVATOR = "excavator";

        //<<<< Hammers >>>>
        public static final String HAMMER_HEAD_WOOD = "hammerHeadWood";

        public static final String HAMMER_THOR = "hammerThor";
        public static final String HAMMER_MINI = "hammerMini";
        public static final String HAMMER_GIANT = "hammerGiant";
        public static final String HAMMER_NETHERSTAR = "hammerNetherStar";

        //<<<< Excavators >>>>
        public static final String EXCAVATOR_HEAD_WOOD = "excavatorHeadWood";

        //<<<< Debug >>>>
        public static final String DEBUG = "debug";
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
        public static final String INGOT_COPPER = "ingotCopper";
        public static final String INGOT_SILVER = "ingotSilver";
        public static final String INGOT_TIN = "ingotTin";
        public static final String INGOT_LEAD = "ingotLead";
        public static final String INGOT_NICKEL = "ingotNickel";
        public static final String INGOT_PLATINUM = "ingotPlatinum";
        public static final String INGOT_BRONZE = "ingotBronze";
        public static final String INGOT_STEEL = "ingotSteel";
        public static final String INGOT_INVAR = "ingotInvar";
        public static final String INGOT_ELECTRUM = "ingotElectrum";

        public static final String INGOT_MANASTEEL = "ingotManasteel";
        public static final String INGOT_TERRASTEEL = "ingotTerrasteel";
        public static final String INGOT_ELEMENTIUM = "ingotElvenElementium";
        public static final String INGOT_MACHALITE = "ingotMachalite";
        public static final String INGOT_DRAGONITE = "ingotDragonite";
        public static final String INGOT_GOSSAMITE = "ingotGossamite";
    }

    public static class ModItemIds
    {
        public static final String COMPRESSED_COBBLE = Mods.EXTRA_UTILITIES + "CompressedCobblestone";
    }
}
