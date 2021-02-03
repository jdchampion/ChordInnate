package chordinnate.model.musictheory.pitch;

import chordinnate.exception.ChordInnateException;
import chordinnate.midi.producer.MidiEventProducer;
import chordinnate.model.musictheory.notation.Accidental;
import chordinnate.model.musictheory.notation.Letter;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.musictheory.pitch.interval.Octave;
import chordinnate.model.musictheory.pitch.interval.set.IntervalDirection;
import chordinnate.model.musictheory.pitch.interval.set.IntervalSet;
import chordinnate.model.musictheory.pitch.key.KeySignature;
import chordinnate.model.playback.InstrumentCapablePlayable;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import javax.sound.midi.InvalidMidiDataException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Joseph on 4/18/16.
 */
@Slf4j
public class Pitch extends InstrumentCapablePlayable
        implements Transposable<Pitch>, Enharmonic<Pitch>, Diatonic {

    // Cbb10 is out of playable MIDI range, so it has been removed
    public static final Pitch C_DOUBLE_FLAT_0 = new Pitch(PitchClass.C_DOUBLE_FLAT, Octave.OCTAVE_0);
    public static final Pitch C_DOUBLE_FLAT_1 = new Pitch(PitchClass.C_DOUBLE_FLAT, Octave.OCTAVE_1);
    public static final Pitch C_DOUBLE_FLAT_2 = new Pitch(PitchClass.C_DOUBLE_FLAT, Octave.OCTAVE_2);
    public static final Pitch C_DOUBLE_FLAT_3 = new Pitch(PitchClass.C_DOUBLE_FLAT, Octave.OCTAVE_3);
    public static final Pitch C_DOUBLE_FLAT_4 = new Pitch(PitchClass.C_DOUBLE_FLAT, Octave.OCTAVE_4);
    public static final Pitch C_DOUBLE_FLAT_5 = new Pitch(PitchClass.C_DOUBLE_FLAT, Octave.OCTAVE_5);
    public static final Pitch C_DOUBLE_FLAT_6 = new Pitch(PitchClass.C_DOUBLE_FLAT, Octave.OCTAVE_6);
    public static final Pitch C_DOUBLE_FLAT_7 = new Pitch(PitchClass.C_DOUBLE_FLAT, Octave.OCTAVE_7);
    public static final Pitch C_DOUBLE_FLAT_8 = new Pitch(PitchClass.C_DOUBLE_FLAT, Octave.OCTAVE_8);
    public static final Pitch C_DOUBLE_FLAT_9 = new Pitch(PitchClass.C_DOUBLE_FLAT, Octave.OCTAVE_9);


    // Cb10 is out of playable MIDI range, so it has been removed
    public static final Pitch C_FLAT_0 = new Pitch(PitchClass.C_FLAT, Octave.OCTAVE_0);
    public static final Pitch C_FLAT_1 = new Pitch(PitchClass.C_FLAT, Octave.OCTAVE_1);
    public static final Pitch C_FLAT_2 = new Pitch(PitchClass.C_FLAT, Octave.OCTAVE_2);
    public static final Pitch C_FLAT_3 = new Pitch(PitchClass.C_FLAT, Octave.OCTAVE_3);
    public static final Pitch C_FLAT_4 = new Pitch(PitchClass.C_FLAT, Octave.OCTAVE_4);
    public static final Pitch C_FLAT_5 = new Pitch(PitchClass.C_FLAT, Octave.OCTAVE_5);
    public static final Pitch C_FLAT_6 = new Pitch(PitchClass.C_FLAT, Octave.OCTAVE_6);
    public static final Pitch C_FLAT_7 = new Pitch(PitchClass.C_FLAT, Octave.OCTAVE_7);
    public static final Pitch C_FLAT_8 = new Pitch(PitchClass.C_FLAT, Octave.OCTAVE_8);
    public static final Pitch C_FLAT_9 = new Pitch(PitchClass.C_FLAT, Octave.OCTAVE_9);

    public static final Pitch C_0 = new Pitch(PitchClass.C, Octave.OCTAVE_0);
    public static final Pitch C_1 = new Pitch(PitchClass.C, Octave.OCTAVE_1);
    public static final Pitch C_2 = new Pitch(PitchClass.C, Octave.OCTAVE_2);
    public static final Pitch C_3 = new Pitch(PitchClass.C, Octave.OCTAVE_3);
    public static final Pitch C_4 = new Pitch(PitchClass.C, Octave.OCTAVE_4);
    public static final Pitch C_5 = new Pitch(PitchClass.C, Octave.OCTAVE_5);
    public static final Pitch C_6 = new Pitch(PitchClass.C, Octave.OCTAVE_6);
    public static final Pitch C_7 = new Pitch(PitchClass.C, Octave.OCTAVE_7);
    public static final Pitch C_8 = new Pitch(PitchClass.C, Octave.OCTAVE_8);
    public static final Pitch C_9 = new Pitch(PitchClass.C, Octave.OCTAVE_9);
    public static final Pitch C_10 = new Pitch(PitchClass.C, Octave.OCTAVE_10);

    public static final Pitch C_SHARP_0 = new Pitch(PitchClass.C_SHARP, Octave.OCTAVE_0);
    public static final Pitch C_SHARP_1 = new Pitch(PitchClass.C_SHARP, Octave.OCTAVE_1);
    public static final Pitch C_SHARP_2 = new Pitch(PitchClass.C_SHARP, Octave.OCTAVE_2);
    public static final Pitch C_SHARP_3 = new Pitch(PitchClass.C_SHARP, Octave.OCTAVE_3);
    public static final Pitch C_SHARP_4 = new Pitch(PitchClass.C_SHARP, Octave.OCTAVE_4);
    public static final Pitch C_SHARP_5 = new Pitch(PitchClass.C_SHARP, Octave.OCTAVE_5);
    public static final Pitch C_SHARP_6 = new Pitch(PitchClass.C_SHARP, Octave.OCTAVE_6);
    public static final Pitch C_SHARP_7 = new Pitch(PitchClass.C_SHARP, Octave.OCTAVE_7);
    public static final Pitch C_SHARP_8 = new Pitch(PitchClass.C_SHARP, Octave.OCTAVE_8);
    public static final Pitch C_SHARP_9 = new Pitch(PitchClass.C_SHARP, Octave.OCTAVE_9);
    public static final Pitch C_SHARP_10 = new Pitch(PitchClass.C_SHARP, Octave.OCTAVE_10);

    public static final Pitch C_DOUBLE_SHARP_0 = new Pitch(PitchClass.C_DOUBLE_SHARP, Octave.OCTAVE_0);
    public static final Pitch C_DOUBLE_SHARP_1 = new Pitch(PitchClass.C_DOUBLE_SHARP, Octave.OCTAVE_1);
    public static final Pitch C_DOUBLE_SHARP_2 = new Pitch(PitchClass.C_DOUBLE_SHARP, Octave.OCTAVE_2);
    public static final Pitch C_DOUBLE_SHARP_3 = new Pitch(PitchClass.C_DOUBLE_SHARP, Octave.OCTAVE_3);
    public static final Pitch C_DOUBLE_SHARP_4 = new Pitch(PitchClass.C_DOUBLE_SHARP, Octave.OCTAVE_4);
    public static final Pitch C_DOUBLE_SHARP_5 = new Pitch(PitchClass.C_DOUBLE_SHARP, Octave.OCTAVE_5);
    public static final Pitch C_DOUBLE_SHARP_6 = new Pitch(PitchClass.C_DOUBLE_SHARP, Octave.OCTAVE_6);
    public static final Pitch C_DOUBLE_SHARP_7 = new Pitch(PitchClass.C_DOUBLE_SHARP, Octave.OCTAVE_7);
    public static final Pitch C_DOUBLE_SHARP_8 = new Pitch(PitchClass.C_DOUBLE_SHARP, Octave.OCTAVE_8);
    public static final Pitch C_DOUBLE_SHARP_9 = new Pitch(PitchClass.C_DOUBLE_SHARP, Octave.OCTAVE_9);
    public static final Pitch C_DOUBLE_SHARP_10 = new Pitch(PitchClass.C_DOUBLE_SHARP, Octave.OCTAVE_10);


    public static final Pitch D_DOUBLE_FLAT_0 = new Pitch(PitchClass.D_DOUBLE_FLAT, Octave.OCTAVE_0);
    public static final Pitch D_DOUBLE_FLAT_1 = new Pitch(PitchClass.D_DOUBLE_FLAT, Octave.OCTAVE_1);
    public static final Pitch D_DOUBLE_FLAT_2 = new Pitch(PitchClass.D_DOUBLE_FLAT, Octave.OCTAVE_2);
    public static final Pitch D_DOUBLE_FLAT_3 = new Pitch(PitchClass.D_DOUBLE_FLAT, Octave.OCTAVE_3);
    public static final Pitch D_DOUBLE_FLAT_4 = new Pitch(PitchClass.D_DOUBLE_FLAT, Octave.OCTAVE_4);
    public static final Pitch D_DOUBLE_FLAT_5 = new Pitch(PitchClass.D_DOUBLE_FLAT, Octave.OCTAVE_5);
    public static final Pitch D_DOUBLE_FLAT_6 = new Pitch(PitchClass.D_DOUBLE_FLAT, Octave.OCTAVE_6);
    public static final Pitch D_DOUBLE_FLAT_7 = new Pitch(PitchClass.D_DOUBLE_FLAT, Octave.OCTAVE_7);
    public static final Pitch D_DOUBLE_FLAT_8 = new Pitch(PitchClass.D_DOUBLE_FLAT, Octave.OCTAVE_8);
    public static final Pitch D_DOUBLE_FLAT_9 = new Pitch(PitchClass.D_DOUBLE_FLAT, Octave.OCTAVE_9);
    public static final Pitch D_DOUBLE_FLAT_10 = new Pitch(PitchClass.D_DOUBLE_FLAT, Octave.OCTAVE_10);

    public static final Pitch D_FLAT_0 = new Pitch(PitchClass.D_FLAT, Octave.OCTAVE_0);
    public static final Pitch D_FLAT_1 = new Pitch(PitchClass.D_FLAT, Octave.OCTAVE_1);
    public static final Pitch D_FLAT_2 = new Pitch(PitchClass.D_FLAT, Octave.OCTAVE_2);
    public static final Pitch D_FLAT_3 = new Pitch(PitchClass.D_FLAT, Octave.OCTAVE_3);
    public static final Pitch D_FLAT_4 = new Pitch(PitchClass.D_FLAT, Octave.OCTAVE_4);
    public static final Pitch D_FLAT_5 = new Pitch(PitchClass.D_FLAT, Octave.OCTAVE_5);
    public static final Pitch D_FLAT_6 = new Pitch(PitchClass.D_FLAT, Octave.OCTAVE_6);
    public static final Pitch D_FLAT_7 = new Pitch(PitchClass.D_FLAT, Octave.OCTAVE_7);
    public static final Pitch D_FLAT_8 = new Pitch(PitchClass.D_FLAT, Octave.OCTAVE_8);
    public static final Pitch D_FLAT_9 = new Pitch(PitchClass.D_FLAT, Octave.OCTAVE_9);
    public static final Pitch D_FLAT_10 = new Pitch(PitchClass.D_FLAT, Octave.OCTAVE_10);

    public static final Pitch D_0 = new Pitch(PitchClass.D, Octave.OCTAVE_0);
    public static final Pitch D_1 = new Pitch(PitchClass.D, Octave.OCTAVE_1);
    public static final Pitch D_2 = new Pitch(PitchClass.D, Octave.OCTAVE_2);
    public static final Pitch D_3 = new Pitch(PitchClass.D, Octave.OCTAVE_3);
    public static final Pitch D_4 = new Pitch(PitchClass.D, Octave.OCTAVE_4);
    public static final Pitch D_5 = new Pitch(PitchClass.D, Octave.OCTAVE_5);
    public static final Pitch D_6 = new Pitch(PitchClass.D, Octave.OCTAVE_6);
    public static final Pitch D_7 = new Pitch(PitchClass.D, Octave.OCTAVE_7);
    public static final Pitch D_8 = new Pitch(PitchClass.D, Octave.OCTAVE_8);
    public static final Pitch D_9 = new Pitch(PitchClass.D, Octave.OCTAVE_9);
    public static final Pitch D_10 = new Pitch(PitchClass.D, Octave.OCTAVE_10);

    public static final Pitch D_SHARP_0 = new Pitch(PitchClass.D_SHARP, Octave.OCTAVE_0);
    public static final Pitch D_SHARP_1 = new Pitch(PitchClass.D_SHARP, Octave.OCTAVE_1);
    public static final Pitch D_SHARP_2 = new Pitch(PitchClass.D_SHARP, Octave.OCTAVE_2);
    public static final Pitch D_SHARP_3 = new Pitch(PitchClass.D_SHARP, Octave.OCTAVE_3);
    public static final Pitch D_SHARP_4 = new Pitch(PitchClass.D_SHARP, Octave.OCTAVE_4);
    public static final Pitch D_SHARP_5 = new Pitch(PitchClass.D_SHARP, Octave.OCTAVE_5);
    public static final Pitch D_SHARP_6 = new Pitch(PitchClass.D_SHARP, Octave.OCTAVE_6);
    public static final Pitch D_SHARP_7 = new Pitch(PitchClass.D_SHARP, Octave.OCTAVE_7);
    public static final Pitch D_SHARP_8 = new Pitch(PitchClass.D_SHARP, Octave.OCTAVE_8);
    public static final Pitch D_SHARP_9 = new Pitch(PitchClass.D_SHARP, Octave.OCTAVE_9);
    public static final Pitch D_SHARP_10 = new Pitch(PitchClass.D_SHARP, Octave.OCTAVE_10);

    public static final Pitch D_DOUBLE_SHARP_0 = new Pitch(PitchClass.D_DOUBLE_SHARP, Octave.OCTAVE_0);
    public static final Pitch D_DOUBLE_SHARP_1 = new Pitch(PitchClass.D_DOUBLE_SHARP, Octave.OCTAVE_1);
    public static final Pitch D_DOUBLE_SHARP_2 = new Pitch(PitchClass.D_DOUBLE_SHARP, Octave.OCTAVE_2);
    public static final Pitch D_DOUBLE_SHARP_3 = new Pitch(PitchClass.D_DOUBLE_SHARP, Octave.OCTAVE_3);
    public static final Pitch D_DOUBLE_SHARP_4 = new Pitch(PitchClass.D_DOUBLE_SHARP, Octave.OCTAVE_4);
    public static final Pitch D_DOUBLE_SHARP_5 = new Pitch(PitchClass.D_DOUBLE_SHARP, Octave.OCTAVE_5);
    public static final Pitch D_DOUBLE_SHARP_6 = new Pitch(PitchClass.D_DOUBLE_SHARP, Octave.OCTAVE_6);
    public static final Pitch D_DOUBLE_SHARP_7 = new Pitch(PitchClass.D_DOUBLE_SHARP, Octave.OCTAVE_7);
    public static final Pitch D_DOUBLE_SHARP_8 = new Pitch(PitchClass.D_DOUBLE_SHARP, Octave.OCTAVE_8);
    public static final Pitch D_DOUBLE_SHARP_9 = new Pitch(PitchClass.D_DOUBLE_SHARP, Octave.OCTAVE_9);
    public static final Pitch D_DOUBLE_SHARP_10 = new Pitch(PitchClass.D_DOUBLE_SHARP, Octave.OCTAVE_10);


    public static final Pitch E_DOUBLE_FLAT_0 = new Pitch(PitchClass.E_DOUBLE_FLAT, Octave.OCTAVE_0);
    public static final Pitch E_DOUBLE_FLAT_1 = new Pitch(PitchClass.E_DOUBLE_FLAT, Octave.OCTAVE_1);
    public static final Pitch E_DOUBLE_FLAT_2 = new Pitch(PitchClass.E_DOUBLE_FLAT, Octave.OCTAVE_2);
    public static final Pitch E_DOUBLE_FLAT_3 = new Pitch(PitchClass.E_DOUBLE_FLAT, Octave.OCTAVE_3);
    public static final Pitch E_DOUBLE_FLAT_4 = new Pitch(PitchClass.E_DOUBLE_FLAT, Octave.OCTAVE_4);
    public static final Pitch E_DOUBLE_FLAT_5 = new Pitch(PitchClass.E_DOUBLE_FLAT, Octave.OCTAVE_5);
    public static final Pitch E_DOUBLE_FLAT_6 = new Pitch(PitchClass.E_DOUBLE_FLAT, Octave.OCTAVE_6);
    public static final Pitch E_DOUBLE_FLAT_7 = new Pitch(PitchClass.E_DOUBLE_FLAT, Octave.OCTAVE_7);
    public static final Pitch E_DOUBLE_FLAT_8 = new Pitch(PitchClass.E_DOUBLE_FLAT, Octave.OCTAVE_8);
    public static final Pitch E_DOUBLE_FLAT_9 = new Pitch(PitchClass.E_DOUBLE_FLAT, Octave.OCTAVE_9);
    public static final Pitch E_DOUBLE_FLAT_10 = new Pitch(PitchClass.E_DOUBLE_FLAT, Octave.OCTAVE_10);

    public static final Pitch E_FLAT_0 = new Pitch(PitchClass.E_FLAT, Octave.OCTAVE_0);
    public static final Pitch E_FLAT_1 = new Pitch(PitchClass.E_FLAT, Octave.OCTAVE_1);
    public static final Pitch E_FLAT_2 = new Pitch(PitchClass.E_FLAT, Octave.OCTAVE_2);
    public static final Pitch E_FLAT_3 = new Pitch(PitchClass.E_FLAT, Octave.OCTAVE_3);
    public static final Pitch E_FLAT_4 = new Pitch(PitchClass.E_FLAT, Octave.OCTAVE_4);
    public static final Pitch E_FLAT_5 = new Pitch(PitchClass.E_FLAT, Octave.OCTAVE_5);
    public static final Pitch E_FLAT_6 = new Pitch(PitchClass.E_FLAT, Octave.OCTAVE_6);
    public static final Pitch E_FLAT_7 = new Pitch(PitchClass.E_FLAT, Octave.OCTAVE_7);
    public static final Pitch E_FLAT_8 = new Pitch(PitchClass.E_FLAT, Octave.OCTAVE_8);
    public static final Pitch E_FLAT_9 = new Pitch(PitchClass.E_FLAT, Octave.OCTAVE_9);
    public static final Pitch E_FLAT_10 = new Pitch(PitchClass.E_FLAT, Octave.OCTAVE_10);

    public static final Pitch E_0 = new Pitch(PitchClass.E, Octave.OCTAVE_0);
    public static final Pitch E_1 = new Pitch(PitchClass.E, Octave.OCTAVE_1);
    public static final Pitch E_2 = new Pitch(PitchClass.E, Octave.OCTAVE_2);
    public static final Pitch E_3 = new Pitch(PitchClass.E, Octave.OCTAVE_3);
    public static final Pitch E_4 = new Pitch(PitchClass.E, Octave.OCTAVE_4);
    public static final Pitch E_5 = new Pitch(PitchClass.E, Octave.OCTAVE_5);
    public static final Pitch E_6 = new Pitch(PitchClass.E, Octave.OCTAVE_6);
    public static final Pitch E_7 = new Pitch(PitchClass.E, Octave.OCTAVE_7);
    public static final Pitch E_8 = new Pitch(PitchClass.E, Octave.OCTAVE_8);
    public static final Pitch E_9 = new Pitch(PitchClass.E, Octave.OCTAVE_9);
    public static final Pitch E_10 = new Pitch(PitchClass.E, Octave.OCTAVE_10);

    public static final Pitch E_SHARP_0 = new Pitch(PitchClass.E_SHARP, Octave.OCTAVE_0);
    public static final Pitch E_SHARP_1 = new Pitch(PitchClass.E_SHARP, Octave.OCTAVE_1);
    public static final Pitch E_SHARP_2 = new Pitch(PitchClass.E_SHARP, Octave.OCTAVE_2);
    public static final Pitch E_SHARP_3 = new Pitch(PitchClass.E_SHARP, Octave.OCTAVE_3);
    public static final Pitch E_SHARP_4 = new Pitch(PitchClass.E_SHARP, Octave.OCTAVE_4);
    public static final Pitch E_SHARP_5 = new Pitch(PitchClass.E_SHARP, Octave.OCTAVE_5);
    public static final Pitch E_SHARP_6 = new Pitch(PitchClass.E_SHARP, Octave.OCTAVE_6);
    public static final Pitch E_SHARP_7 = new Pitch(PitchClass.E_SHARP, Octave.OCTAVE_7);
    public static final Pitch E_SHARP_8 = new Pitch(PitchClass.E_SHARP, Octave.OCTAVE_8);
    public static final Pitch E_SHARP_9 = new Pitch(PitchClass.E_SHARP, Octave.OCTAVE_9);
    public static final Pitch E_SHARP_10 = new Pitch(PitchClass.E_SHARP, Octave.OCTAVE_10);

    public static final Pitch E_DOUBLE_SHARP_0 = new Pitch(PitchClass.E_DOUBLE_SHARP, Octave.OCTAVE_0);
    public static final Pitch E_DOUBLE_SHARP_1 = new Pitch(PitchClass.E_DOUBLE_SHARP, Octave.OCTAVE_1);
    public static final Pitch E_DOUBLE_SHARP_2 = new Pitch(PitchClass.E_DOUBLE_SHARP, Octave.OCTAVE_2);
    public static final Pitch E_DOUBLE_SHARP_3 = new Pitch(PitchClass.E_DOUBLE_SHARP, Octave.OCTAVE_3);
    public static final Pitch E_DOUBLE_SHARP_4 = new Pitch(PitchClass.E_DOUBLE_SHARP, Octave.OCTAVE_4);
    public static final Pitch E_DOUBLE_SHARP_5 = new Pitch(PitchClass.E_DOUBLE_SHARP, Octave.OCTAVE_5);
    public static final Pitch E_DOUBLE_SHARP_6 = new Pitch(PitchClass.E_DOUBLE_SHARP, Octave.OCTAVE_6);
    public static final Pitch E_DOUBLE_SHARP_7 = new Pitch(PitchClass.E_DOUBLE_SHARP, Octave.OCTAVE_7);
    public static final Pitch E_DOUBLE_SHARP_8 = new Pitch(PitchClass.E_DOUBLE_SHARP, Octave.OCTAVE_8);
    public static final Pitch E_DOUBLE_SHARP_9 = new Pitch(PitchClass.E_DOUBLE_SHARP, Octave.OCTAVE_9);
    public static final Pitch E_DOUBLE_SHARP_10 = new Pitch(PitchClass.E_DOUBLE_SHARP, Octave.OCTAVE_10);


    public static final Pitch F_DOUBLE_FLAT_0 = new Pitch(PitchClass.F_DOUBLE_FLAT, Octave.OCTAVE_0);
    public static final Pitch F_DOUBLE_FLAT_1 = new Pitch(PitchClass.F_DOUBLE_FLAT, Octave.OCTAVE_1);
    public static final Pitch F_DOUBLE_FLAT_2 = new Pitch(PitchClass.F_DOUBLE_FLAT, Octave.OCTAVE_2);
    public static final Pitch F_DOUBLE_FLAT_3 = new Pitch(PitchClass.F_DOUBLE_FLAT, Octave.OCTAVE_3);
    public static final Pitch F_DOUBLE_FLAT_4 = new Pitch(PitchClass.F_DOUBLE_FLAT, Octave.OCTAVE_4);
    public static final Pitch F_DOUBLE_FLAT_5 = new Pitch(PitchClass.F_DOUBLE_FLAT, Octave.OCTAVE_5);
    public static final Pitch F_DOUBLE_FLAT_6 = new Pitch(PitchClass.F_DOUBLE_FLAT, Octave.OCTAVE_6);
    public static final Pitch F_DOUBLE_FLAT_7 = new Pitch(PitchClass.F_DOUBLE_FLAT, Octave.OCTAVE_7);
    public static final Pitch F_DOUBLE_FLAT_8 = new Pitch(PitchClass.F_DOUBLE_FLAT, Octave.OCTAVE_8);
    public static final Pitch F_DOUBLE_FLAT_9 = new Pitch(PitchClass.F_DOUBLE_FLAT, Octave.OCTAVE_9);
    public static final Pitch F_DOUBLE_FLAT_10 = new Pitch(PitchClass.F_DOUBLE_FLAT, Octave.OCTAVE_10);

    public static final Pitch F_FLAT_0 = new Pitch(PitchClass.F_FLAT, Octave.OCTAVE_0);
    public static final Pitch F_FLAT_1 = new Pitch(PitchClass.F_FLAT, Octave.OCTAVE_1);
    public static final Pitch F_FLAT_2 = new Pitch(PitchClass.F_FLAT, Octave.OCTAVE_2);
    public static final Pitch F_FLAT_3 = new Pitch(PitchClass.F_FLAT, Octave.OCTAVE_3);
    public static final Pitch F_FLAT_4 = new Pitch(PitchClass.F_FLAT, Octave.OCTAVE_4);
    public static final Pitch F_FLAT_5 = new Pitch(PitchClass.F_FLAT, Octave.OCTAVE_5);
    public static final Pitch F_FLAT_6 = new Pitch(PitchClass.F_FLAT, Octave.OCTAVE_6);
    public static final Pitch F_FLAT_7 = new Pitch(PitchClass.F_FLAT, Octave.OCTAVE_7);
    public static final Pitch F_FLAT_8 = new Pitch(PitchClass.F_FLAT, Octave.OCTAVE_8);
    public static final Pitch F_FLAT_9 = new Pitch(PitchClass.F_FLAT, Octave.OCTAVE_9);
    public static final Pitch F_FLAT_10 = new Pitch(PitchClass.F_FLAT, Octave.OCTAVE_10);

    public static final Pitch F_0 = new Pitch(PitchClass.F, Octave.OCTAVE_0);
    public static final Pitch F_1 = new Pitch(PitchClass.F, Octave.OCTAVE_1);
    public static final Pitch F_2 = new Pitch(PitchClass.F, Octave.OCTAVE_2);
    public static final Pitch F_3 = new Pitch(PitchClass.F, Octave.OCTAVE_3);
    public static final Pitch F_4 = new Pitch(PitchClass.F, Octave.OCTAVE_4);
    public static final Pitch F_5 = new Pitch(PitchClass.F, Octave.OCTAVE_5);
    public static final Pitch F_6 = new Pitch(PitchClass.F, Octave.OCTAVE_6);
    public static final Pitch F_7 = new Pitch(PitchClass.F, Octave.OCTAVE_7);
    public static final Pitch F_8 = new Pitch(PitchClass.F, Octave.OCTAVE_8);
    public static final Pitch F_9 = new Pitch(PitchClass.F, Octave.OCTAVE_9);
    public static final Pitch F_10 = new Pitch(PitchClass.F, Octave.OCTAVE_10);

    public static final Pitch F_SHARP_0 = new Pitch(PitchClass.F_SHARP, Octave.OCTAVE_0);
    public static final Pitch F_SHARP_1 = new Pitch(PitchClass.F_SHARP, Octave.OCTAVE_1);
    public static final Pitch F_SHARP_2 = new Pitch(PitchClass.F_SHARP, Octave.OCTAVE_2);
    public static final Pitch F_SHARP_3 = new Pitch(PitchClass.F_SHARP, Octave.OCTAVE_3);
    public static final Pitch F_SHARP_4 = new Pitch(PitchClass.F_SHARP, Octave.OCTAVE_4);
    public static final Pitch F_SHARP_5 = new Pitch(PitchClass.F_SHARP, Octave.OCTAVE_5);
    public static final Pitch F_SHARP_6 = new Pitch(PitchClass.F_SHARP, Octave.OCTAVE_6);
    public static final Pitch F_SHARP_7 = new Pitch(PitchClass.F_SHARP, Octave.OCTAVE_7);
    public static final Pitch F_SHARP_8 = new Pitch(PitchClass.F_SHARP, Octave.OCTAVE_8);
    public static final Pitch F_SHARP_9 = new Pitch(PitchClass.F_SHARP, Octave.OCTAVE_9);
    public static final Pitch F_SHARP_10 = new Pitch(PitchClass.F_SHARP, Octave.OCTAVE_10);

    public static final Pitch F_DOUBLE_SHARP_0 = new Pitch(PitchClass.F_DOUBLE_SHARP, Octave.OCTAVE_0);
    public static final Pitch F_DOUBLE_SHARP_1 = new Pitch(PitchClass.F_DOUBLE_SHARP, Octave.OCTAVE_1);
    public static final Pitch F_DOUBLE_SHARP_2 = new Pitch(PitchClass.F_DOUBLE_SHARP, Octave.OCTAVE_2);
    public static final Pitch F_DOUBLE_SHARP_3 = new Pitch(PitchClass.F_DOUBLE_SHARP, Octave.OCTAVE_3);
    public static final Pitch F_DOUBLE_SHARP_4 = new Pitch(PitchClass.F_DOUBLE_SHARP, Octave.OCTAVE_4);
    public static final Pitch F_DOUBLE_SHARP_5 = new Pitch(PitchClass.F_DOUBLE_SHARP, Octave.OCTAVE_5);
    public static final Pitch F_DOUBLE_SHARP_6 = new Pitch(PitchClass.F_DOUBLE_SHARP, Octave.OCTAVE_6);
    public static final Pitch F_DOUBLE_SHARP_7 = new Pitch(PitchClass.F_DOUBLE_SHARP, Octave.OCTAVE_7);
    public static final Pitch F_DOUBLE_SHARP_8 = new Pitch(PitchClass.F_DOUBLE_SHARP, Octave.OCTAVE_8);
    public static final Pitch F_DOUBLE_SHARP_9 = new Pitch(PitchClass.F_DOUBLE_SHARP, Octave.OCTAVE_9);
    public static final Pitch F_DOUBLE_SHARP_10 = new Pitch(PitchClass.F_DOUBLE_SHARP, Octave.OCTAVE_10);


    public static final Pitch G_DOUBLE_FLAT_0 = new Pitch(PitchClass.G_DOUBLE_FLAT, Octave.OCTAVE_0);
    public static final Pitch G_DOUBLE_FLAT_1 = new Pitch(PitchClass.G_DOUBLE_FLAT, Octave.OCTAVE_1);
    public static final Pitch G_DOUBLE_FLAT_2 = new Pitch(PitchClass.G_DOUBLE_FLAT, Octave.OCTAVE_2);
    public static final Pitch G_DOUBLE_FLAT_3 = new Pitch(PitchClass.G_DOUBLE_FLAT, Octave.OCTAVE_3);
    public static final Pitch G_DOUBLE_FLAT_4 = new Pitch(PitchClass.G_DOUBLE_FLAT, Octave.OCTAVE_4);
    public static final Pitch G_DOUBLE_FLAT_5 = new Pitch(PitchClass.G_DOUBLE_FLAT, Octave.OCTAVE_5);
    public static final Pitch G_DOUBLE_FLAT_6 = new Pitch(PitchClass.G_DOUBLE_FLAT, Octave.OCTAVE_6);
    public static final Pitch G_DOUBLE_FLAT_7 = new Pitch(PitchClass.G_DOUBLE_FLAT, Octave.OCTAVE_7);
    public static final Pitch G_DOUBLE_FLAT_8 = new Pitch(PitchClass.G_DOUBLE_FLAT, Octave.OCTAVE_8);
    public static final Pitch G_DOUBLE_FLAT_9 = new Pitch(PitchClass.G_DOUBLE_FLAT, Octave.OCTAVE_9);
    public static final Pitch G_DOUBLE_FLAT_10 = new Pitch(PitchClass.G_DOUBLE_FLAT, Octave.OCTAVE_10);

    public static final Pitch G_FLAT_0 = new Pitch(PitchClass.G_FLAT, Octave.OCTAVE_0);
    public static final Pitch G_FLAT_1 = new Pitch(PitchClass.G_FLAT, Octave.OCTAVE_1);
    public static final Pitch G_FLAT_2 = new Pitch(PitchClass.G_FLAT, Octave.OCTAVE_2);
    public static final Pitch G_FLAT_3 = new Pitch(PitchClass.G_FLAT, Octave.OCTAVE_3);
    public static final Pitch G_FLAT_4 = new Pitch(PitchClass.G_FLAT, Octave.OCTAVE_4);
    public static final Pitch G_FLAT_5 = new Pitch(PitchClass.G_FLAT, Octave.OCTAVE_5);
    public static final Pitch G_FLAT_6 = new Pitch(PitchClass.G_FLAT, Octave.OCTAVE_6);
    public static final Pitch G_FLAT_7 = new Pitch(PitchClass.G_FLAT, Octave.OCTAVE_7);
    public static final Pitch G_FLAT_8 = new Pitch(PitchClass.G_FLAT, Octave.OCTAVE_8);
    public static final Pitch G_FLAT_9 = new Pitch(PitchClass.G_FLAT, Octave.OCTAVE_9);
    public static final Pitch G_FLAT_10 = new Pitch(PitchClass.G_FLAT, Octave.OCTAVE_10);

    public static final Pitch G_0 = new Pitch(PitchClass.G, Octave.OCTAVE_0);
    public static final Pitch G_1 = new Pitch(PitchClass.G, Octave.OCTAVE_1);
    public static final Pitch G_2 = new Pitch(PitchClass.G, Octave.OCTAVE_2);
    public static final Pitch G_3 = new Pitch(PitchClass.G, Octave.OCTAVE_3);
    public static final Pitch G_4 = new Pitch(PitchClass.G, Octave.OCTAVE_4);
    public static final Pitch G_5 = new Pitch(PitchClass.G, Octave.OCTAVE_5);
    public static final Pitch G_6 = new Pitch(PitchClass.G, Octave.OCTAVE_6);
    public static final Pitch G_7 = new Pitch(PitchClass.G, Octave.OCTAVE_7);
    public static final Pitch G_8 = new Pitch(PitchClass.G, Octave.OCTAVE_8);
    public static final Pitch G_9 = new Pitch(PitchClass.G, Octave.OCTAVE_9);
    public static final Pitch G_10 = new Pitch(PitchClass.G, Octave.OCTAVE_10);

    /*
     *  All Pitches beyond this point that are enharmonically higher than G
     *  are not playable on OCTAVE_10 (because they are out of the MIDI range),
     *  so they must not contain OCTAVE_10.
     */

    public static final Pitch G_SHARP_0 = new Pitch(PitchClass.G_SHARP, Octave.OCTAVE_0);
    public static final Pitch G_SHARP_1 = new Pitch(PitchClass.G_SHARP, Octave.OCTAVE_1);
    public static final Pitch G_SHARP_2 = new Pitch(PitchClass.G_SHARP, Octave.OCTAVE_2);
    public static final Pitch G_SHARP_3 = new Pitch(PitchClass.G_SHARP, Octave.OCTAVE_3);
    public static final Pitch G_SHARP_4 = new Pitch(PitchClass.G_SHARP, Octave.OCTAVE_4);
    public static final Pitch G_SHARP_5 = new Pitch(PitchClass.G_SHARP, Octave.OCTAVE_5);
    public static final Pitch G_SHARP_6 = new Pitch(PitchClass.G_SHARP, Octave.OCTAVE_6);
    public static final Pitch G_SHARP_7 = new Pitch(PitchClass.G_SHARP, Octave.OCTAVE_7);
    public static final Pitch G_SHARP_8 = new Pitch(PitchClass.G_SHARP, Octave.OCTAVE_8);
    public static final Pitch G_SHARP_9 = new Pitch(PitchClass.G_SHARP, Octave.OCTAVE_9);

    public static final Pitch G_DOUBLE_SHARP_0 = new Pitch(PitchClass.G_DOUBLE_SHARP, Octave.OCTAVE_0);
    public static final Pitch G_DOUBLE_SHARP_1 = new Pitch(PitchClass.G_DOUBLE_SHARP, Octave.OCTAVE_1);
    public static final Pitch G_DOUBLE_SHARP_2 = new Pitch(PitchClass.G_DOUBLE_SHARP, Octave.OCTAVE_2);
    public static final Pitch G_DOUBLE_SHARP_3 = new Pitch(PitchClass.G_DOUBLE_SHARP, Octave.OCTAVE_3);
    public static final Pitch G_DOUBLE_SHARP_4 = new Pitch(PitchClass.G_DOUBLE_SHARP, Octave.OCTAVE_4);
    public static final Pitch G_DOUBLE_SHARP_5 = new Pitch(PitchClass.G_DOUBLE_SHARP, Octave.OCTAVE_5);
    public static final Pitch G_DOUBLE_SHARP_6 = new Pitch(PitchClass.G_DOUBLE_SHARP, Octave.OCTAVE_6);
    public static final Pitch G_DOUBLE_SHARP_7 = new Pitch(PitchClass.G_DOUBLE_SHARP, Octave.OCTAVE_7);
    public static final Pitch G_DOUBLE_SHARP_8 = new Pitch(PitchClass.G_DOUBLE_SHARP, Octave.OCTAVE_8);
    public static final Pitch G_DOUBLE_SHARP_9 = new Pitch(PitchClass.G_DOUBLE_SHARP, Octave.OCTAVE_9);


    public static final Pitch A_DOUBLE_FLAT_0 = new Pitch(PitchClass.A_DOUBLE_FLAT, Octave.OCTAVE_0);
    public static final Pitch A_DOUBLE_FLAT_1 = new Pitch(PitchClass.A_DOUBLE_FLAT, Octave.OCTAVE_1);
    public static final Pitch A_DOUBLE_FLAT_2 = new Pitch(PitchClass.A_DOUBLE_FLAT, Octave.OCTAVE_2);
    public static final Pitch A_DOUBLE_FLAT_3 = new Pitch(PitchClass.A_DOUBLE_FLAT, Octave.OCTAVE_3);
    public static final Pitch A_DOUBLE_FLAT_4 = new Pitch(PitchClass.A_DOUBLE_FLAT, Octave.OCTAVE_4);
    public static final Pitch A_DOUBLE_FLAT_5 = new Pitch(PitchClass.A_DOUBLE_FLAT, Octave.OCTAVE_5);
    public static final Pitch A_DOUBLE_FLAT_6 = new Pitch(PitchClass.A_DOUBLE_FLAT, Octave.OCTAVE_6);
    public static final Pitch A_DOUBLE_FLAT_7 = new Pitch(PitchClass.A_DOUBLE_FLAT, Octave.OCTAVE_7);
    public static final Pitch A_DOUBLE_FLAT_8 = new Pitch(PitchClass.A_DOUBLE_FLAT, Octave.OCTAVE_8);
    public static final Pitch A_DOUBLE_FLAT_9 = new Pitch(PitchClass.A_DOUBLE_FLAT, Octave.OCTAVE_9);
    public static final Pitch A_DOUBLE_FLAT_10 = new Pitch(PitchClass.A_DOUBLE_FLAT, Octave.OCTAVE_10);

    public static final Pitch A_FLAT_0 = new Pitch(PitchClass.A_FLAT, Octave.OCTAVE_0);
    public static final Pitch A_FLAT_1 = new Pitch(PitchClass.A_FLAT, Octave.OCTAVE_1);
    public static final Pitch A_FLAT_2 = new Pitch(PitchClass.A_FLAT, Octave.OCTAVE_2);
    public static final Pitch A_FLAT_3 = new Pitch(PitchClass.A_FLAT, Octave.OCTAVE_3);
    public static final Pitch A_FLAT_4 = new Pitch(PitchClass.A_FLAT, Octave.OCTAVE_4);
    public static final Pitch A_FLAT_5 = new Pitch(PitchClass.A_FLAT, Octave.OCTAVE_5);
    public static final Pitch A_FLAT_6 = new Pitch(PitchClass.A_FLAT, Octave.OCTAVE_6);
    public static final Pitch A_FLAT_7 = new Pitch(PitchClass.A_FLAT, Octave.OCTAVE_7);
    public static final Pitch A_FLAT_8 = new Pitch(PitchClass.A_FLAT, Octave.OCTAVE_8);
    public static final Pitch A_FLAT_9 = new Pitch(PitchClass.A_FLAT, Octave.OCTAVE_9);

    public static final Pitch A_0 = new Pitch(PitchClass.A, Octave.OCTAVE_0);
    public static final Pitch A_1 = new Pitch(PitchClass.A, Octave.OCTAVE_1);
    public static final Pitch A_2 = new Pitch(PitchClass.A, Octave.OCTAVE_2);
    public static final Pitch A_3 = new Pitch(PitchClass.A, Octave.OCTAVE_3);
    public static final Pitch A_4 = new Pitch(PitchClass.A, Octave.OCTAVE_4);
    public static final Pitch A_5 = new Pitch(PitchClass.A, Octave.OCTAVE_5);
    public static final Pitch A_6 = new Pitch(PitchClass.A, Octave.OCTAVE_6);
    public static final Pitch A_7 = new Pitch(PitchClass.A, Octave.OCTAVE_7);
    public static final Pitch A_8 = new Pitch(PitchClass.A, Octave.OCTAVE_8);
    public static final Pitch A_9 = new Pitch(PitchClass.A, Octave.OCTAVE_9);

    public static final Pitch A_SHARP_0 = new Pitch(PitchClass.A_SHARP, Octave.OCTAVE_0);
    public static final Pitch A_SHARP_1 = new Pitch(PitchClass.A_SHARP, Octave.OCTAVE_1);
    public static final Pitch A_SHARP_2 = new Pitch(PitchClass.A_SHARP, Octave.OCTAVE_2);
    public static final Pitch A_SHARP_3 = new Pitch(PitchClass.A_SHARP, Octave.OCTAVE_3);
    public static final Pitch A_SHARP_4 = new Pitch(PitchClass.A_SHARP, Octave.OCTAVE_4);
    public static final Pitch A_SHARP_5 = new Pitch(PitchClass.A_SHARP, Octave.OCTAVE_5);
    public static final Pitch A_SHARP_6 = new Pitch(PitchClass.A_SHARP, Octave.OCTAVE_6);
    public static final Pitch A_SHARP_7 = new Pitch(PitchClass.A_SHARP, Octave.OCTAVE_7);
    public static final Pitch A_SHARP_8 = new Pitch(PitchClass.A_SHARP, Octave.OCTAVE_8);
    public static final Pitch A_SHARP_9 = new Pitch(PitchClass.A_SHARP, Octave.OCTAVE_9);

    public static final Pitch A_DOUBLE_SHARP_0 = new Pitch(PitchClass.A_DOUBLE_SHARP, Octave.OCTAVE_0);
    public static final Pitch A_DOUBLE_SHARP_1 = new Pitch(PitchClass.A_DOUBLE_SHARP, Octave.OCTAVE_1);
    public static final Pitch A_DOUBLE_SHARP_2 = new Pitch(PitchClass.A_DOUBLE_SHARP, Octave.OCTAVE_2);
    public static final Pitch A_DOUBLE_SHARP_3 = new Pitch(PitchClass.A_DOUBLE_SHARP, Octave.OCTAVE_3);
    public static final Pitch A_DOUBLE_SHARP_4 = new Pitch(PitchClass.A_DOUBLE_SHARP, Octave.OCTAVE_4);
    public static final Pitch A_DOUBLE_SHARP_5 = new Pitch(PitchClass.A_DOUBLE_SHARP, Octave.OCTAVE_5);
    public static final Pitch A_DOUBLE_SHARP_6 = new Pitch(PitchClass.A_DOUBLE_SHARP, Octave.OCTAVE_6);
    public static final Pitch A_DOUBLE_SHARP_7 = new Pitch(PitchClass.A_DOUBLE_SHARP, Octave.OCTAVE_7);
    public static final Pitch A_DOUBLE_SHARP_8 = new Pitch(PitchClass.A_DOUBLE_SHARP, Octave.OCTAVE_8);
    public static final Pitch A_DOUBLE_SHARP_9 = new Pitch(PitchClass.A_DOUBLE_SHARP, Octave.OCTAVE_9);


    public static final Pitch B_DOUBLE_FLAT_0 = new Pitch(PitchClass.B_DOUBLE_FLAT, Octave.OCTAVE_0);
    public static final Pitch B_DOUBLE_FLAT_1 = new Pitch(PitchClass.B_DOUBLE_FLAT, Octave.OCTAVE_1);
    public static final Pitch B_DOUBLE_FLAT_2 = new Pitch(PitchClass.B_DOUBLE_FLAT, Octave.OCTAVE_2);
    public static final Pitch B_DOUBLE_FLAT_3 = new Pitch(PitchClass.B_DOUBLE_FLAT, Octave.OCTAVE_3);
    public static final Pitch B_DOUBLE_FLAT_4 = new Pitch(PitchClass.B_DOUBLE_FLAT, Octave.OCTAVE_4);
    public static final Pitch B_DOUBLE_FLAT_5 = new Pitch(PitchClass.B_DOUBLE_FLAT, Octave.OCTAVE_5);
    public static final Pitch B_DOUBLE_FLAT_6 = new Pitch(PitchClass.B_DOUBLE_FLAT, Octave.OCTAVE_6);
    public static final Pitch B_DOUBLE_FLAT_7 = new Pitch(PitchClass.B_DOUBLE_FLAT, Octave.OCTAVE_7);
    public static final Pitch B_DOUBLE_FLAT_8 = new Pitch(PitchClass.B_DOUBLE_FLAT, Octave.OCTAVE_8);
    public static final Pitch B_DOUBLE_FLAT_9 = new Pitch(PitchClass.B_DOUBLE_FLAT, Octave.OCTAVE_9);

    public static final Pitch B_FLAT_0 = new Pitch(PitchClass.B_FLAT, Octave.OCTAVE_0);
    public static final Pitch B_FLAT_1 = new Pitch(PitchClass.B_FLAT, Octave.OCTAVE_1);
    public static final Pitch B_FLAT_2 = new Pitch(PitchClass.B_FLAT, Octave.OCTAVE_2);
    public static final Pitch B_FLAT_3 = new Pitch(PitchClass.B_FLAT, Octave.OCTAVE_3);
    public static final Pitch B_FLAT_4 = new Pitch(PitchClass.B_FLAT, Octave.OCTAVE_4);
    public static final Pitch B_FLAT_5 = new Pitch(PitchClass.B_FLAT, Octave.OCTAVE_5);
    public static final Pitch B_FLAT_6 = new Pitch(PitchClass.B_FLAT, Octave.OCTAVE_6);
    public static final Pitch B_FLAT_7 = new Pitch(PitchClass.B_FLAT, Octave.OCTAVE_7);
    public static final Pitch B_FLAT_8 = new Pitch(PitchClass.B_FLAT, Octave.OCTAVE_8);
    public static final Pitch B_FLAT_9 = new Pitch(PitchClass.B_FLAT, Octave.OCTAVE_9);

    public static final Pitch B_0 = new Pitch(PitchClass.B, Octave.OCTAVE_0);
    public static final Pitch B_1 = new Pitch(PitchClass.B, Octave.OCTAVE_1);
    public static final Pitch B_2 = new Pitch(PitchClass.B, Octave.OCTAVE_2);
    public static final Pitch B_3 = new Pitch(PitchClass.B, Octave.OCTAVE_3);
    public static final Pitch B_4 = new Pitch(PitchClass.B, Octave.OCTAVE_4);
    public static final Pitch B_5 = new Pitch(PitchClass.B, Octave.OCTAVE_5);
    public static final Pitch B_6 = new Pitch(PitchClass.B, Octave.OCTAVE_6);
    public static final Pitch B_7 = new Pitch(PitchClass.B, Octave.OCTAVE_7);
    public static final Pitch B_8 = new Pitch(PitchClass.B, Octave.OCTAVE_8);
    public static final Pitch B_9 = new Pitch(PitchClass.B, Octave.OCTAVE_9);


    public static final Pitch B_SHARP_0 = new Pitch(PitchClass.B_SHARP, Octave.OCTAVE_0);
    public static final Pitch B_SHARP_1 = new Pitch(PitchClass.B_SHARP, Octave.OCTAVE_1);
    public static final Pitch B_SHARP_2 = new Pitch(PitchClass.B_SHARP, Octave.OCTAVE_2);
    public static final Pitch B_SHARP_3 = new Pitch(PitchClass.B_SHARP, Octave.OCTAVE_3);
    public static final Pitch B_SHARP_4 = new Pitch(PitchClass.B_SHARP, Octave.OCTAVE_4);
    public static final Pitch B_SHARP_5 = new Pitch(PitchClass.B_SHARP, Octave.OCTAVE_5);
    public static final Pitch B_SHARP_6 = new Pitch(PitchClass.B_SHARP, Octave.OCTAVE_6);
    public static final Pitch B_SHARP_7 = new Pitch(PitchClass.B_SHARP, Octave.OCTAVE_7);
    public static final Pitch B_SHARP_8 = new Pitch(PitchClass.B_SHARP, Octave.OCTAVE_8);
    public static final Pitch B_SHARP_9 = new Pitch(PitchClass.B_SHARP, Octave.OCTAVE_9);
    public static final Pitch B_SHARP_10 = new Pitch(PitchClass.B_SHARP, Octave.OCTAVE_10);


    public static final Pitch B_DOUBLE_SHARP_0 = new Pitch(PitchClass.B_DOUBLE_SHARP, Octave.OCTAVE_0);
    public static final Pitch B_DOUBLE_SHARP_1 = new Pitch(PitchClass.B_DOUBLE_SHARP, Octave.OCTAVE_1);
    public static final Pitch B_DOUBLE_SHARP_2 = new Pitch(PitchClass.B_DOUBLE_SHARP, Octave.OCTAVE_2);
    public static final Pitch B_DOUBLE_SHARP_3 = new Pitch(PitchClass.B_DOUBLE_SHARP, Octave.OCTAVE_3);
    public static final Pitch B_DOUBLE_SHARP_4 = new Pitch(PitchClass.B_DOUBLE_SHARP, Octave.OCTAVE_4);
    public static final Pitch B_DOUBLE_SHARP_5 = new Pitch(PitchClass.B_DOUBLE_SHARP, Octave.OCTAVE_5);
    public static final Pitch B_DOUBLE_SHARP_6 = new Pitch(PitchClass.B_DOUBLE_SHARP, Octave.OCTAVE_6);
    public static final Pitch B_DOUBLE_SHARP_7 = new Pitch(PitchClass.B_DOUBLE_SHARP, Octave.OCTAVE_7);
    public static final Pitch B_DOUBLE_SHARP_8 = new Pitch(PitchClass.B_DOUBLE_SHARP, Octave.OCTAVE_8);
    public static final Pitch B_DOUBLE_SHARP_9 = new Pitch(PitchClass.B_DOUBLE_SHARP, Octave.OCTAVE_9);
    public static final Pitch B_DOUBLE_SHARP_10 = new Pitch(PitchClass.B_DOUBLE_SHARP, Octave.OCTAVE_10);

    public final PitchClass pitchClass;
    public final Octave octave;

    @Getter
    private final int midiValue;

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
            log.error(e.getMessage(), e.getCause());
        }

        return map;
    }

    Pitch(PitchClass pitchClass, Octave octave) {
        this.pitchClass = pitchClass;
        this.octave = octave;
        this.midiValue = octave.getMidiStart() + pitchClass.aliasBaseMidiValue;
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

            int letterSemitones = Letter.valueOf(letter).baseMidiValue;
            int accidentalsSemitones = Accidental.sumAccidentalsToSemitoneModifier(accidentals);
            int octaveSemitones = Integer.parseInt(octaveNumber) * 12;

            int total = (12 + letterSemitones + accidentalsSemitones) % 12 + octaveSemitones;

            if (total >= 0 && total < 128) {
                PitchClass pitchClass = PitchClass.withName(letter + accidentals, false);
                Octave octave = Octave.valueOf("OCTAVE_" + octaveNumber);
                return new Pitch(pitchClass, octave);
            } else {
                throw new ChordInnateException("Pitch [" + name + "] is outside of playable range");
            }

        }

        throw new IllegalArgumentException("Invalid pitch name [" + name + "]");
    }

    String getName() {
        return pitchClass.getName() + octave.getNumber();
    }

    @Override
    public boolean isDiatonicTo(@NotNull KeySignature keySignature) {
        return this.pitchClass.isDiatonicTo(keySignature);
    }

    @Override
    public boolean isDiatonicTo(@NotNull IntervalSet intervalSet) {
        return this.pitchClass.isDiatonicTo(intervalSet);
    }

    @Override
    public boolean isEnharmonicTo(@NotNull Pitch other) {
        return this.pitchClass.isEnharmonicTo(other.pitchClass);
    }

    @Override
    public boolean isTransposable(@NotNull IntervalDirection direction, @NotNull Interval interval) {
        return direction.getCompareTo() == 1
                ? midiValue + interval.getSemitones() <= 127
                : midiValue - interval.getSemitones() >= 0;
    }

    @Override
    public boolean isTransposable(@NotNull IntervalDirection direction, @NotNull PitchClass pitchClass) {
        return direction.getCompareTo() == 1
                ? midiValue + pitchClass.aliasBaseMidiValue <= 127
                : midiValue - pitchClass.aliasBaseMidiValue >= 0;
    }

    @Override
    public boolean isTransposable(@NotNull PitchClass pitchClass, @NotNull Octave octave) {
        return octave.getMidiStart() <= pitchClass.octaveRange.getMidiStart();
    }

    @Override
    public Pitch transpose(@NotNull IntervalDirection direction, @NotNull Interval interval) {
        boolean attempted = false;
        if (isTransposable(direction, interval)) {

            int goal = pitchClass.aliasBaseMidiValue + octave.getMidiStart()
                    + (interval.getSemitones() * (direction.getCompareTo()));

            PitchClass newPitchClass = pitchClass.transpose(direction, interval);

            int newSemitones = newPitchClass.aliasBaseMidiValue + octave.getMidiStart();

            /*
             * At this point, the candidate Pitch must be enharmonic to the goal, +/- a few octaves.
             * If it isn't, then we know a bug exists in PitchClass.transpose(direction, interval).
             *
             * Figure out which octave we need to use below and return the Pitch.
             */
            if (newSemitones % 12 == goal % 12) {
                int newOctave = octave.getNumber();
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

        throw new ChordInnateException(
                (attempted ? "Error transposing" : "Cannot transpose")
                        + " pitch [" + getName() + "] "
                        + direction.name().toLowerCase()
                        + " by interval [" + interval.getCompoundShortName() + "]");
    }

    @Override
    public Pitch transpose(@NotNull IntervalDirection direction, @NotNull PitchClass pitchClass) {
        if (isTransposable(direction, pitchClass)) {
            Pitch candidate1 = Pitch.withName(pitchClass.getName() + this.octave.getNumber());
            Pitch candidate2 = direction.getCompareTo() == 1
                    ? Pitch.withName(pitchClass.getName() + (this.octave.getNumber() + 1))
                    : Pitch.withName(pitchClass.getName() + (this.octave.getNumber() - 1));

            if (direction.getCompareTo() == 1) {
                return candidate1.midiValue > this.midiValue ? candidate1 : candidate2;
            } else {
                return candidate1.midiValue < this.midiValue ? candidate1 : candidate2;
            }
        }
        return this;
    }

    @Override
    public Pitch transpose(@NotNull Pitch pitch) {
        /*
         * We're transposing on the same type,
         * which is basically apples to apples.
         * No need for any special logic -- just return the requested pitch.
         */
        return pitch;
    }

    @Override
    public Pitch transpose(@NotNull PitchClass pitchClass, @NotNull Octave octave) {
        try {
            return Pitch.withName(pitchClass.getName() + octave.getNumber());
        } catch (IllegalArgumentException | ChordInnateException ex1) {
            throw ex1;
        } catch (Exception ex2) {
            throw new ChordInnateException(
                    ("Cannot transpose pitch ["
                            + getName()
                            + "] to ["
                            + pitchClass.getName()
                            + octave.getNumber())
                            + "]",
                    ex2
            );
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }

        Pitch comparison = (Pitch) other;

        return pitchClass.equals(comparison.pitchClass)
                && octave.equals(comparison.octave)
                && midiValue == comparison.midiValue;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public void accept(MidiEventProducer midiEventProducer) {
        try {
            midiEventProducer.addEvents(this);
        } catch (InvalidMidiDataException e) {
            log.error("Error adding MIDI events", e);
        }
    }
}
