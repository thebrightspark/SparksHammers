package brightspark.sparkshammers.gui;

import brightspark.sparkshammers.hammerCrafting.HammerCraftingManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class SlotSHCrafting extends SlotCrafting
{
    private InventoryCrafting craftMatrix;

    public SlotSHCrafting(EntityPlayer player, InventoryCrafting craftingInventory, IInventory inventoryIn, int slotIndex, int xPosition, int yPosition)
    {
        super(player, craftingInventory, inventoryIn, slotIndex, xPosition, yPosition);
        this.craftMatrix = craftingInventory;
    }

    /**
     * Similar to the SlotCrafting implementation, but using HammerCraftingManager.getRemainingItems
     */
    @Override
    public ItemStack onTake(EntityPlayer player, ItemStack stack)
    {
        onCrafting(stack);
        NonNullList<ItemStack> remaining = HammerCraftingManager.getRemainingItems(craftMatrix);

        for(int i = 0; i < craftMatrix.getSizeInventory(); i++)
        {
            ItemStack gridStack = craftMatrix.getStackInSlot(i);
            if(!gridStack.isEmpty())
                craftMatrix.decrStackSize(i, 1);

            ItemStack remainingStack = remaining.get(i);
            if(remainingStack.isEmpty()) continue;
            if(gridStack.isEmpty())
                craftMatrix.setInventorySlotContents(i, remainingStack);
            else if(ItemStack.areItemStacksEqual(gridStack, remainingStack))
            {
                remainingStack.grow(gridStack.getCount());
                craftMatrix.setInventorySlotContents(i, remainingStack);
            }
            else if(!player.inventory.addItemStackToInventory(remainingStack))
                player.dropItem(remainingStack, false);
        }

        return stack;
    }
}
