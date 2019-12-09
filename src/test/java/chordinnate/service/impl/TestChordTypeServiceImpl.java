package chordinnate.service.impl;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import chordinnate.entity.ChordType;
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

import java.util.Arrays;
import java.util.Collections;
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
        TEST_CHORD_TYPE = new ChordType();
        TEST_CHORD_TYPE.setId(1);
        TEST_CHORD_TYPE.setSymbol("TEST");
        TEST_CHORD_TYPE.setRnSymbol("TEST");
        TEST_CHORD_TYPE.setRnCapital(Boolean.TRUE);
        TEST_CHORD_TYPE.setRnPrecedence(1);
        TEST_CHORD_TYPE.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
    }


    @Test
    public void save_nullParam() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("No chord type to save.");
        service.save(null);
    }

    @Test
    public void save_blankParam() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("No chord type to save.");
        service.save(new ChordType());
    }

    @Test
    public void save_missingRequiredField_symbol() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following required fields are missing: Symbol");

        TEST_CHORD_TYPE.setSymbol(null);

        service.save(TEST_CHORD_TYPE);
    }

    @Test
    public void save_missingRequiredField_rnSymbol() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following required fields are missing: Roman Numeral Symbol");

        TEST_CHORD_TYPE.setRnSymbol(null);

        service.save(TEST_CHORD_TYPE);
    }

    @Test
    public void save_missingRequiredField_rnCapital() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following required fields are missing: Roman Numeral Capital");

        TEST_CHORD_TYPE.setRnCapital(null);

        service.save(TEST_CHORD_TYPE);
    }

    @Test
    public void save_missingRequiredField_rnPrecedence() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following required fields are missing: Roman Numeral Precedence");

        TEST_CHORD_TYPE.setRnPrecedence(null);

        service.save(TEST_CHORD_TYPE);
    }

    @Test
    public void save_missingRequiredField_intervals() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following required fields are missing: Intervals");

        TEST_CHORD_TYPE.setIntervals(null);

        service.save(TEST_CHORD_TYPE);
    }

    @Test
    public void save_invalidField_symbol() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following fields are invalid: Symbol (cannot be blank)");

        TEST_CHORD_TYPE.setSymbol("");

        service.save(TEST_CHORD_TYPE);
    }

    @Test
    public void save_invalidField_rnPrecedence() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following fields are invalid: Roman Numeral Precedence (must be > 0)");

        TEST_CHORD_TYPE.setRnPrecedence(0);

        service.save(TEST_CHORD_TYPE);
    }

    @Test
    public void save_invalidField_intervals_notEnough() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following fields are invalid: Intervals (must contain at least two intervals)");

        TEST_CHORD_TYPE.setIntervals(new Interval[]{});

        service.save(TEST_CHORD_TYPE);
    }

    @Test
    public void save_invalidField_intervals_duplicated() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following fields are invalid: Intervals (cannot contain duplicates)");

        TEST_CHORD_TYPE.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.PERFECT_1});

        service.save(TEST_CHORD_TYPE);
    }

    @Test
    public void save_invalidField_intervals_notIncreasingDecreasing() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following fields are invalid: Intervals (must be increasing or decreasing at each step)");

        TEST_CHORD_TYPE.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_3, Interval.MAJOR_2});

        service.save(TEST_CHORD_TYPE);
    }

    @Test
    public void save_invalidField_size() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following fields are invalid: Size (must match the number of intervals)");

        TEST_CHORD_TYPE.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        TEST_CHORD_TYPE.setSize(0);

        service.save(TEST_CHORD_TYPE);
    }

    @Test
    public void save_overwriteExistingPreset() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("Cannot overwrite a preset chord type.");

        ChordType inRepo = new ChordType();
        inRepo.setId(1);
        inRepo.setSymbol("maj");
        inRepo.setRnSymbol("TEST");
        inRepo.setRnCapital(Boolean.TRUE);
        inRepo.setRnPrecedence(1);
        inRepo.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        inRepo.setPreset(Boolean.TRUE);

        when(mockRepo.findAnyMatchingUniqueConstraints(anyInt(), anyString()))
                .thenReturn(Collections.singletonList(inRepo));

        when(mockRepo.findBySymbol(anyString()))
                .thenReturn(Optional.of(inRepo));

        // Attempt to update an existing ChordType that is a preset
        TEST_CHORD_TYPE = service.findBySymbol("maj").get();
        TEST_CHORD_TYPE.setSymbol("TEST");

        service.save(TEST_CHORD_TYPE);
    }

    @Test
    public void save_attemptToCreateNewPreset() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("Cannot add new preset chord types.");

        when(mockRepo.findAnyMatchingUniqueConstraints(anyInt(), anyString()))
                .thenReturn(Collections.emptyList());

        when(mockRepo.findBySymbol(anyString()))
                .thenReturn(Optional.empty());

        TEST_CHORD_TYPE.setPreset(Boolean.TRUE);

        service.save(TEST_CHORD_TYPE);
    }

    @Test
    public void save_uniqueConstraintViolation_symbol_multipleEntities() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("Another chord type is already defined with this symbol: SYMBOL 2\r\n" +
                "Please choose a different symbol for one of them.");

        ChordType inRepo1 = new ChordType();
        inRepo1.setId(1);
        inRepo1.setSymbol("SYMBOL 1");
        inRepo1.setRnSymbol("TEST");
        inRepo1.setRnCapital(Boolean.TRUE);
        inRepo1.setRnPrecedence(1);
        inRepo1.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        inRepo1.setPreset(Boolean.FALSE);

        ChordType inRepo2 = new ChordType();
        inRepo2.setId(2);
        inRepo2.setSymbol("SYMBOL 2");
        inRepo2.setRnSymbol("TEST");
        inRepo2.setRnCapital(Boolean.TRUE);
        inRepo2.setRnPrecedence(1);
        inRepo2.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        inRepo2.setPreset(Boolean.FALSE);

        when(mockRepo.findAnyMatchingUniqueConstraints(anyInt(), anyString()))
                .thenReturn(Arrays.asList(inRepo1, inRepo2));

        TEST_CHORD_TYPE.setId(1);
        TEST_CHORD_TYPE.setSymbol("SYMBOL 2");

        service.save(TEST_CHORD_TYPE);
    }

    @Test
    public void save_success() {

        when(mockRepo.findAnyMatchingUniqueConstraints(anyInt(), anyString()))
                .thenReturn(Collections.emptyList());

        when(mockRepo.findBySymbol(anyString()))
                .thenReturn(Optional.empty());

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
    public void delete_recordWithIdHasDifferentData() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("Cannot delete chord type #1 (DIFFERENT SYMBOL): entity data does not match record data.");

        ChordType inRepo = new ChordType();
        inRepo.setId(1);
        inRepo.setSymbol("SYMBOL");
        inRepo.setRnSymbol("TEST");
        inRepo.setRnCapital(Boolean.TRUE);
        inRepo.setRnPrecedence(1);
        inRepo.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        inRepo.setPreset(Boolean.FALSE);

        when(mockRepo.findById(anyInt())).thenReturn(Optional.of(inRepo));

        TEST_CHORD_TYPE.setId(1);
        TEST_CHORD_TYPE.setSymbol("DIFFERENT SYMBOL");
        TEST_CHORD_TYPE.setRnSymbol("TEST");
        TEST_CHORD_TYPE.setRnCapital(Boolean.TRUE);
        TEST_CHORD_TYPE.setRnPrecedence(1);
        TEST_CHORD_TYPE.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        TEST_CHORD_TYPE.setPreset(Boolean.FALSE);

        service.delete(TEST_CHORD_TYPE);

        verify(mockRepo, atLeast(1)).findById(anyInt());
    }

    @Test
    public void delete_recordIsPreset() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("Cannot delete preset chord types.");

        TEST_CHORD_TYPE.setPreset(Boolean.TRUE);

        when(mockRepo.findById(anyInt())).thenReturn(Optional.of(TEST_CHORD_TYPE));

        service.delete(TEST_CHORD_TYPE);

        verify(mockRepo, atLeast(1)).findById(anyInt());
    }

    @Test
    public void delete_success() {

        when(mockRepo.findById(anyInt())).thenReturn(Optional.of(TEST_CHORD_TYPE));

        service.delete(TEST_CHORD_TYPE);

        verify(mockRepo, atLeast(1)).findById(anyInt());
    }

}
