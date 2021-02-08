package chordinnate.entity;

import chordinnate.entity.validation.Phase1Validation;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Metadata for storable entities that can be used
 * for grouping and/or classifying the entities.
 */
@Data
@Entity
@Cacheable
@Table(name = "TAG")
@NoArgsConstructor
public final class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(groups = Phase1Validation.class, message = "{tag.name.fieldName} {validation.constraints.blank}")
    @Column(name = "NAME")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "matchingTag", cascade = CascadeType.ALL)
    private Set<ScaleTypeTagRelation> scaleTypeTagRelations;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "matchingTag", cascade = CascadeType.ALL)
    private Set<ChordTypeTagRelation> chordTypeTagRelations;

    public Tag(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }

        Tag comparison = (Tag) other;

        return id.equals(comparison.id) && name.equals(comparison.name);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
