package chordinnate.entity;

import chordinnate.annotation.ValidateIntervalContour;
import chordinnate.annotation.ValidateSize;
import chordinnate.entity.validation.Phase1Validation;
import chordinnate.entity.validation.Phase2Validation;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.musictheory.pitch.interval.set.IntervalDirection;
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
 * Created by Joseph on 8/24/16.
 */
@Data
@Entity
@Cacheable
@Table(name = "SCALE_TYPE")
@ValidateSize(groups = Phase2Validation.class)
public final class ScaleType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(groups = Phase1Validation.class, message = "{scaleType.name.fieldName} {validation.constraints.blank}")
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "ORIGIN")
    private Integer origin;

    @ValidateIntervalContour(groups = Phase2Validation.class, message = "Intervals must be increasing or decreasing at each step", directions = {IntervalDirection.ASCENDING, IntervalDirection.DESCENDING})
    @NotNull(groups = Phase1Validation.class, message = "{scaleType.intervals.fieldName} {validation.constraints.null}")
    @Size(groups = Phase1Validation.class, min = 2, message = "Must contain at least {min} intervals")
    @Column(name = "INTERVALS", nullable = false)
    @Convert(converter = IntervalConverter.class)
    private Interval[] intervals;

    @Positive(groups = Phase1Validation.class, message = "{scaleType.size.fieldName} {validation.constraints.positive}")
    @Column(name = "SIZE", nullable = false)
    private Integer size;

    @NotNull(groups = Phase1Validation.class, message = "{scaleType.preset.fieldName} {validation.constraints.null}")
    @Column(name = "PRESET", nullable = false)
    private Boolean preset;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "matchingScaleType")
    private Set<ScaleTypeTagRelation> scaleTypeTagRelations;

    @Override
    public boolean equals(Object other) {
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }

        ScaleType comparison = (ScaleType) other;

        return id.equals(comparison.id)
                && name.equals(comparison.name)
                && ((origin == null && comparison.origin == null) || origin.equals(comparison.origin))
                && Arrays.deepEquals(intervals, comparison.intervals)
                && size.equals(comparison.size)
                && preset.equals(comparison.preset);
    }
    
}
