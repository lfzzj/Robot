package com.hamitao.kids.keyboard;

import java.util.ArrayList;
import java.util.List;

public class KeyboardUtil {

    /**
     * 获取数字
     *
     * @return
     */
    public static List<String> getNumDatas() {
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            datas.add(i + "");
        }
        return datas;
    }

    /**
     * 获取大写字母
     *
     * @return
     */
    public static List<String> getCapitalLetters() {
        List<String> datas = new ArrayList<String>();
        for (int i = 'A'; i <= 'Z'; i++) {
            datas.add("" + (char) i);
        }
        return datas;
    }

    /**
     * 获取小写字母
     *
     * @return
     */
    public static List<String> getLowercaseLetters() {
        List<String> datas = new ArrayList<String>();
        for (int i = 'a'; i <= 'z'; i++) {
            datas.add("" + (char) i);
        }
        return datas;
    }

    /**
     * 获取符号数据
     *
     * @return
     */
    public static List<String> getSymbolData() {
        List<String> datas = new ArrayList<String>();
        for (int i = '!'; i <= '/'; i++) {
            datas.add("" + (char) i);
        }
        for (int j = ':'; j <= '@'; j++) {
            datas.add("" + (char) j);
        }
        for (int k = '['; k <= '_'; k++) {
            datas.add("" + (char) k);
        }
        datas.add("、");

        for (int a = '{'; a <= '}'; a++) {
            datas.add("" + (char) a);
        }
        datas.add("`");

        return datas;
    }

    public static List<String> getCurData(int curChange,boolean isCapital) {
        List<String> datas = new ArrayList<String>();
        if (curChange == 0) {
            if (isCapital){
                datas = getCapitalLetters();
            }else{
                datas = getLowercaseLetters();
            }
        } else if (curChange == 1) {
            datas = getNumDatas();
        } else {
            datas = getSymbolData();
        }

        return datas;
    }

    /**
     * 获取大小写的数据
     *
     * @param isCapital 是否是大写
     * @return
     */
    public static List<String> getCaseData(boolean isCapital) {
        return isCapital ? getCapitalLetters() : getLowercaseLetters();
    }
}
