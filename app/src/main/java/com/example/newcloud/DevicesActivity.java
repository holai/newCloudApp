package com.example.newcloud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cn.com.newland.nle_sdk.responseEntity.DeviceInfo;
import cn.com.newland.nle_sdk.responseEntity.ProjectInfo;
import cn.com.newland.nle_sdk.responseEntity.base.BasePager;
import cn.com.newland.nle_sdk.responseEntity.base.BaseResponseEntity;
import cn.com.newland.nle_sdk.util.NCallBack;
import cn.com.newland.nle_sdk.util.NetWorkBusiness;

public class DevicesActivity extends AppCompatActivity {
    ArrayList<DeviceInfo> arrayList;
    MyAdapter myAdapter;
    ListView DevicesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices);
        init();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
    }

    private void init() {
        DevicesList = findViewById(R.id.DevicesList);
        final Intent intent = getIntent();
        int projectID = intent.getIntExtra("projectID",1);
        String projectName = intent.getStringExtra("projectName");
        setTitle(projectName);
        NetWorkBusiness netWorkBusiness = new NetWorkBusiness(DataCache.getToken(),DataCache.getBaseurl());
        netWorkBusiness.getDeviceFuzzy("", "", "", "", "", String.valueOf(projectID), "", "", "", "", new NCallBack<BaseResponseEntity<BasePager<DeviceInfo>>>(getApplicationContext()) {
            @Override
            protected void onResponse(BaseResponseEntity<BasePager<DeviceInfo>> response) {
                switch (response.getStatus()){
                    case 0:
                        //成功
                        try {
                            Gson gson = new Gson();
                            JSONObject jsonObject = new JSONObject(gson.toJson(response.getResultObj()));
                            JSONArray jsonArray = jsonObject.getJSONArray("PageSet");
                            arrayList = new ArrayList<>();
                            for (int i=0;i<jsonArray.length();i++){
                                arrayList.add(gson.fromJson(jsonArray.getJSONObject(i).toString(),DeviceInfo.class));
                            }
                            myAdapter = new MyAdapter(arrayList.size(), new MyCall() {
                                @Override
                                View call(int i, View view) {
                                    DeviceInfo deviceInfo = arrayList.get(i);
                                    DeviceHolder holder;
                                    if(view == null){
                                        view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.device_itme,null);
                                        holder = new DeviceHolder();
                                        holder.Name = view.findViewById(R.id.devicesName);
                                        holder.CreateDate = view.findViewById(R.id.devicesCreateDate);
                                        holder.DeviceID = view.findViewById(R.id.devicesID);
                                        holder.IsOnline = view.findViewById(R.id.devicesIsOnline);
                                        holder.Protocol = view.findViewById(R.id.devicesProtocol);
                                        holder.LastOnlineIP = view.findViewById(R.id.LastOnlineIP);
                                        holder.Tag = view.findViewById(R.id.devicesTag);
                                        view.setTag(holder);
                                    }else {
                                        holder = (DeviceHolder) view.getTag();
                                    }
                                    holder.Tag.setText(deviceInfo.getTag());
                                    holder.Protocol.setText(deviceInfo.getProtocol());
                                    holder.IsOnline.setText(deviceInfo.getIsOnline());
                                    holder.DeviceID.setText(deviceInfo.getDeviceID());
                                    holder.CreateDate.setText(deviceInfo.getCreateDate());
                                    holder.Name.setText(deviceInfo.getName());
                                    holder.LastOnlineIP.setText(deviceInfo.getLastOnlineIP());
                                    return view;
                                }
                            });
                            DevicesList.setAdapter(myAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        break;
                    case 1:
                        //失败
                        Toast.makeText(getApplicationContext(),response.getMsg(),Toast.LENGTH_SHORT).show();
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

        //列表点击
        DevicesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1 = new Intent(getApplicationContext(),OverviewActivity.class);
                intent1.putExtra("deviceID",arrayList.get(i).getDeviceID());
                startActivity(intent1);
            }
        });

    }

    //设置监听事件
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
