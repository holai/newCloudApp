package com.example.newcloud;

import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public abstract class MyCall {
    abstract View call(int i,View view);
}

class ProjectHolder {
    TextView project_name,project_industry,project_id,project_tag,project_date;
    ImageView project_kind;
}
// 设备列表项容器
class DeviceHolder{
    TextView DeviceID,Name,Tag,LastOnlineIP,Protocol,IsOnline,CreateDate;
}

class SensorHolder{
    TextView sensor_name,sensor_tag,sensor_recordTime,sensor_value;
    Switch sensor_control;
}