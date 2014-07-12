package com.jadedkitty.kittyStuff2.utility;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;

public class CreativeTab extends net.minecraft.creativetab.CreativeTabs
{
    private Item tabIconItem;
    private String tabLabel;
    public CreativeTab(int position, String tabID,Item tabItem,String label)
    {
        super(position, tabID);
        tabIconItem = tabItem;
        tabLabel=label;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem()
    {
        return tabIconItem;
    }
    public String getTranslatedTabLabel()
    {
        return tabLabel;
    }
    public void setTabIconItem(Item tabItem)
    {
        tabIconItem = tabItem;
    }
}