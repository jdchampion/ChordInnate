package generator;

import musictheory.NashvilleNumber;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.DefaultEdge;


/**
 * Created by Joseph on 1/12/16.
 */
public abstract class ChordProgression
        extends AbstractBaseGraph<NashvilleNumber, DefaultEdge>
        implements DirectedGraph<NashvilleNumber, DefaultEdge> {

    public ChordProgression() {
        super(new ClassBasedEdgeFactory<>(DefaultEdge.class), true, true);
    }
}