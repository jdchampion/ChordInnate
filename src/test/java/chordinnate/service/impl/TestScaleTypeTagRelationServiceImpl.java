package chordinnate.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import chordinnate.entity.ScaleTypeTagRelation;
import chordinnate.exception.ChordInnateException;
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
        TEST_RELATION = new ScaleTypeTagRelation();
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
        service.save(new ScaleTypeTagRelation());
    }

    @Test
    public void save_missingRequiredField_scaleType() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following required fields are missing: Scale Type");
    }

    @Test
    public void save_missingRequiredField_scaleType_subfields() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following required fields are missing: Scale Type");
    }

    @Test
    public void save_missingRequiredField_scaleTypeTag() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following required fields are missing: Scale Type Tag");
    }

    @Test
    public void save_missingRequiredField_scaleTypeTag_subfields() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following required fields are missing: Scale Type Tag");
    }

    @Test
    public void save_invalidField_scaleType() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following required fields are invalid: Scale Type");
    }

    @Test
    public void save_invalidField_scaleType_subfields() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following required fields are invalid: Scale Type");
    }

    @Test
    public void save_invalidField_scaleTypeTag() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following required fields are invalid: Scale Type Tag");
    }

    @Test
    public void save_invalidField_scaleTypeTag_subfields() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("The following required fields are invalid: Scale Type Tag");
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
        service.delete(new ScaleTypeTagRelation());
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
