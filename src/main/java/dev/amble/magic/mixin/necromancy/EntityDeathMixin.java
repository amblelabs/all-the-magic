package dev.amble.magic.mixin.necromancy;

import dev.amble.magic.core.ATMEntityTypes;
import dev.amble.magic.core.blocks.SoulGateBlock;
import dev.amble.magic.core.entities.DeathWhisperEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(LivingEntity.class)
public class EntityDeathMixin {

    @Inject(method = "onDeath", at = @At("TAIL"))
    private void atm$onDeath(CallbackInfo info) {
        LivingEntity entity = (LivingEntity) (Object) this;
        //if (!entity.isRemoved() && !entity.isDead()) {

            if (entity instanceof DeathWhisperEntity)
                return; // Prevent recursion if the entity is already a Death Whisper

            if (entity instanceof PlayerEntity) return; // Players already respawn.. for now. - Loqor

            System.out.println("I AM DYING");

            World world = entity.getWorld();
            if (world instanceof ServerWorld serverWorld) {
                if (entity.hasAttached(SoulGateBlock.HAS_PASSED_THROUGH_GATE))
                    return; // Prevent summoning if the entity has passed through a Soul Gate

                // Summon a "Will O' the Wisp" entity upon death for necromancy purposes
                DeathWhisperEntity deathWhisperEntity = ATMEntityTypes.DEATH_WHISPER.create(serverWorld);
                Objects.requireNonNull(deathWhisperEntity, "Death Whisper entity was null");
                deathWhisperEntity.setPos(entity.getX(), entity.getY(), entity.getZ());
                serverWorld.spawnEntity(deathWhisperEntity);
            }
        //}
    }
}
