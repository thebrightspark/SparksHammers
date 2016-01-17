package com.brightspark.sparkshammers.item;

import com.brightspark.sparkshammers.SHCreativeTab;
import com.brightspark.sparkshammers.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.world.BlockEvent;

public class ItemExcavator extends ItemSpade
{
    private int mineRadius = 1; //Radius around center block hit
    private int mineDepth = 0; //Depth (behind block)
    private boolean infiniteUse = false;
    //These are the material types which the hammer can mine in AOE:
    private Material[] materials = {Material.grass, Material.ground, Material.sand, Material.snow, Material.craftedSnow, Material.clay};

    public ItemExcavator(ToolMaterial mat)
    {
        super(mat);
        setCreativeTab(SHCreativeTab.SH_TAB);
    }

    public ItemExcavator(String name, ToolMaterial mat)
    {
        this(name, mat, null, false);
    }

    public ItemExcavator(String name, ToolMaterial mat, String modName)
    {
        this(name, mat, modName, false);
    }

    public ItemExcavator(String name, ToolMaterial mat, String modName, boolean hasInfiniteUse)
    {
        super(mat);
        setUnlocalizedName(name);
        setCreativeTab(SHCreativeTab.SH_TAB);
        if(modName == null)
            setTextureName(Reference.ITEM_TEXTURE_DIR + name);
        else
            setTextureName(Reference.ITEM_TEXTURE_DIR + modName + "/" + name);
        infiniteUse = hasInfiniteUse;
    }

    /**
     * If true, then the tool will take do damage from digging or attacking.
     * @param isInfinite
     */
    public void setInfinite(boolean isInfinite)
    {
        infiniteUse = isInfinite;
    }

    //Override method from ItemTool to stop durability loss
    public boolean hitEntity(ItemStack stack, EntityLivingBase player, EntityLivingBase entity)
    {
        if(infiniteUse) //Does not decrease durability if has infinite use
            return false;
        return super.hitEntity(stack, player, entity);
    }

