package com.zhanggouzi.servicecard.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public abstract class UIUtil {
    private static final String TAG = "UIUtil";

    public static void startActivity(Context context, Intent intent) {
        if (context == null || intent == null) {
            Log.e(TAG, "Failed to start Activity");
            return;
        }
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            Log.e(TAG, "Failed to start Activity  " + e.getMessage());
        }
    }
}
