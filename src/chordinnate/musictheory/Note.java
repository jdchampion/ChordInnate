package chordinnate.musictheory;

/**
 * Created by Joseph on 6/16/16.
 */
public class Note {
    private Pitch pitch;
    private Duration duration;
    private Articulation articulation;
    private Tuplet tuplet;
    private DotValue dotValue;
    private double totalLength;         // Total duration, including Tuplet and DotValue modifiers.
    private boolean tied = false;       // Default to not being tied

    private Note(
            Pitch pitch,
            Duration duration,
            Articulation articulation,
            Tuplet tuplet,
            DotValue dotValue,
            boolean tied
    ) {
        this.pitch = pitch;
        this.duration = duration;
        this.articulation = articulation;
        this.tuplet = tuplet;
        this.dotValue = dotValue;
        this.tied = tied;
        this.totalLength = (duration.getFractionalValue() + getDotSum())
                * (tuplet == null ? 1 : tuplet.getNumber());
    }

    public Note(Pitch pitch, Duration duration) {
        this.pitch = pitch;
        this.duration = duration;
    }

    private double getDotSum() {
        double value = 0;
        Duration tmp = duration.getPrevious();
        int numDots = dotValue.ordinal();
        for (int i = 0; i < numDots; tmp = tmp.getPrevious(), i++) {
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

    public DotValue getDotValue() {
        return dotValue;
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
        private DotValue dotValue;
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

        public Builder dotValue(DotValue dotValue) {
            if (this.duration.ordinal() - dotValue.ordinal() >= 0) {
                this.dotValue = dotValue;
            }
            else {
                this.dotValue = DotValue.NONE;
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
                    dotValue,
                    tied
            );
        }
    }
}
