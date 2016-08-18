package chordinnate.musictheory.pitch.interval.set;

import chordinnate.musictheory.pitch.Pitch;
import chordinnate.musictheory.pitch.PitchClass;
import chordinnate.musictheory.pitch.interval.Interval;
import chordinnate.musictheory.pitch.interval.Octave;
import chordinnate.musictheory.pitch.notation.EnharmonicSpelling;
import chordinnate.musictheory.pitch.notation.KeySignature;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Joseph on 7/15/16.
 * References: http://pianoencyclopedia.com/scales/
 *             http://www.earmaster.com/music-theory-online/ch04/chapter-4-8.html
 */
public final class Scale extends SerialIntervalSet implements TransposableIntervalSet {
    String typeName, fullName;
    String origin;

    private static final String DATABASE_USERNAME = "";
    private static final String DATABASE_PASSWORD = "";
    private static final String DATABASE_DRIVER = "org.sqlite.JDBC";
    private static final String DATABASE_PROTOCOL = "jdbc:sqlite:";
    private static final String DATABASE_DIRECTORY = "src/resources/sqlite/scales.db";

    private static final int STARTING_INDEX = 1;
    private static Map<Integer, String> INDEX_TO_SCALE_NAME;
    private static Map<String, Interval[]> SCALE_NAME_TO_PITCH_INTERVALS;
    private static Map<String, String> SCALE_NAME_TO_ORIGIN;

    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    static {
        try {
            Class.forName(DATABASE_DRIVER);
            connection = DriverManager
                    .getConnection(DATABASE_PROTOCOL + DATABASE_DIRECTORY, DATABASE_USERNAME, DATABASE_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT Name, Intervals, Origin FROM supported_scales");
            int fetchSize = preparedStatement.getFetchSize();
            INDEX_TO_SCALE_NAME = new HashMap<>(fetchSize);
            SCALE_NAME_TO_PITCH_INTERVALS = new HashMap<>(fetchSize);
            SCALE_NAME_TO_ORIGIN = new HashMap<>(fetchSize);
            resultSet = preparedStatement.executeQuery();
            int index = STARTING_INDEX;
            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                String[] intervals = resultSet.getString("Intervals").split(", ");
                Interval[] pitchIntervals = new Interval[intervals.length];
                for (int i = 0; i < pitchIntervals.length; i++) {
                    pitchIntervals[i] = new Interval(intervals[i], true); // TODO: change this to use a static final Interval
                }
                String origin = resultSet.getString("Origin");
                INDEX_TO_SCALE_NAME.put(index, name);
                SCALE_NAME_TO_PITCH_INTERVALS.put(name, pitchIntervals);
                SCALE_NAME_TO_ORIGIN.put(name, origin);
                index++;
            }
            preparedStatement.close();
            resultSet.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Interval[] getIntervals(String name) {
        // Return a copy of the array (to protect against mutation)
        Interval[] source = SCALE_NAME_TO_PITCH_INTERVALS.get(name);
        Interval[] pitchIntervals = new Interval[source.length];
        System.arraycopy(source, 0, pitchIntervals, 0, source.length);
        return pitchIntervals;
    }

    public Scale(@NotNull EnharmonicSpelling root, @NotNull String scaleTypeName) {
        super.commonInitializations(root, getIntervals(scaleTypeName));
        this.typeName = scaleTypeName;
        this.fullName = super.lowestDiatonic.PITCH_CLASS.ENHARMONIC_SPELLING.NAME + " " + this.typeName;
        this.origin = SCALE_NAME_TO_ORIGIN.get(typeName);
    }

    public Scale(@NotNull PitchClass root, @NotNull String scaleTypeName) {
        this(root.ENHARMONIC_SPELLING, scaleTypeName);
    }

    @Override
    public boolean isDiatonicTo(@NotNull KeySignature keySignature) {
        for (Pitch pitch : getPitchesForOctave(lowestDiatonic.OCTAVE)) {
            if (!pitch.isDiatonicTo(keySignature)) return false;
        }
        return true;
    }

    @Override
    public boolean isDiatonicTo(@NotNull IntervalSet intervalSet) {
        for (Pitch pitch : getPitchesForOctave(lowestDiatonic.OCTAVE)) {
            if (!pitch.isDiatonicTo(intervalSet)) return false;
        }
        return true;
    }

    @Override
    public void transposeTo(@NotNull Interval pitchInterval) {
        Pitch lowestTransposed = super.lowestDiatonic.transposeTo(pitchInterval);
        super.commonInitializations(lowestTransposed.PITCH_CLASS.ENHARMONIC_SPELLING, getIntervals(typeName));
        this.fullName = super.lowestDiatonic.PITCH_CLASS.ENHARMONIC_SPELLING.NAME + " " + this.typeName;
    }

    @Override
    public void transposeTo(@NotNull PitchClass pitchClass) {
        Pitch lowestTransposed = super.lowestDiatonic.transposeTo(pitchClass, lowestDiatonic.OCTAVE);
        super.commonInitializations(lowestTransposed.PITCH_CLASS.ENHARMONIC_SPELLING, getIntervals(typeName));
        this.fullName = super.lowestDiatonic.PITCH_CLASS.ENHARMONIC_SPELLING.NAME + " " + this.typeName;
    }

    public static List<String> getSupportedScaleNames() {
        int numScales = STARTING_INDEX + INDEX_TO_SCALE_NAME.size();
        List<String> names = new ArrayList<>(numScales);
        for (int i = STARTING_INDEX; i < numScales; i++) {
            names.add(i + ". " + INDEX_TO_SCALE_NAME.get(i));
        }
        return names;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getOrigin() {
        return origin;
    }

    public Pitch[] getPitchesForOctave(@NotNull Octave octave) {
        // Return the desired octave (i.e., a subarray from super.pitchesByOctave)
        Pitch[] source = super.pitchesByOctave.get(octave), destination = new Pitch[source.length];
        System.arraycopy(source, 0, destination, 0, destination.length);
        return destination;
    }

    public int length() {
        return super.pitchesByOctave.get(Octave.OCTAVE_0).length;
    }
}
