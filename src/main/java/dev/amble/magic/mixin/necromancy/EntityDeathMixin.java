package dev.amble.magic.mixin.necromancy;

import dev.amble.magic.core.ATMEntityTypes;
import dev.amble.magic.core.entities.DeathWhisperEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class EntityDeathMixin {

    @Inject(method = "onDeath", at = @At("HEAD"))
    private void atm$onDeath(CallbackInfo info) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (entity instanceof DeathWhisperEntity) return; // Prevent recursion if the entity is already a Death Whisper

        if (entity instanceof PlayerEntity) return; // Players already respawn.. for now. - Loqor

        if (entity.getWorld().isClient()) {
            return; // Only handle on the server side
        }

        // Summon a "Will O' the Wisp" entity upon death for necromancy purposes
        DeathWhisperEntity deathWhisperEntity = new DeathWhisperEntity(ATMEntityTypes.DEATH_WHISPER, entity.getWorld());
        deathWhisperEntity.setPos(entity.getX(), entity.getY(), entity.getZ());
        entity.getWorld().spawnEntity(deathWhisperEntity);
    }
}
