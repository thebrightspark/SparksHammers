package brightspark.sparkshammers.item;

import brightspark.sparkshammers.reference.Names;
import brightspark.sparkshammers.worldgen.WorldGenMjolnirShrine;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemDebug extends ItemResource
{
    public ItemDebug()
    {
        super(Names.Items.DEBUG);
    }

    /**
     * Called when a Block is right-clicked with this Item
     */
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(worldIn.isRemote)
            return EnumActionResult.SUCCESS;
        IBlockState state = worldIn.getBlockState(pos);
        Material mat = state.getMaterial();
        if(mat != Material.CRAFTED_SNOW && mat != Material.PLANTS && mat != Material.VINE)
            pos = pos.up();
        WorldGenMjolnirShrine.generateShrine(worldIn, pos);
        return EnumActionResult.SUCCESS;
    }
}
