package cn.com.newland.nle_sdk.responseEntity;

/**
 * Created by marco on 2017/8/22.
 * 网关对应的传感器列表item
 */

public class SensorInfo {
    private String ApiTag;
    private int Groups;
    private int Protocol;
    private String Name;
    private String CreateDate;
    private int TransType;
    private int DataType;
    private String TypeAttrs;
    private int DeviceID;
    private String SensorType;
    private String Value;
    private String RecordTime;
//传感器字段
    private String Unit;
    //执行器字段
    private long OperType;
//    private OperTypeAttrs OperTypeAttrs;
    private String OperTypeAttrs;

    //摄像头字段
    private String HttpIp;
    private long HttpPort;
    private String UserName;
    private String Password;
    private String VideoStreamUrl;
    private String VideoStreamProtocol;
    private String VideoStreamPort;
    private String CtrlUrl;

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public long getOperType() {
        return OperType;
    }

    public void setOperType(long operType) {
        OperType = operType;
    }

    public String getOperTypeAttrs() {
        return OperTypeAttrs;
    }

    public void setOperTypeAttrs(String operTypeAttrs) {
        OperTypeAttrs = operTypeAttrs;
    }

    public String getHttpIp() {
        return HttpIp;
    }

    public void setHttpIp(String httpIp) {
        HttpIp = httpIp;
    }

    public long getHttpPort() {
        return HttpPort;
    }

    public void setHttpPort(long httpPort) {
        HttpPort = httpPort;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getVideoStreamUrl() {
        return VideoStreamUrl;
    }

    public void setVideoStreamUrl(String videoStreamUrl) {
        VideoStreamUrl = videoStreamUrl;
    }

    public String getVideoStreamProtocol() {
        return VideoStreamProtocol;
    }

    public void setVideoStreamProtocol(String videoStreamProtocol) {
        VideoStreamProtocol = videoStreamProtocol;
    }

    public String getVideoStreamPort() {
        return VideoStreamPort;
    }

    public void setVideoStreamPort(String videoStreamPort) {
        VideoStreamPort = videoStreamPort;
    }

    public String getCtrlUrl() {
        return CtrlUrl;
    }

    public void setCtrlUrl(String ctrlUrl) {
        CtrlUrl = ctrlUrl;
    }

    public String getApiTag() {
        return ApiTag;
    }

    public void setApiTag(String apiTag) {
        ApiTag = apiTag;
    }

    public int getGroups() {
        return Groups;
    }

    public void setGroups(int groups) {
        Groups = groups;
    }

    public int getProtocol() {
        return Protocol;
    }

    public void setProtocol(int protocol) {
        Protocol = protocol;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public int getTransType() {
        return TransType;
    }

    public void setTransType(int transType) {
        TransType = transType;
    }

    public int getDataType() {
        return DataType;
    }

    public void setDataType(int dataType) {
        DataType = dataType;
    }

    public String getTypeAttrs() {
        return TypeAttrs;
    }

    public void setTypeAttrs(String typeAttrs) {
        TypeAttrs = typeAttrs;
    }

    public int getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(int deviceID) {
        DeviceID = deviceID;
    }

    public String getSensorType() {
        return SensorType;
    }

    public void setSensorType(String sensorType) {
        SensorType = sensorType;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getRecordTime() {
        return RecordTime;
    }

    public void setRecordTime(String recordTime) {
        RecordTime = recordTime;
    }
    /*public class  OperTypeAttrs{
        public long MaxRange;
        public  long MinRange;
        public long Step;
    }*/
}
