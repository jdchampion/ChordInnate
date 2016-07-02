package chordinnate.musictheory;

import java.util.ArrayList;

/**
 * Created by Joseph on 7/1/16.
 *
 * References: http://www.midnightmusic.net/MusicTheory/advanced/UnusualTimeSigs.html
 *             https://en.wikipedia.org/wiki/Time_signature
 *             http://donrathjr.com/compound-time-signatures/
 */
public class TimeSignature {
    private MeterSubdivision meterSubdivision;
    private ArrayList<MeterProperty> meterProperties;
    private Beat subdivisionUnit;
    private boolean[] stressPattern;

    public TimeSignature(int numBeats, MeterSubdivision meterSubdivision, Beat subdivisionUnit) {
        this.meterSubdivision = meterSubdivision;
        this.subdivisionUnit = subdivisionUnit;
        this.meterProperties = new ArrayList<>();
    }
}
