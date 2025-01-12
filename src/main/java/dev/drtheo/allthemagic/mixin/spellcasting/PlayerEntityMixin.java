package dev.drtheo.allthemagic.mixin.spellcasting;

import dev.drtheo.allthemagic.spellcasting.SpellContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

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
