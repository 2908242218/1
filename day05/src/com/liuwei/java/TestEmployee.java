package com.liuwei.java;

import org.junit.Test;

import java.util.*;

/**
 * @author MikeCoder
 * @create 2022-10-2610:09
 */
public class TestEmployee {
    @Test
    public void test(){
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        System.out.println(next);
    }
    @Test
    public void testEmployee() {
        Employee liuwei = new Employee("liuwei", 25, new MyDate(2000, 7, 9));
        Employee tom = new Employee("Tom", 22, new MyDate(2002, 6, 25));
        Employee Make = new Employee("Make", 19, new MyDate(2003, 5, 23));
        Employee Jerry = new Employee("Jerry", 30, new MyDate(1992, 8, 20));
        Employee Jeck = new Employee("Jeck", 45, new MyDate(1980, 5, 12));
        Comparator comparator = new Comparator(){

            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof Employee && o2 instanceof Employee){
                    Employee o11 = (Employee) o1;
                    Employee o21 = (Employee) o2;
                    return -Integer.compare(o11.getAge(), o21.getAge());
                }
                else {
                    throw new RuntimeException("输入的信息不匹配");
                }
            }
        };
        Set set = new TreeSet(comparator);
        set.add(liuwei);
        set.add(tom);
        set.add(Make);
        set.add(Jerry);
        set.add(Jeck);


        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
