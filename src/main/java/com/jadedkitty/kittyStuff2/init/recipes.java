package com.jadedkitty.kittyStuff2.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class recipes
{
   public static void init()
   {
       GameRegistry.addRecipe(new ItemStack(modItems.kittyClaws), " c ", "ckc", " s ", 'c', new ItemStack(modItems.kittyClaw),'k',new ItemStack(modItems.kittyGem),'s',new ItemStack(Items.diamond_sword));
       GameRegistry.addRecipe(new ItemStack(modblocks.kittyblock), "kkk", "kkk", "kkk", 'k',new ItemStack(modItems.kittyGem));
       GameRegistry.addShapelessRecipe(new ItemStack(modItems.kittyGem), new ItemStack(Items.diamond), new ItemStack(Items.dye, 1, 5));
       GameRegistry.addRecipe(new ItemStack(modblocks.kittyblock), "www", "wbw", "www",'b',new ItemStack(Blocks.diamond_block), 'w',new ItemStack(Blocks.wool, 1, 9));
       GameRegistry.addSmelting(modItems.pasteurizedMilk, new ItemStack(modItems.warmMilk), 0);
       PasteurizerRecipes.smelting().addSmelting(new ItemStack(Items.milk_bucket,1),new ItemStack(modItems.pasteurizedMilk,1),1.0F);
       GameRegistry.addRecipe(new ItemStack(modblocks.pasteurizerIdle), "b b", "b b", "bkb", 'k',new ItemStack(modItems.kittyGem), 'b',new ItemStack(Items.bucket));
   }
}
