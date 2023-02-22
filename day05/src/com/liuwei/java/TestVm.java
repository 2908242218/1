package com.liuwei.java;

import org.junit.Test;

import java.util.Scanner;

/**
 * @author MikeCoder
 * @create 2023-02-099:53
 * @description:java
 * @verson:
 */
public class TestVm {
    @Test
    public void test(){
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        System.out.println(next);
        scanner.close();
    }
}
