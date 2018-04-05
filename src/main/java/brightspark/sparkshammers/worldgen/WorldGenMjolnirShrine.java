package brightspark.sparkshammers.worldgen;

import brightspark.sparkshammers.init.SHBlocks;
import brightspark.sparkshammers.SHConfig;
import brightspark.sparkshammers.util.LogHelper;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenMjolnirShrine implements IWorldGenerator
{
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        //ID for the overworld == 0
        if(world.provider.getDimension() == 0)
        {
            //Get the surface position with a random coord in the given chunk
            BlockPos pos = world.getTopSolidOrLiquidBlock(new BlockPos(chunkX * 16 + random.nextInt(5), 1, chunkZ * 16 + random.nextInt(5)));
            int posY = isValidSpawn(world, pos);

            if(posY > 0 && random.nextInt(SHConfig.mjolnirShrineRarity) == 0)
            {
                //Clear the trees around the area if there are any
                clearTrees(world, pos.add(5, 0, 5), 10);

                //Generate the shrine
                generateShrine(world, pos);

                if(SHConfig.mjolnirShrineDebug)
                    LogHelper.info("Mjolnir Shrine generated at: " + pos.add(5, 0, 5).toString());
            }
        }
    }

    /**
     * Remove Wood
     * Removes all wood blocks at the specified x, z coordinates.  Used to
     * create clearings for the shrines.
     */
    private void removeWood(World world, int x, int z)
    {
        int y = world.getActualHeight() - 1;
        BlockPos pos = new BlockPos(x, y, z);
        Block posBlock = world.getBlockState(pos).getBlock();
        while(posBlock == Blocks.LOG || posBlock == Blocks.LOG2 || posBlock == Blocks.LEAVES || posBlock == Blocks.LEAVES2)
        {
            if(posBlock == Blocks.LOG || posBlock == Blocks.LOG2)
            {
                world.setBlockToAir(pos);
            }
            --y;
        }
    }

    /**
     * Clear Trees
     * Clears out all trees in the specified radius.  This creates a
     * clearing which we can then place our shrine.
     */
    private void clearTrees(World world, BlockPos pos, int radius)
    {
        int radiusSquared = radius * radius;
        for(int i = radius * -1; i <= radius; ++i)
        {
            for(int j = radius * -1; j <= radius; ++j)
            {
                if(((i * i) + (j * j)) <= radiusSquared)
                {
                    removeWood(world, pos.getX() + i, pos.getZ() + j);
                }
            }
        }
    }

    /**
     * Checks if the given coord will be a valid spawn location for the shrine
     * Returns -1 if invalid spawn location, else the correct Y position to spawn at.
     */
    private int isValidSpawn(World world, BlockPos pos)
    {
        //If the position isn't high enough, then don't spawn it.
        //If the biome's minimum height is less than 0 -> rules out any sort of water based biome
        if(pos.getY() < SHConfig.mjolnirShrineMinY || world.getBiome(pos).getBaseHeight() < 0.0F)
            return -1;

        //Checks to see if the ground is 'flat enough'
        BlockPos[] positions = new BlockPos[]{pos, pos.add(10,0,0), pos.add(0,0,10), pos.add(10,0,10)};
        int minY = Integer.MAX_VALUE;

        //Checks each coord to make sure the ground doesn't vary by more than 3 blocks in height.
        for(BlockPos p : positions)
        {
            int i = 0;
            IBlockState state = world.getBlockState(p);
            Block block = state.getBlock();
            while(block.isAir(state, world, pos) || block.isLeaves(state, world, pos) || block.isFoliage(world, pos) || block == Blocks.LOG || block == Blocks.LOG2)
            {
                i++;
                p = p.down();
                block = world.getBlockState(p).getBlock();
                if(i > 3)
                    return -1;
                if(p.getY() < minY)
                    minY = p.getY();
            }
        }

        return minY;
    }

    /**
     * Generates the Shrine structure at the given position.
     */
    public static void generateShrine(World world, BlockPos pos)
    {
        //Space around main center part
        for(int x = 0; x <= 10; x++)
        {
            for(int y = 1; y <= 6; y++)
            {
                for(int z = 0; z <= 10; z++)
                {
                    if((x >= 2 && x <= 8) && (z >= 2 && z <= 8)) continue;
                    world.setBlockToAir(pos.add(x, y, z));
                }
            }
        }

        //Y == 0
        world.setBlockToAir(pos);
        world.setBlockToAir(pos.add(1, 0, 0));
        world.setBlockToAir(pos.add(2, 0, 0));
        world.setBlockToAir(pos.add(3, 0, 0));
        world.setBlockState(pos.add(4, 0, 0), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_RIGHT));
        world.setBlockState(pos.add(5, 0, 0), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
        world.setBlockState(pos.add(6, 0, 0), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_LEFT));
        world.setBlockToAir(pos.add(7, 0, 0));
        world.setBlockToAir(pos.add(8, 0, 0));
        world.setBlockToAir(pos.add(9, 0, 0));
        world.setBlockToAir(pos.add(10, 0, 0));

        world.setBlockToAir(pos.add(0, 0, 1));
        world.setBlockToAir(pos.add(1, 0, 1));
        world.setBlockState(pos.add(2, 0, 1), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_LEFT));
        world.setBlockState(pos.add(3, 0, 1), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
        world.setBlockState(pos.add(4, 0, 1), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.INNER_RIGHT));
        world.setBlockState(pos.add(5, 0, 1), Blocks.QUARTZ_BLOCK.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.LINES_Z));
        world.setBlockState(pos.add(6, 0, 1), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.INNER_LEFT));
        world.setBlockState(pos.add(7, 0, 1), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
        world.setBlockState(pos.add(8, 0, 1), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_RIGHT));
        world.setBlockToAir(pos.add(9, 0, 1));
        world.setBlockToAir(pos.add(10, 0, 1));

        world.setBlockToAir(pos.add(0, 0, 2));
        world.setBlockState(pos.add(1, 0, 2), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_RIGHT));
        world.setBlockState(pos.add(2, 0, 2), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.INNER_RIGHT));
        world.setBlockState(pos.add(3, 0, 2), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(4, 0, 2), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(5, 0, 2), Blocks.QUARTZ_BLOCK.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.LINES_Z));
        world.setBlockState(pos.add(6, 0, 2), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(7, 0, 2), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(8, 0, 2), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.INNER_LEFT));
        world.setBlockState(pos.add(9, 0, 2), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_LEFT));
        world.setBlockToAir(pos.add(10, 0, 2));

        world.setBlockToAir(pos.add(0, 0, 3));
        world.setBlockState(pos.add(1, 0, 3), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
        world.setBlockState(pos.add(2, 0, 3), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(3, 0, 3), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(4, 0, 3), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(5, 0, 3), Blocks.QUARTZ_BLOCK.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.LINES_Z));
        world.setBlockState(pos.add(6, 0, 3), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(7, 0, 3), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(8, 0, 3), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(9, 0, 3), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
        world.setBlockToAir(pos.add(10, 0, 3));

        world.setBlockState(pos.add(0, 0, 4), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_RIGHT));
        world.setBlockState(pos.add(1, 0, 4), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.INNER_RIGHT));
        world.setBlockState(pos.add(2, 0, 4), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(3, 0, 4), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(4, 0, 4), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(5, 0, 4), Blocks.GLOWSTONE.getDefaultState());
        world.setBlockState(pos.add(6, 0, 4), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(7, 0, 4), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(8, 0, 4), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(9, 0, 4), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.INNER_LEFT));
        world.setBlockState(pos.add(10, 0, 4), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_LEFT));

        world.setBlockState(pos.add(0, 0, 5), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
        world.setBlockState(pos.add(1, 0, 5), Blocks.QUARTZ_BLOCK.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.LINES_X));
        world.setBlockState(pos.add(2, 0, 5), Blocks.QUARTZ_BLOCK.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.LINES_X));
        world.setBlockState(pos.add(3, 0, 5), Blocks.QUARTZ_BLOCK.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.LINES_X));
        world.setBlockState(pos.add(4, 0, 5), Blocks.GLOWSTONE.getDefaultState());
        world.setBlockState(pos.add(5, 0, 5), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(6, 0, 5), Blocks.GLOWSTONE.getDefaultState());
        world.setBlockState(pos.add(7, 0, 5), Blocks.QUARTZ_BLOCK.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.LINES_X));
        world.setBlockState(pos.add(8, 0, 5), Blocks.QUARTZ_BLOCK.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.LINES_X));
        world.setBlockState(pos.add(9, 0, 5), Blocks.QUARTZ_BLOCK.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.LINES_X));
        world.setBlockState(pos.add(10, 0, 5), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));

        world.setBlockState(pos.add(0, 0, 6), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_LEFT));
        world.setBlockState(pos.add(1, 0, 6), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.INNER_LEFT));
        world.setBlockState(pos.add(2, 0, 6), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(3, 0, 6), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(4, 0, 6), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(5, 0, 6), Blocks.GLOWSTONE.getDefaultState());
        world.setBlockState(pos.add(6, 0, 6), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(7, 0, 6), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(8, 0, 6), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(9, 0, 6), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.INNER_RIGHT));
        world.setBlockState(pos.add(10, 0, 6), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_RIGHT));

        world.setBlockToAir(pos.add(0, 0, 7));
        world.setBlockState(pos.add(1, 0, 7), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
        world.setBlockState(pos.add(2, 0, 7), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(3, 0, 7), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(4, 0, 7), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(5, 0, 7), Blocks.QUARTZ_BLOCK.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.LINES_Z));
        world.setBlockState(pos.add(6, 0, 7), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(7, 0, 7), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(8, 0, 7), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(9, 0, 7), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
        world.setBlockToAir(pos.add(10, 0, 7));

        world.setBlockToAir(pos.add(0, 0, 8));
        world.setBlockState(pos.add(1, 0, 8), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_LEFT));
        world.setBlockState(pos.add(2, 0, 8), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.INNER_LEFT));
        world.setBlockState(pos.add(3, 0, 8), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(4, 0, 8), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(5, 0, 8), Blocks.QUARTZ_BLOCK.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.LINES_Z));
        world.setBlockState(pos.add(6, 0, 8), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(7, 0, 8), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(8, 0, 8), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.INNER_RIGHT));
        world.setBlockState(pos.add(9, 0, 8), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_RIGHT));
        world.setBlockToAir(pos.add(10, 0, 8));

        world.setBlockToAir(pos.add(0, 0, 9));
        world.setBlockToAir(pos.add(1, 0, 9));
        world.setBlockState(pos.add(2, 0, 9), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_RIGHT));
        world.setBlockState(pos.add(3, 0, 9), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
        world.setBlockState(pos.add(4, 0, 9), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.INNER_LEFT));
        world.setBlockState(pos.add(5, 0, 9), Blocks.QUARTZ_BLOCK.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.LINES_Z));
        world.setBlockState(pos.add(6, 0, 9), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.INNER_RIGHT));
        world.setBlockState(pos.add(7, 0, 9), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
        world.setBlockState(pos.add(8, 0, 9), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_LEFT));
        world.setBlockToAir(pos.add(9, 0, 9));
        world.setBlockToAir(pos.add(10, 0, 9));

        world.setBlockToAir(pos.add(0, 0, 10));
        world.setBlockToAir(pos.add(1, 0, 10));
        world.setBlockToAir(pos.add(2, 0, 10));
        world.setBlockToAir(pos.add(3, 0, 10));
        world.setBlockState(pos.add(4, 0, 10), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_LEFT));
        world.setBlockState(pos.add(5, 0, 10), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
        world.setBlockState(pos.add(6, 0, 10), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_RIGHT));
        world.setBlockToAir(pos.add(7, 0, 10));
        world.setBlockToAir(pos.add(8, 0, 10));
        world.setBlockToAir(pos.add(9, 0, 10));
        world.setBlockToAir(pos.add(10, 0, 10));

        //Blocks for Z == 2 and Z == 8
        for(int y = 1; y <= 6; y++)
        {
            for(int x = 2; x <= 8; x++)
            {
                if(y == 4 && (x == 3 || x == 7))
                {
                    world.setBlockState(pos.add(x, y, 2), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP));
                    world.setBlockState(pos.add(x, y, 8), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP));
                }
                else
                {
                    world.setBlockToAir(pos.add(x, y, 2));
                    world.setBlockToAir(pos.add(x, y, 8));
                }
            }
        }

        //Center Walls
        for(int y2 = 1; y2 <= 3; y2++)
        {
            for(int x = 2; x <= 8; x++)
            {
                world.setBlockToAir(pos.add(x, y2, 2));
                world.setBlockToAir(pos.add(x, y2, 8));
                world.setBlockToAir(pos.add(2, y2, x));
                world.setBlockToAir(pos.add(8, y2, x));
            }
            world.setBlockState(pos.add(3, y2, 3), Blocks.QUARTZ_BLOCK.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.LINES_Y));
            world.setBlockState(pos.add(4, y2, 3), Blocks.STAINED_GLASS_PANE.getDefaultState().withProperty(BlockStainedGlassPane.COLOR, EnumDyeColor.WHITE).withProperty(BlockPane.EAST, true).withProperty(BlockPane.WEST, true));
            world.setBlockState(pos.add(5, y2, 3), Blocks.STAINED_GLASS_PANE.getDefaultState().withProperty(BlockStainedGlassPane.COLOR, EnumDyeColor.YELLOW).withProperty(BlockPane.EAST, true).withProperty(BlockPane.WEST, true));
            world.setBlockState(pos.add(6, y2, 3), Blocks.STAINED_GLASS_PANE.getDefaultState().withProperty(BlockStainedGlassPane.COLOR, EnumDyeColor.WHITE).withProperty(BlockPane.EAST, true).withProperty(BlockPane.WEST, true));
            world.setBlockState(pos.add(7, y2, 3), Blocks.QUARTZ_BLOCK.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.LINES_Y));

            world.setBlockState(pos.add(3, y2, 4), Blocks.STAINED_GLASS_PANE.getDefaultState().withProperty(BlockStainedGlassPane.COLOR, EnumDyeColor.WHITE).withProperty(BlockPane.NORTH, true).withProperty(BlockPane.SOUTH, true));
            world.setBlockState(pos.add(7, y2, 4), Blocks.STAINED_GLASS_PANE.getDefaultState().withProperty(BlockStainedGlassPane.COLOR, EnumDyeColor.WHITE).withProperty(BlockPane.NORTH, true).withProperty(BlockPane.SOUTH, true));

            world.setBlockState(pos.add(3, y2, 5), Blocks.STAINED_GLASS_PANE.getDefaultState().withProperty(BlockStainedGlassPane.COLOR, EnumDyeColor.YELLOW).withProperty(BlockPane.NORTH, true).withProperty(BlockPane.SOUTH, true));
            world.setBlockState(pos.add(7, y2, 5), Blocks.STAINED_GLASS_PANE.getDefaultState().withProperty(BlockStainedGlassPane.COLOR, EnumDyeColor.YELLOW).withProperty(BlockPane.NORTH, true).withProperty(BlockPane.SOUTH, true));

            world.setBlockState(pos.add(3, y2, 6), Blocks.STAINED_GLASS_PANE.getDefaultState().withProperty(BlockStainedGlassPane.COLOR, EnumDyeColor.WHITE).withProperty(BlockPane.NORTH, true).withProperty(BlockPane.SOUTH, true));
            world.setBlockState(pos.add(7, y2, 6), Blocks.STAINED_GLASS_PANE.getDefaultState().withProperty(BlockStainedGlassPane.COLOR, EnumDyeColor.WHITE).withProperty(BlockPane.NORTH, true).withProperty(BlockPane.SOUTH, true));

            world.setBlockState(pos.add(3, y2, 7), Blocks.QUARTZ_BLOCK.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.LINES_Y));
            world.setBlockState(pos.add(4, y2, 7), Blocks.STAINED_GLASS_PANE.getDefaultState().withProperty(BlockStainedGlassPane.COLOR, EnumDyeColor.WHITE).withProperty(BlockPane.EAST, true).withProperty(BlockPane.WEST, true));
            world.setBlockState(pos.add(5, y2, 7), Blocks.STAINED_GLASS_PANE.getDefaultState().withProperty(BlockStainedGlassPane.COLOR, EnumDyeColor.YELLOW).withProperty(BlockPane.EAST, true).withProperty(BlockPane.WEST, true));
            world.setBlockState(pos.add(6, y2, 7), Blocks.STAINED_GLASS_PANE.getDefaultState().withProperty(BlockStainedGlassPane.COLOR, EnumDyeColor.WHITE).withProperty(BlockPane.EAST, true).withProperty(BlockPane.WEST, true));
            world.setBlockState(pos.add(7, y2, 7), Blocks.QUARTZ_BLOCK.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.LINES_Y));
        }

        //Y == 1
        world.setBlockState(pos.add(4, 1, 4), Blocks.CARPET.getDefaultState().withProperty(BlockCarpet.COLOR, EnumDyeColor.YELLOW));
        world.setBlockState(pos.add(5, 1, 4), Blocks.CARPET.getDefaultState().withProperty(BlockCarpet.COLOR, EnumDyeColor.YELLOW));
        world.setBlockState(pos.add(6, 1, 4), Blocks.CARPET.getDefaultState().withProperty(BlockCarpet.COLOR, EnumDyeColor.YELLOW));

        world.setBlockState(pos.add(4, 1, 5), Blocks.CARPET.getDefaultState().withProperty(BlockCarpet.COLOR, EnumDyeColor.YELLOW));
        world.setBlockState(pos.add(5, 1, 5), Blocks.QUARTZ_BLOCK.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.CHISELED));
        world.setBlockState(pos.add(6, 1, 5), Blocks.CARPET.getDefaultState().withProperty(BlockCarpet.COLOR, EnumDyeColor.YELLOW));

        world.setBlockState(pos.add(4, 1, 6), Blocks.CARPET.getDefaultState().withProperty(BlockCarpet.COLOR, EnumDyeColor.YELLOW));
        world.setBlockState(pos.add(5, 1, 6), Blocks.CARPET.getDefaultState().withProperty(BlockCarpet.COLOR, EnumDyeColor.YELLOW));
        world.setBlockState(pos.add(6, 1, 6), Blocks.CARPET.getDefaultState().withProperty(BlockCarpet.COLOR, EnumDyeColor.YELLOW));

        //Y == 2-3
        for(int y3 = 2; y3 <= 3; y3++)
        {
            for(int x = 4; x <= 6; x++)
            {
                for(int z = 4; z <= 6; z++)
                {
                    if(x == 5 && y3 == 2 && z == 5)
                        world.setBlockState(pos.add(x, y3, z), SHBlocks.blockHammer.getDefaultState());
                    else world.setBlockToAir(pos.add(x, y3, z));
                }
            }
        }

        //Y == 4
        world.setBlockState(pos.add(2, 4, 3), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP));
        world.setBlockState(pos.add(3, 4, 3), Blocks.NETHERRACK.getDefaultState());
        world.setBlockState(pos.add(4, 4, 3), Blocks.QUARTZ_BLOCK.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.LINES_X));
        world.setBlockState(pos.add(5, 4, 3), Blocks.QUARTZ_BLOCK.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.LINES_X));
        world.setBlockState(pos.add(6, 4, 3), Blocks.QUARTZ_BLOCK.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.LINES_X));
        world.setBlockState(pos.add(7, 4, 3), Blocks.NETHERRACK.getDefaultState());
        world.setBlockState(pos.add(8, 4, 3), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP));

        world.setBlockToAir(pos.add(2, 4, 4));
        world.setBlockState(pos.add(3, 4, 4), Blocks.QUARTZ_BLOCK.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.LINES_Z));
        world.setBlockState(pos.add(4, 4, 4), Blocks.STONE_SLAB.getDefaultState().withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.QUARTZ).withProperty(BlockStoneSlab.HALF, BlockSlab.EnumBlockHalf.TOP));
        world.setBlockState(pos.add(5, 4, 4), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP));
        world.setBlockState(pos.add(6, 4, 4), Blocks.STONE_SLAB.getDefaultState().withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.QUARTZ).withProperty(BlockStoneSlab.HALF, BlockSlab.EnumBlockHalf.TOP));
        world.setBlockState(pos.add(7, 4, 4), Blocks.QUARTZ_BLOCK.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.LINES_Z));
        world.setBlockToAir(pos.add(8, 4, 4));

        world.setBlockToAir(pos.add(2, 4, 5));
        world.setBlockState(pos.add(3, 4, 5), Blocks.QUARTZ_BLOCK.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.LINES_Z));
        world.setBlockState(pos.add(4, 4, 5), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP));
        world.setBlockToAir(pos.add(5, 4, 5));
        world.setBlockState(pos.add(6, 4, 5), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP));
        world.setBlockState(pos.add(7, 4, 5), Blocks.QUARTZ_BLOCK.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.LINES_Z));
        world.setBlockToAir(pos.add(8, 4, 5));

        world.setBlockToAir(pos.add(2, 4, 6));
        world.setBlockState(pos.add(3, 4, 6), Blocks.QUARTZ_BLOCK.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.LINES_Z));
        world.setBlockState(pos.add(4, 4, 6), Blocks.STONE_SLAB.getDefaultState().withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.QUARTZ).withProperty(BlockStoneSlab.HALF, BlockSlab.EnumBlockHalf.TOP));
        world.setBlockState(pos.add(5, 4, 6), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP));
        world.setBlockState(pos.add(6, 4, 6), Blocks.STONE_SLAB.getDefaultState().withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.QUARTZ).withProperty(BlockStoneSlab.HALF, BlockSlab.EnumBlockHalf.TOP));
        world.setBlockState(pos.add(7, 4, 6), Blocks.QUARTZ_BLOCK.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.LINES_Z));
        world.setBlockToAir(pos.add(8, 4, 6));

        world.setBlockState(pos.add(2, 4, 7), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP));
        world.setBlockState(pos.add(3, 4, 7), Blocks.NETHERRACK.getDefaultState());
        world.setBlockState(pos.add(4, 4, 7), Blocks.QUARTZ_BLOCK.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.LINES_X));
        world.setBlockState(pos.add(5, 4, 7), Blocks.QUARTZ_BLOCK.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.LINES_X));
        world.setBlockState(pos.add(6, 4, 7), Blocks.QUARTZ_BLOCK.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.LINES_X));
        world.setBlockState(pos.add(7, 4, 7), Blocks.NETHERRACK.getDefaultState());
        world.setBlockState(pos.add(8, 4, 7), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP));

        //Y == 5
        world.setBlockToAir(pos.add(2, 5, 3));
        world.setBlockState(pos.add(3, 5, 3), Blocks.FIRE.getDefaultState());
        world.setBlockState(pos.add(4, 5, 3), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
        world.setBlockState(pos.add(5, 5, 3), Blocks.GOLD_BLOCK.getDefaultState());
        world.setBlockState(pos.add(6, 5, 3), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
        world.setBlockState(pos.add(7, 5, 3), Blocks.FIRE.getDefaultState());
        world.setBlockToAir(pos.add(8, 5, 3));

        world.setBlockToAir(pos.add(2, 5, 4));
        world.setBlockState(pos.add(3, 5, 4), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
        world.setBlockState(pos.add(4, 5, 4), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_LEFT));
        world.setBlockState(pos.add(5, 5, 4), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(6, 5, 4), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_RIGHT));
        world.setBlockState(pos.add(7, 5, 4), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
        world.setBlockToAir(pos.add(8, 5, 4));

        world.setBlockToAir(pos.add(2, 5, 5));
        world.setBlockState(pos.add(3, 5, 5), Blocks.GOLD_BLOCK.getDefaultState());
        world.setBlockState(pos.add(4, 5, 5), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(5, 5, 5), Blocks.QUARTZ_BLOCK.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.CHISELED));
        world.setBlockState(pos.add(6, 5, 5), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(7, 5, 5), Blocks.GOLD_BLOCK.getDefaultState());
        world.setBlockToAir(pos.add(8, 5, 5));

        world.setBlockToAir(pos.add(2, 5, 6));
        world.setBlockState(pos.add(3, 5, 6), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
        world.setBlockState(pos.add(4, 5, 6), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_RIGHT));
        world.setBlockState(pos.add(5, 5, 6), Blocks.QUARTZ_BLOCK.getDefaultState());
        world.setBlockState(pos.add(6, 5, 6), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH).withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_LEFT));
        world.setBlockState(pos.add(7, 5, 6), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
        world.setBlockToAir(pos.add(8, 5, 6));

        world.setBlockToAir(pos.add(2, 5, 7));
        world.setBlockState(pos.add(3, 5, 7), Blocks.FIRE.getDefaultState());
        world.setBlockState(pos.add(4, 5, 7), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
        world.setBlockState(pos.add(5, 5, 7), Blocks.GOLD_BLOCK.getDefaultState());
        world.setBlockState(pos.add(6, 5, 7), Blocks.QUARTZ_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
        world.setBlockState(pos.add(7, 5, 7), Blocks.FIRE.getDefaultState());
        world.setBlockToAir(pos.add(8, 5, 7));

        //Y == 6
        for(int x = 2; x <= 8; x++)
        {
            for(int z = 2; z <= 8; z++)
            {
                if(x >= 3 && x <= 7 && z >= 3 && z <= 7 && (x == 5 || z == 5))
                    world.setBlockState(pos.add(x, 6, z), Blocks.STONE_SLAB.getDefaultState().withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.QUARTZ));
                else world.setBlockToAir(pos.add(x, 6, z));
            }
        }
    }
}
