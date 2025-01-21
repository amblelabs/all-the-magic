package dev.drtheo.allthemagic.mixin.spellcasting.verbal;

import dev.drtheo.allthemagic.spellcasting.SpellcastingSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ChatScreen.class)
public class ChatScreenMixin {

    @Redirect(method = "setChatFromHistory", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/TextFieldWidget;setText(Ljava/lang/String;)V"))
    public void setText(TextFieldWidget instance, String text) {
        if (!text.startsWith(SpellcastingSystem.config().verbalSpellPrefix) && !MinecraftClient.getInstance().player.isCreative())
            instance.setText(text);
    }
}
