package dev.drtheo.allthemagic.spellcasting.spell.impl;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

import dev.drtheo.allthemagic.AllTheMagic;
import dev.drtheo.allthemagic.spellcasting.SpellContext;
import dev.drtheo.allthemagic.spellcasting.spell.Spell;

public class BlindSpell extends Spell {

    private static final VerbalComponent VERBAL = new VerbalComponent(
            "STI KALY!"
    );

    public BlindSpell() {
        super(AllTheMagic.id("blind"));
    }

    @Override
    public boolean canCast(SpellContext context, double integrity) {
        return super.canCast(context, integrity) && context.target().isEntity();
    }

    @Override
    public void cast(SpellContext context, double integrity) {
        int maxDuration = 60;
        int duration = (int) (maxDuration * integrity);
        int amplifier = (int) ((integrity - this.threshold) * 3);

        Entity target = context.target().asEntity().entity();

        if (target instanceof LivingEntity living)
            living.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS,
                    duration, amplifier));
    }

    @Override
    public VerbalComponent verbal() {
        return VERBAL;
    }
}
