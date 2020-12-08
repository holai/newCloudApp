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
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiService {
    @POST("Users/Login")
    Call<BaseResponseEntity<User>> signIn(@Body SignIn signIn);

    @GET("Projects/{projectId}")
    Call<BaseResponseEntity<ProjectInfo>> getProject(@Path("projectId") String projectId, @Header("AccessToken") String accessToken);


    @GET("Projects")
    Call<BaseResponseEntity<BasePager<ProjectInfo>>> getProjects(
            @Query("Keyword") String Keyword,
            @Query("ProjectTag") String ProjectTag,
            @Query("NetWorkKind") String NetWorkKind,
            @Query("PageSize") String PageSize,
            @Query("StartDate") String StartDate,
            @Query("EndDate") String EndDate,
            @Query("PageIndex") String PageIndex,
            @Header("AccessToken") String accessToken);

    @GET("Projects/{projectId}/Sensors")
    Call<BaseResponseEntity<List<SensorInfo>>> getAllSensors(@Path("projectId") String projectId, @Header("AccessToken") String accessToken);

    @GET("Devices/Datas")
    Call<BaseResponseEntity<List<DeviceDatas>>> getDevicesDatas(@Query("devIds") String devIds, @Header("AccessToken") String accessToken);

    @GET("Devices/Status")
    Call<BaseResponseEntity<List<DeviceState>>> getBatchOnLine(
            @Query("devIds") String devIds, @Header("AccessToken") String accessToken);

    @GET("Devices/{deviceId}")
    Call<BaseResponseEntity<DeviceInfo>> getDeviceInfo(@Path("deviceId") String deviceId, @Header("AccessToken") String accessToken);

    @GET("Devices")
    Call<BaseResponseEntity<BasePager<DeviceInfo>>> getDeviceFuzzy(
            @Query("Keyword") String Keyword,
            @Query("DeviceIds") String DeviceIds,
            @Query("Tag") String Tag,
            @Query("IsOnline") String IsOnline,
            @Query("IsShare") String IsShare,
            @Query("ProjectKeyWord") String ProjectKeyWord,
            @Query("PageSize") String PageSize,
            @Query("StartDate") String StartDate,
            @Query("EndDate") String EndDate,
            @Query("PageIndex") String PageIndex,
            @Header("AccessToken") String accessToken);

    @POST("Devices")
    Call<BaseResponseEntity> postAddDevice(
            @Body DeviceInfo deviceInfo,
            @Header("AccessToken") String accessToken);

    @PUT("Devices/{deviceId}")
    Call<BaseResponseEntity> updateDevice(
            @Path("deviceId") String deviceId,
            @Body DeviceInfo deviceInfo,
            @Header("AccessToken") String accessToken);

    @DELETE("Devices/{deviceId}")
    Call<BaseResponseEntity> deleteDevice(@Path("deviceId") String deviceId, @Header("AccessToken") String accessToken);

    @GET("devices/{deviceId}/Sensors/{apiTag}")
    Call<BaseResponseEntity<SensorInfo>> getSensor(
            @Path("deviceId") String deviceId, @Path("apiTag") String apiTag, @Header("AccessToken") String accessToken);

    @GET("devices/{deviceId}/Sensors")
    Call<BaseResponseEntity<List<SensorInfo>>> getSensors(@Path("deviceId") String deviceId, @Query("apiTags") String apiTags, @Header("AccessToken") String accessToken);

    @POST("devices/{deviceId}/Sensors")
    Call<BaseResponseEntity> addSensor(
            @Path("deviceId") String deviceId,
            @Body DeviceElement deviceElement,
            @Header("AccessToken") String accessToken);

    @PUT("devices/{deviceId}/Sensors/{apiTag}")
    Call<BaseResponseEntity> updateSensor(
            @Path("deviceId") String deviceId,
            @Path("apiTag") String apiTag,
            @Body DeviceElement.SensorDeviceElement deviceElement,
            @Header("AccessToken") String accessToken);

    @PUT("devices/{deviceId}/Sensors/{apiTag}")
    Call<BaseResponseEntity> updateSensor(
            @Path("deviceId") String deviceId,
            @Path("apiTag") String apiTag,
            @Body DeviceElement.ActuatorDeviceElement deviceElement,
            @Header("AccessToken") String accessToken);

    @PUT("devices/{deviceId}/Sensors/{apiTag}")
    Call<BaseResponseEntity> updateSensor(
            @Path("deviceId") String deviceId,
            @Path("apiTag") String apiTag,
            @Body DeviceElement.CameraDeviceElement deviceElement,
            @Header("AccessToken") String accessToken);

    @DELETE("devices/{deviceId}/Sensors/{apiTag}")
    Call<BaseResponseEntity> deleteDeviceElement(
            @Path("deviceId") String deviceId, @Path("apiTag") String apiTag, @Header("AccessToken") String accessToken);

    @POST("devices/{deviceId}/Datas")
    Call<BaseResponseEntity> addSensorData(
            @Path("deviceId") String deviceId,
            @Body DeviceData datasDTO,
            @Header("AccessToken") String accessToken);

    @GET("devices/{deviceId}/Datas/Grouping")
    Call<BaseResponseEntity<SensorDataInfoDTO>> getSensorDataGrouping(
            @Path("deviceId") String deviceId,
            @Query("ApiTags") String ApiTags,
            @Query("GroupBy") String groupBy,
            @Query("Func") String func,
            @Query("StartDate") String StartDate,
            @Query("EndDate") String EndDate,
            @Header("AccessToken") String accessToken);

    @GET("devices/{deviceId}/Datas")
    Call<BaseResponseEntity<SensorDataPageDTO>> getSensorData(
            @Path("deviceId") String deviceId,
            @Query("ApiTags") String ApiTags,
            @Query("Method") String Method,
            @Query("TimeAgo") String TimeAgo,
            @Query("StartDate") String StartDate,
            @Query("EndDate") String EndDate,
            @Query("Sort") String Sort,
            @Query("PageSize") String PageSize,
            @Query("PageIndex") String PageIndex,
            @Header("AccessToken") String accessToken);

    @POST("Cmds")
    Call<BaseResponseEntity> control(@Query("deviceId") String deviceId, @Query("apiTag") String apiTag, @Body Object data, @Header("AccessToken") String accessToken);

}
