package chordinnate.entity;

import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.util.IntervalConverter;
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
 * Created by Joseph on 1/8/16.
 *
 * References: http://www.all-guitar-chords.com/index.php
 *             https://en.wikipedia.org/wiki/List_of_chords
 */
@Data
@Entity
@Cacheable
@Table(name = "CHORD_TYPE")
public final class ChordType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "SYMBOL", nullable = false)
    private String symbol;

    @Column(name = "RN_SYMBOL", nullable = false)
    private String rnSymbol;

    @Column(name = "RN_CAPITAL", nullable = false)
    private boolean rnCapital;

    @Column(name = "RN_PRECEDENCE", nullable = false)
    private int rnPrecedence;

    @Column(name = "INTERVALS", nullable = false)
    @Convert(converter = IntervalConverter.class)
    private Interval[] intervals;

    @Column(name = "SIZE", nullable = false)
    private int size;

}
