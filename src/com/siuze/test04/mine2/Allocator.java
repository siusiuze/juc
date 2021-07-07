package com.siuze.test04.mine2;

import java.util.ArrayList;
import java.util.List;

public class Allocator {
    private static final List<Account> accountList = new ArrayList<>();

    synchronized public boolean add(Account from, Account to) {
        if (accountList.contains(from) || accountList.contains(to)) {
            return false;
        } else {
            accountList.add(from);
            accountList.add(to);
            return true;
        }
    }

    synchronized public void clean(Account from, Account to) {
        accountList.remove(from);
        accountList.remove(to);
    }

    public static Allocator getInstance() {
        return SingleInstance.Instance;
    }

    private static class SingleInstance {
        private static final Allocator Instance = new Allocator();
    }

}
