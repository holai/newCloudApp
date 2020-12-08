package com.example.newcloud;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


public class MyAdapter extends BaseAdapter {
    private int listsize;
    private MyCall call;

    public MyAdapter(int listsize, MyCall call) {
        this.listsize = listsize;
        this.call = call;
    }

    @Override
    public int getCount() {
        return listsize;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return call.call(i,view);
    }
}
