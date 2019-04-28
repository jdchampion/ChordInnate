package chordinnate.model.musictheory.pitch.interval.set;

import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.model.musictheory.pitch.interval.IntervalConverter;
import lombok.Data;
import org.hibernate.annotations.NamedQuery;

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
@NamedQuery(name = "ChordType.findBySymbol", query = "SELECT c FROM ChordType c WHERE c.symbol = :symbol")
@NamedQuery(name = "ChordType.findByIntervals", query = "SELECT c FROM ChordType c WHERE c.intervals = :intervals")
@NamedQuery(name = "ChordType.findAllDistinctByIntervals", query = "SELECT c FROM ChordType c WHERE c.intervals IN :intervals")
public final class ChordType {

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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

}
