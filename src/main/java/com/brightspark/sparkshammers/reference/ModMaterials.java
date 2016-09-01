package com.brightspark.sparkshammers.reference;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ModMaterials
{
    //Common Mod Metals
    public static ToolMaterial COPPER, SILVER, TIN, LEAD, NICKEL, PLATINUM, BRONZE, STEEL, INVAR, ELECTRUM;
    //Botania
    public static ToolMaterial MANASTEEL, TERRASTEEL, ELEMENTIUM;
    //EnderIO
    public static ToolMaterial DARKSTEEL;
    //MobHunter
    public static ToolMaterial MACHALITE, DRAGONITE, GOSSAMITE;

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

        //Common Mod Metals
        COPPER = EnumHelper.addToolMaterial("HammerCopper", Config.copperHarvestLevel, Config.copperMaxUses, Config.copperEfficiency, Config.copperDamageVsEntity, Config.copperEnchantability);
        SILVER = EnumHelper.addToolMaterial("HammerSilver", Config.silverHarvestLevel, Config.silverMaxUses, Config.silverEfficiency, Config.silverDamageVsEntity, Config.silverEnchantability);
        TIN = EnumHelper.addToolMaterial("HammerTin", Config.tinHarvestLevel, Config.tinMaxUses, Config.tinEfficiency, Config.tinDamageVsEntity, Config.tinEnchantability);
        LEAD = EnumHelper.addToolMaterial("HammerLead", Config.leadHarvestLevel, Config.leadMaxUses, Config.leadEfficiency, Config.leadDamageVsEntity, Config.leadEnchantability);
        NICKEL = EnumHelper.addToolMaterial("HammerNickel", Config.nickelHarvestLevel, Config.nickelMaxUses, Config.nickelEfficiency, Config.nickelDamageVsEntity, Config.nickelEnchantability);
        PLATINUM = EnumHelper.addToolMaterial("HammerPlatinum", Config.platinumHarvestLevel, Config.platinumMaxUses, Config.platinumEfficiency, Config.platinumDamageVsEntity, Config.platinumEnchantability);
        BRONZE = EnumHelper.addToolMaterial("HammerBronze", Config.bronzeHarvestLevel, Config.bronzeMaxUses, Config.bronzeEfficiency, Config.bronzeDamageVsEntity, Config.bronzeEnchantability);
        STEEL = EnumHelper.addToolMaterial("HammerSteel", Config.steelHarvestLevel, Config.steelMaxUses, Config.steelEfficiency, Config.steelDamageVsEntity, Config.steelEnchantability);
        INVAR = EnumHelper.addToolMaterial("HammerInvar", Config.invarHarvestLevel, Config.invarMaxUses, Config.invarEfficiency, Config.invarDamageVsEntity, Config.invarEnchantability);
        ELECTRUM = EnumHelper.addToolMaterial("HammerElectrum", Config.electrumHarvestLevel, Config.electrumMaxUses, Config.electrumEfficiency, Config.electrumDamageVsEntity, Config.electrumEnchantability);
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
    }
}
