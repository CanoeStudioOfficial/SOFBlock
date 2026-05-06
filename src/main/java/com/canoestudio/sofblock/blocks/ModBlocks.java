package com.canoestudio.sofblock.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;

public class ModBlocks {

    public static final BlockCrystalCluster CRYSTAL_CLUSTER = new BlockCrystalCluster();

    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(CRYSTAL_CLUSTER);
    }

    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemBlock(CRYSTAL_CLUSTER).setRegistryName(CRYSTAL_CLUSTER.getRegistryName()).setTranslationKey(CRYSTAL_CLUSTER.getTranslationKey()));
    }
}
