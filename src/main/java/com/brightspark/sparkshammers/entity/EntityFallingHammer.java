package com.brightspark.sparkshammers.entity;

import com.brightspark.sparkshammers.SparksHammers;
import com.brightspark.sparkshammers.block.BlockHammer;
import com.brightspark.sparkshammers.tileentity.TileHammer;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

public class EntityFallingHammer extends EntityFallingBlock
{
    private IBlockState fallTile;
    private int fallHurtMax = 80;
    private float fallHurtAmount = 2.0F;

    private UUID playerUUID;

    public EntityFallingHammer(World worldIn, double x, double y, double z, IBlockState state, UUID playerUUID)
    {
        super(worldIn, x, y, z, state);
        fallTile = state;
        this.playerUUID = playerUUID;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        IBlockState state = this.fallTile;
        Block block = state.getBlock();

        if (state.getMaterial() == Material.AIR)
            this.setDead();
        else
        {
            this.prevPosX = this.posX;
            this.prevPosY = this.posY;
            this.prevPosZ = this.posZ;

            if (this.fallTime++ == 0)
            {
                BlockPos blockpos = new BlockPos(this);

                if (this.world.getBlockState(blockpos).getBlock() == block)
                {
                    this.world.setBlockToAir(blockpos);
                }
                else if (!this.world.isRemote)
                {
                    this.setDead();
                    return;
                }
            }

            this.motionY -= 0.03999999910593033D;
            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.9800000190734863D;
            this.motionY *= 0.9800000190734863D;
            this.motionZ *= 0.9800000190734863D;

            if (!this.world.isRemote)
            {
                BlockPos blockpos1 = new BlockPos(this);

                if (this.onGround)
                {
                    this.motionX *= 0.699999988079071D;
                    this.motionZ *= 0.699999988079071D;
                    this.motionY *= -0.5D;
                    this.setDead();

                    //func_190527_a -> canBlockBePlaced
                    if (this.world.mayPlace(block, blockpos1, true, EnumFacing.UP, null) && !BlockFalling.canFallThrough(this.world.getBlockState(blockpos1.down())) && this.world.setBlockState(blockpos1, this.fallTile))
                    {
                        if (block instanceof BlockHammer)
                            ((BlockHammer)block).onEndFalling(this.world, blockpos1);

                        //Set the owner to the hammer block from this entity
                        TileHammer tileentity = (TileHammer) this.world.getTileEntity(blockpos1);
                        if(tileentity != null)
                            tileentity.setOwner(playerUUID);

                        if (this.tileEntityData != null)
                        {
                            if (tileentity != null)
                            {
                                NBTTagCompound nbttagcompound = new NBTTagCompound();
                                tileentity.writeToNBT(nbttagcompound);

                                for (String s : this.tileEntityData.getKeySet())
                                {
                                    NBTBase nbtbase = this.tileEntityData.getTag(s);

                                    if (!s.equals("x") && !s.equals("y") && !s.equals("z"))
                                    {
                                        nbttagcompound.setTag(s, nbtbase.copy());
                                    }

                                }

                                tileentity.readFromNBT(nbttagcompound);
                                tileentity.markDirty();
                            }
                        }
                    }
                    else if (this.shouldDropItem && this.world.getGameRules().getBoolean("doEntityDrops"))
                        this.entityDropItem(new ItemStack(block, 1, block.damageDropped(this.fallTile)), 0.0F);
                }
                else if (this.fallTime > 100 && !this.world.isRemote && (blockpos1.getY() < 1 || blockpos1.getY() > 256) || this.fallTime > 600)
                {
                    if (this.shouldDropItem && this.world.getGameRules().getBoolean("doEntityDrops"))
                        this.entityDropItem(new ItemStack(block, 1, block.damageDropped(this.fallTile)), 0.0F);
                    this.setDead();
                }
            }
        }
    }

    public void fall(float distance, float damageMultiplier)
    {
        int i = MathHelper.ceil(distance - 1.0F);

        if (i > 0)
        {
            List<Entity> list = Lists.newArrayList(this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox()));
            DamageSource damagesource = SparksHammers.fallingHammer;

            for (Entity entity : list)
            {
                entity.attackEntityFrom(damagesource, (float)Math.min(MathHelper.floor((float)i * this.fallHurtAmount), this.fallHurtMax));
            }
        }
        if(i > 10)
        {
            //TODO: Create hammer falling explosion
            //Create explosion (no block damage) which scales with height
        }
    }

    protected void writeEntityToNBT(NBTTagCompound tag)
    {
        tag.setLong("uuidLeastSig", playerUUID.getLeastSignificantBits());
        tag.setLong("uuidMostSig", playerUUID.getMostSignificantBits());
        super.writeEntityToNBT(tag);
    }

    protected void readEntityFromNBT(NBTTagCompound tag)
    {
        if(tag.hasKey("uuidMostSig"))
            playerUUID = new UUID(tag.getLong("uuidMostSig"), tag.getLong("uuidLeastSig"));
        else
            playerUUID = null;
        super.readEntityFromNBT(tag);
    }
}
