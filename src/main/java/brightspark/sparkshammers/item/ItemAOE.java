package brightspark.sparkshammers.item;

import brightspark.sparkshammers.Reference;
import brightspark.sparkshammers.SHConfig;
import brightspark.sparkshammers.SparksHammers;
import brightspark.sparkshammers.customTools.Tool;
import brightspark.sparkshammers.util.CommonUtils;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
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
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Set;

public class ItemAOE extends ItemTool
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
    private boolean infiniteUse = false;
    private boolean shiftRotating = false;

    private String dependantOreDic;
    private ItemStack dependantStack;
    protected String localName;
    //Used for the colour tint of the head of the tool texture
    protected int textureColour;

    public ItemAOE(Tool tool, boolean isExcavator)
    {
        super(isExcavator ? 0f : 2f, isExcavator ? -3f : -3.2f, tool.material, isExcavator ? ShovelBlocks : PickaxeBlocks);
        String name = tool.getToolName(isExcavator);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(SparksHammers.SH_TAB);
        setHarvestLevel(isExcavator ? "shovel" : "pickaxe", tool.material.getHarvestLevel());
        this.isExcavator = isExcavator;
        localName = tool.localName;
        textureColour = tool.toolColour;
        dependantOreDic = tool.dependantOreDic;
        dependantStack = tool.dependantStack;
    }

    /**
     * Gets just the material part of the registry name
     * e.g. "hammer_gold" returns "gold"
     */
    public String getMaterialName()
    {
        String regPath = getRegistryName().getResourcePath();
        int i = regPath.indexOf('_');
        return regPath.substring(i + 1);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        return localName != null ? localName + " " + CommonUtils.capitaliseFirstLetter(isExcavator ? Reference.Items.EXCAVATOR : Reference.Items.HAMMER) : super.getItemStackDisplayName(stack);
    }

    /**
     * If true, then the tool will take no damage from digging or attacking.
     */
    public void setInfinite(boolean isInfinite)
    {
        infiniteUse = isInfinite;
    }

    //Don't show the item in creative tabs if it can't be created
    @Nullable
    @Override
    public CreativeTabs getCreativeTab()
    {
        return dependantOreDic != null || dependantStack != null ? super.getCreativeTab() : null;
    }

    //Override method from ItemTool to stop durability loss
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase entityHit, EntityLivingBase player)
    {
        //Does not decrease durability if has infinite use
        return !infiniteUse && super.hitEntity(stack, entityHit, player);
    }

    //Overriding this just to make it public
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

    @Override
    public boolean canHarvestBlock(IBlockState state, ItemStack stack)
    {
        String tool = state.getBlock().getHarvestTool(state);
        if(state.getMaterial().isToolNotRequired() || tool == null) return true;
        return getHarvestLevel(stack, tool, null, state) >= state.getBlock().getHarvestLevel(state);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state)
    {
        return isEffective(stack, state) ? efficiency : 1F;
    }

    public boolean isEffective(ItemStack stack, IBlockState state)
    {
        for (String type : getToolClasses(stack))
            if(state.getBlock().isToolEffective(type, state))
                return true;

        return isExcavator ?
                ShovelMats.contains(state.getMaterial()) || ShovelBlocks.contains(state.getBlock()) :
                PickaxeMats.contains(state.getMaterial()) || PickaxeBlocks.contains(state.getBlock());
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
        BlockPos[] positions = CommonUtils.getBreakArea(stack, pos, ray.sideHit, player);
        BlockPos start = positions[0];
        BlockPos end = positions[1];

        //LogHelper.info("Breaking blocks from " + start.toString() + " to " + end.toString());
        breakArea(stack, player, pos, start, end);

        return super.onBlockStartBreak(stack, pos, player);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(SHConfig.rightClickPlacesTorches && !world.isRemote && !player.isSneaking() && player.inventory.hasItemStack(SparksHammers.TORCH_STACK))
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
        //TODO: Make grass paths with excavators?
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

    public int getMineWidth(ItemStack stack)
    {
        return mineWidth;
    }

    public int getMineHeight(ItemStack stack)
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
     * Returns the hexadecimal colour to tint the grey-scale texture with.
     * Return -1 for this item to be excluded from the item colour handler.
     */
    public int getTextureColour()
    {
        return textureColour;
    }

    public void nullDependants()
    {
        dependantOreDic = null;
        dependantStack = null;
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
