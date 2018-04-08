package brightspark.sparkshammers.gui;

import brightspark.sparkshammers.init.SHBlocks;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.world.World;

public class GuiHammerCraft extends GuiContainerBase<ContainerHammerCraft>
{
    public GuiHammerCraft(InventoryPlayer invPlayer, World world, int x, int y, int z)
    {
        super(new ContainerHammerCraft(invPlayer, world, x, y, z), "gui_hammer_craft");
        xSize = 184;
        ySize = 219;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        //Draw text
        fontRenderer.drawString(I18n.format(SHBlocks.blockHammerCraft.getUnlocalizedName() + ".name"), 12, 6, 4210752);
        fontRenderer.drawString(I18n.format("container.inventory"), 12, this.ySize - 92, 4210752);
    }
}
