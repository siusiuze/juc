package com.siuze.test03;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MyTest01 {

    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();

        list.add(1 + "");

    }
}
