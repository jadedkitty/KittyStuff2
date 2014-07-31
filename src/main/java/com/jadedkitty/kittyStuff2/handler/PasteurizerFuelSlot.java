package com.jadedkitty.kittyStuff2.handler;

import com.jadedkitty.kittyStuff2.block.TileEntities.TileEntityPasteurizer;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class PasteurizerFuelSlot extends Slot
{
    public PasteurizerFuelSlot(IInventory inventory, int par2, int par3, int par4)
    {
        super(inventory, par2, par3, par4);
    }

    @Override
    public boolean isItemValid(ItemStack itemstack)
    {
        if(itemstack.getItem() == Items.coal)
        {
            return true;
        }
        Item item = itemstack.getItem();
		if(item instanceof ItemBlock && Block.getBlockFromItem(item)!=Blocks.air)
		{
			Block block = Block.getBlockFromItem(item);
			if(block==Blocks.coal_block)
            {
				return true;
            }
        }
        return false;
    }

    @Override
    public int getSlotStackLimit()
    {
        return 64;
    }
}