package chordinnate.entity;

import chordinnate.entity.validation.Phase1Validation;
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
 * An intermediary object representing the relation between a {@link ScaleType}
 * and one of its associated {@link Tag} objects.
 */
@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "SCALE_TYPE_TAG")
public final class ScaleTypeTagRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(groups = Phase1Validation.class, message = "Scale Type {validation.constraints.null}")
    @Valid
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "SCALE_TYPE_ID", referencedColumnName = "ID")
    private ScaleType matchingScaleType;

    @NotNull(groups = Phase1Validation.class, message = "Tag {validation.constraints.null}")
    @Valid
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "TAG_ID")
    private Tag matchingTag;

    public ScaleTypeTagRelation(ScaleType scaleType, Tag tag) {
        this.matchingScaleType = scaleType;
        this.matchingTag = tag;
    }

}
