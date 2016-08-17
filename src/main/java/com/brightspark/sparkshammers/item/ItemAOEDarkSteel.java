package com.brightspark.sparkshammers.item;

import cofh.api.energy.IEnergyContainerItem;
import com.brightspark.sparkshammers.reference.ModMaterials;
import com.brightspark.sparkshammers.reference.Names;
import com.enderio.core.api.client.gui.IAdvancedTooltipProvider;
import com.enderio.core.common.transform.EnderCoreMethods;
import crazypants.enderio.item.PowerBarOverlayRenderHelper;
import crazypants.enderio.item.darksteel.DarkSteelRecipeManager;
import crazypants.enderio.item.darksteel.IDarkSteelItem;
import crazypants.enderio.item.darksteel.upgrade.EnergyUpgrade;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemAOEDarkSteel extends ItemAOE implements IDarkSteelItem, IEnergyContainerItem, EnderCoreMethods.IOverlayRenderAware, IAdvancedTooltipProvider
{
    public ItemAOEDarkSteel()
    {
        this(false);
    }

    public ItemAOEDarkSteel(boolean isExcavator)
    {
        super(isExcavator ? Names.ModItems.EXCAVATOR_DARKSTEEL : Names.ModItems.HAMMER_DARKSTEEL, ModMaterials.HAMMER_DARKSTEEL, isExcavator);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tabs, List<ItemStack> list)
    {
        ItemStack stack = new ItemStack(this);
        list.add(stack);

        stack = new ItemStack(this);
        EnergyUpgrade.EMPOWERED_FOUR.writeToItem(stack);
        EnergyUpgrade.setPowerFull(stack);
        list.add(stack);
    }

    //Uses the stored RF to absorb durability damage
    public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos, EntityLivingBase player)
    {
        absorbDamageWithEnergy(stack, isEffective(state) ? 1 : 2);
        return super.onBlockDestroyed(stack, world, state, pos, player);
    }

    public void setDamage(ItemStack stack, int newDamage)
    {
        int oldDamage = this.getDamage(stack);
        if(newDamage <= oldDamage)
            super.setDamage(stack, newDamage);
        else
        {
            int damage = newDamage - oldDamage;
            if(!this.absorbDamageWithEnergy(stack, damage * 750))
                super.setDamage(stack, newDamage);
        }
    }

    private boolean absorbDamageWithEnergy(ItemStack stack, int amount)
    {
        EnergyUpgrade eu = EnergyUpgrade.loadFromItem(stack);
        if(eu != null && eu.isAbsorbDamageWithPower(stack) && eu.getEnergy() > 0)
        {
            eu.extractEnergy(amount, false);
            eu.writeToItem(stack);
            return true;
        }
        return false;
    }

    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate)
    {
        return EnergyUpgrade.receiveEnergy(container, maxReceive, simulate);
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate)
    {
        return EnergyUpgrade.extractEnergy(container, maxExtract, simulate);
    }

    @Override
    public int getEnergyStored(ItemStack container)
    {
        return EnergyUpgrade.getEnergyStored(container);
    }

    @Override
    public int getMaxEnergyStored(ItemStack container)
    {
        return EnergyUpgrade.getMaxEnergyStored(container);
    }

    @Override
    public void addCommonEntries(ItemStack itemStack, EntityPlayer entityPlayer, List<String> list, boolean b)
    {
        DarkSteelRecipeManager.instance.addCommonTooltipEntries(itemStack, entityPlayer, list, b);
    }

    @Override
    public void addBasicEntries(ItemStack itemStack, EntityPlayer entityPlayer, List<String> list, boolean b)
    {
        DarkSteelRecipeManager.instance.addBasicTooltipEntries(itemStack, entityPlayer, list, b);
    }

    @Override
    public void addDetailedEntries(ItemStack itemStack, EntityPlayer entityPlayer, List<String> list, boolean b)
    {
        String energy = EnergyUpgrade.getStoredEnergyString(itemStack);
        if(energy != null)
            list.add(energy);
        DarkSteelRecipeManager.instance.addAdvancedTooltipEntries(itemStack, entityPlayer, list, b);
    }

    @Override
    public void renderItemOverlayIntoGUI(ItemStack stack, int xPos, int yPos)
    {
        PowerBarOverlayRenderHelper.instance_upgradeable.render(stack, xPos, yPos);
    }

    @Override
    public int getIngotsRequiredForFullRepair()
    {
        return 10;
    }

    @Override
    public String getItemName()
    {
        return getRegistryName().getResourcePath();
    }
}
