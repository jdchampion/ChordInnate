package chordinnate.service.generator;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by Joseph on 6/22/16.
 */
@Slf4j
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
        boolean[] bitmap = Bjorklund.computeBitmap(slots, pulses);
        StringBuilder sb = new StringBuilder();
        for (boolean b : bitmap) sb.append(b ? 1 : 0);
        log.info("Bjorklund(" + slots + ", " + pulses + "): " + sb.toString());
    }
}