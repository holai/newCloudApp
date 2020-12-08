package com.example.newcloud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import cn.com.newland.nle_sdk.responseEntity.base.BaseResponseEntity;
import cn.com.newland.nle_sdk.util.NCallBack;
import cn.com.newland.nle_sdk.util.NetWorkBusiness;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OverviewActivity extends AppCompatActivity {
    TextView device_name,device_id,device_tag,device_protocol,device_isOnline;
    ImageView device_isOnline_img;
    JSONArray sensorList;
    MyAdapter myAdapter;
    ListView sensorListView;
    String deviceID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        Intent intent = getIntent();
        deviceID = intent.getStringExtra("deviceID");
        init();
        xx();
    }

    private void xx() {
        // http://api.nlecloud.com/Devices/87271
        String url = "http://api.nlecloud.com/Devices/"+deviceID;
        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .header("AccessToken",DataCache.getToken())
                .get()
                .build();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                qq(client,request);
            }
        },100,2000);

    }

    private void init() {

        device_name = findViewById(R.id.device_name);
        device_id = findViewById(R.id.device_id);
        device_isOnline = findViewById(R.id.device_isOnline);
        device_protocol = findViewById(R.id.device_protocol);
        device_tag = findViewById(R.id.device_tag);
        device_isOnline_img = findViewById(R.id.device_isOnline_img);
        device_id.setText(deviceID);
        sensorListView=findViewById(R.id.sensor_list);

    }


    private void qq(final OkHttpClient client, final Request request){
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(getApplicationContext(),"请求失败："+e,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(final Call call, Response response){
                try {
                    String res = response.body().string();
                    final JSONObject jsonObject = new JSONObject(res);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            switch (jsonObject.optInt("Status",-1)){
                                case 0:
                                    JSONObject data = jsonObject.optJSONObject("ResultObj");

                                    device_name.setText(data.optString("Name","null"));
                                    device_protocol.setText(data.optString("Protocol","null"));
                                    device_tag.setText(data.optString("Tag","null"));
                                    if(data.optBoolean("IsOnline",false)){
                                        device_isOnline.setText("在线");
                                        device_isOnline_img.setImageResource(R.drawable.lamp_on);
                                    }else {
                                        device_isOnline.setText("否");
                                        device_isOnline_img.setImageResource(R.drawable.lamp_off);
                                    }

                                    sensorList = data.optJSONArray("Sensors");
                                    if(myAdapter==null){
                                        myAdapter = new MyAdapter(sensorList.length(), new MyCall() {
                                            @Override
                                            View call(int i, View view) {
                                                final JSONObject data = sensorList.optJSONObject(i);
                                                final SensorHolder sensorHolder;
                                                if(view == null){
                                                    view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.sensor_item,null);
                                                    sensorHolder = new SensorHolder();
                                                    sensorHolder.sensor_name = view.findViewById(R.id.sensor_name);
                                                    sensorHolder.sensor_control = view.findViewById(R.id.sensor_control);
                                                    sensorHolder.sensor_recordTime = view.findViewById(R.id.sensor_recordTime);
                                                    sensorHolder.sensor_tag = view.findViewById(R.id.sensor_tag);
                                                    sensorHolder.sensor_value = view.findViewById(R.id.sensor_value);
                                                    view.setTag(sensorHolder);
                                                }else {
                                                    sensorHolder = (SensorHolder) view.getTag();
                                                }
                                                sensorHolder.sensor_name.setText(data.optString("Name","Null"));
                                                sensorHolder.sensor_tag.setText(data.optString("ApiTag","Null"));
                                                sensorHolder.sensor_recordTime.setText(data.optString("RecordTime","Null"));
                                                switch (data.optInt("Groups")){
                                                    case 1:
                                                        //传感器
                                                        if(data.optInt("DataType")==1){
                                                            String value = data.optString("Value","0")+data.optString("Unit","");
                                                            sensorHolder.sensor_value.setText(value);
                                                        }else{
                                                            sensorHolder.sensor_value.setText(data.optInt("Value",0)==0 ? "有人" : "无人" );
                                                        }
                                                        sensorHolder.sensor_value.setVisibility(View.VISIBLE);
                                                        sensorHolder.sensor_control.setVisibility(View.GONE);
                                                        break;
                                                    case 2:
                                                        // 执行器
                                                        sensorHolder.sensor_value.setVisibility(View.GONE);
                                                        sensorHolder.sensor_control.setVisibility(View.VISIBLE);
                                                        sensorHolder.sensor_control.setChecked(data.optBoolean("Value",false));
                                                        sensorHolder.sensor_control.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View view) {
                                                                Log.e("ddd", "onClick: "+data.optString("ApiTag"));
                                                                NetWorkBusiness netWorkBusiness = new NetWorkBusiness(DataCache.getToken(),DataCache.getBaseurl());
                                                                netWorkBusiness.control(deviceID, data.optString("ApiTag"), ((Switch) view).isChecked() ? 1 : 0, new NCallBack<BaseResponseEntity>(getApplicationContext()) {
                                                                    @Override
                                                                    protected void onResponse(BaseResponseEntity baseResponseEntity) {
                                                                        Log.e("ddd", "onResponse: "+baseResponseEntity.getStatus() );
                                                                        if(baseResponseEntity.getStatus()==0){
                                                                            qq(client,request);
                                                                        }
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        break;
                                                }
                                                return view;
                                            }
                                        });
                                        sensorListView.setAdapter(myAdapter);
                                    }else{
                                        myAdapter.notifyDataSetChanged();
                                    }
                                    break;
                                case 1:
                                    //失败
                                    Toast.makeText(getApplicationContext(),jsonObject.optString("Msg","未知错误！"),Toast.LENGTH_SHORT).show();
                                    break;
                                case 2:
                                    //异常
                                    Toast.makeText(getApplicationContext(),"出现异常！",Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    Toast.makeText(getApplicationContext(),"出现未知错误！",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
