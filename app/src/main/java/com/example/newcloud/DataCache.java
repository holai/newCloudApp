package com.example.newcloud;

public class DataCache {
    private static String Baseurl;
    private static String Baseuse;
    private static String Token;

    public static String getBaseurl() {
        return Baseurl;
    }

    public static void setBaseurl(String baseurl) {
        Baseurl = baseurl;
    }


    public static String getBaseuse() {
        return Baseuse;
    }

    public static void setBaseuse(String baseuse) {
        Baseuse = baseuse;
    }

    public static String getToken() {
        return Token;
    }

    public static void setToken(String token) {
        Token = token;
    }
}
