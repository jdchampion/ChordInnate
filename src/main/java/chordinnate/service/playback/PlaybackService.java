package chordinnate.service.playback;

import chordinnate.model.playback.Playable;
import chordinnate.service.playback.visitor.SequenceVisitor;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;

/**
 * Created by Joseph on 6/16/16.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PlaybackService {

    private static final int SEQUENCER_DEVICE = 0;
    private static final int SYNTHESIZER_DEVICE = 1;

    private static Sequencer prepareSequencer() {
        MidiDevice midiDevice = null;

        try {
            midiDevice = initializeAndOpen(null, SEQUENCER_DEVICE);
        } catch (MidiUnavailableException ex) {
            log.error("Error initializing the MIDI device", ex);
        }

        return (Sequencer) midiDevice;
    }

    private static Sequencer prepareSequencer(Sequence sequence) {
        Sequencer sequencer = prepareSequencer();

        try {
            sequencer.setSequence(sequence);
        } catch (InvalidMidiDataException ex) {
            log.error("Error sending sequence to the MIDI device {}", sequencer.getDeviceInfo().getName(), ex);
        }

        return sequencer;
    }

    private static Synthesizer prepareSynthesizer() {
        MidiDevice midiDevice = null;
        try {
            midiDevice =  initializeAndOpen(null, SYNTHESIZER_DEVICE);
        } catch (MidiUnavailableException ex) {
            log.error("Error initializing the MIDI device", ex);
        }

        return (Synthesizer) midiDevice;
    }

    private static MidiDevice initializeAndOpen(MidiDevice midiDevice, final int deviceType) throws MidiUnavailableException {
        if (midiDevice == null) {
            try {
                if (deviceType == SEQUENCER_DEVICE) {
                    midiDevice = MidiSystem.getSequencer();
                } else if (deviceType == SYNTHESIZER_DEVICE) {
                    midiDevice = MidiSystem.getSynthesizer();
                } else {
                    return null;
                }
            } catch (MidiUnavailableException ex) {
                throw new MidiUnavailableException("Error initializing the MIDI device");
            }
        }

        try {
            if (!midiDevice.isOpen()) {
                midiDevice.open();
            }
        } catch (MidiUnavailableException ex) {
            throw new MidiUnavailableException("Error preparing the MIDI device " + midiDevice.getDeviceInfo().getName());
        }

        return midiDevice;
    }

    private static MidiDevice stop(final MidiDevice midiDevice) {
        try {
            if (midiDevice instanceof Synthesizer) {
                MidiChannel[] channels = ((Synthesizer) midiDevice).getChannels();
                for (MidiChannel midiChannel : channels) {
                    midiChannel.allSoundOff();
                }
            }
            midiDevice.close();
        } catch (Exception ex) {
            log.error("Error stopping the MIDI device {}", midiDevice.getDeviceInfo().getName(), ex);
        }

        return midiDevice;
    }

    private static void playBackSequence(Sequence sequence) {

        Sequencer sequencer = prepareSequencer(sequence);

        try {
            sequencer.start();
        } catch (IllegalStateException ex) {
            log.error("Error starting the MIDI device {}", sequencer.getDeviceInfo().getName(), ex);
        }

        while (sequencer.isRunning()) ;

        stop(sequencer);
    }

    /**
     *
     * @param playable
     */
    public static void play(@NotNull Playable playable) {
        playBackSequence(playable.accept(new SequenceVisitor()));
    }
}
