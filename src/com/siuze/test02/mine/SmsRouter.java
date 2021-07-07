package com.siuze.test02.mine;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SmsRouter {

    private static volatile SmsRouter instance = new SmsRouter();

    private final Map<String, SmsInfo> smsRouterMap;

    public SmsRouter() {
        this.smsRouterMap = this.loadSmsInfoRouteMapFromDb();
    }


    public static SmsRouter getInstance() {
        return instance;
    }

    public static void setInstance(SmsRouter newInstance) {
        instance = newInstance;
    }

    private Map<String, SmsInfo> loadSmsInfoRouteMapFromDb() {
        // 初始化 模拟db的数据
        Map<String, SmsInfo> routeMap = new HashMap<>();
        routeMap.put("180", new SmsInfo("001", "http://www.baidu.com", 180L));
        routeMap.put("181", new SmsInfo("002", "http://www.biying.com", 181L));
        routeMap.put("182", new SmsInfo("003", "http://www.google.com", 182L));
        return routeMap;
    }


    public Map<String, SmsInfo> getSmsRouterMap() {
        return Collections.unmodifiableMap(deepCopy(smsRouterMap));
    }

    private Map<String, SmsInfo> deepCopy(Map<String, SmsInfo> map) {
        Map<String, SmsInfo> newMap = new HashMap<>();
/*        for (String key : map.keySet()) {
            newMap.put(key,map.get(key));
        }*/
        for (String key : map.keySet()) {
            newMap.put(key, new SmsInfo(map.get(key)));
        }
        return newMap;
    }
}
