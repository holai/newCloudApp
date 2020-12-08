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
import org.json.JSONObject;

import java.sql.Driver;
import java.util.ArrayList;

import cn.com.newland.nle_sdk.responseEntity.ProjectInfo;
import cn.com.newland.nle_sdk.responseEntity.User;
import cn.com.newland.nle_sdk.responseEntity.base.BasePager;
import cn.com.newland.nle_sdk.responseEntity.base.BaseResponseEntity;
import cn.com.newland.nle_sdk.util.NCallBack;
import cn.com.newland.nle_sdk.util.NetWorkBusiness;

public class ProjectsActivity extends AppCompatActivity {
    private User user;
    private Gson gson;
    ArrayList<ProjectInfo> arrayList;
    ListView projectList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        setTitle("项目管理");
        init();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用

    }

    private void init() {
        projectList = findViewById(R.id.projectList);
        final Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("result");
        DataCache.setToken(user.getAccessToken());
        NetWorkBusiness netWorkBusiness = new NetWorkBusiness(user.getAccessToken(),DataCache.getBaseurl());
        netWorkBusiness.getProjects("", "", "", "", "", "", "", new NCallBack<BaseResponseEntity<BasePager<ProjectInfo>>>(getApplicationContext()) {
            @Override
            protected void onResponse(BaseResponseEntity<BasePager<ProjectInfo>> response) {
                switch (response.getStatus()){
                    case 0:
                        //成功
                        try {
                            gson = new Gson();
                            JSONObject jsonObject = new JSONObject(gson.toJson(response.getResultObj()));
                            //[{"CreateDate":"2020-03-13 11:45:54","Industry":"工业物联","Name":"xx","NetWorkKind":"蜂窝网络(2G\/3G\/4G)","ProjectID":68939,"ProjectTag":"cdda8d2dfc20436cadc6beb2a69687cd","Remark":"DDDDDDD"},{"CreateDate":"2020-03-13 11:28:50","Industry":"智慧城市","Name":"仿真Test","NetWorkKind":"WIFI","ProjectID":68938,"ProjectTag":"82b9c3eb986f4d6e81f32d1087ff7a19","Remark":""}]
                            arrayList = new ArrayList<>();
                            JSONArray jsonArray =  jsonObject.getJSONArray("PageSet");
                            for(int i=0;i<jsonArray.length();i++){
                                ProjectInfo projectInfo= gson.fromJson(jsonArray.getJSONObject(i).toString(),ProjectInfo.class);
                                arrayList.add(projectInfo);
                            }

                            MyAdapter myAdapter = new MyAdapter(arrayList.size(), new MyCall() {
                                @Override
                                View call(int i, View view) {
                                    ProjectInfo projectInfo = arrayList.get(i);
                                    ProjectHolder holder;
                                    if(view == null){
                                        view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.project_item,null);
                                        holder = new ProjectHolder();
                                        holder.project_name = view.findViewById(R.id.project_name);
                                        holder.project_industry = view.findViewById(R.id.project_industry);
                                        holder.project_date = view.findViewById(R.id.project_date);
                                        holder.project_id = view.findViewById(R.id.project_id);
                                        holder.project_kind = view.findViewById(R.id.project_kind);
                                        holder.project_tag = view.findViewById(R.id.project_tag);
                                        view.setTag(holder);
                                    }else {
                                        holder = (ProjectHolder) view.getTag();
                                    }
                                    holder.project_name.setText(projectInfo.getName());
                                    holder.project_tag.setText(projectInfo.getProjectTag());
                                    holder.project_id.setText(String.valueOf(projectInfo.getProjectID()));
                                    holder.project_date.setText(projectInfo.getCreateDate());
                                    holder.project_industry.setText(projectInfo.getIndustry());
                                    switch (projectInfo.getNetWorkKind()){
                                        case "NB-IoT":
                                            holder.project_kind.setImageResource(R.mipmap.nbiot);
                                            break;
                                        case "蓝牙":
                                            holder.project_kind.setImageResource(R.mipmap.ble);
                                            break;
                                        case "以太网":
                                            holder.project_kind.setImageResource(R.mipmap.ethernet);
                                            break;
                                        case "蜂窝网络(2G/3G/4G)":
                                            holder.project_kind.setImageResource(R.mipmap.cellular);
                                            break;
                                        case "WIFI":
                                            holder.project_kind.setImageResource(R.mipmap.wifi);
                                            break;
                                    }
                                    return view;
                                }
                            });
                              projectList.setAdapter(myAdapter);
//                            projectAdapter = new ProjectAdapter(getApplicationContext(),arrayList,R.layout.project_item);
//                            projectList.setAdapter(projectAdapter);
                        } catch (Exception e) {
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


        projectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1 = new Intent(getApplicationContext(),DevicesActivity.class);
                intent1.putExtra("projectID",arrayList.get(i).getProjectID());
                intent1.putExtra("projectName",arrayList.get(i).getName());
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
