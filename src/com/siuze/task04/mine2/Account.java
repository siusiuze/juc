package com.siuze.task04.mine2;

public class Account {
    private int balance;

    private final Allocator allocator = Allocator.getInstance();

    public void transfer(Account target, int amt) {
        while (!allocator.add(this, target)) ;
        try {
            synchronized (this) {
                System.out.println("got this");
                Thread.sleep(200);
                synchronized (target) {
                    System.out.println("got target");
                    if (this.balance >= amt) {
                        this.balance -= amt;
                        target.balance += amt;
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            allocator.clean(this, target);
        }
    }

    public Account(int balance) {
        this.balance = balance;
    }

    public static void main(String[] args) {
        Account a = new Account(10);
        Account b = new Account(3);
        System.out.println("before transfer a:" + a.balance + " b:" + b.balance );
        new Thread(()->{
            a.transfer(b,5);
            System.out.println("thread1 transfer a:" + a.balance + " b:" + b.balance );
        }).start();

        new Thread(()->{
            b.transfer(a,7);
            System.out.println("thread2 transfer a:" + a.balance + " b:" + b.balance );
        }).start();
        System.out.println("main thread transfer a:" + a.balance + " b:" + b.balance );

        System.out.println();
    }

}
