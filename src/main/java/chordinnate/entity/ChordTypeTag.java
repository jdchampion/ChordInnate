package chordinnate.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

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

@Data
@Entity
@Cacheable
@Table(name = "TAG")
@EqualsAndHashCode(callSuper = true, exclude = {"chordTypeTagRelations"})
public final class ChordTypeTag extends Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "matchingTag", cascade = CascadeType.ALL)
    private Set<ChordTypeTagRelation> chordTypeTagRelations;

}
