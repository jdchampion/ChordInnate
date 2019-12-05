package chordinnate.service;

import chordinnate.entity.TagGroupRel;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface TagGroupRelService {

    Optional<TagGroupRel> findById(@NotNull Integer id);

}
