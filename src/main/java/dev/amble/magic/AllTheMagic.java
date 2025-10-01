package dev.amble.magic;

import dev.amble.magic.core.ATMBlocks;
import dev.amble.magic.core.ATMEntityTypes;
import dev.amble.magic.core.ATMItems;
import dev.amble.magic.core.entities.DeathWhisperEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;

import dev.amble.magic.spellcasting.SpellcastingSystem;

public class AllTheMagic implements ModInitializer {

    public static final String MOD_ID = "all-the-magic";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static MinecraftServer server;

    @Override
    public void onInitialize() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> AllTheMagic.server = server);
        ServerLifecycleEvents.SERVER_STOPPING.register(server -> AllTheMagic.server = null);
        SpellcastingSystem.init();
        registerEntityAttributes();

        // Register Blocks, Entities, and Items
        ATMBlocks.initialize();
        ATMEntityTypes.initialize();
        ATMItems.initialize();
    }

    public void registerEntityAttributes() {
        FabricDefaultAttributeRegistry.register(ATMEntityTypes.DEATH_WHISPER, DeathWhisperEntity.createLivingAttributes());
    }

    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }

    public static Random random() {
        return server.getOverworld().random;
    }
}
