package com.zhanggouzi.servicecard.bean;

import com.zhanggouzi.servicecard.bean.ServiceCard;

import java.util.List;

/*
{
   "columnTitle":"none",
   "columnColor":"none",
   "cards":[{
       "type":"类型"，
       "style":"样式",
       "filed":[ {  "key":"icon", "label":"标签","value":"值"},{  "key":"title", "label":"标签","value":"值"}]
   },{另外一张卡}
   ]
}
 */
public class MetaData {
    private  String columnTitle;
    private String columnColor;
    private List<ServiceCard> cards;

    public String getColumnTitle() {
        return columnTitle;
    }

    public void setColumnTitle(String columnTitle) {
        this.columnTitle = columnTitle;
    }

    public String getColumnColor() {
        return columnColor;
    }

    public void setColumnColor(String columnColor) {
        this.columnColor = columnColor;
    }

    public List<ServiceCard> getCards() {
        return cards;
    }

    public void setCards(List<ServiceCard> cards) {
        this.cards = cards;
    }
}
