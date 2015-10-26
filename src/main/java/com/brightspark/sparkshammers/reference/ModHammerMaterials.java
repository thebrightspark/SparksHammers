package com.brightspark.sparkshammers.reference;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ModHammerMaterials
{
    //Botania
    public static ToolMaterial MANASTEEL;
    public static ToolMaterial TERRASTEEL;
    public static ToolMaterial ELEMENTIUM;
    public static final ToolMaterial HAMMER_MANASTEEL = EnumHelper.addToolMaterial("HammerManasteel", MANASTEEL.getHarvestLevel(), (int) (MANASTEEL.getMaxUses() * Config.toolDurabilityModifier), MANASTEEL.getEfficiencyOnProperMaterial() * Config.toolSpeedModifier, MANASTEEL.getDamageVsEntity(), MANASTEEL.getEnchantability());
    public static final ToolMaterial HAMMER_TERRASTEEL = EnumHelper.addToolMaterial("HammerTerrasteel", TERRASTEEL.getHarvestLevel(), (int) (TERRASTEEL.getMaxUses() * Config.toolDurabilityModifier), TERRASTEEL.getEfficiencyOnProperMaterial() * Config.toolSpeedModifier, TERRASTEEL.getDamageVsEntity(), TERRASTEEL.getEnchantability());
    public static final ToolMaterial HAMMER_ELEMENTIUM = EnumHelper.addToolMaterial("HammerElementium", ELEMENTIUM.getHarvestLevel(), (int) (ELEMENTIUM.getMaxUses() * Config.toolDurabilityModifier), ELEMENTIUM.getEfficiencyOnProperMaterial() * Config.toolSpeedModifier, ELEMENTIUM.getDamageVsEntity(), ELEMENTIUM.getEnchantability());
}
