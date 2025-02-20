package dev.amble.allthemagic.spellcasting.verbal;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.LivingEntity;

import dev.amble.allthemagic.spellcasting.SpellContext;
import dev.amble.allthemagic.spellcasting.SpellQueue;
import dev.amble.allthemagic.spellcasting.SpellRegistry;
import dev.amble.allthemagic.spellcasting.SpellcastingSystem;
import dev.amble.allthemagic.spellcasting.spell.Spell;

public class Cantrip {

    private final List<String> parts = new ArrayList<>();

    private int cursor;
    private int lastCursor;

    public Cantrip(String message) {
        for (String part : message.split(SpellcastingSystem.config().verbalSpellSeparator)) {
            parts.add(part.strip());
        }
    }

    public void consume() {
        this.cursor++;
    }

    public boolean hasNext() {
        return this.cursor < this.parts.size();
    }

    public String get() {
        return parts.get(this.cursor);
    }

    public <T extends LivingEntity & SpellContext.Spellcaster> SpellQueue cast(SpellContext.Builder<T> builder) {
        SpellQueue queue = new SpellQueue();
        int lastSuccess = this.cursor;

        for (int i = 0; i < SpellRegistry.REGISTRY.size(); i++) {
            Spell spell = SpellRegistry.REGISTRY.get(i);

            Spell.VerbalComponent.Score score = spell.verbal().score(this);

            if (!score.success()) {
                this.cursor = this.lastCursor;
                continue;
            }

            this.lastCursor = this.cursor;
            SpellContext ctx = builder.build(spell);

            System.out.println("Spell: " + spell.id() + "; integrity: " + score.integrity());
            if (spell.canCast(ctx, score.integrity()))
                queue.add(ctx, score.integrity(), spell);

            if (!this.hasNext())
                return queue;
        }

        return queue;
    }
}
