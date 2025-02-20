package dev.amble.allthemagic.spellcasting;

import java.util.ArrayList;
import java.util.List;

import dev.amble.allthemagic.spellcasting.spell.Spell;

public class SpellQueue {

    // TODO: replace with something not lambda
    private List<Runnable> runnables;

    public void add(SpellContext context, double integrity, Spell spell) {
        if (this.runnables == null)
            this.runnables = new ArrayList<>();

        this.runnables.add(() -> spell.cast(context, integrity));
    }

    public boolean castAny() {
        return this.runnables != null && !this.runnables.isEmpty();
    }

    public void cast() {
        for (Runnable runnable : runnables) {
            runnable.run();
        }
    }
}
