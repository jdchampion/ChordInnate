package chordinnate.entity;

import lombok.Data;

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
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@Entity
@Cacheable
@Table(name = "TAG")
public final class ChordTypeTag extends Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "{tag.name.fieldName} {validation.constraints.blank}")
    @Column(name = "NAME")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "matchingTag", cascade = CascadeType.ALL)
    private Set<ChordTypeTagRelation> chordTypeTagRelations;

    @Override
    public boolean equals(Object other) {
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }

        ChordTypeTag comparison = (ChordTypeTag) other;

        return id.equals(comparison.id) && name.equals(comparison.name);
    }

}
