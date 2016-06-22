package chordinnate.musictheory;

/**
 * Created by Joseph on 6/16/16.
 */
public class Note {
    private Pitch pitch;
    private Duration duration;
    private Articulation articulation;
    private Tuplet tuplet;
    private int dots = 0;
    private double totalLength;         // Total duration, including triplet and dot modifiers.
    private boolean tied = false;       // Default to not being tied

    private Note(
            Pitch pitch,
            Duration duration,
            Articulation articulation,
            Tuplet tuplet,
            int dots,
            boolean tied
    ) {
        this.pitch = pitch;
        this.duration = duration;
        this.articulation = articulation;
        this.tuplet = tuplet;
        this.dots = dots;
        this.tied = tied;
        this.totalLength = (duration.getFractionalValue() + getDottedValue())
                * (tuplet == null ? 1 : tuplet.getNumber());
    }

    public Note(Pitch pitch, Duration duration) {
        this.pitch = pitch;
        this.duration = duration;
    }

    private double getDottedValue() {
        double value = 0;
        Duration tmp = duration.getPrevious();
        for (int i = 0; i < dots; tmp = tmp.getPrevious(), i++) {
            value += tmp.getFractionalValue();
        }
        return value;
    }

    public Pitch getPitch() {
        return pitch;
    }

    public Duration getDuration() {
        return duration;
    }

    public Articulation getArticulation() {
        return articulation;
    }

    public Tuplet getTuplet() {
        return tuplet;
    }

    public int getDots() {
        return dots;
    }

    public boolean isTied() {
        return tied;
    }

    public double getTotalLength() {
        return totalLength;
    }

    public static class Builder {
        private Pitch pitch;
        private Duration duration;
        private Articulation articulation;
        private Tuplet tuplet;
        private int dots = 0;
        private boolean tied = false;

        public Builder(Pitch pitch, Duration duration) {
            this.pitch = pitch;
            this.duration = duration;
        }

        public Builder articulation(Articulation articulation) {
            this.articulation = articulation;
            return this;
        }

        public Builder tuplet(Tuplet tuplet) {
            this.tuplet = tuplet;
            return this;
        }

        public Builder dots(int dots) {
            if (this.duration.ordinal() - dots >= 0) {
                this.dots = dots;
            }
            return this;
        }

        public Builder tie() {
            this.tied = true;
            return this;
        }

        public Note build() {
            return new Note(
                    pitch,
                    duration,
                    articulation,
                    tuplet,
                    dots,
                    tied
            );
        }
    }
}
