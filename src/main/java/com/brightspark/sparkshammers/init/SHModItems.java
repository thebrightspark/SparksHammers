package com.brightspark.sparkshammers.init;

import com.brightspark.sparkshammers.item.ItemHammer;
import com.brightspark.sparkshammers.item.ItemResource;
import com.brightspark.sparkshammers.reference.ModHammerMaterials;
import com.brightspark.sparkshammers.reference.Names;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTool;

public class SHModItems
{
    //Botania
    public static final Item itemHeadManasteel = new ItemResource(Names.ModItems.HEAD_MANASTEEL, Names.Mods.BOTANIA);
    public static final Item itemHeadTerrasteel = new ItemResource(Names.ModItems.HEAD_TERRASTEEL, Names.Mods.BOTANIA);
    public static final Item itemHeadElementium = new ItemResource(Names.ModItems.HEAD_ELEMENTIUM, Names.Mods.BOTANIA);
    public static final ItemTool hammerManasteel = new ItemHammer(Names.ModItems.HAMMER_MANASTEEL, ModHammerMaterials.HAMMER_MANASTEEL, Names.Mods.BOTANIA);
    public static final ItemTool hammerTerrasteel = new ItemHammer(Names.ModItems.HAMMER_TERRASTEEL, ModHammerMaterials.HAMMER_TERRASTEEL, Names.Mods.BOTANIA);
    public static final ItemTool hammerElementium = new ItemHammer(Names.ModItems.HAMMER_ELEMENTIUM, ModHammerMaterials.HAMMER_ELEMENTIUM, Names.Mods.BOTANIA);

    public static void init()
    {
        //Botania
        GameRegistry.registerItem(itemHeadManasteel, Names.ModItems.HEAD_MANASTEEL);
        GameRegistry.registerItem(itemHeadTerrasteel, Names.ModItems.HEAD_TERRASTEEL);
        GameRegistry.registerItem(itemHeadElementium, Names.ModItems.HEAD_ELEMENTIUM);
        GameRegistry.registerItem(hammerManasteel, Names.ModItems.HAMMER_MANASTEEL);
        GameRegistry.registerItem(hammerTerrasteel, Names.ModItems.HAMMER_TERRASTEEL);
        GameRegistry.registerItem(hammerElementium, Names.ModItems.HAMMER_ELEMENTIUM);
    }
}
