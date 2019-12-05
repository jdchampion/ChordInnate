package chordinnate.entity;

import chordinnate.service.Services;
import chordinnate.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;


@Slf4j
public class TestTag {

    @Test
    public void test() {

        // TODO: need to test adding (insert) and removing (delete) a tag from a given ScaleType
        TagService service = Services.getTagService();

        // TODO: the FetchType.EAGER on Tag.tagGroupRels
        Tag tag = service.findById(9).orElse(null);

//        assertNotNull(tag);
//
//        tag = service.findByName("South Indian").orElse(null);
//
//        assertNotNull(tag);
//
//        assertEquals("South Indian", tag.getName());
//
//        tag.getTagGroupRels().forEach(rel -> {
//            log.info(rel.getMatchingScaleType().getName());
//        });

    }

}
