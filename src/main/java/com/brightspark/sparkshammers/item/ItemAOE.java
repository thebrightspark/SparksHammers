package com.brightspark.sparkshammers.item;

import com.brightspark.sparkshammers.SparksHammers;
import com.brightspark.sparkshammers.init.SHAchievements;
import com.brightspark.sparkshammers.init.SHItems;
import com.brightspark.sparkshammers.reference.Names;
import com.brightspark.sparkshammers.util.CommonUtils;
import com.brightspark.sparkshammers.util.Lang;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.Set;

public class ItemAOE extends ItemTool implements IColourable
{
    private static final Set<Block> PickaxeBlocks = Sets.newHashSet(new Block[] {Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.DOUBLE_STONE_SLAB, Blocks.GOLDEN_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.LIT_REDSTONE_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.STONE_SLAB, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE});
    private static final Set<Material> PickaxeMats = Sets.newHashSet(new Material[]{Material.ANVIL, Material.GLASS, Material.ICE, Material.IRON, Material.PACKED_ICE, Material.PISTON, Material.ROCK});
    private static final Set<Block> ShovelBlocks = Sets.newHashSet(new Block[] {Blocks.CLAY, Blocks.DIRT, Blocks.FARMLAND, Blocks.GRASS, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.SAND, Blocks.SNOW, Blocks.SNOW_LAYER, Blocks.SOUL_SAND, Blocks.GRASS_PATH});
    private static final Set<Material> ShovelMats = Sets.newHashSet(new Material[]{Material.GRASS, Material.GROUND, Material.SAND, Material.SNOW, Material.CRAFTED_SNOW, Material.CLAY});

    //True if a hammer, false if an excavator
    private boolean isExcavator;
    private int mineWidth = 1;
    private int mineHeight = 1;
    private int mineDepth = 0; //Depth (behind block)
    private boolean infiniteUse;
    private boolean shiftRotating = false;

    //The material types which the tool can mine in AOE:
    private Set<Material> materials;

    //Used for the colour tint of the head of the tool texture
    protected int textureColour = -1;

    public ItemAOE(Names.EnumMaterials name)
    {
        this(name, false);
    }

    public ItemAOE(Names.EnumMaterials name, boolean isExcavator)
    {
        this(name, isExcavator, false);
    }

    public ItemAOE(Names.EnumMaterials name, boolean isExcavator, boolean isInfiniteUse)
    {
        this(name.unlocToolName(isExcavator), name.material, isExcavator, isInfiniteUse);
        textureColour = name.colour;
    }

    public ItemAOE(String name, ToolMaterial material)
    {
        this(name, material, false, false);
    }

    public ItemAOE(String name, ToolMaterial material, boolean isExcavator)
    {
        this(name, material, isExcavator, false);
    }

    public ItemAOE(String name, ToolMaterial material, boolean isExcavator, boolean isInfiniteUse)
    {
        super(isExcavator ? 1.5f : 2f, isExcavator ? -3f : -3.2f, material, isExcavator ? ShovelBlocks : PickaxeBlocks);
        this.isExcavator = isExcavator;
        infiniteUse = isInfiniteUse;
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(SparksHammers.SH_TAB);
        materials = isExcavator ? ShovelMats : PickaxeMats;
    }

