package com.canoestudio.sofblock.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {

    public static final List<Block> BLOCKS = new ArrayList<>();
    public static final List<Item> BLOCKITEMS = new ArrayList<>();

    public static final BlockPrimordialCrystal PRIMORDIAL_AER_CRYSTAL = new BlockPrimordialCrystal(EnumPrimalAspect.AER);
    public static final BlockPrimordialCrystal PRIMORDIAL_AQUA_CRYSTAL = new BlockPrimordialCrystal(EnumPrimalAspect.AQUA);
    public static final BlockPrimordialCrystal PRIMORDIAL_IGNIS_CRYSTAL = new BlockPrimordialCrystal(EnumPrimalAspect.IGNIS);
    public static final BlockPrimordialCrystal PRIMORDIAL_ORDO_CRYSTAL = new BlockPrimordialCrystal(EnumPrimalAspect.ORDO);
    public static final BlockPrimordialCrystal PRIMORDIAL_PERDITIO_CRYSTAL = new BlockPrimordialCrystal(EnumPrimalAspect.PERDITIO);
    public static final BlockPrimordialCrystal PRIMORDIAL_TERRA_CRYSTAL = new BlockPrimordialCrystal(EnumPrimalAspect.TERRA);

    public static void registerBlock(Block block) {
        BLOCKS.add(block);
        BLOCKITEMS.add(new ItemBlock(block).setRegistryName(block.getRegistryName()));
    }

    public static BlockPrimordialCrystal getPrimordialCrystal(EnumPrimalAspect aspect) {
        switch (aspect) {
            case AER:      return PRIMORDIAL_AER_CRYSTAL;
            case AQUA:     return PRIMORDIAL_AQUA_CRYSTAL;
            case IGNIS:    return PRIMORDIAL_IGNIS_CRYSTAL;
            case ORDO:     return PRIMORDIAL_ORDO_CRYSTAL;
            case PERDITIO: return PRIMORDIAL_PERDITIO_CRYSTAL;
            case TERRA:    return PRIMORDIAL_TERRA_CRYSTAL;
            default:       return PRIMORDIAL_AER_CRYSTAL;
        }
    }
}
