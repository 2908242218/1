package com.liuwei.java1;

/**
 * @author MikeCoder
 * @create 2022-10-108:59
 */
public class CommunicationTest {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producer p = new Producer(clerk);
        Consumer c = new Consumer(clerk);
        p.setName("生产者");
        c.setName("消费者");
        p.start();
        c.start();
    }


}

class Clerk {
    private int productNumber;//产品数量
    //生产者生成产品
    public synchronized void produceProduct() {

        if(productNumber < 20){
            productNumber++;
            System.out.println(Thread.currentThread().getName()+"正生产第"+productNumber+"个产品");
            notify();
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    //消费者消费产品
    public synchronized void consumeProduct() {
        if(productNumber > 0){
            System.out.println(Thread.currentThread().getName()+"正消费第"+productNumber+"个产品");
            productNumber--;
            notify();
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Producer extends Thread {
    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println("生成者开始生成......");
        while(true) {
            try {
                Thread.sleep(180);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.produceProduct();
        }
    }
}

class Consumer extends Thread {
    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println("消费者开始消费......");
        while(true) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.consumeProduct();
        }
    }
}
