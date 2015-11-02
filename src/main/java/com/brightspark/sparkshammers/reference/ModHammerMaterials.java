package com.brightspark.sparkshammers.reference;

import com.brightspark.sparkshammers.util.LoaderHelper;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ModHammerMaterials
{
    //Botania
    private static ToolMaterial MANASTEEL, TERRASTEEL, ELEMENTIUM;
    public static ToolMaterial HAMMER_MANASTEEL, HAMMER_TERRASTEEL, HAMMER_ELEMENTIUM;
    //Random Things
    private static ToolMaterial SPECTRE;
    public static ToolMaterial HAMMER_SPECTRE;
    //EnderIO
    private static ToolMaterial DARKSTEEL;
    public static ToolMaterial HAMMER_DARKSTEEL;
    //IC2
    private static ToolMaterial BRONZE;
    public static ToolMaterial HAMMER_BRONZE;

    public static void init()
    {
        //LogHelper.info("Current Tool Materials!");

        //Go through tool materials and get the other mod ones we want
        Item.ToolMaterial[] mat = Item.ToolMaterial.values();
        for(Item.ToolMaterial m : mat)
        {
            //LogHelper.info("Material: " + m.name());
            //Botania
            if(LoaderHelper.isModLoaded(Names.Mods.BOTANIA))
            {
                if(m.name().equals(Names.ModToolMaterials.BOTANIA_MANASTEEL))
                {
                    MANASTEEL = m;
                    continue;
                }
                if(m.name().equals(Names.ModToolMaterials.BOTANIA_TERRASTEEL))
                {
                    TERRASTEEL = m;
                    continue;
                }
                if(m.name().equals(Names.ModToolMaterials.BOTANIA_ELEMENTIUM))
                {
                    ELEMENTIUM = m;
                    continue;
                }
            }
            //Random Things
            if(LoaderHelper.isModLoaded(Names.Mods.RANDOM_THINGS))
            {
                if(m.name().equals(Names.ModToolMaterials.RANDOMTHINGS_SPECTRE))
                {
                    SPECTRE = m;
                }
            }
            //EnderIO
            if(LoaderHelper.isModLoaded(Names.Mods.ENDERIO))
            {
                if(m.name().equals(Names.ModToolMaterials.ENDERIO_DARKSTEEL))
                {
                    DARKSTEEL = m;
                }
            }
            //IC2
            if(LoaderHelper.isModLoaded(Names.Mods.IC2))
            {
                if(m.name().equals(Names.ModToolMaterials.IC2_BRONZE))
                {
                    BRONZE = m;
                }
            }
        }

        /*
        LogHelper.info("Spectre!");
        LogHelper.info("Harvest level: "+SPECTRE.getHarvestLevel());
        LogHelper.info("Max Uses: "+SPECTRE.getMaxUses());
        LogHelper.info("Efficiency: "+SPECTRE.getEfficiencyOnProperMaterial());
        LogHelper.info("Damage: "+SPECTRE.getDamageVsEntity());
        LogHelper.info("Enchantability: "+SPECTRE.getEnchantability());
        */

        //Botania
        if(LoaderHelper.isModLoaded(Names.Mods.BOTANIA))
        {
            HAMMER_MANASTEEL = EnumHelper.addToolMaterial("HammerManasteel", MANASTEEL.getHarvestLevel(), (int) (MANASTEEL.getMaxUses() * Config.toolDurabilityModifier), MANASTEEL.getEfficiencyOnProperMaterial() * Config.toolSpeedModifier, MANASTEEL.getDamageVsEntity(), MANASTEEL.getEnchantability());
            HAMMER_TERRASTEEL = EnumHelper.addToolMaterial("HammerTerrasteel", TERRASTEEL.getHarvestLevel(), (int) (TERRASTEEL.getMaxUses() * Config.toolDurabilityModifier), TERRASTEEL.getEfficiencyOnProperMaterial() * Config.toolSpeedModifier, TERRASTEEL.getDamageVsEntity(), TERRASTEEL.getEnchantability());
            HAMMER_ELEMENTIUM = EnumHelper.addToolMaterial("HammerElementium", ELEMENTIUM.getHarvestLevel(), (int) (ELEMENTIUM.getMaxUses() * Config.toolDurabilityModifier), ELEMENTIUM.getEfficiencyOnProperMaterial() * Config.toolSpeedModifier, ELEMENTIUM.getDamageVsEntity(), ELEMENTIUM.getEnchantability());
        }
        //Random Things
        if(LoaderHelper.isModLoaded(Names.Mods.RANDOM_THINGS))
        {
            HAMMER_SPECTRE = EnumHelper.addToolMaterial("HammerSpectre", SPECTRE.getHarvestLevel(), (int) (SPECTRE.getMaxUses() * Config.toolDurabilityModifier), SPECTRE.getEfficiencyOnProperMaterial() * Config.toolSpeedModifier, SPECTRE.getDamageVsEntity(), SPECTRE.getEnchantability());
        }
        //EnderIO
        if(LoaderHelper.isModLoaded(Names.Mods.ENDERIO))
        {
            HAMMER_DARKSTEEL = EnumHelper.addToolMaterial("HammerDarksteel", DARKSTEEL.getHarvestLevel(), (int) (DARKSTEEL.getMaxUses() * Config.toolDurabilityModifier), DARKSTEEL.getEfficiencyOnProperMaterial() * Config.toolSpeedModifier, DARKSTEEL.getDamageVsEntity(), DARKSTEEL.getEnchantability());
        }
        //IC2
        if(LoaderHelper.isModLoaded(Names.Mods.IC2))
        {
            HAMMER_BRONZE = EnumHelper.addToolMaterial("HammerBronze", BRONZE.getHarvestLevel(), (int) (BRONZE.getMaxUses() * Config.toolDurabilityModifier), BRONZE.getEfficiencyOnProperMaterial() * Config.toolSpeedModifier, BRONZE.getDamageVsEntity(), BRONZE.getEnchantability());
        }
    }
}
