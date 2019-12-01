package chordinnate.repository.musictheory;

import chordinnate.model.musictheory.pitch.interval.set.ScaleType;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ScaleTypeRepository extends CrudRepository<ScaleType, Integer> {

    @Query("SELECT s FROM ScaleType s WHERE UPPER(s.name) = UPPER(:name)")
    Optional<ScaleType> findByName(@NotNull @Param("name") String name);

    @Query("SELECT s FROM ScaleType s WHERE s.origin = :origin")
    Optional<ScaleType> findByOriginId(@NotNull @Param("origin") Integer originId);

    @Query("SELECT s FROM ScaleType s WHERE s.origin IN :originIds")
    Collection<ScaleType> findAllByOriginIds(@NotNull @Param("originIds") Integer... originIds);

    @Query("SELECT s FROM ScaleType s WHERE s.origin IN :originIds")
    Collection<ScaleType> findAllByOriginIds(@NotNull @Param("originIds") Collection<Integer> originIds);

}
