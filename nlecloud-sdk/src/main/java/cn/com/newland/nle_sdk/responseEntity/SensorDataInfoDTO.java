package cn.com.newland.nle_sdk.responseEntity;

import java.util.List;

public class SensorDataInfoDTO {
    public int Count;
    public int DeviceId;
    public List<DataPoint> DataPoints;

    public class DataPoint {
        public String ApiTag;
        public List<VR> PointDTO;
    }

    public class VR {
        public String Value;
        private String RecordTime;
    }
}
