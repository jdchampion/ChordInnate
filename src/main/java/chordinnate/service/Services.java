package chordinnate.service;

import chordinnate.service.impl.ChordTypeServiceImpl;
import chordinnate.service.impl.ChordTypeTagRelationServiceImpl;
import chordinnate.service.impl.ScaleTypeServiceImpl;
import chordinnate.service.impl.ScaleTypeTagRelationServiceImpl;
import chordinnate.service.impl.TagServiceImpl;
import chordinnate.util.ContextProvider;
import org.springframework.stereotype.Service;

@Service
public final class Services {

    private Services() {}


    public static TagService getTagService() {
        return (TagService) ContextProvider.getContext().getBean(TagServiceImpl.SERVICE_NAME);
    }

    public static ChordTypeService getChordTypeService() {
        return (ChordTypeService) ContextProvider.getContext().getBean(ChordTypeServiceImpl.SERVICE_NAME);
    }

    public static ChordTypeTagRelationService getChordTypeTagRelationService() {
        return (ChordTypeTagRelationService) ContextProvider.getContext().getBean(ChordTypeTagRelationServiceImpl.SERVICE_NAME);
    }

    public static ScaleTypeService getScaleTypeService() {
        return (ScaleTypeService) ContextProvider.getContext().getBean(ScaleTypeServiceImpl.SERVICE_NAME);
    }

    public static ScaleTypeTagRelationService getScaleTypeTagRelationService() {
        return (ScaleTypeTagRelationService) ContextProvider.getContext().getBean(ScaleTypeTagRelationServiceImpl.SERVICE_NAME);
    }

}
