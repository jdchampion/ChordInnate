package chordinnate.service.generator;

import chordinnate.model.musictheory.notation.NashvilleNumber;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.DefaultEdge;


/**
 * Created by Joseph on 1/12/16.
 *
 * References: http://web.as.uky.edu/statistics/users/pbreheny/764-F11/notes/9-1.pdf
 */
public abstract class ChordProgression
        extends AbstractBaseGraph<NashvilleNumber, DefaultEdge>
        implements DirectedGraph<NashvilleNumber, DefaultEdge> {

    public ChordProgression() {
        super(new ClassBasedEdgeFactory<>(DefaultEdge.class), true, true);
    }
}