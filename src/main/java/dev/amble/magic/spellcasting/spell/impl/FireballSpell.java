package dev.amble.magic.spellcasting.spell.impl;

import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.util.math.Vec3d;

import dev.amble.magic.AllTheMagic;
import dev.amble.magic.spellcasting.SpellContext;
import dev.amble.magic.spellcasting.spell.Spell;

public class FireballSpell extends Spell {

    private static final VerbalComponent VERBAL = new VerbalComponent(
            "ONI'SOMA!"
    );

    public FireballSpell() {
        super(AllTheMagic.id("fireball"));
    }

    @Override
    public boolean canCast(SpellContext context, double integrity) {
        return super.canCast(context, integrity) && !context.target().isSelf();
    }

    @Override
    public void cast(SpellContext context, double integrity) {
        Vec3d from = context.caster().getCasterPos();
        Vec3d target = context.target().pos();

        Vec3d vec3d = context.caster().asEntity().getRotationVec(1.0F);

        double f = target.getX() - (from.getX() + vec3d.x * 4.0);
        double g = target.getY() - from.getY();
        double h = target.getZ() - (from.getZ() + vec3d.z * 4.0);

        int power = (int) ((integrity - this.threshold) * 8);

        FireballEntity fireball = new FireballEntity(
                context.world(), context.caster().asPlayer(), f, g, h, power
        );

        fireball.setPosition(from.getX() + vec3d.x * 4.0, from.getY() + 0.5, from.getZ() + vec3d.z * 4.0);
        context.world().spawnEntity(fireball);
    }

    @Override
    public VerbalComponent verbal() {
        return VERBAL;
    }
}
