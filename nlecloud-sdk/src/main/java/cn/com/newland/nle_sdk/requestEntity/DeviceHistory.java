package cn.com.newland.nle_sdk.requestEntity;

/**
 * Created by marco on 2017/8/22.
 * 设备历史数据请求体
 */

public class DeviceHistory {
    public String Method;
    public String TimeAgo;

    /**
     * @param method  1，2，3，4，5对应分钟，小时，天，周，月
     * @param timeAgo 前几
     */
    public DeviceHistory(String method, String timeAgo) {
        Method = method;
        TimeAgo = timeAgo;
    }
}
