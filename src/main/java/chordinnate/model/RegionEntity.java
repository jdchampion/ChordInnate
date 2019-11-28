package chordinnate.model;

import lombok.Data;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * References: https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2
 *             https://en.wikipedia.org/wiki/List_of_countries_by_United_Nations_geoscheme
 *             https://en.wikipedia.org/wiki/UN_M49
 */
@Data
@Entity
@Cacheable
@Table(name = "REGION")
public class RegionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * Integer format for the ISO 3166-1 numeric region code, as maintained by UNM.49
     * and utilized by ICU4J for getting region info
     */
    @Column(name = "CODE")
    private int regionCode;

    /**
     * English short name for the region, as indicated by UNM.49
     */
    @Column(name = "NAME")
    private String regionName;

    /**
     * ISO 3166-1 Alpha-2 region code, as maintained by UNM.49
     * and utilized by ICU4J for getting region info
     */
    @Column(name = "ALIAS")
    private String regionAlias;

}
