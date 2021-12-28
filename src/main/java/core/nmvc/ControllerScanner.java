package core.nmvc;

import core.mvc.Controller;
import core.mvc.DispatcherServlet;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ControllerScanner {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

    public static List<Object> controllerScan(Object... basePackage) throws IllegalAccessException, InstantiationException {
        Reflections reflections = new Reflections(basePackage);
        Set<Class<?>> annotatedClazzs = reflections.getTypesAnnotatedWith(core.annotation.Controller.class);
        List<Object> controllers = new ArrayList<>();
        for(Class clazz : annotatedClazzs){
            logger.info(clazz.toString());
            controllers.add(clazz.newInstance());
        }
        return controllers;
    }
}
