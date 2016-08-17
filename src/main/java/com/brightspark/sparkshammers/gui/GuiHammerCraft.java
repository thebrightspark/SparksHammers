package com.brightspark.sparkshammers.gui;

import com.brightspark.sparkshammers.container.ContainerHammerCraft;
import com.brightspark.sparkshammers.init.SHBlocks;
import com.brightspark.sparkshammers.reference.Reference;
import com.brightspark.sparkshammers.util.Lang;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class GuiHammerCraft extends GuiContainer
{
    private static final ResourceLocation guiImage = new ResourceLocation(Reference.MOD_ID, Reference.GUI_TEXTURE_DIR + "guiHammerCraft.png");

    public GuiHammerCraft(InventoryPlayer invPlayer, World world, int x, int y, int z)
    {
        super(new ContainerHammerCraft(invPlayer, world, x, y, z));
        this.xSize = 184;
        this.ySize = 219;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        //Draw gui
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(guiImage);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        //Draw text
        this.fontRendererObj.drawString(Lang.localize(SHBlocks.blockHammerCraft.getUnlocalizedName() + ".name"), 12, 6, 4210752);
        this.fontRendererObj.drawString(Lang.localize("container.inventory"), 12, this.ySize - 92, 4210752);
    }
}
