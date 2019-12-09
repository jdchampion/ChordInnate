package chordinnate.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import chordinnate.entity.ChordTypeTagRelation;
import chordinnate.exception.ChordInnateException;
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
        TEST_RELATION = new ChordTypeTagRelation();
    }


    @Test
    public void save_nullParam() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("No tag relation to save.");
        service.save(null);
    }

    @Test
    public void save_blankParam() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("No tag relation to save.");
        service.save(new ChordTypeTagRelation());
    }

    @Test
    public void save_missingRequiredField_scaleType() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following required fields are missing: Chord Type");
    }

    @Test
    public void save_missingRequiredField_scaleType_subfields() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following required fields are missing: Chord Type");
    }

    @Test
    public void save_missingRequiredField_scaleTypeTag() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following required fields are missing: Chord Type Tag");
    }

    @Test
    public void save_missingRequiredField_scaleTypeTag_subfields() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following required fields are missing: Chord Type Tag");
    }

    @Test
    public void save_invalidField_scaleType() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following required fields are invalid: Chord Type");
    }

    @Test
    public void save_invalidField_scaleType_subfields() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following required fields are invalid: Chord Type");
    }

    @Test
    public void save_invalidField_scaleTypeTag() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following required fields are invalid: Chord Type Tag");
    }

    @Test
    public void save_invalidField_scaleTypeTag_subfields() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following required fields are invalid: Chord Type Tag");
    }

    @Test
    public void save_success() {

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

    }

    @Test
    public void delete_invalidId() {

    }

    @Test
    public void delete_recordWithIdHasDifferentData() {

    }

    @Test
    public void delete_success() {

    }

}