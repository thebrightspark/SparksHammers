package com.brightspark.sparkshammers.reference;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ModMaterials
{
    //Botania
    public static ToolMaterial MANASTEEL, TERRASTEEL, ELEMENTIUM;
    //EnderIO
    public static ToolMaterial DARKSTEEL;
    //MobHunter
    public static ToolMaterial MACHALITE, DRAGONITE, GOSSAMITE;
    //Misc
    public static ToolMaterial COPPER;
    public static ToolMaterial BRONZE;
    public static ToolMaterial STEEL;

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
        MANASTEEL = EnumHelper.addToolMaterial("HammerManasteel", Config.manasteelHarvestLevel, Config.manasteelMaxUses, Config.manasteelEfficiency, Config.manasteelDamageVsEntity, Config.manasteelEnchantability);
        TERRASTEEL = EnumHelper.addToolMaterial("HammerTerrasteel", Config.terrasteelHarvestLevel, Config.terrasteelMaxUses, Config.terrasteelEfficiency, Config.terrasteelDamageVsEntity, Config.terrasteelEnchantability);
        ELEMENTIUM = EnumHelper.addToolMaterial("HammerElementium", Config.elementiumHarvestLevel, Config.elementiumMaxUses, Config.elementiumEfficiency, Config.elementiumDamageVsEntity, Config.elementiumEnchantability);
        //EnderIO
        DARKSTEEL = EnumHelper.addToolMaterial("HammerDarksteel", Config.darksteelHarvestLevel, Config.darksteelMaxUses, Config.darksteelEfficiency, Config.darksteelDamageVsEntity, Config.darksteelEnchantability);
        //MobHunter
        MACHALITE = EnumHelper.addToolMaterial("HammerMachalite", Config.machaliteHarvestLevel, Config.machaliteMaxUses, Config.machaliteEfficiency, Config.machaliteDamageVsEntity, Config.machaliteEnchantability);
        DRAGONITE = EnumHelper.addToolMaterial("HammerDragonite", Config.dragoniteHarvestLevel, Config.dragoniteMaxUses, Config.dragoniteEfficiency, Config.dragoniteDamageVsEntity, Config.dragoniteEnchantability);
        GOSSAMITE = EnumHelper.addToolMaterial("HammerGossamite", Config.gossamiteHarvestLevel, Config.gossamiteMaxUses, Config.gossamiteEfficiency, Config.gossamiteDamageVsEntity, Config.gossamiteEnchantability);
        //Misc
        BRONZE = EnumHelper.addToolMaterial("HammerBronze", Config.bronzeHarvestLevel, Config.bronzeMaxUses, Config.bronzeEfficiency, Config.bronzeDamageVsEntity, Config.bronzeEnchantability);
    }
}
