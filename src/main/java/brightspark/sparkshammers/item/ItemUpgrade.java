package brightspark.sparkshammers.item;

import brightspark.sparkshammers.item.upgrade.EnumUpgrades;

public class ItemUpgrade extends ItemResource
{
    private EnumUpgrades upgrade;

    public ItemUpgrade(EnumUpgrades upgrade)
    {
        super("upgrade_" + upgrade.toString());
        this.upgrade = upgrade;
        setMaxStackSize(1);
    }

    public EnumUpgrades getUpgrade()
    {
        return upgrade;
    }
}
