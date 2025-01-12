package dev.drtheo.allthemagic.spellcasting;

import dev.drtheo.allthemagic.spellcasting.config.SpellcastingConfig;
import dev.drtheo.allthemagic.spellcasting.verbal.VerbalSpellCasting;

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
