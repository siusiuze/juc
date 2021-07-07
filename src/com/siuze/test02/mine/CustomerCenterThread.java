package com.siuze.test02.mine;

public class CustomerCenterThread extends Thread {

    private boolean isChangeRouteInfo = false;

    public CustomerCenterThread(boolean isChangeRouteInfo) {
        this.isChangeRouteInfo = isChangeRouteInfo;
    }

    @Override
    public void run() {

        while (true) {
            if (isChangeRouteInfo) {
                SmsRouter.setInstance(new SmsRouter());

            }
        }
    }
}
