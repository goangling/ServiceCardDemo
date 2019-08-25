package com.zhanggouzi.servicecard.bean;

import android.text.TextUtils;

public class DestinationConfigure {
    public static final int ACTIVITY_TYPE = 1;  //通过Activity跳转
    public static final int ACTION_TYPE = 2;   //通过action跳转

    private static final String ACTIVITY_TAG = "@Activity/";
    private static final String ACTION_TAG = "@Action/";
    private int type;  //1 activity
    private String url; //跳转目的地

    public DestinationConfigure(String action) {
        if (action.startsWith(ACTIVITY_TAG)) {
            type = ACTIVITY_TYPE;
            url = action.substring(ACTIVITY_TAG.length());
        }else if (action.startsWith(ACTION_TAG)) {
            type = ACTION_TYPE;
            url = action.substring(ACTION_TAG.length());
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean valid() {
        if (type <= 0) {
            return false;
        }
        if (TextUtils.isEmpty(url)) {
            return false;
        }
        return true;
    }


}
