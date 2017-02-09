package com.brightspark.sparkshammers.reference;

import com.brightspark.sparkshammers.util.CommonUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;

public class Names
{
    public enum EnumMaterials
    {
        //Name, harvest level, durability, mining speed, damage vs entities, enchantability

        //Vanilla
        // Durability -> vanilla * 6
        // Efficiency -> vanilla * 0.6
        // Attack     -> vanilla + 3
        WOOD(0x866526, "plankWood", EnumHelper.addToolMaterial("HammerWood", 0, 354, 1.2f, 3f, 15).setRepairItem(new ItemStack(net.minecraft.init.Blocks.LOG, 1, OreDictionary.WILDCARD_VALUE))),
        STONE(0x9A9A9A, "cobblestone", EnumHelper.addToolMaterial("HammerStone", 1, 786, 2.4f, 4f, 5).setRepairItem(new ItemStack(net.minecraft.init.Blocks.COBBLESTONE))),
        IRON(0xFFFFFF, "ingotIron", EnumHelper.addToolMaterial("HammerIron", 2, 1500, 3.6f, 5f, 14).setRepairItem(new ItemStack(net.minecraft.init.Items.IRON_INGOT))),
        GOLD(0xEAEE57, "ingotGold", EnumHelper.addToolMaterial("HammerGold", 0, 192, 7.2f, 3f, 22).setRepairItem(new ItemStack(net.minecraft.init.Items.GOLD_INGOT))),
        DIAMOND(0x33EBCB, "gemDiamond", EnumHelper.addToolMaterial("HammerDiamond", 3, 9366, 4.8f, 6f, 10).setRepairItem(new ItemStack(net.minecraft.init.Items.DIAMOND))),

        //Special
        GIANT(0x955CC4, EnumHelper.addToolMaterial("HammerGiant", 2, 9000, 1.8f, 8f, 10).setRepairItem(new ItemStack(net.minecraft.init.Blocks.IRON_BLOCK))),
        MINI(EnumHelper.addToolMaterial("HammerMini", 2, 750, 3.6f, 3.5f, 14).setRepairItem(new ItemStack(net.minecraft.init.Items.IRON_INGOT))),
        MJOLNIR(EnumHelper.addToolMaterial("HammerMjolnir", Integer.MAX_VALUE, 1, 10f, 10f, 0)),
        //TODO: Add config for Nether Star Hammer attack damage
        NETHER_STAR(EnumHelper.addToolMaterial("HammerNetherStar", 3, 10, 5f, 40.0f, 0).setRepairItem(new ItemStack(net.minecraft.init.Items.NETHER_STAR))),
        POWERED(EnumHelper.addToolMaterial("HammerPowered", 3, 1500, 4.8f, 6f, 0).setRepairItem(new ItemStack(net.minecraft.init.Blocks.IRON_BLOCK))),

        //Common Mod Metals
        COPPER(0xFF976E, "ingotCopper", EnumHelper.addToolMaterial("HammerCopper", 1, 768, 2.4f, 4f, 12)),
        SILVER(0xBEBEBE, "ingotSilver", EnumHelper.addToolMaterial("HammerSilver", 1, 768, 3f, 4.3f, 15)),
        TIN(0xFFF3E5, "ingotTin", EnumHelper.addToolMaterial("HammerTin", 1, 192, 1.8f, 3.8f, 5)),
        LEAD(0x737373, "ingotLead", EnumHelper.addToolMaterial("HammerLead", 0, 192, 0.6f, 3.3f, 2)),
        NICKEL(0xE5FFE1, "ingotNickel", EnumHelper.addToolMaterial("HammerNickel", 1, 768, 2.4f, 4f, 17)),
        PLATINUM(0xB0BEBA, "ingotPlatinum", EnumHelper.addToolMaterial("HammerPlatinum", 1, 960, 1.8f, 3.8f, 37)),
        BRONZE(0xEC9D4B, "ingotBronze", EnumHelper.addToolMaterial("HammerBronze", 2, 768, 4.8f, 5f, 11)),
        STEEL(0xCDDADC, "ingotSteel", EnumHelper.addToolMaterial("HammerSteel", 2, 2880, 4.8f, 5f, 5)),
        INVAR(0xCBC6B2, "ingotInvar", EnumHelper.addToolMaterial("HammerInvar", 2, 1920, 5.4f, 5.3f, 7)),
        ELECTRUM(0xFFF0A4, "ingotElectrum", EnumHelper.addToolMaterial("HammerElectrum", 1, 768, 3f, 4.3f, 25)),
        ALUMINIUM(0xECEAF7, "ingotAluminum", EnumHelper.addToolMaterial("HammerAluminium", 2, 1320, 7.2f, 4.8f, 14)),
        BRASS(0xFFE067, "ingotBrass", EnumHelper.addToolMaterial("HammerBrass", 2, 576, 2.1f, 4.6f, 10)),

