package com.jadedkitty.kittyStuff2.handler;

import com.jadedkitty.kittyStuff2.block.TileEntities.TileEntityPasteurizer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class PasteurizerInputSlot extends Slot
{
    public PasteurizerInputSlot(IInventory inventory, int par2, int par3, int par4)
    {
        super(inventory, par2, par3, par4);
    }
    @Override
    public boolean isItemValid(ItemStack itemstack)
    {
        return true;
        //return TileEntityPressureVessel.canBeSmelted(itemstack);
    }

    @Override
    public int getSlotStackLimit()
    {
        return 64;
    }
}