package chordinnate.entity;

import chordinnate.service.Services;
import chordinnate.service.TagGroupRelService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;


@Slf4j
public class TestTagGroupRel {

    @Test
    public void test() {

        // TODO: might not even need TagGroupRelService since all useful func

        TagGroupRelService service = Services.getTagGroupRelService();

        TagGroupRel tagGroupRel = service.findById(1).orElse(null);

        assertNotNull(tagGroupRel);

        assertNotNull(tagGroupRel.getMatchingTag());

        ScaleType scaleType = tagGroupRel.getMatchingScaleType();

        log.info(scaleType.getName());

        scaleType.getTagGroupRels().forEach(rel -> {
            log.info(rel.getMatchingTag().getName());
        });

    }

}
