package dev.amble.magic.client;

import dev.amble.magic.client.rendering.DeathWhisperRenderer;
import dev.amble.magic.core.ATMBlocks;
import dev.amble.magic.core.ATMEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

public class AllTheMagicClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        registerEntityRenderers();
        registerBlockRenderTypes();
    }

    public void registerEntityRenderers() {
        EntityRendererRegistry.register(ATMEntityTypes.DEATH_WHISPER, DeathWhisperRenderer::new);
    }

    public void registerBlockRenderTypes() {
        BlockRenderLayerMap map = BlockRenderLayerMap.INSTANCE;
        map.putBlock(ATMBlocks.SOUL_GATE, RenderLayer.getTranslucent());
    }
}
