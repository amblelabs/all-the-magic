package dev.amble.magic.core;

import dev.amble.magic.AllTheMagic;
import dev.amble.magic.core.items.WandItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

// register the items here. - Loqor
public class ATMItems {

    public static final Item WAND = register(AllTheMagic.id("wand"),
            new WandItem(new Item.Settings().maxCount(1)));

    public static Item register(Identifier id, Item item) {
        return Registry.register(Registries.ITEM, id, item);
    }

    public static void initialize() {

    }
}
