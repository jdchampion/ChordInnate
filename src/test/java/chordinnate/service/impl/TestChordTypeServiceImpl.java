package chordinnate.service.impl;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import chordinnate.entity.ChordType;
import chordinnate.exception.ChordInnateException;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.repository.ChordTypeRepository;
import lombok.extern.slf4j.Slf4j;
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
    private static final ChordType SUCCESS = new ChordType();

    @BeforeClass
    public static void init() {
        mockRepo = mock(ChordTypeRepository.class);
        when(mockRepo.save(any(ChordType.class))).thenReturn(SUCCESS);
        doNothing().when(mockRepo).delete(any(ChordType.class));

        service = new ChordTypeServiceImpl(mockRepo);
    }

    @Test
    public void findBySymbol() {

    }

    @Test
    public void findByIntervals() {

    }

    @Test
    public void findAllByIntervals() {

    }

    @Test
    public void findByRomanNumeralCriteria() {

    }

    @Test
    public void findAllBySize() {

    }

    @Test
    public void findAllByTags() {

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

        ChordType c = new ChordType();
        c.setSymbol(null);
        c.setRnSymbol("TEST");
        c.setRnCapital(Boolean.TRUE);
        c.setRnPrecedence(1);
        c.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});

        service.save(c);
    }

    @Test
    public void save_missingRequiredField_rnSymbol() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following required fields are missing: Roman Numeral Symbol");

        ChordType c = new ChordType();
        c.setSymbol("TEST");
        c.setRnSymbol(null);
        c.setRnCapital(Boolean.TRUE);
        c.setRnPrecedence(1);
        c.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});

        service.save(c);
    }

    @Test
    public void save_missingRequiredField_rnCapital() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following required fields are missing: Roman Numeral Capital");

        ChordType c = new ChordType();
        c.setSymbol("TEST");
        c.setRnSymbol("TEST");
        c.setRnCapital(null);
        c.setRnPrecedence(1);
        c.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});

        service.save(c);
    }

    @Test
    public void save_missingRequiredField_rnPrecedence() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following required fields are missing: Roman Numeral Precedence");

        ChordType c = new ChordType();
        c.setSymbol("TEST");
        c.setRnSymbol("TEST");
        c.setRnCapital(Boolean.TRUE);
        c.setRnPrecedence(null);
        c.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});

        service.save(c);
    }

    @Test
    public void save_missingRequiredField_intervals() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following required fields are missing: Intervals");

        ChordType c = new ChordType();
        c.setSymbol("TEST");
        c.setRnSymbol("TEST");
        c.setRnCapital(Boolean.TRUE);
        c.setRnPrecedence(1);
        c.setIntervals(null);

        service.save(c);
    }

    @Test
    public void save_invalidField_symbol() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following fields are invalid: Symbol (cannot be blank)");

        ChordType c = new ChordType();
        c.setSymbol("");
        c.setRnSymbol("TEST");
        c.setRnCapital(Boolean.TRUE);
        c.setRnPrecedence(1);
        c.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});

        service.save(c);
    }

    @Test
    public void save_invalidField_rnPrecedence() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following fields are invalid: Roman Numeral Precedence (must be > 0)");

        ChordType c = new ChordType();
        c.setSymbol("TEST");
        c.setRnSymbol("TEST");
        c.setRnCapital(Boolean.TRUE);
        c.setRnPrecedence(0);
        c.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        c.setPreset(Boolean.FALSE);

        service.save(c);
    }

    @Test
    public void save_invalidField_intervals_notEnough() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following fields are invalid: Intervals (must contain at least two intervals)");

        ChordType c = new ChordType();
        c.setSymbol("TEST");
        c.setRnSymbol("TEST");
        c.setRnCapital(Boolean.TRUE);
        c.setRnPrecedence(1);
        c.setIntervals(new Interval[]{});
        c.setPreset(Boolean.FALSE);

        service.save(c);
    }

    @Test
    public void save_invalidField_intervals_duplicated() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following fields are invalid: Intervals (cannot contain duplicates)");

        ChordType c = new ChordType();
        c.setSymbol("TEST");
        c.setRnSymbol("TEST");
        c.setRnCapital(Boolean.TRUE);
        c.setRnPrecedence(1);
        c.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.PERFECT_1});
        c.setPreset(Boolean.FALSE);

        service.save(c);
    }

    @Test
    public void save_invalidField_intervals_notIncreasingDecreasing() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following fields are invalid: Intervals (must be increasing or decreasing at each step)");

        ChordType c = new ChordType();
        c.setSymbol("TEST");
        c.setRnSymbol("TEST");
        c.setRnCapital(Boolean.TRUE);
        c.setRnPrecedence(1);
        c.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_3, Interval.MAJOR_2});
        c.setPreset(Boolean.FALSE);

        service.save(c);
    }

    @Test
    public void save_invalidField_size() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following fields are invalid: Size (must match the number of intervals)");

        ChordType c = new ChordType();
        c.setSymbol("TEST");
        c.setRnSymbol("TEST");
        c.setRnCapital(Boolean.TRUE);
        c.setRnPrecedence(1);
        c.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        c.setPreset(Boolean.FALSE);
        c.setSize(0);

        service.save(c);
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
        ChordType c = service.findBySymbol("maj").get();
        c.setSymbol("TEST");

        service.save(c);
    }

    @Test
    public void save_attemptToCreateNewPreset() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("Cannot add new preset chord types.");

        when(mockRepo.findAnyMatchingUniqueConstraints(anyInt(), anyString()))
                .thenReturn(Collections.emptyList());

        when(mockRepo.findBySymbol(anyString()))
                .thenReturn(Optional.empty());

        ChordType c = new ChordType();
        c.setSymbol("TEST");
        c.setRnSymbol("TEST");
        c.setRnCapital(Boolean.TRUE);
        c.setRnPrecedence(1);
        c.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        c.setPreset(Boolean.TRUE);

        service.save(c);
    }

    @Test
    public void save_uniqueConstraintViolation_symbol_multipleEntities() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("Another chord type is already defined with this symbol: TEST\r\n" +
                "Please choose a different symbol for one of them.");

        ChordType inRepo1 = new ChordType();
        inRepo1.setId(1);
        inRepo1.setSymbol("maj");
        inRepo1.setRnSymbol("TEST");
        inRepo1.setRnCapital(Boolean.TRUE);
        inRepo1.setRnPrecedence(1);
        inRepo1.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        inRepo1.setPreset(Boolean.FALSE);

        ChordType inRepo2 = new ChordType();
        inRepo2.setId(2);
        inRepo2.setSymbol("TEST");
        inRepo2.setRnSymbol("TEST");
        inRepo2.setRnCapital(Boolean.TRUE);
        inRepo2.setRnPrecedence(1);
        inRepo2.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        inRepo2.setPreset(Boolean.FALSE);

        when(mockRepo.findAnyMatchingUniqueConstraints(anyInt(), anyString()))
                .thenReturn(Arrays.asList(inRepo1, inRepo2));

        ChordType c = new ChordType();
        c.setId(1);
        c.setSymbol("TEST");
        c.setRnSymbol("TEST");
        c.setRnCapital(Boolean.TRUE);
        c.setRnPrecedence(1);
        c.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        c.setPreset(Boolean.FALSE);

        service.save(c);
    }

    @Test
    public void save_success() {

        when(mockRepo.findAnyMatchingUniqueConstraints(anyInt(), anyString()))
                .thenReturn(Collections.emptyList());

        when(mockRepo.findBySymbol(anyString()))
                .thenReturn(Optional.empty());

        ChordType c = new ChordType();
        c.setSymbol("TEST");
        c.setRnSymbol("TEST");
        c.setRnCapital(Boolean.TRUE);
        c.setRnPrecedence(1);
        c.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});

        service.save(c);
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
        ChordType c = new ChordType();
        c.setSymbol("TEST");
        c.setRnSymbol("TEST");
        c.setRnCapital(Boolean.TRUE);
        c.setRnPrecedence(1);
        c.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        c.setPreset(Boolean.FALSE);

        service.delete(c);
    }

    @Test
    public void delete_invalidId() {
        ChordType c = new ChordType();
        c.setId(1);
        c.setSymbol("TEST");
        c.setRnSymbol("TEST");
        c.setRnCapital(Boolean.TRUE);
        c.setRnPrecedence(1);
        c.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        c.setPreset(Boolean.FALSE);

        when(mockRepo.findById(anyInt())).thenReturn(Optional.empty());

        service.delete(c);

        verify(mockRepo, times(1)).findById(anyInt());
    }

    @Test
    public void delete_recordWithIdHasDifferentData() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("Cannot delete chord type #1 (TEST): entity data does not match record data.");

        ChordType inRepo = new ChordType();
        inRepo.setId(1);
        inRepo.setSymbol("DIFFERENT");
        inRepo.setRnSymbol("TEST");
        inRepo.setRnCapital(Boolean.TRUE);
        inRepo.setRnPrecedence(1);
        inRepo.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        inRepo.setPreset(Boolean.FALSE);

        when(mockRepo.findById(anyInt())).thenReturn(Optional.of(inRepo));

        ChordType c = new ChordType();
        c.setId(1);
        c.setSymbol("TEST");
        c.setRnSymbol("TEST");
        c.setRnCapital(Boolean.TRUE);
        c.setRnPrecedence(1);
        c.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        c.setPreset(Boolean.FALSE);

        service.delete(c);

        verify(mockRepo, times(1)).findById(anyInt());
    }

    @Test
    public void delete_recordIsPreset() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("Cannot delete preset chord types.");

        ChordType c = new ChordType();
        c.setId(1);
        c.setSymbol("TEST");
        c.setRnSymbol("TEST");
        c.setRnCapital(Boolean.TRUE);
        c.setRnPrecedence(1);
        c.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        c.setPreset(Boolean.TRUE);

        when(mockRepo.findById(anyInt())).thenReturn(Optional.of(c));

        service.delete(c);

        verify(mockRepo, times(1)).findById(anyInt());
    }

    @Test
    public void delete_success() {
        ChordType c = new ChordType();
        c.setId(1);
        c.setSymbol("TEST");
        c.setRnSymbol("TEST");
        c.setRnCapital(Boolean.TRUE);
        c.setRnPrecedence(1);
        c.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        c.setPreset(Boolean.FALSE);

        when(mockRepo.findById(anyInt())).thenReturn(Optional.of(c));

        service.delete(c);

        verify(mockRepo, atLeast(1)).findById(anyInt());
    }

}
