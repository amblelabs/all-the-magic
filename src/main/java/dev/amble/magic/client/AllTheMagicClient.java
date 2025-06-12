package dev.amble.magic.client;

import dev.amble.magic.client.rendering.DeathWhisperRenderer;
import dev.amble.magic.core.ATMEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class AllTheMagicClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        registerEntityRenderers();
    }

    public void registerEntityRenderers() {
        EntityRendererRegistry.register(ATMEntityTypes.DEATH_WHISPER, DeathWhisperRenderer::new);
    }
}
