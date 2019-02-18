package com.hamitao.kids.model;

import com.hamitao.requestframe.entity.GetContentTreeInfo;

import java.util.List;

/**
 * @data on 2018/7/6 14:28
 * @describe:
 */

public class ContentTreeInfo {

    String nodename;
    String nodeheaderimgurl;
    List<GetContentTreeInfo.ResponseDataObjBean.ContentTreeNodesBean.ChildrenBeanX.ChildrenBean> childrenBeans;

    public ContentTreeInfo(String nodename, String nodeheaderimgurl, List<GetContentTreeInfo.ResponseDataObjBean.ContentTreeNodesBean.ChildrenBeanX.ChildrenBean> childrenBeans) {
        this.nodename = nodename;
        this.nodeheaderimgurl = nodeheaderimgurl;
        this.childrenBeans = childrenBeans;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }

    public void setNodeheaderimgurl(String nodeheaderimgurl) {
        this.nodeheaderimgurl = nodeheaderimgurl;
    }

    public void setChildrenBeans(List<GetContentTreeInfo.ResponseDataObjBean.ContentTreeNodesBean.ChildrenBeanX.ChildrenBean> childrenBeans) {
        this.childrenBeans = childrenBeans;
    }

    public String getNodename() {

        return nodename;
    }

    public String getNodeheaderimgurl() {
        return nodeheaderimgurl;
    }

    public List<GetContentTreeInfo.ResponseDataObjBean.ContentTreeNodesBean.ChildrenBeanX.ChildrenBean> getChildrenBeans() {
        return childrenBeans;
    }
}
