package dev.drtheo.allthemagic.spellcasting.spell.impl;

import dev.drtheo.allthemagic.AllTheMagic;
import dev.drtheo.allthemagic.spellcasting.SpellContext;
import dev.drtheo.allthemagic.spellcasting.spell.Spell;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class FreezeSpell extends Spell {

    private static final VerbalComponent VERBAL = new VerbalComponent(
            "GLACI'O FRI'GUS"
    );

    public FreezeSpell() {
        super(AllTheMagic.id("freeze"));
    }

    @Override
    public boolean canCast(SpellContext context, double integrity) {
        return super.canCast(context, integrity) && context.target().isEntity();
    }

    @Override
    public void cast(SpellContext context, double integrity) {
        double power = integrity - this.threshold;

        Entity target = context.target().asEntity().entity();

        target.setFrozenTicks((int) (power * 240));
        target.damage(target.getEntityWorld().getDamageSources().freeze(), (float) (power * 5));

        if (target instanceof LivingEntity living)
            living.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, (int) (power * 100)));
    }

    @Override
    public VerbalComponent verbal() {
        return VERBAL;
    }
}
