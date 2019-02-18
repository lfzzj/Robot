package com.hamitao.kids.utils;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.framework.model.MediaInfo;
import com.hamitao.framework.utils.Logger;
import com.hamitao.framework.utils.TimeUtil;
import com.hamitao.kids.app.MyApp;
import com.hamitao.kids.manager.SPManager;
import com.hamitao.kids.model.CourseScheduleBean;
import com.hamitao.kids.model.ReceiveMsgInfo;
import com.hamitao.kids.model.RelationInfo;
import com.hamitao.requestframe.entity.ContentsBean;
import com.hamitao.requestframe.entity.CourseScheduleInfo;
import com.hamitao.requestframe.entity.ParseChineseInfo;
import com.hamitao.requestframe.entity.QueryContactInfo;
import com.hamitao.requestframe.entity.QueryContentByContentIdInfo;
import com.hamitao.requestframe.entity.QueryContentByMediaIdInfo;
import com.hamitao.requestframe.entity.QueryContentInfo;
import com.hamitao.requestframe.entity.QueryNfcByIdInfo;
import com.hamitao.requestframe.entity.QueryPublishedCourseScheduleInfo;
import com.hamitao.requestframe.entity.QueryRelationInfo;
import com.hamitao.requestframe.entity.QueryVoiceRecordingByIDInfo;
import com.hamitao.requestframe.entity.RequestCourseScheduleInfo;
import com.hamitao.requestframe.entity.RequestPushMsgInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @data on 2018/4/4 17:33
 * @describe:
 */

public class Util {


    /**
     * 设置上传到极光服务器的数据
     *
     * @param action
     * @param httpURL
     * @return
     */
    public static RequestPushMsgInfo setRequestPushMsgInfo(String action, String contents, String httpURL, String sourceChannelid, String targetChannelid) {
        RequestPushMsgInfo info = new RequestPushMsgInfo();
        RequestPushMsgInfo.ResponseDataObjBean responseDataObjBean = new RequestPushMsgInfo.ResponseDataObjBean();
        RequestPushMsgInfo.ResponseDataObjBean.ChannelMsgBean channelMsgBean = new RequestPushMsgInfo.ResponseDataObjBean.ChannelMsgBean();
        RequestPushMsgInfo.ResponseDataObjBean.ChannelMsgBean.ActionResultBean actionResultBean = new RequestPushMsgInfo.ResponseDataObjBean.ChannelMsgBean.ActionResultBean();
        actionResultBean.setAction(action);
        actionResultBean.setHttpURL(httpURL);
        actionResultBean.setContents(contents);
        actionResultBean.setUrltype(BaseConstant.MEDIA_CONTENT_YPE_KEYONOSS);
        channelMsgBean.setActionResult(actionResultBean);
        channelMsgBean.setSource_channelid(sourceChannelid);
        channelMsgBean.setTarget_channelid(targetChannelid);
        responseDataObjBean.setChannelMsg(channelMsgBean);
        info.setResponseDataObj(responseDataObjBean);
        return info;
    }

    /**
     * 获取手机端推送过来的消息
     *
     * @param message
     * @return
     */
    public static ReceiveMsgInfo getReceiveMsgInfo(String message) {
        Gson gson = new Gson();
        return gson.fromJson(message, ReceiveMsgInfo.class);
    }


    /**
     * 根据网络搜索到的内容转换成需要播放的内容
     *
     * @param info
     * @return
     */
    public static List<MediaInfo> getMediaInfoByNetSeach(ParseChineseInfo.ResponseDataObjBean.NlpParseAnswersBean info) {
        List<ContentsBean> contentsBeanList = info.getContents();
        //获取到全部数据
        List<ContentsBean.MediaListBean> mMediaDatas = MsgHandleUtil.getMediaList(contentsBeanList);
        List<MediaInfo> mediaInfos = new ArrayList<>();
        if (mMediaDatas != null && mMediaDatas.size() > 0) {
            MediaInfo mediaInfo;
            for (int i = 0; i < mMediaDatas.size(); i++) {
                ContentsBean.MediaListBean mediaListBean = mMediaDatas.get(i);
                mediaInfo = new MediaInfo(mediaListBean.getMediacontent(), mediaListBean.getMediatype(), mediaListBean.getMediatitle());
                mediaInfos.add(mediaInfo);
            }
        }
        return mediaInfos;
    }

