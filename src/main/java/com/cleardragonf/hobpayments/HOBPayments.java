package com.cleardragonf.hobpayments;

import com.cleardragonf.hobpayments.commands.EconomyCommands;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(HOBPayments.MODID)
public class HOBPayments {
    public static final String MODID = "hobpayments";

    public HOBPayments() {
        // Register the common setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);

        // Register server starting event to the server event bus
        MinecraftForge.EVENT_BUS.addListener(this::onServerStarting);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Initialization code
    }

    private void onServerStarting(ServerStartingEvent event) {
        // Register commands and other server-side setup
        EconomyCommands.register(event.getServer().getCommands().getDispatcher());
    }
}
