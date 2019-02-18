package com.hamitao.kids.oss;

import android.content.Context;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.common.auth.OSSAuthCredentialsProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationToken;
import com.google.gson.Gson;
import com.hamitao.requestframe.entity.QueryKeyOnOssInfo;
import com.hamitao.requestframe.presenter.QueryKeyOnOssPresenter;

/**
 * @data on 2018/3/15 15:06
 * @describe:
 */

public class OssProvider extends OSSFederationCredentialProvider {
    private String mAuthServerUrl;
    private OSSAuthCredentialsProvider.AuthDecoder mDecoder;

    private QueryKeyOnOssPresenter queryKeyOnOssPresenter;

    public OssProvider(String authServerUrl) {
        this.mAuthServerUrl = authServerUrl;
    }

    /**
     * set auth server url
     *
     * @param authServerUrl
     */
    public void setAuthServerUrl(String authServerUrl) {
        this.mAuthServerUrl = authServerUrl;
    }

    /**
     * set response data decoder
     *
     * @param decoder
     */
    public void setDecoder(OSSAuthCredentialsProvider.AuthDecoder decoder) {
        this.mDecoder = decoder;
    }

    @Override
    public OSSFederationToken getFederationToken() throws ClientException {
        OSSFederationToken authToken;
        String authData;

        try {
            authData = IOUtil.get(mAuthServerUrl);
            QueryKeyOnOssInfo.ResponseDataObjBean.STSBean stsBean = getOssAuthData(authData);
            if (stsBean != null) {
                String ak = stsBean.getAccessKeyId();
                String sk = stsBean.getAccessKeySecret();
                String token = stsBean.getSecurityToken();
                String expiration = stsBean.getExpiration();
                authToken = new OSSFederationToken(ak, sk, token, expiration);
            } else {
                throw new ClientException("ErrorCode: " + "| ErrorMessage: ");
            }
            return authToken;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClientException(e);
        }
    }


    public interface AuthDecoder {
        String decode(String data);
    }

    private QueryKeyOnOssInfo.ResponseDataObjBean.STSBean getOssAuthData(String authData) {
        Gson gson = new Gson();
        QueryKeyOnOssInfo bean = gson.fromJson(authData, QueryKeyOnOssInfo.class);
        if (bean.getResult().equals("success")) {
            return bean.getResponseDataObj().getSTS();

        }
        return null;
    }


}