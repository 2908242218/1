package com.liuwei.java;

import org.junit.Test;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author MikeCoder
 * @create 2022-11-117:49
 */
public class quoteMethodTest {
    @Test
    public void test1() {
        PrintStream out = System.out;
        Consumer<String> consumer = out::println;
        consumer.accept("大家上午好");
    }

    @Test
    public void test2() {
        Person person = new Person("Tom", 25);
        Supplier<String> supplier = person::getName;
        String s = supplier.get();
        System.out.println(s);
    }

    @Test
    public void test3() {
        int[]a = {78,56,95,64,32,85,64,78,36,96,46,27};
        int i = Arrays.binarySearch(a, 85);
        System.out.println(i);

    }
    @Test
    public void test4(){

    }

}
