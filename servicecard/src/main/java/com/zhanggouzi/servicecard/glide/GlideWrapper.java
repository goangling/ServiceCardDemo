package com.zhanggouzi.servicecard.glide;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhanggouzi.servicecard.utils.FileUtil;
import com.zhanggouzi.servicecard.utils.ResUtil;

public abstract class GlideWrapper {

    private static final String TAG = "GlideWrapper";

    public static void setImage(Context context, String path, ImageView imageView) {
        if (context == null || TextUtils.isEmpty(path) || imageView == null) {
            return;
        }

        if (path.startsWith("http")) {
            Glide.with(context).load(path).into(imageView);
            return;
        }

        if (path.startsWith("file://")) {
            String fileName = path.substring("file://".length());
            byte[] buf = FileUtil.readAssetsFileByte(context, fileName);

            Glide.with(context).load(buf).into(imageView);
            return;
        }

        if (path.startsWith("res://")) {
            String resName = path.substring("res://".length());
            if (resName.startsWith("R.mipmap.")) {
                int resId = ResUtil.getMipmapId(context, resName.substring("R.mipmap.".length()));
                Glide.with(context).load(resId).into(imageView);
            } else if(resName.startsWith("R.drawable.")) {
                int resId = ResUtil.getDrawableId(context, resName.substring("R.drawable.".length()));
                Glide.with(context).load(resId).into(imageView);
            }else{

                Log.e(TAG, "unknown res "+ path);
            }

            return;
        }
    }


}
