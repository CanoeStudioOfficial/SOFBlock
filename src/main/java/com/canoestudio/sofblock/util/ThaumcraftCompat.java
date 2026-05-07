package com.canoestudio.sofblock.util;

import com.canoestudio.sofblock.SOFBlock;
import com.canoestudio.sofblock.blocks.EnumPrimalAspect;
import com.canoestudio.sofblock.blocks.ModBlocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.AspectRegistryEvent;

public class ThaumcraftCompat {

    public static void registerAspects(AspectRegistryEvent event) {
        for (EnumPrimalAspect primal : EnumPrimalAspect.values()) {
            ItemStack stack = new ItemStack(ModBlocks.getPrimordialCrystal(primal));
            AspectList aspects = new AspectList()
                    .add(primal.getThaumcraftAspect(), 10)
                    .add(Aspect.CRYSTAL, 5)
                    .add(Aspect.MAGIC, 3)
                    .add(Aspect.AURA, 2);
            event.register.registerObjectTag(stack, aspects);
            SOFBlock.LOGGER.debug("Registered Thaumcraft aspects for primordial {} crystal", primal.getName());
        }
    }
}
