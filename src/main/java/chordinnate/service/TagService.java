package chordinnate.service;

import chordinnate.entity.Tag;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface TagService {

    Optional<Tag> findById(@NotNull Integer id);

    Optional<Tag> findByName(@NotNull String name);

}
