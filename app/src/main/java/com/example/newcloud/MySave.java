package com.example.newcloud;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class MySave {
    private static SharedPreferences sharedPreferences;
    private static MySave mySave;
    public static MySave getInstant(Context context,String name){
        if(mySave == null){
            synchronized (MySave.class){
                if(mySave == null){
                    mySave =new MySave();
                    mySave.sharedPreferences = context.getSharedPreferences(name,Context.MODE_PRIVATE);
                }
            }
        }
        return mySave;
    }

    public static MySave getInstance(Context context){
        return  getInstant(context,"lu");
    }

    public void setSharedPreferences(String key,Object val){
       SharedPreferences.Editor editor=sharedPreferences.edit();
       if(val instanceof  String){
           editor.putString(key, (String) val);
       }else if(val instanceof  Boolean){
           editor.putBoolean(key, (Boolean) val);
       }else if(val instanceof  Integer){
           editor.putInt(key, (Integer) val);
       }else if(val instanceof  Float){
           editor.putFloat(key, (float) val);
       }
       editor.apply();
    }

    public String getStringSharedPreferences(String key){
        return sharedPreferences.getString(key,"");
    }
    public boolean getBooleanSharedPreferences(String key){
        return sharedPreferences.getBoolean(key,false);
    }
}
