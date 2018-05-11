package chordinnate.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseService {

    private static final ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/applicationContext.xml");

    protected static final ChordTypeManager chordTypeService = (ChordTypeManager) context.getBean(ChordTypeManager.class);
    protected static final ScaleTypeManager scaleTypeService = (ScaleTypeManager) context.getBean(ScaleTypeManager.class);

    private BaseService() {}

    public static ChordTypeManager getChordTypeService() {
        return chordTypeService;
    }

    public static ScaleTypeManager getScaleTypeService() {
        return scaleTypeService;
    }

}
