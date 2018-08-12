package brightspark.sparkshammers.handlers;

import brightspark.sparkshammers.init.SHItems;
import brightspark.sparkshammers.item.ItemHammerEnergy;
import brightspark.sparkshammers.item.ItemUpgrade;
import brightspark.sparkshammers.item.upgrade.EnumUpgrades;
import brightspark.sparkshammers.item.upgrade.Upgrade;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class EventHandler
{
    private static void addLoot(LootPool pool, Item item, int weight)
    {
        if(item != null)
            pool.addEntry(new LootEntryItem(item, weight, 0, new LootFunction[0], new LootCondition[0], item.getRegistryName().toString()));
    }

    @SubscribeEvent
    public static void onLootLoad(LootTableLoadEvent event)
    {
        if(event.getName().equals(LootTableList.CHESTS_ABANDONED_MINESHAFT))
        {
            LootPool pool = event.getTable().getPool("main");
            addLoot(pool, SHItems.hammerWood, 1);
            addLoot(pool, SHItems.hammerStone, 1);
        }
    }

    @SubscribeEvent
    public static void onAnvilRecipe(AnvilUpdateEvent event)
    {
        //Powered Hammer upgrading
        if(event.getLeft().getItem() instanceof ItemHammerEnergy && event.getRight().getItem() instanceof ItemUpgrade)
        {
            ItemStack hammer = event.getLeft();
            ItemStack upgrade = event.getRight();
            ItemStack output = hammer.copy();
            int num = ItemHammerEnergy.addUpgrade(output, upgrade);
            if(num > 0)
            {
                event.setOutput(output);
                switch(((ItemUpgrade) upgrade.getItem()).getUpgrade())
                {
                    case HARVEST:
                        event.setCost(num * 20);
                        break;
                    case SIZE:
                        event.setCost(num * 10);
                        break;
                    case SPEED:
                    case ATTACK:
                    case CAPACITY:
                    default:
                        event.setCost(num * 5);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onAnvilUpgrade(AnvilRepairEvent event)
    {
        ItemStack left = event.getItemInput();
        ItemStack right = event.getIngredientInput();
        if(left.getItem() instanceof ItemHammerEnergy && right.getItem() instanceof ItemUpgrade && ((ItemUpgrade) right.getItem()).getUpgrade() == EnumUpgrades.SIZE)
        {
            ItemStack output = event.getItemResult();
            Upgrade upgrade = ItemHammerEnergy.getUpgrade(output, EnumUpgrades.SIZE);
            ItemHammerEnergy.setDigSize(output, (byte) upgrade.getNum());
            //Send chat message if it's the first size upgrade on the hammer
            if(event.getEntityPlayer().world.isRemote && upgrade.getNum() == 1)
                event.getEntityPlayer().sendMessage(new TextComponentTranslation("item.hammer_powered.chat.size"));
        }
    }
}
