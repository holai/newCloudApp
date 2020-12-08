package cn.com.newland.nle_sdk.responseEntity;

/**
 * Created by marco on 2017/8/22.
 * 网关在线状态
 */

public class DeviceState {
    private String LastOnlineIP;
    private int DeviceID;
    private String Name;
    private boolean IsOnline;

    public void setDeviceID(int deviceID) {
        DeviceID = deviceID;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setOnline(boolean online) {
        IsOnline = online;
    }

    public void setLastOnlineIP(String lastOnlineIP) {

        LastOnlineIP = lastOnlineIP;
    }

    public String getLastOnlineIP() {

        return LastOnlineIP;
    }

    public int getDeviceID() {
        return DeviceID;
    }

    public String getName() {
        return Name;
    }

    public boolean isOnline() {
        return IsOnline;
    }
}
