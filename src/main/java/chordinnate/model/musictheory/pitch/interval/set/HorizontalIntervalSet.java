package chordinnate.model.musictheory.pitch.interval.set;

import chordinnate.entity.ChordType;
import chordinnate.model.musictheory.pitch.PitchClass;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.musictheory.pitch.interval.RomanNumeral;
import chordinnate.util.nomenclature.GreekGrouping;
import chordinnate.service.playback.sequence.SequenceGenerator;
import chordinnate.service.playback.sequence.event.MidiEventGenerator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Sequence;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Joseph on 7/15/16.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HorizontalIntervalSet extends IntervalSet {

    @Override
    protected void commonInitializations(PitchClass root, Interval[] intervals) {
        super.commonInitializations(root, intervals);
        this.orientation = IntervalOrientation.HORIZONTAL;
    }

    @Override
    public int getHorizontalSize() {
        return super.intervals.length;
    }

    @Override
    public int getVerticalSize() {
        return 1;
    }

    @Override
    public String getGrouping() {

        int size = getHorizontalSize();

        switch (size) {
            case 1: return "monochord"; // special case because it is the only grouping with a prefix of 1
            case 2:
            case 3: return GreekGrouping.grouping(size) + "chord";
            default: return GreekGrouping.grouping(size) + "achord";
        }

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

    public RomanNumeral[] getRomanNumeralAnalysis() {

        Map<Interval, List<ChordType>> diatonicsByInterval = getDiatonicChordTypes();

        List<RomanNumeral> analysis = new ArrayList<>();

        for (Map.Entry<Interval, List<ChordType>> entry : diatonicsByInterval.entrySet()) {
            if (!entry.getValue().isEmpty()) {
                analysis.add(RomanNumeral.from(entry.getKey().getCompoundDiatonic(), entry.getValue().get(0).getIntervals()));
            }
        }

        return analysis.toArray(new RomanNumeral[0]);
    }
}
