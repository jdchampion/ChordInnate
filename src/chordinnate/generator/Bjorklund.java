package chordinnate.generator;

/**
 * Created by Joseph on 1/9/16.
 *
 * References: https://ics-web.sns.ornl.gov/timing/Rep-Rate%20Tech%20Note.pdf
 *             http://erikdemaine.org/papers/DeepRhythms_CGTA/paper.pdf
 *             http://student.ulb.ac.be/~ptaslaki/publications/phdThesis-Perouz.pdf
 *             http://www.noisylittlebugger.net/diy/bjorklund/Bjorklund_Working_Final/Bjorklund_algorithm_arduino.txt
 */
public class Bjorklund {
    private int steps;
    private int pulses;

    private int[] remainder;
    private int[] count;
    private int level;
    private int stepstatus;

    private static boolean[] sequence;

    /**
     *
     * @param num_slots
     * @param num_pulses
     * @return
     */
    public boolean[] compute_bitmap(int num_slots, int num_pulses) {

        stepstatus = 0;
        steps = num_slots;

        remainder = new int[steps];
        count = new int[steps];

        sequence = new boolean[steps];

        if (num_pulses > num_slots) {
            num_pulses = num_slots;
        }

        int divisor = num_slots - num_pulses;
        steps = num_slots; pulses = num_pulses;
        remainder[0] = num_pulses;

        level = 0;
        do {
            count[level] = divisor / remainder[level];
            remainder[level+1] = divisor % remainder[level];
            divisor = remainder[level];
            level++;
        }
        while (remainder[level] > 1);

        count[level] = divisor;
        build_string(level);

        return sequence;
    }

    /**
     *
     * @param level
     */
    private void build_string(int level) {
        if (level == -1) {
            sequence[stepstatus] = false;
            stepstatus++;
        }
        else if (level == -2) {
            sequence[stepstatus] = true;
            stepstatus++;
        }
        else {
            for (int i = 0; i < count[level]; i++) {
                build_string(level - 1);
            }
            if (remainder[level] != 0) {
                build_string(level - 2);
            }
        }

    }
}