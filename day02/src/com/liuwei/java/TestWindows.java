package com.liuwei.java;

import static java.lang.Thread.sleep;

/**
 * @author MikeCoder
 * @create 2022-10-0915:43
 */
class Windows implements Runnable {
    private int ticket;

    public Windows(int ticket) {
        this.ticket = ticket;
    }

    @Override
    public void run() {
        while (true) {
            if (ticket > 0) {
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "票号为：" + ticket);
                ticket--;
            } else {
                break;
            }

        }
    }
}

public class TestWindows {
    public static void main(String[] args) {
        Windows w = new Windows(100);
        Thread w1 = new Thread(w);
        Thread w2 = new Thread(w);
        Thread w3 = new Thread(w);
        w1.setName("线程一");
        w2.setName("线程二");
        w3.setName("线程三");
        w1.start();
        w2.start();
        w3.start();
    }

}

