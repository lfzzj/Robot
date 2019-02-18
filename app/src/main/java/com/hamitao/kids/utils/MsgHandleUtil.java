package com.hamitao.kids.utils;

import com.hamitao.requestframe.entity.ContentsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @data on 2018/3/27 11:47
 * @describe: 消息处理工具类
 */

public class MsgHandleUtil {

    /**
     * 获取资源列表
     */
    public static List<ContentsBean.MediaListBean> getMediaList(List<ContentsBean> contentsBeanList) {
        List<ContentsBean.MediaListBean> mediaListBeanList = new ArrayList<>();
        List<ContentsBean.MediaListBean> mediaDatas = new ArrayList<>();
        for (int i = 0; i < contentsBeanList.size(); i++) {
            ContentsBean contentsBean = contentsBeanList.get(i);
            mediaListBeanList = contentsBean.getMediaList();
            if (mediaListBeanList!=null&&mediaListBeanList.size()>0){
                mediaDatas.addAll(mediaListBeanList);
            }
        }
        return mediaDatas;
    }
}
