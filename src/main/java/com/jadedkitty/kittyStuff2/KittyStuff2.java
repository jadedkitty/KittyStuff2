package com.jadedkitty.kittyStuff2;

import com.jadedkitty.kittyStuff2.handler.ConfigurationHandler;
import com.jadedkitty.kittyStuff2.init.modItems;
import com.jadedkitty.kittyStuff2.init.modblocks;
import com.jadedkitty.kittyStuff2.init.recipes;
import com.jadedkitty.kittyStuff2.item.itemKittyGem;
import com.jadedkitty.kittyStuff2.proxy.IProxy;
import com.jadedkitty.kittyStuff2.reference.Reference;
import com.jadedkitty.kittyStuff2.utility.CreativeTab;
import com.jadedkitty.kittyStuff2.utility.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

@Mod(modid = Reference.MOD_ID, name=Reference.MOD_NAME, version=Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class KittyStuff2
{

    @Mod.Instance(Reference.MOD_ID)
    public static KittyStuff2 instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;


    public static CreativeTabs tabTools = new CreativeTab(CreativeTabs.getNextID(),"KittyStuff", Items.diamond,"Kitty Stuff 2");

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
        LogHelper.info("Pre Initialization Complete!");

        modItems.init();

        modblocks.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        ((CreativeTab) tabTools).setTabIconItem(modItems.kittyGem);
        LogHelper.info("Initialization Complete!");
        recipes.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        LogHelper.info("Post Initialization Complete!");
    }
}