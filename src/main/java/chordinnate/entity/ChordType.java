package chordinnate.entity;

import chordinnate.annotation.ValidateIntervals;
import chordinnate.annotation.ValidateSize;
import chordinnate.entity.validation.Phase1Validation;
import chordinnate.entity.validation.Phase2Validation;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.util.IntervalConverter;
import lombok.Data;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;

/**
 * Created by Joseph on 1/8/16.
 *
 * References: http://www.all-guitar-chords.com/index.php
 *             https://en.wikipedia.org/wiki/List_of_chords
 */
@Data
@Entity
@Cacheable
@Table(name = "CHORD_TYPE")
@ValidateSize(groups = Phase2Validation.class)
public final class ChordType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(groups = Phase1Validation.class, message = "{chordType.symbol.fieldName} {validation.constraints.blank}")
    @Column(name = "SYMBOL", nullable = false)
    private String symbol;

    @NotBlank(groups = Phase1Validation.class, message = "{chordType.rnSymbol.fieldName} {validation.constraints.blank}")
    @Column(name = "RN_SYMBOL", nullable = false)
    private String rnSymbol;

    @NotNull(groups = Phase1Validation.class, message = "{chordType.rnCapital.fieldName} {validation.constraints.null}")
    @Column(name = "RN_CAPITAL", nullable = false)
    private Boolean rnCapital;

    @NotNull(groups = Phase1Validation.class, message = "{chordType.rnPrecedence.fieldName} {validation.constraints.null}")
    @Positive(groups = Phase1Validation.class, message = "{chordType.rnPrecedence.fieldName} {validation.constraints.positive}")
    @Column(name = "RN_PRECEDENCE", nullable = false)
    private Integer rnPrecedence;

    @ValidateIntervals(groups = Phase2Validation.class)
    @NotNull(groups = Phase1Validation.class, message = "{chordType.intervals.fieldName} {validation.constraints.null}")
    @Size(groups = Phase1Validation.class, min = 2, message = "Must contain at least {min} intervals")
    @Column(name = "INTERVALS", nullable = false)
    @Convert(converter = IntervalConverter.class)
    private Interval[] intervals;

    @Positive(groups = Phase1Validation.class, message = "{chordType.size.fieldName} {validation.constraints.positive}")
    @Column(name = "SIZE", nullable = false)
    private Integer size;

    @NotNull(groups = Phase1Validation.class, message = "{chordType.preset.fieldName} {validation.constraints.null}")
    @Column(name = "PRESET", nullable = false)
    private Boolean preset;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "matchingChordType")
    private Set<ChordTypeTagRelation> chordTypeTagRelations;

    @Override
    public boolean equals(Object other) {
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }

        ChordType comparison = (ChordType) other;

        return id.equals(comparison.id)
                && symbol.equals(comparison.symbol)
                && rnSymbol.equals(comparison.rnSymbol)
                && rnCapital.equals(comparison.rnCapital)
                && rnPrecedence.equals(comparison.rnPrecedence)
                && Arrays.deepEquals(intervals, comparison.intervals)
                && size.equals(comparison.size)
                && preset.equals(comparison.preset);
    }

}
