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
}
