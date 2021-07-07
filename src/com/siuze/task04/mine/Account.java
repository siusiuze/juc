package com.siuze.task04.mine;

public class Account {
    private Allocator allocator = Allocator.getInstance();
    private int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    void transfer(Account to, int num) {
        while (allocator.add(this, to)) ;
        try {
            synchronized (this) {
                Thread.sleep(200);
                System.out.println("got this");
                synchronized (to) {
                    System.out.println("got to");
                    if (this.balance >= num) {
                        this.balance -= num;
                        to.balance += num;
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            allocator.clean(this, to);
        }
    }

    public static void main(String[] args) {
        Account a = new Account(10);
        Account b = new Account(5);
        System.out.println("a.balance " + a.balance);
        System.out.println("b.balance " + b.balance);
        new Thread(()->{
            a.transfer(b,5);
        }).start();
        new Thread(()->{
            b.transfer(a,1);
        }).start();
        System.out.println("a.balance " + a.balance);
        System.out.println("b.balance " + b.balance);
    }

}
