package com.gjy.test;

import com.winter.annotation.MyAnnotation;
import com.winter.domain.Ann;
import com.winter.domain.MyEnum;
import com.winter.domain.User;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class SimpleTest {

    @Test
    public void testAnn() throws NoSuchFieldException {
        Class<Ann> clazz = Ann.class;
        Annotation[] annotations = clazz.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println("annotation = " + annotation.annotationType().getTypeName());
        }
        MyAnnotation annotation = clazz.getAnnotation(MyAnnotation.class);
        System.out.println("annotation.value() = " + annotation.value());

        Annotation[] clazzAnnotations = clazz.getAnnotations();

    }

    @Test
    public void testSer() throws Exception{
        Class<?> clazz = Class.forName("com.winter.domain.MyEnum");//true
        MyEnum state = MyEnum.state;//true
        User user = new User();//false

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(user);

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object o = ois.readObject();

        System.out.println("(state == o) = " + (user == o));
    }

    @Test
    public void testEnum() throws Exception{
        Class<?> clazz = Class.forName("com.winter.domain.MyEnum");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("field = " + field);
        }

        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println("constructor = " + constructor);
            constructor.setAccessible(true);
//            Object o = constructor.newInstance(String.class, int.class);
//            System.out.println("o = " + o);
        }
    }

    /**
     * 通过反射获取类的完整结构
     * Field,Method,Constructor,Annotation,SuperClass,Interfaces
     */
    @Test
    public void test5(){

    }

    @Test
    public void test4() throws Exception{
        Class<?> clazz = Class.forName("com.winter.domain.User");
        Constructor<?> constructor = clazz.getConstructor(Integer.class, String.class);
        Object instance = constructor.newInstance(21, "宫静雨");
        System.out.println("instance = " + instance);

        Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(Integer.class);
        declaredConstructor.setAccessible(true);
        Object o = declaredConstructor.newInstance(21);
        System.out.println("o = " + o);
    }

    @Test
    public void test3(){
        System.out.println("Object.class = " + Object.class);
        System.out.println("String[].class = " + String[].class);
        System.out.println("String[][].class = " + String[][].class);
        System.out.println("Override.class = " + Override.class);
        System.out.println("ElementType.class = " + ElementType.class);
        System.out.println("Void.class = " + Void.class);
        System.out.println("int.class = " + int.class);
    }

    @Test
    public void test2() throws Exception{
        Class clazz = Class.forName("com.winter.domain.User");
        Class superclass = clazz.getSuperclass();
        System.out.println("superclass = " + superclass);

        for (Class clazzInterface : clazz.getInterfaces()) {
            System.out.println("clazzInterface = " + clazzInterface);
        }

        System.out.println("clazz.getClassLoader() = " + clazz.getClassLoader());

        Object o = clazz.newInstance();
        Method a = clazz.getMethod("a", String.class, Integer.class);
        a.invoke(o, "gjy", 21);

        Field id = clazz.getField("name");
        id.set(o,"12");
        System.out.println(id);

    }

    @Test
    public void test1() throws Exception{
        User user = new User();
        System.out.println("user = " + user.hashCode());

        System.out.println("user.getClass().newInstance() = " + user.getClass().newInstance().hashCode());

    }
}
