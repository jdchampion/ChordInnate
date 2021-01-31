package chordinnate.util.algorithm;

/**
 * Created by Joseph on 1/9/16.
 *
 * References: https://ics-web.sns.ornl.gov/timing/Rep-Rate%20Tech%20Note.pdf
 *             http://erikdemaine.org/papers/DeepRhythms_CGTA/paper.pdf
 *             http://student.ulb.ac.be/~ptaslaki/publications/phdThesis-Perouz.pdf
 *             http://www.noisylittlebugger.net/diy/bjorklund/Bjorklund_Working_Final/Bjorklund_algorithm_arduino.txt
 */
final class Bjorklund {

    private static int[] remainder;
    private static int[] count;
    private static int stepStatus;

    private static boolean[] sequence;

    private Bjorklund() {}

    /**
     *
     * @param numSlots
     * @param numPulses
     * @return
     */
    public static int[] getPulseLocations(int numSlots, int numPulses) {
        computeBitmap(numSlots, numPulses);
        int[] pulseLocations = new int[Math.min(numPulses, sequence.length)];
        int j = 0;
        for (int i = 0; i < sequence.length; i++) {
            if (sequence[i]) {
                pulseLocations[j++] = i;
            }
        }

        return pulseLocations;
    }

    /**
     *
     * @param numSlots
     * @param numPulses
     * @return
     */
    public static boolean[] computeBitmap(int numSlots, int numPulses) {

        stepStatus = 0;

        remainder = new int[numSlots];
        count = new int[numSlots];

        sequence = new boolean[numSlots];

        if (numPulses > numSlots) {
            numPulses = numSlots;
        }

        int divisor = numSlots - numPulses;
        remainder[0] = numPulses;

        int level = 0;
        do {
            count[level] = divisor / remainder[level];
            remainder[level + 1] = divisor % remainder[level];
            divisor = remainder[level];
            level++;
        } while (remainder[level] > 1);

        count[level] = divisor;
        buildString(level);

        return sequence;
    }

    /**
     *
     * @param level
     */
    private static void buildString(int level) {
        if (level == -1) {
            sequence[stepStatus] = false;
            stepStatus++;
        } else if (level == -2) {
            sequence[stepStatus] = true;
            stepStatus++;
        } else {
            for (int i = 0; i < count[level]; i++) {
                buildString(level - 1);
            }
            if (remainder[level] != 0) {
                buildString(level - 2);
            }
        }
    }
}