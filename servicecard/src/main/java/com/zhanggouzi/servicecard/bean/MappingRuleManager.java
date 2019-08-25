package com.zhanggouzi.servicecard.bean;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhanggouzi.servicecard.glide.GlideWrapper;
import com.zhanggouzi.servicecard.utils.ResUtil;
import com.zhanggouzi.servicecard.utils.UIUtil;

/**
 * 有多种使用方式
 * 方式1. 类似simplecard，是固定格式，只用来管理action
 * 方式2. 通用的页面， 传入layoutId、serviceCard Json、mapping rule json 即可完成显示
 * <p>
 * TODO 缓存，提高性能
 */
public class MappingRuleManager {
    private static final String TAG = "MappingRuleManager";
    private Context context;
    private MappingRuleCollection mappingRuleCollection;

    public MappingRuleManager(Context context) {
        this.context = context;
    }

    public boolean init(String module, String style) {
        String json = StyleFactory.getInstance().getConfigByStyle(context, module, style);
        return initByJsonContent(json);
    }

    public boolean initByJsonContent(String jsonContent) {
        if (TextUtils.isEmpty(jsonContent)) {
            Log.i(TAG, "not need init for mapping is empty");
            return true;
        }
        mappingRuleCollection = new Gson().fromJson(jsonContent, MappingRuleCollection.class);
        if (mappingRuleCollection == null) {
            return false;
        }
        return true;
    }


    public boolean map(View v, ServiceCard serviceCard) {
        boolean ret = true;
        for (MappingRule rule : mappingRuleCollection.getRules()) {
            ret = ret && mapOneRule(v, rule, serviceCard);
        }
        return ret;
    }

    private boolean mapOneRule(View v, MappingRule rule, ServiceCard serviceCard) {
        MappingRule processedRule = new MappingRule();
        processedRule.process(rule,serviceCard);
        if (!processedRule.valid()) {
            return false;
        }

        int resId = ResUtil.getId(v.getContext(), rule.getId().substring("R.id.".length()));
        View childView = v.findViewById(resId);

        // 目前只支持文本、图片
        if (childView instanceof TextView) {
            ((TextView) childView).setText(processedRule.getValue());
        } else if (childView instanceof ImageView) {
            GlideWrapper.setImage(context, processedRule.getValue(), (ImageView) childView);
        }

        childView.setOnClickListener(new CardClickListener(context,processedRule));
        return true;
    }



}