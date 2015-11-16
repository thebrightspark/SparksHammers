package com.brightspark.sparkshammers.reference;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ModHammerMaterials
{
    //TODO: Add more mod material made hammers!

    //Botania
    public static ToolMaterial HAMMER_MANASTEEL, HAMMER_TERRASTEEL, HAMMER_ELEMENTIUM;
    //Random Things
    public static ToolMaterial HAMMER_SPECTRE;
    //EnderIO
    public static ToolMaterial HAMMER_DARKSTEEL;
    //Misc
    public static ToolMaterial HAMMER_COPPER;
    public static ToolMaterial HAMMER_BRONZE;
    public static ToolMaterial HAMMER_STEEL;

    public static void init()
    {
        //Go through tool materials and print them out with details
        /*
        LogHelper.info("Current Tool Materials!");
        ToolMaterial[] mat = ToolMaterial.values();
        for(ToolMaterial m : mat)
        {
            LogHelper.info("Material: " + m.name());
            LogHelper.info(m.getHarvestLevel()+","+m.getMaxUses()+","+m.getEfficiencyOnProperMaterial()+","+m.getDamageVsEntity()+","+m.getEnchantability());
        }
        */

        //Botania
        HAMMER_MANASTEEL = EnumHelper.addToolMaterial("HammerManasteel", Config.manasteelHarvestLevel, Config.manasteelMaxUses, Config.manasteelEfficiency, Config.manasteelDamageVsEntity, Config.manasteelEnchantability);
        HAMMER_TERRASTEEL = EnumHelper.addToolMaterial("HammerTerrasteel", Config.terrasteelHarvestLevel, Config.terrasteelMaxUses, Config.terrasteelEfficiency, Config.terrasteelDamageVsEntity, Config.terrasteelEnchantability);
        HAMMER_ELEMENTIUM = EnumHelper.addToolMaterial("HammerElementium", Config.elementiumHarvestLevel, Config.elementiumMaxUses, Config.elementiumEfficiency, Config.elementiumDamageVsEntity, Config.elementiumEnchantability);
        //Random Things
        HAMMER_SPECTRE = EnumHelper.addToolMaterial("HammerSpectre", Config.spectreHarvestLevel, Config.spectreMaxUses, Config.spectreEfficiency, Config.spectreDamageVsEntity, Config.spectreEnchantability);
        //EnderIO
        HAMMER_DARKSTEEL = EnumHelper.addToolMaterial("HammerDarksteel", Config.darksteelHarvestLevel, Config.darksteelMaxUses, Config.darksteelEfficiency, Config.darksteelDamageVsEntity, Config.darksteelEnchantability);
        //Misc
        HAMMER_BRONZE = EnumHelper.addToolMaterial("Hammerbronze", Config.bronzeHarvestLevel, Config.bronzeMaxUses, Config.bronzeEfficiency, Config.bronzeDamageVsEntity, Config.bronzeEnchantability);
    }
}
