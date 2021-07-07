package com.siuze.task04;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock03 {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();


    public static void main(String[] args) {
        Thread a = new Thread(new Lock1());
        Thread b = new Thread(new Lock2());
        a.start();
        b.start();
    }

    static class Lock1 implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("Lock1 running");
                while (true) {
                    if (lock1.tryLock(1, TimeUnit.MILLISECONDS)) {
                        System.out.println("Lock1 lock obj1");
                        //Thread.sleep(3000);
                        if (lock2.tryLock(1, TimeUnit.MILLISECONDS)) {
                            System.out.println("Lock1 lock obj2");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }
}
