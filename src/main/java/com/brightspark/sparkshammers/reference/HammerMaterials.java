package com.brightspark.sparkshammers.reference;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class HammerMaterials
{
    //Name, harvest level, durability, mining speed, damage vs entities, enchantability
    public static final ToolMaterial HAMMER_WOOD = EnumHelper.addToolMaterial("HammerWood", ToolMaterial.WOOD.getHarvestLevel(), ToolMaterial.WOOD.getMaxUses() * 8, ToolMaterial.WOOD.getEfficiencyOnProperMaterial() * 0.8f, ToolMaterial.WOOD.getDamageVsEntity(), ToolMaterial.WOOD.getEnchantability());
    public static final ToolMaterial HAMMER_STONE = EnumHelper.addToolMaterial("HammerStone", ToolMaterial.STONE.getHarvestLevel(), ToolMaterial.STONE.getMaxUses()*8, ToolMaterial.STONE.getEfficiencyOnProperMaterial()*0.8f, ToolMaterial.STONE.getDamageVsEntity(), ToolMaterial.STONE.getEnchantability());
    public static final ToolMaterial HAMMER_IRON = EnumHelper.addToolMaterial("HammerIron", ToolMaterial.IRON.getHarvestLevel(), ToolMaterial.IRON.getMaxUses()*8, ToolMaterial.IRON.getEfficiencyOnProperMaterial()*0.8f, ToolMaterial.IRON.getDamageVsEntity(), ToolMaterial.IRON.getEnchantability());
    public static final ToolMaterial HAMMER_GOLD = EnumHelper.addToolMaterial("HammerGold", ToolMaterial.GOLD.getHarvestLevel(), ToolMaterial.GOLD.getMaxUses()*8, ToolMaterial.GOLD.getEfficiencyOnProperMaterial()*0.8f, ToolMaterial.GOLD.getDamageVsEntity(), ToolMaterial.GOLD.getEnchantability());
    public static final ToolMaterial HAMMER_DIAMOND = EnumHelper.addToolMaterial("HammerDiamond", ToolMaterial.EMERALD.getHarvestLevel(), ToolMaterial.EMERALD.getMaxUses()*8, ToolMaterial.EMERALD.getEfficiencyOnProperMaterial()*0.8f, ToolMaterial.EMERALD.getDamageVsEntity(), ToolMaterial.EMERALD.getEnchantability());
}
