package com.zhanggouzi.servicecard.bean;

/**
 * 一条映射规则
 */
public class MappingRule {
    private String id;
    private String action;
    private String args;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }
}
