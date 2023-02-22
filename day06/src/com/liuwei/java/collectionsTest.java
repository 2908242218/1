package com.liuwei.java;

import org.junit.Test;

import java.util.*;

/**
 * @author MikeCoder
 * @create 2022-10-299:13
 */
public class collectionsTest {
    @Test
    public void copyTest(){
        ArrayList list = new ArrayList();
        list.add(526);
        list.add(586);
        list.add(238);
        list.add(492);
        list.add(792);
        List objects = Arrays.asList(new Object[list.size()]);

        Collections.reverse(list);
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
