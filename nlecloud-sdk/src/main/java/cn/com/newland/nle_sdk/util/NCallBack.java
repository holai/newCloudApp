package cn.com.newland.nle_sdk.util;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class NCallBack<T> implements Callback<T> {
    private Context context;
    private Handler handler;

    protected NCallBack(Context context) {
        this.context = context;
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onResponse(final Call<T> call, final Response<T> response) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (response.body() == null) {
                    onFailure(call, new Throwable("服务器异常"));
                } else {
                    T t = response.body();
                    onResponse(t);
                }
            }
        });
    }

    protected abstract void onResponse(T response);

    @Override
    public void onFailure(Call<T> call, final Throwable t) {
        handler.post(new Runnable() {

            @Override
            public void run() {
                if (t.getClass() == SocketTimeoutException.class) {
                    Toast.makeText(context, "连接超时，请检查网络", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
}