    //Override method from ItemTool to stop durability loss
    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase player)
    {
        if(infiniteUse) //Does not decrease durability if has infinite use
            return false;
        return super.onBlockDestroyed(stack, world, block, x, y, z, player);
    }

    // <<<< From Tinkers Construct: HarvestTool >>>>
    public boolean isEffective (Material material)
    {
        for (Material m : materials)
            if (m == material)
                return true;

        return false;
    }

    // <<<< From Tinkers Construct: HarvestTool >>>>
    protected void breakExtraBlock(World world, int x, int y, int z, int sidehit, EntityPlayer playerEntity, int refX, int refY, int refZ)
    {
        // prevent calling that stuff for air blocks, could lead to unexpected behaviour since it fires events
        if(world.isAirBlock(x, y, z)) return;

        // what?
        if(! (playerEntity instanceof EntityPlayerMP)) return;
        EntityPlayerMP player = (EntityPlayerMP) playerEntity;

        // check if the block can be broken, since extra block breaks shouldn't instantly break stuff like obsidian
        // or precious ores you can't harvest while mining stone
        Block block = world.getBlock(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);

        // only effective materials
        if(! isEffective(block.getMaterial())) return;

        Block refBlock = world.getBlock(refX, refY, refZ);
        float refStrength = ForgeHooks.blockStrength(refBlock, player, world, refX, refY, refZ);
        float strength = ForgeHooks.blockStrength(block, player, world, x, y, z);

        // only harvestable blocks that aren't impossibly slow to harvest
        if(! ForgeHooks.canHarvestBlock(block, player, meta) || refStrength / strength > 10f) return;

        // send the blockbreak event
        BlockEvent.BreakEvent event = ForgeHooks.onBlockBreakEvent(world, player.theItemInWorldManager.getGameType(), player, x, y, z);
        if(event.isCanceled()) return;

        if(player.capabilities.isCreativeMode)
        {
            block.onBlockHarvested(world, x, y, z, meta, player);
            if(block.removedByPlayer(world, player, x, y, z, false))
                block.onBlockDestroyedByPlayer(world, x, y, z, meta);

            // send update to client
            if(! world.isRemote)
            {
                player.playerNetServerHandler.sendPacket(new S23PacketBlockChange(x, y, z, world));
            }
            return;
        }

        // callback to the tool the player uses. Called on both sides. This damages the tool n stuff.
        player.getCurrentEquippedItem().func_150999_a(world, block, x, y, z, player);

        // server sided handling
        if(! world.isRemote)
        {
            // serverside we reproduce ItemInWorldManager.tryHarvestBlock

            // ItemInWorldManager.removeBlock
            block.onBlockHarvested(world, x, y, z, meta, player);

            if(block.removedByPlayer(world, player, x, y, z, true)) // boolean is if block can be harvested, checked above
            {
                block.onBlockDestroyedByPlayer(world, x, y, z, meta);
                block.harvestBlock(world, player, x, y, z, meta);
                block.dropXpOnBlockBreak(world, x, y, z, event.getExpToDrop());
            }

            // always send block update to client
            player.playerNetServerHandler.sendPacket(new S23PacketBlockChange(x, y, z, world));
        }
        // client sided handling
        else
        {
            //PlayerControllerMP pcmp = Minecraft.getMinecraft().playerController;
            // clientside we do a "this clock has been clicked on long enough to be broken" call. This should not send any new packets
            // the code above, executed on the server, sends a block-updates that give us the correct state of the block we destroy.

            // following code can be found in PlayerControllerMP.onPlayerDestroyBlock
            world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(block) + (meta << 12));
            if(block.removedByPlayer(world, player, x, y, z, true))
            {
                block.onBlockDestroyedByPlayer(world, x, y, z, meta);
            }
            // callback to the tool
            ItemStack itemstack = player.getCurrentEquippedItem();
            if(itemstack != null)
            {
                itemstack.func_150999_a(world, block, x, y, z, player);

                if(itemstack.stackSize == 0)
                {
                    player.destroyCurrentEquippedItem();
                }
            }

            // send an update to the server, so we get an update back
            //if(PHConstruct.extraBlockUpdates)
            Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C07PacketPlayerDigging(2, x, y, z, Minecraft.getMinecraft().objectMouseOver.sideHit));
        }
    }

    // <<<< Also made with some help from Tinkers Construct >>>>
    public boolean onBlockStartBreak (ItemStack stack, int x, int y, int z, EntityPlayer player)
    {
        //Block being mined
        Block block = player.worldObj.getBlock(x, y, z);
        MovingObjectPosition mop = Common.raytraceFromEntity(player.worldObj, player, false, 4.5d);
        if(mop == null)
            return super.onBlockStartBreak(stack, x, y, z, player);
        int sideHit = mop.sideHit;

        //If not effective, then use normal pickaxe block breaking
        if(!isEffective(block.getMaterial()))
            return super.onBlockStartBreak(stack, x, y, z, player);

        int yDist = mineRadius;
        int xDist = mineRadius;
        int zDist = mineRadius;

        //Block destroyed, now for AOE
        switch (sideHit) {
            case 0:
            case 1:
                //Facing up/down
                yDist = mineDepth;
                break;
            case 2:
            case 3:
                //Facing along z axis
                zDist = mineDepth;
                break;
            case 4:
            case 5:
                //Facing along x axis
                xDist = mineDepth;
                break;
        }

        for (int xPos = x - xDist; xPos <= x + xDist; xPos++)
            for (int yPos = y - yDist; yPos <= y + yDist; yPos++)
                for (int zPos = z - zDist; zPos <= z + zDist; zPos++) {
                    // don't break the originally already broken block, duh
                    if (xPos == x && yPos == y && zPos == z)
                        continue;

                    if(!super.onBlockStartBreak(stack, xPos, yPos, zPos, player))
                        breakExtraBlock(player.worldObj, xPos, yPos, zPos, sideHit, player, x,y,z);
                }

        return super.onBlockStartBreak(stack, x, y, z, player);
    }
}
