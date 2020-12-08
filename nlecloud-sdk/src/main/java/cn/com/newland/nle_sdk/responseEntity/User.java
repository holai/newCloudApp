package cn.com.newland.nle_sdk.responseEntity;

import java.io.Serializable;

/**
 * Created by marco on 2017/8/21.
 * 用户信息
 */

public class User implements Serializable {
    private String UserID;
    private String UserName;
    private String AccessToken;
    private String DataToken;

    public void setDataToken(String dataToken) {
        DataToken = dataToken;
    }

    public String getDataToken() {

        return DataToken;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setAccessToken(String accessToken) {
        AccessToken = accessToken;
    }


    public String getUserName() {

        return UserName;
    }

    public String getAccessToken() {
        return AccessToken;
    }


    public String getUserID() {

        return UserID;
    }
}
