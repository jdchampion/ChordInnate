package chordinnate.model.musictheory.pitch.interval.set;

import org.hibernate.annotations.NamedQuery;

import javax.persistence.Cacheable;
import javax.persistence.Column;
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
@Entity
@Cacheable
@Table(name = "CHORD_TYPE")
@NamedQuery(name = "ChordType.findBySymbol", query = "SELECT c FROM ChordType c WHERE c.symbol = :symbol")
public final class ChordType {

    @Column(name = "SYMBOL", nullable = false)
    private String symbol;

    @Column(name = "INTERVALS", nullable = false)
    private String intervals;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntervals() {
        return intervals;
    }

    public void setIntervals(String intervals) {
        this.intervals = intervals;
    }

}
