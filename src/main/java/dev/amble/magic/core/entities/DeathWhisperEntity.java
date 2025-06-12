package dev.amble.magic.core.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.Collections;

public class DeathWhisperEntity extends LivingEntity {
    // TODO implement a copy of the entity the Death Whisper spawned from with tracked data and respawn it on usage of a wand on the Death Whisper. - Loqor
    public DeathWhisperEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public Iterable<ItemStack> getArmorItems() {
        return Collections::emptyIterator;
    }

    @Override
    public ItemStack getEquippedStack(EquipmentSlot slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public void equipStack(EquipmentSlot slot, ItemStack stack) {}

    @Override
    public Arm getMainArm() {
        return Arm.RIGHT;
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        return super.interact(player, hand);
    }

    @Override
    public boolean isPushedByFluids() {
        return false;
    }

    @Override
    public void onPlayerCollision(PlayerEntity player) {
        // Prevent player collision logic
    }
}
