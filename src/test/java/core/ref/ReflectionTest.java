package core.ref;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.model.Question;
import next.model.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);
    private Class<Student> clazz;

    @Test
    public void showClass() {
        Class<Question> clazz = Question.class;
        for(Field field : clazz.getDeclaredFields()){
            System.out.println(field);
        }
        for(Constructor constructor : clazz.getDeclaredConstructors()){
            System.out.println(constructor);
        } for(Method method  : clazz.getDeclaredMethods()) {
            System.out.println(method);
        }
        logger.debug(clazz.getName());
    }
    
    @Test
    public void newInstanceWithConstructorArgs() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<User> clazz = User.class;
        User user = null;
        for(Constructor constructor: clazz.getDeclaredConstructors())
            user = (User) constructor.newInstance("userId1","password1","name1","email1");
        Assert.assertEquals(user.getUserId(),"userId1");
        logger.debug(clazz.getName());
    }
    
    @Test
    public void privateFieldAccess() throws IllegalAccessException {
        Class<Student> clazz = Student.class;
        Student student = new Student();

        for (Field field: clazz.getDeclaredFields()){
            field.setAccessible(true);
            if(field.getName().equals("name"))
                field.set(student, "soungho");
            if(field.getName().equals("age"))
                field.set(student, 14);
        }

        logger.debug(student.getName());
        logger.debug(String.valueOf(student.getAge()));
        logger.debug(clazz.getName());
    }
}
