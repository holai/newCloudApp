package cn.com.newland.nle_sdk.requestEntity;

import java.util.List;

public class DeviceData {

    private List<DatasDTO> DatasDTO;

    /**
     * 设备数据结构
     *
     * @param datasDTO 数据DTO列表
     */
    public DeviceData(List<DeviceData.DatasDTO> datasDTO) {
        DatasDTO = datasDTO;
    }

    public static class DatasDTO {
        private String ApiTag;
        private List<PointDTO> pointDTO;

        /**
         * 数据DTO
         *
         * @param apiTag   apiTag
         * @param pointDTO PointDTO集合
         */
        public DatasDTO(String apiTag, List<PointDTO> pointDTO) {
            ApiTag = apiTag;
            this.pointDTO = pointDTO;
        }
    }

    public static class PointDTO {
        private Object Value;
        private String RecordTime;

        /**
         * 记录值
         *
         * @param value      具体的数据
         * @param recordTime 记录的时间
         */
        public PointDTO(Object value, String recordTime) {
            Value = value;
            RecordTime = recordTime;
        }


    }
}
