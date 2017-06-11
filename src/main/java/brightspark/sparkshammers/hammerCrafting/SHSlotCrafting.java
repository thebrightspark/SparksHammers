package brightspark.sparkshammers.hammerCrafting;

import brightspark.sparkshammers.util.CommonUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.*;

public class SHSlotCrafting extends Slot
{
    /** The craft matrix inventory linked to this result slot. */
    private final InventoryCrafting craftMatrix;
    /** The player that is using the GUI where this slot resides. */
    private final EntityPlayer thePlayer;
    /** The number of items that have been crafted so far. Gets passed to ItemStack.onCrafting before being reset. */
    private int amountCrafted;

    public SHSlotCrafting(EntityPlayer player, InventoryCrafting craftingInventory, IInventory p_i45790_3_, int slotIndex, int xPosition, int yPosition)
    {
        super(p_i45790_3_, slotIndex, xPosition, yPosition);
        thePlayer = player;
        craftMatrix = craftingInventory;
    }

    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    public boolean isItemValid(ItemStack stack)
    {
        return false;
    }

    /**
     * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new
     * stack.
     */
    public ItemStack decrStackSize(int amount)
    {
        if (getHasStack())
            amountCrafted += Math.min(amount, getStack().getCount());

        return super.decrStackSize(amount);
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood. Typically increases an
     * internal count then calls onCrafting(item).
     */
    protected void onCrafting(ItemStack stack, int amount)
    {
        amountCrafted += amount;
        onCrafting(stack);
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood.
     */
    protected void onCrafting(ItemStack stack)
    {
        if (amountCrafted > 0)
            stack.onCrafting(thePlayer.world, thePlayer, amountCrafted);

        amountCrafted = 0;
    }

    //onPickupFromSlot
    public ItemStack func_190901_a(EntityPlayer playerIn, ItemStack stack)
    {
        net.minecraftforge.fml.common.FMLCommonHandler.instance().firePlayerCraftingEvent(playerIn, stack, craftMatrix);
        onCrafting(stack);
        net.minecraftforge.common.ForgeHooks.setCraftingPlayer(playerIn);
        ItemStack[] aitemstack = HammerCraftingManager.getInstance().func_180303_b(craftMatrix, playerIn.world);
        net.minecraftforge.common.ForgeHooks.setCraftingPlayer(null);

        for (int i = 0; i < aitemstack.length; ++i)
        {
            ItemStack itemstack = craftMatrix.getStackInSlot(i);
            ItemStack itemstack1 = aitemstack[i];

            if (!CommonUtils.isStackEmptyOrNull(itemstack))
                craftMatrix.decrStackSize(i, 1);

            if (!CommonUtils.isStackEmptyOrNull(itemstack1))
            {
                if (CommonUtils.isStackEmptyOrNull(craftMatrix.getStackInSlot(i)))
                    craftMatrix.setInventorySlotContents(i, itemstack1);
                else if (!thePlayer.inventory.addItemStackToInventory(itemstack1))
                    thePlayer.dropItem(itemstack1, false);
            }
        }
        return stack;
    }
}
