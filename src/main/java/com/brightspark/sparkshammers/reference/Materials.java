package com.brightspark.sparkshammers.reference;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class Materials
{
    //Name, harvest level, durability, mining speed, damage vs entities, enchantability
    public static final ToolMaterial HAMMER_WOOD = EnumHelper.addToolMaterial("HammerWood", ToolMaterial.WOOD.getHarvestLevel(), (int) (ToolMaterial.WOOD.getMaxUses() * Config.toolDurabilityModifier), (int) (ToolMaterial.WOOD.getEfficiencyOnProperMaterial() * Config.toolSpeedModifier), ToolMaterial.WOOD.getDamageVsEntity(), ToolMaterial.WOOD.getEnchantability());
    public static final ToolMaterial HAMMER_STONE = EnumHelper.addToolMaterial("HammerStone", ToolMaterial.STONE.getHarvestLevel(), (int) (ToolMaterial.STONE.getMaxUses() * Config.toolDurabilityModifier), (int) (ToolMaterial.STONE.getEfficiencyOnProperMaterial() * Config.toolSpeedModifier), ToolMaterial.STONE.getDamageVsEntity(), ToolMaterial.STONE.getEnchantability());
    public static final ToolMaterial HAMMER_IRON = EnumHelper.addToolMaterial("HammerIron", ToolMaterial.IRON.getHarvestLevel(), (int) (ToolMaterial.IRON.getMaxUses() * Config.toolDurabilityModifier), (int) (ToolMaterial.IRON.getEfficiencyOnProperMaterial() * Config.toolSpeedModifier), ToolMaterial.IRON.getDamageVsEntity(), ToolMaterial.IRON.getEnchantability());
    public static final ToolMaterial HAMMER_GOLD = EnumHelper.addToolMaterial("HammerGold", ToolMaterial.GOLD.getHarvestLevel(), (int) (ToolMaterial.GOLD.getMaxUses() * Config.toolDurabilityModifier), (int) (ToolMaterial.GOLD.getEfficiencyOnProperMaterial() * Config.toolSpeedModifier), ToolMaterial.GOLD.getDamageVsEntity(), ToolMaterial.GOLD.getEnchantability());
    public static final ToolMaterial HAMMER_DIAMOND = EnumHelper.addToolMaterial("HammerDiamond", ToolMaterial.EMERALD.getHarvestLevel(), (int) (ToolMaterial.EMERALD.getMaxUses() * Config.toolDurabilityModifier), (int) (ToolMaterial.EMERALD.getEfficiencyOnProperMaterial() * Config.toolSpeedModifier), ToolMaterial.EMERALD.getDamageVsEntity(), ToolMaterial.EMERALD.getEnchantability());
    public static final ToolMaterial HAMMER_MINI = EnumHelper.addToolMaterial("HammerMini", HAMMER_IRON.getHarvestLevel(), (int) (HAMMER_IRON.getMaxUses() * 0.5), HAMMER_IRON.getEfficiencyOnProperMaterial(), HAMMER_IRON.getDamageVsEntity() * 0.75f, HAMMER_IRON.getEnchantability());
    public static final ToolMaterial HAMMER_GIANT = EnumHelper.addToolMaterial("HammerGiant", HAMMER_IRON.getHarvestLevel(), (int) (HAMMER_IRON.getMaxUses() * 6), HAMMER_IRON.getEfficiencyOnProperMaterial() * 0.5f, HAMMER_IRON.getDamageVsEntity() * 1.25f, HAMMER_IRON.getEnchantability());
    public static final ToolMaterial HAMMER_NETHERSTAR = EnumHelper.addToolMaterial("HammerNetherStar", ToolMaterial.EMERALD.getHarvestLevel(), Config.netherStarHammerDurability, ToolMaterial.EMERALD.getEfficiencyOnProperMaterial(), 10.0f, 0);
}
