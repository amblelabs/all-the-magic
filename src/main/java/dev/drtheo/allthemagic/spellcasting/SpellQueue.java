package dev.drtheo.allthemagic.spellcasting;

import dev.drtheo.allthemagic.spellcasting.spell.Spell;

import java.util.ArrayList;
import java.util.List;

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
