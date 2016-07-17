package chordinnate.generator;

import org.junit.Test;

/**
 * Created by Joseph on 6/22/16.
 */
public class TestBjorklund {
    @Test
    public void sandbox() throws Exception {
        for (int i = 2; i < 10; i++) {
            for (int j = 2; j < i; j++) {
                printBitmap(i, j);
            }
        }

        printBitmap(9, 3);
    }

    private void printBitmap(int slots, int pulses) {
        System.out.print("Bjorklund(" + slots + ", " + pulses + "): ");
        boolean[] bitmap = Bjorklund.computeBitmap(slots, pulses);
        for (boolean b : bitmap) System.out.print(b ? 1 : 0);
        System.out.println();
    }
}