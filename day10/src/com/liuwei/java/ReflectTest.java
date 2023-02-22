package com.liuwei.java;

import org.junit.Test;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @author MikeCoder
 * @create 2022-11-0720:18
 */
public class ReflectTest {
    @Test
    //创建运行时类的四个方式
    public void test1() throws Exception {
        //方式一：调用运行时类的属性：.class
        Class<Person> personClass = Person.class;
        Constructor<Person> declaredConstructor = personClass.getDeclaredConstructor(String.class ,int.class);
        Person person1 = declaredConstructor.newInstance("大树王子", 22);
        System.out.println(person1);

        //方式二：调用getClass()方法
        Person person = new Person("Tom",26);
        Class<? extends Person> aClass = person.getClass();
        Field name = aClass.getDeclaredField("name");
        name.setAccessible(true);
        name.set(person,"jaryy");
        System.out.println(person);
        Person person2 = aClass.newInstance();
        System.out.println(person2);
        //方式三：调用Class类的静态方法forName(String classpath);
        Class<?> aClass1 = Class.forName("com.liuwei.java.Person");
        Object o = aClass1.newInstance();
        System.out.println(o);
        //方式四：使用类的加载器：classLoader
        ClassLoader classLoader = ReflectTest.class.getClassLoader();
        Class<?> aClass2 = classLoader.loadClass("com.liuwei.java.Person");
        System.out.println(aClass2);
        System.out.println(personClass == aClass);
        System.out.println(personClass == aClass1);
        System.out.println(aClass1 == aClass2);
        ///
        ClassLoader loader = String.class.getClassLoader();
        System.out.println(loader);
        ClassLoader parent = Person.class.getClassLoader().getParent();
        System.out.println(parent);
        //使用properties类读取配置文件
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src\\jbkd.properties");
        properties.load(fileInputStream);
        String name1 = properties.getProperty("name");
        String age = properties.getProperty("age");
        System.out.println(name1);
        System.out.println(age);
    }

}
