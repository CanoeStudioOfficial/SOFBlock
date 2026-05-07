package com.canoestudio.sofblock.util.proxy;

import com.canoestudio.sofblock.util.ThaumcraftCompat;
import com.canoestudio.sofblock.world.WorldGenPrimordialCrystal;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thaumcraft.api.aspects.AspectRegistryEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new AspectRegistryHandler());
    }

    public void init(FMLInitializationEvent event) {
        GameRegistry.registerWorldGenerator(new WorldGenPrimordialCrystal(), 10);
    }

    public void postInit(FMLPostInitializationEvent event) {

    }

    public static class AspectRegistryHandler {
        @net.minecraftforge.fml.common.eventhandler.SubscribeEvent
        public void onAspectRegistry(AspectRegistryEvent event) {
            ThaumcraftCompat.registerAspects(event);
        }
    }
}
