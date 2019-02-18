package com.hamitao.kids.manager.wakeup;

import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.requestframe.entity.ParseChineseInfo;

public class WakeUpUtil {
    /**
     * 判断服务器数据是否返回正常
     *
     * @return
     */
    public static boolean isNullParseChinese(ParseChineseInfo mParseChineseInfo) {
        if (mParseChineseInfo != null && BaseConstant.SUCCESS.equals(mParseChineseInfo.getResult())
                && mParseChineseInfo.getResponseDataObj() != null
                && mParseChineseInfo.getResponseDataObj().getNlpParseAnswers() != null
                && mParseChineseInfo.getResponseDataObj().getNlpParseAnswers().size() != 0
                && mParseChineseInfo.getResponseDataObj().getNlpParseAnswers().get(0) != null) {
            return true;
        }
        return false;
    }
}
