package com.canoestudio.sofblock.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {

    public static final List<Block> BLOCKS = new ArrayList<>();
    public static final List<Item> BLOCKITEMS = new ArrayList<>();

    public static final BlockCrystalCluster CRYSTAL_CLUSTER = new BlockCrystalCluster();

    public static void registerBlock(Block block) {
        BLOCKS.add(block);
        BLOCKITEMS.add(new ItemBlock(block).setRegistryName(block.getRegistryName()));
    }
}
