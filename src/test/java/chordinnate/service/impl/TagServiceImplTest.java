package chordinnate.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import chordinnate.entity.Tag;
import chordinnate.exception.ChordInnateConstraintViolation;
import chordinnate.exception.ChordInnateException;
import chordinnate.repository.TagRepository;
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
@PrepareForTest({TagRepository.class})
public class TagServiceImplTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private static TagServiceImpl service;
    private static TagRepository mockRepo;
    private static Tag TEST_TAG;

    @BeforeClass
    public static void init() {
        mockRepo = mock(TagRepository.class);
        when(mockRepo.save(any(Tag.class))).thenReturn(TEST_TAG);
        doNothing().when(mockRepo).delete(any(Tag.class));

        service = new TagServiceImpl(mockRepo);
    }

    @Before
    public void setup() {
        TEST_TAG = new Tag();
        TEST_TAG.setName("TEST");
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
        service.save(new Tag());
    }

    @Test
    public void save_missingRequiredField_name() {
        expectedException.expect(ChordInnateConstraintViolation.class);
        expectedException.expectMessage("Tag Name must not be blank");

        TEST_TAG.setName(null);

        service.save(TEST_TAG);
    }

    @Test
    public void save_invalidField_name() {
        expectedException.expect(ChordInnateConstraintViolation.class);
        expectedException.expectMessage("Tag Name must not be blank");

        TEST_TAG.setName("");

        service.save(TEST_TAG);
    }

    @Test
    public void save_success() {
        service.save(TEST_TAG);
    }

    @Test
    public void delete_nullParam() {
        service.delete(null);
    }

    @Test
    public void delete_blankParam() {
        service.delete(new Tag());
    }

    @Test
    public void delete_missingId() {
        TEST_TAG.setId(null);

        service.delete(TEST_TAG);
    }

    @Test
    public void delete_invalidId() {
        TEST_TAG.setId(1);

        when(mockRepo.findById(anyInt())).thenReturn(Optional.empty());

        service.delete(TEST_TAG);

        verify(mockRepo, atLeast(1)).findById(anyInt());
    }

    @Test
    public void delete_existingRecordDoesNotMatchEntity() {
        expectedException.expect(ChordInnateException.class);
        expectedException.expectMessage("Cannot delete tag #1 (DIFFERENT TAG NAME): entity data does not match record data");

        Tag inRepo = new Tag();
        inRepo.setId(1);
        inRepo.setName("TAG NAME");

        when(mockRepo.findById(anyInt())).thenReturn(Optional.of(inRepo));

        TEST_TAG.setId(1);
        TEST_TAG.setName("DIFFERENT TAG NAME");

        service.delete(TEST_TAG);

        verify(mockRepo, atLeast(1)).findById(anyInt());
    }

    @Test
    public void delete_success() {
        TEST_TAG.setId(1);

        when(mockRepo.findById(anyInt())).thenReturn(Optional.of(TEST_TAG));

        service.delete(TEST_TAG);

        verify(mockRepo, atLeast(1)).findById(anyInt());
    }

}
