package chordinnate.entity;

import chordinnate.service.Services;
import chordinnate.service.ScaleTypeTagService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;


@Slf4j
public class TestScaleTypeTag {

    @Test
    public void test() {

        // TODO: need to test adding (insert) and removing (delete) a tag from a given ScaleType
        ScaleTypeTagService service = Services.getScaleTypeTagService();

        service.findAllFor(Services.getScaleTypeService().findByName("Adonai Malakh").get()).forEach(scaleTypeTag -> {
            log.info(scaleTypeTag.getName());
        });

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
