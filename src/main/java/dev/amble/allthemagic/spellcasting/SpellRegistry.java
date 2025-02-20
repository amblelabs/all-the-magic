package dev.amble.allthemagic.spellcasting;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;

import net.minecraft.registry.*;

import dev.amble.allthemagic.AllTheMagic;
import dev.amble.allthemagic.spellcasting.spell.Spell;
import dev.amble.allthemagic.spellcasting.spell.impl.BlindSpell;
import dev.amble.allthemagic.spellcasting.spell.impl.FireballSpell;
import dev.amble.allthemagic.spellcasting.spell.impl.LightningSpell;
import dev.amble.allthemagic.spellcasting.spell.impl.SingularitySpell;

public class SpellRegistry {

    public static final RegistryKey<Registry<Spell>> REGISTRY_KEY = RegistryKey.ofRegistry(
            AllTheMagic.id("spell"));

    public static Registry<Spell> REGISTRY = FabricRegistryBuilder.createSimple(REGISTRY_KEY)
            .attribute(RegistryAttribute.SYNCED).buildAndRegister();


    public static void init() { }

    public static final Spell SINGULARITY = register(new SingularitySpell());
    public static final Spell BLIND = register(new BlindSpell());
    public static final Spell FIREBALL = register(new FireballSpell());
    public static final Spell LIGHTNING = register(new LightningSpell());

    private static Spell register(Spell spell) {
        return Registry.register(REGISTRY, spell.id(), spell);
    }
}
