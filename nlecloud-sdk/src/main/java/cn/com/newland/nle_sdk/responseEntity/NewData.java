package cn.com.newland.nle_sdk.responseEntity;

/**
 * Created by marco on 2017/8/22.
 * 某个网关的所有传感器、执行器最新值
 */

public class NewData {
    private String ApiTag;
    private String Name;
    private String Value;
    private int GatewayDeviceID;

    public String getApiTag() {
        return ApiTag;
    }

    public void setApiTag(String apiTag) {
        ApiTag = apiTag;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public int getGatewayDeviceID() {
        return GatewayDeviceID;
    }

    public void setGatewayDeviceID(int gatewayDeviceID) {
        GatewayDeviceID = gatewayDeviceID;
    }
}
