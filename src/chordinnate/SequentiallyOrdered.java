package chordinnate;

/**
 * Created by Joseph on 7/7/16.
 */
public interface SequentiallyOrdered {
    <T> T getNext();
    <T> T getPrevious();
}