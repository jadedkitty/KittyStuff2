package com.jadedkitty.kittyStuff2.block;

import com.jadedkitty.kittyStuff2.KittyStuff2;
import com.jadedkitty.kittyStuff2.block.TileEntities.TileEntityPasteurizer;
import com.jadedkitty.kittyStuff2.init.modblocks;
import com.jadedkitty.kittyStuff2.reference.Reference;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;

import java.util.Random;

public class Pasteurizer extends BlockContainer
{
    private final boolean isActive;

    @SideOnly(Side.CLIENT)
    private IIcon iconFront;
    @SideOnly(Side.CLIENT)
    private IIcon iconTop;

    private static boolean keepInventory;

    public Pasteurizer(boolean isActive)
    {
        super(Material.iron);
        this.setHardness(3.5F);
        this.isActive=isActive;
    }


    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(Reference.MOD_ID+":pasteurizerSide");
        if(this.isActive)
        {
            this.iconFront = iconRegister.registerIcon(Reference.MOD_ID+":pasteurizerSideOpen");
        }
        else
        {
            this.iconFront = iconRegister.registerIcon(Reference.MOD_ID+":pasteurizerSideClosed");
        }
        this.iconTop = iconRegister.registerIcon(Reference.MOD_ID+":pasteurizerTop");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side,int meta)
    {
        //return side==3?this.iconFront:(side==1?this.iconTop:(side==0?this.iconTop:(side!=meta?this.blockIcon:this.iconFront)));
        return side==1?this.iconTop:(side==0?this.iconTop:(side!=meta?this.blockIcon:this.iconFront));
    }

    public Item getItemDropped(World world,int x,int y,int z)
    {
        return Item.getItemFromBlock(modblocks.pasteurizerIdle);
    }

    public void onBlockAdded(World world,int x,int y,int z)
    {
        super.onBlockAdded(world, x, y, z);
        this.setDefaultDirection(world,x,y,z);
    }

    private void setDefaultDirection(World world, int x, int y, int z)
    {
        if(!world.isRemote)
        {
            Block b1 = world.getBlock(x,y,z-1);
            Block b2 = world.getBlock(x,y,z+1);
            Block b3 = world.getBlock(x-1,y,z);
            Block b4 = world.getBlock(x+1,y,z);

            byte b0 = 3;
            if(b1.func_149730_j() && !b2.func_149730_j())
            {
                b0=3;
            }
            if(b2.func_149730_j() && !b1.func_149730_j())
            {
                b0=2;
            }
            if(b3.func_149730_j() && !b4.func_149730_j())
            {
                b0=5;
            }
            if(b4.func_149730_j() && !b3.func_149730_j())
            {
                b0=4;
            }

            world.setBlockMetadataWithNotify(x, y, z, b0, 2);
        }
    }

    //TODO onBlockActivated
    public boolean onBlockActivated(World world, int x, int y, int z,EntityPlayer player,int side,float hitX,float hitY,float hitZ)
    {
        if(!world.isRemote)
        {
            FMLNetworkHandler.openGui(player, KittyStuff2.instance, Reference.GUID_PASTEURIZER, world, x, y, z);
        }
        return true;
    }
    @Override
    public TileEntity createNewTileEntity(World world, int i)
    {
        return new TileEntityPasteurizer();
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world,int x,int y,int z,Random random)
    {
        if(this.isActive)
        {
            int direction=world.getBlockMetadata(x, y, z);
            float x1=(float)x+0.5F;
            float y1=(float)y+random.nextFloat();
            float z1=(float)z+0.5F;

            float f=0.52F;
            float f1=random.nextFloat() * 0.6F - 0.3F;

            if(direction==4)
			{
				world.spawnParticle("smoke", (double)(x1-f), (double)(y1), (double)(z1+f1), 0D, 0D, 0D);
				world.spawnParticle("flame", (double)(x1-f), (double)(y1), (double)(z1+f1), 0D, 0D, 0D);
			}
			if(direction==5)
			{
				world.spawnParticle("smoke", (double)(x1+f), (double)(y1), (double)(z1+f1), 0D, 0D, 0D);
				world.spawnParticle("flame", (double)(x1+f), (double)(y1), (double)(z1+f1), 0D, 0D, 0D);
			}
			if(direction==2)
			{
				world.spawnParticle("smoke", (double)(x1+f1), (double)(y1), (double)(z1-f), 0D, 0D, 0D);
				world.spawnParticle("flame", (double)(x1+f1), (double)(y1), (double)(z1-f), 0D, 0D, 0D);
			}
			if(direction==3)
			{
				world.spawnParticle("smoke", (double)(x1+f1), (double)(y1), (double)(z1+f), 0D, 0D, 0D);
				world.spawnParticle("flame", (double)(x1+f1), (double)(y1), (double)(z1+f), 0D, 0D, 0D);
			}
		}
	}
    public void onBlockPlacedBy(World world,int x, int y,int z,EntityLivingBase entityPlayer,ItemStack itemstack)
    {
        int l = MathHelper.floor_double((double)(entityPlayer.rotationYaw*4.0F/360.F)+0.5D) & 3;

        switch(l)
        {
            case 0:
                world.setBlockMetadataWithNotify(x, y, z, 2, 2);
                break;
            case 1:
                world.setBlockMetadataWithNotify(x, y, z, 5, 2);
                break;
            case 2:
                world.setBlockMetadataWithNotify(x, y, z, 3, 2);
                break;
            case 3:
                world.setBlockMetadataWithNotify(x, y, z, 4, 2);
                break;
            default:
                break;
        }
        if(itemstack.hasDisplayName())
        {
            ((TileEntityPasteurizer) world.getTileEntity(x,y,z)).setGuiDisplayName(itemstack.getDisplayName());
        }
    }

    public static void updateBlockState(boolean active, World world, int x, int y, int z)
    {
        int i=world.getBlockMetadata(x, y, z);
        TileEntity entity = world.getTileEntity(x, y, z);

        keepInventory=true;

        if(active)
        {
            world.setBlock(x, y, z, modblocks.pasteurizerActive);
        }
        else
        {
            world.setBlock(x, y, z, modblocks.pasteurizerIdle);
        }
        keepInventory=false;
        world.setBlockMetadataWithNotify(x, y, z, i, 2);

        if(entity!=null)
        {
            entity.validate();
            world.setTileEntity(x, y, z, entity);
        }
    }
    public void breakBlock(World world, int x, int y, int z, Block oldblock, int oldMetadata)
    {
        if(!keepInventory)
        {
            TileEntityPasteurizer tileEntity = (TileEntityPasteurizer) world.getTileEntity(x, y, z);
            if(tileEntity!=null)
            {
                for(int i=0;i<tileEntity.getSizeInventory();i++)
                {
                    ItemStack stack = tileEntity.getStackInSlot(i);
                    if(stack!=null)
                    {
                        EntityItem itemDrop = new EntityItem(world,(double)(x),(double)(y), (double)(z), stack);
                        itemDrop.delayBeforeCanPickup=0;
                        world.spawnEntityInWorld(itemDrop);
                    }
                }
                world.func_147453_f(x, y, z,oldblock);
            }
        }
        super.breakBlock(world, x, y, z, oldblock, oldMetadata);
    }
    public Item getItem(World world, int x,int y, int z)
    {
        return Item.getItemFromBlock(modblocks.pasteurizerIdle);
    }
}
