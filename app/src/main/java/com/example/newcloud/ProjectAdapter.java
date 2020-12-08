package com.example.newcloud;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.jar.JarEntry;
import java.util.logging.Handler;

import cn.com.newland.nle_sdk.responseEntity.ProjectInfo;

public class ProjectAdapter extends BaseAdapter {
    Context context;
    ArrayList<ProjectInfo> projectInfos;
    int layout;

    public ProjectAdapter(Context context, ArrayList<ProjectInfo> projectInfos, int layout) {
        this.context = context;
        this.projectInfos = projectInfos;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return projectInfos.size();
    }

    @Override
    public ProjectInfo getItem(int i) {
        return projectInfos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ProjectInfo projectInfo = getItem(i);
        Holder holder;
        if(view == null){
            view = LayoutInflater.from(context).inflate(layout,null);
            holder = new Holder();
            holder.project_name = view.findViewById(R.id.project_name);
            holder.project_industry = view.findViewById(R.id.project_industry);
            holder.project_date = view.findViewById(R.id.project_date);
            holder.project_id = view.findViewById(R.id.project_id);
            holder.project_kind = view.findViewById(R.id.project_kind);
            holder.project_tag = view.findViewById(R.id.project_tag);
            view.setTag(holder);
        }else {
            holder = (Holder) view.getTag();
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
        Log.e("dddd", "getView: "+projectInfo.getRemark() );
        return view;
    }

    private class Holder{
        TextView project_name,project_industry,project_id,project_tag,project_date;
        ImageView project_kind;
    }
}
