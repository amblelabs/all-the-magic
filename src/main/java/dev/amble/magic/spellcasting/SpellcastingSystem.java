package dev.amble.magic.spellcasting;

import dev.amble.magic.spellcasting.config.SpellcastingConfig;
import dev.amble.magic.spellcasting.verbal.VerbalSpellCasting;

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
