package com.zhanggouzi.servicecard.utils;

import android.content.Context;
import android.text.TextUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public abstract class FileUtil {
    public static String readAssetsFile(Context context, String fileName) {

        byte[] buf = readAssetsFileByte(context, fileName);
        if (buf == null) {
            return null;
        }
        try {
            return new String(buf, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] readAssetsFileByte(Context context, String fileName) {
        if (context == null || TextUtils.isEmpty(fileName)) {
            return null;
        }
        InputStream in = null;
        try {
            in = context.getAssets().open(fileName);
            byte[] buffer = new byte[in.available()];
            in.read(buffer);
            return buffer;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtil.close(in);
        }
        return null;

    }

}
