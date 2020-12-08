package com.example.newcloud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.com.newland.nle_sdk.requestEntity.SignIn;
import cn.com.newland.nle_sdk.responseEntity.User;
import cn.com.newland.nle_sdk.responseEntity.base.BaseResponseEntity;
import cn.com.newland.nle_sdk.util.NCallBack;
import cn.com.newland.nle_sdk.util.NetWorkBusiness;

public class MainActivity extends AppCompatActivity {
    private  MySave mySave;
    private  String defultUrl = "api.nlecloud.com";
    private  String defultPort = "80";
    String login_url,login_port,use,password;
    boolean isCheck;
    TextView urlView;
    EditText useView,passwordView;
    Button  loginView;
    CheckBox isCheckView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shared();
        init();
    }

    private void shared(){
        mySave = MySave.getInstance(getApplicationContext());
        login_url= mySave.getStringSharedPreferences("login_url");
        login_port = mySave.getStringSharedPreferences("login_port");
        use  = mySave.getStringSharedPreferences("use");
        password  = mySave.getStringSharedPreferences("password");
        isCheck = mySave.getBooleanSharedPreferences("isCheck");
        if(login_url.equals("")){
            mySave.setSharedPreferences("login_url",defultUrl);
            login_url = defultUrl;
        }
        if(login_port.equals("")){
            mySave.setSharedPreferences("login_port",defultPort);
            login_port = defultPort;
        }
        DataCache.setBaseurl("http://"+login_url+":"+login_port+"/");
    }

    @SuppressLint("SetTextI18n")
    private void init(){
        urlView = findViewById(R.id.login_url);
        useView = findViewById(R.id.use);
        passwordView = findViewById(R.id.password);
        loginView = findViewById(R.id.login);
        isCheckView = findViewById(R.id.isCheck);

        urlView.setText(DataCache.getBaseurl()+"Users/Login/");
        useView.setText(use);
        passwordView.setText(password);
        isCheckView.setChecked(isCheck);

        loginView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String use = String.valueOf(useView.getText());
                final String password = String.valueOf(passwordView.getText());
                if(TextUtils.isEmpty(DataCache.getBaseurl())){
                    Toast.makeText(getApplicationContext(),"服务器地址不能为空！",Toast.LENGTH_SHORT).show();
                }else{
                    if(TextUtils.isEmpty(use) || TextUtils.isEmpty(password)){
                        Toast.makeText(getApplicationContext(),"用户名或密码不能为空！",Toast.LENGTH_SHORT).show();
                    }else {
                        try {
                            NetWorkBusiness netWorkBusiness = new NetWorkBusiness("",DataCache.getBaseurl());
                            netWorkBusiness.signIn(new SignIn(use, password), new NCallBack<BaseResponseEntity<User>>(getApplicationContext()) {
                                @Override
                                protected void onResponse(BaseResponseEntity<User> response) {
    //                                Log.e("ddd", "onResponse: "+response.getStatus() );
    //                                Log.e("ddd",response.getResultObj().getAccessToken());
                                    switch (response.getStatus()){
                                        case 0:
                                            //成功
                                            mySave.setSharedPreferences("use",use);
                                            if(isCheckView.isChecked()){
                                                mySave.setSharedPreferences("password",password);
                                                mySave.setSharedPreferences("isCheck",true);
                                            }
                                            Intent intent = new Intent(getApplicationContext(),ProjectsActivity.class);
                                            intent.putExtra("result",response.getResultObj());
                                            startActivity(intent);
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
                        }catch (Exception ignored){
                            Toast.makeText(getApplicationContext(),ignored.getMessage(),Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.login_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.setting:
                LayoutInflater layoutInflater = getLayoutInflater();
                final View view = layoutInflater.inflate(R.layout.set_login,null);
                final EditText setUrl = view.findViewById(R.id.setUrl);
                final EditText setPort = view.findViewById(R.id.setPort);
                setPort.setText(login_port);
                setUrl.setText(login_url);
                new AlertDialog.Builder(this)
                        .setView(view)
                        .setCancelable(false)
                        .setTitle("设置服务器地址")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String set_url = String.valueOf(setUrl.getText());
                                String set_port = String.valueOf(setPort.getText());
                                DataCache.setBaseurl("http://"+set_url+":"+set_port+"/");
                                urlView.setText("http://"+set_url+":"+set_port+"/Users/Login/");
                                mySave.setSharedPreferences("login_url",set_url);
                                mySave.setSharedPreferences("login_port",set_port);
                            }
                        })
                        .setNegativeButton("取消",null)
                        .show();
                break;
        }
        return true;
    }

}
