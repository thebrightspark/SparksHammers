package brightspark.sparkshammers.item;

import brightspark.sparkshammers.SHConfig;
import brightspark.sparkshammers.SparksHammers;
import brightspark.sparkshammers.customTools.Tool;
import brightspark.sparkshammers.init.SHItems;
import brightspark.sparkshammers.EnumMaterials;
import brightspark.sparkshammers.Reference;
import brightspark.sparkshammers.util.CommonUtils;
import brightspark.sparkshammers.util.NBTHelper;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.Set;

public class ItemAOE extends ItemTool implements IColourable
{
    private static final Set<Block> PickaxeBlocks = Sets.newHashSet(Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.DOUBLE_STONE_SLAB, Blocks.GOLDEN_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.LIT_REDSTONE_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.STONE_SLAB, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE);
    private static final Set<Material> PickaxeMats = Sets.newHashSet(Material.ANVIL, Material.GLASS, Material.ICE, Material.IRON, Material.PACKED_ICE, Material.PISTON, Material.ROCK);
    private static final Set<Block> ShovelBlocks = Sets.newHashSet(Blocks.CLAY, Blocks.DIRT, Blocks.FARMLAND, Blocks.GRASS, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.SAND, Blocks.SNOW, Blocks.SNOW_LAYER, Blocks.SOUL_SAND, Blocks.GRASS_PATH, Blocks.CONCRETE_POWDER);
    private static final Set<Material> ShovelMats = Sets.newHashSet(Material.GRASS, Material.GROUND, Material.SAND, Material.SNOW, Material.CRAFTED_SNOW, Material.CLAY);

    //True if a hammer, false if an excavator
    public final boolean isExcavator;
    private int mineWidth = 1;
    private int mineHeight = 1;
    private int mineDepth = 0; //Depth (behind block)
    private boolean infiniteUse;
    private boolean shiftRotating = false;

    private String dependantOreDic;
    private ItemStack dependantStack;
    protected String localName;

    protected static final String KEY_CUSTOM_NAME = "customName";
    protected static final String KEY_CUSTOM_FORMATTING = "customFormatting";

    //The material types which the tool can mine in AOE:
    private Set<Material> materials;

    //Used for the colour tint of the head of the tool texture
    protected int textureColour = -1;

    public ItemAOE(EnumMaterials name)
    {
        this(name, false);
    }

    public ItemAOE(EnumMaterials name, boolean isExcavator)
    {
        this(name, isExcavator, false);
    }

    public ItemAOE(EnumMaterials name, boolean isExcavator, boolean isInfiniteUse)
    {
        this(name.unlocToolName(isExcavator), name.material, isExcavator, isInfiniteUse);
        textureColour = name.colour;
        dependantOreDic = name.dependantOreDic;
    }

    //This constructor is used when registering tools from the custom json file
    public ItemAOE(Tool tool, boolean isExcavator)
    {
        this(tool.getToolName(isExcavator), tool.material, isExcavator, false);
        localName = tool.localName;
        textureColour = tool.toolColour;
        dependantOreDic = tool.dependantOreDic;
        dependantStack = tool.dependantStack;
    }

    public ItemAOE(String name, ToolMaterial material)
    {
        this(name, material, false, false);
    }

    public ItemAOE(String name, ToolMaterial material, boolean isExcavator, boolean isInfiniteUse)
    {
        super(isExcavator ? 0f : 2f, isExcavator ? -3f : -3.2f, material, isExcavator ? ShovelBlocks : PickaxeBlocks);
        this.isExcavator = isExcavator;
        infiniteUse = isInfiniteUse;
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(SparksHammers.SH_TAB);
        materials = isExcavator ? ShovelMats : PickaxeMats;
    }

    @Override
    public Set<String> getToolClasses(ItemStack stack)
    {
        return com.google.common.collect.ImmutableSet.of(isExcavator ? "shovel" : "pickaxe");
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        //Get custom name if there's one in the NBT
        String customName = NBTHelper.getString(stack, KEY_CUSTOM_NAME);
        return customName.equals("") ? super.getUnlocalizedName(stack) : getUnlocalizedName() + "." + customName;
    }

    protected String getLocalName(ItemStack stack)
    {
        return localName != null ? localName + " " + CommonUtils.capitaliseFirstLetter(isExcavator ? Reference.Items.EXCAVATOR : Reference.Items.HAMMER) : super.getItemStackDisplayName(stack);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        //Get custom colour formatting if there's one in the NBT
        String customFormatting = NBTHelper.getString(stack, KEY_CUSTOM_FORMATTING);
        return customFormatting.equals("") ? getLocalName(stack) : customFormatting + getLocalName(stack);
    }

