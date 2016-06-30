package chordinnate.musictheory;

/**
 * Created by Joseph on 6/30/16.
 */
final class NoteUtils {

    static double getRatio(Duration duration, DotValue dotValue, Tuplet tuplet) {
        return (duration.getRatio() + getDotSum(duration, dotValue))
                * (tuplet == null ? 1 : tuplet.getNumber());
    }

    private static double getDotSum(Duration duration, DotValue dotValue) {
        double sum = 0;
        Duration tmp = duration.getPrevious();
        int numDots = dotValue.ordinal();
        for (int i = 0; i < numDots && tmp != null; tmp = tmp.getPrevious(), i++) {
            sum += tmp.getRatio();
        }
        return sum;
    }

    static boolean isSupportedNoteLength(Duration duration, DotValue dotValue) {
        return duration.ordinal() - dotValue.ordinal() >= 0;
    }
}
