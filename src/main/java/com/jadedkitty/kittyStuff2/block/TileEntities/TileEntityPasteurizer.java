package com.jadedkitty.kittyStuff2.block.TileEntities;

import com.jadedkitty.kittyStuff2.block.Pasteurizer;
import com.jadedkitty.kittyStuff2.init.PasteurizerRecipes;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPasteurizer extends TileEntity implements ISidedInventory
{
    private String localizedName;

    private static final int[] slots_top = new int[]{0};
    private static final int[] slots_side = new int[]{1};
    private static final int[] slots_bottom = new int[]{2,1};

    private ItemStack[] slots = new ItemStack[3];

    public int furnaceSpeed=1800;
    public int burnTime;
    public int currentItemBurnTime;
    public int cookTime;

    public TileEntityPasteurizer()
    {
    	this.localizedName="Pasteurizer";
    }
    {
    	slots = new ItemStack[3];
    }
    public void setGuiDisplayName(String displayName)
    {
        this.localizedName=displayName;
    }

    @Override
    public int getSizeInventory() {
        return 3;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return this.slots[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int quantity) {
        if(this.slots[slot]!=null)
        {
            ItemStack stack;
            if(this.slots[slot].stackSize<=quantity)
            {
                stack = this.slots[slot];
                this.slots[slot]=null;
                return stack;
            }
            else
            {
                stack=this.slots[slot].splitStack(quantity);
                if(this.slots[slot].stackSize==0)
                {
                    this.slots[slot]=null;
                }
                return stack;
            }
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        if(this.slots[slot]!=null)
        {
            ItemStack stack = this.slots[slot];
            this.slots[slot]=null;
            return stack;
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack)
    {
        this.slots[slot]=stack;
        if(stack!=null && stack.stackSize>this.getInventoryStackLimit())
        {
            stack.stackSize=this.getInventoryStackLimit();
        }

    }

    public String getInventoryName()
    {
        return this.hasCustomInventoryName()?this.localizedName:"container.pasteurizer";
    }
    public boolean hasCustomInventoryName()
    {
        return this.localizedName!=null && this.localizedName.length()>0;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(xCoord, yCoord, zCoord) == this && player.getDistanceSq((double) xCoord + 0.5D, (double) yCoord + 0.5D, (double) zCoord + 0.5D) <= 64.0D;
    }

    public void openInventory() {

    }

    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        switch(slot)
        {
        case 0://Input

        case 1://Fuels
            return isItemFuel(itemStack);
        case 2://Output
        }
        return false;
    }

    public static boolean isItemFuel(ItemStack itemStack) {
        {
            return getItemBurnTime(itemStack)>0;
        }
    }

    private static int getItemBurnTime(ItemStack itemStack) {
        if (itemStack == null)
        {
            return 0;
        }
        else
        {
            Item item = itemStack.getItem();
            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air)
            {
                Block block = Block.getBlockFromItem(item);
                if (block == Blocks.coal_block){return Fuels.COAL_BLOCK;}
            }
            if(item == Items.coal){return Fuels.COAL;}
            return 0;
        }
    }

    public boolean isBurning()
    {
        return this.burnTime>0;
    }

    public void updateEntity()
    {
        boolean flag= this.burnTime>0;
        boolean flag1=false;
        if(this.isBurning())
        {
            this.burnTime--;
        }
        if(!this.worldObj.isRemote)
        {
            if(this.burnTime==0 && this.canSmelt())
            {
                this.currentItemBurnTime=this.burnTime = getItemBurnTime(this.slots[1]);

                if(this.isBurning())
                {
                    flag1=true;
                    if(this.slots[1]!=null)
                    {
                        this.slots[1].stackSize--;
                        if(this.slots[1].stackSize==0)
                        {
                            this.slots[1] = this.slots[1].getItem().getContainerItem(this.slots[1]);
                        }
                    }
                }
            }
            if(this.isBurning() && this.canSmelt())
            {
                this.cookTime++;
                if(this.cookTime == this.furnaceSpeed)
                {
                    this.cookTime=0;
                    this.smeltItem();
                    flag1=true;
                }
            }
            else
            {
                this.cookTime=0;
            }
            if(flag!=this.isBurning())
            {
                flag1=true;
                Pasteurizer.updateBlockState(this.burnTime>0,this.worldObj,this.xCoord,this.yCoord,this.zCoord);
            }
        }
        if(flag1)
        {
            this.markDirty();
        }
    }


    public boolean canSmelt()
    {
        if(this.slots[0]==null)
        {
            return false;
        }
        else
        {
            Item contents=this.slots[0].getItem();
            ItemStack stack = PasteurizerRecipes.smelting().getSmeltingResult(this.slots[0]);


            if(stack==null)
            {
                return false;
            }
            if(contents==Items.milk_bucket)
            {
                return this.slots[0].stackSize>=1;
            }
            if(this.slots[2]==null)
            {
                return true;
            }
            if(!this.slots[2].isItemEqual(stack))
            {
                return false;
            }

            int result = this.slots[2].stackSize+stack.stackSize;
            return (result<=getInventoryStackLimit() && result <=stack.getMaxStackSize());

        }
    }

    public void smeltItem()
    {
        if(this.canSmelt())
        {
            Item item=this.slots[0].getItem();
            ItemStack stack = PasteurizerRecipes.smelting().getSmeltingResult(this.slots[0]);

            if(this.slots[2] == null)
            {
                this.slots[2] = stack.copy();
            }
            else if(this.slots[2].isItemEqual(stack) && this.slots[2].stackSize+stack.stackSize<= stack.getMaxStackSize())
            {
                this.slots[2].stackSize+=stack.stackSize;
            }

            if(item==Items.milk_bucket)
            {
                if(this.slots[0].stackSize>=1)
                {
                    this.slots[0].stackSize-=1;
                }
            }
            else
            {
                this.slots[0].stackSize--;
            }

            if(this.slots[0].stackSize<=0)
            {
                this.slots[0]=null;
            }

        }
    }


    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return side==0?slots_bottom:(side==1?slots_top:slots_side);
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack itemStack, int p_102007_3_) {
        return  this.isItemValidForSlot(slot, itemStack);
    }

    @Override
    public boolean canExtractItem(int i, ItemStack itemStack, int j) {
        return j !=0 || i!=1;
    }

    public static boolean canBeSmelted(ItemStack itemstack)
    {
        return PasteurizerRecipes.smelting().getSmeltingResult(itemstack)!=null;
    }

    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int par1)
    {
        if (currentItemBurnTime == 0)
        {
            currentItemBurnTime = this.furnaceSpeed;
        }

        return (burnTime * par1) / currentItemBurnTime;
    }
    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int par1)
    {
        return (cookTime * par1) / this.furnaceSpeed;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        this.burnTime = nbt.getShort("BurnTime");
        this.cookTime = nbt.getShort("CookTime");
        this.currentItemBurnTime = nbt.getShort("CurrentBurnTime");

        NBTTagList tagList = nbt.getTagList("Inventory",10);
        this.slots = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound tag = (NBTTagCompound) tagList.getCompoundTagAt(i);

            byte slot = tag.getByte("Slot");

            if (slot >= 0 && slot < slots.length) {
                slots[slot] = ItemStack.loadItemStackFromNBT(tag);
            }
        }
        if(nbt.hasKey("CustomName"))
        {
            this.localizedName=nbt.getString("CustomName");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        NBTTagList itemList = new NBTTagList();

        nbt.setShort("BurnTime",(short)this.burnTime);
        nbt.setShort("CookTime", (short)this.cookTime);
        nbt.setShort("CurrentBurnTime",(short)this.currentItemBurnTime);

        for (int i = 0; i < slots.length; i++) {
            ItemStack stack = slots[i];

            if (stack != null) {
                NBTTagCompound tag = new NBTTagCompound();

                tag.setByte("Slot", (byte) i);
                stack.writeToNBT(tag);
                itemList.appendTag(tag);
            }
        }

        nbt.setTag("Inventory", itemList);
        if(this.hasCustomInventoryName())
        {
            nbt.setString("CustomName",this.localizedName);
        }
    }
}
