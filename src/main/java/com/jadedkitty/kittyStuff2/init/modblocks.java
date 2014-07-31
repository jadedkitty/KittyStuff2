package com.jadedkitty.kittyStuff2.init;

import com.jadedkitty.kittyStuff2.KittyStuff2;
import com.jadedkitty.kittyStuff2.block.Pasteurizer;
import com.jadedkitty.kittyStuff2.block.TileEntities.TileEntityPasteurizer;
import net.minecraft.block.Block;

import com.jadedkitty.kittyStuff2.block.BlockKitty;
import com.jadedkitty.kittyStuff2.block.BlockKittyStuff;
import com.jadedkitty.kittyStuff2.reference.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class modblocks
{
    public static Block kittyblock;
    public static Block pasteurizerIdle;
    public static Block pasteurizerActive;

    public static  void init()
    {
    	kittyblock = new BlockKitty();
    	pasteurizerIdle = new Pasteurizer(false).setBlockName("PasteurizerIdle").setCreativeTab(KittyStuff2.tabTools);
        pasteurizerActive = new Pasteurizer(true).setBlockName("PasteurizerActive").setLightLevel(0.625F);
    }
    public static void register()
    {
    	GameRegistry.registerBlock(kittyblock, "kittyblock");
        GameRegistry.registerBlock(pasteurizerIdle, pasteurizerIdle.getUnlocalizedName());
        GameRegistry.registerBlock(pasteurizerActive, pasteurizerActive.getUnlocalizedName());
    	
        
    }
    public static  void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityPasteurizer.class,"Pasteurizer");
    }
}
