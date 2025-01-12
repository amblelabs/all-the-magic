package dev.drtheo.allthemagic.spellcasting.spell.impl;

import dev.drtheo.allthemagic.AllTheMagic;
import dev.drtheo.allthemagic.spellcasting.SpellContext;
import dev.drtheo.allthemagic.spellcasting.spell.Spell;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SingularitySpell extends Spell {

    private static final VerbalComponent VERBAL = new VerbalComponent(
            "lets talk about our lord and savior: jesus christ",
            "hallelujah!"
    );

    public SingularitySpell() {
        super(AllTheMagic.id("singularity"));
    }

    @Override
    public boolean canCast(SpellContext context, double integrity) {
        return integrity > 0.4;
    }

    @Override
    public void cast(SpellContext ctx, double integrity) {
        SpellContext.Spellcaster caster = ctx.caster();
        Vec3d pos = caster.getCasterBlockPos().toCenterPos();

        caster.getCasterWorld().createExplosion(
                caster.asPlayer(), pos.getX(), pos.getY(), pos.getZ(),
                (float) (integrity * 16f), World.ExplosionSourceType.TNT
        );
    }

    @Override
    public VerbalComponent verbal() {
        return VERBAL;
    }
}
