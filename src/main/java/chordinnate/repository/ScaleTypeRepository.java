package chordinnate.repository;

import chordinnate.entity.ScaleType;
import chordinnate.model.musictheory.pitch.interval.Interval;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScaleTypeRepository extends CrudRepository<ScaleType, Integer> {

    @Query("SELECT s FROM ScaleType s WHERE UPPER(s.name) = UPPER(:name)")
    Optional<ScaleType> findByName(@NotNull @Param("name") String name);

    Optional<ScaleType> findByIntervals(@Param("intervals") Interval... intervals);

    @Query("SELECT s FROM ScaleType s WHERE s.intervals IN :intervals")
    Iterable<ScaleType> findAllByIntervals(@Param("intervals") Collection<Interval[]> intervals);

    @Query("SELECT s FROM ScaleType s WHERE s.origin = :origin")
    Optional<ScaleType> findByOriginId(@NotNull @Param("origin") Integer originId);

    @Query("SELECT s FROM ScaleType s WHERE s.origin IN :originIds")
    Iterable<ScaleType> findAllByOriginId(@NotNull @Param("originIds") Integer... originIds);

    @Query("SELECT s FROM ScaleType s WHERE s.origin IN :originIds")
    Iterable<ScaleType> findAllByOriginId(@NotNull @Param("originIds") Collection<Integer> originIds);

    @Query("SELECT s FROM ScaleType s WHERE s.size >= :min AND s.size <= :max")
    Iterable<ScaleType> findAllBySizeRange(@Param("min") int min, @Param("max") int max);

    @Query("SELECT s FROM ScaleType s JOIN FETCH ScaleTypeTagRelation sttr ON s.id = sttr.matchingScaleType.id JOIN FETCH ScaleTypeTag t ON sttr.matchingTag.id = t.id WHERE t.name IN (:tags)")
    Iterable<ScaleType> findAllByTag(@NotNull @Param("tags") Collection<String> tags);

    /**
     * Intended for internal use only.
     * <p>
     * Retrieves any existing ScaleTypes that contain at least one of the specified fields,
     * which are UNIQUE CONSTRAINT on the SCALE_TYPE table. Used for checking the database before saving.
     *
     * @param id
     * @param name
     * @return
     */
    @Query("SELECT s FROM ScaleType s WHERE s.id = :id OR s.name = :name")
    List<ScaleType> findAnyMatchingUniqueConstraints(@Param("id") int id, @Param("name") String name);

}
