package com.zhanggouzi.servicecarddemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.zhanggouzi.servicecard.IColumn;
import com.zhanggouzi.servicecarddemo.model.Column;
import com.zhanggouzi.servicecarddemo.model.DataFactory;
import com.zhanggouzi.servicecarddemo.model.HomeConfig;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 演示直接写在这里了，实际开发建议使用MVP
 */
public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    LinearLayout llGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llGroup = findViewById(R.id.dynamic_group);


        findViewById(R.id.hello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeConfig homeConfig = DataFactory.getInstance().getHome(MainActivity.this);
                if (homeConfig != null) {
                    Log.i(TAG, "begin init home by homeConfig");
                    initView(homeConfig);
                }
            }
        });

    }

    private void initView(HomeConfig homeConfig) {
        llGroup.removeAllViews();
        for (Column column : homeConfig.getColumns()) {
            String container = column.getContainer();
            if (TextUtils.isEmpty(container)) {
                Log.i(TAG, "container is error");
                continue;
            }


            View v = getColumnView(container);
            IColumn columnView = (IColumn) v;
            if (v != null) {
                Log.i(TAG, "add view : " + columnView.getName());
                Log.i(TAG, "add url : " + column.getDataUrl());
                columnView.setData(DataFactory.getInstance().getDataByUrl(this, column.getDataUrl()));
                llGroup.addView(v);
            }
        }
    }

    private View getColumnView(String container) {


        try {
            Class clazz = Class.forName(container);
            Constructor constructor = clazz.getConstructor(Context.class);
            View v = (View) constructor.newInstance((Context) this);
            return v;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;

    }
}
