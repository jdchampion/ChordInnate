package chordinnate.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseService {

    private static ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/applicationContext.xml");

    protected static ChordTypeManager chordTypeService = (ChordTypeManager) context.getBean(ChordTypeManager.class);
    protected static ScaleTypeManager scaleTypeService = (ScaleTypeManager) context.getBean(ScaleTypeManager.class);

}
