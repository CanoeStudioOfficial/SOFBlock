package com.canoestudio.sofblock.blocks;

import java.util.Random;

import thaumcraft.api.aspects.Aspect;

public enum EnumPrimalAspect {

    AER(0, "aer"),
    AQUA(1, "aqua"),
    IGNIS(2, "ignis"),
    ORDO(3, "ordo"),
    PERDITIO(4, "perditio"),
    TERRA(5, "terra");

    private static final EnumPrimalAspect[] META_LOOKUP = new EnumPrimalAspect[values().length];

    static {
        for (EnumPrimalAspect aspect : values()) {
            META_LOOKUP[aspect.meta] = aspect;
        }
    }

    private final int meta;
    private final String name;

    EnumPrimalAspect(int meta, String name) {
        this.meta = meta;
        this.name = name;
    }

    public int getMeta() {
        return meta;
    }

    public String getName() {
        return name;
    }

    public static EnumPrimalAspect byMeta(int meta) {
        if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
        }
        return META_LOOKUP[meta];
    }

    public Aspect getThaumcraftAspect() {
        switch (this) {
            case AER:     return Aspect.AIR;
            case AQUA:    return Aspect.WATER;
            case IGNIS:   return Aspect.FIRE;
            case ORDO:    return Aspect.ORDER;
            case PERDITIO:return Aspect.ENTROPY;
            case TERRA:   return Aspect.EARTH;
            default:      return Aspect.AIR;
        }
    }

    public static EnumPrimalAspect getRandom(Random rand) {
        return values()[rand.nextInt(values().length)];
    }
}
