package chordinnate.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import chordinnate.entity.ScaleType;
import chordinnate.entity.ScaleTypeTag;
import chordinnate.entity.ScaleTypeTagRelation;
import chordinnate.exception.ChordInnateConstraintViolation;
import chordinnate.exception.ChordInnateException;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.repository.ScaleTypeTagRelationRepository;
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
@PrepareForTest({ScaleTypeTagRelationRepository.class})
public class TestScaleTypeTagRelationServiceImpl {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private static ScaleTypeTagRelationServiceImpl service;
    private static ScaleTypeTagRelationRepository mockRepo;
    private static ScaleTypeTagRelation TEST_RELATION;

    @BeforeClass
    public static void init() {
        mockRepo = mock(ScaleTypeTagRelationRepository.class);
        when(mockRepo.save(any(ScaleTypeTagRelation.class))).thenReturn(TEST_RELATION);
        doNothing().when(mockRepo).delete(any(ScaleTypeTagRelation.class));

        service = new ScaleTypeTagRelationServiceImpl(mockRepo);
    }

    @Before
    public void setup() {
        ScaleType scaleType = new ScaleType();
        scaleType.setName("SCALE NAME");
        scaleType.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        scaleType.setSize(2);
        scaleType.setPreset(Boolean.FALSE);

        ScaleTypeTag tag = new ScaleTypeTag();
        tag.setName("TAG NAME");

        TEST_RELATION = new ScaleTypeTagRelation(scaleType, tag);
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
        service.save(new ScaleTypeTagRelation());
    }

    @Test
    public void save_missingRequiredField_scaleType() {
        expectedException.expect(ChordInnateConstraintViolation.class);
        TEST_RELATION.setMatchingScaleType(null);
        service.save(TEST_RELATION);
    }

    @Test
    public void save_missingRequiredField_scaleType_subfields() {
        expectedException.expect(ChordInnateConstraintViolation.class);
        TEST_RELATION.setMatchingScaleType(new ScaleType());
        service.save(TEST_RELATION);
    }

    @Test
    public void save_missingRequiredField_scaleTypeTag() {
        expectedException.expect(ChordInnateException.class);
        TEST_RELATION.setMatchingTag(null);
        service.save(TEST_RELATION);
    }

    @Test
    public void save_missingRequiredField_scaleTypeTag_subfields() {
        expectedException.expect(ChordInnateException.class);
        TEST_RELATION.setMatchingTag(new ScaleTypeTag());
        service.save(TEST_RELATION);
    }

    @Test
    public void save_success() {
        service.save(TEST_RELATION);
    }

    @Test
    public void delete_nullParam() {
        service.delete(null);
    }

    @Test
    public void delete_blankParam() {
        service.delete(new ScaleTypeTagRelation());
    }

    @Test
    public void delete_missingId() {
        TEST_RELATION.setId(null);
        service.delete(TEST_RELATION);
    }

    @Test
    public void delete_invalidId() {
        TEST_RELATION.setId(1);
        when(mockRepo.findById(anyInt())).thenReturn(Optional.empty());
        service.delete(TEST_RELATION);
        verify(mockRepo, atLeast(1)).findById(anyInt());
    }

    @Test
    public void delete_recordWithIdHasDifferentData() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("entity data does not match record data");

        ScaleType type = new ScaleType();
        type.setId(1);
        type.setName("TYPE NAME");
        type.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        type.setPreset(Boolean.FALSE);
        type.setSize(2);

        ScaleTypeTag tag = new ScaleTypeTag();
        tag.setId(1);
        tag.setName("TAG NAME");

        ScaleTypeTagRelation inRepo = new ScaleTypeTagRelation(type, tag);

        TEST_RELATION.setId(1);
        when(mockRepo.findById(anyInt())).thenReturn(Optional.of(inRepo));
        service.delete(TEST_RELATION);
        verify(mockRepo, atLeast(1)).findById(anyInt());
    }

    @Test
    public void delete_success() {
        TEST_RELATION.setId(1);
        when(mockRepo.findById(anyInt())).thenReturn(Optional.of(TEST_RELATION));
        service.delete(TEST_RELATION);
        verify(mockRepo, atLeast(1)).findById(anyInt());
    }

}