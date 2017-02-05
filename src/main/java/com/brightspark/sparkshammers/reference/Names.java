package com.brightspark.sparkshammers.reference;

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
        WOOD(0x866526, "plankWood", EnumHelper.addToolMaterial("HammerWood", Item.ToolMaterial.WOOD.getHarvestLevel(), (int) (Item.ToolMaterial.WOOD.getMaxUses() * Config.toolDurabilityModifier), (int) (Item.ToolMaterial.WOOD.getEfficiencyOnProperMaterial() * Config.toolSpeedModifier), (Item.ToolMaterial.WOOD.getDamageVsEntity() + 3f) * Config.toolAttackModifier, Item.ToolMaterial.WOOD.getEnchantability()).setRepairItem(new ItemStack(net.minecraft.init.Blocks.LOG, 1, OreDictionary.WILDCARD_VALUE))),
        STONE(0x9A9A9A, "cobblestone", EnumHelper.addToolMaterial("HammerStone", Item.ToolMaterial.STONE.getHarvestLevel(), (int) (Item.ToolMaterial.STONE.getMaxUses() * Config.toolDurabilityModifier), (int) (Item.ToolMaterial.STONE.getEfficiencyOnProperMaterial() * Config.toolSpeedModifier), (Item.ToolMaterial.STONE.getDamageVsEntity() + 3f) * Config.toolAttackModifier, Item.ToolMaterial.STONE.getEnchantability()).setRepairItem(new ItemStack(net.minecraft.init.Blocks.COBBLESTONE))),
        IRON(0xFFFFFF, "ingotIron", EnumHelper.addToolMaterial("HammerIron", Item.ToolMaterial.IRON.getHarvestLevel(), (int) (Item.ToolMaterial.IRON.getMaxUses() * Config.toolDurabilityModifier), (int) (Item.ToolMaterial.IRON.getEfficiencyOnProperMaterial() * Config.toolSpeedModifier), (Item.ToolMaterial.IRON.getDamageVsEntity() + 3f) * Config.toolAttackModifier, Item.ToolMaterial.IRON.getEnchantability()).setRepairItem(new ItemStack(net.minecraft.init.Items.IRON_INGOT))),
        GOLD(0xEAEE57, "ingotGold", EnumHelper.addToolMaterial("HammerGold", Item.ToolMaterial.GOLD.getHarvestLevel(), (int) (Item.ToolMaterial.GOLD.getMaxUses() * Config.toolDurabilityModifier), (int) (Item.ToolMaterial.GOLD.getEfficiencyOnProperMaterial() * Config.toolSpeedModifier), (Item.ToolMaterial.GOLD.getDamageVsEntity() + 3f) * Config.toolAttackModifier, Item.ToolMaterial.GOLD.getEnchantability()).setRepairItem(new ItemStack(net.minecraft.init.Items.GOLD_INGOT))),
        DIAMOND(0x33EBCB, "gemDiamond", EnumHelper.addToolMaterial("HammerDiamond", Item.ToolMaterial.DIAMOND.getHarvestLevel(), (int) (Item.ToolMaterial.DIAMOND.getMaxUses() * Config.toolDurabilityModifier), (int) (Item.ToolMaterial.DIAMOND.getEfficiencyOnProperMaterial() * Config.toolSpeedModifier), (Item.ToolMaterial.DIAMOND.getDamageVsEntity() + 3f) * Config.toolAttackModifier, Item.ToolMaterial.DIAMOND.getEnchantability()).setRepairItem(new ItemStack(net.minecraft.init.Items.DIAMOND))),

        //Special
        GIANT(0x955CC4, EnumHelper.addToolMaterial("HammerGiant", IRON.material.getHarvestLevel(), IRON.material.getMaxUses() * 6, IRON.material.getEfficiencyOnProperMaterial() * 0.5f, IRON.material.getDamageVsEntity() * 1.25f, IRON.material.getEnchantability()).setRepairItem(new ItemStack(net.minecraft.init.Blocks.IRON_BLOCK))),
        MINI(EnumHelper.addToolMaterial("HammerMini", IRON.material.getHarvestLevel(), (int) (IRON.material.getMaxUses() * 0.5), IRON.material.getEfficiencyOnProperMaterial(), IRON.material.getDamageVsEntity() * 0.75f, IRON.material.getEnchantability()).setRepairItem(new ItemStack(net.minecraft.init.Items.IRON_INGOT))),
        MJOLNIR(EnumHelper.addToolMaterial("HammerMjolnir", Config.mjolnirHarvestLevel, 1, Config.mjolnirEfficiency, Config.mjolnirDamageVsEntity, 0)),
        //TODO: Add config for Nether Star Hammer attack damage
        NETHERSTAR(EnumHelper.addToolMaterial("HammerNetherStar", Item.ToolMaterial.DIAMOND.getHarvestLevel(), Config.netherStarHammerDurability, Item.ToolMaterial.DIAMOND.getEfficiencyOnProperMaterial(), 40.0f, 0).setRepairItem(new ItemStack(net.minecraft.init.Items.NETHER_STAR))),

        //Common Mod Metals
        COPPER(0xFF976E, "ingotCopper", Config.getAsToolMaterial("HammerCopper", Config.materialCopper)),
        SILVER(0xBEBEBE, "ingotSilver", Config.getAsToolMaterial("HammerSilver", Config.materialSilver)),
        TIN(0xFFF3E5, "ingotTin", Config.getAsToolMaterial("HammerTin", Config.materialTin)),
        LEAD(0x737373, "ingotLead", Config.getAsToolMaterial("HammerLead", Config.materialLead)),
        NICKEL(0xE5FFE1, "ingotNickel", Config.getAsToolMaterial("HammerNickel", Config.materialNickel)),
        PLATINUM(0xB0BEBA, "ingotPlatinum", Config.getAsToolMaterial("HammerPlatinum", Config.materialPlatinum)),
        BRONZE(0xEC9D4B, "ingotBronze", Config.getAsToolMaterial("HammerBronze", Config.materialBronze)),
        STEEL(0xCDDADC, "ingotSteel", Config.getAsToolMaterial("HammerSteel", Config.materialSteel)),
        INVAR(0xCBC6B2, "ingotInvar", Config.getAsToolMaterial("HammerInvar", Config.materialInvar)),
        ELECTRUM(0xFFF0A4, "ingotElectrum", Config.getAsToolMaterial("HammerElectrum", Config.materialElectrum)),
        ALUMINIUM(0xECEAF7, "ingotAluminum", Config.getAsToolMaterial("HammerAluminium", Config.materialAluminium)),
        BRASS(0xFFE067, "ingotBrass", Config.getAsToolMaterial("HammerBrass", Config.materialBrass)),

        //Uncommon Mod Metals
        OSMIUM(0x8C9CA8, "ingotOsmium", Config.getAsToolMaterial("HammerOsmium", Config.materialOsmium)),
        ZINC(0xC3CCB9, "ingotZinc", Config.getAsToolMaterial("HammerZinc", Config.materialZinc)),
        CHROME(0xD1A3AE, "ingotChrome", Config.getAsToolMaterial("HammerChrome", Config.materialChrome)),
        //IRIDIUM(0xB9C0C9, "ingotIridium", Config.getAsToolMaterial("HammerIridium", Config.materialIridium)),
        //TITANIUM(0xADAECF, "ingotTitanium", Config.getAsToolMaterial("HammerTitanium", Config.materialTitanium)),
        //TUNGSTEN(0x818991, "ingotTungsten", Config.getAsToolMaterial("HammerTungsten", Config.materialTungsten)),

        //Other Mod Materials
        SAPPHIRE(0x548ABE, "gemSapphire", Config.getAsToolMaterial("HammerSapphire", Config.materialSapphire)),
        RUBY(0xBE5562, "gemRuby", Config.getAsToolMaterial("HammerRuby", Config.materialRuby)),
        PERIDOT(0x9DBE56, "gemPeridot", Config.getAsToolMaterial("HammerPeridot", Config.materialPeridot)),
        OBSIDIAN(0x30244A, "obsidian", Config.getAsToolMaterial("HammerObsidian", Config.materialObsidian)),

        //Mod Materials

        //Botania
        MANASTEEL(0xA1E0FF, "ingotManasteel", Config.getAsToolMaterial("HammerManasteel", Config.materialManasteel)),
        TERRASTEEL(0x9BF76C, "ingotTerrasteel", Config.getAsToolMaterial("HammerTerrasteel", Config.materialTerrasteel)),
        ELEMENTIUM(0xF8A1FF, "ingotElvenElementium", Config.getAsToolMaterial("HammerElementium", Config.materialElementium)),

        //EnderIO
        DARKSTEEL(0x636363, "ingotDarkSteel", Config.getAsToolMaterial("HammerDarksteel", Config.materialDarksteel)),

        //MobHunter
        MACHALITE(0xC1DCEA, "ingotMachalite", Config.getAsToolMaterial("HammerMachalite", Config.materialMachalite)),
        DRAGONITE(0xCBE0CB, "ingotDragonite", Config.getAsToolMaterial("HammerDragonite", Config.materialDragonite)),
        GOSSAMITE(0xE2CFDC, "ingotGossamite", Config.getAsToolMaterial("HammerGossamite", Config.materialGossamite));

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

        public String unlocToolName(boolean isExcavator)
        {
            return (isExcavator ? Items.EXCAVATOR : Items.HAMMER) + "_" + toString().toLowerCase();
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
    }
}
