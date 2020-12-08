package cn.com.newland.nle_sdk.util;

import java.util.List;

import cn.com.newland.nle_sdk.requestEntity.DeviceData;
import cn.com.newland.nle_sdk.requestEntity.DeviceElement;
import cn.com.newland.nle_sdk.requestEntity.SignIn;
import cn.com.newland.nle_sdk.responseEntity.DeviceDatas;
import cn.com.newland.nle_sdk.responseEntity.DeviceInfo;
import cn.com.newland.nle_sdk.responseEntity.DeviceState;
import cn.com.newland.nle_sdk.responseEntity.ProjectInfo;
import cn.com.newland.nle_sdk.responseEntity.SensorDataInfoDTO;
import cn.com.newland.nle_sdk.responseEntity.SensorDataPageDTO;
import cn.com.newland.nle_sdk.responseEntity.SensorInfo;
import cn.com.newland.nle_sdk.responseEntity.User;
import cn.com.newland.nle_sdk.responseEntity.base.BasePager;
import cn.com.newland.nle_sdk.responseEntity.base.BaseResponseEntity;

/**
 * Created by marco on 2017/8/21.
 * api调用逻辑
 */

public class NetWorkBusiness {
    private ApiService apiService;
    private String accessToken;

    public NetWorkBusiness(String accessToken, String baseUrl) {
        this.apiService = Tools.buildService(baseUrl);
        this.accessToken = accessToken;
    }

    /**
     * 登录
     *
     * @param signIn   登录信息
     * @param callback 回调
     */
    public void signIn(SignIn signIn, NCallBack<BaseResponseEntity<User>> callback) {
        apiService.signIn(signIn).enqueue(callback);
    }

    /**
     * 查询单个项目
     *
     * @param projectId 项目ID
     */
    public void getProject(String projectId, NCallBack<BaseResponseEntity<ProjectInfo>> callback) {
        apiService.getProject(projectId, accessToken).enqueue(callback);
    }

    /**
     * 模糊查询项目
     *
     * @param Keyword     关键字（可选，从id或name字段模糊匹配查询）
     * @param ProjectTag  项目标识码（可选，一个32位字符串）
     * @param NetWorkKind 联网方案 （可选，1：WIFI 2：以太网 3:蜂窝网络 4:蓝牙)
     * @param PageSize    指定每页要显示的数据个数，默认20，最多100
     * @param StartDate   起始时间（可选，包括当天，格式YYYY-MM-DD）
     * @param EndDate     结束时间（可选，包括当天，格式YYYY-MM-DD）
     * @param PageIndex   指定页码
     */
    public void getProjects(String Keyword, String ProjectTag, String NetWorkKind, String PageSize, String StartDate, String EndDate, String PageIndex,
                            NCallBack<BaseResponseEntity<BasePager<ProjectInfo>>> callback) {
        apiService.getProjects(Keyword, ProjectTag, NetWorkKind, PageSize, StartDate, EndDate, PageIndex, accessToken).enqueue(callback);
    }

    /**
     * 查询项目所有设备的传感器
     *
     * @param projectId 项目ID
     */
    public void getAllSensors(String projectId, NCallBack<BaseResponseEntity<List<SensorInfo>>> callback) {
        apiService.getAllSensors(projectId, accessToken).enqueue(callback);
    }

    /**
     * 批量查询设备最新数据
     *
     * @param deviceIds 设备ID用逗号隔开, 限制100个设备
     */
    public void getDevicesDatas(String deviceIds, NCallBack<BaseResponseEntity<List<DeviceDatas>>> callback) {
        apiService.getDevicesDatas(deviceIds, accessToken).enqueue(callback);
    }

    /**
     * 批量查询设备的在线状态
     *
     * @param deviceIds 设备ID用逗号隔开, 限制100个设备
     */
    public void getBatchOnLine(String deviceIds, NCallBack<BaseResponseEntity<List<DeviceState>>> callback) {
        apiService.getBatchOnLine(deviceIds, accessToken).enqueue(callback);

    }

    /**
     * 查询单个设备
     *
     * @param deviceId 设备ID
     */
    public void getDeviceInfo(String deviceId, NCallBack<BaseResponseEntity<DeviceInfo>> callback) {
        apiService.getDeviceInfo(deviceId, accessToken).enqueue(callback);
    }

