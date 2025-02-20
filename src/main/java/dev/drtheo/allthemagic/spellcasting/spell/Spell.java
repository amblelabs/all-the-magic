package dev.drtheo.allthemagic.spellcasting.spell;

import net.ricecode.similarity.StringSimilarityService;

import net.minecraft.util.Identifier;

import dev.drtheo.allthemagic.spellcasting.SpellContext;
import dev.drtheo.allthemagic.spellcasting.SpellcastingSystem;
import dev.drtheo.allthemagic.spellcasting.verbal.Cantrip;

public abstract class Spell {

    private final Identifier id;
    protected final double threshold;

    public Spell(Identifier id) {
        this.id = id;

        double threshold = 0.5;
        VerbalComponent verbal = this.verbal();

        if (verbal != null)
            threshold = verbal.calculateThreshold();

        this.threshold = threshold;
    }

    public Identifier id() {
        return id;
    }

    public float getRange(SpellContext.Spellcaster caster) {
        return 16;
    }

    public boolean canCast(SpellContext context, double integrity) {
        return integrity >= this.threshold;
    }

    // TODO
    public int successRate(SpellContext context, double integrity) {
        return 1;
    }

    public abstract void cast(SpellContext context, double integrity);

    public abstract VerbalComponent verbal();

    public record VerbalComponent(double min, String... parts) {

        public VerbalComponent(String... parts) {
            this(calculateThreshold(parts), parts);
        }

        public Score score(Cantrip cantrip) {
            StringSimilarityService service = SpellcastingSystem.config().stringSimilarity.get();

            double totalScore = 0;

            for (String part : parts) {
                double score = service.score(part, cantrip.get());
                cantrip.consume();

                if (score < min)
                    return Score.fail();

                totalScore += score;

                if (!cantrip.hasNext())
                    break;
            }

            if (totalScore / parts.length < min)
                return Score.fail();

            return new Score(totalScore / parts.length, true);
        }

        private double calculateThreshold() {
            return calculateThreshold(this.parts);
        }

        private static double calculateThreshold(String[] parts) {
            int totalLength = 0;
            for (String part : parts) {
                totalLength += part.length();
            }

            // 1 - 16x = 0.5
            // 0.5 = 16x
            if (totalLength < 16)
                return 1 - totalLength * 0.03125;

            return 0.5;
        }

        public record Score(double integrity, boolean success) {

            public static Score fail() {
                return new Score(0, false);
            }
        }
    }
}
