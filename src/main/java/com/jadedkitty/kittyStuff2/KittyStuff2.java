package com.jadedkitty.kittyStuff2;

import com.jadedkitty.kittyStuff2.handler.ConfigurationHandler;
import com.jadedkitty.kittyStuff2.handler.Drops;
import com.jadedkitty.kittyStuff2.handler.GuiHandler;
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
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

@Mod(modid = Reference.MOD_ID, name=Reference.MOD_NAME, version=Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class KittyStuff2 {

    @Mod.Instance(Reference.MOD_ID)
    public static KittyStuff2 instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;


    public static CreativeTabs tabTools = new CreativeTab(CreativeTabs.getNextID(), "KittyStuff", Items.diamond, "Kitty Stuff 2");

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
        LogHelper.info("Pre Initialization Complete!");
        MinecraftForge.EVENT_BUS.register(new Drops());

        modItems.init();
        modItems.register();

        modblocks.init();
        modblocks.register();
        modblocks.registerTileEntities();

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
        ((CreativeTab) tabTools).setTabIconItem(modItems.kittyGem);
        LogHelper.info("Initialization Complete!");
        recipes.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        LogHelper.info("Post Initialization Complete!");
    }


}

