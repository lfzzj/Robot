package com.hamitao.kids.manager.play;

import com.hamitao.kids.manager.play.callback.OnTextPlayCallBack;
import com.hamitao.kids.utils.Util;

public class TextPlayUtil {

    public static void getTxtByUrl(String url, OnTextPlayCallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String txt = Util.getTxtByUrl(url);
                if (callBack != null) {
                    callBack.OnTextReader(txt);
                }
            }
        }).start();
    }



}
