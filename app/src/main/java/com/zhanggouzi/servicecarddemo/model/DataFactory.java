package com.zhanggouzi.servicecarddemo.model;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.zhanggouzi.servicecard.utils.FileUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public final class DataFactory {

    private static final String TAG_ASSETS = "@assets/";
    private static DataFactory instance = new DataFactory();

    public static DataFactory getInstance() {
        return instance;
    }

    public HomeConfig getHome(Context context) {
        //TODO 后续可以根据用户从云侧下载，现在从本地获取

        try {
            InputStream inputStream = context.getAssets().open("home.json");
            Reader reader = new InputStreamReader(inputStream);
            return new Gson().fromJson(reader, HomeConfig.class);
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;

    }

    public String getDataByUrl(Context context, String url) {
        if (context == null || TextUtils.isEmpty(url)) {
            return null;
        }
        if (url.startsWith(TAG_ASSETS)) {
            String fileName = url.substring(TAG_ASSETS.length());
            return FileUtil.readAssetsFile(context, fileName);
        }
        return null;
    }
}
