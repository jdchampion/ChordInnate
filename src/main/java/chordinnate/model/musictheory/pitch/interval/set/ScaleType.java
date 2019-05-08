package chordinnate.model.musictheory.pitch.interval.set;

import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.musictheory.pitch.interval.IntervalConverter;
import lombok.Data;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Joseph on 8/24/16.
 */
@Data
@Entity
@Cacheable
@Table(name = "SCALE_TYPE")
public final class ScaleType {

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "ORIGIN")
    private String origin;

    @Column(name = "INTERVALS", nullable = false)
    @Convert(converter = IntervalConverter.class)
    private Interval[] intervals;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
}
