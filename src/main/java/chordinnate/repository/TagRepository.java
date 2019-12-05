package chordinnate.repository;

import chordinnate.entity.Tag;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends CrudRepository<Tag, Integer> {

    @Query("SELECT t FROM Tag t WHERE t.id = :id")
    Optional<Tag> findById(@NotNull @Param("id") Integer id);

    @Query("SELECT t FROM Tag t WHERE UPPER(t.name) = UPPER(:name)")
    Optional<Tag> findByName(@NotNull @Param("name") String name);

}
