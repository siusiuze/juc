package com.siuze.test04.mine;

import java.util.ArrayList;
import java.util.List;

public class Allocator {
    private List<Account> accountList = new ArrayList<>();

    synchronized public boolean add(Account from, Account to) {
        if (accountList.contains(from) || accountList.contains(to)) {
            return false;
        } else {
            accountList.add(from);
            accountList.add(to);
            return true;
        }
    }

    synchronized void clean(Account from, Account to) {
        accountList.remove(from);
        accountList.remove(to);
    }


    private static class SingleTonHolder {
        private static Allocator Intance = new Allocator();
    }

    public static Allocator getInstance() {
        return SingleTonHolder.Intance;
    }

}
