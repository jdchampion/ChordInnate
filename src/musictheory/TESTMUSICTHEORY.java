package musictheory;

import org.jgrapht.graph.*;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

/**
 * Created by Joseph on 12/29/15.
 */
public class TESTMUSICTHEORY {

    //===============================================================================================//
    //  EDITABLE VARIABLES
    //===============================================================================================//

    // Toggle for hearing Midi playback
    static final boolean PLAYBACK = true;
    static final boolean PLAY_SCALES_UP_DOWN = false;

    // All possible note types that this program can play
    static final Note[] ALL_NOTES = Note.values();

    // Variable for testing notes
    static final Note note = Note.C;

    // Variables for testing scales
    static final ScaleType scaleType = ScaleType.MAJOR;

    //===============================================================================================//

    static MidiChannel[] channels;




    public static void main(String[] args) {
//        SimpleDirectedGraph<Note, DefaultEdge> directedGraph =
//                new SimpleDirectedGraph<>(DefaultEdge.class);
//
//        Note[] notes = {Note.C, Note.D, Note.E, Note.F, Note.G, Note.A, Note.B};
//        directedGraph.addVertex(notes[0]);
//        for (int i = 1; i < notes.length; i++) {
//            directedGraph.addVertex(notes[i]);
//            directedGraph.addEdge(notes[i-1], notes[i]);
//        }
//        System.out.println(directedGraph.toString());
//
//        Note n = directedGraph.getEdgeSource(directedGraph.getEdge(Note.C, Note.D));
//        System.out.println(n);
//        n = directedGraph.getEdgeTarget(directedGraph.getEdge(Note.C, Note.D));
//        System.out.println(n);

        try {
            Synthesizer synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            channels = synthesizer.getChannels();

            Thread.sleep(1000); // To minimize the (annoying) initial sound delay

            // TODO: Tests performed here

            // TODO remove for loop when finished
            for (Note n: ALL_NOTES) {

                testScale(n, scaleType, PLAYBACK, false);

            }

//            testAscendingNotes(PLAYBACK);
//
//            testDescendingNotes(PLAYBACK);
//
//            testIntervalNotes(PLAYBACK);

//            testNextPreviousNotes(PLAYBACK);

//            testEnharmonicNotes(PLAYBACK);

            synthesizer.close();
        }
        catch (MidiUnavailableException ex) {}
        catch (InterruptedException ex) {}
    }

    private static void soundNote(int midiValue, int volume, int wait) {
        if (PLAYBACK)
        try {
            Thread.sleep(100);
            channels[0].noteOn(midiValue, volume);
            Thread.sleep(200);
            channels[0].noteOff(midiValue, volume);
            Thread.sleep(wait);
        }
        catch (InterruptedException ex) {}
    }

    private static void testAscendingNotes() {
        for (int i = 0; i < ALL_NOTES.length; i++) {
            System.out.print("Playing " + ALL_NOTES[i].getName() + "...\n");
            for (int j = 0; j < ALL_NOTES[i].getOctaveRange(); j++) {

                int midiValue = (12 * j) + ALL_NOTES[i].getRelativePitch();
                System.out.print(" " + midiValue);

                soundNote(midiValue, 127, 0);

                System.out.println(" \t(isAccidental: " + ALL_NOTES[i].hasAccidentalSymbol() + ")");
            }
            System.out.println("\n==========================================\n");
        }
    }

    private static void testDescendingNotes() {
        for (int i = 0; i < ALL_NOTES.length; i++) {
            System.out.print("Playing " + ALL_NOTES[i].getName() + "...\n");
            for (int j = ALL_NOTES[i].getOctaveRange(); j >= 0; j--) {

                int midiValue = (12 * j) + ALL_NOTES[i].getRelativePitch();
                System.out.print(" " + midiValue);

                soundNote(midiValue, 127, 0);

                System.out.println(" \t(isAccidental: " + ALL_NOTES[i].hasAccidentalSymbol() + ")");
            }
            System.out.println("\n==========================================\n");
        }
    }

    private static void testIntervalNotes() {
        for (Interval interval: Interval.values()) {
            System.out.println(interval.toString());
            System.out.println("Short Name: " + interval.getShortName());
            System.out.println("RomanNumeral: " + interval.getRomanNumeralName());
            System.out.println("=======================================");

            soundNote(60, 127, 0);
            soundNote(60 + interval.relativePitchDistance, 127, 500);
        }
    }

