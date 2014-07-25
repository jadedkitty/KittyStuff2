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
	public static ToolMaterial gemMaterial = EnumHelper.addToolMaterial("Kitty Gem Material", 10, 0, 10000000000000000000.0F, 0, 1);
    public static final itemKittyStuff2 kittyGem = new itemKittyGem();
    public static final Item kittyClaws = new kittyClaws(gemMaterial);
    public static final Item warmMilk = new warmMilk();
    public static final Item kittyClaw = new itemKittyClaw();

    public static void init()
    {
        GameRegistry.registerItem(kittyGem, "kittyGem");
        GameRegistry.registerItem(kittyClaws, "kittyClaws");
        GameRegistry.registerItem(warmMilk, "warmMilk");
        GameRegistry.registerItem(kittyClaw, "itemkittyClaw");
    }
}