    /**
     * 根据网络搜索到的内容转换成需要播放的内容
     *
     * @param info
     * @return
     */
    public static List<MediaInfo> getMediaInfoByNetSeach1(ParseChineseInfo.ResponseDataObjBean.NlpParseAnswersBean info) {
        List<ContentsBean> contentsBeanList = info.getContents();
        //获取到全部数据
        List<ContentsBean.MediaListBean> mMediaDatas = MsgHandleUtil.getMediaList(contentsBeanList);
        List<MediaInfo> mediaInfos = new ArrayList<>();
        MediaInfo mediaInfo;
        for (int i = 0; i < mMediaDatas.size(); i++) {
            ContentsBean.MediaListBean mediaListBean = mMediaDatas.get(i);

            String mediaType = mediaListBean.getMediatype();
            String mediaUrl = mediaListBean.getMediacontent();

            String mediaTitle = mediaListBean.getMediatitle();
            mediaInfo = new MediaInfo(mediaUrl, mediaType, mediaTitle);
            mediaInfos.add(mediaInfo);
        }
        return mediaInfos;
    }


    /**
     * 将接收到的数据转化为统一的数据
     *
     * @param contentsBean
     * @return
     */
    public static List<MediaInfo> getContentInfoByMedia(QueryContentByMediaIdInfo.ResponseDataObjBean.ContentsBean contentsBean) {
        List<QueryContentByMediaIdInfo.ResponseDataObjBean.ContentsBean.MediaListBean> mediaListBeans = contentsBean.getMediaList();
        List<MediaInfo> mediaInfos = new ArrayList<>();
        for (int i = 0; i < mediaListBeans.size(); i++) {
            String mediaContent = mediaListBeans.get(i).getMediacontent();
            String mediaTitle = mediaListBeans.get(i).getMediatitle();
            String mediaType = mediaListBeans.get(i).getMediatype();
            mediaInfos.add(new MediaInfo(mediaContent, mediaType, mediaTitle));
        }
        return mediaInfos;
    }

    public static List<MediaInfo> getContentInfoByContent(QueryContentByContentIdInfo.ResponseDataObjBean.ContentsBean contentsBean) {
        List<QueryContentByContentIdInfo.ResponseDataObjBean.ContentsBean.MediaListBean> mediaListBeans = contentsBean.getMediaList();
        List<MediaInfo> mediaInfos = new ArrayList<>();
        for (int i = 0; i < mediaListBeans.size(); i++) {
            String mediaContent = mediaListBeans.get(i).getMediacontent();
            String mediaTitle = mediaListBeans.get(i).getMediatitle();
            String mediaType = mediaListBeans.get(i).getMediatype();
            mediaInfos.add(new MediaInfo(mediaContent, mediaType, mediaTitle));
        }
        return mediaInfos;
    }


