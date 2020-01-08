package chordinnate.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import chordinnate.config.DatabaseConfig;
import chordinnate.entity.ChordType;
import chordinnate.entity.ChordTypeTagRelation;
import chordinnate.entity.ScaleType;
import chordinnate.entity.ScaleTypeTagRelation;
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
import java.util.Collections;
import java.util.List;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseConfig.class)
@Transactional
public class TagRelationServiceIT {

    private static final String TAG_NAME = "TEST TAG NAME " + Instant.now().toString();

    @Autowired
    TagService tagService;

    @Autowired
    ScaleTypeTagRelationService scaleTypeTagRelationService;

    @Autowired
    ChordTypeTagRelationService chordTypeTagRelationService;

    @Autowired
    ScaleTypeService scaleTypeService;

    @Autowired
    ChordTypeService chordTypeService;

    @Test
    @Rollback
    public void create_chordType() {
        Tag tag = tagService.save(new Tag(TAG_NAME));
        assertTrue(tagService.findByName(tag.getName()).isPresent());

        ChordType ct = chordTypeService.findById(1).orElse(null);
        assertNotNull(ct);

        ChordTypeTagRelation cttr = chordTypeTagRelationService.save(new ChordTypeTagRelation(ct, tag));
        assertNotNull(cttr);

        List<ChordType> ctList = chordTypeService.findAllByTags(Collections.singleton(tag));
        assertEquals(1, ctList.size());
        assertEquals(ct, ctList.get(0));
        assertTrue(ctList.get(0).getChordTypeTagRelations().contains(cttr));
    }

    @Test
    @Rollback
    public void update_chordType() {
        Tag tag = tagService.save(new Tag(TAG_NAME));
        assertTrue(tagService.findByName(TAG_NAME).isPresent());

        ChordType ct = chordTypeService.findById(1).orElse(null);
        assertNotNull(ct);

        ChordTypeTagRelation cttr = chordTypeTagRelationService.save(new ChordTypeTagRelation(ct, tag));
        assertNotNull(cttr);

        List<Tag> tags = tagService.findAllFor(ct);
        assertTrue(tags.contains(tag));

        final String DIFFERENT_TAG_NAME = TAG_NAME + "2";

        tag.setName(DIFFERENT_TAG_NAME);
        tagService.save(tag);

        assertTrue(tagService.findByName(DIFFERENT_TAG_NAME).isPresent());
        assertTrue(tagService.findByName(TAG_NAME).isEmpty());

        tags = tagService.findAllFor(ct);
        assertTrue(tags.stream().anyMatch(t -> DIFFERENT_TAG_NAME.equals(t.getName())));
        assertTrue(tags.stream().noneMatch(t -> TAG_NAME.equals(t.getName())));
    }

    @Test
    @Rollback
    public void delete_chordType() {
        Tag tag = tagService.save(new Tag(TAG_NAME));
        assertTrue(tagService.findByName(tag.getName()).isPresent());

        ChordType ct = chordTypeService.findById(1).orElse(null);
        assertNotNull(ct);

        ChordTypeTagRelation cttr = chordTypeTagRelationService.save(new ChordTypeTagRelation(ct, tag));
        assertNotNull(cttr);

        tagService.delete(tag);
        assertTrue(tagService.findByName(tag.getName()).isEmpty());

        List<ChordType> ctList = chordTypeService.findAllByTags(Collections.singleton(tag));
        assertTrue(ctList.isEmpty());
    }

    @Test
    @Rollback
    public void create_scaleType() {
        Tag tag = tagService.save(new Tag(TAG_NAME));
        assertTrue(tagService.findByName(tag.getName()).isPresent());

        ScaleType st = scaleTypeService.findById(1).orElse(null);
        assertNotNull(st);

        ScaleTypeTagRelation sttr = scaleTypeTagRelationService.save(new ScaleTypeTagRelation(st, tag));
        assertNotNull(sttr);

        List<ScaleType> stList = scaleTypeService.findAllByTags(Collections.singleton(tag));
        assertEquals(1, stList.size());
        assertEquals(st, stList.get(0));
        assertTrue(stList.get(0).getScaleTypeTagRelations().contains(sttr));
    }

