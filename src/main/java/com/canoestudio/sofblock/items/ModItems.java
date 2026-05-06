package com.canoestudio.sofblock.items;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;

public class ModItems {

    public static final Item CRYSTAL_SHARD = new Item().setTranslationKey("crystal_shard").setRegistryName("crystal_shard");

    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(CRYSTAL_SHARD);
    }
}
