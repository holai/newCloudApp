package cn.com.newland.nle_sdk.responseEntity;

import java.util.List;

public class DeviceDatas {
    private int DeviceID;
    private String Name;
    private List<NewData> Datas;

    public void setDeviceID(int deviceID) {
        DeviceID = deviceID;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDatas(List<NewData> datas) {
        Datas = datas;
    }
}
