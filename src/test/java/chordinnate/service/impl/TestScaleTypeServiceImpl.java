package chordinnate.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import chordinnate.entity.ScaleType;
import chordinnate.exception.ChordInnateException;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.repository.ScaleTypeRepository;
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
@PrepareForTest({ScaleTypeRepository.class})
public class TestScaleTypeServiceImpl {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private static ScaleTypeServiceImpl service;
    private static ScaleTypeRepository mockRepo;
    private static ScaleType TEST_SCALE_TYPE;

    @BeforeClass
    public static void init() {
        mockRepo = mock(ScaleTypeRepository.class);
        when(mockRepo.save(any(ScaleType.class))).thenReturn(TEST_SCALE_TYPE);
        doNothing().when(mockRepo).delete(any(ScaleType.class));

        service = new ScaleTypeServiceImpl(mockRepo);
    }

    @Before
    public void setup() {
        TEST_SCALE_TYPE = new ScaleType();
        TEST_SCALE_TYPE.setId(1);
        TEST_SCALE_TYPE.setName("TEST");
        TEST_SCALE_TYPE.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
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

        TEST_SCALE_TYPE.setName(null);

        service.save(TEST_SCALE_TYPE);
    }

    @Test
    public void save_missingRequiredField_intervals() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following required fields are missing: Intervals");

        TEST_SCALE_TYPE.setIntervals(null);

        service.save(TEST_SCALE_TYPE);
    }

    @Test
    public void save_invalidField_name() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following fields are invalid: Name (cannot be blank)");

        TEST_SCALE_TYPE.setName("");

        service.save(TEST_SCALE_TYPE);
    }

    @Test
    public void save_invalidField_intervals_notEnough() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following fields are invalid: Intervals (must contain at least two intervals)");

        TEST_SCALE_TYPE.setIntervals(new Interval[]{});

        service.save(TEST_SCALE_TYPE);
    }

    @Test
    public void save_invalidField_intervals_duplicated() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following fields are invalid: Intervals (cannot contain duplicates)");

        TEST_SCALE_TYPE.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.PERFECT_1});

        service.save(TEST_SCALE_TYPE);
    }

    @Test
    public void save_invalidField_intervals_notIncreasingDecreasing() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following fields are invalid: Intervals (must be increasing or decreasing at each step)");

        TEST_SCALE_TYPE.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_3, Interval.MAJOR_2});

        service.save(TEST_SCALE_TYPE);
    }

    @Test
    public void save_invalidField_size() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following fields are invalid: Size (must match the number of intervals)");

        TEST_SCALE_TYPE.setSize(0);

        service.save(TEST_SCALE_TYPE);
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
        TEST_SCALE_TYPE = service.findByName("maj").get();
        TEST_SCALE_TYPE.setName("TEST");

        service.save(TEST_SCALE_TYPE);
    }

    @Test
    public void save_attemptToCreateNewPreset() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("Cannot add new preset scale types.");

        when(mockRepo.findAnyMatchingUniqueConstraints(anyInt(), anyString()))
                .thenReturn(Collections.emptyList());

        when(mockRepo.findByName(anyString()))
                .thenReturn(Optional.empty());

        TEST_SCALE_TYPE.setPreset(Boolean.TRUE);

        service.save(TEST_SCALE_TYPE);
    }

    @Test
    public void save_uniqueConstraintViolation_name_multipleEntities() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("Another scale type is already defined with this name: NAME 2\r\n" +
                "Please choose a different name for one of them.");

        ScaleType inRepo1 = new ScaleType();
        inRepo1.setId(1);
        inRepo1.setName("NAME 1");
        inRepo1.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        inRepo1.setPreset(Boolean.FALSE);

        ScaleType inRepo2 = new ScaleType();
        inRepo2.setId(2);
        inRepo2.setName("NAME 2");
        inRepo2.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        inRepo2.setPreset(Boolean.FALSE);

        when(mockRepo.findAnyMatchingUniqueConstraints(anyInt(), anyString()))
                .thenReturn(Arrays.asList(inRepo1, inRepo2));

        TEST_SCALE_TYPE.setId(1);
        TEST_SCALE_TYPE.setName("NAME 2");

        service.save(TEST_SCALE_TYPE);
    }

    @Test
    public void save_success() {

        when(mockRepo.findAnyMatchingUniqueConstraints(anyInt(), anyString()))
                .thenReturn(Collections.emptyList());

        when(mockRepo.findByName(anyString()))
                .thenReturn(Optional.empty());

        service.save(TEST_SCALE_TYPE);
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

        TEST_SCALE_TYPE.setId(null);

        service.delete(TEST_SCALE_TYPE);
    }

    @Test
    public void delete_invalidId() {
        when(mockRepo.findById(anyInt())).thenReturn(Optional.empty());

        service.delete(TEST_SCALE_TYPE);

        verify(mockRepo, atLeast(1)).findById(anyInt());
    }

    @Test
    public void delete_recordWithIdHasDifferentData() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("Cannot delete scale type #1 (DIFFERENT NAME): entity data does not match record data.");

        ScaleType inRepo = new ScaleType();
        inRepo.setId(1);
        inRepo.setName("NAME");
        inRepo.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        inRepo.setPreset(Boolean.FALSE);

        when(mockRepo.findById(anyInt())).thenReturn(Optional.of(inRepo));

        TEST_SCALE_TYPE.setId(1);
        TEST_SCALE_TYPE.setName("DIFFERENT NAME");
        TEST_SCALE_TYPE.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        TEST_SCALE_TYPE.setPreset(Boolean.FALSE);

        service.delete(TEST_SCALE_TYPE);

        verify(mockRepo, atLeast(1)).findById(anyInt());
    }

    @Test
    public void delete_recordIsPreset() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("Cannot delete preset scale types.");

        TEST_SCALE_TYPE.setPreset(Boolean.TRUE);

        when(mockRepo.findById(anyInt())).thenReturn(Optional.of(TEST_SCALE_TYPE));

        service.delete(TEST_SCALE_TYPE);

        verify(mockRepo, atLeast(1)).findById(anyInt());
    }

    @Test
    public void delete_success() {
        when(mockRepo.findById(anyInt())).thenReturn(Optional.of(TEST_SCALE_TYPE));

        service.delete(TEST_SCALE_TYPE);

        verify(mockRepo, atLeast(1)).findById(anyInt());
    }
}
