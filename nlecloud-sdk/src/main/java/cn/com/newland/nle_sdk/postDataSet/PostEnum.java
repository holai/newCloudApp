package cn.com.newland.nle_sdk.postDataSet;

/**
 * Created by marco on 2017/8/28.
 * 请求枚举
 */

public enum PostEnum {
    GET_PROJECT("Projects/{projectId}", "查询单个项目"),
    GET_PROJECTS("Projects","模糊查询项目"),
    GET_SENSORS("Projects/{projectId}/Sensors","查询项目所有设备的传感器"),

    GET_DEVICE_NEWS_DATA("Devices/Datas","批量查询设备最新数据"),
    GET_DEVICE_STATES("Devices/Status","批量查询设备的在线状态"),
    GET_DEVICE("Devices/{deviceId}","查询单个设备"),
    GET_DEVICES("Devices","模糊查询设备"),
    POST_ADD_DEVICE("Devices","添加1个新设备"),
    PUT_UPDATE_DEVICE("Devices/{deviceId}","更新某个设备"),
    DELETE_DEVICE("Devices/{deviceId}", "删除某个设备"),


    GET_SENSOR("{deviceId}/Sensors/{apiTag}", " 查询单个传感器"),
    GET_SENSORS_BY_DEVICE("devices/{deviceId}/Sensors", "模糊查询传感器"),
    POST_ADD_SENSOR("devices/{deviceId}/Sensors", "添加1个新传感器"),
    PUT_UPDATE_SENSOR("devices/{deviceId}/Sensors/{apiTag}", "更新某个传感器"),
    DELETE_SENSOR("devices/{deviceId}/Sensors/{apiTag}", "删除某个传感器"),


    POST_ADD_SENSOR_DATA("devices/{deviceId}/Datas", "新增传感数据"),
    GET_SENSOR_GROUPING("devices/{deviceId}/Datas/Grouping","聚合查询传感数据"),
    GET_SENSOR_DATA("devices/{deviceId}/Datas", "查询传感数据"),

    POST_CONTROL("Cmds", "发送命令/控制设备");
    private String url;
    private String title;

    PostEnum(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }
}
