package com.jadedkitty.kittyStuff2.item;

import com.jadedkitty.kittyStuff2.KittyStuff2;
import com.jadedkitty.kittyStuff2.reference.Reference;

public class itemKittyGem extends itemKittyStuff2
{
    public itemKittyGem()
    {
        super();
        this.setUnlocalizedName("kittyGem");
        this.setTextureName(Reference.MOD_ID + ":kittyGem");
        this.setCreativeTab(KittyStuff2.tabTools);
    }
}
