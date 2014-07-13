package com.jadedkitty.kittyStuff2.item;

import com.jadedkitty.kittyStuff2.KittyStuff2;
import com.jadedkitty.kittyStuff2.reference.Reference;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class kittyClaws extends sword{
    public kittyClaws(ToolMaterial p_i45356_1_) {
        super(p_i45356_1_);
        this.setUnlocalizedName("kittyClaws");
        this.setCreativeTab(KittyStuff2.tabTools);
        this.setTextureName(Reference.MOD_ID + ":kittyClaws");
    }
	@Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int blockFace, float par8, float par9, float par10)
    {
		if(player.isInWater() && !world.isRemote)
		{
			ItemStack fish = new ItemStack(Items.fish,1);
			EntityItem dropItem = new EntityItem(world,(double)x,(double)y,(double)z,fish);
			dropItem.delayBeforeCanPickup=0;
            world.spawnEntityInWorld(dropItem);
        }
		return true;
    }
}
