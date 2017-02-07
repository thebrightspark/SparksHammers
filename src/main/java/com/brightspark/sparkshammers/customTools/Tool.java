package com.brightspark.sparkshammers.customTools;

import com.brightspark.sparkshammers.reference.Names;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;

/**
 * Created by Mark on 06/02/2017.
 */
public class Tool
{
    public String materialName;
    public ToolMaterial material;
    public int toolColour;
    public String dependantOreDic;
    public ItemStack dependantStack;

    private Tool(String materialName, ToolMaterial material, int toolColour)
    {
        this.materialName = materialName;
        this.material = material;
        this.toolColour = toolColour;
    }

    public Tool(String materialName, ToolMaterial material, int toolColour, Object dependantItem)
    {
        this(materialName, material, toolColour);
        if(dependantItem instanceof String)
            dependantOreDic = (String) dependantItem;
        else if(dependantItem instanceof ItemStack)
            dependantStack = (ItemStack) dependantItem;
        else
            throw new IllegalArgumentException("Dependant Item must be a String (ore dictionary) or an ItemStack!");
    }

    public Tool(Names.EnumMaterials enumMaterial)
    {
        this(enumMaterial.toString().toLowerCase(), enumMaterial.material, enumMaterial.colour);
        if(enumMaterial.dependantOreDic != null)
            dependantOreDic = enumMaterial.dependantOreDic;
        else if(enumMaterial.dependantItem != null)
            dependantStack = enumMaterial.dependantItem;
    }

    public String getToolName(boolean isExcavator)
    {
        return (isExcavator ? Names.Items.EXCAVATOR : Names.Items.HAMMER) + "_" + materialName.toLowerCase();
    }
}
