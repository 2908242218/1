package com.liuwei;

/**
 * 创建线程的方式二：实现Runable接口
 *
 * @author MikeCoder
 * @create 2022-10-0519:48
 */
public class ThreadTest2 {
    public static void main(String[] args) {
        //3.创建实现类的对象
        Tread1 p = new Tread1();
        //4.将次对象作为参数传递到Thread类的构造器中，创建Thread类的对象
        Thread t2 = new Thread(p);
        Thread t3 = new Thread(p);
        t2.setName("线程1");
        t3.setName("线程2");
        t2.start();
        t3.start();

    }

}

//1.创建一个实现了Runnable接口的类
class Tread1 implements Runnable {
    //2.实现类去实现Runnable中的抽象方法
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName()+":"+i);
            }

        }
    }
}



