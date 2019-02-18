package com.hamitao.kids.model;

import java.io.Serializable;

/**
 * @data on 2018/5/29 9:46
 * @describe: 功能
 */

public class FuncBean implements Serializable{
     private String funcName;//name
     private int funcIcon;//icon

    public FuncBean(String funcName, int funcIcon) {
        this.funcName = funcName;
        this.funcIcon = funcIcon;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public void setFuncIcon(int funcIcon) {
        this.funcIcon = funcIcon;
    }

    public String getFuncName() {
        return funcName;
    }

    public int getFuncIcon() {
        return funcIcon;
    }
}
