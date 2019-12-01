package chordinnate.service;

import chordinnate.config.DatabaseConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

@Service
public final class Services {

    private static AnnotationConfigApplicationContext context;

    private Services() {}

    private static AnnotationConfigApplicationContext getContext() {
        if (context == null) {
            context = new AnnotationConfigApplicationContext();
            context.register(DatabaseConfig.class);
            context.refresh();
        }

        return context;
    }

    public static ChordTypeService getChordTypeService() {
        return (ChordTypeService) getContext().getBean("chordTypeService");
    }

    public static ScaleTypeService getScaleTypeService() {
        return (ScaleTypeService) getContext().getBean("scaleTypeService");
    }

}
