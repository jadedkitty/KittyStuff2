package com.jadedkitty.kittyStuff2.item;

import com.jadedkitty.kittyStuff2.KittyStuff2;
import com.jadedkitty.kittyStuff2.init.modItems;
import com.jadedkitty.kittyStuff2.reference.Reference;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class itemKittyClaw extends itemKittyStuff2
{
    public itemKittyClaw()
    {
        super();
        this.setUnlocalizedName("kittyClaw");
        this.setTextureName(Reference.MOD_ID + ":kittyClaw");
        this.setCreativeTab(KittyStuff2.tabTools);
    }

    @SubscribeEvent
    public void playerKilledOcelot(LivingDropsEvent event){
        if (event.source.getDamageType().equals("player")){
            double rand = Math.random();
            if (event.entityLiving instanceof EntityOcelot){
                System.out.print(rand);
                if(rand < 0.10D){
                    event.entityLiving.dropItem(modItems.kittyClaw, 1);
                }
            }
        }
    }
}
