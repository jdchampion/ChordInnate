package chordinnate.model.musictheory.temporal.tempo;

import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import chordinnate.config.MidiConfig;
import chordinnate.model.musictheory.temporal.rhythm.Beat;
import chordinnate.util.ContextProvider;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Joseph on 6/22/16.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = ContextProvider.class)
public class TempoTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void init() {
        mockStatic(ContextProvider.class);

        MidiConfig mockConfig = mock(MidiConfig.class);
        when(mockConfig.getMinBpm()).thenReturn(20);
        when(mockConfig.getMaxBpm()).thenReturn(240);

        AnnotationConfigApplicationContext mockContext = mock(AnnotationConfigApplicationContext.class);
        when(ContextProvider.getContext()).thenReturn(mockContext);
        when(mockContext.getBean(MidiConfig.class)).thenReturn(mockConfig);
    }

    @Test
    public void invalidBPM() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Tempo must be between "
                + TempoMarking.slowest().minBpm + " and "
                + TempoMarking.fastest().maxBpm + " BPM (inclusive).");

        new Tempo(Beat.QUARTER, 0);
    }

}