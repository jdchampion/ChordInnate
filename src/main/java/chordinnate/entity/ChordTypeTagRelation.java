package chordinnate.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * An intermediary object representing the relation between a {@link ChordType}
 * and one of its associated {@link ChordTypeTag} objects.
 */
@Data
@Entity
@EqualsAndHashCode
@Table(name = "CHORD_TYPE_TAG")
public final class ChordTypeTagRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "CHORD_TYPE_ID", referencedColumnName = "ID")
    private ChordType matchingChordType;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "TAG_ID")
    private ChordTypeTag matchingTag;

}
