package chordinnate.service;

import static org.junit.Assert.assertTrue;

import chordinnate.config.DatabaseConfig;
import chordinnate.entity.Tag;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseConfig.class)
@Transactional
public class TagServiceIT {

    private static final String TAG_NAME = "TEST TAG NAME " + Instant.now().toString();

    @Autowired
    TagService tagService;

    @Test
    @Rollback
    public void create() {
        assertTrue(tagService.findByName(TAG_NAME).isEmpty());
        Tag tag = tagService.save(new Tag(TAG_NAME));
        assertTrue(tagService.findByName(tag.getName()).isPresent());
    }

    @Test
    @Rollback
    public void update() {
        final String OTHER_TAG_NAME = TAG_NAME + "2";

        Tag tag = tagService.save(new Tag(TAG_NAME));
        assertTrue(tagService.findByName(tag.getName()).isPresent());

        tag.setName(OTHER_TAG_NAME);
        tagService.save(tag);
        assertTrue(tagService.findByName(OTHER_TAG_NAME).isPresent());
        assertTrue(tagService.findByName(TAG_NAME).isEmpty());
    }

    @Test
    @Rollback
    public void delete() {
        Tag tag = tagService.save(new Tag(TAG_NAME));
        assertTrue(tagService.findByName(tag.getName()).isPresent());

        tagService.delete(tag);
        assertTrue(tagService.findByName(tag.getName()).isEmpty());
    }
}