    @Test
    @Rollback
    public void update_scaleType() {
        Tag tag = tagService.save(new Tag(TAG_NAME));
        assertTrue(tagService.findByName(TAG_NAME).isPresent());

        ScaleType st = scaleTypeService.findById(1).orElse(null);
        assertNotNull(st);

        ScaleTypeTagRelation sttr = scaleTypeTagRelationService.save(new ScaleTypeTagRelation(st, tag));
        assertNotNull(sttr);

        List<Tag> tags = tagService.findAllFor(st);
        assertTrue(tags.contains(tag));

        final String DIFFERENT_TAG_NAME = TAG_NAME + "2";

        tag.setName(DIFFERENT_TAG_NAME);
        tagService.save(tag);

        assertTrue(tagService.findByName(DIFFERENT_TAG_NAME).isPresent());
        assertTrue(tagService.findByName(TAG_NAME).isEmpty());

        tags = tagService.findAllFor(st);
        assertTrue(tags.stream().anyMatch(t -> DIFFERENT_TAG_NAME.equals(t.getName())));
        assertTrue(tags.stream().noneMatch(t -> TAG_NAME.equals(t.getName())));
    }

    @Test
    @Rollback
    public void delete_scaleType() {
        Tag tag = tagService.save(new Tag(TAG_NAME));
        assertTrue(tagService.findByName(tag.getName()).isPresent());

        ScaleType st = scaleTypeService.findById(1).orElse(null);
        assertNotNull(st);

        ScaleTypeTagRelation sttr = scaleTypeTagRelationService.save(new ScaleTypeTagRelation(st, tag));
        assertNotNull(sttr);

        tagService.delete(tag);
        assertTrue(tagService.findByName(tag.getName()).isEmpty());

        List<ScaleType> stList = scaleTypeService.findAllByTags(Collections.singleton(tag));
        assertTrue(stList.isEmpty());
    }

    @Test
    @Rollback
    public void create_chordTypeAndScaleType() {
        Tag tag = tagService.save(new Tag(TAG_NAME));
        assertTrue(tagService.findByName(tag.getName()).isPresent());

        ChordType ct = chordTypeService.findById(1).orElse(null);
        ScaleType st = scaleTypeService.findById(1).orElse(null);
        assertNotNull(ct);
        assertNotNull(st);

        ChordTypeTagRelation cttr = chordTypeTagRelationService.save(new ChordTypeTagRelation(ct, tag));
        assertNotNull(cttr);

        List<ChordType> ctList = chordTypeService.findAllByTags(Collections.singleton(tag));
        assertEquals(1, ctList.size());
        assertEquals(ct, ctList.get(0));
        assertTrue(ctList.get(0).getChordTypeTagRelations().contains(cttr));

        ScaleTypeTagRelation sttr = scaleTypeTagRelationService.save(new ScaleTypeTagRelation(st, tag));
        assertNotNull(sttr);

        List<ScaleType> stList = scaleTypeService.findAllByTags(Collections.singleton(tag));
        assertEquals(1, stList.size());
        assertEquals(st, stList.get(0));
        assertTrue(stList.get(0).getScaleTypeTagRelations().contains(sttr));
    }

    @Test
    @Rollback
    public void update_chordTypeAndScaleType() {
        // TODO
    }

    @Test
    @Rollback
    public void delete_chordTypeAndScaleType() {
        Tag tag = tagService.save(new Tag(TAG_NAME));
        assertTrue(tagService.findByName(tag.getName()).isPresent());

        ChordType ct = chordTypeService.findById(1).orElse(null);
        ScaleType st = scaleTypeService.findById(1).orElse(null);
        assertNotNull(ct);
        assertNotNull(st);

        ChordTypeTagRelation cttr = chordTypeTagRelationService.save(new ChordTypeTagRelation(ct, tag));
        ScaleTypeTagRelation sttr = scaleTypeTagRelationService.save(new ScaleTypeTagRelation(st, tag));
        assertNotNull(cttr);
        assertNotNull(sttr);

        tagService.delete(tag);
        assertTrue(tagService.findByName(tag.getName()).isEmpty());

        List<ChordType> ctList = chordTypeService.findAllByTags(Collections.singleton(tag));
        List<ScaleType> stList = scaleTypeService.findAllByTags(Collections.singleton(tag));
        assertTrue(ctList.isEmpty());
        assertTrue(stList.isEmpty());
    }
}
