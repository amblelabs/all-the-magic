package dev.drtheo.allthemagic.spellcasting.verbal;

import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

import dev.drtheo.allthemagic.AllTheMagic;
import dev.drtheo.allthemagic.spellcasting.SpellContext;
import dev.drtheo.allthemagic.spellcasting.SpellQueue;
import dev.drtheo.allthemagic.spellcasting.SpellcastingSystem;

public class VerbalSpellCasting {

    public static void init() {
        ServerMessageEvents.ALLOW_CHAT_MESSAGE.register((message, sender, params) -> {
            if (!(message.getContent().getContent() instanceof LiteralTextContent literal))
                return true;

            String text = literal.string();
            String prefix = SpellcastingSystem.config().verbalSpellPrefix;

            if (!text.startsWith(prefix))
                return true;

            text = text.substring(prefix.length());

            Cantrip cantrip = new Cantrip(text);
            SpellQueue queue = cantrip.cast(
                    SpellContext.Builder.forPlayer(sender)
            );

            if (!queue.castAny()) {
                sender.sendMessage(createSpellFailText(), true);
                sender.playSound(SoundEvents.BLOCK_CHAIN_BREAK, SoundCategory.PLAYERS, 1, 1);
                return false;
            }

            sender.getServer().executeSync(queue::cast);

            for (ServerPlayerEntity player : PlayerLookup.all(sender.getServer())) {
                player.sendMessage(createSpellText(sender.getDisplayName(), text));
            }

            return false;
        });
    }

    private static final Text PREFIX = Text.literal("e")
            .setStyle(Style.EMPTY.withObfuscated(true));

    private static Text createSpellText(Text sender, String message) {
        Text spell = Text.empty().append(PREFIX).append(
                Text.literal(message).setStyle(Style.EMPTY.withBold(true)
                        .withItalic(true).withUnderline(true)
                ).append(PREFIX));

        int variant = AllTheMagic.random().nextBetween(0, 4);
        return Text.translatable("text.all-the-magic.casts." + variant,
                sender, spell);
    }

    private static Text createSpellFailText() {
        int variant = AllTheMagic.random().nextBetween(0, 2);
        return Text.translatable("text.all-the-magic.casts.fail." + variant);
    }
}
