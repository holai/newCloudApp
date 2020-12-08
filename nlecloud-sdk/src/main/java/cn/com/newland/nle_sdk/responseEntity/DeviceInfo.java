package cn.com.newland.nle_sdk.responseEntity;

import java.util.List;

public class DeviceInfo {
    private String Name;
    private String Tag;
    private String Protocol;
    private String LastOnlineIP;
    private String LastOnlineTime;
    private String Coordinate;
    private String CreateDate;
    private String IsShare;
    private String IsTrans;
    private String DeviceID;
    private String ProjectID;
    private String IsOnline;
    private List<SensorInfo> Sensors;
    private String ProjectIdOrTag;
    private String DeviceImg;

    public DeviceInfo() {
    }

    /**
     * 设备信息
     * @param name  设备名称（中英文、数字的6到15个字）
     * @param tag 设备标识（英文、数字或其组合6到30个字符）
     * @param protocol 通讯协议（1:TCP 2:MQTT 3:HTTP）
     * @param coordinate 设备座标（可选，格式为经度值, 纬度值）
     * @param isShare 数据保密性，true | false（可选，默认为ture）
     * @param isTrans 数据上报状态，true | false（可选，默认为ture）
     * @param projectID 项目ID 添加设备时置空
     * @param isOnline 是否在线
     * @param ProjectIdOrTag 项目ID（一个数字）或标识码（一个32位字符串）
     * @param deviceImg 设备头像（可选）
     */
    public DeviceInfo(String name, String tag, String protocol, String coordinate, String isShare, String isTrans,
                      String projectID, String isOnline, String ProjectIdOrTag, String deviceImg) {
        Name = name;
        Tag = tag;
        Protocol = protocol;
        Coordinate = coordinate;
        IsShare = isShare;
        IsTrans = isTrans;
        ProjectID = projectID;
        IsOnline = isOnline;
        this.ProjectIdOrTag = ProjectIdOrTag;
        DeviceImg = deviceImg;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setTag(String tag) {
        Tag = tag;
    }

    public void setProtocol(String protocol) {
        Protocol = protocol;
    }

    public void setLastOnlineIP(String lastOnlineIP) {
        LastOnlineIP = lastOnlineIP;
    }

    public void setLastOnlineTime(String lastOnlineTime) {
        LastOnlineTime = lastOnlineTime;
    }

    public void setCoordinate(String coordinate) {
        Coordinate = coordinate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public void setIsShare(String isShare) {
        IsShare = isShare;
    }

    public void setIsTrans(String isTrans) {
        IsTrans = isTrans;
    }

    public void setDeviceID(String deviceID) {
        DeviceID = deviceID;
    }

    public void setProjectID(String projectID) {
        ProjectID = projectID;
    }

    public void setIsOnline(String isOnline) {
        IsOnline = isOnline;
    }

    public void setSensors(List<SensorInfo> sensors) {
        Sensors = sensors;
    }

    public void setProjectIdOrTag(String projectIdOrTag) {
        ProjectIdOrTag = projectIdOrTag;
    }

    public void setDeviceImg(String deviceImg) {
        DeviceImg = deviceImg;
    }

    public String getName() {

        return Name;
    }

    public String getTag() {
        return Tag;
    }

    public String getProtocol() {
        return Protocol;
    }

    public String getLastOnlineIP() {
        return LastOnlineIP;
    }

    public String getLastOnlineTime() {
        return LastOnlineTime;
    }

    public String getCoordinate() {
        return Coordinate;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public String getIsShare() {
        return IsShare;
    }

    public String getIsTrans() {
        return IsTrans;
    }

    public String getDeviceID() {
        return DeviceID;
    }

    public String getProjectID() {
        return ProjectID;
    }

    public String getIsOnline() {
        return IsOnline;
    }

    public List<SensorInfo> getSensors() {
        return Sensors;
    }

    public String getProjectIdOrTag() {
        return ProjectIdOrTag;
    }

    public String getDeviceImg() {
        return DeviceImg;
    }
}
