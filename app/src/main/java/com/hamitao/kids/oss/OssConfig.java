package com.hamitao.kids.oss;

/**
 * @data on 2018/3/15 14:58
 * @describe: Oss配置
 */

public class OssConfig {

    // 访问的endpoint地址
    public static final String endpoint = "oss-cn-hangzhou.aliyuncs.com";

    //callback 测试地址
    public static final String callbackAddress = "http://oss-demo.aliyuncs.com:23450";

//    public static final String STS_SERVER = "http://192.168.1.200:8080/proxy/security/querykeyonoss";//STS 地址
    public static final String STS_SERVER = "http://cloud.kidknow.net:8080/proxy/security/querykeyonoss";//STS 地址

    public static final String uploadFilePath = ""; //本地文件上传地址
    public static final String bucket = "hamitaocontent";
    public static final String uploadObject = "上传object名称";
    public static final String downloadObject = "下载object名称";

    public static final int DOWNLOAD_SUC = 1; //下载成功
    public static final int DOWNLOAD_Fail = 2;//下载失败
    public static final int UPLOAD_SUC = 3;//上传成功
    public static final int UPLOAD_Fail = 4;//上传失败
    public static final int UPLOAD_PROGRESS = 5;
    public static final int LIST_SUC = 6;
    public static final int HEAD_SUC = 7;
    public static final int RESUMABLE_SUC = 8;
    public static final int SIGN_SUC = 9;
    public static final int BUCKET_SUC = 10;
    public static final int GET_STS_SUC = 11;
    public static final int MULTIPART_SUC = 12;
    public static final int STS_TOKEN_SUC = 13;
    public static final int FAIL = 9999;
    public static final int REQUESTCODE_AUTH = 10111;
    public static final int REQUESTCODE_LOCALPHOTOS = 10112;


    public static final int MESSAGE_UPLOAD_2_OSS = 10002;
}
