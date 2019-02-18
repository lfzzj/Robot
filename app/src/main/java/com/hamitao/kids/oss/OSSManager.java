package com.hamitao.kids.oss;

import android.content.Context;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;

/**
 * @data on 2018/3/29 11:04
 * @describe:
 */

public class OSSManager {
    private Context mContext;
    private OssService ossService;    //OSS的上传下载

    public OSSManager(Context context) {
        mContext = context;
        ossService = initOSS(OssConfig.endpoint, OssConfig.bucket);
    }

    private OssService initOSS(String endpoint, String bucket) {
        OSSCredentialProvider credentialProvider = new OssProvider(OssConfig.STS_SERVER);
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSS oss = new OSSClient(mContext, endpoint, credentialProvider, conf);
        return new OssService(oss, bucket);
    }

    /**
     * 获取url
     *
     * @param objectName
     * @return
     */
    public String getOssConstrainedObjectURL(String objectName) {
        return ossService.getOssConstrainedObjectURL(objectName);
    }

    /**
     * 上传图片
     *
     * @param object
     * @param localFile
     */
    public void photographUpload(String object, String localFile) {
        ossService.asyncPutImage(object, localFile);
    }

    /**
     * 删除照片
     *
     * @param object
     */
    public void photographDel(String object) {
        ossService.delete(object);
    }

    public void photoDownload(String localFile) {
        ossService.asyncGetImage(localFile);
    }


}
