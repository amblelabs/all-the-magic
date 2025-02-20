package dev.drtheo.allthemagic;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;

import dev.drtheo.allthemagic.spellcasting.SpellcastingSystem;

public class AllTheMagic implements ModInitializer {

    public static final String MOD_ID = "all-the-magic";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static MinecraftServer server;

    @Override
    public void onInitialize() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> AllTheMagic.server = server);
        ServerLifecycleEvents.SERVER_STOPPING.register(server -> AllTheMagic.server = null);
        SpellcastingSystem.init();
    }

    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }

    public static Random random() {
        return server.getOverworld().random;
    }
}
