package brightspark.sparkshammers.api;

import brightspark.sparkshammers.customTools.Tool;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

/**
 * A mod should subscribe to the {@link RegisterToolEvent} and add instances of this class to it to create new hammers
 * and excavators.
 *
 * There are 3 constructors you can use, each provide a different way of creating the recipe for the tools. Please read
 * the comments on each constructor for what they do.
 */
public class ModTool extends Tool
{
    private Type type = Type.BOTH;
    public Object[] recipe;

    /**
     * This will set an ore dictionary entry to be used in creating recipes for this tool
     *
     * @param name            The material name for the tool. This should be the name you expect to see as part of the
     *                        tool's name in-game. For example, "Iron" will be used to make the names "Iron Hammer" and
     *                        "Iron Excavator" as well as the tool's registry names "hammer_iron" and "excavator_iron".
     * @param material        The {@link ToolMaterial} which will be used to create the hammer and/or excavator
     * @param colour          The colour used to colour the tool head texture
     * @param dependantOreDic The ore dictionary name used for the tool head part of the recipe (the handle will be
     *                        sticks)
     */
    public ModTool(@Nonnull String name, @Nonnull ToolMaterial material, int colour, @Nonnull String dependantOreDic)
    {
        super(name, material, colour);
        this.dependantOreDic = dependantOreDic;
    }

    /**
     * This will set an ore dictionary entry to be used in creating recipes for this tool
     *
     * @param name           The material name for the tool. This should be the name you expect to see as part of the
     *                       tool's name in-game. For example, "Iron" will be used to make the names "Iron Hammer" and
     *                       "Iron Excavator" as well as the tool's registry names "hammer_iron" and "excavator_iron".
     * @param material       The {@link ToolMaterial} which will be used to create the hammer and/or excavator
     * @param colour         The colour used to colour the tool head texture
     * @param dependantStack The {@link ItemStack} used for the tool head part of the recipe (the handle will be sticks)
     */
    public ModTool(@Nonnull String name, @Nonnull ToolMaterial material, int colour, @Nonnull ItemStack dependantStack)
    {
        super(name, material, colour);
        this.dependantStack = dependantStack;
    }

    /**
     * This will set an ore dictionary entry to be used in creating recipes for this tool
     *
     * @param name     The material name for the tool. This should be the name you expect to see as part of the tool's
     *                 name in-game. For example, "Iron" will be used to make the names "Iron Hammer" and
     *                 "Iron Excavator" as well as the tool's registry names "hammer_iron" and "excavator_iron".
     * @param material The {@link ToolMaterial} which will be used to create the hammer and/or excavator
     * @param colour   The colour used to colour the tool head texture
     * @param recipe   The recipe to use for this tool
     *                 NOTE: THIS CAN ONLY BE USED FOR A HAMMER ONLY OR EXCAVATOR ONLY, NOT BOTH
     *                 If you want both, then add them as two separate ModTools
     *                 The recipe should follow the same format as the
     *                 {@link brightspark.sparkshammers.hammerCrafting.HammerShapedOreRecipe)
     *                 (i.e. same a {@link net.minecraftforge.oredict.ShapedOreRecipe} but there's more slots:
     *                 Crafting matrix:
     *                 H H H H H
     *                 H H H H H
     *                 S S S S X
     *                 H = hammer head
     *                 S = stick (handle)
     *                 X = not used
     * @see {@link ModTool#setHammerOnly()}
     * @see {@link ModTool#setExcavatorOnly()}
     */
    //TODO: Removed until I find a way to save the custom recipe
    /*
    public ModTool(@Nonnull String name, @Nonnull ToolMaterial material, int colour, @Nonnull Object... recipe)
    {
        super(name, material, colour);
        this.recipe = recipe;
    }
    */

    /**
     * This will mean that only a hammer will be created for this tool
     */
    public ModTool setHammerOnly()
    {
        type = Type.HAMMER;
        return this;
    }

    /**
     * This will mean that only an excavator will be created for this tool
     */
    public ModTool setExcavatorOnly()
    {
        type = Type.EXCAVATOR;
        return this;
    }

    public Type getType()
    {
        return type;
    }

    public enum Type
    {
        HAMMER,
        EXCAVATOR,
        BOTH;

        public boolean isHammer()
        {
            return this == BOTH || this == HAMMER;
        }

        public boolean isExcavator()
        {
            return this == BOTH || this == EXCAVATOR;
        }
    }
}
