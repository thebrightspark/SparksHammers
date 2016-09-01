package com.brightspark.sparkshammers.reference;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;

public class Materials
{
    //Name, harvest level, durability, mining speed, damage vs entities, enchantability
    public static final ToolMaterial WOOD = EnumHelper.addToolMaterial("HammerWood", ToolMaterial.WOOD.getHarvestLevel(), (int) (ToolMaterial.WOOD.getMaxUses() * Config.toolDurabilityModifier), (int) (ToolMaterial.WOOD.getEfficiencyOnProperMaterial() * Config.toolSpeedModifier), ToolMaterial.WOOD.getDamageVsEntity(), ToolMaterial.WOOD.getEnchantability()).setRepairItem(new ItemStack(Blocks.LOG, 1, OreDictionary.WILDCARD_VALUE));
    public static final ToolMaterial STONE = EnumHelper.addToolMaterial("HammerStone", ToolMaterial.STONE.getHarvestLevel(), (int) (ToolMaterial.STONE.getMaxUses() * Config.toolDurabilityModifier), (int) (ToolMaterial.STONE.getEfficiencyOnProperMaterial() * Config.toolSpeedModifier), ToolMaterial.STONE.getDamageVsEntity(), ToolMaterial.STONE.getEnchantability()).setRepairItem(new ItemStack(Blocks.COBBLESTONE));
    public static final ToolMaterial IRON = EnumHelper.addToolMaterial("HammerIron", ToolMaterial.IRON.getHarvestLevel(), (int) (ToolMaterial.IRON.getMaxUses() * Config.toolDurabilityModifier), (int) (ToolMaterial.IRON.getEfficiencyOnProperMaterial() * Config.toolSpeedModifier), ToolMaterial.IRON.getDamageVsEntity(), ToolMaterial.IRON.getEnchantability()).setRepairItem(new ItemStack(Items.IRON_INGOT));
    public static final ToolMaterial GOLD = EnumHelper.addToolMaterial("HammerGold", ToolMaterial.GOLD.getHarvestLevel(), (int) (ToolMaterial.GOLD.getMaxUses() * Config.toolDurabilityModifier), (int) (ToolMaterial.GOLD.getEfficiencyOnProperMaterial() * Config.toolSpeedModifier), ToolMaterial.GOLD.getDamageVsEntity(), ToolMaterial.GOLD.getEnchantability()).setRepairItem(new ItemStack(Items.GOLD_INGOT));
    public static final ToolMaterial DIAMOND = EnumHelper.addToolMaterial("HammerDiamond", ToolMaterial.DIAMOND.getHarvestLevel(), (int) (ToolMaterial.DIAMOND.getMaxUses() * Config.toolDurabilityModifier), (int) (ToolMaterial.DIAMOND.getEfficiencyOnProperMaterial() * Config.toolSpeedModifier), ToolMaterial.DIAMOND.getDamageVsEntity(), ToolMaterial.DIAMOND.getEnchantability()).setRepairItem(new ItemStack(Items.DIAMOND));
    public static final ToolMaterial MJOLNIR = EnumHelper.addToolMaterial("HammerMjolnir", Config.mjolnirHarvestLevel, 1, Config.mjolnirEfficiency, Config.mjolnirDamageVsEntity, 0);
    public static final ToolMaterial MINI = EnumHelper.addToolMaterial("HammerMini", IRON.getHarvestLevel(), (int) (IRON.getMaxUses() * 0.5), IRON.getEfficiencyOnProperMaterial(), IRON.getDamageVsEntity() * 0.75f, IRON.getEnchantability()).setRepairItem(new ItemStack(Items.IRON_INGOT));
    public static final ToolMaterial GIANT = EnumHelper.addToolMaterial("HammerGiant", IRON.getHarvestLevel(), IRON.getMaxUses() * 6, IRON.getEfficiencyOnProperMaterial() * 0.5f, IRON.getDamageVsEntity() * 1.25f, IRON.getEnchantability()).setRepairItem(new ItemStack(Blocks.IRON_BLOCK));
    public static final ToolMaterial NETHERSTAR = EnumHelper.addToolMaterial("HammerNetherStar", ToolMaterial.DIAMOND.getHarvestLevel(), Config.netherStarHammerDurability, ToolMaterial.DIAMOND.getEfficiencyOnProperMaterial(), 20.0f, 0).setRepairItem(new ItemStack(Items.NETHER_STAR));
}
