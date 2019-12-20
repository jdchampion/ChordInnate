package chordinnate.service.impl;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import chordinnate.entity.ChordType;
import chordinnate.exception.ChordInnateConstraintViolation;
import chordinnate.exception.ChordInnateException;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.repository.ChordTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Optional;

@Slf4j
@RunWith(PowerMockRunner.class)
@PrepareForTest({ChordTypeRepository.class})
public class TestChordTypeServiceImpl {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private static ChordTypeServiceImpl service;
    private static ChordTypeRepository mockRepo;
    private static ChordType TEST_CHORD_TYPE;

    @BeforeClass
    public static void init() {
        mockRepo = mock(ChordTypeRepository.class);
        when(mockRepo.save(any(ChordType.class))).thenReturn(TEST_CHORD_TYPE);
        doNothing().when(mockRepo).delete(any(ChordType.class));

        service = new ChordTypeServiceImpl(mockRepo);
    }

    @Before
    public void setup() {
        // example of a completely valid chord type
        TEST_CHORD_TYPE = new ChordType();
        TEST_CHORD_TYPE.setSymbol("TEST");
        TEST_CHORD_TYPE.setRnSymbol("TEST");
        TEST_CHORD_TYPE.setRnCapital(Boolean.TRUE);
        TEST_CHORD_TYPE.setRnPrecedence(1);
        TEST_CHORD_TYPE.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        TEST_CHORD_TYPE.setSize(2);
        TEST_CHORD_TYPE.setPreset(Boolean.FALSE);
    }


