package com.community.util;

public interface CommunityConstant {

    /**
     * 激活成功
     */
    int ACTIVATION_SUCCESS = 0;

    /**
     * 重复激活
     */
    int ACTIVATION_REPEAT = 1;

    /**
     * 激活失败
     */
    int ACTIVATION_FAILURE = 2;

    /**
     * 默认状态下的登录凭证超时时间
     */
    int DEFAULT_EXPERIED_SECOND = 3600 * 12;

    /**
     * 记住我后 登录凭证超时时间
     */
    int REMEBER_EXPERID_SECOND = 3600 * 24 * 10;
}
