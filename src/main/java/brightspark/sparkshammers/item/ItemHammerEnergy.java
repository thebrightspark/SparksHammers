package brightspark.sparkshammers.item;

import brightspark.sparkshammers.SHConfig;
import brightspark.sparkshammers.customTools.Tool;
import brightspark.sparkshammers.energy.SHEnergyStorage;
import brightspark.sparkshammers.item.upgrade.DigSize;
import brightspark.sparkshammers.item.upgrade.EnumUpgrades;
import brightspark.sparkshammers.item.upgrade.Upgrade;
import brightspark.sparkshammers.util.NBTHelper;
import cofh.redstoneflux.api.IEnergyContainerItem;
import com.google.common.collect.Multimap;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@Optional.Interface(modid = "redstoneflux", iface = "cofh.redstoneflux.api.IEnergyContainerItem", striprefs = true)
public class ItemHammerEnergy extends ItemAOE implements IEnergyContainerItem
{
    private static final String KEY_UPGRADES = "upgrades";
    private static final String KEY_SIZE = "size";

    public ItemHammerEnergy(Tool tool)
    {
        super(tool, false);
    }

    @Override
    protected boolean shouldShowInCreativeTabs()
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems)
    {
        super.getSubItems(tab, subItems);

        if(isInCreativeTab(tab))
        {
            ItemStack poweredStack = new ItemStack(this);
            SHEnergyStorage energy = ((SHEnergyStorage) poweredStack.getCapability(CapabilityEnergy.ENERGY, null));
            energy.setEnergyStored(energy.getMaxEnergyStored());
            subItems.add(poweredStack);

            ItemStack upgradedStack = new ItemStack(this);
            energy = ((SHEnergyStorage) upgradedStack.getCapability(CapabilityEnergy.ENERGY, null));
            for(EnumUpgrades u : EnumUpgrades.values())
                setUpgrade(upgradedStack, new Upgrade(u, u.getMaxUpgrades()));
            energy.setEnergyStored(energy.getMaxEnergyStored());
            subItems.add(upgradedStack);
        }
    }

    public static SHEnergyStorage getEnergyStorage(ItemStack stack)
    {
        return (SHEnergyStorage) stack.getCapability(CapabilityEnergy.ENERGY, null);
    }

    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate)
    {
        return getEnergyStorage(container).receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate)
    {
        return getEnergyStorage(container).extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored(ItemStack container)
    {
        return getEnergyStorage(container).getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ItemStack container)
    {
        return getEnergyStorage(container).getMaxEnergyStored();
    }

    public static boolean useEnergy(ItemStack stack, int amount)
    {
        SHEnergyStorage energy = getEnergyStorage(stack);
        if(energy.getEnergyStored() < amount)
            return false;
        else
        {
            energy.extractEnergyInternal(amount);
            return true;
        }
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase entityHit, EntityLivingBase player)
    {
        //Uses energy before damaging stack
        return getEnergyStored(stack) < SHConfig.POWERED.poweredEnergyUsePerBlock * 2 ? super.hitEntity(stack, entityHit, player) : useEnergy(stack, SHConfig.POWERED.poweredEnergyUsePerBlock * 2);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving)
    {
        //Uses energy before damaging stack
        return getEnergyStored(stack) < SHConfig.POWERED.poweredEnergyUsePerBlock ? super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving) : useEnergy(stack, SHConfig.POWERED.poweredEnergyUsePerBlock);
    }

    @Override
    public net.minecraftforge.common.capabilities.ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt)
    {
        return new ICapabilitySerializable<NBTTagCompound>()
        {
            SHEnergyStorage energy = new SHEnergyStorage(SHConfig.POWERED.poweredEnergyCapacity, SHConfig.POWERED.poweredEnergyInputRate, 0);

            @Override
            public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
            {
                return capability == CapabilityEnergy.ENERGY;
            }

            @Nullable
            @Override
            public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing)
            {
                if(capability == CapabilityEnergy.ENERGY)
                    return (T) energy;
                return null;
            }

            @Override
            public NBTTagCompound serializeNBT()
            {
                return energy.serializeNBT();
            }

            @Override
            public void deserializeNBT(NBTTagCompound nbt)
            {
                energy.deserializeNBT(nbt);
            }
        };
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced)
    {
        //Energy
        IEnergyStorage energy = getEnergyStorage(stack);
        tooltip.add(I18n.format("item.hammer_powered.tooltip.energy"));
        tooltip.add(energy.getEnergyStored() + " / " + energy.getMaxEnergyStored());
        tooltip.add("");

        //Upgrades
        List<Upgrade> upgrades = getUpgrades(stack);
        if(GuiScreen.isShiftKeyDown())
        {
            tooltip.add(I18n.format("item.hammer_powered.tooltip.upgrades.shift"));
            upgrades.forEach(upgrade -> tooltip.add("  " + upgrade.getType().toLocal() + " (" + upgrade.getNum() + "/" + upgrade.getType().getMaxUpgrades() + ")"));
        }
        else
        {
            if(upgrades.isEmpty())
                tooltip.add(I18n.format("item.hammer_powered.tooltip.upgrades.none"));
            else
                tooltip.add(I18n.format("item.hammer_powered.tooltip.upgrades.normal", upgrades.size()));
        }
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(hand == EnumHand.MAIN_HAND && player.isSneaking())
        {
            //Change dig size if upgrade is installed
            ItemStack stack = player.getHeldItem(hand);
            Upgrade upgrade = getUpgrade(stack, EnumUpgrades.SIZE);
            if(upgrade != null)
            {
                DigSize size = getDigSize(stack).nextSize(upgrade);
                setDigSize(stack, (byte) size.ordinal());
                if(world.isRemote)
                    player.sendMessage(new TextComponentString("Set dig size to " + size));
                return EnumActionResult.SUCCESS;
            }
        }
        return super.onItemUse(player, world, pos, hand, facing, hitX, hitY, hitZ);
    }

    public static int addUpgrade(ItemStack hammer, ItemStack upgrade)
    {
        if(!(upgrade.getItem() instanceof ItemUpgrade)) return 0;
        EnumUpgrades upgradeToAdd = ((ItemUpgrade) upgrade.getItem()).getUpgrade();
        return addUpgrade(hammer, upgradeToAdd);
    }

    public static int addUpgrade(ItemStack hammer, EnumUpgrades upgradeType)
    {
        NBTTagList tagList = NBTHelper.getList(hammer, KEY_UPGRADES);
        for(int i = 0; i < tagList.tagCount(); i++)
        {
            Upgrade u = new Upgrade(tagList.getCompoundTagAt(i));
            //If upgrade already exists, then increase its number
            if(u.getType() == upgradeType)
            {
                int result = u.increaseNum();
                if(result > 0)
                {
                    tagList.set(i, u.serializeNBT());
                    NBTHelper.setList(hammer, KEY_UPGRADES, tagList);
                    if(upgradeType == EnumUpgrades.CAPACITY)
                        setEnergyUpgrade(hammer, u.getNum());
                }
                return result;
            }
        }

        //If upgrade does not exist, then add it
        tagList.appendTag(new Upgrade(upgradeType).serializeNBT());
        NBTHelper.setList(hammer, KEY_UPGRADES, tagList);
        if(upgradeType == EnumUpgrades.CAPACITY)
            setEnergyUpgrade(hammer, 1);
        return 1;
    }

    private static void setUpgrade(ItemStack hammer, Upgrade upgrade)
    {
        NBTTagList tagList = NBTHelper.getList(hammer, KEY_UPGRADES);
        for(int i = 0; i < tagList.tagCount(); i++)
        {
            Upgrade u = new Upgrade(tagList.getCompoundTagAt(i));
            //If upgrade already exists, then change it
            if(u.getType() == upgrade.getType())
            {
                tagList.set(i, upgrade.serializeNBT());
                NBTHelper.setList(hammer, KEY_UPGRADES, tagList);
                if(upgrade.getType() == EnumUpgrades.CAPACITY)
                    setEnergyUpgrade(hammer, upgrade.getNum());
                return;
            }
        }

        //If upgrade does not exist, then add it
        tagList.appendTag(upgrade.serializeNBT());
        NBTHelper.setList(hammer, KEY_UPGRADES, tagList);
        if(upgrade.getType() == EnumUpgrades.CAPACITY)
            setEnergyUpgrade(hammer, upgrade.getNum());
    }

    public static List<Upgrade> getUpgrades(ItemStack hammer)
    {
        List<Upgrade> upgrades = new ArrayList<>();
        NBTTagList upgradeList = NBTHelper.getList(hammer, KEY_UPGRADES);
        for(int i = 0; i < upgradeList.tagCount(); i++)
            upgrades.add(new Upgrade(upgradeList.getCompoundTagAt(i)));
        upgrades.sort((o1, o2) -> o1.getType().name().compareToIgnoreCase(o2.getType().name()));
        return upgrades;
    }

    public static Upgrade getUpgrade(ItemStack hammer, EnumUpgrades upgrade)
    {
        NBTTagList upgradeList = NBTHelper.getList(hammer, KEY_UPGRADES);
        for(int i = 0; i < upgradeList.tagCount(); i++)
        {
            Upgrade u = new Upgrade(upgradeList.getCompoundTagAt(i));
            if(u.getType() == upgrade)
                return u;
        }
        return null;
    }

    public static DigSize getDigSize(ItemStack hammer)
    {
        return DigSize.values()[NBTHelper.getByte(hammer, KEY_SIZE)];
    }

    public static void setDigSize(ItemStack hammer, byte size)
    {
        NBTHelper.setByte(hammer, KEY_SIZE, size);
    }

    @Override
    public int getMineHeight(ItemStack stack)
    {
        return getDigSize(stack).getSize();
    }

    @Override
    public int getMineWidth(ItemStack stack)
    {
        return getDigSize(stack).getSize();
    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state)
    {
        //Speed upgrade effects
        Upgrade upgrade = getUpgrade(stack, EnumUpgrades.SPEED);
        return super.getDestroySpeed(stack, state) + (upgrade == null ? 0 : upgrade.getNum());
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(slot);

        if(slot == EntityEquipmentSlot.MAINHAND)
        {
            //Attack upgrade effects
            multimap.removeAll(SharedMonsterAttributes.ATTACK_DAMAGE.getName());
            Upgrade upgrade = getUpgrade(stack, EnumUpgrades.ATTACK);
            double damage = attackDamage + (upgrade == null ? 0F : upgrade.getNum());
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", damage, 0));
        }

        return multimap;
    }

    @Override
    public int getHarvestLevel(ItemStack stack, String toolClass, @Nullable EntityPlayer player, @Nullable IBlockState blockState)
    {
        int level = super.getHarvestLevel(stack, toolClass, player, blockState);
        if(level >= 0)
        {
            //Harvest upgrade effects
            Upgrade upgrade = getUpgrade(stack, EnumUpgrades.HARVEST);
            if(upgrade != null) level += upgrade.getNum();
        }
        return level;
    }

    private static void setEnergyUpgrade(ItemStack stack, int upgradeNum)
    {
        SHEnergyStorage energy = getEnergyStorage(stack);
        int capacity = SHConfig.POWERED.poweredEnergyCapacity;
        energy.setCapacity(capacity + (upgradeNum * (capacity / 2)));
    }
}
