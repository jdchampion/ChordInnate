package chordinnate.entity;

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
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Joseph on 8/24/16.
 */
@Data
@EqualsAndHashCode(exclude = {"tagGroupRels"})
@Entity
@Cacheable
@Table(name = "SCALE_TYPE")
public final class ScaleType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "ORIGIN")
    private Integer origin;

    @Column(name = "INTERVALS", nullable = false)
    @Convert(converter = IntervalConverter.class)
    private Interval[] intervals;

    @Column(name = "PRESET", nullable = false)
    private boolean preset;

    @Column(name = "TAG_GROUP_ID", nullable = false)
    private UUID tagGroupId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "matchingScaleType")
    Set<TagGroupRel> tagGroupRels;
    
}
