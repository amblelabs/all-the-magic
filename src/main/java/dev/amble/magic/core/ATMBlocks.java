package dev.amble.magic.core;

import dev.amble.magic.AllTheMagic;
import dev.amble.magic.core.blocks.SoulGateBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

// register the blocks here. - Loqor
public class ATMBlocks {

    public static final Block SOUL_GATE = register(AllTheMagic.id("soul_gate"),
            new SoulGateBlock(FabricBlockSettings.create().mapColor(MapColor.BLACK).noCollision()
                    .luminance((state) -> 7)
                    .dropsNothing().pistonBehavior(PistonBehavior.NORMAL)
                    .strength(5.0f, 6.0f)));

    public static Block register(Identifier id, Block block) {
        return Registry.register(Registries.BLOCK, id, block);
    }

    public static void initialize() {

    }
}