    /**
     * 模糊查询设备
     *
     * @param Keyword        关键字（可选，从id或name字段左匹配）
     * @param DeviceIds      指定设备ID（可选，如“124,34423,2345”，多个用逗号分隔，最多100个）
     * @param Tag            设备标识（可选）
     * @param IsOnline       在线状态（可选，true|false）
     * @param IsShare        数据保密性（可选，true|false）
     * @param ProjectKeyWord 项目ID或纯32位字符的项目标识码（可选）
     * @param PageSize       指定每页要显示的数据个数，默认20，最多100
     * @param StartDate      起始时间（可选，包括当天，格式YYYY-MM-DD）
     * @param EndDate        结束时间（可选，包括当天，格式YYYY-MM-DD）
     * @param PageIndex      指定页码
     */
    public void getDeviceFuzzy(String Keyword, String DeviceIds, String Tag, String IsOnline, String IsShare, String ProjectKeyWord, String PageSize, String StartDate, String
            EndDate, String PageIndex, NCallBack<BaseResponseEntity<BasePager<DeviceInfo>>> callback) {
        apiService.getDeviceFuzzy(Keyword, DeviceIds, Tag, IsOnline, IsShare, ProjectKeyWord, PageSize, StartDate, EndDate, PageIndex, accessToken).enqueue(callback);
    }

    /**
     * 添加1个新设备
     *
     * @param deviceInfo DeviceInfo
     */
    public void postAddDevice(DeviceInfo deviceInfo, NCallBack<BaseResponseEntity> callback) {
        apiService.postAddDevice(deviceInfo, accessToken).enqueue(callback);
    }

    /**
     * 更新某个设备
     *
     * @param deviceId   设备ID
     * @param deviceInfo deviceInfo
     */
    public void updateDevice(String deviceId, DeviceInfo deviceInfo, NCallBack<BaseResponseEntity> callback) {
        apiService.updateDevice(deviceId, deviceInfo, accessToken).enqueue(callback);
    }

    /**
     * 删除设备
     *
     * @param deviceId 设备id
     */
    public void deleteDevice(String deviceId, NCallBack<BaseResponseEntity> callback) {
        apiService.deleteDevice(deviceId, accessToken).enqueue(callback);
    }

    /**
     * 查询单个传感器
     *
     * @param deviceId 设备id
     * @param apiTag   apiTag
     */
    public void getSensor(String deviceId, String apiTag, NCallBack<BaseResponseEntity<SensorInfo>> callback) {
        apiService.getSensor(deviceId, apiTag, accessToken).enqueue(callback);
    }

    /**
     * 模糊查询传感器
     *
     * @param deviceId 设备ID（必须）
     * @param apiTags  传感标识名（必须），多个标识名之间用逗号分开（参数值为空时查询所有传感器）
     */
    public void getSensors(String deviceId, String apiTags, NCallBack<BaseResponseEntity<List<SensorInfo>>> callback) {
        apiService.getSensors(deviceId, apiTags, accessToken).enqueue(callback);
    }

    /**
     * 添加1个新传感器
     *
     * @param deviceId      设备ID
     * @param deviceElement deviceElement
     */
    public void addSensor(String deviceId, DeviceElement deviceElement, NCallBack<BaseResponseEntity> callback) {
        apiService.addSensor(deviceId, deviceElement, accessToken).enqueue(callback);
    }

    /**
     * 更新某个传感器
     *
     * @param deviceId      设备ID
     * @param apiTag        传感器标识名
     * @param deviceElement deviceElement
     */
    public void updateSensor(String deviceId, String apiTag, DeviceElement.SensorDeviceElement deviceElement, NCallBack<BaseResponseEntity> callback) {
        apiService.updateSensor(deviceId, apiTag, deviceElement, accessToken).enqueue(callback);
    }

    /**
     * 更新某个执行器
     *
     * @param deviceId      设备ID
     * @param apiTag        apiTag
     * @param deviceElement deviceElement
     */
    public void updateActuator(String deviceId, String apiTag, DeviceElement.ActuatorDeviceElement deviceElement, NCallBack<BaseResponseEntity> callback) {
        apiService.updateSensor(deviceId, apiTag, deviceElement, accessToken).enqueue(callback);
    }

