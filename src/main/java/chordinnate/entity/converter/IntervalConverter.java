package chordinnate.entity.converter;

import chordinnate.model.musictheory.pitch.interval.Interval;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.stream.Collectors;

@Converter
public class IntervalConverter implements AttributeConverter<Interval[], String> {

    @Override
    public String convertToDatabaseColumn(Interval[] attribute) {
        return Arrays.stream(attribute)
                .map(Interval::getSimpleShortName)
                .collect(Collectors.joining(", "));
    }

    @Override
    public Interval[] convertToEntityAttribute(String dbData) {
        return parseIntervals(dbData);
    }

    private static Interval[] parseIntervals(String intervalString) {
        String[] intervalShortNames = intervalString.split(", ");
        final Interval[] parsedIntervals = new Interval[intervalShortNames.length];
        for (int i = 0; i < parsedIntervals.length; i++) {
            parsedIntervals[i] = Interval.withShortName(intervalShortNames[i]);
        }
        return parsedIntervals;
    }
}