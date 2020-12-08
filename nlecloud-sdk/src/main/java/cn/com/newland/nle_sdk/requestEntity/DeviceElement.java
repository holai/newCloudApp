package cn.com.newland.nle_sdk.requestEntity;

public abstract class DeviceElement {
    private String Name;
    private String TransType;
    private String DataType;
    private String TypeAttrs;
    private String ApiTag;

    /**
     * @param apiTag    标识名（英文、数字与下划线，须以英文字母开头，设备内唯一）
     * @param name      名称（中英文、数字或下划线的2到10个字符）
     * @param transType 传输类型（可选，0：只上报1：上报和下发2：报警3：故障，默认0）
     * @param dataType  数据类型（可选，0：整数型1：浮点型2：布尔型3：字符型4：枚举型5：二进制型，默认0）
     * @param typeAttrs 传输类型与数据类型的属性（可选，如枚举型值以半角逗号分隔：可爱，有在，装备，蜗牛）
     */
    DeviceElement(String apiTag, String name, String transType, String dataType, String typeAttrs) {
        ApiTag = apiTag;
        Name = name;
        TransType = transType;
        DataType = dataType;
        TypeAttrs = typeAttrs;
    }

    public static class SensorDeviceElement extends DeviceElement {
        private String Unit;
        private String Precision;

        /**
         * 传感器设备
         *
         * @param apiTag    标识名（英文、数字与下划线，须以英文字母开头，设备内唯一）
         * @param name      名称（中英文、数字或下划线的2到10个字符）
         * @param transType 传输类型（可选，0：只上报1：上报和下发2：报警3：故障，默认0）
         * @param dataType  数据类型（可选，0：整数型1：浮点型2：布尔型3：字符型4：枚举型5：二进制型，默认0）
         * @param typeAttrs 传输类型与数据类型的属性（可选，如枚举型值以半角逗号分隔：可爱，有在，装备，蜗牛）
         * @param unit      单位（可选，定义传感器的单位）
         * @param precision 精度（可选，默认保留两位小数）
         */
        public SensorDeviceElement(String apiTag, String name, String transType, String dataType, String typeAttrs, String unit, String precision) {
            super(apiTag, name, transType, dataType, typeAttrs);
            Unit = unit;
            Precision = precision;
        }
    }

    public static class ActuatorDeviceElement extends DeviceElement {
        private String OperType;
        private String OperTypeAttrs;
        private String SerialNumber;

        /**
         * 执行器设备
         *
         * @param apiTag        标识名（英文、数字与下划线，须以英文字母开头，设备内唯一）
         * @param name          名称（中英文、数字或下划线的2到10个字符）
         * @param transType     传输类型（可选，0：只上报1：上报和下发2：报警3：故障，默认0）
         * @param dataType      数据类型（可选，0：整数型1：浮点型2：布尔型3：字符型4：枚举型5：二进制型，默认0）
         * @param typeAttrs     传输类型与数据类型的属性（可选，如枚举型值以半角逗号分隔：可爱，有在，装备，蜗牛）
         * @param operType      操作类型（1：开关型 2：开关停型 3：按钮型 4：刻度型）
         * @param operTypeAttrs 操作类型的附加属性（JSON格式，如刻度型时定义：{"MaxRange" : 180 ,"MinRange" : 0, "Step" : 10}）
         * @param serialNumber  序列号（可选，同一类型的多个以此区别，默认0）
         */
        public ActuatorDeviceElement(String apiTag, String name, String transType, String dataType, String typeAttrs, String operType, String operTypeAttrs, String serialNumber) {
            super(apiTag, name, transType, dataType, typeAttrs);
            OperType = operType;
            OperTypeAttrs = operTypeAttrs;
            SerialNumber = serialNumber;
        }
    }

    public static class CameraDeviceElement extends DeviceElement {
        private String HttpIp;
        private String HttpPort;
        private String UserName;
        private String Password;
        private String VideoStreamUrl;
        private String VideoStreamProtocol;
        private String VideoStreamPort;
        private String CtrlUrl;

        /**
         * 摄像头设备
         *
         * @param apiTag    标识名（英文、数字与下划线，须以英文字母开头，设备内唯一）
         * @param name      名称（中英文、数字或下划线的2到10个字符）
         * @param transType 传输类型（可选，0：只上报1：上报和下发2：报警3：故障，默认0）
         * @param dataType  数据类型（可选，0：整数型1：浮点型2：布尔型3：字符型4：枚举型5：二进制型，默认0）
         * @param typeAttrs 传输类型与数据类型的属性（可选，如枚举型值以半角逗号分隔：可爱，有在，装备，蜗牛）
         * @param httpIp    IP地址
         * @param httpPort  端口（定义视频流或控制流的端口，默认80）
         * @param userName  登录用户名
         * @param password  登录密码
         */
        public CameraDeviceElement(String apiTag, String name, String transType, String dataType, String typeAttrs, String httpIp, String httpPort, String userName, String password) {
            super(apiTag, name, transType, dataType, typeAttrs);
            HttpIp = httpIp;
            HttpPort = httpPort;
            UserName = userName;
            Password = password;
        }

        /**
         * 摄像头设备
         *
         * @param apiTag              标识名（英文、数字与下划线，须以英文字母开头，设备内唯一）
         * @param name                名称（中英文、数字或下划线的2到10个字符）
         * @param transType           传输类型（可选，0：只上报1：上报和下发2：报警3：故障，默认0）
         * @param dataType            数据类型（可选，0：整数型1：浮点型2：布尔型3：字符型4：枚举型5：二进制型，默认0）
         * @param typeAttrs           传输类型与数据类型的属性（可选，如枚举型值以半角逗号分隔：可爱，有在，装备，蜗牛）
         * @param httpIp              IP地址
         * @param httpPort            端口（定义视频流或控制流的端口，默认80）
         * @param userName            登录用户名
         * @param password            登录密码
         * @param videoStreamUrl      视频流路径（一般是以“/”开头的一串字符串路径，每产家每款摄像头一般不一样）
         * @param videoStreamProtocol 视频流协议（可选，值可以是http/rtsp/rtp/rtcp）
         * @param videoStreamPort     视频流端口（可选，若视频流端口与HttpPort字段不一致时可定义）
         * @param ctrlUrl             控制流路径（一般是以“/”开头的一串字符串路径，每产家每款摄像头一般不一样）
         */
        public CameraDeviceElement(String apiTag, String name, String transType, String dataType, String typeAttrs, String httpIp, String httpPort, String userName, String password, String videoStreamUrl,
                                   String videoStreamProtocol, String videoStreamPort, String ctrlUrl) {
            super(apiTag, name, transType, dataType, typeAttrs);
            HttpIp = httpIp;
            HttpPort = httpPort;
            UserName = userName;
            Password = password;
            VideoStreamUrl = videoStreamUrl;
            VideoStreamProtocol = videoStreamProtocol;
            VideoStreamPort = videoStreamPort;
            CtrlUrl = ctrlUrl;
        }
    }
}