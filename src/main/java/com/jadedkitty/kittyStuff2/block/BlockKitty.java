package com.jadedkitty.kittyStuff2.block;

import com.jadedkitty.kittyStuff2.KittyStuff2;
import com.jadedkitty.kittyStuff2.reference.Reference;
import net.minecraft.block.material.Material;

public class BlockKitty extends BlockKittyStuff
{
    public BlockKitty()
    {
        super();
        this.setBlockName("kittyblock");
        this.setBlockTextureName("kittyblock");
        this.setCreativeTab(KittyStuff2.tabTools);
        this.setHardness(1);
        this.blockMaterial.isToolNotRequired();
    }
}
