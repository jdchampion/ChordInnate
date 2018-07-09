package chordinnate.model.musictheory.pitch;

import chordinnate.model.musictheory.notation.Accidental;
import chordinnate.model.musictheory.notation.Letter;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.musictheory.pitch.interval.Octave;
import chordinnate.model.musictheory.pitch.interval.set.IntervalSet;
import chordinnate.model.musictheory.pitch.key.KeySignature;
import chordinnate.model.playback.Playable;
import org.jetbrains.annotations.NotNull;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Joseph on 4/18/16.
 */
public class Pitch
        implements Transposable<Pitch>, Enharmonic<Pitch>, Diatonic, Playable {

    public static final Pitch
    // Cbb10 is out of playable MIDI range, so it has been removed
        C_DOUBLE_FLAT_0 = new Pitch(PitchClass.C_DOUBLE_FLAT, Octave.OCTAVE_0),
        C_DOUBLE_FLAT_1 = new Pitch(PitchClass.C_DOUBLE_FLAT, Octave.OCTAVE_1),
        C_DOUBLE_FLAT_2 = new Pitch(PitchClass.C_DOUBLE_FLAT, Octave.OCTAVE_2),
        C_DOUBLE_FLAT_3 = new Pitch(PitchClass.C_DOUBLE_FLAT, Octave.OCTAVE_3),
        C_DOUBLE_FLAT_4 = new Pitch(PitchClass.C_DOUBLE_FLAT, Octave.OCTAVE_4),
        C_DOUBLE_FLAT_5 = new Pitch(PitchClass.C_DOUBLE_FLAT, Octave.OCTAVE_5),
        C_DOUBLE_FLAT_6 = new Pitch(PitchClass.C_DOUBLE_FLAT, Octave.OCTAVE_6),
        C_DOUBLE_FLAT_7 = new Pitch(PitchClass.C_DOUBLE_FLAT, Octave.OCTAVE_7),
        C_DOUBLE_FLAT_8 = new Pitch(PitchClass.C_DOUBLE_FLAT, Octave.OCTAVE_8),
        C_DOUBLE_FLAT_9 = new Pitch(PitchClass.C_DOUBLE_FLAT, Octave.OCTAVE_9),


    // Cb10 is out of playable MIDI range, so it has been removed
        C_FLAT_0 = new Pitch(PitchClass.C_FLAT, Octave.OCTAVE_0),
        C_FLAT_1 = new Pitch(PitchClass.C_FLAT, Octave.OCTAVE_1),
        C_FLAT_2 = new Pitch(PitchClass.C_FLAT, Octave.OCTAVE_2),
        C_FLAT_3 = new Pitch(PitchClass.C_FLAT, Octave.OCTAVE_3),
        C_FLAT_4 = new Pitch(PitchClass.C_FLAT, Octave.OCTAVE_4),
        C_FLAT_5 = new Pitch(PitchClass.C_FLAT, Octave.OCTAVE_5),
        C_FLAT_6 = new Pitch(PitchClass.C_FLAT, Octave.OCTAVE_6),
        C_FLAT_7 = new Pitch(PitchClass.C_FLAT, Octave.OCTAVE_7),
        C_FLAT_8 = new Pitch(PitchClass.C_FLAT, Octave.OCTAVE_8),
        C_FLAT_9 = new Pitch(PitchClass.C_FLAT, Octave.OCTAVE_9),

        C_0 = new Pitch(PitchClass.C, Octave.OCTAVE_0),
        C_1 = new Pitch(PitchClass.C, Octave.OCTAVE_1),
        C_2 = new Pitch(PitchClass.C, Octave.OCTAVE_2),
        C_3 = new Pitch(PitchClass.C, Octave.OCTAVE_3),
        C_4 = new Pitch(PitchClass.C, Octave.OCTAVE_4),
        C_5 = new Pitch(PitchClass.C, Octave.OCTAVE_5),
        C_6 = new Pitch(PitchClass.C, Octave.OCTAVE_6),
        C_7 = new Pitch(PitchClass.C, Octave.OCTAVE_7),
        C_8 = new Pitch(PitchClass.C, Octave.OCTAVE_8),
        C_9 = new Pitch(PitchClass.C, Octave.OCTAVE_9),
        C_10 = new Pitch(PitchClass.C, Octave.OCTAVE_10),

        C_SHARP_0 = new Pitch(PitchClass.C_SHARP, Octave.OCTAVE_0),
        C_SHARP_1 = new Pitch(PitchClass.C_SHARP, Octave.OCTAVE_1),
        C_SHARP_2 = new Pitch(PitchClass.C_SHARP, Octave.OCTAVE_2),
        C_SHARP_3 = new Pitch(PitchClass.C_SHARP, Octave.OCTAVE_3),
        C_SHARP_4 = new Pitch(PitchClass.C_SHARP, Octave.OCTAVE_4),
        C_SHARP_5 = new Pitch(PitchClass.C_SHARP, Octave.OCTAVE_5),
        C_SHARP_6 = new Pitch(PitchClass.C_SHARP, Octave.OCTAVE_6),
        C_SHARP_7 = new Pitch(PitchClass.C_SHARP, Octave.OCTAVE_7),
        C_SHARP_8 = new Pitch(PitchClass.C_SHARP, Octave.OCTAVE_8),
        C_SHARP_9 = new Pitch(PitchClass.C_SHARP, Octave.OCTAVE_9),
        C_SHARP_10 = new Pitch(PitchClass.C_SHARP, Octave.OCTAVE_10),

        C_DOUBLE_SHARP_0 = new Pitch(PitchClass.C_DOUBLE_SHARP, Octave.OCTAVE_0),
        C_DOUBLE_SHARP_1 = new Pitch(PitchClass.C_DOUBLE_SHARP, Octave.OCTAVE_1),
        C_DOUBLE_SHARP_2 = new Pitch(PitchClass.C_DOUBLE_SHARP, Octave.OCTAVE_2),
        C_DOUBLE_SHARP_3 = new Pitch(PitchClass.C_DOUBLE_SHARP, Octave.OCTAVE_3),
        C_DOUBLE_SHARP_4 = new Pitch(PitchClass.C_DOUBLE_SHARP, Octave.OCTAVE_4),
        C_DOUBLE_SHARP_5 = new Pitch(PitchClass.C_DOUBLE_SHARP, Octave.OCTAVE_5),
        C_DOUBLE_SHARP_6 = new Pitch(PitchClass.C_DOUBLE_SHARP, Octave.OCTAVE_6),
        C_DOUBLE_SHARP_7 = new Pitch(PitchClass.C_DOUBLE_SHARP, Octave.OCTAVE_7),
        C_DOUBLE_SHARP_8 = new Pitch(PitchClass.C_DOUBLE_SHARP, Octave.OCTAVE_8),
        C_DOUBLE_SHARP_9 = new Pitch(PitchClass.C_DOUBLE_SHARP, Octave.OCTAVE_9),
        C_DOUBLE_SHARP_10 = new Pitch(PitchClass.C_DOUBLE_SHARP, Octave.OCTAVE_10),



        D_DOUBLE_FLAT_0 = new Pitch(PitchClass.D_DOUBLE_FLAT, Octave.OCTAVE_0),
        D_DOUBLE_FLAT_1 = new Pitch(PitchClass.D_DOUBLE_FLAT, Octave.OCTAVE_1),
        D_DOUBLE_FLAT_2 = new Pitch(PitchClass.D_DOUBLE_FLAT, Octave.OCTAVE_2),
        D_DOUBLE_FLAT_3 = new Pitch(PitchClass.D_DOUBLE_FLAT, Octave.OCTAVE_3),
        D_DOUBLE_FLAT_4 = new Pitch(PitchClass.D_DOUBLE_FLAT, Octave.OCTAVE_4),
        D_DOUBLE_FLAT_5 = new Pitch(PitchClass.D_DOUBLE_FLAT, Octave.OCTAVE_5),
        D_DOUBLE_FLAT_6 = new Pitch(PitchClass.D_DOUBLE_FLAT, Octave.OCTAVE_6),
        D_DOUBLE_FLAT_7 = new Pitch(PitchClass.D_DOUBLE_FLAT, Octave.OCTAVE_7),
        D_DOUBLE_FLAT_8 = new Pitch(PitchClass.D_DOUBLE_FLAT, Octave.OCTAVE_8),
        D_DOUBLE_FLAT_9 = new Pitch(PitchClass.D_DOUBLE_FLAT, Octave.OCTAVE_9),
        D_DOUBLE_FLAT_10 = new Pitch(PitchClass.D_DOUBLE_FLAT, Octave.OCTAVE_10),

        D_FLAT_0 = new Pitch(PitchClass.D_FLAT, Octave.OCTAVE_0),
        D_FLAT_1 = new Pitch(PitchClass.D_FLAT, Octave.OCTAVE_1),
        D_FLAT_2 = new Pitch(PitchClass.D_FLAT, Octave.OCTAVE_2),
        D_FLAT_3 = new Pitch(PitchClass.D_FLAT, Octave.OCTAVE_3),
        D_FLAT_4 = new Pitch(PitchClass.D_FLAT, Octave.OCTAVE_4),
        D_FLAT_5 = new Pitch(PitchClass.D_FLAT, Octave.OCTAVE_5),
        D_FLAT_6 = new Pitch(PitchClass.D_FLAT, Octave.OCTAVE_6),
        D_FLAT_7 = new Pitch(PitchClass.D_FLAT, Octave.OCTAVE_7),
        D_FLAT_8 = new Pitch(PitchClass.D_FLAT, Octave.OCTAVE_8),
        D_FLAT_9 = new Pitch(PitchClass.D_FLAT, Octave.OCTAVE_9),
        D_FLAT_10 = new Pitch(PitchClass.D_FLAT, Octave.OCTAVE_10),

        D_0 = new Pitch(PitchClass.D, Octave.OCTAVE_0),
        D_1 = new Pitch(PitchClass.D, Octave.OCTAVE_1),
        D_2 = new Pitch(PitchClass.D, Octave.OCTAVE_2),
        D_3 = new Pitch(PitchClass.D, Octave.OCTAVE_3),
        D_4 = new Pitch(PitchClass.D, Octave.OCTAVE_4),
        D_5 = new Pitch(PitchClass.D, Octave.OCTAVE_5),
        D_6 = new Pitch(PitchClass.D, Octave.OCTAVE_6),
        D_7 = new Pitch(PitchClass.D, Octave.OCTAVE_7),
        D_8 = new Pitch(PitchClass.D, Octave.OCTAVE_8),
        D_9 = new Pitch(PitchClass.D, Octave.OCTAVE_9),
        D_10 = new Pitch(PitchClass.D, Octave.OCTAVE_10),

        D_SHARP_0 = new Pitch(PitchClass.D_SHARP, Octave.OCTAVE_0),
        D_SHARP_1 = new Pitch(PitchClass.D_SHARP, Octave.OCTAVE_1),
        D_SHARP_2 = new Pitch(PitchClass.D_SHARP, Octave.OCTAVE_2),
        D_SHARP_3 = new Pitch(PitchClass.D_SHARP, Octave.OCTAVE_3),
        D_SHARP_4 = new Pitch(PitchClass.D_SHARP, Octave.OCTAVE_4),
        D_SHARP_5 = new Pitch(PitchClass.D_SHARP, Octave.OCTAVE_5),
        D_SHARP_6 = new Pitch(PitchClass.D_SHARP, Octave.OCTAVE_6),
        D_SHARP_7 = new Pitch(PitchClass.D_SHARP, Octave.OCTAVE_7),
        D_SHARP_8 = new Pitch(PitchClass.D_SHARP, Octave.OCTAVE_8),
        D_SHARP_9 = new Pitch(PitchClass.D_SHARP, Octave.OCTAVE_9),
        D_SHARP_10 = new Pitch(PitchClass.D_SHARP, Octave.OCTAVE_10),

        D_DOUBLE_SHARP_0 = new Pitch(PitchClass.D_DOUBLE_SHARP, Octave.OCTAVE_0),
        D_DOUBLE_SHARP_1 = new Pitch(PitchClass.D_DOUBLE_SHARP, Octave.OCTAVE_1),
        D_DOUBLE_SHARP_2 = new Pitch(PitchClass.D_DOUBLE_SHARP, Octave.OCTAVE_2),
        D_DOUBLE_SHARP_3 = new Pitch(PitchClass.D_DOUBLE_SHARP, Octave.OCTAVE_3),
        D_DOUBLE_SHARP_4 = new Pitch(PitchClass.D_DOUBLE_SHARP, Octave.OCTAVE_4),
        D_DOUBLE_SHARP_5 = new Pitch(PitchClass.D_DOUBLE_SHARP, Octave.OCTAVE_5),
        D_DOUBLE_SHARP_6 = new Pitch(PitchClass.D_DOUBLE_SHARP, Octave.OCTAVE_6),
        D_DOUBLE_SHARP_7 = new Pitch(PitchClass.D_DOUBLE_SHARP, Octave.OCTAVE_7),
        D_DOUBLE_SHARP_8 = new Pitch(PitchClass.D_DOUBLE_SHARP, Octave.OCTAVE_8),
        D_DOUBLE_SHARP_9 = new Pitch(PitchClass.D_DOUBLE_SHARP, Octave.OCTAVE_9),
        D_DOUBLE_SHARP_10 = new Pitch(PitchClass.D_DOUBLE_SHARP, Octave.OCTAVE_10),




        E_DOUBLE_FLAT_0 = new Pitch(PitchClass.E_DOUBLE_FLAT, Octave.OCTAVE_0),
        E_DOUBLE_FLAT_1 = new Pitch(PitchClass.E_DOUBLE_FLAT, Octave.OCTAVE_1),
        E_DOUBLE_FLAT_2 = new Pitch(PitchClass.E_DOUBLE_FLAT, Octave.OCTAVE_2),
        E_DOUBLE_FLAT_3 = new Pitch(PitchClass.E_DOUBLE_FLAT, Octave.OCTAVE_3),
        E_DOUBLE_FLAT_4 = new Pitch(PitchClass.E_DOUBLE_FLAT, Octave.OCTAVE_4),
        E_DOUBLE_FLAT_5 = new Pitch(PitchClass.E_DOUBLE_FLAT, Octave.OCTAVE_5),
        E_DOUBLE_FLAT_6 = new Pitch(PitchClass.E_DOUBLE_FLAT, Octave.OCTAVE_6),
        E_DOUBLE_FLAT_7 = new Pitch(PitchClass.E_DOUBLE_FLAT, Octave.OCTAVE_7),
        E_DOUBLE_FLAT_8 = new Pitch(PitchClass.E_DOUBLE_FLAT, Octave.OCTAVE_8),
        E_DOUBLE_FLAT_9 = new Pitch(PitchClass.E_DOUBLE_FLAT, Octave.OCTAVE_9),
        E_DOUBLE_FLAT_10 = new Pitch(PitchClass.E_DOUBLE_FLAT, Octave.OCTAVE_10),

        E_FLAT_0 = new Pitch(PitchClass.E_FLAT, Octave.OCTAVE_0),
        E_FLAT_1 = new Pitch(PitchClass.E_FLAT, Octave.OCTAVE_1),
        E_FLAT_2 = new Pitch(PitchClass.E_FLAT, Octave.OCTAVE_2),
        E_FLAT_3 = new Pitch(PitchClass.E_FLAT, Octave.OCTAVE_3),
        E_FLAT_4 = new Pitch(PitchClass.E_FLAT, Octave.OCTAVE_4),
        E_FLAT_5 = new Pitch(PitchClass.E_FLAT, Octave.OCTAVE_5),
        E_FLAT_6 = new Pitch(PitchClass.E_FLAT, Octave.OCTAVE_6),
        E_FLAT_7 = new Pitch(PitchClass.E_FLAT, Octave.OCTAVE_7),
        E_FLAT_8 = new Pitch(PitchClass.E_FLAT, Octave.OCTAVE_8),
        E_FLAT_9 = new Pitch(PitchClass.E_FLAT, Octave.OCTAVE_9),
        E_FLAT_10 = new Pitch(PitchClass.E_FLAT, Octave.OCTAVE_10),

        E_0 = new Pitch(PitchClass.E, Octave.OCTAVE_0),
        E_1 = new Pitch(PitchClass.E, Octave.OCTAVE_1),
        E_2 = new Pitch(PitchClass.E, Octave.OCTAVE_2),
        E_3 = new Pitch(PitchClass.E, Octave.OCTAVE_3),
        E_4 = new Pitch(PitchClass.E, Octave.OCTAVE_4),
        E_5 = new Pitch(PitchClass.E, Octave.OCTAVE_5),
        E_6 = new Pitch(PitchClass.E, Octave.OCTAVE_6),
        E_7 = new Pitch(PitchClass.E, Octave.OCTAVE_7),
        E_8 = new Pitch(PitchClass.E, Octave.OCTAVE_8),
        E_9 = new Pitch(PitchClass.E, Octave.OCTAVE_9),
        E_10 = new Pitch(PitchClass.E, Octave.OCTAVE_10),

        E_SHARP_0 = new Pitch(PitchClass.E_SHARP, Octave.OCTAVE_0),
        E_SHARP_1 = new Pitch(PitchClass.E_SHARP, Octave.OCTAVE_1),
        E_SHARP_2 = new Pitch(PitchClass.E_SHARP, Octave.OCTAVE_2),
        E_SHARP_3 = new Pitch(PitchClass.E_SHARP, Octave.OCTAVE_3),
        E_SHARP_4 = new Pitch(PitchClass.E_SHARP, Octave.OCTAVE_4),
        E_SHARP_5 = new Pitch(PitchClass.E_SHARP, Octave.OCTAVE_5),
        E_SHARP_6 = new Pitch(PitchClass.E_SHARP, Octave.OCTAVE_6),
        E_SHARP_7 = new Pitch(PitchClass.E_SHARP, Octave.OCTAVE_7),
        E_SHARP_8 = new Pitch(PitchClass.E_SHARP, Octave.OCTAVE_8),
        E_SHARP_9 = new Pitch(PitchClass.E_SHARP, Octave.OCTAVE_9),
        E_SHARP_10 = new Pitch(PitchClass.E_SHARP, Octave.OCTAVE_10),

        E_DOUBLE_SHARP_0 = new Pitch(PitchClass.E_DOUBLE_SHARP, Octave.OCTAVE_0),
        E_DOUBLE_SHARP_1 = new Pitch(PitchClass.E_DOUBLE_SHARP, Octave.OCTAVE_1),
        E_DOUBLE_SHARP_2 = new Pitch(PitchClass.E_DOUBLE_SHARP, Octave.OCTAVE_2),
        E_DOUBLE_SHARP_3 = new Pitch(PitchClass.E_DOUBLE_SHARP, Octave.OCTAVE_3),
        E_DOUBLE_SHARP_4 = new Pitch(PitchClass.E_DOUBLE_SHARP, Octave.OCTAVE_4),
        E_DOUBLE_SHARP_5 = new Pitch(PitchClass.E_DOUBLE_SHARP, Octave.OCTAVE_5),
        E_DOUBLE_SHARP_6 = new Pitch(PitchClass.E_DOUBLE_SHARP, Octave.OCTAVE_6),
        E_DOUBLE_SHARP_7 = new Pitch(PitchClass.E_DOUBLE_SHARP, Octave.OCTAVE_7),
        E_DOUBLE_SHARP_8 = new Pitch(PitchClass.E_DOUBLE_SHARP, Octave.OCTAVE_8),
        E_DOUBLE_SHARP_9 = new Pitch(PitchClass.E_DOUBLE_SHARP, Octave.OCTAVE_9),
        E_DOUBLE_SHARP_10 = new Pitch(PitchClass.E_DOUBLE_SHARP, Octave.OCTAVE_10),



        F_DOUBLE_FLAT_0 = new Pitch(PitchClass.F_DOUBLE_FLAT, Octave.OCTAVE_0),
        F_DOUBLE_FLAT_1 = new Pitch(PitchClass.F_DOUBLE_FLAT, Octave.OCTAVE_1),
        F_DOUBLE_FLAT_2 = new Pitch(PitchClass.F_DOUBLE_FLAT, Octave.OCTAVE_2),
        F_DOUBLE_FLAT_3 = new Pitch(PitchClass.F_DOUBLE_FLAT, Octave.OCTAVE_3),
        F_DOUBLE_FLAT_4 = new Pitch(PitchClass.F_DOUBLE_FLAT, Octave.OCTAVE_4),
        F_DOUBLE_FLAT_5 = new Pitch(PitchClass.F_DOUBLE_FLAT, Octave.OCTAVE_5),
        F_DOUBLE_FLAT_6 = new Pitch(PitchClass.F_DOUBLE_FLAT, Octave.OCTAVE_6),
        F_DOUBLE_FLAT_7 = new Pitch(PitchClass.F_DOUBLE_FLAT, Octave.OCTAVE_7),
        F_DOUBLE_FLAT_8 = new Pitch(PitchClass.F_DOUBLE_FLAT, Octave.OCTAVE_8),
        F_DOUBLE_FLAT_9 = new Pitch(PitchClass.F_DOUBLE_FLAT, Octave.OCTAVE_9),
        F_DOUBLE_FLAT_10 = new Pitch(PitchClass.F_DOUBLE_FLAT, Octave.OCTAVE_10),

        F_FLAT_0 = new Pitch(PitchClass.F_FLAT, Octave.OCTAVE_0),
        F_FLAT_1 = new Pitch(PitchClass.F_FLAT, Octave.OCTAVE_1),
        F_FLAT_2 = new Pitch(PitchClass.F_FLAT, Octave.OCTAVE_2),
        F_FLAT_3 = new Pitch(PitchClass.F_FLAT, Octave.OCTAVE_3),
        F_FLAT_4 = new Pitch(PitchClass.F_FLAT, Octave.OCTAVE_4),
        F_FLAT_5 = new Pitch(PitchClass.F_FLAT, Octave.OCTAVE_5),
        F_FLAT_6 = new Pitch(PitchClass.F_FLAT, Octave.OCTAVE_6),
        F_FLAT_7 = new Pitch(PitchClass.F_FLAT, Octave.OCTAVE_7),
        F_FLAT_8 = new Pitch(PitchClass.F_FLAT, Octave.OCTAVE_8),
        F_FLAT_9 = new Pitch(PitchClass.F_FLAT, Octave.OCTAVE_9),
        F_FLAT_10 = new Pitch(PitchClass.F_FLAT, Octave.OCTAVE_10),

        F_0 = new Pitch(PitchClass.F, Octave.OCTAVE_0),
        F_1 = new Pitch(PitchClass.F, Octave.OCTAVE_1),
        F_2 = new Pitch(PitchClass.F, Octave.OCTAVE_2),
        F_3 = new Pitch(PitchClass.F, Octave.OCTAVE_3),
        F_4 = new Pitch(PitchClass.F, Octave.OCTAVE_4),
        F_5 = new Pitch(PitchClass.F, Octave.OCTAVE_5),
        F_6 = new Pitch(PitchClass.F, Octave.OCTAVE_6),
        F_7 = new Pitch(PitchClass.F, Octave.OCTAVE_7),
        F_8 = new Pitch(PitchClass.F, Octave.OCTAVE_8),
        F_9 = new Pitch(PitchClass.F, Octave.OCTAVE_9),
        F_10 = new Pitch(PitchClass.F, Octave.OCTAVE_10),

        F_SHARP_0 = new Pitch(PitchClass.F_SHARP, Octave.OCTAVE_0),
        F_SHARP_1 = new Pitch(PitchClass.F_SHARP, Octave.OCTAVE_1),
        F_SHARP_2 = new Pitch(PitchClass.F_SHARP, Octave.OCTAVE_2),
        F_SHARP_3 = new Pitch(PitchClass.F_SHARP, Octave.OCTAVE_3),
        F_SHARP_4 = new Pitch(PitchClass.F_SHARP, Octave.OCTAVE_4),
        F_SHARP_5 = new Pitch(PitchClass.F_SHARP, Octave.OCTAVE_5),
        F_SHARP_6 = new Pitch(PitchClass.F_SHARP, Octave.OCTAVE_6),
        F_SHARP_7 = new Pitch(PitchClass.F_SHARP, Octave.OCTAVE_7),
        F_SHARP_8 = new Pitch(PitchClass.F_SHARP, Octave.OCTAVE_8),
        F_SHARP_9 = new Pitch(PitchClass.F_SHARP, Octave.OCTAVE_9),
        F_SHARP_10 = new Pitch(PitchClass.F_SHARP, Octave.OCTAVE_10),

        F_DOUBLE_SHARP_0 = new Pitch(PitchClass.F_DOUBLE_SHARP, Octave.OCTAVE_0),
        F_DOUBLE_SHARP_1 = new Pitch(PitchClass.F_DOUBLE_SHARP, Octave.OCTAVE_1),
        F_DOUBLE_SHARP_2 = new Pitch(PitchClass.F_DOUBLE_SHARP, Octave.OCTAVE_2),
        F_DOUBLE_SHARP_3 = new Pitch(PitchClass.F_DOUBLE_SHARP, Octave.OCTAVE_3),
        F_DOUBLE_SHARP_4 = new Pitch(PitchClass.F_DOUBLE_SHARP, Octave.OCTAVE_4),
        F_DOUBLE_SHARP_5 = new Pitch(PitchClass.F_DOUBLE_SHARP, Octave.OCTAVE_5),
        F_DOUBLE_SHARP_6 = new Pitch(PitchClass.F_DOUBLE_SHARP, Octave.OCTAVE_6),
        F_DOUBLE_SHARP_7 = new Pitch(PitchClass.F_DOUBLE_SHARP, Octave.OCTAVE_7),
        F_DOUBLE_SHARP_8 = new Pitch(PitchClass.F_DOUBLE_SHARP, Octave.OCTAVE_8),
        F_DOUBLE_SHARP_9 = new Pitch(PitchClass.F_DOUBLE_SHARP, Octave.OCTAVE_9),
        F_DOUBLE_SHARP_10 = new Pitch(PitchClass.F_DOUBLE_SHARP, Octave.OCTAVE_10),



        G_DOUBLE_FLAT_0 = new Pitch(PitchClass.G_DOUBLE_FLAT, Octave.OCTAVE_0),
        G_DOUBLE_FLAT_1 = new Pitch(PitchClass.G_DOUBLE_FLAT, Octave.OCTAVE_1),
        G_DOUBLE_FLAT_2 = new Pitch(PitchClass.G_DOUBLE_FLAT, Octave.OCTAVE_2),
        G_DOUBLE_FLAT_3 = new Pitch(PitchClass.G_DOUBLE_FLAT, Octave.OCTAVE_3),
        G_DOUBLE_FLAT_4 = new Pitch(PitchClass.G_DOUBLE_FLAT, Octave.OCTAVE_4),
        G_DOUBLE_FLAT_5 = new Pitch(PitchClass.G_DOUBLE_FLAT, Octave.OCTAVE_5),
        G_DOUBLE_FLAT_6 = new Pitch(PitchClass.G_DOUBLE_FLAT, Octave.OCTAVE_6),
        G_DOUBLE_FLAT_7 = new Pitch(PitchClass.G_DOUBLE_FLAT, Octave.OCTAVE_7),
        G_DOUBLE_FLAT_8 = new Pitch(PitchClass.G_DOUBLE_FLAT, Octave.OCTAVE_8),
        G_DOUBLE_FLAT_9 = new Pitch(PitchClass.G_DOUBLE_FLAT, Octave.OCTAVE_9),
        G_DOUBLE_FLAT_10 = new Pitch(PitchClass.G_DOUBLE_FLAT, Octave.OCTAVE_10),

        G_FLAT_0 = new Pitch(PitchClass.G_FLAT, Octave.OCTAVE_0),
        G_FLAT_1 = new Pitch(PitchClass.G_FLAT, Octave.OCTAVE_1),
        G_FLAT_2 = new Pitch(PitchClass.G_FLAT, Octave.OCTAVE_2),
        G_FLAT_3 = new Pitch(PitchClass.G_FLAT, Octave.OCTAVE_3),
        G_FLAT_4 = new Pitch(PitchClass.G_FLAT, Octave.OCTAVE_4),
        G_FLAT_5 = new Pitch(PitchClass.G_FLAT, Octave.OCTAVE_5),
        G_FLAT_6 = new Pitch(PitchClass.G_FLAT, Octave.OCTAVE_6),
        G_FLAT_7 = new Pitch(PitchClass.G_FLAT, Octave.OCTAVE_7),
        G_FLAT_8 = new Pitch(PitchClass.G_FLAT, Octave.OCTAVE_8),
        G_FLAT_9 = new Pitch(PitchClass.G_FLAT, Octave.OCTAVE_9),
        G_FLAT_10 = new Pitch(PitchClass.G_FLAT, Octave.OCTAVE_10),

        G_0 = new Pitch(PitchClass.G, Octave.OCTAVE_0),
        G_1 = new Pitch(PitchClass.G, Octave.OCTAVE_1),
        G_2 = new Pitch(PitchClass.G, Octave.OCTAVE_2),
        G_3 = new Pitch(PitchClass.G, Octave.OCTAVE_3),
        G_4 = new Pitch(PitchClass.G, Octave.OCTAVE_4),
        G_5 = new Pitch(PitchClass.G, Octave.OCTAVE_5),
        G_6 = new Pitch(PitchClass.G, Octave.OCTAVE_6),
        G_7 = new Pitch(PitchClass.G, Octave.OCTAVE_7),
        G_8 = new Pitch(PitchClass.G, Octave.OCTAVE_8),
        G_9 = new Pitch(PitchClass.G, Octave.OCTAVE_9),
        G_10 = new Pitch(PitchClass.G, Octave.OCTAVE_10),

    /*
     *  All Pitches beyond this point that are enharmonically higher than G
     *  are not playable on OCTAVE_10 (because they are out of the MIDI range),
     *  so they must not contain OCTAVE_10.
     */

        G_SHARP_0 = new Pitch(PitchClass.G_SHARP, Octave.OCTAVE_0),
        G_SHARP_1 = new Pitch(PitchClass.G_SHARP, Octave.OCTAVE_1),
        G_SHARP_2 = new Pitch(PitchClass.G_SHARP, Octave.OCTAVE_2),
        G_SHARP_3 = new Pitch(PitchClass.G_SHARP, Octave.OCTAVE_3),
        G_SHARP_4 = new Pitch(PitchClass.G_SHARP, Octave.OCTAVE_4),
        G_SHARP_5 = new Pitch(PitchClass.G_SHARP, Octave.OCTAVE_5),
        G_SHARP_6 = new Pitch(PitchClass.G_SHARP, Octave.OCTAVE_6),
        G_SHARP_7 = new Pitch(PitchClass.G_SHARP, Octave.OCTAVE_7),
        G_SHARP_8 = new Pitch(PitchClass.G_SHARP, Octave.OCTAVE_8),
        G_SHARP_9 = new Pitch(PitchClass.G_SHARP, Octave.OCTAVE_9),

        G_DOUBLE_SHARP_0 = new Pitch(PitchClass.G_DOUBLE_SHARP, Octave.OCTAVE_0),
        G_DOUBLE_SHARP_1 = new Pitch(PitchClass.G_DOUBLE_SHARP, Octave.OCTAVE_1),
        G_DOUBLE_SHARP_2 = new Pitch(PitchClass.G_DOUBLE_SHARP, Octave.OCTAVE_2),
        G_DOUBLE_SHARP_3 = new Pitch(PitchClass.G_DOUBLE_SHARP, Octave.OCTAVE_3),
        G_DOUBLE_SHARP_4 = new Pitch(PitchClass.G_DOUBLE_SHARP, Octave.OCTAVE_4),
        G_DOUBLE_SHARP_5 = new Pitch(PitchClass.G_DOUBLE_SHARP, Octave.OCTAVE_5),
        G_DOUBLE_SHARP_6 = new Pitch(PitchClass.G_DOUBLE_SHARP, Octave.OCTAVE_6),
        G_DOUBLE_SHARP_7 = new Pitch(PitchClass.G_DOUBLE_SHARP, Octave.OCTAVE_7),
        G_DOUBLE_SHARP_8 = new Pitch(PitchClass.G_DOUBLE_SHARP, Octave.OCTAVE_8),
        G_DOUBLE_SHARP_9 = new Pitch(PitchClass.G_DOUBLE_SHARP, Octave.OCTAVE_9),



        A_DOUBLE_FLAT_0 = new Pitch(PitchClass.A_DOUBLE_FLAT, Octave.OCTAVE_0),
        A_DOUBLE_FLAT_1 = new Pitch(PitchClass.A_DOUBLE_FLAT, Octave.OCTAVE_1),
        A_DOUBLE_FLAT_2 = new Pitch(PitchClass.A_DOUBLE_FLAT, Octave.OCTAVE_2),
        A_DOUBLE_FLAT_3 = new Pitch(PitchClass.A_DOUBLE_FLAT, Octave.OCTAVE_3),
        A_DOUBLE_FLAT_4 = new Pitch(PitchClass.A_DOUBLE_FLAT, Octave.OCTAVE_4),
        A_DOUBLE_FLAT_5 = new Pitch(PitchClass.A_DOUBLE_FLAT, Octave.OCTAVE_5),
        A_DOUBLE_FLAT_6 = new Pitch(PitchClass.A_DOUBLE_FLAT, Octave.OCTAVE_6),
        A_DOUBLE_FLAT_7 = new Pitch(PitchClass.A_DOUBLE_FLAT, Octave.OCTAVE_7),
        A_DOUBLE_FLAT_8 = new Pitch(PitchClass.A_DOUBLE_FLAT, Octave.OCTAVE_8),
        A_DOUBLE_FLAT_9 = new Pitch(PitchClass.A_DOUBLE_FLAT, Octave.OCTAVE_9),
        A_DOUBLE_FLAT_10 = new Pitch(PitchClass.A_DOUBLE_FLAT, Octave.OCTAVE_10),

        A_FLAT_0 = new Pitch(PitchClass.A_FLAT, Octave.OCTAVE_0),
        A_FLAT_1 = new Pitch(PitchClass.A_FLAT, Octave.OCTAVE_1),
        A_FLAT_2 = new Pitch(PitchClass.A_FLAT, Octave.OCTAVE_2),
        A_FLAT_3 = new Pitch(PitchClass.A_FLAT, Octave.OCTAVE_3),
        A_FLAT_4 = new Pitch(PitchClass.A_FLAT, Octave.OCTAVE_4),
        A_FLAT_5 = new Pitch(PitchClass.A_FLAT, Octave.OCTAVE_5),
        A_FLAT_6 = new Pitch(PitchClass.A_FLAT, Octave.OCTAVE_6),
        A_FLAT_7 = new Pitch(PitchClass.A_FLAT, Octave.OCTAVE_7),
        A_FLAT_8 = new Pitch(PitchClass.A_FLAT, Octave.OCTAVE_8),
        A_FLAT_9 = new Pitch(PitchClass.A_FLAT, Octave.OCTAVE_9),

        A_0 = new Pitch(PitchClass.A, Octave.OCTAVE_0),
        A_1 = new Pitch(PitchClass.A, Octave.OCTAVE_1),
        A_2 = new Pitch(PitchClass.A, Octave.OCTAVE_2),
        A_3 = new Pitch(PitchClass.A, Octave.OCTAVE_3),
        A_4 = new Pitch(PitchClass.A, Octave.OCTAVE_4),
        A_5 = new Pitch(PitchClass.A, Octave.OCTAVE_5),
        A_6 = new Pitch(PitchClass.A, Octave.OCTAVE_6),
        A_7 = new Pitch(PitchClass.A, Octave.OCTAVE_7),
        A_8 = new Pitch(PitchClass.A, Octave.OCTAVE_8),
        A_9 = new Pitch(PitchClass.A, Octave.OCTAVE_9),

        A_SHARP_0 = new Pitch(PitchClass.A_SHARP, Octave.OCTAVE_0),
        A_SHARP_1 = new Pitch(PitchClass.A_SHARP, Octave.OCTAVE_1),
        A_SHARP_2 = new Pitch(PitchClass.A_SHARP, Octave.OCTAVE_2),
        A_SHARP_3 = new Pitch(PitchClass.A_SHARP, Octave.OCTAVE_3),
        A_SHARP_4 = new Pitch(PitchClass.A_SHARP, Octave.OCTAVE_4),
        A_SHARP_5 = new Pitch(PitchClass.A_SHARP, Octave.OCTAVE_5),
        A_SHARP_6 = new Pitch(PitchClass.A_SHARP, Octave.OCTAVE_6),
        A_SHARP_7 = new Pitch(PitchClass.A_SHARP, Octave.OCTAVE_7),
        A_SHARP_8 = new Pitch(PitchClass.A_SHARP, Octave.OCTAVE_8),
        A_SHARP_9 = new Pitch(PitchClass.A_SHARP, Octave.OCTAVE_9),

        A_DOUBLE_SHARP_0 = new Pitch(PitchClass.A_DOUBLE_SHARP, Octave.OCTAVE_0),
        A_DOUBLE_SHARP_1 = new Pitch(PitchClass.A_DOUBLE_SHARP, Octave.OCTAVE_1),
        A_DOUBLE_SHARP_2 = new Pitch(PitchClass.A_DOUBLE_SHARP, Octave.OCTAVE_2),
        A_DOUBLE_SHARP_3 = new Pitch(PitchClass.A_DOUBLE_SHARP, Octave.OCTAVE_3),
        A_DOUBLE_SHARP_4 = new Pitch(PitchClass.A_DOUBLE_SHARP, Octave.OCTAVE_4),
        A_DOUBLE_SHARP_5 = new Pitch(PitchClass.A_DOUBLE_SHARP, Octave.OCTAVE_5),
        A_DOUBLE_SHARP_6 = new Pitch(PitchClass.A_DOUBLE_SHARP, Octave.OCTAVE_6),
        A_DOUBLE_SHARP_7 = new Pitch(PitchClass.A_DOUBLE_SHARP, Octave.OCTAVE_7),
        A_DOUBLE_SHARP_8 = new Pitch(PitchClass.A_DOUBLE_SHARP, Octave.OCTAVE_8),
        A_DOUBLE_SHARP_9 = new Pitch(PitchClass.A_DOUBLE_SHARP, Octave.OCTAVE_9),



        B_DOUBLE_FLAT_0 = new Pitch(PitchClass.B_DOUBLE_FLAT, Octave.OCTAVE_0),
        B_DOUBLE_FLAT_1 = new Pitch(PitchClass.B_DOUBLE_FLAT, Octave.OCTAVE_1),
        B_DOUBLE_FLAT_2 = new Pitch(PitchClass.B_DOUBLE_FLAT, Octave.OCTAVE_2),
        B_DOUBLE_FLAT_3 = new Pitch(PitchClass.B_DOUBLE_FLAT, Octave.OCTAVE_3),
        B_DOUBLE_FLAT_4 = new Pitch(PitchClass.B_DOUBLE_FLAT, Octave.OCTAVE_4),
        B_DOUBLE_FLAT_5 = new Pitch(PitchClass.B_DOUBLE_FLAT, Octave.OCTAVE_5),
        B_DOUBLE_FLAT_6 = new Pitch(PitchClass.B_DOUBLE_FLAT, Octave.OCTAVE_6),
        B_DOUBLE_FLAT_7 = new Pitch(PitchClass.B_DOUBLE_FLAT, Octave.OCTAVE_7),
        B_DOUBLE_FLAT_8 = new Pitch(PitchClass.B_DOUBLE_FLAT, Octave.OCTAVE_8),
        B_DOUBLE_FLAT_9 = new Pitch(PitchClass.B_DOUBLE_FLAT, Octave.OCTAVE_9),

        B_FLAT_0 = new Pitch(PitchClass.B_FLAT, Octave.OCTAVE_0),
        B_FLAT_1 = new Pitch(PitchClass.B_FLAT, Octave.OCTAVE_1),
        B_FLAT_2 = new Pitch(PitchClass.B_FLAT, Octave.OCTAVE_2),
        B_FLAT_3 = new Pitch(PitchClass.B_FLAT, Octave.OCTAVE_3),
        B_FLAT_4 = new Pitch(PitchClass.B_FLAT, Octave.OCTAVE_4),
        B_FLAT_5 = new Pitch(PitchClass.B_FLAT, Octave.OCTAVE_5),
        B_FLAT_6 = new Pitch(PitchClass.B_FLAT, Octave.OCTAVE_6),
        B_FLAT_7 = new Pitch(PitchClass.B_FLAT, Octave.OCTAVE_7),
        B_FLAT_8 = new Pitch(PitchClass.B_FLAT, Octave.OCTAVE_8),
        B_FLAT_9 = new Pitch(PitchClass.B_FLAT, Octave.OCTAVE_9),

        B_0 = new Pitch(PitchClass.B, Octave.OCTAVE_0),
        B_1 = new Pitch(PitchClass.B, Octave.OCTAVE_1),
        B_2 = new Pitch(PitchClass.B, Octave.OCTAVE_2),
        B_3 = new Pitch(PitchClass.B, Octave.OCTAVE_3),
        B_4 = new Pitch(PitchClass.B, Octave.OCTAVE_4),
        B_5 = new Pitch(PitchClass.B, Octave.OCTAVE_5),
        B_6 = new Pitch(PitchClass.B, Octave.OCTAVE_6),
        B_7 = new Pitch(PitchClass.B, Octave.OCTAVE_7),
        B_8 = new Pitch(PitchClass.B, Octave.OCTAVE_8),
        B_9 = new Pitch(PitchClass.B, Octave.OCTAVE_9),

        B_SHARP_0 = new Pitch(PitchClass.B_SHARP, Octave.OCTAVE_0),
        B_SHARP_1 = new Pitch(PitchClass.B_SHARP, Octave.OCTAVE_1),
        B_SHARP_2 = new Pitch(PitchClass.B_SHARP, Octave.OCTAVE_2),
        B_SHARP_3 = new Pitch(PitchClass.B_SHARP, Octave.OCTAVE_3),
        B_SHARP_4 = new Pitch(PitchClass.B_SHARP, Octave.OCTAVE_4),
        B_SHARP_5 = new Pitch(PitchClass.B_SHARP, Octave.OCTAVE_5),
        B_SHARP_6 = new Pitch(PitchClass.B_SHARP, Octave.OCTAVE_6),
        B_SHARP_7 = new Pitch(PitchClass.B_SHARP, Octave.OCTAVE_7),
        B_SHARP_8 = new Pitch(PitchClass.B_SHARP, Octave.OCTAVE_8),
        B_SHARP_9 = new Pitch(PitchClass.B_SHARP, Octave.OCTAVE_9),

        B_DOUBLE_SHARP_0 = new Pitch(PitchClass.B_DOUBLE_SHARP, Octave.OCTAVE_0),
        B_DOUBLE_SHARP_1 = new Pitch(PitchClass.B_DOUBLE_SHARP, Octave.OCTAVE_1),
        B_DOUBLE_SHARP_2 = new Pitch(PitchClass.B_DOUBLE_SHARP, Octave.OCTAVE_2),
        B_DOUBLE_SHARP_3 = new Pitch(PitchClass.B_DOUBLE_SHARP, Octave.OCTAVE_3),
        B_DOUBLE_SHARP_4 = new Pitch(PitchClass.B_DOUBLE_SHARP, Octave.OCTAVE_4),
        B_DOUBLE_SHARP_5 = new Pitch(PitchClass.B_DOUBLE_SHARP, Octave.OCTAVE_5),
        B_DOUBLE_SHARP_6 = new Pitch(PitchClass.B_DOUBLE_SHARP, Octave.OCTAVE_6),
        B_DOUBLE_SHARP_7 = new Pitch(PitchClass.B_DOUBLE_SHARP, Octave.OCTAVE_7),
        B_DOUBLE_SHARP_8 = new Pitch(PitchClass.B_DOUBLE_SHARP, Octave.OCTAVE_8),
        B_DOUBLE_SHARP_9 = new Pitch(PitchClass.B_DOUBLE_SHARP, Octave.OCTAVE_9);

    public final PitchClass PITCH_CLASS;
    public final Octave OCTAVE;
    public final int ABSOLUTE_PITCH;

    private static final String FIRST_PASS_PITCH_REGEX = "^([A-Ga-g])([b#x]*)?([0-9]|10)$";
    private static final Pattern FIRST_PASS_PITCH_PATTERN = Pattern.compile(FIRST_PASS_PITCH_REGEX);

    static final Map<String, Pitch> STANDARD_PITCH_LOOKUP = Collections.unmodifiableMap(initSPL());

    private static Map<String, Pitch> initSPL() {
        Map<String, Pitch> map = new HashMap<>();

        try {
            for (Field field : Class.forName(Pitch.class.getName()).getDeclaredFields()) {
                if (field.getType().isAssignableFrom(Pitch.class)) {
                    Pitch toAdd = (Pitch) field.get(Class.forName(Pitch.class.getName()));
                    String name = Accidental.convertToUTF8Symbols(toAdd.getName());
                    map.put(name, toAdd);
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return map;
    }

    Pitch(PitchClass pitchClass, Octave octave) {
        this.PITCH_CLASS = pitchClass;
        this.OCTAVE = octave;
        this.ABSOLUTE_PITCH = octave.getMidiStart() + pitchClass.ALIAS_BASE_MIDI_VALUE;
    }

    public static Pitch withName(String name) {
        String scrubbedName = Accidental.convertToUTF8Symbols(name);

        Matcher matcher = FIRST_PASS_PITCH_PATTERN.matcher(scrubbedName);

        if (matcher.matches()) {

            // First check the cache
            Pitch cachedPitch = STANDARD_PITCH_LOOKUP.get(scrubbedName);
            if (cachedPitch != null) {
                return cachedPitch;
            }

            // Valid non-standard pitches must be within playable range (0 - 127)
            String letter = matcher.group(1);
            String accidentals = matcher.group(2) != null ? matcher.group(2) : "";
            String octaveNumber = matcher.group(3);

            int letterSemitones = Letter.valueOf(letter).BASE_MIDI_VALUE;
            int accidentalsSemitones = Accidental.sumAccidentalsToSemitoneModifier(accidentals);
            int octaveSemitones = Integer.parseInt(octaveNumber) * 12;

            int total = (12 + letterSemitones + accidentalsSemitones) % 12 + octaveSemitones;

            if (total >= 0 && total < 128) {
                PitchClass pitchClass = PitchClass.withName(letter + accidentals, false);
                Octave octave = Octave.valueOf("OCTAVE_" + octaveNumber);
                return new Pitch(pitchClass, octave);
            } else {
                throw new IllegalArgumentException("Pitch " + name + " is outside of playable range");
            }

        }

        throw new IllegalArgumentException("Invalid pitch name provided: " + name);
    }

    String getName() {
        return PITCH_CLASS.getName() + OCTAVE.getNumber();
    }

    @Override
    public boolean isDiatonicTo(@NotNull KeySignature keySignature) {
        return this.PITCH_CLASS.isDiatonicTo(keySignature);
    }

    @Override
    public boolean isDiatonicTo(@NotNull IntervalSet intervalSet) {
        return this.PITCH_CLASS.isDiatonicTo(intervalSet);
    }

    @Override
    public boolean isEnharmonicTo(@NotNull Pitch other) {
        return this.PITCH_CLASS.isEnharmonicTo(other.PITCH_CLASS);
    }

    @Override
    public boolean isTransposable(boolean direction, @NotNull Interval interval) {
        return direction
                ? ABSOLUTE_PITCH + interval.getSemitones() <= 127
                : ABSOLUTE_PITCH - interval.getSemitones() >= 0;
    }

    @Override
    public boolean isTransposable(boolean direction, @NotNull PitchClass pitchClass) {
        return direction
                ? ABSOLUTE_PITCH + pitchClass.ALIAS_BASE_MIDI_VALUE <= 127
                : ABSOLUTE_PITCH - pitchClass.ALIAS_BASE_MIDI_VALUE >= 0;
    }

    @Override
    public boolean isTransposable(@NotNull Pitch pitch) {
        return true;
    }

    @Override
    public boolean isTransposable(@NotNull PitchClass pitchClass, @NotNull Octave octave) {
        return octave.getMidiStart() <= pitchClass.OCTAVE_RANGE.getMidiStart();
    }

    @Override
    public Pitch transpose(boolean direction, @NotNull Interval interval) {
        boolean attempted = false;
        if (isTransposable(direction, interval)) {

            int goal = PITCH_CLASS.ALIAS_BASE_MIDI_VALUE + OCTAVE.getMidiStart()
                    + (interval.getSemitones() * (direction ? 1 : -1));

            PitchClass newPitchClass = PITCH_CLASS.transpose(direction, interval);

            int newSemitones = newPitchClass.ALIAS_BASE_MIDI_VALUE + OCTAVE.getMidiStart();

            /*
             * At this point, the candidate Pitch must be enharmonic to the goal, +/- a few octaves.
             * If it isn't, then we know a bug exists in PitchClass.transpose(direction, interval).
             *
             * Figure out which octave we need to use below and return the Pitch.
             */
            if (newSemitones % 12 == goal % 12) {
                int newOctave = OCTAVE.getNumber();
                while (newSemitones != goal) {
                    if (newSemitones < goal) {
                        newSemitones += 12;
                        newOctave++;
                    } else {
                        newSemitones -= 12;
                        newOctave--;
                    }
                }
                return Pitch.withName(Accidental.convertToUTF8Symbols(newPitchClass.getName()) + newOctave);
            }
            attempted = true;
        }

        throw new RuntimeException(
                (attempted ? "Error transposing" : "Cannot transpose")
                        + " pitch " + getName()
                        + (direction ? " up " : " down ")
                        + "by interval " + interval.getCompoundShortName());
    }

    @Override
    public Pitch transpose(boolean direction, @NotNull PitchClass pitchClass) {
        if (isTransposable(direction, pitchClass)) {
            Pitch candidate1 = Pitch.withName(pitchClass.getName() + this.OCTAVE.getNumber());
            Pitch candidate2 = direction
                    ? Pitch.withName(pitchClass.getName() + (this.OCTAVE.getNumber() + 1))
                    : Pitch.withName(pitchClass.getName() + (this.OCTAVE.getNumber() - 1));

            if (direction) {
                return candidate1.ABSOLUTE_PITCH > this.ABSOLUTE_PITCH ? candidate1 : candidate2;
            } else {
                return candidate1.ABSOLUTE_PITCH < this.ABSOLUTE_PITCH ? candidate1 : candidate2;
            }
        }
        return this;
    }

    @Override
    public Pitch transpose(@NotNull Pitch pitch) {
        /*
         * We're transposing on the same enumerated type,
         * which is basically apples to apples.
         * No need for any special logic -- just return the requested pitch.
         */
        return pitch;
    }

    @Override
    public Pitch transpose(@NotNull PitchClass pitchClass, @NotNull Octave octave) {
        try {
            return Pitch.withName(pitchClass.getName() + octave.getNumber());
        } catch (Exception e) {
            throw new RuntimeException(
                    "Cannot transpose pitch "
                            + getName()
                            + " to "
                            + pitchClass.getName()
                            + octave.getNumber()
            );
        }
    }

    @Override
    public Sequence getMidiSequence() throws Exception {
        //TODO
        Sequence s = new Sequence(Sequence.PPQ, 1, 1);
        Track track = s.getTracks()[0];
        byte[] stuff = new byte[1];
        MidiMessage message = new MidiMessage(stuff) {
            @Override
            public Object clone() {
                return null;
            }
        };
        return s;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !other.getClass().isAssignableFrom(Pitch.class)) {
            return false;
        }

        Pitch comparison = (Pitch) other;

        return comparison.PITCH_CLASS.equals(this.PITCH_CLASS)
                && comparison.OCTAVE.equals(this.OCTAVE)
                && comparison.ABSOLUTE_PITCH == this.ABSOLUTE_PITCH;
    }
}
