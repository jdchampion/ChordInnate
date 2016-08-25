package chordinnate.musictheory.pitch.interval.set;

import chordinnate.musictheory.pitch.interval.Interval;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 * Created by Joseph on 8/24/16.
 */
final class ScaleType {
    private static final String DATABASE_USERNAME = "";
    private static final String DATABASE_PASSWORD = "";
    private static final String DATABASE_DRIVER = "org.sqlite.JDBC";
    private static final String DATABASE_PROTOCOL = "jdbc:sqlite:";
    private static final String DATABASE_DIRECTORY = "src/resources/sqlite/scales.db";

    private static final int STARTING_INDEX = 1;
    private static Map<Integer, ScaleType> INDEX_TO_SCALE_TYPE;
    private static Map<String, ScaleType> NAME_TO_SCALE_TYPE;

    final String NAME, ORIGIN;
    private final Interval[] INTERVALS;

    static {
        try {
            Class.forName(DATABASE_DRIVER);
            Connection connection = DriverManager
                    .getConnection(DATABASE_PROTOCOL + DATABASE_DIRECTORY, DATABASE_USERNAME, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Name, Intervals, Origin FROM supported_scales");
            int fetchSize = preparedStatement.getFetchSize();
            INDEX_TO_SCALE_TYPE = new HashMap<>(fetchSize);
            NAME_TO_SCALE_TYPE = new HashMap<>(fetchSize);
            ResultSet resultSet = preparedStatement.executeQuery();
            int index = STARTING_INDEX;
            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                String[] intervalShortNames = resultSet.getString("Intervals").split(", ");
                final Interval[] intervals = new Interval[intervalShortNames.length];
                for (int i = 0; i < intervals.length; i++) {
                    intervals[i] = Interval.withShortName(intervalShortNames[i]);
                }
                String origin = resultSet.getString("Origin");
                final ScaleType scaleType = new ScaleType(name, intervals, origin);
                INDEX_TO_SCALE_TYPE.put(index, scaleType);
                NAME_TO_SCALE_TYPE.put(name, scaleType);
                index++;
            }
            preparedStatement.close();
            resultSet.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ScaleType(String name, Interval[] intervals, String origin) {
        this.NAME = name;
        this.INTERVALS = intervals;
        this.ORIGIN = origin;
    }

    static ScaleType withName(String name) {
        return NAME_TO_SCALE_TYPE.get(name);
    }

    static ScaleType withIndex(int index) {
        return INDEX_TO_SCALE_TYPE.get(index);
    }

    static List<String> listSupportedScaleTypes() {
        int numScales = STARTING_INDEX + INDEX_TO_SCALE_TYPE.size();
        List<String> list = new LinkedList<>();
        for (int i = STARTING_INDEX; i < numScales; i++) {
            list.add(i + ". " + INDEX_TO_SCALE_TYPE.get(i).NAME);
        }
        return list;
    }

    Interval[] getIntervals() {
        // Return a copy of the array (to protect against mutation)
        Interval[] intervals = new Interval[INTERVALS.length];
        System.arraycopy(INTERVALS, 0, intervals, 0, INTERVALS.length);
        return intervals;
    }
}
