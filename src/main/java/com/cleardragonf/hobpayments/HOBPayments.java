package com.cleardragonf.hobpayments;

import com.cleardragonf.hobpayments.commands.EconomyCommands;
import com.cleardragonf.hobpayments.economy.EconomyManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import com.cleardragonf.hobpayments.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.cleardragonf.hobpayments.config.ModConfig.COMMON_SPEC;

@Mod(HOBPayments.MODID)
public class HOBPayments {
    public static final String MODID = "hobpayments";

    // Static instance of EconomyManager
    public static EconomyManager economyManager;

    public HOBPayments() {
        // Register the common setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        // Register server starting event to the server event bus
        MinecraftForge.EVENT_BUS.addListener(this::onServerStarting);

        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, COMMON_SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Initialization code
    }

    private void onServerStarting(ServerStartingEvent event) {
        // Initialize EconomyManager when the server starts
        economyManager = new EconomyManager();

        // Register commands
        EconomyCommands.register(event.getServer().getCommands().getDispatcher());
    }
}
