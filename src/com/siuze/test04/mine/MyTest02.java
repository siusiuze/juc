package com.siuze.test04.mine;

import java.util.ArrayList;
import java.util.List;

public class MyTest02 {

    static class Account {
        private final Allocator allocator = getInstance();
        private int balance;

        void transfer(Account target, int num) {
            while (!allocator.apply(this, target)) ;
            try {
                synchronized (this) {
                    System.out.println("got from");
                    synchronized (target) {
                        System.out.println("got to");
                        if (this.balance > num) {
                            this.balance -= num;
                            target.balance += num;
                        }
                    }
                }
            } finally {
                allocator.clean(this, target);
            }
        }
    }

    static class Allocator {
        List<Object> als = new ArrayList<>();

        synchronized boolean apply(Object from, Object to) {
            if (als.contains(from) || als.contains(to)) {
                return false;
            } else {
                als.add(from);
                als.add(to);
                return true;
            }
        }

        synchronized void clean(Object from, Object to) {
            als.remove(from);
            als.remove(to);
        }
    }

    private static class SingleInstance {
        private static final Allocator instance = new Allocator();
    }

    public static Allocator getInstance() {
        return SingleInstance.instance;
    }

    public static void main(String[] args) {
        Account a = new Account();
        Account b = new Account();
        a.transfer(b, 100);
        b.transfer(a, 100);
    }
}
