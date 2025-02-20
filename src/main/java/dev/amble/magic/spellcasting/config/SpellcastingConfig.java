package dev.amble.magic.spellcasting.config;

import java.util.function.Supplier;

import net.ricecode.similarity.*;

public class SpellcastingConfig {

    public StringSimilarityAlgorithm stringSimilarity = StringSimilarityAlgorithm.JAROWINKLER;
    public String verbalSpellSeparator = "[.]";
    public String verbalSpellPrefix = "\\";

    public enum StringSimilarityAlgorithm {
        JAROWINKLER(JaroWinklerStrategy::new),
        JARO(JaroStrategy::new),
        LEVENSHTEIN(LevenshteinDistanceStrategy::new),
        DICE(DiceCoefficientStrategy::new);

        private final Supplier<SimilarityStrategy> supplier;
        private StringSimilarityService cache;

        StringSimilarityAlgorithm(Supplier<SimilarityStrategy> supplier) {
            this.supplier = supplier;
        }

        public StringSimilarityService get() {
            if (this.cache != null)
                return cache;

            this.cache = new StringSimilarityServiceImpl(this.supplier.get());
            return cache;
        }
    }
}
