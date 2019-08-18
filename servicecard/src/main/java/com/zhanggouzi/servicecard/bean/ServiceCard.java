package com.zhanggouzi.servicecard.bean;

import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;

import java.util.List;

/* serviceCard 定义
第一级是所有service card都有的属性， field内部是某种service card特有的属性
{
       "type":"类型"，
       "style":"样式",
       "field":[ {  "key":"键", "label":"标签","value":"值"}]
   }
 */
public class ServiceCard {
    private static final String TAG ="ServiceCard" ;
    private  String type;
    private  String style;
    private List<Field> field;

    public String getLabelByKey(String key){
        return getPairByKey(key).second;
    }
    public String getValueByKey(String key){
        return getPairByKey(key).first;
    }
    public Pair<String ,String > getPairByKey(String key){
        if(TextUtils.isEmpty(key)||field==null){
            Log.e(TAG,"failed to getPairByKey " + key);
            return new Pair<>(null,null);
        }
        for(Field oneField: field){
            if(key.equals(oneField.getKey())){
                return new Pair<>(oneField.getValue(),oneField.getLabel());
            }
        }
        return new Pair<>(null,null);
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public List<Field> getField() {
        return field;
    }

    public void setField(List<Field> field) {
        this.field = field;
    }
}
