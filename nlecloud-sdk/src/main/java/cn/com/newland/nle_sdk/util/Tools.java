package cn.com.newland.nle_sdk.util;

import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by marco on 2017/8/21.
 * 工具类
 */

public class Tools {

    public static ApiService buildService(String baseUrl) {
        return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(baseUrl).build().create(ApiService.class);
    }

    public static String getObjectFieldMsg(Object object, int spaceNum) {
        if (object == null) {
            return "";
        }
        String spaceStr = "";
        for (int i = 0; i < spaceNum; i++) {
            spaceStr = spaceStr + "" + "\t";
        }
        Field[] fields = object.getClass().getDeclaredFields();
        StringBuffer stringBuffer = new StringBuffer();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                stringBuffer.append(spaceStr).append(field.getName()).append(" : ").append(field.get(object)).append("\n");
            } catch (IllegalAccessException ex) {
                return "";
            }

        }
        return String.valueOf(stringBuffer);
    }

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    public static void printJson(TextView textView, String msg,boolean clear) {
        if (clear) {
            textView.setText("");
        }
        String message;
        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(4);
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(4);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }

        message = LINE_SEPARATOR + message;
        String[] lines = message.split(LINE_SEPARATOR);
        for (String line : lines) {
            textView.append(line + LINE_SEPARATOR);
        }

    }
    public static void printJson(TextView textView, String msg) {
        printJson(textView,msg,true);
    }
}
