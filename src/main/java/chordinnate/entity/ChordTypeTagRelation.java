package chordinnate.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * An intermediary object representing the relation between a {@link ChordType}
 * and one of its associated {@link ChordTypeTag} objects.
 */
@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "CHORD_TYPE_TAG")
public final class ChordTypeTagRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Chord Type {validation.constraints.null}")
    @Valid
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "CHORD_TYPE_ID", referencedColumnName = "ID")
    private ChordType matchingChordType;

    @NotNull(message = "Tag {validation.constraints.null}")
    @Valid
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "TAG_ID")
    private ChordTypeTag matchingTag;

    public ChordTypeTagRelation(ChordType chordType, ChordTypeTag tag) {
        this.matchingChordType = chordType;
        this.matchingTag = tag;
    }

}
