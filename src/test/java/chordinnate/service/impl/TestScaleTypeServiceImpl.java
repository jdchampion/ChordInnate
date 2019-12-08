package chordinnate.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import chordinnate.entity.ScaleType;
import chordinnate.exception.ChordInnateException;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.repository.ScaleTypeRepository;
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
@PrepareForTest({ScaleTypeRepository.class})
public class TestScaleTypeServiceImpl {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private static ScaleTypeServiceImpl service;
    private static ScaleTypeRepository mockRepo;
    private static final ScaleType SUCCESS = new ScaleType();

    @BeforeClass
    public static void init() {
        mockRepo = mock(ScaleTypeRepository.class);
        when(mockRepo.save(any(ScaleType.class))).thenReturn(SUCCESS);
        doNothing().when(mockRepo).delete(any(ScaleType.class));

        service = new ScaleTypeServiceImpl(mockRepo);
    }



    @Test
    public void save_nullParam() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("No scale type to save.");
        service.save(null);
    }

    @Test
    public void save_blankParam() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("No scale type to save.");
        service.save(new ScaleType());
    }

    @Test
    public void save_missingRequiredField_name() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following required fields are missing: Name");

        ScaleType s = new ScaleType();
        s.setName(null);
        s.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});

        service.save(s);
    }

    @Test
    public void save_missingRequiredField_intervals() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following required fields are missing: Intervals");

        ScaleType s = new ScaleType();
        s.setName("TEST");
        s.setIntervals(null);

        service.save(s);
    }

    @Test
    public void save_invalidField_name() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following fields are invalid: Name (cannot be blank)");

        ScaleType s = new ScaleType();
        s.setName("");
        s.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});

        service.save(s);
    }

    @Test
    public void save_invalidField_intervals_notEnough() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following fields are invalid: Intervals (must contain at least two intervals)");

        ScaleType s = new ScaleType();
        s.setName("TEST");
        s.setIntervals(new Interval[]{});
        s.setPreset(Boolean.FALSE);

        service.save(s);
    }

    @Test
    public void save_invalidField_intervals_duplicated() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following fields are invalid: Intervals (cannot contain duplicates)");

        ScaleType s = new ScaleType();
        s.setName("TEST");
        s.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.PERFECT_1});
        s.setPreset(Boolean.FALSE);

        service.save(s);
    }

    @Test
    public void save_invalidField_intervals_notIncreasingDecreasing() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following fields are invalid: Intervals (must be increasing or decreasing at each step)");

        ScaleType s = new ScaleType();
        s.setName("TEST");
        s.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_3, Interval.MAJOR_2});
        s.setPreset(Boolean.FALSE);

        service.save(s);
    }

    @Test
    public void save_invalidField_size() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following fields are invalid: Size (must match the number of intervals)");

        ScaleType s = new ScaleType();
        s.setName("TEST");
        s.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        s.setPreset(Boolean.FALSE);
        s.setSize(0);

        service.save(s);
    }

    @Test
    public void save_overwriteExistingPreset() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("Cannot overwrite a preset scale type.");

        ScaleType inRepo = new ScaleType();
        inRepo.setId(1);
        inRepo.setName("maj");
        inRepo.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        inRepo.setPreset(Boolean.TRUE);

        when(mockRepo.findAnyMatchingUniqueConstraints(anyInt(), anyString()))
                .thenReturn(Collections.singletonList(inRepo));

        when(mockRepo.findByName(anyString()))
                .thenReturn(Optional.of(inRepo));

        // Attempt to update an existing ScaleType that is a preset
        ScaleType s = service.findByName("maj").get();
        s.setName("TEST");

        service.save(s);
    }

    @Test
    public void save_attemptToCreateNewPreset() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("Cannot add new preset scale types.");

        when(mockRepo.findAnyMatchingUniqueConstraints(anyInt(), anyString()))
                .thenReturn(Collections.emptyList());

        when(mockRepo.findByName(anyString()))
                .thenReturn(Optional.empty());

        ScaleType s = new ScaleType();
        s.setName("TEST");
        s.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        s.setPreset(Boolean.TRUE);

        service.save(s);
    }

    @Test
    public void save_uniqueConstraintViolation_name_multipleEntities() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("Another scale type is already defined with this name: TEST\r\n" +
                "Please choose a different name for one of them.");

        ScaleType inRepo1 = new ScaleType();
        inRepo1.setId(1);
        inRepo1.setName("maj");
        inRepo1.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        inRepo1.setPreset(Boolean.FALSE);

        ScaleType inRepo2 = new ScaleType();
        inRepo2.setId(2);
        inRepo2.setName("TEST");
        inRepo2.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        inRepo2.setPreset(Boolean.FALSE);

        when(mockRepo.findAnyMatchingUniqueConstraints(anyInt(), anyString()))
                .thenReturn(Arrays.asList(inRepo1, inRepo2));

        ScaleType s = new ScaleType();
        s.setId(1);
        s.setName("TEST");
        s.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        s.setPreset(Boolean.FALSE);

        service.save(s);
    }

    @Test
    public void save_success() {

        when(mockRepo.findAnyMatchingUniqueConstraints(anyInt(), anyString()))
                .thenReturn(Collections.emptyList());

        when(mockRepo.findByName(anyString()))
                .thenReturn(Optional.empty());

        ScaleType s = new ScaleType();
        s.setName("TEST");
        s.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});

        service.save(s);
    }

    @Test
    public void delete_nullParam() {
        service.delete(null);
    }

    @Test
    public void delete_blankParam() {
        service.delete(new ScaleType());
    }

    @Test
    public void delete_missingId() {
        ScaleType s = new ScaleType();
        s.setName("TEST");
        s.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        s.setPreset(Boolean.FALSE);

        service.delete(s);
    }

    @Test
    public void delete_invalidId() {
        ScaleType s = new ScaleType();
        s.setId(1);
        s.setName("TEST");
        s.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        s.setPreset(Boolean.FALSE);

        when(mockRepo.findById(anyInt())).thenReturn(Optional.empty());

        service.delete(s);

        verify(mockRepo, times(1)).findById(anyInt());
    }

    @Test
    public void delete_recordWithIdHasDifferentData() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("Cannot delete scale type #1 (TEST): entity data does not match record data.");

        ScaleType inRepo = new ScaleType();
        inRepo.setId(1);
        inRepo.setName("DIFFERENT");
        inRepo.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        inRepo.setPreset(Boolean.FALSE);

        when(mockRepo.findById(anyInt())).thenReturn(Optional.of(inRepo));

        ScaleType s = new ScaleType();
        s.setId(1);
        s.setName("TEST");
        s.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        s.setPreset(Boolean.FALSE);

        service.delete(s);

        verify(mockRepo, times(1)).findById(anyInt());
    }

    @Test
    public void delete_recordIsPreset() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("Cannot delete preset scale types.");

        ScaleType s = new ScaleType();
        s.setId(1);
        s.setName("TEST");
        s.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        s.setPreset(Boolean.TRUE);

        when(mockRepo.findById(anyInt())).thenReturn(Optional.of(s));

        service.delete(s);

        verify(mockRepo, times(1)).findById(anyInt());
    }

    @Test
    public void delete_success() {
        ScaleType s = new ScaleType();
        s.setId(1);
        s.setName("TEST");
        s.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        s.setPreset(Boolean.FALSE);

        when(mockRepo.findById(anyInt())).thenReturn(Optional.of(s));

        service.delete(s);

        verify(mockRepo, atLeast(1)).findById(anyInt());
    }
}
