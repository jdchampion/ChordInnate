package chordinnate.util;

import chordinnate.config.DatabaseConfig;
import chordinnate.config.MidiConfig;
import chordinnate.midi.MidiDeviceManager;
import chordinnate.midi.MidiOutputRouter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ContextProvider {

    private static AnnotationConfigApplicationContext context;

    public static AnnotationConfigApplicationContext getContext() {
        if (context == null) {
            context = new AnnotationConfigApplicationContext();
            context.register(DatabaseConfig.class, MidiConfig.class, MidiDeviceManager.class, MidiOutputRouter.class);
            context.refresh();
        }

        return context;
    }
}
