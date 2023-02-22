package com.liuwei.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**Stream的实例化
 * @author MikeCoder
 * @create 2022-11-1115:40
 */
public class StreamTest {
    @Test
    //方式一：通过集合
    public void test1(){
        String s1 = "大树王子";
        String s2 = "周杰伦";
        ArrayList<String> objects = new ArrayList<>();
        objects.add(s1);
        objects.add(s2);
        Stream<String> stream = objects.stream();
        Stream<String> parallelStream = objects.parallelStream();
        System.out.println(stream);
        System.out.println(parallelStream);
    }
    @Test
    public void test2(){
        List<Person> list = new ArrayList<>();
        list.add(new Person("Tom",25));
        list.add(new Person("Jerry",22));
        list.add(new Person("Jack",20));
        list.add(new Person("Jack",20));
        list.add(new Person("Rose",19));
        Stream<Person> stream = list.stream();
        //filter方法练习
        stream.filter(e->e.getAge()>=20).forEach(str-> System.out.println(str));
        System.out.println("***********");
        //limit方法练习
        list.stream().filter(e -> e.getAge()>=20).limit(2).forEach(System.out::println);
        System.out.println("*********");
        //Skip方法练习
        list.stream().skip(2).forEach(System.out::println);
        System.out.println("************");
        //distinct方法练习
        list.stream().distinct().forEach(System.out::println);


    }

}
