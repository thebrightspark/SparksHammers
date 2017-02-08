package com.brightspark.sparkshammers.customTools;

import com.brightspark.sparkshammers.reference.Names;
import com.brightspark.sparkshammers.reference.Reference;
import com.brightspark.sparkshammers.util.CommonUtils;
import com.brightspark.sparkshammers.util.LogHelper;
import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark on 06/02/2017.
 */
public class CustomTools
{
    private static File jsonFile = new File(Reference.CONFIG_DIR, "customTools.json");
    private static List<String> SPECIAL_TOOLS = Lists.newArrayList("mjolnir", "mini", "giant", "netherstar", "powered");

    /**
     * Reads the custom tools' json file and returns them
     */
    public static List<Tool> read()
    {
        LogHelper.info("Reading custom tools from json file...");

        if(Reference.CONFIG_DIR.mkdirs())
            LogHelper.info("Created config directory");

        JsonArray jsonData;
        if(jsonFile.exists())
        {
            try
            {
                jsonData = new JsonParser().parse(new JsonReader(new FileReader(jsonFile))).getAsJsonArray();
            }
            catch(FileNotFoundException e)
            {
                return createDefault();
            }
        }
        else
        {
            return createDefault();
        }

        List<Tool> tools = new ArrayList<Tool>();

        //Read json data and populate tools array
        for(JsonElement toolElement : jsonData)
        {
            JsonObject toolObj = toolElement.getAsJsonObject();
            String name = getJsonString(toolObj.get("MaterialName"), null);
            if(name == null)
            {
                LogHelper.warn("A material in the custom tools json was found without a name! Material will not be added.");
                continue;
            }

            Object dependant = null;
            if(!SPECIAL_TOOLS.contains(name))
            {
                if((dependant = getJsonString(toolObj.get("DependantOreDic"), null)) == null)
                {
                    String id = getJsonString(toolObj.get("DependantItemId"), "");
                    int meta = getJsonInt(toolObj.get("DependantItemMeta"), - 1);
                    if(!id.equals("") && meta != - 1)
                        dependant = new ItemStack(CommonUtils.getRegisteredItem(id), 1, meta);
                    else
                    {
                        LogHelper.warn("No dependant entry for material " + name + " in json file! Material will not be added.");
                        continue;
                    }
                }
            }

            Tool tool = new Tool(
                    name,
                    EnumHelper.addToolMaterial(
                            name,
                            getJsonInt(toolObj.get("HarvestLevel"), 1),
                            getJsonInt(toolObj.get("Durability"), 500),
                            getJsonFloat(toolObj.get("Efficiency"), 1f),
                            getJsonFloat(toolObj.get("AttackDamage"), 2f),
                            getJsonInt(toolObj.get("Enchantability"), 0)),
                    getJsonInt(toolObj.get("TextureColour"), -1),
                    dependant);
            tools.add(tool);
        }

        LogHelper.info("Finished reading custom tools");
        return tools;
    }

    private static String getJsonString(JsonElement element, String defaultValue)
    {
        return element == null ? defaultValue : element.getAsString();
    }

    private static int getJsonInt(JsonElement element, int defaultValue)
    {
        return element == null ? defaultValue : element.getAsInt();
    }

    private static float getJsonFloat(JsonElement element, float defaultValue)
    {
        return element == null ? defaultValue : element.getAsFloat();
    }

    /**
     * Creates the default json file with all tools I've added
     */
    private static List<Tool> createDefault()
    {
        LogHelper.info("File not found, creating default json file...");

        List<Tool> tools = new ArrayList<Tool>();

        //Populate tools array
        for(Names.EnumMaterials material : Names.EnumMaterials.values())
            tools.add(new Tool(material));

        //Create file from tools array
        try
        {
            JsonWriter writer = new JsonWriter(new FileWriter(jsonFile));
            writer.setIndent("    ");
            writer.beginArray();
            for(Tool tool : tools)
            {
                writer.beginObject();
                writer.name("MaterialName").value(tool.materialName);
                writer.name("HarvestLevel").value(tool.material.getHarvestLevel());
                writer.name("Durability").value(tool.material.getMaxUses());
                writer.name("Efficiency").value(tool.material.getEfficiencyOnProperMaterial());
                writer.name("AttackDamage").value(tool.material.getDamageVsEntity());
                writer.name("Enchantability").value(tool.material.getEnchantability());
                if(tool.toolColour >= 0)
                    writer.name("TextureColour").value(tool.toolColour);
                if(tool.dependantOreDic != null)
                    writer.name("DependantOreDic").value(tool.dependantOreDic);
                else if(tool.dependantStack != null)
                {
                    String itemId = CommonUtils.getRegisteredId(tool.dependantStack.getItem());
                    if(itemId != null)
                    {
                        writer.name("DependantItemId").value(itemId);
                        writer.name("DependantItemMeta").value(tool.dependantStack.getMetadata());
                    }
                    else
                        LogHelper.warn("Id not found for stack " + tool.dependantStack.toString() + "! Dependant item not being saved for material " + tool.materialName);
                }
                writer.endObject();
            }
            writer.endArray();
            writer.close();
        }
        catch(IOException e)
        {
            LogHelper.error("Error creating default custom tool json file!");
            e.printStackTrace();
        }

        LogHelper.info("Finished creating default json file");
        return tools;
    }
}
