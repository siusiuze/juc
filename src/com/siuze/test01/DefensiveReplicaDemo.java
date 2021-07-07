package com.siuze.test01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DefensiveReplicaDemo {
    private final List<Integer> data = new ArrayList<>();

    private DefensiveReplicaDemo(){
        data.add(1);
        data.add(2);
        data.add(3);
    }

    public List<Integer> getData(){
        return data;
    }

    public List<Integer> newGetData(){
        return Collections.unmodifiableList(new ArrayList<>(data));
    }

    public static void main(String[] args) {
        DefensiveReplicaDemo defensiveReplicaDemo = new DefensiveReplicaDemo();
        List<Integer> data = defensiveReplicaDemo.getData();
        data.add(4);
//        List<Integer> integers = defensiveReplicaDemo.newGetData();
//        integers.add(5);

    }
}
