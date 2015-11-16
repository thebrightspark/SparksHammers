package com.brightspark.sparkshammers.item;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemHammerInfinite extends ItemHammer
{
    public ItemHammerInfinite(String name, ToolMaterial mat, String modName)
    {
        super(name, mat, modName);
    }

    //Override method from ItemTool to stop durability loss
    public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_)
    {
        return false;
    }

    //Override method from ItemTool to stop durability loss
    public boolean onBlockDestroyed(ItemStack p_150894_1_, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_)
    {
        return false;
    }
}
