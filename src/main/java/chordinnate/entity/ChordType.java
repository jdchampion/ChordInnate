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

/**
 * Created by Joseph on 1/8/16.
 *
 * References: http://www.all-guitar-chords.com/index.php
 *             https://en.wikipedia.org/wiki/List_of_chords
 */
@Data
@EqualsAndHashCode(exclude = {"chordTypeTagRelations"})
@Entity
@Cacheable
@Table(name = "CHORD_TYPE")
public final class ChordType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "SYMBOL", nullable = false)
    private String symbol;

    @Column(name = "RN_SYMBOL", nullable = false)
    private String rnSymbol;

    @Column(name = "RN_CAPITAL", nullable = false)
    private Boolean rnCapital;

    @Column(name = "RN_PRECEDENCE", nullable = false)
    private Integer rnPrecedence;

    @Column(name = "INTERVALS", nullable = false)
    @Convert(converter = IntervalConverter.class)
    private Interval[] intervals;

    @Column(name = "SIZE", nullable = false)
    private Integer size;

    @Column(name = "PRESET", nullable = false)
    private Boolean preset;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "matchingChordType")
    private Set<ChordTypeTagRelation> chordTypeTagRelations;

}
