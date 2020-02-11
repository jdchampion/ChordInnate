package chordinnate.model.musictheory.temporal;

import chordinnate.model.musictheory.pitch.key.KeySignature;
import chordinnate.model.musictheory.temporal.meter.MeterSubdivision;
import chordinnate.model.musictheory.temporal.meter.MeterType;
import chordinnate.model.musictheory.temporal.meter.Metered;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.Fraction;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;


@Slf4j
public class MeterTypeTest {

    @Test
    public void classify_free() {
        TimeSignature ts = TimeSignature.NONE;
        assertTrue(MeterType.classify(ts).contains(MeterType.FREE));
    }

    @Test
    public void classify_fixed() {
        Metered measure = new Measure(new TimeSignature(4, 4), KeySignature.NO_KEY_SIGNATURE, Collections.emptyList());
        assertTrue(MeterType.classify(measure).contains(MeterType.FIXED));
    }

    @Test
    public void classify_complete() {
        TimeSignature ts = new TimeSignature(4, 4);
        assertTrue(MeterType.classify(ts).contains(MeterType.COMPLETE));
    }

    @Test
    public void classify_mixed() {
        Motif motif = new Motif();

        Cell cell1 = new Cell();
        cell1.setMeasure(new Measure(new TimeSignature(4, 4), KeySignature.NO_KEY_SIGNATURE, Collections.emptyList()));

        Cell cell2 = new Cell();
        cell2.setMeasure(new Measure(new TimeSignature(2, 4), KeySignature.NO_KEY_SIGNATURE, Collections.emptyList()));

        motif.setCells(Arrays.asList(cell1, cell2));

        assertTrue(MeterType.classify(motif).contains(MeterType.MIXED));
    }

    @Test
    public void classify_alternating() {
        Motif motif = new Motif();

        Cell cell1 = new Cell();
        cell1.setMeasure(new Measure(new TimeSignature(4, 4), KeySignature.NO_KEY_SIGNATURE, Collections.emptyList()));

        Cell cell2 = new Cell();
        cell2.setMeasure(new Measure(new TimeSignature(2, 4), KeySignature.NO_KEY_SIGNATURE, Collections.emptyList()));

        Cell cell3 = new Cell();
        cell3.setMeasure(new Measure(new TimeSignature(4, 4), KeySignature.NO_KEY_SIGNATURE, Collections.emptyList()));

        motif.setCells(Arrays.asList(cell1, cell2, cell3));

        assertTrue(MeterType.classify(motif).contains(MeterType.ALTERNATING));
    }

    @Test
    public void classify_multiplicative() {
        TimeSignature ts = new TimeSignature(6, 8);
        assertTrue(MeterType.classify(ts).contains(MeterType.MULTPLICATIVE));
    }

    @Test
    public void classify_imperfect() {
        TimeSignature ts = new TimeSignature(6, 8);
        assertTrue(MeterType.classify(ts).contains(MeterType.IMPERFECT));
    }

    @Test
    public void classify_odd() {
        TimeSignature ts = new TimeSignature(5, 8);
        assertTrue(MeterType.classify(ts).contains(MeterType.ODD));
    }

    @Test
    public void classify_simple() {
        TimeSignature ts = new TimeSignature(4, 4);
        assertTrue(MeterType.classify(ts).contains(MeterType.SIMPLE));
    }

    @Test
    public void classify_perfect() {
        TimeSignature ts = new TimeSignature(6, 8);
        assertTrue(MeterType.classify(ts).contains(MeterType.PERFECT));
    }

    @Test
    public void classify_compound() {
        TimeSignature ts = new TimeSignature(6, 8);
        assertTrue(MeterType.classify(ts).contains(MeterType.COMPOUND));
    }

    @Test
    public void classify_additive() {
        TimeSignature ts = new TimeSignature(Arrays.asList(MeterSubdivision.DUPLE, MeterSubdivision.TRIPLE, MeterSubdivision.DUPLE, MeterSubdivision.DUPLE), 8);
        assertTrue(MeterType.classify(ts).contains(MeterType.ADDITIVE));
    }

    @Test
    public void classify_complex() {
        TimeSignature ts = new TimeSignature(5, 8);
        assertTrue(MeterType.classify(ts).contains(MeterType.COMPLEX));
    }

    @Test
    public void classify_irregular() {
        TimeSignature ts = new TimeSignature(5, 8);
        assertTrue(MeterType.classify(ts).contains(MeterType.IRREGULAR));
    }

    @Test
    public void classify_asymmetrical() {
        TimeSignature ts = new TimeSignature(5, 8);
        assertTrue(MeterType.classify(ts).contains(MeterType.ASYMMETRICAL));
    }

    @Test
    public void classify_irrational() {
        TimeSignature ts = new TimeSignature(6, 5);
        assertTrue(MeterType.classify(ts).contains(MeterType.IRRATIONAL));
    }

    @Test
    public void classify_partial() {
        TimeSignature ts = new TimeSignature(2.5, 4);
        assertTrue(MeterType.classify(ts).contains(MeterType.PARTIAL));
    }

    @Test
    public void classify_fractional() {
        TimeSignature ts = new TimeSignature(Fraction.getFraction(2.5), 8);
        assertTrue(MeterType.classify(ts).contains(MeterType.FRACTIONAL));
    }
}
