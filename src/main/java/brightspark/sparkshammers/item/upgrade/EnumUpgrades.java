package brightspark.sparkshammers.item.upgrade;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public enum EnumUpgrades
{
    SIZE(2),
    SPEED(3),
    ATTACK(3),
    HARVEST,
    CAPACITY(4);

    private short maxUpgrades = 1;

    EnumUpgrades() {}

    EnumUpgrades(int maxUpgrades)
    {
        this.maxUpgrades = (short) maxUpgrades;
    }

    public short getMaxUpgrades()
    {
        return maxUpgrades;
    }

    @Override
    public String toString()
    {
        return name().toLowerCase();
    }

    @SideOnly(Side.CLIENT)
    public String toLocal()
    {
        return I18n.format("upgrade." + toString() + ".name");
    }
}
