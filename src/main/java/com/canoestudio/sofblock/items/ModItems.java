package com.canoestudio.sofblock.items;

import com.canoestudio.sofblock.SOFBlock;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;

public class ModItems {

    public static final Item CRYSTAL_SHARD = new Item()
            .setTranslationKey(SOFBlock.MOD_ID + ".crystal_shard")
            .setRegistryName("crystal_shard")
            .setCreativeTab(CreativeTabs.MATERIALS);

    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(CRYSTAL_SHARD);
    }
}
