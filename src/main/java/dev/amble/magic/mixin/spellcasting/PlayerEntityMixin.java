package dev.amble.magic.mixin.spellcasting;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import dev.amble.magic.spellcasting.SpellContext;

@Mixin(PlayerEntity.class)
@SuppressWarnings("AddedMixinMembersNamePattern")
public class PlayerEntityMixin implements SpellContext.Spellcaster {

    @Override
    public World getCasterWorld() {
        return ((PlayerEntity) (Object) this).getWorld();
    }

    @Override
    public BlockPos getCasterBlockPos() {
        return ((PlayerEntity) (Object) this).getBlockPos();
    }

    @Override
    public Vec3d getCasterPos() {
        return ((PlayerEntity) (Object) this).getPos();
    }
}
