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
        WOOD(0x866526, "plankWood", EnumHelper.addToolMaterial("HammerWood", Item.ToolMaterial.WOOD.getHarvestLevel(), (int) (Item.ToolMaterial.WOOD.getMaxUses() * Config.toolDurabilityModifier), (int) (Item.ToolMaterial.WOOD.getEfficiencyOnProperMaterial() * Config.toolSpeedModifier), Item.ToolMaterial.WOOD.getDamageVsEntity(), Item.ToolMaterial.WOOD.getEnchantability()).setRepairItem(new ItemStack(net.minecraft.init.Blocks.LOG, 1, OreDictionary.WILDCARD_VALUE))),
        STONE(0x9A9A9A, "cobblestone", EnumHelper.addToolMaterial("HammerStone", Item.ToolMaterial.STONE.getHarvestLevel(), (int) (Item.ToolMaterial.STONE.getMaxUses() * Config.toolDurabilityModifier), (int) (Item.ToolMaterial.STONE.getEfficiencyOnProperMaterial() * Config.toolSpeedModifier), Item.ToolMaterial.STONE.getDamageVsEntity(), Item.ToolMaterial.STONE.getEnchantability()).setRepairItem(new ItemStack(net.minecraft.init.Blocks.COBBLESTONE))),
        IRON(0xFFFFFF, "ingotIron", EnumHelper.addToolMaterial("HammerIron", Item.ToolMaterial.IRON.getHarvestLevel(), (int) (Item.ToolMaterial.IRON.getMaxUses() * Config.toolDurabilityModifier), (int) (Item.ToolMaterial.IRON.getEfficiencyOnProperMaterial() * Config.toolSpeedModifier), Item.ToolMaterial.IRON.getDamageVsEntity(), Item.ToolMaterial.IRON.getEnchantability()).setRepairItem(new ItemStack(net.minecraft.init.Items.IRON_INGOT))),
        GOLD(0xEAEE57, "ingotGold", EnumHelper.addToolMaterial("HammerGold", Item.ToolMaterial.GOLD.getHarvestLevel(), (int) (Item.ToolMaterial.GOLD.getMaxUses() * Config.toolDurabilityModifier), (int) (Item.ToolMaterial.GOLD.getEfficiencyOnProperMaterial() * Config.toolSpeedModifier), Item.ToolMaterial.GOLD.getDamageVsEntity(), Item.ToolMaterial.GOLD.getEnchantability()).setRepairItem(new ItemStack(net.minecraft.init.Items.GOLD_INGOT))),
        DIAMOND(0x33EBCB, "gemDiamond", EnumHelper.addToolMaterial("HammerDiamond", Item.ToolMaterial.DIAMOND.getHarvestLevel(), (int) (Item.ToolMaterial.DIAMOND.getMaxUses() * Config.toolDurabilityModifier), (int) (Item.ToolMaterial.DIAMOND.getEfficiencyOnProperMaterial() * Config.toolSpeedModifier), Item.ToolMaterial.DIAMOND.getDamageVsEntity(), Item.ToolMaterial.DIAMOND.getEnchantability()).setRepairItem(new ItemStack(net.minecraft.init.Items.DIAMOND))),

        //Special
        GIANT(0x955CC4, EnumHelper.addToolMaterial("HammerGiant", IRON.material.getHarvestLevel(), IRON.material.getMaxUses() * 6, IRON.material.getEfficiencyOnProperMaterial() * 0.5f, IRON.material.getDamageVsEntity() * 1.25f, IRON.material.getEnchantability()).setRepairItem(new ItemStack(net.minecraft.init.Blocks.IRON_BLOCK))),
        MINI(EnumHelper.addToolMaterial("HammerMini", IRON.material.getHarvestLevel(), (int) (IRON.material.getMaxUses() * 0.5), IRON.material.getEfficiencyOnProperMaterial(), IRON.material.getDamageVsEntity() * 0.75f, IRON.material.getEnchantability()).setRepairItem(new ItemStack(net.minecraft.init.Items.IRON_INGOT))),
        MJOLNIR(EnumHelper.addToolMaterial("HammerMjolnir", Config.mjolnirHarvestLevel, 1, Config.mjolnirEfficiency, Config.mjolnirDamageVsEntity, 0)),
        NETHERSTAR(EnumHelper.addToolMaterial("HammerNetherStar", Item.ToolMaterial.DIAMOND.getHarvestLevel(), Config.netherStarHammerDurability, Item.ToolMaterial.DIAMOND.getEfficiencyOnProperMaterial(), 20.0f, 0).setRepairItem(new ItemStack(net.minecraft.init.Items.NETHER_STAR))),

        //Common Mod Metals
        COPPER(0xFF976E, "ingotCopper", EnumHelper.addToolMaterial("HammerCopper", Config.copperHarvestLevel, Config.copperMaxUses, Config.copperEfficiency, Config.copperDamageVsEntity, Config.copperEnchantability)),
        SILVER(0xBEBEBE, "ingotSilver", EnumHelper.addToolMaterial("HammerSilver", Config.silverHarvestLevel, Config.silverMaxUses, Config.silverEfficiency, Config.silverDamageVsEntity, Config.silverEnchantability)),
        TIN(0xFFF3E5, "ingotTin", EnumHelper.addToolMaterial("HammerTin", Config.tinHarvestLevel, Config.tinMaxUses, Config.tinEfficiency, Config.tinDamageVsEntity, Config.tinEnchantability)),
        LEAD(0x737373, "ingotLead", EnumHelper.addToolMaterial("HammerLead", Config.leadHarvestLevel, Config.leadMaxUses, Config.leadEfficiency, Config.leadDamageVsEntity, Config.leadEnchantability)),
        NICKEL(0xE5FFE1, "ingotNickel", EnumHelper.addToolMaterial("HammerNickel", Config.nickelHarvestLevel, Config.nickelMaxUses, Config.nickelEfficiency, Config.nickelDamageVsEntity, Config.nickelEnchantability)),
        PLATINUM(0xB0BEBA, "ingotPlatinum", EnumHelper.addToolMaterial("HammerPlatinum", Config.platinumHarvestLevel, Config.platinumMaxUses, Config.platinumEfficiency, Config.platinumDamageVsEntity, Config.platinumEnchantability)),
        BRONZE(0xEC9D4B, "ingotBronze", EnumHelper.addToolMaterial("HammerBronze", Config.bronzeHarvestLevel, Config.bronzeMaxUses, Config.bronzeEfficiency, Config.bronzeDamageVsEntity, Config.bronzeEnchantability)),
        STEEL(0xCDDADC, "ingotSteel", EnumHelper.addToolMaterial("HammerSteel", Config.steelHarvestLevel, Config.steelMaxUses, Config.steelEfficiency, Config.steelDamageVsEntity, Config.steelEnchantability)),
        INVAR(0xCBC6B2, "ingotInvar", EnumHelper.addToolMaterial("HammerInvar", Config.invarHarvestLevel, Config.invarMaxUses, Config.invarEfficiency, Config.invarDamageVsEntity, Config.invarEnchantability)),
        ELECTRUM(0xFFF0A4, "ingotElectrum", EnumHelper.addToolMaterial("HammerElectrum", Config.electrumHarvestLevel, Config.electrumMaxUses, Config.electrumEfficiency, Config.electrumDamageVsEntity, Config.electrumEnchantability)),

        //Mod Materials
        //Botania
        MANASTEEL(0xA1E0FF, "ingotManasteel", EnumHelper.addToolMaterial("HammerManasteel", Config.manasteelHarvestLevel, Config.manasteelMaxUses, Config.manasteelEfficiency, Config.manasteelDamageVsEntity, Config.manasteelEnchantability)),
        TERRASTEEL(0x9BF76C, "ingotTerrasteel", EnumHelper.addToolMaterial("HammerTerrasteel", Config.terrasteelHarvestLevel, Config.terrasteelMaxUses, Config.terrasteelEfficiency, Config.terrasteelDamageVsEntity, Config.terrasteelEnchantability)),
        ELEMENTIUM(0xF8A1FF, "ingotElvenElementium", EnumHelper.addToolMaterial("HammerElementium", Config.elementiumHarvestLevel, Config.elementiumMaxUses, Config.elementiumEfficiency, Config.elementiumDamageVsEntity, Config.elementiumEnchantability)),
        //EnderIO
        //DARKSTEEL(0x636363, EnumHelper.addToolMaterial("HammerDarksteel", Config.darksteelHarvestLevel, Config.darksteelMaxUses, Config.darksteelEfficiency, Config.darksteelDamageVsEntity, Config.darksteelEnchantability)),
        //MobHunter
        MACHALITE(0xC1DCEA, "ingotMachalite", EnumHelper.addToolMaterial("HammerMachalite", Config.machaliteHarvestLevel, Config.machaliteMaxUses, Config.machaliteEfficiency, Config.machaliteDamageVsEntity, Config.machaliteEnchantability)),
        DRAGONITE(0xCBE0CB, "ingotDragonite", EnumHelper.addToolMaterial("HammerDragonite", Config.dragoniteHarvestLevel, Config.dragoniteMaxUses, Config.dragoniteEfficiency, Config.dragoniteDamageVsEntity, Config.dragoniteEnchantability)),
        GOSSAMITE(0xE2CFDC, "ingotGossamite", EnumHelper.addToolMaterial("HammerGossamite", Config.gossamiteHarvestLevel, Config.gossamiteMaxUses, Config.gossamiteEfficiency, Config.gossamiteDamageVsEntity, Config.gossamiteEnchantability));

        public int colour = -1;
        public Item.ToolMaterial material;
        public String dependantOreDic = null;
        public ItemStack dependantItem = null;
        public static String OTHER_DEPENDENCY = "<OTHER>";
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

        @Override
        public String toString()
        {
            return CommonUtils.capitaliseFirstLetter(super.toString().toLowerCase());
        }

        public String unlocToolName(boolean isExcavator)
        {
            return (isExcavator ? Items.EXCAVATOR : Items.HAMMER) + toString();
        }
    }

    public static class Items
    {
        public static final String HAMMER = "hammer";
        public static final String EXCAVATOR = "excavator";

        public static final String HAMMER_HEAD_WOOD = "hammerHeadWood";
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
        public static final String EXTRA_UTILITIES = "ExtraUtils2";
        public static final String ENDERIO = "EnderIO";
        public static final String MISC = "Misc";
    }

    public static class ModItemIds
    {
        public static final String COMPRESSED_COBBLE = Mods.EXTRA_UTILITIES + ":CompressedCobblestone";
    }
}
