package com.zhanggouzi.servicecard;

public interface IColumn {
    /**
     * 获取该栏目的名称
     * @return
     */
    String getName();

    /**
     * 设置该栏目的数据
     * @param json
     */
    void setData(String json);
}
