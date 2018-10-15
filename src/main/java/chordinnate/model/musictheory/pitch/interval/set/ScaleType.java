package chordinnate.model.musictheory.pitch.interval.set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Created by Joseph on 8/24/16.
 */
@Entity
@Table(name = "SCALE_TYPE")
@NamedQueries({
        @NamedQuery(name = "ScaleType.findByName", query = "SELECT s FROM ScaleType s WHERE s.name = :name")
})
public final class ScaleType {

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "ORIGIN")
    private String origin;

    @Column(name = "INTERVALS", nullable = false)
    private String intervals;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getIntervals() {
        return intervals;
    }

    public void setIntervals(String intervals) {
        this.intervals = intervals;
    }
}
