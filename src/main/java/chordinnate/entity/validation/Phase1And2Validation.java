package chordinnate.entity.validation;

import jakarta.validation.GroupSequence;

@GroupSequence({Phase1Validation.class, Phase2Validation.class})
public interface Phase1And2Validation {
}
