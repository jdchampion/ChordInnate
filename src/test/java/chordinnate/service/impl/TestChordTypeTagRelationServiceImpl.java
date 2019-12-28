package chordinnate.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import chordinnate.entity.ChordType;
import chordinnate.entity.ChordTypeTagRelation;
import chordinnate.entity.Tag;
import chordinnate.exception.ChordInnateConstraintViolation;
import chordinnate.exception.ChordInnateException;
import chordinnate.model.musictheory.pitch.interval.Interval;
import chordinnate.repository.ChordTypeTagRelationRepository;
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
@PrepareForTest({ChordTypeTagRelationRepository.class})
public class TestChordTypeTagRelationServiceImpl {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private static ChordTypeTagRelationServiceImpl service;
    private static ChordTypeTagRelationRepository mockRepo;
    private static ChordTypeTagRelation TEST_RELATION;

    @BeforeClass
    public static void init() {
        mockRepo = mock(ChordTypeTagRelationRepository.class);
        when(mockRepo.save(any(ChordTypeTagRelation.class))).thenReturn(TEST_RELATION);
        doNothing().when(mockRepo).delete(any(ChordTypeTagRelation.class));

        service = new ChordTypeTagRelationServiceImpl(mockRepo);
    }

    @Before
    public void setup() {
        ChordType type = new ChordType();
        type.setSymbol("TYPE SYMBOL");
        type.setRnSymbol("RN SYMBOL");
        type.setRnCapital(Boolean.TRUE);
        type.setRnPrecedence(1);
        type.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        type.setSize(2);
        type.setPreset(Boolean.FALSE);

        Tag tag = new Tag();
        tag.setName("TAG NAME");

        TEST_RELATION = new ChordTypeTagRelation(type, tag);
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
        service.save(new ChordTypeTagRelation());
    }

    @Test
    public void save_missingRequiredField_chordType() {
        expectedException.expect(ChordInnateConstraintViolation.class);
        TEST_RELATION.setMatchingChordType(null);
        service.save(TEST_RELATION);
    }

    @Test
    public void save_missingRequiredField_chordType_subfields() {
        expectedException.expect(ChordInnateConstraintViolation.class);
        TEST_RELATION.setMatchingChordType(new ChordType());
        service.save(TEST_RELATION);
    }

    @Test
    public void save_missingRequiredField_chordTypeTag() {
        expectedException.expect(ChordInnateException.class);
        TEST_RELATION.setMatchingTag(null);
        service.save(TEST_RELATION);
    }

    @Test
    public void save_missingRequiredField_chordTypeTag_subfields() {
        expectedException.expect(ChordInnateException.class);
        TEST_RELATION.setMatchingTag(new Tag());
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
        service.delete(new ChordTypeTagRelation());
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

        ChordType type = new ChordType();
        type.setId(1);
        type.setSymbol("TYPE SYMBOL");
        type.setRnSymbol("RN SYMBOL");
        type.setRnCapital(Boolean.TRUE);
        type.setIntervals(new Interval[]{Interval.PERFECT_1, Interval.MAJOR_2});
        type.setPreset(Boolean.FALSE);
        type.setSize(2);

        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("TAG NAME");

        ChordTypeTagRelation inRepo = new ChordTypeTagRelation(type, tag);

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