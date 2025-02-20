package dev.amble.allthemagic.spellcasting;

import dev.amble.allthemagic.spellcasting.config.SpellcastingConfig;
import dev.amble.allthemagic.spellcasting.verbal.VerbalSpellCasting;

public class SpellcastingSystem {

    private static SpellcastingConfig config;

    public static void init() {
        config = new SpellcastingConfig();

        SpellRegistry.init();
        VerbalSpellCasting.init();
    }

    public static SpellcastingConfig config() {
        return config;
    }
}
