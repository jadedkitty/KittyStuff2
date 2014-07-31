package com.jadedkitty.kittyStuff2.init;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.World;

public class PasteurizerRecipes
{
    private static final PasteurizerRecipes smeltingBase = new PasteurizerRecipes();
    /** The list of smelting results. */
    private Map smeltingList = new HashMap();
    private Map experienceList = new HashMap();
    public static PasteurizerRecipes smelting()
    {
        return smeltingBase;
    }

    private PasteurizerRecipes()
    {

    }

    public void addSmelting(Block input, ItemStack result, float xp)
    {
        this.addSmelting(Item.getItemFromBlock(input), result, xp);
    }

    public void addSmelting(Item input, ItemStack result, float xp)
    {
        this.addSmelting(new ItemStack(input, 1, 32767), result, xp);
    }

    public void addSmelting(ItemStack input, ItemStack result, float xp)
    {
        this.smeltingList.put(input, result);
        this.experienceList.put(result, Float.valueOf(xp));
    }

    /**
     * Returns the smelting result of an item.
     */
    public ItemStack getSmeltingResult(ItemStack stack)
    {
        Iterator iterator = this.smeltingList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Entry)iterator.next();
        }
        while (!this.func_151397_a(stack, (ItemStack)entry.getKey()));

        return (ItemStack)entry.getValue();
    }

    private boolean func_151397_a(ItemStack p_151397_1_, ItemStack p_151397_2_)
    {
        return p_151397_2_.getItem() == p_151397_1_.getItem() && (p_151397_2_.getItemDamage() == 32767 || p_151397_2_.getItemDamage() == p_151397_1_.getItemDamage());
    }

    public Map getSmeltingList()
    {
        return this.smeltingList;
    }

    public float func_151398_b(ItemStack stack)
    {
        float ret = stack.getItem().getSmeltingExperience(stack);
        if (ret != -1) return ret;

        Iterator iterator = this.experienceList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return 0.0F;
            }

            entry = (Entry)iterator.next();
        }
        while (!this.func_151397_a(stack, (ItemStack)entry.getKey()));

        return ((Float)entry.getValue()).floatValue();
    }

    public static void updateBlockState(boolean b, World worldObj, int xCoord, int yCoord, int zCoord) {
    }
}