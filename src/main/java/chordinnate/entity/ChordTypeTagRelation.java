package chordinnate.entity;

import chordinnate.entity.validation.Phase1Validation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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

/**
 * An intermediary object representing the relation between a {@link ChordType}
 * and one of its associated {@link Tag} objects.
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

    @NotNull(groups = Phase1Validation.class, message = "Chord Type {validation.constraints.null}")
    @Valid
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "CHORD_TYPE_ID", referencedColumnName = "ID")
    private ChordType matchingChordType;

    @NotNull(groups = Phase1Validation.class, message = "Tag {validation.constraints.null}")
    @Valid
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "TAG_ID")
    private Tag matchingTag;

    public ChordTypeTagRelation(ChordType chordType, Tag tag) {
        this.matchingChordType = chordType;
        this.matchingTag = tag;
    }

}
