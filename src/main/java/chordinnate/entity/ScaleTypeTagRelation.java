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
 * An intermediary object representing the relation between a {@link ScaleType}
 * and one of its associated {@link ScaleTypeTag} objects.
 */
@Data
@Entity
@EqualsAndHashCode
@Table(name = "SCALE_TYPE_TAG")
public final class ScaleTypeTagRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "SCALE_TYPE_ID", referencedColumnName = "ID")
    private ScaleType matchingScaleType;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "TAG_ID")
    private ScaleTypeTag matchingTag;

}
