package chordinnate.model.musictheory.progression;

import chordinnate.model.musictheory.notation.NashvilleNumber;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedPseudograph;


/**
 * Created by Joseph on 1/12/16.
 *
 * References: http://web.as.uky.edu/statistics/users/pbreheny/764-F11/notes/9-1.pdf
 */
public abstract class ChordProgression {

    protected final DirectedPseudograph<NashvilleNumber, DefaultWeightedEdge> progression = new DirectedPseudograph<>(DefaultWeightedEdge.class);

}