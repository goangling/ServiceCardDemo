package com.zhanggouzi.servicecarddemo.model;

import java.util.List;

/*
{
"type":"home",  #home表示首页,以及其他全局配置
"columns":[{
     "container":"com.zhanggouzi.servicecard.twocard.TwoCard",
     "dataUrl":"@assets/card1.json"   #@assets表示本地文件  @uri表示远程网址
},
{另外一栏}
]
}
 */
public class HomeConfig {

    private String type;
    private List<Column> columns;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}
