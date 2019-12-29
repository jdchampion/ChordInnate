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

import java.util.Collections;
import java.util.List;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseConfig.class)
@Transactional
public class TestDBTransaction {

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
    public void saveTag() {
        Tag tag = new Tag();
        tag.setName("TEST TAG NAME");

        tagService.save(tag);

        assertTrue(tagService.findByName(tag.getName()).isPresent());
    }

    @Test
    @Rollback
    public void saveRelation() {
        Tag tag = new Tag();
        tag.setName("TEST TAG NAME");

        tagService.save(tag);
        assertTrue(tagService.findByName(tag.getName()).isPresent());

        ChordType ct = chordTypeService.findById(1).orElse(null);
        ScaleType st = scaleTypeService.findById(1).orElse(null);
        assertNotNull(ct);
        assertNotNull(st);

        ScaleTypeTagRelation sttr = scaleTypeTagRelationService.save(new ScaleTypeTagRelation(st, tag));
        assertNotNull(sttr);

        List<ScaleType> stList = scaleTypeService.findAllByTags(Collections.singleton(tag));
        assertEquals(1, stList.size());
        assertTrue(stList.stream().allMatch(scaleType -> scaleType.equals(st)));

        ScaleType stFound = stList.get(0);
        assertEquals(1, stFound.getScaleTypeTagRelations().size());
        assertEquals(sttr, stFound.getScaleTypeTagRelations().stream().findFirst().get());

        ChordTypeTagRelation cttr = chordTypeTagRelationService.save(new ChordTypeTagRelation(ct, tag));
        assertNotNull(sttr);

        List<ChordType> ctList = chordTypeService.findAllByTags(Collections.singleton(tag));
        assertEquals(1, ctList.size());
        assertTrue(ctList.stream().allMatch(scaleType -> scaleType.equals(ct)));

        ChordType ctFound = ctList.get(0);
        assertEquals(1, ctFound.getChordTypeTagRelations().size());
        assertEquals(cttr, ctFound.getChordTypeTagRelations().stream().findFirst().get());
    }
}
