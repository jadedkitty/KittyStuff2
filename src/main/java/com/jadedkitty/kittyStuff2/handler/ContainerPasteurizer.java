package com.jadedkitty.kittyStuff2.handler;

import com.jadedkitty.kittyStuff2.block.TileEntities.TileEntityPasteurizer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;

public class ContainerPasteurizer extends Container
{
    private TileEntityPasteurizer tileEntity;

    public int lastBurnTime;
    public int lastCurrentItemBurnTime;
    public int lastCookTime;

    public ContainerPasteurizer(InventoryPlayer inventory, TileEntityPasteurizer entity)
    {
        this.tileEntity=entity;

        this.addSlotToContainer(new PasteurizerInputSlot(tileEntity, 0, 56, 17));
        this.addSlotToContainer(new PasteurizerFuelSlot(tileEntity, 1, 56, 53));
        this.addSlotToContainer(new OutputSlot(tileEntity, 2, 116, 35));

        for (int i = 0; i < 3; i++)
        {
            for (int k = 0; k < 9; k++)
            {
                this.addSlotToContainer((new Slot(inventory, k+i * 9 + 9, 8 + k * 18, 84 + i * 18)));
            }
        }

        for (int i = 0; i < 9; i++)
        {
            this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
        }

    }

    public void addCraftingToCrafters(ICrafting crafting)
    {
        super.addCraftingToCrafters(crafting);
        crafting.sendProgressBarUpdate(this, 0, this.tileEntity.cookTime);
        crafting.sendProgressBarUpdate(this, 1, this.tileEntity.burnTime);
        crafting.sendProgressBarUpdate(this, 2, this.tileEntity.currentItemBurnTime);
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastCookTime != this.tileEntity.cookTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tileEntity.cookTime);
            }

            if (this.lastBurnTime != this.tileEntity.burnTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileEntity.burnTime);
            }

            if (this.lastCurrentItemBurnTime!= this.tileEntity.currentItemBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.tileEntity.currentItemBurnTime);
            }
        }

        this.lastCookTime = this.tileEntity.cookTime;
        this.lastBurnTime = this.tileEntity.burnTime;
        this.lastCurrentItemBurnTime = this.tileEntity.currentItemBurnTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int slot, int newValue)
    {
        if (slot == 0)
        {
            this.tileEntity.cookTime = newValue;
        }

        if (slot == 1)
        {
            this.tileEntity.burnTime = newValue;
        }

        if (slot == 2)
        {
            this.tileEntity.currentItemBurnTime = newValue;
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_)
    {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotnumber)
{
    ItemStack itemstack = null;
    Slot slot = (Slot)inventorySlots.get(slotnumber);

    if (slot != null && slot.getHasStack())
    {
        ItemStack itemstack1 = slot.getStack();
        itemstack = itemstack1.copy();

        if (slotnumber == 2)
        {
            if (!mergeItemStack(itemstack1, 3, 39, true))
            {
                return null;
            }

            slot.onSlotChange(itemstack1, itemstack);
        }
        else if (slotnumber == 1 || slotnumber == 0)
        {
            if (!mergeItemStack(itemstack1, 3, 39, false))
            {
                return null;
            }
        }
        else if (TileEntityPasteurizer.canBeSmelted(itemstack1))
        {
            if (!mergeItemStack(itemstack1, 0, 1, false))
            {
                return null;
            }
        }
        else if (TileEntityPasteurizer.isItemFuel(itemstack1))
        {
            if (!mergeItemStack(itemstack1, 1, 2, false))
            {
                return null;
            }
        }
        else if (slotnumber >= 3 && slotnumber < 30)
        {
            if (!mergeItemStack(itemstack1, 30, 39, false))
            {
                return null;
            }
        }
        else if (slotnumber >= 30 && slotnumber < 39 && !mergeItemStack(itemstack1, 3, 30, false))
        {
            return null;
        }

        if (itemstack1.stackSize == 0)
        {
            slot.putStack(null);
        }
        else
        {
            slot.onSlotChanged();
        }

        if (itemstack1.stackSize == itemstack.stackSize)
        {
            return null;
        }

        slot.onPickupFromSlot(player, itemstack);
    }

    return itemstack;
}
}
