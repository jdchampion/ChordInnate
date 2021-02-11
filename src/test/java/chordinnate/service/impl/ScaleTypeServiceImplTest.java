package chordinnate.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import chordinnate.entity.ScaleType;
import chordinnate.exception.ChordInnateConstraintViolation;
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

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Optional;

@Slf4j
@RunWith(PowerMockRunner.class)
@PrepareForTest({ScaleTypeRepository.class})
public class ScaleTypeServiceImplTest {

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

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        service = new ScaleTypeServiceImpl(mockRepo, validator);
    }

    @Before
    public void setup() {
        // example of a completely valid scale type
        TEST_SCALE_TYPE = new ScaleType();
        TEST_SCALE_TYPE.setName("TEST");
        TEST_SCALE_TYPE.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        TEST_SCALE_TYPE.setSize(2);
        TEST_SCALE_TYPE.setPreset(Boolean.FALSE);
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
        service.save(new ScaleType());
    }

    @Test
    public void save_missingRequiredField_name() {
        expectedException.expect(ChordInnateConstraintViolation.class);
        expectedException.expectMessage("Name must not be blank");

        TEST_SCALE_TYPE.setName(null);

        service.save(TEST_SCALE_TYPE);
    }

    @Test
    public void save_missingRequiredField_intervals() {
        expectedException.expect(ChordInnateConstraintViolation.class);
        expectedException.expectMessage("Intervals must not be null");

        TEST_SCALE_TYPE.setIntervals(null);

        service.save(TEST_SCALE_TYPE);
    }

    @Test
    public void save_invalidField_name() {
        expectedException.expect(ChordInnateConstraintViolation.class);
        expectedException.expectMessage("Name must not be blank");

        TEST_SCALE_TYPE.setName("");

        service.save(TEST_SCALE_TYPE);
    }

    @Test
    public void save_invalidField_intervals_notEnough() {
        expectedException.expect(ChordInnateConstraintViolation.class);
        expectedException.expectMessage("Must contain at least 2 intervals");

        TEST_SCALE_TYPE.setIntervals(new Interval[]{});

        service.save(TEST_SCALE_TYPE);
    }

    @Test
    public void save_invalidField_intervals_duplicated() {
        expectedException.expect(ChordInnateConstraintViolation.class);
        expectedException.expectMessage("Intervals must be increasing or decreasing at each step");

        TEST_SCALE_TYPE.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.PERFECT_1});

        service.save(TEST_SCALE_TYPE);
    }

    @Test
    public void save_invalidField_intervals_notIncreasingDecreasing() {
        expectedException.expect(ChordInnateConstraintViolation.class);
        expectedException.expectMessage("Intervals must be increasing or decreasing at each step");

        TEST_SCALE_TYPE.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_3, Interval.MAJOR_2});

        service.save(TEST_SCALE_TYPE);
    }

    @Test
    public void save_invalidField_size_notMatching() {
        expectedException.expect(ChordInnateConstraintViolation.class);
        expectedException.expectMessage("Size must match the number of intervals");

        TEST_SCALE_TYPE.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        TEST_SCALE_TYPE.setSize(1);

        service.save(TEST_SCALE_TYPE);
    }

    @Test
    public void save_invalidField_size_notPositive() {
        expectedException.expect(ChordInnateConstraintViolation.class);
        expectedException.expectMessage("Size must be positive (was: 0)");

        TEST_SCALE_TYPE.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        TEST_SCALE_TYPE.setSize(0);

        service.save(TEST_SCALE_TYPE);
    }

    @Test
    public void save_overwriteExistingPreset() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("Overwriting preset scale types is not allowed");

        ScaleType inRepo = new ScaleType();
        inRepo.setId(1);
        inRepo.setName("NAME 1");
        inRepo.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        inRepo.setPreset(Boolean.TRUE);
        inRepo.setSize(2);

        when(mockRepo.findById(anyInt())).thenReturn(Optional.of(inRepo));

        // Attempt to update an existing ScaleType that is a preset
        TEST_SCALE_TYPE = service.findById(1).get();
        TEST_SCALE_TYPE.setName("NAME 2");

        service.save(TEST_SCALE_TYPE);
    }

    @Test
    public void save_attemptToCreateNewPreset() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("Creating new preset scale types is not allowed");

        TEST_SCALE_TYPE.setPreset(Boolean.TRUE);

        service.save(TEST_SCALE_TYPE);
    }

    @Test
    public void save_success() {
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
        TEST_SCALE_TYPE.setId(1);

        when(mockRepo.findById(anyInt())).thenReturn(Optional.empty());

        service.delete(TEST_SCALE_TYPE);

        verify(mockRepo, atLeast(1)).findById(anyInt());
    }

    @Test
    public void delete_recordWithIdHasDifferentData() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("Cannot delete scale type #1 (DIFFERENT NAME): entity data does not match record data");

        ScaleType inRepo = new ScaleType();
        inRepo.setId(1);
        inRepo.setName("NAME");
        inRepo.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        inRepo.setPreset(Boolean.FALSE);
        inRepo.setSize(2);

        when(mockRepo.findById(anyInt())).thenReturn(Optional.of(inRepo));

        TEST_SCALE_TYPE.setId(1);
        TEST_SCALE_TYPE.setName("DIFFERENT NAME");
        TEST_SCALE_TYPE.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        TEST_SCALE_TYPE.setPreset(Boolean.FALSE);
        TEST_SCALE_TYPE.setSize(2);

        service.delete(TEST_SCALE_TYPE);

        verify(mockRepo, atLeast(1)).findById(anyInt());
    }

    @Test
    public void delete_recordIsPreset() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("Cannot delete preset scale types");

        TEST_SCALE_TYPE.setId(1);
        TEST_SCALE_TYPE.setPreset(Boolean.TRUE);

        when(mockRepo.findById(anyInt())).thenReturn(Optional.of(TEST_SCALE_TYPE));

        service.delete(TEST_SCALE_TYPE);

        verify(mockRepo, atLeast(1)).findById(anyInt());
    }

    @Test
    public void delete_success() {
        TEST_SCALE_TYPE.setId(1);

        when(mockRepo.findById(anyInt())).thenReturn(Optional.of(TEST_SCALE_TYPE));

        service.delete(TEST_SCALE_TYPE);

        verify(mockRepo, atLeast(1)).findById(anyInt());
    }
}
