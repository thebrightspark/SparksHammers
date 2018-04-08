package brightspark.sparkshammers.gui;

import brightspark.sparkshammers.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public abstract class GuiContainerBase<T extends Container> extends GuiContainer
{
    private final ResourceLocation guiImage;
    protected T container;
    protected int textPlayerInvY = 82;
    protected int textColour = 4210752;

    public GuiContainerBase(T container, String guiName)
    {
        super(container);
        this.container = container;
        guiImage = new ResourceLocation(Reference.MOD_ID, Reference.GUI_TEXTURE_DIR + guiName + ".png");
        xSize = 176;
        ySize = 168;
    }

    protected void drawString(String text, int x, int y, int colour, boolean shadow)
    {
        fontRenderer.drawString(text, x, y, colour, shadow);
    }

    protected void drawCenteredString(String text, int colour)
    {
        drawCenteredString(text, guiTop + ySize / 2, colour);
    }

    protected void drawCenteredString(String text, int y, int colour)
    {
        drawCenteredString(fontRenderer, text, guiLeft + xSize / 2, y, colour);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(guiImage);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
