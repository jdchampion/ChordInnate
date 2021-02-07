package chordinnate.util;

import static org.junit.Assert.assertEquals;

import chordinnate.config.MidiConfig;
import org.junit.Test;

public class ContextProviderTest {

    @Test
    public void test_mutate_MidiConfig() {
        MidiConfig config = ContextProvider.getContext().getBean(MidiConfig.class);
        assertEquals(20, config.getMinBpm());

        config.setMinBpm(40);
        assertEquals(40, config.getMinBpm());
    }
}
