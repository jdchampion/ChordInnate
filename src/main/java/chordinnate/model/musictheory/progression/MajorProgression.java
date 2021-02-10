package chordinnate.model.musictheory.progression;

import static chordinnate.model.musictheory.notation.NashvilleNumber.FIVE;
import static chordinnate.model.musictheory.notation.NashvilleNumber.FOUR;
import static chordinnate.model.musictheory.notation.NashvilleNumber.ONE;
import static chordinnate.model.musictheory.notation.NashvilleNumber.SEVEN;
import static chordinnate.model.musictheory.notation.NashvilleNumber.SIX;
import static chordinnate.model.musictheory.notation.NashvilleNumber.THREE;
import static chordinnate.model.musictheory.notation.NashvilleNumber.TWO;

import chordinnate.model.musictheory.notation.NashvilleNumber;

/**
 * Created by Joseph on 1/12/16.
 *
 * @see <a href=http://www.people.vcu.edu/~bhammel/theory/resources/flash/interactive_chord_chart.swf>Interactive Chord Chart from VCU</a>
 * @see <a href=https://www.amazon.com/Techniques-Materials-Music-Practice-Twentieth/dp/1285446178>"Chord Chart" from Techniques and Materials of Music (2013), by Thomas Benjamin, Michael Horvit, Timothy Koozin, Robert Nelson (p. 254)</a>
 */
public final class MajorProgression extends ChordProgression {

    private MajorProgression() {
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
