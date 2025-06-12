package dev.amble.magic.core.entities;

import dev.amble.magic.core.ATMItems;
import dev.amble.magic.core.items.WandItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.Objects;

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
        ItemStack stack = player.getMainHandStack();
        if (stack.isOf(ATMItems.WAND)) {
            if (!this.getWorld().isClient()) {
                PigEntity pig = EntityType.PIG.create(this.getWorld());
                Objects.requireNonNull(pig, "entity was null");
                System.out.println(pig + "HELLO");
                pig.setPos(this.getX(), this.getY(), this.getZ());

                this.getWorld().spawnEntity(pig);
                this.discard();
            }
            return ActionResult.SUCCESS;
        }
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
