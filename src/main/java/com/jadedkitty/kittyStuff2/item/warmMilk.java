package com.jadedkitty.kittyStuff2.item;

import com.jadedkitty.kittyStuff2.KittyStuff2;
import com.jadedkitty.kittyStuff2.reference.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class warmMilk extends ItemBucketMilk
{
	public warmMilk()
	{
	    super();
	    this.setUnlocalizedName("warmMilk");
	    this.setTextureName(Reference.MOD_ID + ":warmMilk");
	    this.setCreativeTab(KittyStuff2.tabTools);
	}
	
	@Override
    public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer player)
    {
        if (!player.capabilities.isCreativeMode)
        {
            --itemstack.stackSize;
        }

        if (!world.isRemote)
        {
        	player.curePotionEffects(itemstack);
        	player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 600, 3));
            
        }
		return new ItemStack(Items.bucket,1);
    }
}
