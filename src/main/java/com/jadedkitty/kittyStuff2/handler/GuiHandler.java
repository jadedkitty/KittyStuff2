package com.jadedkitty.kittyStuff2.handler;

import com.jadedkitty.kittyStuff2.block.TileEntities.TileEntityPasteurizer;
import com.jadedkitty.kittyStuff2.reference.Reference;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import com.jadedkitty.kittyStuff2.handler.GuiPasteurizer;

public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity entity = world.getTileEntity(x, y, z);
        if(entity!=null)
        {
            switch(ID)
            {
                case Reference.GUID_PASTEURIZER:
                    if(entity instanceof TileEntityPasteurizer)
                    {
                        return new ContainerPasteurizer(player.inventory,(TileEntityPasteurizer)entity);
                    }
            }
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player,World world,int x, int y, int z)
    {
        TileEntity entity = world.getTileEntity(x, y, z);
        if(entity!=null)
        {
            switch(ID)
            {
                case Reference.GUID_PASTEURIZER:
                    if(entity instanceof TileEntityPasteurizer)
                    {
                        return new GuiPasteurizer(player.inventory,(TileEntityPasteurizer)entity);
                    }
            }
        }
        return null;
    }

}
