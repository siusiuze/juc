package com.siuze.task01;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CarLocationTracker {

    private Map<String,Location> locationMap = new ConcurrentHashMap<>();

    public void updateLocation(String code,Location newLocation){
        locationMap.put(code,newLocation);
    }

    public Location getLocation(String code){
        return  locationMap.get(code);
    }

}
