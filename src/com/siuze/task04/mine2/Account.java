package com.siuze.task04.mine2;

public class Account {
    private int id;
    private int balance;

    void transfer(Account target, int amt) {
        Account left = this;
        Account right = target;
        if (this.id > target.id) {
            left = target;
            right = this;
        }
        synchronized (left) {
            synchronized (right) {
                if (this.balance > amt) {
                    this.balance -= amt;
                    target.balance += amt;
                }
            }
        }
    }
}