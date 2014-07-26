package com.jadedkitty.kittyStuff2.handler;

import com.jadedkitty.kittyStuff2.init.modItems;
import com.jadedkitty.kittyStuff2.item.itemKittyClaw;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

import java.util.Random;

public class Drops
{
    @SubscribeEvent(priority= EventPriority.NORMAL, receiveCanceled=true)
    public void onEvent(LivingDropsEvent event)
    {
        if (event.entity instanceof EntityOcelot)
        {
            // DEBUG
            System.out.println("EntityOcelot drops event");
            event.drops.clear();
            Random rand;
            ItemStack itemStackToDrop = new ItemStack(modItems.kittyClaw, 3);
            event.drops.add(new EntityItem(event.entity.worldObj, event.entity.posX,

                    event.entity.posY, event.entity.posZ, itemStackToDrop));
        }
    }
}
