package com.zhanggouzi.servicecard.bean;

import android.content.Context;
import android.text.TextUtils;

import com.zhanggouzi.servicecard.utils.FileUtil;

public class StyleFactory {
    private static final StyleFactory instance = new StyleFactory();

    public static StyleFactory getInstance() {
        return instance;
    }

    //后续可以从云侧下载
    public String getConfigByStyle(Context context, String module, String style) {
        if (context == null || TextUtils.isEmpty(module)||TextUtils.isEmpty(style)) {
            return "";
        }
        String json = FileUtil.readAssetsFile(context, module+"_"+style+".json");
        if(TextUtils.isEmpty(json)){
            //读取默认style
            json = FileUtil.readAssetsFile(context, style+".json");
        }
        return json;
    }
}
