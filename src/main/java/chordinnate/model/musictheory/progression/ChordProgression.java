package chordinnate.model.musictheory.progression;

import chordinnate.model.musictheory.notation.IntervalNotation;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedPseudograph;


/**
 * Created by Joseph on 1/12/16.
 *
 * @see <a href=http://web.as.uky.edu/statistics/users/pbreheny/764-F11/notes/9-1.pdf>1</a>
 */
public abstract class ChordProgression {

    protected final DirectedPseudograph<IntervalNotation, DefaultWeightedEdge> progression = new DirectedPseudograph<>(DefaultWeightedEdge.class);

}