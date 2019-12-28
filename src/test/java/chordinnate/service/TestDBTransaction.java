package chordinnate.service;

import chordinnate.config.DatabaseConfig;
import chordinnate.entity.ScaleTypeTag;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseConfig.class)
@Transactional
public class TestDBTransaction {

    @Autowired
    ScaleTypeTagService service;

    @Test
    @Rollback
    public void testSave() {
        ScaleTypeTag tag = new ScaleTypeTag();
        tag.setName("TEST TAG NAME");

        service.save(tag);
    }
}
