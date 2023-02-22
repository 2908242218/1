package com.liuwei.java;

/**
 * @author MikeCoder
 * @create 2022-10-0915:08
 */
public class TreadSafetyText {
    public static void main(String[] args) {
        Account account = new Account(0);
        Depositor d1 = new Depositor(account);
        Depositor d2 = new Depositor(account);
        d1.setName("储户一");
        d2.setName("储户二");
        d1.start();
        d2.start();
    }
}

class Account {
    private double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double i) {
        if(i > 0) {
            balance += i;
        }
    }
}


class Depositor extends Thread {
    private Account account;

    public Depositor(Account account) {
        this.account = account;
    }
    public void run(){
        synchronized(Depositor.class) {
            for (int i = 0; i < 3; i++) {
                account.deposit(1000);
                System.out.println(getName() + "余额为：" + account.getBalance());
            }
        }
    }
}