    @Test
    public void save_nullParam() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Cannot save null entities");
        service.save(null);
    }

    @Test
    public void save_blankParam() {
        expectedException.expect(ChordInnateConstraintViolation.class);
        service.save(new ChordType());
    }

    @Test
    public void save_missingRequiredField_symbol() {
        expectedException.expect(ChordInnateConstraintViolation.class);
        expectedException.expectMessage("Symbol must not be blank");

        TEST_CHORD_TYPE.setSymbol(null);

        service.save(TEST_CHORD_TYPE);
    }

    @Test
    public void save_missingRequiredField_rnSymbol() {
        expectedException.expect(ChordInnateConstraintViolation.class);
        expectedException.expectMessage("Roman Numeral Symbol must not be blank");

        TEST_CHORD_TYPE.setRnSymbol(null);

        service.save(TEST_CHORD_TYPE);
    }

    @Test
    public void save_missingRequiredField_rnCapital() {
        expectedException.expect(ChordInnateConstraintViolation.class);
        expectedException.expectMessage("Roman Numeral Capital must not be null");

        TEST_CHORD_TYPE.setRnCapital(null);

        service.save(TEST_CHORD_TYPE);
    }

    @Test
    public void save_missingRequiredField_rnPrecedence() {
        expectedException.expect(ChordInnateConstraintViolation.class);
        expectedException.expectMessage("Roman Numeral Precedence must not be null");

        TEST_CHORD_TYPE.setRnPrecedence(null);

        service.save(TEST_CHORD_TYPE);
    }

    @Test
    public void save_missingRequiredField_intervals() {
        expectedException.expect(ChordInnateConstraintViolation.class);
        expectedException.expectMessage("Intervals must not be null");

        TEST_CHORD_TYPE.setIntervals(null);

        service.save(TEST_CHORD_TYPE);
    }

    @Test
    public void save_invalidField_symbol() {
        expectedException.expect(ChordInnateConstraintViolation.class);
        expectedException.expectMessage("Symbol must not be blank");

        TEST_CHORD_TYPE.setSymbol("");

        service.save(TEST_CHORD_TYPE);
    }

    @Test
    public void save_invalidField_rnPrecedence() {
        expectedException.expect(ChordInnateConstraintViolation.class);
        expectedException.expectMessage("Roman Numeral Precedence must be positive (was: 0)");

        TEST_CHORD_TYPE.setRnPrecedence(0);

        service.save(TEST_CHORD_TYPE);
    }

    @Test
    public void save_invalidField_intervals_notEnough() {
        expectedException.expect(ChordInnateConstraintViolation.class);
        expectedException.expectMessage("Must contain at least 2 intervals");

        TEST_CHORD_TYPE.setIntervals(new Interval[]{});

        service.save(TEST_CHORD_TYPE);
    }

    @Test
    public void save_invalidField_intervals_duplicated() {
        expectedException.expect(ChordInnateConstraintViolation.class);
        expectedException.expectMessage("Intervals must be increasing or decreasing at each step");

        TEST_CHORD_TYPE.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.PERFECT_1});

        service.save(TEST_CHORD_TYPE);
    }

    @Test
    public void save_invalidField_intervals_notIncreasingDecreasing() {
        expectedException.expect(ChordInnateConstraintViolation.class);
        expectedException.expectMessage("Intervals must be increasing or decreasing at each step");

        TEST_CHORD_TYPE.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_3, Interval.MAJOR_2});
        TEST_CHORD_TYPE.setSize(3); // to prevent "invalid size" error

        service.save(TEST_CHORD_TYPE);
    }

    @Test
    public void save_invalidField_size_notMatching() {
        expectedException.expect(ChordInnateConstraintViolation.class);
        expectedException.expectMessage("Size must match the number of intervals");

        TEST_CHORD_TYPE.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        TEST_CHORD_TYPE.setSize(1);

        service.save(TEST_CHORD_TYPE);
    }

    @Test
    public void save_invalidField_size_notPositive() {
        expectedException.expect(ChordInnateConstraintViolation.class);
        expectedException.expectMessage("Size must be positive (was: 0)");

        TEST_CHORD_TYPE.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        TEST_CHORD_TYPE.setSize(0);

        service.save(TEST_CHORD_TYPE);
    }

    @Test
    public void save_overwriteExistingPreset() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("Overwriting preset chord types is not allowed");

        ChordType inRepo = new ChordType();
        inRepo.setId(1);
        inRepo.setSymbol("SYMBOL 1");
        inRepo.setRnSymbol("TEST");
        inRepo.setRnCapital(Boolean.TRUE);
        inRepo.setRnPrecedence(1);
        inRepo.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        inRepo.setPreset(Boolean.TRUE);
        inRepo.setSize(2);

        when(mockRepo.findById(anyInt())).thenReturn(Optional.of(inRepo));

        // Attempt to update an existing ChordType that is a preset
        TEST_CHORD_TYPE = service.findById(1).get();
        TEST_CHORD_TYPE.setSymbol("SYMBOL 2");

        service.save(TEST_CHORD_TYPE);
    }

    @Test
    public void save_attemptToCreateNewPreset() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("Creating new preset chord types is not allowed");

        TEST_CHORD_TYPE.setPreset(Boolean.TRUE);

        service.save(TEST_CHORD_TYPE);
    }

    @Test
    public void save_success() {
        service.save(TEST_CHORD_TYPE);
    }

    @Test
    public void delete_nullParam() {
        service.delete(null);
    }

    @Test
    public void delete_blankParam() {
        service.delete(new ChordType());
    }

    @Test
    public void delete_missingId() {
        TEST_CHORD_TYPE.setId(null);

        service.delete(TEST_CHORD_TYPE);
    }

    @Test
    public void delete_invalidId() {
        TEST_CHORD_TYPE.setId(1);

        when(mockRepo.findById(anyInt())).thenReturn(Optional.empty());

        service.delete(TEST_CHORD_TYPE);

        verify(mockRepo, atLeast(1)).findById(anyInt());
    }

    @Test
    public void delete_existingRecordDoesNotMatchEntity() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("Cannot delete chord type #1 (DIFFERENT SYMBOL): entity data does not match record data");

        ChordType inRepo = new ChordType();
        inRepo.setId(1);
        inRepo.setSymbol("SYMBOL");
        inRepo.setRnSymbol("TEST");
        inRepo.setRnCapital(Boolean.TRUE);
        inRepo.setRnPrecedence(1);
        inRepo.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        inRepo.setPreset(Boolean.FALSE);
        inRepo.setSize(2);

        when(mockRepo.findById(anyInt())).thenReturn(Optional.of(inRepo));

        TEST_CHORD_TYPE.setId(1);
        TEST_CHORD_TYPE.setSymbol("DIFFERENT SYMBOL");
        TEST_CHORD_TYPE.setRnSymbol("TEST");
        TEST_CHORD_TYPE.setRnCapital(Boolean.TRUE);
        TEST_CHORD_TYPE.setRnPrecedence(1);
        TEST_CHORD_TYPE.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        TEST_CHORD_TYPE.setPreset(Boolean.FALSE);
        TEST_CHORD_TYPE.setSize(2);

        service.delete(TEST_CHORD_TYPE);

        verify(mockRepo, atLeast(1)).findById(anyInt());
    }

    @Test
    public void delete_recordIsPreset() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("Cannot delete preset chord types");

        TEST_CHORD_TYPE.setId(1);
        TEST_CHORD_TYPE.setPreset(Boolean.TRUE);

        when(mockRepo.findById(anyInt())).thenReturn(Optional.of(TEST_CHORD_TYPE));

        service.delete(TEST_CHORD_TYPE);

        verify(mockRepo, atLeast(1)).findById(anyInt());
    }

    @Test
    public void delete_success() {
        TEST_CHORD_TYPE.setId(1);

        when(mockRepo.findById(anyInt())).thenReturn(Optional.of(TEST_CHORD_TYPE));

        service.delete(TEST_CHORD_TYPE);

        verify(mockRepo, atLeast(1)).findById(anyInt());
    }

}
