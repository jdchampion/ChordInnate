package chordinnate.service;

import chordinnate.config.DatabaseConfig;
import chordinnate.service.impl.*;
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

    public static TagService getTagService() {
        return (TagService) getContext().getBean(TagServiceImpl.SERVICE_NAME);
    }

    public static ChordTypeService getChordTypeService() {
        return (ChordTypeService) getContext().getBean(ChordTypeServiceImpl.SERVICE_NAME);
    }

    public static ChordTypeTagRelationService getChordTypeTagRelationService() {
        return (ChordTypeTagRelationService) getContext().getBean(ChordTypeTagRelationServiceImpl.SERVICE_NAME);
    }

    public static ScaleTypeService getScaleTypeService() {
        return (ScaleTypeService) getContext().getBean(ScaleTypeServiceImpl.SERVICE_NAME);
    }

    public static ScaleTypeTagRelationService getScaleTypeTagRelationService() {
        return (ScaleTypeTagRelationService) getContext().getBean(ScaleTypeTagRelationServiceImpl.SERVICE_NAME);
    }

}
