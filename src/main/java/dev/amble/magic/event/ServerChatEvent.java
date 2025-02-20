package dev.amble.magic.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;

import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.server.network.ServerPlayerEntity;

public class ServerChatEvent {

    public static final Event<ServerMessageEvents.ChatMessage> EVENT = EventFactory.createArrayBacked(ServerMessageEvents.ChatMessage.class, handlers -> (message, sender, params) -> {
        for (ServerMessageEvents.ChatMessage handler : handlers) {
            handler.onChatMessage(message, sender, params);
        }
    });

    @FunctionalInterface
    public interface ChatMessage {
        boolean onChatMessage(SignedMessage message, ServerPlayerEntity sender, MessageType.Parameters params);
    }
}
