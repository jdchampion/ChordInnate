package chordinnate.model.musictheory.pitch.interval.set;

import chordinnate.model.musictheory.pitch.PitchClass;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.midi.producer.MidiEventProducer;
import chordinnate.util.nomenclature.GreekGrouping;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sound.midi.InvalidMidiDataException;

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
    public void accept(MidiEventProducer midiEventProducer) {
        try {
            midiEventProducer.addEvents(this);
        } catch (InvalidMidiDataException e) {
            log.error("Error adding MIDI events", e);
        }
    }
}
