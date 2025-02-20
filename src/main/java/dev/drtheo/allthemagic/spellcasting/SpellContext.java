package dev.drtheo.allthemagic.spellcasting;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import dev.drtheo.allthemagic.spellcasting.spell.Spell;

public record SpellContext(Spellcaster caster, Target target) {

    public World world() {
        return caster.getCasterWorld();
    }

    public static class Builder<T extends LivingEntity & Spellcaster> {

        private final T caster;

        public Builder(T caster) {
            this.caster = caster;
        }

        @SuppressWarnings("unchecked")
        public static <T extends ServerPlayerEntity & Spellcaster> Builder<T> forPlayer(ServerPlayerEntity player) {
            return new Builder<>((T) player);
        }

        public SpellContext build(Spell spell) {
            return SpellContext.create(spell, this.caster);
        }
    }

    private static <T extends LivingEntity & Spellcaster> SpellContext create(Spell spell, T caster) {
        float range = spell.getRange(caster);
        HitResult hit = ProjectileUtil.getCollision(caster, Entity::canHit, range);

        Target target = switch (hit.getType()) {
            case MISS -> new SelfTarget(caster);
            case BLOCK -> new BlockTarget(((BlockHitResult) hit).getBlockPos());
            case ENTITY -> new EntityTarget(((EntityHitResult) hit).getEntity());
        };

        return new SpellContext(caster, target);
    }

    public interface Spellcaster {
        World getCasterWorld();
        BlockPos getCasterBlockPos();
        Vec3d getCasterPos();

        default boolean isPlayer() {
            return this instanceof PlayerEntity;
        }

        @SuppressWarnings("unchecked")
        default <T extends PlayerEntity & Spellcaster> T asPlayer() {
            return (T) this;
        }

        @SuppressWarnings("unchecked")
        default <T extends Entity & Spellcaster> T asEntity() {
            return (T) this;
        }
    }

    public interface Target {

        default boolean isEntity() {
            return this instanceof EntityTarget;
        }

        default EntityTarget asEntity() {
            return (EntityTarget) this;
        }

        default boolean isBlock() {
            return this instanceof BlockTarget;
        }

        default BlockTarget asBlock() {
            return (BlockTarget) this;
        }

        default boolean isSelf() {
            return this instanceof SelfTarget;
        }

        default SelfTarget asSelf() {
            return (SelfTarget) this;
        }

        BlockPos blockPos();
        Vec3d pos();
    }

    public record EntityTarget(Entity entity) implements Target {

        @Override
        public BlockPos blockPos() {
            return entity.getBlockPos();
        }

        @Override
        public Vec3d pos() {
            return entity.getPos();
        }
    }

    public record SelfTarget(Spellcaster caster) implements Target {

        @Override
        public BlockPos blockPos() {
            return caster.getCasterBlockPos();
        }

        @Override
        public Vec3d pos() {
            return caster.getCasterPos();
        }
    }

    public record BlockTarget(BlockPos blockPos) implements Target {

        @Override
        public Vec3d pos() {
            return blockPos.toCenterPos();
        }
    }
}
