package chordinnate.util;

/**
 * Created by Joseph on 7/7/16.
 */
public interface Sequential {
    <T> T getNext();
    <T> T getPrevious();
}