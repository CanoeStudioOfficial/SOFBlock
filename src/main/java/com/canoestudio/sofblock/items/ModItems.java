package com.canoestudio.sofblock.items;

import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

    public static final List<Item> ITEMS = new ArrayList<>();

    public static final Item CRYSTAL_SHARD = new ItemCrystalShard();

    public static void registerItem(Item item) {
        ITEMS.add(item);
    }
}
