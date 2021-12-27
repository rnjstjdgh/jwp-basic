package core.ref;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class Junit4TestRunner {
    @Test
    public void run() throws Exception {
        Class<Junit4Test> clazz = Junit4Test.class;
        boolean isHavingAnnotation;
        for(Method method: clazz.getDeclaredMethods()){
            isHavingAnnotation = false;
            for (Annotation annotation: method.getDeclaredAnnotations()) {
                if(annotation.annotationType() != MyTest.class)
                    continue;
                isHavingAnnotation = true;
                break;
            }
            if(isHavingAnnotation)
                method.invoke(clazz.newInstance());
        }

    }
}
