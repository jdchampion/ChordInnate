package chordinnate.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public final class BaseService implements ApplicationContextAware {

    private BaseService() {}

    @Autowired
    static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static ChordTypeService getChordTypeService() {
        return (ChordTypeService) context.getBean("chordTypeService");
    }

    public static ScaleTypeService getScaleTypeService() {
        return (ScaleTypeService) context.getBean("scaleTypeService");
    }

}
