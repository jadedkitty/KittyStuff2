package com.jadedkitty.kittyStuff2.init;

import com.jadedkitty.kittyStuff2.item.itemKittyGem;
import com.jadedkitty.kittyStuff2.item.itemKittyStuff2;
import com.jadedkitty.kittyStuff2.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class modItems
{
    public static final itemKittyStuff2 kittyGem = new itemKittyGem();

    public static void init()
    {
        GameRegistry.registerItem(kittyGem, "kittyGem");
    }
}
