package com.jadedkitty.kittyStuff2.init;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;

import com.jadedkitty.kittyStuff2.reference.Reference;
import com.jadedkitty.kittyStuff2.item.*;
import net.minecraftforge.common.util.EnumHelper;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class modItems
{
	public static ToolMaterial gemMaterial = EnumHelper.addToolMaterial("Kitty Gem Material", 10, 0, 100000000000.0F, 1000000000, 1);
    public static itemKittyStuff2 kittyGem = new itemKittyGem();
    public static Item kittyClaws = new kittyClaws(gemMaterial);
    public static Item warmMilk = new warmMilk();
    public static Item kittyClaw = new itemKittyClaw();
    public static Item pasteurizedMilk = new pasteurizedMilk();

    public static void init()
    {
        kittyGem = new itemKittyGem();
        kittyClaws = new kittyClaws(gemMaterial);
        warmMilk = new warmMilk();
        kittyClaw = new itemKittyClaw();
        pasteurizedMilk = new pasteurizedMilk();
    }
    
    public static void register()
    {
        GameRegistry.registerItem(kittyGem, "kittyGem");
        GameRegistry.registerItem(kittyClaws, "kittyClaws");
        GameRegistry.registerItem(warmMilk, "warmMilk");
        GameRegistry.registerItem(kittyClaw, "itemkittyClaw");
        GameRegistry.registerItem(pasteurizedMilk, "pasteurizedMilk");
    }
}
