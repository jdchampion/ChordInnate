package chordinnate.musictheory.pitch.interval.set;

import chordinnate.musictheory.pitch.interval.Interval;
import chordinnate.musictheory.pitch.interval.Octave;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Joseph on 1/8/16.
 *
 * References: http://www.all-guitar-chords.com/index.php
 *             https://en.wikipedia.org/wiki/List_of_chords
 */
final class ChordType {
    private static final String DATABASE_USERNAME = "";
    private static final String DATABASE_PASSWORD = "";
    private static final String DATABASE_DRIVER = "org.sqlite.JDBC";
    private static final String DATABASE_PROTOCOL = "jdbc:sqlite:";
    private static final String DATABASE_DIRECTORY = "src/resources/sqlite/chords.db";

    private static final int STARTING_INDEX = 1;
    private static Map<Integer, ChordType> INDEX_TO_CHORD_TYPE;
    private static Map<String, ChordType> NAME_TO_CHORD_TYPE;

    public final String SYMBOL;
    private final Interval[] INTERVALS;
    private final Octave[] BASE_OCTAVES;

    static {
        try {
            Class.forName(DATABASE_DRIVER);
            Connection connection = DriverManager
                    .getConnection(DATABASE_PROTOCOL + DATABASE_DIRECTORY, DATABASE_USERNAME, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Symbol, Intervals FROM supported_chords");
            int fetchSize = preparedStatement.getFetchSize();
            INDEX_TO_CHORD_TYPE = new HashMap<>(fetchSize);
            NAME_TO_CHORD_TYPE = new HashMap<>(fetchSize);
            ResultSet resultSet = preparedStatement.executeQuery();
            int index = STARTING_INDEX;
            while (resultSet.next()) {
                String name = resultSet.getString("Symbol");
                String[] intervalShortNames = resultSet.getString("Intervals").split(", ");
                final Interval[] intervals = new Interval[intervalShortNames.length];
                for (int i = 0; i < intervals.length; i++) {
                    intervals[i] = Interval.withShortName(intervalShortNames[i]);
                }
                final ChordType chordType = new ChordType(name, intervals);
                INDEX_TO_CHORD_TYPE.put(index, chordType);
                NAME_TO_CHORD_TYPE.put(name, chordType);
                index++;
            }
            preparedStatement.close();
            resultSet.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ChordType(String chordSymbol, Interval... intervals) {
        this.SYMBOL = chordSymbol;
        this.INTERVALS = intervals;
        this.BASE_OCTAVES = new Octave[intervals.length];
        BASE_OCTAVES[0] = Octave.OCTAVE_0;
        for (int i = 1; i < BASE_OCTAVES.length; i++) {
            if (INTERVALS[i].NUM_SEMITONES < INTERVALS[i - 1].NUM_SEMITONES) {
                BASE_OCTAVES[i] = Octave.OCTAVE_1;
            }
            else {
                BASE_OCTAVES[i] = Octave.OCTAVE_0;
            }
        }
    }

    static ChordType withName(String name) {
        return NAME_TO_CHORD_TYPE.get(name);
    }

    static ChordType withIndex(int index) {
        return INDEX_TO_CHORD_TYPE.get(index);
    }

    static List<String> listSupportedScaleTypes() {
        int numScales = STARTING_INDEX + INDEX_TO_CHORD_TYPE.size();
        List<String> list = new LinkedList<>();
        for (int i = STARTING_INDEX; i < numScales; i++) {
            list.add(i + ". " + INDEX_TO_CHORD_TYPE.get(i).SYMBOL);
        }
        return list;
    }

    public Interval[] getIntervals() {
        // Return a copy of the array (to protect against mutation)
        Interval[] intervals = new Interval[INTERVALS.length];
        System.arraycopy(INTERVALS, 0, intervals, 0, INTERVALS.length);
        return intervals;
    }

    public Octave[] getBaseOctaves() {
        // Return a copy of the array (to protect against mutation)
        Octave[] octaves = new Octave[BASE_OCTAVES.length];
        System.arraycopy(BASE_OCTAVES, 0, octaves, 0, BASE_OCTAVES.length);
        return octaves;
    }

    public int length() {
        return INTERVALS.length;
    }
}