    /**
     * Check whether this Item can harvest the given Block
     */
    @Override
    public boolean canHarvestBlock(IBlockState state)
    {
        Block block = state.getBlock();

        if(!isExcavator)
            return block == Blocks.OBSIDIAN ? this.toolMaterial.getHarvestLevel() == 3 : (block != Blocks.DIAMOND_BLOCK && block != Blocks.DIAMOND_ORE ? (block != Blocks.EMERALD_ORE && block != Blocks.EMERALD_BLOCK ? (block != Blocks.GOLD_BLOCK && block != Blocks.GOLD_ORE ? (block != Blocks.IRON_BLOCK && block != Blocks.IRON_ORE ? (block != Blocks.LAPIS_BLOCK && block != Blocks.LAPIS_ORE ? (block != Blocks.REDSTONE_ORE && block != Blocks.LIT_REDSTONE_ORE ? (state.getMaterial() == Material.ROCK || (state.getMaterial() == Material.IRON || state.getMaterial() == Material.ANVIL)) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2);
        else
            return block == Blocks.SNOW_LAYER || block == Blocks.SNOW;
    }

    /**
     * Used in vanilla code for the pickaxe, but not the shovel
     */
    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state)
    {
        if(!isExcavator)
            return state.getMaterial() != Material.IRON && state.getMaterial() != Material.ANVIL && state.getMaterial() != Material.ROCK ? super.getDestroySpeed(stack, state) : efficiency;
        else
            return super.getDestroySpeed(stack, state);
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
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase entityHit, EntityLivingBase player)
    {
        //Does not decrease durability if has infinite use
        return !infiniteUse && super.hitEntity(stack, entityHit, player);
    }

    @Override
    public RayTraceResult rayTrace(World worldIn, EntityPlayer playerIn, boolean useLiquids)
    {
        return super.rayTrace(worldIn, playerIn, useLiquids);
    }

    //Override method from ItemTool to stop durability loss
    @Override
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

                    if(!super.onBlockStartBreak(stack, new BlockPos(xPos, yPos, zPos), player) && !stack.isEmpty())
                        CommonUtils.breakBlock(stack, player.world, player, new BlockPos(xPos, yPos, zPos), posHit);
                }
    }

    // <<<< Also made with some help from Tinkers Construct >>>>
    @Override
    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player)
    {
        //Block being mined
        RayTraceResult ray = rayTrace(player.world, player, false);
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

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(SHConfig.rightClickPlacesTorches && !world.isRemote && player.inventory.hasItemStack(SparksHammers.TORCH_STACK))
        {
            //Find torches in the player's inventory
            ItemStack torchStack = CommonUtils.findItemInPlayerInv(player, SparksHammers.TORCH_ITEM);
            if(torchStack != null)
            {
                //Place torch
                BlockPos torchPos = world.getBlockState(pos).getBlock().isReplaceable(world, pos) ? pos : pos.offset(facing);
                if(player.canPlayerEdit(pos, facing, torchStack) && world.mayPlace(Blocks.TORCH, torchPos, false, facing, null))
                {
                    IBlockState torchState = Blocks.TORCH.getStateForPlacement(world, torchPos, facing, hitX, hitY, hitZ, 0, player, hand);
                    boolean success = SparksHammers.TORCH_ITEM.placeBlockAt(torchStack, player, world, torchPos, facing, hitX, hitY, hitZ, torchState);
                    if(success)
                    {
                        torchState = world.getBlockState(torchPos);
                        SoundType soundtype = torchState.getBlock().getSoundType(torchState, world, pos, player);
                        world.playSound(null, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                        if(!player.isCreative())
                            torchStack.shrink(1);
                    }
                    return success ? EnumActionResult.SUCCESS : EnumActionResult.PASS;
                }
            }
        }
        return super.onItemUse(player, world, pos, hand, facing, hitX, hitY, hitZ);
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

    /**
     * Called when item is crafted/smelted. Used only by maps so far.
     */
    @Override
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer player)
    {
        super.onCreated(stack, worldIn, player);

        //Sets the name to 8BrickDMG's custom name if he crafts the giant hammer.
        if(!worldIn.isRemote && stack.getItem().equals(SHItems.hammerGiant) && player.getUniqueID().equals(Reference.UUIDs._8BRICKDMG))
        {
            NBTHelper.setString(stack, KEY_CUSTOM_NAME, "8brickdmg");
            NBTHelper.setString(stack, KEY_CUSTOM_FORMATTING, TextFormatting.LIGHT_PURPLE.toString());
        }
    }

    @Override
    public int getTextureColour()
    {
        return textureColour;
    }

    public String getDependantOreDic()
    {
        return dependantOreDic;
    }

    public ItemStack getDependantStack()
    {
        return dependantStack;
    }
}
