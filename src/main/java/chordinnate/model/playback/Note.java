package chordinnate.model.playback;

import chordinnate.exception.ChordInnateIllegalArgumentException;
import chordinnate.model.musictheory.pitch.Pitch;
import chordinnate.model.musictheory.temporal.rhythm.Beat;
import chordinnate.service.playback.Playable;
import chordinnate.service.playback.sequence.SequenceGenerator;
import chordinnate.service.playback.sequence.MidiEventGenerator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Sequence;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by Joseph on 6/16/16.
 */
@Slf4j
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Note implements Rhythmic, Playable {

    @NotNull
    private Set<Pitch> pitches;

    @Setter(AccessLevel.PRIVATE)
    private Set<Pitch> sharedPitchesOnLeft = new HashSet<>();

    @Setter(AccessLevel.PRIVATE)
    private Set<Pitch> sharedPitchesOnRight = new HashSet<>();

    @NotNull
    private Beat beat;

    @Nullable
    private Articulation articulation;

    @Nullable
    private Dynamic dynamic;

    @Nullable
    private Effect effect;

    @Nullable
    @Setter(AccessLevel.PRIVATE)
    private Note tiedToLeft;

    @Nullable
    @Setter(AccessLevel.PRIVATE)
    private Note tiedToRight;

    public Note(@NotNull Beat beat, @NotNull Pitch... pitches) {
        this.pitches = new HashSet<>(Arrays.asList(pitches));
        this.beat = beat;
    }

    @Builder
    private Note(@NotNull Beat beat, @Nullable Articulation articulation, @Nullable Dynamic dynamic, @Nullable Effect effect, @NotNull Pitch... pitches) {
        this.beat = beat;
        this.articulation = articulation;
        this.dynamic = dynamic;
        this.effect = effect;
        this.pitches = new HashSet<>(Arrays.asList(pitches));
    }

    /**
     * Connects this {@link Note} (on the left) to another (on the right) with a tie.
     * @param rhs the other note to tie to the current note
     * @throws ChordInnateIllegalArgumentException if:
     * <ul>
     *     <li>{@code rhs} is the same {@link Note} (in memory) as {@code this}</li>
     *     <li>either of the two {@link Note}s are already tied on their respective side that this method would modify</li>
     *     <li>the two {@link Note}s do not share at least one pitch</li>
     * </ul>
     */
    public void tieTo(@NotNull Note rhs) throws ChordInnateIllegalArgumentException {
        if (this == rhs) {
            throw new ChordInnateIllegalArgumentException("Cycle detected: cannot tie a note to itself");
        }

        if (this.isTiedOnRight() || rhs.isTiedOnLeft()) {
            throw new ChordInnateIllegalArgumentException("Cannot tie a note twice on the same side");
        }

        for (Pitch pitch : rhs.pitches) {
            if (this.pitches.contains(pitch)) {
                this.sharedPitchesOnRight.add(pitch);
                rhs.sharedPitchesOnLeft.add(pitch);
            }
        }

        if (this.sharedPitchesOnRight.isEmpty()) {
            throw new ChordInnateIllegalArgumentException("Tied notes must contain at least one shared pitch");
        }

        this.tiedToRight = rhs;
        rhs.tiedToLeft = this;
    }

    public void untie(Note note) {
        if (note == null) {
            return;
        }

        if (note == this.tiedToLeft) {
            this.sharedPitchesOnLeft.clear();
            this.tiedToLeft = null;
            note.sharedPitchesOnRight.clear();
            note.tiedToRight = null;
        } else if (note == this.tiedToRight) {
            this.sharedPitchesOnRight.clear();
            this.tiedToRight = null;
            note.sharedPitchesOnLeft.clear();
            note.tiedToLeft = null;
        }
    }

    public void untieOnLeft(Pitch pitch) {
        if (pitch == null || this.sharedPitchesOnLeft.isEmpty() || !this.sharedPitchesOnLeft.contains(pitch)) {
            return;
        }

        Note lhs = this.tiedToLeft;

        this.sharedPitchesOnLeft.remove(pitch);
        lhs.sharedPitchesOnRight.remove(pitch);

        if (this.sharedPitchesOnLeft.isEmpty()) {
            this.tiedToLeft = null;
        }

        if (lhs.sharedPitchesOnRight.isEmpty()) {
            lhs.tiedToRight = null;
        }
    }

    public void untieOnRight(Pitch pitch) {
        if (pitch == null || this.sharedPitchesOnRight.isEmpty() || !this.sharedPitchesOnRight.contains(pitch)) {
            return;
        }

        Note rhs = this.tiedToRight;

        this.sharedPitchesOnRight.remove(pitch);
        rhs.sharedPitchesOnLeft.remove(pitch);

        if (this.sharedPitchesOnRight.isEmpty()) {
            this.tiedToRight = null;
        }

        if (rhs.sharedPitchesOnLeft.isEmpty()) {
            rhs.tiedToLeft = null;
        }
    }

    /**
     * Connects all notes in the collection with a tie.
     * @param notes the notes to tie together
     * @throws ChordInnateIllegalArgumentException if:
     * <ul>
     *     <li>any {@link Note} is {@code null}</li>
     *     <li>any two {@link Note}s are identical objects in memory (to prevent cycles)</li>
     *     <li>any of the {@link Note}s are already tied on their respective side that this method would modify</li>
     *     <li>the {@link Note}s in the collection do not share at least one pitch with each adjacent {@link Note}</li>
     * </ul>
     */
    public static void tieAll(@NotNull Note... notes) throws ChordInnateIllegalArgumentException {
        Set<Note> dupeCheck;
        try {
            dupeCheck = new HashSet<>(Arrays.asList(notes));
        } catch (NullPointerException npe) {
            throw new ChordInnateIllegalArgumentException("Cannot tie a null note");
        }
        if (dupeCheck.size() != notes.length) {
            throw new ChordInnateIllegalArgumentException("Cycle detected in note tie chain");
        }

        if (notes.length >= 2) {
            try {
                for (int i1 = 0, i2 = 1; i2 < notes.length; i1++, i2++) {
                    notes[i1].tieTo(notes[i2]); // Note.tieTo(Note) covers the last two acceptance criteria
                }
            } catch (ChordInnateIllegalArgumentException ex) {
                throw new ChordInnateIllegalArgumentException("Adjacent notes in a tie must contain at least one shared pitch", ex);
            }
        }
    }

    /**
     * Connects all notes in the collection with a tie.
     * @param notes the notes to tie together
     * @throws ChordInnateIllegalArgumentException if:
     * <ul>
     *     <li>any {@link Note} is {@code null}</li>
     *     <li>any two {@link Note}s are identical objects in memory (to prevent cycles)</li>
     *     <li>any of the {@link Note}s are already tied on their respective side that this method would modify</li>
     *     <li>the {@link Note}s in the collection do not share the same pitch</li>
     * </ul>
     */
    public static void tieAll(@NotNull List<Note> notes) throws ChordInnateIllegalArgumentException {
        Set<Note> dupeCheck;
        try {
            dupeCheck = new HashSet<>(notes);
        } catch (NullPointerException npe) {
            throw new ChordInnateIllegalArgumentException("Cannot tie a null note");
        }
        if (dupeCheck.size() != notes.size()) {
            throw new ChordInnateIllegalArgumentException("Cycle detected in note tie chain");
        }

        if (notes.size() >= 2) {
            Note[] ns = notes.toArray(new Note[0]);
            try {
                for (int i1 = 0, i2 = 1; i2 < ns.length; i1++, i2++) {
                    ns[i1].tieTo(ns[i2]); // Note.tieTo(Note) covers the last two acceptance criteria
                }
            } catch (ChordInnateIllegalArgumentException ex) {
                throw new ChordInnateIllegalArgumentException("Adjacent notes in a tie must contain at least one shared pitch", ex);
            }
        }
    }

    public boolean isTied() {
        return isTiedOnLeft() || isTiedOnRight();
    }

    public boolean isTiedOnLeft() {
        return tiedToLeft != null;
    }

    public boolean isTiedOnRight() {
        return tiedToRight != null;
    }

    public static NoteBuilder builder(@NotNull Beat beat, @NotNull Pitch... pitches) {
        return new NoteBuilder().pitches(pitches).beat(beat);
    }

    @Override
    public Sequence accept(SequenceGenerator sequenceGenerator) {
        return sequenceGenerator.getSequence(this);
    }

    @Override
    public void accept(MidiEventGenerator midiEventGenerator) {
        try {
            midiEventGenerator.addEvents(this);
        } catch (InvalidMidiDataException e) {
            log.error("Error adding MIDI events", e);
        }
    }

    @Override
    public boolean equals(Object other) {
        return this == other; // we only care about identical references
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
