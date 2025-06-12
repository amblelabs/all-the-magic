package dev.amble.magic.core;

import dev.amble.magic.AllTheMagic;
import dev.amble.magic.core.entities.DeathWhisperEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

// Register the entity types here. - Loqor
public class ATMEntityTypes {

    public static final EntityType<DeathWhisperEntity> DEATH_WHISPER = register(
            AllTheMagic.id("death_whisper"),
            EntityType.Builder.create(DeathWhisperEntity::new, SpawnGroup.MISC)
                    .setDimensions(0.5f, 0.5f)
                    .build("death_whisper")
    );

    private static <T extends Entity> EntityType<T> register(Identifier name, EntityType<T> entityType) {
        return Registry.register(Registries.ENTITY_TYPE, name, entityType);
    }

    public static void initialize() {

    }
}
