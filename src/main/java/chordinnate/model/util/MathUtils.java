package chordinnate.model.util;

public class MathUtils {

    public static boolean isPowerOf(int power, double val) {
        final double v = Math.log(val) / Math.log(power);
        return Math.ceil(v) == Math.floor(v);
    }

}
