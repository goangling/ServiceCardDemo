package com.zhanggouzi.servicecard.twocard;

import android.util.Log;

import com.google.gson.Gson;
import com.zhanggouzi.servicecard.bean.MetaData;
import com.zhanggouzi.servicecard.bean.ServiceCard;
import com.zhanggouzi.servicecard.simplecard.CardBean;

import java.util.ArrayList;
import java.util.List;


public class ColumnBean {
    private static final String TAG = "ColumnBean";
    private List<CardBean> cardBeanList = new ArrayList<>(2);

    public boolean fromJson(String json) {

        MetaData metaData = new Gson().fromJson(json, MetaData.class);
        if (metaData == null){
            Log.e(TAG, "failed to get meta data");
            return false;
        }

        //TODO 演示用，栏的相关配置
        String columnTitle = metaData.getColumnTitle();
        Log.i(TAG, "column title is " + columnTitle);

        for(ServiceCard serviceCard: metaData.getCards()){
            CardBean cardBean = new CardBean();
            cardBean.setIcon(serviceCard.getValueByKey("icon"));
            cardBean.setTitle(serviceCard.getValueByKey("title"));
            cardBean.setStyle(serviceCard.getStyle());
            cardBean.setType(serviceCard.getType());
            cardBeanList.add(cardBean);
        }
        return true;
    }

    public CardBean getA() {
        return cardBeanList.size() > 0 ? cardBeanList.get(0) : null;
    }

    public CardBean getB() {
        return cardBeanList.size() > 1 ? cardBeanList.get(1) : null;
    }


}
