package dev.drtheo.allthemagic.spellcasting.spell.impl;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.util.math.Vec3d;

import dev.drtheo.allthemagic.AllTheMagic;
import dev.drtheo.allthemagic.spellcasting.SpellContext;
import dev.drtheo.allthemagic.spellcasting.spell.Spell;

public class LightningSpell extends Spell {

    private static final VerbalComponent VERBAL = new VerbalComponent(
            "P'WAH, UNLIM'TED P'WAH"
    );

    public LightningSpell() {
        super(AllTheMagic.id("lightning"));
    }

    @Override
    public boolean canCast(SpellContext context, double integrity) {
        return super.canCast(context, integrity) && !context.target().isSelf();
    }

    @Override
    public void cast(SpellContext context, double integrity) {
        Vec3d target = context.target().blockPos().toCenterPos();
        int amount = (int) ((integrity - this.threshold) * 5);

        for (int i = 0; i < amount; i++) {
            LightningEntity lightning = new LightningEntity(
                    EntityType.LIGHTNING_BOLT, context.caster().getCasterWorld()
            );

            lightning.setPos(target.getX(), target.getY(), target.getZ());
            context.caster().getCasterWorld().spawnEntity(lightning);
        }
    }

    @Override
    public VerbalComponent verbal() {
        return VERBAL;
    }
}
