package chordinnate.service.impl;

import chordinnate.entity.ScaleTypeTagRelation;
import chordinnate.repository.ScaleTypeTagRelationRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

final class ScaleTypeTagRelationValidator {

    public static Pair<Boolean, String> validateBeforeSaving(ScaleTypeTagRelation scaleTypeTagRelation, ScaleTypeTagRelationRepository repository) {
        return Pair.of(Boolean.TRUE, StringUtils.EMPTY);
    }

}
