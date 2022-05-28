package com.hx.imchat.user.login;

import androidx.annotation.Nullable;

/**
 * 身份验证结果：成功（用户详细信息）或错误消息。
 */
class LoginResult {
    @Nullable
    private LoggedInUserView successInfo;
    @Nullable
    private Integer error;

    LoginResult(@Nullable Integer error) {
        this.error = error;
    }

    LoginResult(@Nullable LoggedInUserView successInfo) {
        this.successInfo = successInfo;
    }

    @Nullable
    LoggedInUserView getSuccessInfo() {
        return successInfo;
    }

    @Nullable
    Integer getError() {
        return error;
    }
}