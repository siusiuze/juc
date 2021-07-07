package com.siuze.test02.mine;

public final  class SmsInfo {


    private final String deviceCode;

    private final String interfaceUrl;

    private final Long maxSizeInBytes;

    public SmsInfo(String deviceCode, String interfaceUrl, Long maxSizeInBytes) {
        this.deviceCode = deviceCode;
        this.interfaceUrl = interfaceUrl;
        this.maxSizeInBytes = maxSizeInBytes;
    }
    public SmsInfo(SmsInfo smsInfo){
        this.deviceCode = smsInfo.getDeviceCode();
        this.interfaceUrl = smsInfo.getInterfaceUrl();
        this.maxSizeInBytes = smsInfo.getMaxSizeInBytes();
    }


    public String getDeviceCode() {
        return deviceCode;
    }

    public String getInterfaceUrl() {
        return interfaceUrl;
    }

    public Long getMaxSizeInBytes() {
        return maxSizeInBytes;
    }
}
