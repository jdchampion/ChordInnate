package chordinnate.dao.musictheory;

import chordinnate.model.musictheory.pitch.interval.set.ScaleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ScaleTypeDAO extends JpaRepository<ScaleType, Integer> {

    @Query("SELECT s FROM ScaleType s WHERE UPPER(s.name) = UPPER(:name)")
    Optional<ScaleType> findByName(@Param("name") String name);

}
