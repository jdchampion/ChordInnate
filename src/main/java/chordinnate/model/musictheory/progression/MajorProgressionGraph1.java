package chordinnate.model.musictheory.progression;

import static chordinnate.model.musictheory.pitch.interval.NashvilleNumber.FIVE;
import static chordinnate.model.musictheory.pitch.interval.NashvilleNumber.FOUR;
import static chordinnate.model.musictheory.pitch.interval.NashvilleNumber.ONE;
import static chordinnate.model.musictheory.pitch.interval.NashvilleNumber.SEVEN;
import static chordinnate.model.musictheory.pitch.interval.NashvilleNumber.SIX;
import static chordinnate.model.musictheory.pitch.interval.NashvilleNumber.THREE;
import static chordinnate.model.musictheory.pitch.interval.NashvilleNumber.TWO;

import chordinnate.model.musictheory.pitch.interval.NashvilleNumber;
import chordinnate.model.musictheory.progression.ChordProgression;

/**
 * Created by Joseph on 1/12/16.
 *
 * References: http://www.people.vcu.edu/~bhammel/theory/resources/flash/interactive_chord_chart.swf
 *             "Chord Chart" from Techniques and Materials of Music (2013),
 *             by Thomas Benjamin, Michael Horvit, Timothy Koozin, Robert Nelson
 *             (p. 254)
 */
public final class MajorProgressionGraph1 extends ChordProgression {

    private MajorProgressionGraph1() {
        NashvilleNumber[] intervals = {ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN};

        for (NashvilleNumber i : intervals) {
            progression.addVertex(i);
        }

        progression.addEdge(ONE, ONE);
        progression.addEdge(ONE, TWO);
        progression.addEdge(ONE, THREE);
        progression.addEdge(ONE, FOUR);
        progression.addEdge(ONE, FIVE);
        progression.addEdge(ONE, SIX);

        progression.addEdge(TWO, FIVE);
        progression.addEdge(TWO, SEVEN);

        progression.addEdge(THREE, FOUR);
        progression.addEdge(THREE, SIX);

        progression.addEdge(FOUR, ONE);
        progression.addEdge(FOUR, TWO);
        progression.addEdge(FOUR, FIVE);
        progression.addEdge(FOUR, SEVEN);

        progression.addEdge(FIVE, ONE);
        progression.addEdge(FIVE, SIX);
        progression.addEdge(FIVE, SEVEN);

        progression.addEdge(SIX, TWO);
        progression.addEdge(SIX, FOUR);
        progression.addEdge(SIX, FIVE);

        progression.addEdge(SEVEN, ONE);
        progression.addEdge(SEVEN, THREE);
    }
}
