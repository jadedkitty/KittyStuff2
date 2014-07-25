package com.jadedkitty.kittyStuff2.init;

import com.jadedkitty.kittyStuff2.item.warmMilk;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class recipes
{
   public static void init()
   {
       GameRegistry.addRecipe(new ItemStack(modItems.kittyClaws), " c ", "ckc", " s ", 'c', new ItemStack(modItems.kittyClaw),'k',new ItemStack(modItems.kittyGem),'s',new ItemStack(Items.diamond_sword));
       GameRegistry.addShapelessRecipe(new ItemStack(modItems.kittyClaw), new ItemStack(modItems.kittyGem));
       GameRegistry.addRecipe(new ItemStack(modblocks.kittyblock), "kkk", "kkk", "kkk", 'k',new ItemStack(modItems.kittyGem));
       GameRegistry.addShapelessRecipe(new ItemStack(modItems.kittyGem), new ItemStack(Items.diamond), new ItemStack(Items.dye, 1, 5));
       GameRegistry.addRecipe(new ItemStack(modblocks.kittyblock), "www", "wbw", "www",'b',new ItemStack(Blocks.diamond_block), 'w',new ItemStack(Blocks.wool, 1, 9));
       GameRegistry.addSmelting(Items.milk_bucket, new ItemStack(modItems.warmMilk), 0);
   }
}