        //Uncommon Mod Metals
        OSMIUM(0x8C9CA8, "ingotOsmium", EnumHelper.addToolMaterial("HammerOsmium", 2, 3000, 6f, 7f, 12)),
        ZINC(0xC3CCB9, "ingotZinc", EnumHelper.addToolMaterial("HammerZinc", 0, 192, 0.6f, 3.3f, 2)),
        CHROME(0xD1A3AE, "ingotChrome", EnumHelper.addToolMaterial("HammerChrome", 3, 3600, 4.2f, 7f, 7)),
        //IRIDIUM(0xB9C0C9, "ingotIridium", EnumHelper.addToolMaterial("HammerIridium", 5, 12000, 6f, 8f, 1)),
        //TITANIUM(0xADAECF, "ingotTitanium", EnumHelper.addToolMaterial("HammerTitanium", 4, 6000, 4.8f, 7f, 3)),
        //TUNGSTEN(0x818991, "ingotTungsten", EnumHelper.addToolMaterial("HammerTungsten", 4, 6000, 4.8f, 7f, 3)),

        //Other Mod Materials
        SAPPHIRE(0x548ABE, "gemSapphire", EnumHelper.addToolMaterial("HammerSapphire", 2, 3720, 3f, 5f, 8)),
        RUBY(0xBE5562, "gemRuby", EnumHelper.addToolMaterial("HammerRuby", 2, 1920, 3.7f, 5.7f, 10)),
        PERIDOT(0x9DBE56, "gemPeridot", EnumHelper.addToolMaterial("HammerPeridot", 2, 2400, 4.2f, 5.4f, 16)),
        OBSIDIAN(0x30244A, "obsidian", EnumHelper.addToolMaterial("HammerObsidian", 1, 6000, 2.4f, 3f, 5)),

        //Mod Specific Materials

        //Botania
        MANASTEEL(0xA1E0FF, "ingotManasteel", EnumHelper.addToolMaterial("HammerManasteel", 2, 3000, 3.7f, 5f, 20)),
        ELEMENTIUM(0xF8A1FF, "ingotElvenElementium", EnumHelper.addToolMaterial("HammerElementium", 3, 4320, 3.7f, 5f, 20)),
        TERRASTEEL(0x9BF76C, "ingotTerrasteel", EnumHelper.addToolMaterial("HammerTerrasteel", 4, 6000, 5.4f, 6f, 26)),

        //EnderIO
        DARKSTEEL(0x636363, "ingotDarkSteel", EnumHelper.addToolMaterial("HammerDarksteel", 5, 9366, 3.7f, 5f, 25)),

        //MobHunter
        MACHALITE(0xC1DCEA, "ingotMachalite", EnumHelper.addToolMaterial("HammerMachalite", 2, 3000, 4.2f, 6f, 15)),
        DRAGONITE(0xCBE0CB, "ingotDragonite", EnumHelper.addToolMaterial("HammerDragonite", 3, 4500, 4.2f, 7f, 15)),
        GOSSAMITE(0xE2CFDC, "ingotGossamite", EnumHelper.addToolMaterial("HammerGossamite", 3, 6000, 4.8f, 8f, 15));

        public int colour = -1;
        public Item.ToolMaterial material;
        public String dependantOreDic = null;
        public ItemStack dependantItem = null;
        public static EnumMaterials[] VANILLA;

        static
        {
            VANILLA = new EnumMaterials[]{WOOD, STONE, IRON, GOLD, DIAMOND};
        }

        EnumMaterials(Item.ToolMaterial material)
        {
            this.material = material;
        }

        EnumMaterials(int colour, Item.ToolMaterial material)
        {
            this.colour = colour;
            this.material = material;
        }

        EnumMaterials(int colour, String dependantOreDic, Item.ToolMaterial material)
        {
            this(colour, material);
            this.dependantOreDic = dependantOreDic;
        }

        //Currently not using this constructor or the dependant item!
        EnumMaterials(int colour, ItemStack dependantItem, Item.ToolMaterial material)
        {
            this(colour, material);
            this.dependantItem = dependantItem;
        }

        public String getMaterialName()
        {
            return CommonUtils.capitaliseAllFirstLetters(toString().toLowerCase().replaceAll("_", " "));
        }

        public String unlocToolName(boolean isExcavator)
        {
            return (isExcavator ? Items.EXCAVATOR : Items.HAMMER) + "_" + toString().toLowerCase().replaceAll("_", "");
        }
    }

    public static class Items
    {
        public static final String HAMMER = "hammer";
        public static final String EXCAVATOR = "excavator";

        public static final String HAMMER_HEAD_WOOD = "hammer_head_wood";
        public static final String EXCAVATOR_HEAD_WOOD = "excavator_head_wood";

        //<<<< Debug >>>>
        public static final String DEBUG = "debug";
    }

    public static class Blocks
    {
        public static final String HAMMER = "hammer_block";
        public static final String HAMMER_CRAFT = "hammer_craft";
    }

    public static class Mods
    {
        public static final String BOTANIA = "botania";
        public static final String EXTRA_UTILITIES = "extrautils2";
        public static final String ENDERIO = "enderio";
    }

    public static class ModItemIds
    {
        public static final String COMPRESSED_COBBLE = Mods.EXTRA_UTILITIES + ":CompressedCobblestone";
        public static final String CAPACITOR_BANK = Mods.ENDERIO + ":blockCapBank";
    }
}
