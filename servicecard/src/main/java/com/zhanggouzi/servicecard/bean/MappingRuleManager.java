package com.zhanggouzi.servicecard.bean;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhanggouzi.servicecard.utils.FileUtil;
import com.zhanggouzi.servicecard.utils.ResUtil;

/**
 * 有多种使用方式
 * 方式1. 类似simplecard，是固定格式，只用来管理action
 * 方式2. 通用的页面， 传入layoutId、serviceCard Json、mapping rule json 即可完成显示
 *
 * TODO 缓存，提高性能
 */
public class MappingRuleManager {
    private Context context;
    private MappingRuleCollection mappingRuleCollection;

    public MappingRuleManager(Context context) {
        this.context = context;
    }

    public boolean init(String jsonFileName) {
        if (context == null || TextUtils.isEmpty(jsonFileName)) {
            return false;
        }
        String json = FileUtil.readAssetsFile(context, jsonFileName);
        mappingRuleCollection = new Gson().fromJson(json, MappingRuleCollection.class);
        if (mappingRuleCollection == null) {
            return false;
        }
        return true;
    }

    public boolean map(View v){
        boolean ret = true;
        for(MappingRule rule: mappingRuleCollection.getRules()){
            ret = ret && mapOneRule(v,rule);
        }
        return ret;
    }
    private boolean mapOneRule(View v , MappingRule rule){
        String idName = rule.getId();
        if(TextUtils.isEmpty(idName)){
            return false;
        }
        int resId = ResUtil.getId(v.getContext(),idName.substring("R.id.".length()));
        View childView = v.findViewById(resId);
        childView.setOnClickListener(new ClickListener(rule));
        return true;
    }

    public static class  ClickListener implements View.OnClickListener{
        private  MappingRule rule;

        public ClickListener(MappingRule rule) {
            this.rule = rule;
        }

        @Override
        public void onClick(View v) {

            Toast.makeText(v.getContext(),"action is "+ rule.getAction(),Toast.LENGTH_SHORT).show();
        }
    }

}