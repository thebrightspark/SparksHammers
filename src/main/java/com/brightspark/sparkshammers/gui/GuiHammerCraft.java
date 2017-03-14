package com.brightspark.sparkshammers.gui;

import com.brightspark.sparkshammers.init.SHBlocks;
import com.brightspark.sparkshammers.reference.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class GuiHammerCraft extends GuiContainer
{
    private static final ResourceLocation guiImage = new ResourceLocation(Reference.MOD_ID, Reference.GUI_TEXTURE_DIR + "gui_hammer_craft.png");

    public GuiHammerCraft(InventoryPlayer invPlayer, World world, int x, int y, int z)
    {
        super(new ContainerHammerCraft(invPlayer, world, x, y, z));
        xSize = 184;
        ySize = 219;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        //Draw gui
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(guiImage);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        //Draw text
        fontRenderer.drawString(I18n.format(SHBlocks.blockHammerCraft.getUnlocalizedName() + ".name"), 12, 6, 4210752);
        fontRenderer.drawString(I18n.format("container.inventory"), 12, this.ySize - 92, 4210752);
    }
}
