package chordinnate.entity.validation;

import javax.validation.GroupSequence;

@GroupSequence({Phase1Validation.class, Phase2Validation.class})
public interface Phase1And2Validation {
}
