package com.jadedkitty.kittyStuff2.handler;

import com.jadedkitty.kittyStuff2.block.TileEntities.TileEntityPasteurizer;
import com.jadedkitty.kittyStuff2.reference.Reference;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

class GuiPasteurizer extends GuiContainer
{
    public static final ResourceLocation bground = new ResourceLocation("textures/gui/container/furnace.png");
    public TileEntityPasteurizer tileEntity;
    public GuiPasteurizer(InventoryPlayer inventoryPlayer, TileEntityPasteurizer entity)
    {
        super(new ContainerPasteurizer(inventoryPlayer,entity));
        this.tileEntity=entity;
        this.xSize=176;
        this.ySize=166;
    }

    public void drawGuiContainerForegroundLayer(int par1,int par2)
    {
        String name = this.tileEntity.hasCustomInventoryName() ? this.tileEntity.getInventoryName() : I18n.format(this.tileEntity.getInventoryName(), new Object[0]);

        this.fontRendererObj.drawString(name, this.xSize/2 - this.fontRendererObj.getStringWidth(name)/2, 6, 10970616);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96+2, 10970616);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_,int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1F, 1F, 1F, 1F);

        Minecraft.getMinecraft().getTextureManager().bindTexture(bground);
        drawTexturedModalRect(guiLeft,guiTop,0,0,xSize,ySize);

        // This is the x value of the picture, it will be used later
        int x = (width - xSize) / 2;
        // This is the y value of the picture, it will be used later
        int y = (height - ySize) / 2;
        if (tileEntity.isBurning())
        {
            int i1 = this.tileEntity.getBurnTimeRemainingScaled(13);
            this.drawTexturedModalRect(x + 56, y + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 1);
            i1 = this.tileEntity.getCookProgressScaled(24);
            this.drawTexturedModalRect(x + 79, y + 34, 176, 14, i1 + 1, 16);
        }
    }

}