    /**
     * Check whether this Item can harvest the given Block
     */
    public boolean canHarvestBlock(IBlockState state)
    {
        Block block = state.getBlock();

        if(!isExcavator)
            return block == Blocks.OBSIDIAN ? this.toolMaterial.getHarvestLevel() == 3 : (block != Blocks.DIAMOND_BLOCK && block != Blocks.DIAMOND_ORE ? (block != Blocks.EMERALD_ORE && block != Blocks.EMERALD_BLOCK ? (block != Blocks.GOLD_BLOCK && block != Blocks.GOLD_ORE ? (block != Blocks.IRON_BLOCK && block != Blocks.IRON_ORE ? (block != Blocks.LAPIS_BLOCK && block != Blocks.LAPIS_ORE ? (block != Blocks.REDSTONE_ORE && block != Blocks.LIT_REDSTONE_ORE ? (state.getMaterial() == Material.ROCK ? true : (state.getMaterial() == Material.IRON ? true : state.getMaterial() == Material.ANVIL)) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2);
        else
            return block == Blocks.SNOW_LAYER ? true : block == Blocks.SNOW;
    }

    /**
     * Used in vanilla code for the pickaxe, but not the shovel
     */
    public float getStrVsBlock(ItemStack stack, IBlockState state)
    {
        if(!isExcavator)
            return state.getMaterial() != Material.IRON && state.getMaterial() != Material.ANVIL && state.getMaterial() != Material.ROCK ? super.getStrVsBlock(stack, state) : this.efficiencyOnProperMaterial;
        else
            return super.getStrVsBlock(stack, state);
    }

    /**
     * If true, then the tool will take do damage from digging or attacking.
     * @param isInfinite Set to true for infinite use
     */
    public void setInfinite(boolean isInfinite)
    {
        infiniteUse = isInfinite;
    }

    //Override method from ItemTool to stop durability loss
    public boolean hitEntity(ItemStack stack, EntityLivingBase entityHit, EntityLivingBase player)
    {
        //Does not decrease durability if has infinite use
        return !infiniteUse && super.hitEntity(stack, entityHit, player);
    }

    public RayTraceResult rayTrace(World worldIn, EntityPlayer playerIn, boolean useLiquids)
    {
        return super.rayTrace(worldIn, playerIn, useLiquids);
    }

    //Override method from ItemTool to stop durability loss
    public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos, EntityLivingBase player)
    {
        //Does not decrease durability if has infinite use
        return !infiniteUse && super.onBlockDestroyed(stack, world, state, pos, player);
    }

    // <<<< From Tinkers Construct: HarvestTool >>>>
    public boolean isEffective(IBlockState state)
    {
        return materials.contains(state.getMaterial());
    }

    /**
     * Used for most hammers
     */
    private void breakArea(ItemStack stack, EntityPlayer player, BlockPos posHit, BlockPos posStart, BlockPos posEnd)
    {
        for (int xPos = posStart.getX(); xPos <= posEnd.getX(); xPos++)
            for (int yPos = posStart.getY(); yPos <= posEnd.getY(); yPos++)
                for (int zPos = posStart.getZ(); zPos <= posEnd.getZ(); zPos++)
                {
                    // don't break the originally already broken block, duh
                    if (xPos == posHit.getX() && yPos == posHit.getY() && zPos == posHit.getZ())
                        continue;

                    if(!super.onBlockStartBreak(stack, new BlockPos(xPos, yPos, zPos), player))
                        CommonUtils.breakBlock(stack, player.worldObj, player, new BlockPos(xPos, yPos, zPos), posHit);
                }
    }

    // <<<< Also made with some help from Tinkers Construct >>>>
    public boolean onBlockStartBreak (ItemStack stack, BlockPos pos, EntityPlayer player)
    {
        //Block being mined
        RayTraceResult ray = rayTrace(player.worldObj, player, false);
        if(ray == null)
            return super.onBlockStartBreak(stack, pos, player);

        //Calculate area to break
        BlockPos[] positions = CommonUtils.getBreakArea((ItemAOE) stack.getItem(), pos, ray.sideHit, player);
        BlockPos start = positions[0];
        BlockPos end = positions[1];

        //LogHelper.info("Breaking blocks from " + start.toString() + " to " + end.toString());
        breakArea(stack, player, pos, start, end);

        return super.onBlockStartBreak(stack, pos, player);
    }

    public ItemAOE setMineWidth(int width)
    {
        mineWidth = width;
        return this;
    }

    public ItemAOE setMineHeight(int height)
    {
        mineHeight = height;
        return this;
    }

    public ItemAOE setShiftRotating(boolean bool)
    {
        shiftRotating = bool;
        return this;
    }

    public int getMineWidth()
    {
        return mineWidth;
    }

    public int getMineHeight()
    {
        return mineHeight;
    }

    public int getMineDepth()
    {
        return mineDepth;
    }

    public boolean getShiftRotating()
    {
        return shiftRotating;
    }

    public ItemAOE setItemColour(int colour)
    {
        textureColour = colour;
        return this;
    }

    /**
     * Called when item is crafted/smelted. Used only by maps so far.
     */
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer player)
    {
        //Sets the name to Jason's custom name if he crafts the giant hammer.
        if(stack.getItem().equals(SHItems.hammerGiant) && player.getDisplayNameString().equals("8BrickDMG"))
            stack.setStackDisplayName(TextFormatting.LIGHT_PURPLE + Lang.localize(getUnlocalizedName(stack) + ".8brickdmg"));

        //Handle achievements
        Item item = stack.getItem();
        if(item.equals(SHItems.hammerWood))
            player.addStat(SHAchievements.woodHammer);
        else if(item.equals(SHItems.hammerDiamond))
            player.addStat(SHAchievements.diamondHammer);
        else if(item.equals(SHItems.hammerNetherStar))
            player.addStat(SHAchievements.netherStarHammer);
    }

    @Override
    public int getTextureColour()
    {
        return textureColour;
    }
}
