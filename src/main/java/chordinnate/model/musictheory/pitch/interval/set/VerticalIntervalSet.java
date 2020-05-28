package chordinnate.model.musictheory.pitch.interval.set;

import chordinnate.model.musictheory.pitch.PitchClass;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.util.nomenclature.GreekGrouping;
import chordinnate.service.playback.SequenceGenerator;
import chordinnate.service.playback.MidiEventGenerator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Sequence;

/**
 * Created by Joseph on 7/15/16.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VerticalIntervalSet extends InvertibleIntervalSet {

    @Override
    protected void commonInitializations(PitchClass root, Interval[] intervals) {
        super.commonInitializations(root, intervals);
        this.orientation = IntervalOrientation.VERTICAL;
    }

    @Override
    public int getHorizontalSize() {
        return 1;
    }

    @Override
    public int getVerticalSize() {
        return super.intervals.length;
    }

    @Override
    public String getGrouping() {

        int size = getVerticalSize();

        if (size == 2) {
            return "dyad"; // special case because all other 2 prefixes are "di"
        }

        return GreekGrouping.grouping(size) + "ad";
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
}