    private static void testSoundScale(Scale scale) {
        Note[] upNotes = scale.getAscendingNotes();
        Note[] downNotes = scale.getDescendingNotes();

        for (Note n : upNotes) {
            if (n != null) {
                /*
                 * FIXME Notes are correct but sound at the incorrect octave.
                 * Need a modifier variable for the octave.
                 */
                soundNote(60 + n.getRelativePitch(), 127, 0);
            }
        }

        // Top octave note (root)
        soundNote(72 + scale.getRoot().getRelativePitch(), 127, 0);

        if (PLAY_SCALES_UP_DOWN) {
            for (Note n : downNotes) {
                if (n != null) {
                    /*
                     * FIXME Notes are correct but sound at the incorrect octave.
                     * Need a modifier variable for the octave.
                     */
                    soundNote(60 + n.getRelativePitch(), 127, 0);
                }
            }
        }
    }

    private static void testScale(Note note, ScaleType scaleType, boolean playback, boolean testTranspose) {
        System.out.println(note);
        try {
            Scale scale = new Scale(note, scaleType);


            testScaleAttributes(scale);

            if (testTranspose) {
                for (Note n : ALL_NOTES) {
                    System.out.println("\n==========================================");
                    try {
                        scale = scale.getTransposition(n);
                        System.out.print("Scale transposition from " + note.getName() + " to ");
                        testScaleAttributes(scale);
                    }
                    catch (Exception e) {
//                        System.out.println(e.getMessage());
                    }
                }
            }

            if (playback) {
                try {
                    Thread.sleep(500);
                }
                catch (InterruptedException ex) {}
            }

        } catch (Exception e) {
            // Skip the natural notes (don't generate scales from them)
//            System.out.println(e.getMessage());
//            System.out.println("\n==========================================");
        }

        System.out.println("\n==========================================");
    }

    private static void testScaleAttributes(Scale scale) {

        System.out.print(scale.getName() + ": ");

        Note root = scale.getRoot();

        System.out.println(root.getName() + " " + root.name());

        KeySignature keySignature = scale.getKeySignature();

        System.out.print("Key Signature: " + keySignature + "( ");
        for (Note n : keySignature.notes) {
            System.out.print(n.getName() + " ");
        }

        System.out.println(")");

        // Print the scale notes out, and sound them
        System.out.print("Notes: ");
        Note[] notes = scale.getAscendingNotes();
        for (Note n : notes) {
            if (n != null) {
                System.out.print(n.getName() + " ");
            }
            else System.out.print("_ ");
        }

        testSoundScale(scale);

        System.out.println();

        System.out.print("Steps: ");
        for (Step s : scale.getSteps()) {
            System.out.print(s + " ");
        }

        System.out.println();

        System.out.print("Intervals: ");
        for (NashvilleInterval interval : scaleType.intervals) {
            System.out.print(interval + " ");
        }
        System.out.println();
    }

    private static void testNextPreviousNotes() {
        for (Note note : ALL_NOTES) {
            System.out.println("Note: " + note);
            soundNote(60 + note.getRelativePitch(), 127, 0);
            System.out.println("Next: " + note.getNext());
            if (note.getNext() != null) {
                soundNote(60 + note.getNext().getRelativePitch(), 127, 0);
            }
            System.out.println("Previous: " + note.getPrevious());
            if (note.getPrevious() != null) {
                soundNote(60 + note.getPrevious().getRelativePitch(), 127, 0);
            }

            System.out.println("\n==========================================");
            if (PLAYBACK) {
                try {
                    Thread.sleep(500);
                }
                catch (InterruptedException ex) {}
            }
        }
    }

    private static void testEnharmonicNotes(boolean playback) {
        for (Note note : ALL_NOTES) {
            System.out.println("Note: " + note);
            soundNote(60 + note.getRelativePitch(), 127, 200);

            for (Note n : Theory.getEnharmonicEquivalents(note, true, true)) {
                System.out.print(n.getName() + " ");

                soundNote(60 + n.getRelativePitch(), 127, 0);
            }
            System.out.println();
            for (Note n : Theory.getEnharmonicEquivalents(note, false, true)) {
                System.out.print(n.getName() + " ");

                soundNote(60 + n.getRelativePitch(), 127, 0);
            }
            System.out.println("\n==========================================");
            if (PLAYBACK) {
                try {
                    Thread.sleep(500);
                }
                catch (InterruptedException ex) {}
            }
        }
    }
}
