package com.gjy.test;

import com.winter.annotation.Component;
import com.winter.domain.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.annotation.Annotation;

public class AnnotationTest {

    @Test
    public void test1() throws Exception{
        Person person = getBean("com.winter.domain.Person", Person.class);
        System.out.println("person = " + person);
    }

    public <T> T getBean(String name,Class<T> clazz) throws Exception{
        clazz = (Class<T>) Class.forName(name);
        T t = clazz.newInstance();

        Annotation[] annotations = clazz.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            Class<? extends Annotation> annotationType = annotation.annotationType();
            String typeName = annotationType.getTypeName();
            System.out.println("typeName = " + typeName);

            Component component = (Component) annotation;
            String value = component.value();
            System.out.println(value);
        }
        return t;
    }

    public void test2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("");
        context.getBean("");
    }
}
