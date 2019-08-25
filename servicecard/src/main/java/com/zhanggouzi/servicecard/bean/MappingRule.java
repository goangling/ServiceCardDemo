package com.zhanggouzi.servicecard.bean;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * 一条映射规则
 * <p>
 * 跳转方式
 * 1） new Intent(context, 跳转的类.class);
 * 2） 隐式启动，一个是action， 一个是category，一个是data
 */
public class MappingRule {
    private static final String TAG = "MappingRule";
    private static final String CARD_TAG = "@Card/";
    private String id;
    private String value;
    private String destination;
    private ActionArgs args;

    private DestinationConfigure destinationConfigure;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public ActionArgs getArgs() {
        return args;
    }

    public void setArgs(ActionArgs args) {
        this.args = args;
    }

    public DestinationConfigure getDestinationConfigure() {
        return destinationConfigure;
    }

    public boolean valid() {
        if (TextUtils.isEmpty(id) || !id.startsWith("R.id.")) {
            Log.e(TAG, "id is invalid");
            return false;
        }
        if (TextUtils.isEmpty(destination)) {
            Log.e(TAG, "action is invalid");
            return false;
        }
        if (!destinationConfigure.valid()) {
            Log.e(TAG, "action is invalid");
            return false;
        }
        return true;
    }

    // 预处理rule，将其中的配置通过servicecard替换成实际值
    public boolean process(MappingRule rule, ServiceCard serviceCard) {
        id = rule.getId();
        value = getActualValue(rule.getValue(), serviceCard);
        destination = rule.getDestination();
        destinationConfigure = new DestinationConfigure(destination);
        destinationConfigure.setUrl(getActualValue(destinationConfigure.getUrl(), serviceCard));

        return processArgs(rule.getArgs(), serviceCard);
    }

    private boolean processArgs(ActionArgs ruleArgs, ServiceCard serviceCard) {
        args = new ActionArgs();
        args.setFlags(ruleArgs.getFlags());
        args.setUri(getActualValue(ruleArgs.getUri(), serviceCard));
        args.setPackageName(getActualValue(ruleArgs.getPackageName(), serviceCard));
        args.setClassName(getActualValue(ruleArgs.getClassName(), serviceCard));

        List<ActionArgs.Extra> extraInstList = new ArrayList<>(ruleArgs.getExtras().size());
        for (ActionArgs.Extra extra : ruleArgs.getExtras()) {
            ActionArgs.Extra extraInst = new ActionArgs.Extra();
            extraInst.setKey(extra.getKey());
            extraInst.setValue(getActualValue(extra.getValue(), serviceCard));
            extraInstList.add(extraInst);
        }
        args.setExtras(extraInstList);
        return true;
    }

    //@Card/field[icon].value
    private String getActualValue(String value, ServiceCard serviceCard) {
        if (TextUtils.isEmpty(value)) {
            return "";
        }
        if (value.startsWith(CARD_TAG)) {
            return getValueFromCard(value.substring(CARD_TAG.length()), serviceCard);
        }
        return value; //其他配置，返回原始值
    }

    // field[icon].value
    private String getValueFromCard(String value, ServiceCard serviceCard) {
        if (value.startsWith("field[")) {
            String key = value.substring("field[".length(), value.indexOf(']'));
            String type = value.substring(value.indexOf('.') + 1);
            if (type.equals("key")) {
                return key;
            } else if (type.equals("label")) {
                return serviceCard.getLabelByKey(key);
            } else if (type.equals("value")) {
                return serviceCard.getValueByKey(key);
            }
            Log.e(TAG, "error cfg " + value);
            return "";
        } else if (value.equals("type")) {   //这种类型比较少，后续多时，可以考虑直接从json中获取
            return serviceCard.getType();
        } else if (value.equals("style")) {
            return serviceCard.getStyle();
        } else if(value.equals("all")){
            return new Gson().toJson(serviceCard);
        }
        Log.e(TAG, "unknown " + value);
        return "";
    }
}
