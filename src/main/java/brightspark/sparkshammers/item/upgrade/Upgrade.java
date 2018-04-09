package brightspark.sparkshammers.item.upgrade;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class Upgrade implements INBTSerializable<NBTTagCompound>
{
    private EnumUpgrades type;
    private short num = 1;

    public Upgrade(EnumUpgrades type)
    {
        this.type = type;
    }

    public Upgrade(EnumUpgrades type, short num)
    {
        this(type);
        this.num = num;
    }

    public Upgrade(NBTTagCompound nbt)
    {
        deserializeNBT(nbt);
    }

    public EnumUpgrades getType()
    {
        return type;
    }

    public short getNum()
    {
        return num;
    }

    public void setNum(short num)
    {
        this.num = (short) Math.min(num, type.getMaxUpgrades());
    }

    /**
     * Tries to increase the num and returns the new num or 0 if it couldn't be increased any more
     */
    public int increaseNum()
    {
        return num < type.getMaxUpgrades() ? ++num : 0;
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("name", type.name());
        nbt.setShort("num", num);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {
        type = EnumUpgrades.valueOf(nbt.getString("name"));
        num = nbt.getShort("num");
    }
}
