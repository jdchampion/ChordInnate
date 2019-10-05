package chordinnate.repository.musictheory;

import chordinnate.model.musictheory.pitch.interval.set.ScaleType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScaleTypeRepository extends CrudRepository<ScaleType, Integer> {

    @Query("SELECT s FROM ScaleType s WHERE UPPER(s.name) = UPPER(:name)")
    Optional<ScaleType> findByName(@Param("name") String name);

}
