package chordinnate.entity;

import chordinnate.annotation.ValidateIntervals;
import chordinnate.annotation.ValidateSize;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.util.IntervalConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
import java.util.Set;

/**
 * Created by Joseph on 8/24/16.
 */
@Data
@EqualsAndHashCode(exclude = {"scaleTypeTagRelations"})
@Entity
@Cacheable
@Table(name = "SCALE_TYPE")
@ValidateSize
@ValidateIntervals
public final class ScaleType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "{scaleType.name.fieldName} {validation.constraints.blank}")
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "ORIGIN")
    private Integer origin;

    @NotNull(message = "{scaleType.intervals.fieldName} {validation.constraints.null}")
    @Size(min = 2, message = "Must contain at least {min} intervals")
    @Column(name = "INTERVALS", nullable = false)
    @Convert(converter = IntervalConverter.class)
    private Interval[] intervals;

    @Positive(message = "{scaleType.size.fieldName} {validation.constraints.positive}")
    @Column(name = "SIZE", nullable = false)
    private Integer size;

    @NotNull(message = "{scaleType.preset.fieldName} {validation.constraints.null}")
    @Column(name = "PRESET", nullable = false)
    private Boolean preset;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "matchingScaleType")
    private Set<ScaleTypeTagRelation> scaleTypeTagRelations;
    
}