    /**
     * 更新某个摄像头
     *
     * @param deviceId      设备ID
     * @param apiTag        apiTag
     * @param deviceElement deviceElement
     */
    public void updateCamera(String deviceId, String apiTag, DeviceElement.CameraDeviceElement deviceElement, NCallBack<BaseResponseEntity> callback) {
        apiService.updateSensor(deviceId, apiTag, deviceElement, accessToken).enqueue(callback);
    }

    /**
     * 删除某个传感器、执行器、摄像头
     *
     * @param deviceId 设备ID
     * @param apiTag   apiTag
     */
    public void deleteDeviceElement(String deviceId, String apiTag, NCallBack<BaseResponseEntity> callback) {
        apiService.deleteDeviceElement(deviceId, apiTag, accessToken).enqueue(callback);
    }

    /**
     * 新增传感数据
     *
     * @param deviceId 设备ID
     * @param datasDTO dataDto
     */
    public void addSensorData(String deviceId, DeviceData datasDTO, NCallBack<BaseResponseEntity> callback) {
        apiService.addSensorData(deviceId, datasDTO, accessToken).enqueue(callback);
    }

    /**
     * 聚合查询传感数据
     *
     * @param deviceId  设备ID
     * @param ApiTags   传感标识名（可选，多个用逗号分隔，最多50个）
     * @param groupBy   聚合方式（1：按分钟分组聚合 2：按小时分组聚合 3：按天分组聚合 4：按月分组聚合），默认2按小时聚合
     * @param Func      聚合函数（与GroupBy配对使用，可以是MAX：按最大值聚合 MIN：按最小值聚合 COUNT：按统计条数聚合），默认MAX最大值聚合
     * @param StartDate 起始时间（必填，格式YYYY-MM-DD HH:mm:ss）
     * @param EndDate   结束时间（可选，格式YYYY-MM-DD HH:mm:ss）默认取当前时间
     */
    public void getSensorDataGrouping(String deviceId, String ApiTags, String groupBy, String Func, String StartDate, String EndDate,
                                      NCallBack<BaseResponseEntity<SensorDataInfoDTO>> callback) {
        apiService.getSensorDataGrouping(deviceId, ApiTags, groupBy, Func, StartDate, EndDate, accessToken).enqueue(callback);
    }

    /**
     * 查询传感数据
     *
     * @param deviceId  设备ID
     * @param ApiTags   传感标识名（可选，多个用逗号分隔，最多50个）
     * @param Method    查询方式（1：XX分钟内 2：XX小时内 3：XX天内 4：XX周内 5：XX月内 6：按startDate与endDate指定日期查询）
     * @param TimeAgo   与Method一起配对使用（当Method=1~5时），表示以现在起"多长时间范围内"的数据，例：(Method=2,TimeAgo=30)表示现在起30小时内的历史数据
     * @param StartDate 起始时间（可选，格式YYYY-MM-DD HH:mm:ss）
     * @param EndDate   结束时间（可选，格式YYYY-MM-DD HH:mm:ss）
     * @param Sort      时间排序方式，DESC:倒序，ASC升序
     * @param PageSize  指定每次要请求的数据条数，默认20，最多3000
     * @param PageIndex 指定页码
     */
    public void getSensorData(String deviceId, String ApiTags, String Method, String TimeAgo, String StartDate, String EndDate, String Sort, String PageSize, String PageIndex,
                              NCallBack<BaseResponseEntity<SensorDataPageDTO>> callback) {
        apiService.getSensorData(deviceId, ApiTags, Method, TimeAgo, StartDate, EndDate, Sort, PageSize, PageIndex, accessToken).enqueue(callback);
    }

    /**
     * 发送命令/控制设备
     *
     * @param deviceId 设备ID（必填）
     * @param apiTag   传感标识名（必须但值可为空）
     * @param data     开关类：开=1，关=0，暂停=2
     *                 家居类：调光灯亮度=0~254，RGB灯色度=2~239，窗帘、卷闸门、幕布打开百分比=3%~100%，红外指令=1(on)2(off)
     *                 其它：integer/float/Json/String类型值
     */
    public void control(String deviceId, String apiTag, Object data, NCallBack<BaseResponseEntity> callback) {
        apiService.control(deviceId, apiTag, data, accessToken).enqueue(callback);
    }


}