    /**
     * 根据url解析成文本
     *
     * @param urlStr
     * @return
     */
    public static String getTxtByUrl(String urlStr) {
//        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//                .detectDiskReads().detectDiskWrites().detectNetwork()
//                .penaltyLog().build());
//        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
//                .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
//                .penaltyLog().penaltyDeath().build());
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(60 * 1000);
            conn.setReadTimeout(60 * 1000);
            // 取得inputStream，并进行读取
            InputStream input = conn.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取电话号码
     */
    public static String getPhoneNumByRelation(List<QueryRelationInfo.ResponseDataObjBean.RelationBean.CustomerInfosBean> customerInfosLists, String mBindName) {
        return getInfoByRelation(customerInfosLists, mBindName, BaseConstant.FLAG_RELATION_LOGINNAME);
    }

    /**
     * 判断bindname在关系表中是否存在
     */
    public static String getIsExistByRelation(List<QueryRelationInfo.ResponseDataObjBean.RelationBean.CustomerInfosBean> customerInfosLists, String mBindName) {
        return getInfoByRelation(customerInfosLists, mBindName, BaseConstant.FLAG_RELATION_BINDNAME);
    }

    /**
     * 获取channelId
     */
    public static String getChannelidByRelation(List<QueryRelationInfo.ResponseDataObjBean.RelationBean.CustomerInfosBean> customerInfosLists, String mBindName) {
        return getInfoByRelation(customerInfosLists, mBindName, BaseConstant.FLAG_RELATION_CHANNELID);
    }

    /**
     * 根据关系获取信息
     *
     * @param customerInfosLists 关系列表
     * @param mBindName          对象
     * @param flag               返回数据   bindName/loginname
     * @return
     */
    public static String getInfoByRelation(List<QueryRelationInfo.ResponseDataObjBean.RelationBean.CustomerInfosBean> customerInfosLists,
                                           String mBindName, int flag) {
        if (mBindName.equals(null)) {
            return null;
        }
        if (customerInfosLists != null && customerInfosLists.size() != 0) {
            for (int i = 0; i < customerInfosLists.size(); i++) {
                QueryRelationInfo.ResponseDataObjBean.RelationBean.CustomerInfosBean customerInfosBean = customerInfosLists.get(i);
                String bindName = customerInfosBean.getBindname();
                if (bindName != null && bindName.equals(mBindName)) {
                    if (flag == BaseConstant.FLAG_RELATION_BINDNAME) {
                        return bindName;
                    } else if (flag == BaseConstant.FLAG_RELATION_LOGINNAME) {
                        return customerInfosBean.getLoginname();
                    } else if (flag == BaseConstant.FLAG_RELATION_CHANNELID) {
                        return customerInfosBean.getChannelid_listen_on_pushserver();
                    }
                }
            }
        }
        return null;
    }

    public static RelationInfo getRelationByBindName(List<QueryRelationInfo.ResponseDataObjBean.RelationBean.CustomerInfosBean> customerInfosLists, String mBindName) {
        if (mBindName.equals(null)) {
            return null;
        }
        if (customerInfosLists != null && customerInfosLists.size() != 0) {
            for (int i = 0; i < customerInfosLists.size(); i++) {
                QueryRelationInfo.ResponseDataObjBean.RelationBean.CustomerInfosBean customerInfosBean = customerInfosLists.get(i);
                String bindName = customerInfosBean.getBindname();
                if (bindName != null && bindName.equals(mBindName)) {
                    return new RelationInfo(customerInfosBean.getBindname(), customerInfosBean.getLoginname(),
                            true, 0, customerInfosBean.getChannelid_listen_on_pushserver());
                }
            }
        }
        return null;
    }

    /**
     * 解析课程表
     *
     * @param courseScheduleList
     * @param MyApp.getInstance().getSpManager()
     * @return
     */
    public static List<CourseScheduleBean> getCourseScheduleList(List<QueryPublishedCourseScheduleInfo.ResponseDataObjBean.CourseScheduleListBean> courseScheduleList, SPManager spManager) {
        List<CourseScheduleBean> courseScheduleBean = new ArrayList<>();
        String courseId = "";

        for (int i = 0; i < courseScheduleList.size(); i++) {
            //获取课程表
            QueryPublishedCourseScheduleInfo.ResponseDataObjBean.CourseScheduleListBean courseScheduleListBean = courseScheduleList.get(i);
            //获取课程
            List<QueryPublishedCourseScheduleInfo.ResponseDataObjBean.CourseScheduleListBean.ScheduleContentsBean> scheduleContents = courseScheduleListBean.getScheduleContents();
            //课表的开始事件
            String starttime = courseScheduleListBean.getScheduleplan().getStarttime();
            //课表ID
            String id = TimeUtil.getCourseSchedule() + i;
            //用于存放解析后的课程
            List<RequestCourseScheduleInfo> requestCourseScheduleInfos = new ArrayList<>();
            //遍历课程
            for (int j = 0; j < scheduleContents.size(); j++) {
                //第J节课
                QueryPublishedCourseScheduleInfo.ResponseDataObjBean.CourseScheduleListBean.ScheduleContentsBean scheduleContentsBean = scheduleContents.get(j);
                //课程类型
                String infoType = scheduleContentsBean.getInfotype();
                //课程ID
                String info = scheduleContentsBean.getInfo();
                //将当天的课程加入到播放列表中
                if (scheduleContentsBean.getSchedule().getDay().equals(AlarmManagerUtil.getToday())) {
                    if (!TextUtils.isEmpty(infoType) && !TextUtils.isEmpty(infoType) && !TextUtils.isEmpty(infoType)) {
                        requestCourseScheduleInfos.add(new RequestCourseScheduleInfo(info, infoType));
                    }
                }
            }
            Logger.d("way", "requestCourseScheduleInfos=" + requestCourseScheduleInfos.size());
            //添加解析后的课程到集合中
            courseScheduleBean.add(new CourseScheduleBean(starttime, id, requestCourseScheduleInfos));
            courseId = courseId + id + ",";
        }
        //将课程ID存储到本地
        MyApp.getInstance().getSpManager().putCourseScheduleId(courseId);
        return courseScheduleBean;
    }


    /**
     * 根据课程表的内容 拿到播放内容
     *
     * @return
     */
    public static List<MediaInfo> getMediaInfoByCourseSchedule(CourseScheduleInfo info) {
        //专辑
        List<CourseScheduleInfo.ResponseDataObjBean.ContentsBean> contentsBeans = info.getResponseDataObj().getContents();
        //录音
        List<CourseScheduleInfo.ResponseDataObjBean.VoiceRecordingsBean> voiceRecordingsBeans = info.getResponseDataObj().getVoiceRecordings();

        List<MediaInfo> mediaInfos = new ArrayList<>();

        if (contentsBeans != null && contentsBeans.size() != 0) {
            for (int i = 0; i < contentsBeans.size(); i++) {
                List<CourseScheduleInfo.ResponseDataObjBean.ContentsBean.MediaListBean> mediaListBeans = contentsBeans.get(i).getMediaList();
                for (int j = 0; j < mediaListBeans.size(); j++) {
                    String mediaContent = mediaListBeans.get(j).getMediacontent();
                    String mediaType = mediaListBeans.get(j).getMediatype();
                    mediaInfos.add(new MediaInfo(mediaContent, mediaType, mediaListBeans.get(j).getMediatitle()));
                }
            }
        }
        if (voiceRecordingsBeans != null && voiceRecordingsBeans.size() != 0) {
            for (int i = 0; i < voiceRecordingsBeans.size(); i++) {
                CourseScheduleInfo.ResponseDataObjBean.VoiceRecordingsBean voiceRecordingsBean = voiceRecordingsBeans.get(i);
                mediaInfos.add(new MediaInfo(voiceRecordingsBean.getHttpURL(), voiceRecordingsBean.getName(), BaseConstant.TYPE_AUDIO));
            }
        }
        return mediaInfos;
    }

    /**
     * 根据查询内容拿到播放内容
     *
     * @param info
     * @return
     */
    public static List<MediaInfo> getMediaInfoByContentInfo(QueryContentInfo info) {
        List<MediaInfo> mediaInfos = new ArrayList<>();
        if (BaseConstant.SUCCESS.equalsIgnoreCase(info.getResult()) && info.getResponseDataObj() != null) {
            List<QueryContentInfo.ResponseDataObjBean.ContentsBean> contentsBeans = info.getResponseDataObj().getContents();
            if (contentsBeans != null && contentsBeans.size() != 0) {
                for (int i = 0; i < contentsBeans.size(); i++) {
                    List<QueryContentInfo.ResponseDataObjBean.ContentsBean.MediaListBean> mediaListBeans = contentsBeans.get(i).getMediaList();
                    if (mediaListBeans != null && mediaListBeans.size() != 0) {
                        for (int j = 0; j < mediaListBeans.size(); j++) {
                            String mediacontent = mediaListBeans.get(j).getMediacontent();
                            if (TextUtils.isEmpty(mediacontent)) {
                                continue;
                            }
                            String mediaContent = mediacontent;
                            String mediaType = mediaListBeans.get(j).getMediatype();
                            if (mediaType.equalsIgnoreCase(BaseConstant.TYPE_VIDEO)) {
                                String mediasubtype = mediaListBeans.get(j).getMediasubtype();
                                if (!BaseConstant.TYPE_VIDEO_MP4.equalsIgnoreCase(mediasubtype)) {
                                    continue;
                                }
                            }
                            mediaInfos.add(new MediaInfo(mediaContent, mediaType, mediaListBeans.get(j).getMediatitle()));
                        }
                    }
                }
            }
        }
        return mediaInfos;
    }

    public static List<QueryNfcByIdInfo.ResponseDataObjBean.NFCCardsBean.ContentDescBean.MediaListBean> getMediaListByNfcId(List<QueryNfcByIdInfo.ResponseDataObjBean.NFCCardsBean> nfcCardsBeans) {
        List<QueryNfcByIdInfo.ResponseDataObjBean.NFCCardsBean.ContentDescBean.MediaListBean> mediaListBeans = new ArrayList<>();
        if (nfcCardsBeans != null && nfcCardsBeans.size() != 0) {
            QueryNfcByIdInfo.ResponseDataObjBean.NFCCardsBean.ContentDescBean contentDescBean = nfcCardsBeans.get(0).getContentDesc();
            if (contentDescBean != null) {
                mediaListBeans = contentDescBean.getMediaList();
                if (mediaListBeans != null && mediaListBeans.size() != 0) {
                    return mediaListBeans;
                }
            }
        }
        return null;
    }

    public static List<MediaInfo> getMediaInfoByMediaList(List<QueryNfcByIdInfo.ResponseDataObjBean.NFCCardsBean.ContentDescBean.MediaListBean> mediaListBeans) {
        List<MediaInfo> mediaInfos = new ArrayList<>();
        for (int i = 0; i < mediaListBeans.size(); i++) {
            String mediaContent = mediaListBeans.get(i).getMediacontent();
            String mediaType = mediaListBeans.get(i).getMediatype();
            mediaInfos.add(new MediaInfo(mediaContent, mediaType, mediaListBeans.get(i).getMediatitle()));
        }
        return mediaInfos;
    }

    public static String getBindNameByRelation(List<QueryRelationInfo.ResponseDataObjBean.RelationBean.CustomerInfosBean> customerInfos, String sourceChannelid) {
        for (int i = 0; i < customerInfos.size(); i++) {
            String channelid = customerInfos.get(i).getChannelid_listen_on_pushserver();
            if (sourceChannelid != null && channelid.equals(sourceChannelid)) {
                return customerInfos.get(i).getBindname();
            }
        }
        return "";
    }

    public static void setMaxFlingVelocity(RecyclerView recyclerView, int velocity) {
        try {
            Field field = recyclerView.getClass().getDeclaredField("mMaxFlingVelocity");
            field.setAccessible(true);
            field.set(recyclerView, velocity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取录音文件
    public static List<MediaInfo> getVoiceRecordings(QueryVoiceRecordingByIDInfo.ResponseDataObjBean.VoiceRecordingsBean voiceRecordingsBean) {
        List<MediaInfo> mediaInfos = new ArrayList<>();
        String title = voiceRecordingsBean.getName();
        String content = voiceRecordingsBean.getUrl();
        mediaInfos.add(new MediaInfo(content, BaseConstant.TYPE_AUDIO, title));
        return mediaInfos;
    }

    //获取关系列表的Channelid
    public static List<String> getRelationChannelid(QueryRelationInfo info) {
        List<QueryRelationInfo.ResponseDataObjBean.RelationBean.CustomerInfosBean> customerInfos = info.getResponseDataObj().getRelation().getCustomerInfos();
        List<String> channelids = new ArrayList<>();
        for (int i = 0; i < customerInfos.size(); i++) {
            channelids.add(customerInfos.get(i).getChannelid_listen_on_pushserver());
        }
        return channelids;
    }

    //根据名称在电话本中查询电话号码
    public static String getBindNameByContact(List<QueryContactInfo.ResponseDataObjBean.ContactsBean> contactsInfos, String contact) {
        String phoneNum = "";
        for (int i = 0; i < contactsInfos.size(); i++) {
            QueryContactInfo.ResponseDataObjBean.ContactsBean contactsBean = contactsInfos.get(i);
            String contactName = contactsBean.getContactname();
            if (contact != null && contact.equals(contactName)) {
                phoneNum = contactsBean.getPhonenum();
            }
        }
        return phoneNum;
    }

}
