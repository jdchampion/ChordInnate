package chordinnate.service.impl;

import chordinnate.entity.ChordTypeTagRelation;
import chordinnate.repository.ChordTypeTagRelationRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

final class ChordTypeTagRelationValidator {

    public static Pair<Boolean, String> validateBeforeSaving(ChordTypeTagRelation chordTypeTagRelation, ChordTypeTagRelationRepository repository) {
        return Pair.of(Boolean.TRUE, StringUtils.EMPTY);
    }

}
