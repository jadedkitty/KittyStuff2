package com.jadedkitty.kittyStuff2.init;

import com.jadedkitty.kittyStuff2.block.BlockKitty;
import com.jadedkitty.kittyStuff2.block.BlockKittyStuff;
import com.jadedkitty.kittyStuff2.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class modblocks
{
    public static final BlockKittyStuff kittyblock = new BlockKitty();

    public static  void init()
    {
        GameRegistry.registerBlock(kittyblock, "kittyblock");
    }
}
