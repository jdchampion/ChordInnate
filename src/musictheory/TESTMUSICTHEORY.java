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

    // Toggle for hearing Midi playback
    static final boolean PLAYBACK = true;

    // All possible note types that this program can play
    static final Note[] ALL_NOTES = Note.values();

    // Variable for testing scales
    static Scale scale;

    // Variable for testing key signatures
    static KeySignature keySignature;

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

            // TODO: Tests performed here

//            testScale(PLAYBACK);

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
    }

    private static void soundNote(boolean playback, int midiValue, int volume, int wait) {
        if (playback)
        try {
            Thread.sleep(100);
            channels[0].noteOn(midiValue, volume);
            Thread.sleep(200);
            channels[0].noteOff(midiValue, volume);
            Thread.sleep(wait);
        }
        catch (InterruptedException ex) {}
    }

    private static void testAscendingNotes(boolean playback) {
        for (int i = 0; i < ALL_NOTES.length; i++) {
            System.out.print("Playing " + ALL_NOTES[i].getName() + "...\n");
            for (int j = 0; j < ALL_NOTES[i].getOctaveRange(); j++) {

                int midiValue = (12 * j) + ALL_NOTES[i].getRelativePitch();
                System.out.print(" " + midiValue);

                soundNote(playback, midiValue, 127, 0);

                System.out.println(" \t(isAccidental: " + ALL_NOTES[i].hasAccidentalSymbol() + ")");
            }
            System.out.println("\n==========================================\n");
        }
    }

    private static void testDescendingNotes(boolean playback) {
        for (int i = 0; i < ALL_NOTES.length; i++) {
            System.out.print("Playing " + ALL_NOTES[i].getName() + "...\n");
            for (int j = ALL_NOTES[i].getOctaveRange(); j >= 0; j--) {

                int midiValue = (12 * j) + ALL_NOTES[i].getRelativePitch();
                System.out.print(" " + midiValue);

                soundNote(playback, midiValue, 127, 0);

                System.out.println(" \t(isAccidental: " + ALL_NOTES[i].hasAccidentalSymbol() + ")");
            }
            System.out.println("\n==========================================\n");
        }
    }

    private static void testIntervalNotes(boolean playback) {
        for (Interval interval: Interval.values()) {
            System.out.println(interval.toString());
            System.out.println("Short Name: " + interval.getShortName());
            System.out.println("RomanNumeral: " + interval.getRomanNumeralName());
            System.out.println("=======================================");

            soundNote(playback, 60, 127, 0);
            soundNote(playback, 60 + interval.relativePitchDistance, 127, 500);
        }
    }

    private static void testScale(boolean playback) {
        for (ScaleType scaleType: ScaleType.values()) {
            for (Note note : Note.values()) {
                try {
                    scale = new Scale(note, scaleType);

                    System.out.print(scale.getName() + ": ");

                    Note root = scale.getRoot();

                    System.out.println(root.getName() + " " + root.name());

                    keySignature = scale.getKeySignature();

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

                            /*
                             * FIXME Notes are correct but sound at the incorrect octave.
                             * Need a modifier variable for the octave.
                             */
                            soundNote(playback, 60 + n.getRelativePitch(), 127, 0);
                        }
                        else System.out.print("_ ");
                    }

                    /*
                     * FIXME Notes are correct but sound at the incorrect octave.
                     * Need a modifier variable for the octave.
                     */
                    soundNote(playback, 72 + note.getRelativePitch(), 127, 0);

                    System.out.println();

                    System.out.print("Steps: ");
                    for (Step s : scale.getSteps()) {
                        System.out.print(s + " ");
                    }

                    System.out.println();

                    System.out.println("\n==========================================");

                    if (playback) {
                        try {
                            Thread.sleep(500);
                        }
                        catch (InterruptedException ex) {}
                    }

                } catch (Exception e) {
                    // Skip the natural notes (don't generate scales from them)
                    e.getMessage();
                }
            }
        }
    }

    private static void testNextPreviousNotes(boolean playback) {
        for (Note note : Note.values()) {
            System.out.println("Note: " + note);
            soundNote(playback, 60 + note.getRelativePitch(), 127, 0);
            System.out.println("Next: " + note.getNext());
            if (note.getNext() != null) {
                soundNote(playback, 60 + note.getNext().getRelativePitch(), 127, 0);
            }
            System.out.println("Previous: " + note.getPrevious());
            if (note.getPrevious() != null) {
                soundNote(playback, 60 + note.getPrevious().getRelativePitch(), 127, 0);
            }

            System.out.println("\n==========================================");
            if (playback) {
                try {
                    Thread.sleep(500);
                }
                catch (InterruptedException ex) {}
            }
        }
    }

    private static void testEnharmonicNotes(boolean playback) {
        for (Note note : Note.values()) {
            System.out.println("Note: " + note);
            soundNote(playback, 60 + note.getRelativePitch(), 127, 200);

            for (Note n : note.getEnharmonicEquivalents(true, true)) {
                System.out.print(n.getName() + " ");

                soundNote(playback, 60 + n.getRelativePitch(), 127, 0);
            }
            System.out.println();
            for (Note n : note.getEnharmonicEquivalents(false, true)) {
                System.out.print(n.getName() + " ");

                soundNote(playback, 60 + n.getRelativePitch(), 127, 0);
            }
            System.out.println("\n==========================================");
            if (playback) {
                try {
                    Thread.sleep(500);
                }
                catch (InterruptedException ex) {}
            }
        }
    }
}
