package com.canoestudio.sofblock.items;

import com.canoestudio.sofblock.SOFBlock;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemCrystalShard extends Item {

    public ItemCrystalShard() {
        setTranslationKey(SOFBlock.MOD_ID + ".crystal_shard");
        setRegistryName("crystal_shard");
        setCreativeTab(CreativeTabs.MATERIALS);

        ModItems.registerItem(this);
    }
}
