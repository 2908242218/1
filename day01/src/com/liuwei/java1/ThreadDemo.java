package com.liuwei.java1;

/**
 * 练习：创建两个分线程，其中一个线程遍历100以内的偶数，另一个线程遍历100以内的奇数
 * @author MikeCoder
 * @create 2022-10-058:34
 */
public class ThreadDemo extends Thread{
    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        thread2.start();
    }
}
class Thread1 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 ==0){
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}
class Thread2 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 !=0){
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}
